package com.example.mbank.api.user.web;

import lombok.Builder;

@Builder
public record UserDto(String uuid,
                      String name,
                      String gender,
                      String email,
                      String phoneNumber,
                      Boolean isStudent,
                      String studentCadeNo

) {


}
