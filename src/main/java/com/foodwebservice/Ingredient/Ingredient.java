package com.foodwebservice.Ingredient;

import com.foodwebservice.food_ingredient.FoodIngredient;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id") @Getter @Setter
@Entity
public class Ingredient {

    @Id @GeneratedValue
    @Column(name = "ingredent_id")
    public Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @OneToMany(mappedBy = "ingredient")
    private Set<FoodIngredient> foodIngredients = new HashSet<>();


    public void addFoodIngredient(FoodIngredient foodIngredient){
        this.foodIngredients.add(foodIngredient);
    }
}
