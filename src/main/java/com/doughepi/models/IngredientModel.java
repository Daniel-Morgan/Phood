package com.doughepi.models;

import org.hibernate.annotations.*;
import org.hibernate.annotations.common.util.StringHelper;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by ajreicha on 2/20/17.
 */
@Entity
@Table(name = "ingredient")
public class IngredientModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ingredientID", length = 16)
    private UUID ingredientID;

    @Column(name = "recipeid", length = 16)
    private UUID recipeID;

    @ManyToOne
    @JoinColumn(name = "recipeid", insertable = false, updatable = false)
    private RecipeModel recipeModel;

    @Column(name = "ingredientquantity")
    private double ingredientQuantity;

    @Column(name = "ingredientname")
    private String ingredientName;

    @Column(name = "ingredientunit")
    private String ingredientUnit;


    public UUID getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(UUID ingredientID) {
        this.ingredientID = ingredientID;
    }

    public UUID getRecipeID() {
        return recipeID;
    }

    public RecipeModel getRecipeModel() {
        return recipeModel;
    }

    public void setRecipeModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }
}
