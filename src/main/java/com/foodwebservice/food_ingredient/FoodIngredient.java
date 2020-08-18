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
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    private String amount;
}
