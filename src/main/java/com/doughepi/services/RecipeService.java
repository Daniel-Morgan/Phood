package com.doughepi.services;

import com.doughepi.models.IngredientModel;
import com.doughepi.models.RecipeCategory;
import com.doughepi.models.RecipeModel;
import com.doughepi.models.UserModel;
import com.doughepi.repositories.RecipeRepository;
import com.doughepi.repositories.UserRepository;
import com.doughepi.serializers.IngredientModelDeserializer;
import com.doughepi.serializers.RecipeModelDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ajreicha on 3/16/17.
 */
@Service
public class RecipeService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    RecipeRepository recipeRepository;


    public void createRecipe(@RequestBody String recipeModel) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RecipeModel.class, new RecipeModelDeserializer());
        gsonBuilder.registerTypeAdapter(IngredientModel.class, new IngredientModelDeserializer());

        Gson gson = gsonBuilder.create();

        RecipeModel createdRecipe = gson.fromJson(recipeModel, RecipeModel.class);
        createdRecipe.setCreationDate(new Date());

        //TODO validation
        UserModel currentLoggedInUser = userService.getCurrentLoggedInUser();
        createdRecipe.setUserModel(currentLoggedInUser);
        currentLoggedInUser.getRecipeModels().add(createdRecipe);

        //TODO remove recipe logging
        UserModel save = userRepository.save(currentLoggedInUser);
        for (RecipeModel model : save.getRecipeModels()) {
            System.out.println(model.getRecipeID());
            System.out.println(model.getRecipeName());
        }

    }


    public List<List<RecipeModel>> getTopRecipeforCategories() {
        List<List<RecipeModel>> categoryRecipeLists = new ArrayList<>();

        for (RecipeCategory recipeCategory : RecipeCategory.values()) {
            List<RecipeModel> recipeList = recipeRepository.getCategoryTopTen(recipeCategory.name());

            if (recipeList != null && !recipeList.isEmpty()) {
                categoryRecipeLists.add(recipeList);
            }
        }

        return categoryRecipeLists;
    }


}
