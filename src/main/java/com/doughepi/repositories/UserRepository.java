package com.doughepi.repositories;

import com.doughepi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by dough on 1/30/2017.
 */
public interface UserRepository extends JpaRepository<UserModel, UUID>
{
    UserModel findByUserUsername(String userUsername);
    UserModel findByUserEmail(String userEmail);
}
