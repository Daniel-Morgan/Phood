package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by ajreicha on 2/20/17.
 */
public class ImageModel {

    private UUID imageID;
    private UUID recipeID;
    private RecipeModel recipeModel;
    private Byte[] imageData;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "imageid")
    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
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

    @Column(name = "imagedata")
    public Byte[] getImageData() {
        return imageData;
    }

    public void setImageData(Byte[] imageData) {
        this.imageData = imageData;
    }
}
