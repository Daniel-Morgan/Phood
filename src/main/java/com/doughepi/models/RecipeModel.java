package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ajreicha on 2/18/17.
 */
@Entity
@Table(name = "recipe")
public class RecipeModel {
    private UUID recipeID;
    private UUID userID;
    private UserModel userModel;
    private Date creationDate;
    private String recipeName;
    private String recipeDescription;
    private String recipeCatagory;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "recipeid")
    public UUID getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
    }

    @Column(name = "userid")
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Column(name = "creationdate")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "recipename")
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @Column(name = "recipedescription")
    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Column(name = "recipecatagory")
    public String getRecipeCatagory() {
        return recipeCatagory;
    }

    public void setRecipeCatagory(String recipeCatagory) {
        this.recipeCatagory = recipeCatagory;
    }
}
