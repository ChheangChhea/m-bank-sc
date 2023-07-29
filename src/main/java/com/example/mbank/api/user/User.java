package com.example.mbank.api.user;

import com.example.mbank.api.account.Account;
import com.example.mbank.api.account.UserAccount;
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
    private String verifiedCode;
    private Boolean isVerified;
    private Boolean isDeleted;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserRole> userRoles;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserAccount> userAccounts;
}
