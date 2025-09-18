package com.example.demo.DTOS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventarioDTO {
    @NotBlank
    private String producto;

    @NotNull
    private int cantidad_ml;

}
