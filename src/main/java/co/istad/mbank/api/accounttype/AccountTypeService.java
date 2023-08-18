package co.istad.mbank.api.accounttype;

import co.istad.mbank.api.accounttype.web.AccountTypeDto;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeDto> findAll();
    AccountTypeDto findById(Integer id);


}
