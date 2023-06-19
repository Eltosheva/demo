package com.library.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Book implements Serializable {
    @Id
    private int id;
    @NotBlank
    private String name;

}
