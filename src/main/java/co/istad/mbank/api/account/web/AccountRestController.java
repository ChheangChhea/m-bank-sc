package co.istad.mbank.api.account.web;


import co.istad.mbank.api.account.AccountService;
import co.istad.mbank.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountRestController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNew(@Valid @RequestBody CreateAccountDto createAccountDto) {
        accountService.createNew(createAccountDto);
    }

    @GetMapping
    public BaseApi<?> findAll() {

        List<AccountDto> accountDtoList = accountService.findAll();

        return BaseApi.ok()
                .message("Accounts have been found")
                .payload(accountDtoList)
                .build();
    }

    @GetMapping("/{uuid}")
    public BaseApi<?> findByUuid(@PathVariable String uuid) {

        AccountDto accountDto = accountService.findByUuid(uuid);

        return BaseApi.ok()
                .message("Account has been found")
                .payload(accountDto)
                .build();
    }


}
