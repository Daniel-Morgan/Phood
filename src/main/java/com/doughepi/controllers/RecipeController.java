package com.doughepi.controllers;

import com.doughepi.repositories.RecipeRepository;
import com.doughepi.repositories.UserRepository;
import com.doughepi.services.RecipeService;
import com.doughepi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * Created by pjdoughe on 2/28/17.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    final
    RecipeRepository recipeRepository;
    final UserService userService;
    final UserRepository userRepository;
    final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, UserService userService, UserRepository userRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.recipeService = recipeService;
    }


    @RequestMapping("/new")
    public String showRecipeForm(Model model) {
        return "create-recipe";
    }

    @RequestMapping(params = {"recipeID"})
    public String showRecipePage(Model model, @RequestParam("recipeID") UUID recipeID) {

        model.addAttribute("recipe", recipeRepository.findOne(recipeID));

        return "recipe";
    }

    @RequestMapping(value = "/new", params = {"_submit"}, method = RequestMethod.POST)
    public String jsonInsertion(@RequestBody String recipeModel) {
        recipeService.createRecipe(recipeModel);
        return "index";
    }


}
