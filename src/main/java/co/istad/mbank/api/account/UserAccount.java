package co.istad.mbank.api.account;


import co.istad.mbank.api.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean isDisable;

    @ManyToOne
    private User user;

    @ManyToOne
    private Account accounts;

}
