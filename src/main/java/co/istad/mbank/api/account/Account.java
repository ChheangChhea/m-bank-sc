package co.istad.mbank.api.account;

import co.istad.mbank.api.accounttype.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @Column(unique = true)
    private String uuid;

    private String actNo;
    private String actName;
    private BigDecimal transferLimit;
    private String pin;


    @ManyToOne
    private AccountType accountType;

    @OneToMany
    private List<UserAccount> userAccounts;


}
