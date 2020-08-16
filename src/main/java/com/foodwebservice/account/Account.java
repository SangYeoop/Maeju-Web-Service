package com.foodwebservice.account;

import com.foodwebservice.account.form.ProfileUpdateForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.type.GenderType;
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

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    private LocalDateTime createdAt;

    private boolean existDietType;

    public Account update(ProfileUpdateForm profileUpdateForm) {
        this.name = profileUpdateForm.getName();
        this.genderType = profileUpdateForm.getGenderType();
        //TODO Email은 변경하면 안됨.
        return this;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
