package com.example.mbank.api.user;

import com.example.mbank.base.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends BaseJpaRepository<User,Integer> {
}
