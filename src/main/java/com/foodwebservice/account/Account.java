package com.foodwebservice.account;

import com.foodwebservice.account.type.AccountType;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private boolean existDietType;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }


    public Account update(String name) {
        this.name = name;

        return this;
    }
}
