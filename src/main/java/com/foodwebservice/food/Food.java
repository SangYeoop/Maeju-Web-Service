package com.foodwebservice.food;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class Food {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Lob
    private String recipe;

    private String imagePath;

    private String amount;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Situation situation;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Way way;
}
