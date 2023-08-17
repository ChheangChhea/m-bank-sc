package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import co.istad.mbank.api.accounttype.AccountTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

       Account createNew(CreateAccountDto createAccountDto);

      /* @Mapping(source = "account.accountType.name", target = "actType")
       AccountDto toAccountDto(Account account);

       List<AccountDto> toAccountDtoList(List<Account> accounts);*/

//       Account fromCreateAccountDto(CreateAccountDto createAccountDto);
}
