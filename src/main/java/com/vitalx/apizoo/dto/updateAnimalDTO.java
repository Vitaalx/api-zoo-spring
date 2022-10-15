package com.vitalx.apizoo.dto;

import lombok.Data;

@Data
public class updateAnimalDTO {

    private int age;
    private String type;
    private String sex;
    private String nomEspece;

    public updateAnimalDTO(int age, String type, String sex, String nomEspece) {
        this.age = age;
        this.type = type;
        this.sex = sex;
        this.nomEspece = nomEspece;
    }
}
