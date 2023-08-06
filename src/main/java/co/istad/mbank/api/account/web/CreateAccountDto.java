package co.istad.mbank.api.account.web;

public record CreateAccountDto(
        String actNo,
        String actName,
        String transferLimit
) {

}
