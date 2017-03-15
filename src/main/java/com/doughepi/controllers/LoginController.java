package com.doughepi.controllers;

import com.doughepi.models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by dough on 1/30/2017.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController
{
    @RequestMapping
    public String showLogin()
    {
        return "login";
    }
}
