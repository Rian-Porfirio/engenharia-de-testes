package br.com.engTestesJava;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String email;
    private int age;
    private boolean isActive;
}