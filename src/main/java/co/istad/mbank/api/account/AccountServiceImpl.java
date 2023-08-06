package co.istad.mbank.api.account;

import co.istad.mbank.api.account.web.AccountDto;
import co.istad.mbank.api.account.web.CreateAccountDto;
import co.istad.mbank.api.account.web.UpdateAccountDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;


  /*  @Override
    public Iterable<Account> findAll() {

        Iterable<Account> accounts = accountRepository.findAll();
        log.info(" ======================{}",accounts);
//        accounts.forEach(account -> log.info("user:{}"
//                , account.getUserAccounts().get(0)));//.getRole().getName()));

        return accounts;
    }*/

    @Override
    public AccountDto createNew(CreateAccountDto createAccountDto) {
        Account newAccount = accountMapper.createAccountDtoToAccount(createAccountDto);
        newAccount.setActNo(UUID.randomUUID().toString());
        newAccount.setActName("qwer");
        System.out.println("create accDTO"+createAccountDto);
        newAccount = accountRepository.save(newAccount);
        AccountDto accountDto = accountMapper.accountToAccountDto(newAccount);
//        return findById(newAccount.getId());
        System.out.println(accountDto);
        return accountDto;

    }

    /*@Override
    public AccountDto findById(Integer id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID  : %s is not found", id)));
        return accountMapper.createAccountDtoToAccount(account);
    }*/

//    @Override
//    public void deleteByid(Integer id) {
//        if (accountRepository.existsById(id)) {
//
//            accountRepository.deleteById(id);
//            return;
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("User UUID  : %s is not found", id));
//
//    }
//
//    @Override
//    public void disableByid(Integer id) {
//        accountRepository.findByActNameAndIsDeletedFalse(id);
//    }
//
//    @Override
//    public AccountDto updateByid(Integer id, UpdateAccountDto updateAccountDto) {
//        Account account = accountRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        String.format("User UUID  : %s is not found", id))
//                );
//        accountMapper.updateAccountDtoToAccount(updateAccountDto, account);
//        System.out.println(account.getId());
//        accountRepository.save(account);
//
//        return accountMapper.accountToAccountDto(account);
//    }


}
