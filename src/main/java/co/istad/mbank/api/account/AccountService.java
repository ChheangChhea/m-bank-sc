package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {



  Account createNew(CreateAccountDto createAccountDto);

    List<AccountDto> findAll();

    AccountDto findByUuid(String uuid);


}
