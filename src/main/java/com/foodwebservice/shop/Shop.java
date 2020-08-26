package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.Food;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity @EqualsAndHashCode(of = "id")
public class Shop {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

}
