package com.example.mbank.api.account;

import com.example.mbank.api.accounttype.AccountType;
import com.example.mbank.api.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String actNo;
    private String actName;
    private Double transferLimit;
    private String pin;

    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "accounts")
    private List<UserAccount> userAccounts;
}
