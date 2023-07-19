package com.example.mbank.api.auth;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Authority> authorities;

    @ManyToMany(mappedBy ="authorities")
    private List<Role>roles;

}
