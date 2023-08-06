package co.istad.mbank.api.accounttype;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
}
