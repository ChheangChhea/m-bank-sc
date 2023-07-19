package com.example.mbank.api.user;

import com.example.mbank.api.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;





    @CreatedDate
    private LocalDateTime creatAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;


}