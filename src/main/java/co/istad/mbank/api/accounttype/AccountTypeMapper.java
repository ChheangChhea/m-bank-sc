package co.istad.mbank.api.accounttype;


import co.istad.mbank.api.accounttype.web.AccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AccountTypeMapper {


    List<AccountTypeDto> toAccountTypeDtoList(List<AccountType> accountTypes);

    AccountTypeDto toAccountTypeDto(AccountType accountType);
}
