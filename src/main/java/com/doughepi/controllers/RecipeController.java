package com.doughepi.controllers;

import com.doughepi.models.IngredientModel;
import com.doughepi.models.RecipeModel;
import com.doughepi.models.UserModel;
import com.doughepi.repositories.RecipeRepository;
import com.doughepi.repositories.UserRepository;
import com.doughepi.services.UserService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.Arrays;

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

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, UserService userService, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userService  = userService;
        this.userRepository = userRepository;
    }


    @RequestMapping("/new")
    public String showRecipeForm(Model model) {
        return "create-recipe";
    }

    @RequestMapping
    public String showRecipePage(Model model) {
        return "recipe";
    }

    @RequestMapping(value = "/new", params = {"_submit"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String jsonInsertion(@RequestBody String recipeModel) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RecipeModel.class, new RecipeModelDeserializer());
        gsonBuilder.registerTypeAdapter(IngredientModel.class, new IngredientModelDeserializer());

        Gson gson = gsonBuilder.create();

        RecipeModel createdRecipe = gson.fromJson(recipeModel, RecipeModel.class);

        //TODO validation
        UserModel currentLoggedInUser = userService.getCurrentLoggedInUser();
        createdRecipe.setUserModel(currentLoggedInUser);
        currentLoggedInUser.getRecipeModels().add(createdRecipe);
        userRepository.save(currentLoggedInUser);
        return "success";
    }

    private class RecipeModelDeserializer implements JsonDeserializer<RecipeModel> {
        @Override
        public RecipeModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            RecipeModel recipeModel = new RecipeModel();

            JsonObject recipeJson = jsonElement.getAsJsonObject();

            String recipeName = recipeJson.get("name").getAsString();
            String recipeDescription = recipeJson.get("description").getAsString();
            String recipeCategory = recipeJson.get("category").getAsString();

            IngredientModel[] ingredients = jsonDeserializationContext.deserialize(recipeJson.get("ingredientList"), IngredientModel[].class);

            for (IngredientModel ingredient : ingredients) {
                ingredient.setRecipeModel(recipeModel);
            }

            recipeModel.setIngredientModels(Arrays.asList(ingredients));
            recipeModel.setRecipeName(recipeName);
            recipeModel.setRecipeDescription(recipeDescription);
            recipeModel.setRecipeCategory(recipeCategory);

            return recipeModel;
        }
    }

    private class IngredientModelDeserializer implements JsonDeserializer<IngredientModel> {

        @Override
        public IngredientModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject ingredientJson = jsonElement.getAsJsonObject();

            IngredientModel ingredientModel = new IngredientModel();

            String ingredientQuantity = ingredientJson.get("ingredientQuantity").getAsString();
            String ingredientUnit = ingredientJson.get("ingredientUnit").getAsString();
            String ingredientName = ingredientJson.get("ingredientName").getAsString();

            ingredientModel.setIngredientQuantity(Double.parseDouble(ingredientQuantity));
            ingredientModel.setIngredientUnit(ingredientUnit);
            ingredientModel.setIngredientName(ingredientName);

            return ingredientModel;

        }
    }
}
