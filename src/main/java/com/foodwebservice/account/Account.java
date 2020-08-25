package com.foodwebservice.account;

import com.foodwebservice.account.form.ProfileUpdateForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.type.GenderType;
import com.foodwebservice.food.Food;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
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

    @ManyToMany
    private Set<Food> likeFoods = new HashSet<>();

    private boolean existDietType = false;

    private boolean existFoodType = false;

    public Account update(ProfileUpdateForm profileUpdateForm) {
        this.name = profileUpdateForm.getName();
        this.genderType = profileUpdateForm.getGenderType();
        //TODO Email은 변경하면 안됨.
        return this;
    }

    public Account updatePassword(String password) {
        this.password = password;
        return this;
    }

    public void addLikeFood(Food food) {
        this.likeFoods.add(food);
    }

    public void removeLikeFood(Food food) {
        if(!this.likeFoods.contains(food))
            throw new IllegalArgumentException();
        this.likeFoods.remove(food);
    }
}
