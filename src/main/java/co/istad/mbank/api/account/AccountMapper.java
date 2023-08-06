package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import co.istad.mbank.api.account.web.UpdateAccountDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

       Account createAccountToAccountDto(CreateAccountDto createAccountDto);

       AccountDto AccountToaccountDto(Account account);


       Account createAccountDtoToAccount(CreateAccountDto createAccountDto);


       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void updateAccountDtoToAccount(UpdateAccountDto updateAccountDto, @MappingTarget Account account);

       AccountDto accountToAccountDto(Account account);

       AccountDto createAccountDtoToAccount(Account account);
}
