package com.foodwebservice.food_ingredient;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.food.Food;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Entity @Getter @Setter
@EqualsAndHashCode (of = "id")
public class FoodIngredient {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(nullable = true)
    private String amount;
}
