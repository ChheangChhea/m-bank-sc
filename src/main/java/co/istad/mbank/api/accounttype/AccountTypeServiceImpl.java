package co.istad.mbank.api.accounttype;

import co.istad.mbank.api.accounttype.web.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeDto> findAll() {

        List<AccountType> accountTypes = accountTypeRepository.findAll();

        return accountTypeMapper.toAccountTypeDtoList(accountTypes);
    }
    @Override
    public AccountTypeDto findById(Integer id) {

        AccountType accountType = accountTypeRepository.findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account Type ID: %d is not found", id))
                );


        return accountTypeMapper.toAccountTypeDto(accountType);
    }
}
