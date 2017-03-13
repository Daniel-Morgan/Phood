package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ajreicha on 2/18/17.
 */
@Entity
@Table(name = "recipe")
public class RecipeModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "recipeid", length = 16)
    private UUID recipeID;

    @Column(name = "userid", length = 16)
    private UUID userID;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private UserModel userModel;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "recipename")
    private String recipeName;

    @Column(name = "recipedescription")
    private String recipeDescription;

    @Column(name = "recipecategory")
    private String recipeCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipeID")
    private List<IngredientModel> ingredientModel;


    public UUID getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public List<IngredientModel> getIngredientModel() {
        return ingredientModel;
    }

    public void setIngredientModel(List<IngredientModel> ingredientModel) {
        this.ingredientModel = ingredientModel;
    }

}
