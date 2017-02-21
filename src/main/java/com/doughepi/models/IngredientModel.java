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

    private UUID ingredientID;
    private UUID recipeID;
    private RecipeModel recipeModel;
    private double ingredientQuantity;
    private String ingredientName;
    private String ingredientUnit;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ingredientID")
    public UUID getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(UUID ingredientID) {
        this.ingredientID = ingredientID;
    }

    @Column(name = "recipeid")
    public UUID getRecipeID() {
        return recipeID;
    }

    @ManyToOne
    @JoinColumn(name = "recipeid", insertable = false, updatable = false)
    public RecipeModel getRecipeModel() {
        return recipeModel;
    }

    public void setRecipeModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
    }

    @Column(name = "ingredientquantity")
    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    @Column(name = "ingredientname")
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Column(name = "ingredientunit")
    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }
}
