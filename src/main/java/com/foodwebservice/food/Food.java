package com.foodwebservice.food;

import com.foodwebservice.food.condition.Difficulty;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id") @Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Food {

    @Id @GeneratedValue
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

}
