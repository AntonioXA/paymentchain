/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@Entity
@Schema(name = "CustomerProduct", description = "Modelo que representa la tabla CustomerProduct en la base de datos")
public class CustomerProduct {
    
       @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Schema(name = "productId", example = "", description = "Id del producto")
    private long productId;
    @Transient
    @Schema(name = "productName", example = "", description = "Nombre del producto")
    private String productName;//este atributo no se guardara por @Transient
    
    @JsonIgnore//it is necesary for avoid infinite recursion
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Customer.class)
    @JoinColumn(name = "customerId", nullable = true)   
    @Schema(name = "customer", example = "", description = "Cliente")
    private Customer customer;//id del cliente
    
}
