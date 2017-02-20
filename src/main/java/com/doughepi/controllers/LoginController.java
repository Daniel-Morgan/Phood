package com.doughepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
