package com.vitalx.apizoo.dtos;

public record UpdateAnimalDto(
        int age,
        String type,
        String sex,
        String speciesName
) {
}
