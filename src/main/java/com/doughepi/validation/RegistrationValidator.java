package com.doughepi.validation;

import com.doughepi.models.UserModel;
import com.doughepi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by dough on 2017-02-13.
 */
@Component
public class RegistrationValidator implements Validator
{

    @Value("${validation}")
    private boolean validationEnabled;

    @Autowired
    private UserService userService;

    private PageEnum pageEnum;

    @Override
    public boolean supports(Class<?> aClass)
    {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        if (!validationEnabled)
        {
            return;
        }

        UserModel userModel = ((UserModel) o);

        switch (pageEnum)
        {
            case REGISTRATION_PAGE:
                //Validation logic for the first page of the registration form.

                //Validate for empty fields.
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userUsername", "Registration.emptyUsername");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "Registration.emptyEmail");
                ValidationUtils.rejectIfEmptyOrWhitespace(
                        errors,
                        "userPassword",
                        "Registration.password.emptyPassword");
                ValidationUtils.rejectIfEmptyOrWhitespace(
                        errors,
                        "userConfirmationPassword",
                        "Registration.emptyConfirmationPassword");

                //Validate for password that doesn't equal confirmation password.
                if (!userModel.getUserConfirmationPassword().equals(userModel.getUserPassword()))
                {
                    errors.rejectValue("userConfirmationPassword", "Registration.passwordMatch");
                }

                //Validate for password of length less than 8 or greater than 24.
                int passwordLength = userModel.getUserPassword().length();
                if (passwordLength > 24)
                {
                    errors.rejectValue("userPassword", "Registration.password.tooLong");
                } else if (passwordLength < 8)
                {
                    errors.rejectValue("userPassword", "Registration.password.tooShort");
                }

                //Validate for username of length less than 8 or greater than 24.
                int usernameLength = userModel.getUserUsername().length();
                if (usernameLength > 24)
                {
                    errors.rejectValue("userUsername", "Registration.username.tooLong");
                } else if (usernameLength < 8)
                {
                    errors.rejectValue("userUsername", "Registration.username.tooShort");
                }

                //Validate for username or email already in use.
                String username = userModel.getUserUsername();
                String email = userModel.getUserEmail();
                if (userService.findByUsername(username) != null)
                {
                    errors.rejectValue("userUsername", "Registration.usernameTaken");
                }

                if (userService.findByEmail(email) != null)
                {
                    errors.rejectValue("userEmail", "Registration.emailTaken");
                }

                break;
            case PERSONAL_DETAILS_PAGE:
                //Validation logic for the second page of the registration form.
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userFirstName", "Registration.emptyFirstName");
                ValidationUtils.rejectIfEmptyOrWhitespace(
                        errors,
                        "userMiddleInitial",
                        "Registration.emptyMiddleInitial");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLastName", "Registration.emptyLastName");
                break;
        }
    }

    public void validateRegistration(UserModel userModel, Errors errors)
    {
        setPage(PageEnum.REGISTRATION_PAGE);
        validate(userModel, errors);
    }

    private void setPage(PageEnum pageEnum)
    {
        this.pageEnum = pageEnum;
    }

    public void validatePersonal(UserModel userModel, Errors errors)
    {
        setPage(PageEnum.PERSONAL_DETAILS_PAGE);
        validate(userModel, errors);
    }

    private enum PageEnum
    {
        REGISTRATION_PAGE, PERSONAL_DETAILS_PAGE
    }
}
