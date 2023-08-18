package co.istad.mbank.api.accounttype.web;

import co.istad.mbank.api.accounttype.AccountTypeService;
import co.istad.mbank.base.BaseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {
private final AccountTypeService accountTypeService;
        ;


    @GetMapping
    public BaseApi<?> findAll() {

        List<AccountTypeDto> accountTypeDtoList = accountTypeService.findAll();

        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("Account types have been found")
               // .timestamp(LocalDateTime.now())
                .payload(accountTypeDtoList)
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> findByUuid(@PathVariable Integer id){
        AccountTypeDto accountTypeDto =accountTypeService.findById(id);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("Account Ty has been found ")
                .dateTime(LocalDateTime.now())
                .payload(accountTypeDto)
                .build();
    }

}
