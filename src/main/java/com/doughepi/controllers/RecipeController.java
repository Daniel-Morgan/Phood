package com.doughepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pjdoughe on 2/28/17.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {


    @RequestMapping("/new")
    public String showRecipeForm(Model model) {
        return "create-recipe";
    }

    @RequestMapping
    public String showRecipePage(Model model) {
        return "recipe";
    }

}
