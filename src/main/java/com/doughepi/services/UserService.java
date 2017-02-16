package com.doughepi.services;

import com.doughepi.models.UserModel;

import java.util.UUID;

/**
 * Created by dough on 2017-02-06.
 */
public interface UserService
{

    UserModel findByUsername(String username);

    UserModel findByEmail(String email);

    UserModel createTestUser(
            UUID testAccountId,
            String testAccountUsername,
            String testAccountPassword,
            String testAccountEmail,
            String testAccountFirstName,
            String testAccountMiddleInitial,
            String testAccountLastName);

    UserModel save(UserModel userModel);
}
