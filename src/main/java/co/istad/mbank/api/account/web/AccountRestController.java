package co.istad.mbank.api.account.web;


import co.istad.mbank.api.account.Account;
import co.istad.mbank.api.account.AccountService;
import co.istad.mbank.api.user.web.UpdateUserDto;
import co.istad.mbank.api.user.web.UserDto;
import co.istad.mbank.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping
    public BaseApi<?> createActNew(@Valid @RequestBody CreateAccountDto createAccountDto) {
        AccountDto newAccount = accountService.createNew(createAccountDto);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" Account  has been created")
                .dateTime(LocalDateTime.now())
                .payload(newAccount)
                .build();
    }
    /*@GetMapping
    public BaseApi<?> finAll() {

        var accountDto = accountService.findAll();
//        System.out.println(accountDto);
           return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())

                .massage(" Account  have been found")
                .dateTime(LocalDateTime.now())

                .payload(accountDto)
                .build();
    }*/

//    @GetMapping("/{id}")
//    public BaseApi<?> findAccountById(@Valid @PathVariable Integer id) {
//        AccountDto newAccount = accountService.createNew(createAccountDto);
//        return BaseApi.builder()
//                .isSuccess(true)
//                .code(HttpStatus.OK.value())
//                .massage(" Account  has been created")
//                .dateTime(LocalDateTime.now())
//                .payload(newAccount)
//                .build();
//    }
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void deleteByid(@PathVariable Integer id) {
//
//        accountService.deleteByid(id);
//    }
//    @PutMapping("/{id}")
//    public BaseApi<?> updateByUuid(@PathVariable Integer id, @Valid @RequestBody UpdateAccountDto updateAccountDto ){
//        AccountDto updateAccount= accountService.updateByid(id,updateAccountDto);
//        return BaseApi.builder()
//                .isSuccess(true)
//                .code(HttpStatus.OK.value())
//                .massage(" Account  has been found")
//                .dateTime(LocalDateTime.now())
//                .payload(updateAccount)
//                .build();
//
//    }
//    @PutMapping("/{id}/disable")
//    public void disableUuid(@PathVariable Integer id){
//        accountService.disableByid(id);
//
//    }

}
