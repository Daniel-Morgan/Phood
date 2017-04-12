package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ajreicha on 2/18/17.
 */
@Entity
@Table(name = "recipe")
@Indexed
public class RecipeModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "recipe_id", length = 16)
    private UUID recipeID;

    @ManyToOne(cascade = CascadeType.MERGE)
    private UserModel userModel;

    @Column(name = "creation_date", columnDefinition = "DATETIME NOT NULL DEFAULT NOW()")
    private Date creationDate;

    @Column(name = "recipe_name")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String recipeName;

    @Column(name = "recipe_description", columnDefinition = "TEXT")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String recipeDescription;

    @Column(name = "recipe_preperation_instructions")
    private String recipePreparationInstructions;

    @Column(name = "recipe_category")
    @Enumerated(EnumType.STRING)
    private RecipeCategory recipeCategory;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Transient
    private String categoryName;

    @OneToMany(mappedBy = "recipeModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IngredientModel> ingredientModels;

    @OneToMany(mappedBy = "recipeModel", cascade = CascadeType.ALL)
    private List<ImageModel> imageModels;

    @Column(name = "likes")
    private int likes;


    public UUID getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
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

    public String getRecipePreparationInstructions() {
        return recipePreparationInstructions;
    }

    public void setRecipePreparationInstructions(String recipePreparationInstructions) {
        this.recipePreparationInstructions = recipePreparationInstructions;
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public List<IngredientModel> getIngredientModels() {
        return ingredientModels;
    }

    public void setIngredientModels(List<IngredientModel> ingredientModels) {
        this.ingredientModels = ingredientModels;
    }

    public String getCategoryName() {
        return recipeCategory.getEnumText();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<ImageModel> getImageModels() {
        return imageModels;
    }

    public void setImageModels(List<ImageModel> imageModels) {
        this.imageModels = imageModels;
    }
}
