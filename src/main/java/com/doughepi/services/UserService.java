package com.doughepi.services;

import com.doughepi.models.UserModel;

/**
 * Created by dough on 2017-02-06.
 */
public interface UserService
{

    UserModel findByUsername(String username);

    UserModel findByEmail(String email);

    UserModel save(UserModel userModel);
}
