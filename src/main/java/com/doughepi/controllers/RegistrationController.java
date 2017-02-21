package com.doughepi.controllers;

import com.doughepi.models.UserModel;
import com.doughepi.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.logging.Logger;

/**
 * Created by dough on 1/30/2017.
 */
@Controller
@RequestMapping(value = "/register")
@SessionAttributes("user")
public class RegistrationController {

    private final RegistrationValidator registrationValidator;
    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RegistrationController(RegistrationValidator registrationValidator) {
        this.registrationValidator =
                registrationValidator;
    }

    @RequestMapping
    public String paramRedirect() {
        return "redirect:/register?page=1";
    }

    @RequestMapping(params = "page=1")
    public String initialPage(final Model model) {
        //Insert a blank user into the model for the registration page.
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        return "/registration/register-1";
    }

    @RequestMapping(params = "page=2")
    public String secondPage(
            final @ModelAttribute("user") UserModel userModel,
            final BindingResult bindingResult
    ) {
        //If the previous page has errors, return to the previous page.
        registrationValidator.validateRegistration(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.warning(String.format("Validation failed for user: %s " +
                    "Failed fields include:", userModel));
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                logger.warning(String.format("Code: %s", fieldError.getCode()));
            }
            return "/registration/register-1";
        }

        return "/registration/register-2";
    }

    @RequestMapping(params = "page=3")
    public String thirdPage(
            final @ModelAttribute("user") UserModel userModel,
            final BindingResult bindingResult,
            final SessionStatus sessionStatus
    ) {
        //If the previous page has errors, return to the previous page.
        registrationValidator.validatePersonal(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/registration/register-2";
        }

        return "/registration/register-3";
    }

    @RequestMapping(params = "_confirm")
    public String processConfirm(
            final @ModelAttribute("user") UserModel userModel,
            final SessionStatus sessionStatus
    ) {
        //If the user clicks confirm, save the account and redirect to the root page.
        sessionStatus.setComplete();
        return "index";
    }

    @RequestMapping(params = "_cancel")
    public String processCancel(
            final SessionStatus sessionStatus
    ) {
        sessionStatus.setComplete();
        return "index";
    }
}
