/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;


@Entity
@Data
@Schema(name = "Customer", description = "Modelo que representa un cliente en la base de datos")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Schema(name = "code", example = "01", description = "Código del cliente")
    private String code;
    @Schema(name = "name", example = "Paco", description = "Nombre del cliente")
    private String name;
    @Schema(name = "phone", example = "603892934", description = "Teléfono del cliente")
    private String phone;
    @Schema(name = "iban", example = "000251487", description = "Iban de la cuenta del cliente")
    private String iban;
    @Schema(name = "surname", example = "Ruiz", description = "Apellido del cliente")
    private String surname;
    @Schema(name = "address", example = "Calle nueva, 8", description = "Nombre del cliente")
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "products", example = "", description = "Productos asociados al cliente")
    private List<CustomerProduct> products;
    @Transient
    private List<?> transactions;

}
