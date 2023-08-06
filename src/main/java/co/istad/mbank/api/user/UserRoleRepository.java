package co.istad.mbank.api.user;

import co.istad.mbank.base.BaseJpaRepository;

import java.util.List;


public interface UserRoleRepository extends BaseJpaRepository<UserRole, Integer> {

    List<UserRole> findByUser(User user);

    void deleteByUser(User user);


}
