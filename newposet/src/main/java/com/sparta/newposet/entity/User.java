package com.sparta.newposet.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false, unique = true)
    @Size(min=4 ,max=10)
    @Pattern(regexp = "[a-z0-9]")
    private String username;

    @Column(name = "password",nullable = false)
    @Size(min=4 ,max=10)
    @Pattern(regexp = "[a-zA-Z0-9]")
    private String password;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password ,UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}