package co.istad.mbank.api.account.web;

import java.util.List;

public record AccountDto(
                          String actName,
                          String actType,
                          Double transferLimit,
                          String pin,
                          List<Integer> roleIds) {
}
