package com.foodwebservice.food;

import com.foodwebservice.food.condition.Difficulty;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import com.foodwebservice.food_ingredient.FoodIngredient;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = "id") @Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Food {

    @Id @GeneratedValue
    @Column(name = "FOOD_ID")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String ingredient;

    @Lob
    private String recipe;

    private String imagePath;

    private String amount;

    private String time;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Situation situation;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Way way;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "food")
    private Set<FoodIngredient> foodIngredients = new HashSet<>();


    public void addFoodIngredient(FoodIngredient foodIngredient){
        this.foodIngredients.add(foodIngredient);
    }

}
