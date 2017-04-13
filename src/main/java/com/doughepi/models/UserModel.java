package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by dough on 1/30/2017.
 */
@Entity
@Table(name = "\"user\"")
public class UserModel {
    //Primary key for Hibernate.
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", length = 16)
    private UUID userID;

    //User login information.
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "user_username")
    private String userUsername;

    @Column(name = "user_password")
    private String userPassword;

    @Transient
    private String userConfirmationPassword;

    //User personal information.
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_firstName")
    private String userFirstName;

    @Column(name = "user_middle_initial")
    private String userMiddleInitial;

    @Column(name = "user_lastname")
    private String userLastName;

    //Used for testing.
    @Column(name = "other", length = 16)
    private UUID other;

    //User assigned roles.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)"),
            inverseJoinColumns = @JoinColumn(name = "role_id", columnDefinition = "BINARY(16)"))
    private Set<RoleModel> roleSet;

    // Foreign key linking recipes to the user
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userModel", cascade = CascadeType.ALL)
    private List<RecipeModel> recipeModels;


    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmationPassword() {
        return userConfirmationPassword;
    }

    public void setUserConfirmationPassword(String userConfirmationPassword) {
        this.userConfirmationPassword = userConfirmationPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleInitial() {
        return userMiddleInitial;
    }

    public void setUserMiddleInitial(String userMiddleInitial) {
        this.userMiddleInitial = userMiddleInitial;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Set<RoleModel> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<RoleModel> roleSet) {
        this.roleSet = roleSet;
    }

    public UUID getOther() {
        return other;
    }

    public void setOther(UUID other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserModel && ((UserModel) obj).getUserID().equals(getUserID());
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public List<RecipeModel> getRecipeModels() {
        return recipeModels;
    }

    public void setRecipeModels(List<RecipeModel> recipeModels) {
        this.recipeModels = recipeModels;
    }

    @Override
    public String toString() {
        return String.format("[UUID: %s, Username: %s, Email: %s, FirstName: %s, LastName: %s]",
                getUserID(), getUserUsername(), getUserEmail(), getUserFirstName(), getUserLastName());
    }
}
