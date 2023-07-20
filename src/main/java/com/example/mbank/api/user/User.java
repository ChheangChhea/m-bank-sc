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
    private String verifyCode;
    private String studentCardNo;
    private  Boolean isStudent;
    private  Boolean isDelete;
    private  Boolean isVerifyCode;
    private  Boolean isVerifyField;


    @OneToMany(mappedBy ="user" )
    List<UserRole> userRoles;

}
