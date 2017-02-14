package com.doughepi.repositories;

import com.doughepi.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by dough on 2017-02-13.
 */
public interface RoleRepository extends JpaRepository<RoleModel, UUID>
{
}
