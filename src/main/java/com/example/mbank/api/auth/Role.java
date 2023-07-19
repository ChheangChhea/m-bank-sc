package com.example.mbank.api.auth;

import com.example.mbank.api.user.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private List<UserRole> userRoles;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority>authorities;
}
