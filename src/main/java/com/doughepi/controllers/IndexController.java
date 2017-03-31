package com.doughepi.controllers;

import com.doughepi.services.RecipeService;
import com.doughepi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dough on 1/30/2017.
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class IndexController
{
    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    /**
     * Basic mapping that returns the index page on client navigation to '/'.
     *
     * @return The name of the template to return to the client.
     */
    @RequestMapping
    public String showIndex(Model model)
    {
        model.addAttribute("categories", recipeService.getTopRecipeforCategories());
        return "index";
    }
}
