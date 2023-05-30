package com.alura.forum.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum AnswerStatus {
    NAO_SOLUCIONADO(1, "Não solucionado"),
    SOLUCIONADO(2, "Solucionado"),
    FECHADO(3, "Fechado");
    private Integer id;
    private String name;

    public static AnswerStatus findById(Integer id) {
        return Arrays.stream(AnswerStatus.values())
                .filter(s -> Objects.equals(s.id, id))
                .findFirst().orElse(null);
    }
}