package com.foodwebservice.account;

import com.foodwebservice.account.type.AccountType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;

    private boolean existDietType;

    public Account update(String name) {
        this.name = name;
        return this;
    }
}
