package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import co.istad.mbank.api.account.web.UpdateAccountDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    /*Iterable<Account> findAll();*/

    AccountDto createNew(CreateAccountDto createAccountDto);


    /*AccountDto findById(Integer id)*/;
//
//    void deleteByid(Integer id);
//
//    void disableByid(Integer id);
//
//    AccountDto updateByid(Integer id, UpdateAccountDto updateAccountDto);


}
