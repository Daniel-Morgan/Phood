package com.doughepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ajreicha on 2/18/17.
 */
@Controller
@RequestMapping(value = "/profile", method = RequestMethod.GET)
public class ProfileController {

    @RequestMapping
    public String showProfile() {
        return "profile";
    }

}
