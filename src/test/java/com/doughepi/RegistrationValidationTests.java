package com.doughepi;

import com.doughepi.models.UserModel;
import com.doughepi.repositories.UserRepository;
import com.doughepi.validation.RegistrationValidator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by dough on 2017-02-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationValidationTests
{

    Logger logger = Logger.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationValidator registrationValidator;

    UserModel validUser;
    Errors errors;

    @Before
    public void setUp() throws Exception
    {
        validUser = userRepository.findOne(UserAccountTests.TEST_ACCOUNT_ID);
        validUser.setUserConfirmationPassword("password");

        errors = new BeanPropertyBindingResult(validUser, "validUser");
    }

    @Test
    public void rejectEmptyUsername() throws Exception
    {
        validUser.setUserUsername("");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userUsername");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyUsername");
    }

    @Test
    public void rejectEmptyEmail() throws Exception
    {
        validUser.setUserEmail("");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userEmail");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyEmail");

    }

    @Test
    public void rejectEmptyPassword() throws Exception
    {
        validUser.setUserPassword("");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userPassword");
        Assert.assertEquals(fieldError.getCode(), "Registration.password.emptyPassword");
    }

    @Test
    public void rejectEmptyConfirmationPassword() throws Exception
    {
        validUser.setUserConfirmationPassword("");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userConfirmationPassword");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyConfirmationPassword");

    }

    @Test
    public void rejectNonMatchingPasswordAndConfirmationPassword() throws Exception
    {
        validUser.setUserPassword("firstPassword");
        validUser.setUserConfirmationPassword("confirmationPassword");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userConfirmationPassword");
        Assert.assertEquals(fieldError.getCode(), "Registration.passwordMatch");
    }

    @Test
    public void rejectPasswordTooLong() throws Exception
    {
        validUser.setUserPassword("asdwiuibubeubwbebuiwbubeiubwiuebiubfliubceubaveaweawefasdfeaweaf");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userPassword");
        Assert.assertEquals(fieldError.getCode(), "Registration.password.tooLong");
    }

    @Test
    public void rejectPasswordTooShort() throws Exception
    {
        validUser.setUserPassword("a");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userPassword");
        Assert.assertEquals(fieldError.getCode(), "Registration.password.tooShort");
    }

    @Test
    public void rejectUsernameTooLong() throws Exception
    {
        validUser.setUserUsername("asdwiuibubeubwbebuiwbubeiubwiuebiubfliubceubaveaweawefasdfeaweaf");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userUsername");
        Assert.assertEquals(fieldError.getCode(), "Registration.username.tooLong");

    }

    @Test
    public void rejectUsernameTooShort() throws Exception
    {
        validUser.setUserUsername("a");
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userUsername");
        Assert.assertEquals(fieldError.getCode(), "Registration.username.tooShort");
    }

    @Test
    public void rejectUsernameInUse() throws Exception
    {
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userUsername");
        Assert.assertEquals(fieldError.getCode(), "Registration.usernameTaken");
    }

    @Test
    public void rejectEmailInUse() throws Exception
    {
        registrationValidator.validateRegistration(validUser, errors);
        FieldError fieldError = errors.getFieldError("userEmail");
        Assert.assertEquals(fieldError.getCode(), "Registration.emailTaken");
    }

    @Test
    public void rejectEmptyFirstName() throws Exception
    {
        validUser.setUserFirstName("");
        registrationValidator.validatePersonal(validUser, errors);
        FieldError fieldError = errors.getFieldError("userFirstName");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyFirstName");
    }

    @Test
    public void rejectEmptyMiddleInitial() throws Exception
    {
        validUser.setUserMiddleInitial("");
        registrationValidator.validatePersonal(validUser, errors);
        FieldError fieldError = errors.getFieldError("userMiddleInitial");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyMiddleInitial");
    }

    @Test
    public void rejectEmptyLastName() throws Exception
    {
        validUser.setUserLastName("");
        registrationValidator.validatePersonal(validUser, errors);
        FieldError fieldError = errors.getFieldError("userLastName");
        Assert.assertEquals(fieldError.getCode(), "Registration.emptyLastName");
    }

    @Test
    public void validUserPasses() throws Exception
    {
        /*
        Generate a random user and email to avoid the user taken checks.
        Set a random password because pulling the test user from the database has an extremely long encrypted
        password.
        */
        validUser.setUserUsername("validusername");
        validUser.setUserEmail("validemail");
        validUser.setUserPassword("password");
        validUser.setUserConfirmationPassword("password");

        registrationValidator.validateRegistration(validUser, errors);
        registrationValidator.validatePersonal(validUser, errors);

        Assert.assertFalse(errors.hasErrors());

        List<ObjectError> allErrors = errors.getAllErrors();
        for (ObjectError allError : allErrors)
        {
            logger.info(allError.getCode());
        }
    }
}
