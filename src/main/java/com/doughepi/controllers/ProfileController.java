package com.doughepi.controllers;

import com.doughepi.models.UserModel;
import com.doughepi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ajreicha on 2/18/17.
 */
@Controller
@RequestMapping(value = "/profile", method = RequestMethod.GET)
public class ProfileController {

    @Autowired
    UserService userService;

    @RequestMapping
    public String showProfile(Model model) {
        UserModel currentLoggedInUser = userService.getCurrentLoggedInUser();
        model.addAttribute("username", currentLoggedInUser.getUserUsername());
        model.addAttribute("name", String.format("%s %s %s", currentLoggedInUser.getUserFirstName(),
                currentLoggedInUser.getUserMiddleInitial(),
                currentLoggedInUser.getUserLastName()));
        model.addAttribute("recipes", currentLoggedInUser.getRecipeModels());

        return "profile";
    }
}
