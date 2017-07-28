package com.doughepi.repositories;

import com.doughepi.models.RecipeCategory;
import com.doughepi.models.RecipeModel;
import com.doughepi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by ajreicha on 2/28/17.
 */
public interface RecipeRepository extends JpaRepository<RecipeModel, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM recipe where recipe_category = ?1 order by likes DESC limit 5")
    List<RecipeModel> getCategoryTopTen(String categoryName);

    List<RecipeModel> findRecipeModelsByRecipeCategoryAndUserModel(RecipeCategory recipeCategory, UserModel userModel);

}
