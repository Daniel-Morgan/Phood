package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by ajreicha on 2/20/17.
 */
public class ImageModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "imageid", length = 16)
    private UUID imageID;

    @Column(name = "recipeid", length = 16)
    private UUID recipeID;

    @ManyToOne
    @JoinColumn(name = "recipeid", insertable = false, updatable = false)
    private RecipeModel recipeModel;

    @Column(name = "imagedata")
    private Byte[] imageData;


    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
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


    public Byte[] getImageData() {
        return imageData;
    }

    public void setImageData(Byte[] imageData) {
        this.imageData = imageData;
    }
}
