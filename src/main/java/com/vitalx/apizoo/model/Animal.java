package com.vitalx.apizoo.model;

import com.vitalx.apizoo.service.AnimalsType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Animal {

    private static int count = 0;
    private int id = 0;
    private String name;
    private int age;
    private AnimalsType type;
    private String nomEspece;
    private String sex;

    // Constructeur
    public Animal(final String name, final int age, final AnimalsType type, String nomEspece, String sex) {
        super();
        this.id = count++;
        this.name = name;
        this.age = age;
        this.type = type;
        this.nomEspece = nomEspece;
        this.sex = sex;
    }

    public Animal(int id, final String name, final int age, final AnimalsType type, String nomEspece, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.nomEspece = nomEspece;
        this.sex = sex;
    }

    public Animal(int age, AnimalsType type, String nomEspece, String sex) {
        this.age = age;
        this.type = type;
        this.nomEspece = nomEspece;
        this.sex = sex;
    }

    public Animal() {
    }

    public boolean equals (@NotNull Animal animal){
        if(this.name.equals(animal.getName()))
            return true;
        else
            return false;
    }
}
