package com.foodwebservice.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class Tuple<L, R> {

    private final L first;
    private final R second;

    public static <L,R> Tuple<L,R> of(L first, R second) {
        return new Tuple<>(first, second);
    }

}
