package co.istad.mbank.api.account;

import co.istad.mbank.base.BaseJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository extends BaseJpaRepository<Account,Integer> {


//    Optional<Account> findByUuid(String uuid);
//
}

