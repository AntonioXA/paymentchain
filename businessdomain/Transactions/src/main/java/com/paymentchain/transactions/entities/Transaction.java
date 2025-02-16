/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.transactions.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
@Schema(name = "Transaction", description = "Modelo que representa una transacción en la base de datos")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Schema(name = "reference", example = "6524ld", description = "Referencia de la transacción")
    private String reference;
    @Schema(name = "ibanAccount", example = "000251487", description = "Iban de la cuenta que ha realizado la transacción ")
    private String ibanAccount;
    @Schema(name = "date", example = "2022-08-15T11:36:07.683Z", description = "Fecha de la realización de la transacción")
    private LocalDateTime date;
    @Schema(name = "amount", example = "450", description = "Monto de la transacción")
    private double amount;
    @Schema(name = "fee", example = "0", description = "Tasa de la transacción")
    private double fee;
    @Schema(name = "description", example = "Retiro", description = "Descripción de la transacción")
    private String description;
    @Schema(name = "status", example = "Rechazada", description = "Estatus de la transacción")
    private String status;
    @Schema(name = "channel", example = "WEB", description = "Canal por el cual se ha realizado la transacción")
    private String channel;
}
