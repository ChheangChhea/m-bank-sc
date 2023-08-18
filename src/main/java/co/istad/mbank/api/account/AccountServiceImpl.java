package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import co.istad.mbank.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final UserAccountRepository userAccountRepository;
    private final AccountMapper accountMapper;



    @Override
    public List<AccountDto> findAll() {

        List<Account> accounts = accountRepository.findAll();

        return accountMapper.toAccountDtoList(accounts);
    }

    @Override
    public Account createNew(CreateAccountDto createAccountDto) {

//        Account account = accountMapper.fromCreateAccountDto(createAccountDto);

        Account account =accountMapper.createNew(createAccountDto);
        account.setUuid(UUID.randomUUID().toString());
        account.setActNo("000-000-001");
        account.setTransferLimit(BigDecimal.valueOf(5000));

        accountRepository.save(account);
        User user = User.builder()
                .id(createAccountDto.userId())
                .build();
        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .account(account)
                .isDisabled(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

         userAccountRepository.save(userAccount);
         return userAccount.getAccount() ;
    }

    @Override
    public AccountDto findByUuid(String uuid) {

        Account account = accountRepository.findByUuid(uuid)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account UUID: %s is not found", uuid))
                );

        return accountMapper.toAccountDto(account);
    }



}
