package co.istad.mbank.api.account;

import co.istad.mbank.api.accounttype.AccountType;
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
