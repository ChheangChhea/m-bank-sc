package co.istad.mbank.api.account.web;

public record UpdateAccountDto(String actName,
                               Double transferLimit) {
}
