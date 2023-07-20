package com.example.mbank.api.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uuid;
    private String name;
    private String gender;
    private String email;
    private String password;
    private String phoneNumber;
    private String onSignalId;
    private Boolean isStudent;
    private String studentCardNo;
    private String verifyCode;
    private  Boolean isVerifyCode;
    private  Boolean isVerified;
    private  Boolean isDelete;


    @OneToMany(mappedBy ="user" )
    List<UserRole> userRoles;

}
