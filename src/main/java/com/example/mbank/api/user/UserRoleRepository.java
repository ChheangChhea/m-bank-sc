package com.example.mbank.api.user;

import com.example.mbank.api.auth.Role;
import com.example.mbank.base.BaseJpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRoleRepository extends BaseJpaRepository<UserRole, Integer> {

    List<UserRole> findByUser(User user);

    void deleteByUser(User user);


}
