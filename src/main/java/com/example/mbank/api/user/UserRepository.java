package com.example.mbank.api.user;

import com.example.mbank.base.BaseJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository  extends BaseJpaRepository<User,Integer> {
    Optional<User> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE User set isDeleted=?1 where uuid =?2")
    void updateIsDeletedByUuid(Boolean isDeleted,String uuid);
}
