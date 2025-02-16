/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(name = "Product", description = "Modelo que representa un producto en la base de datos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Schema(name = "code", example = "01", description = "Código del producto")
    private String code;
    @Schema(name = "name", example = "Tarjeta de crédito", description = "Nombre del producto")
    private String name;

}
