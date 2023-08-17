package co.istad.mbank.api.account.web;

public record CreateAccountDto(String actName,
                               String pin,
                               String actTypeId,
                               String accountType,
                               Integer userId) {

}
