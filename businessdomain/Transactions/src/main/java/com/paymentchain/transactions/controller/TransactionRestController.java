/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.transactions.controller;

import com.paymentchain.transactions.business.transactions.BusinessTransactions;
import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.exception.BusinessRuleException;
import com.paymentchain.transactions.respository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author anton
 */
@Tag(name = "Transaction API", description = "Esta API despliega todas las funcionalidades para manejar transacciones")
@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BusinessTransactions businessTransactions;

    @Operation(description = "Obtiene todas las transacciones")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "204", description = "Lista de transacciones vacía")
    })
    @GetMapping()
    public ResponseEntity<?> list() {
        List<Transaction> findAll = transactionRepository.findAll();
        if (findAll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Obtiene una transacción por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Transaction> findById = transactionRepository.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(description = "Obtiene todas las transacciones de un cliente por IBAN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "404", description = "No se encontraron transacciones para el IBAN especificado")
    })
    @GetMapping("/customer/transactions")
    public ResponseEntity<?> get(@RequestParam(name = "ibanAccount") String ibanAccount) {
        List<Transaction> findByIbanAccount = transactionRepository.findByIbanAccount(ibanAccount);
        if (findByIbanAccount != null) {
            return ResponseEntity.ok(findByIbanAccount);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(description = "Actualiza una transacción por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transacción actualizada exitosamente"),
        @ApiResponse(responseCode = "412", description = "Transacción no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Transaction input) {
        Transaction find = transactionRepository.findById(id).get();
        if (find != null) {
            find.setAmount(input.getAmount());
            find.setChannel(input.getChannel());
            find.setDate(input.getDate());
            find.setDescription(input.getDescription());
            find.setFee(input.getFee());
            find.setIbanAccount(input.getIbanAccount());
            find.setReference(input.getReference());
            find.setStatus(input.getStatus());
        }
        Transaction save = transactionRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @Operation(description = "Crea una nueva transacción")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente"),
        @ApiResponse(responseCode = "412", description = "Faltan datos")
    })
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transaction input) throws BusinessRuleException, UnknownHostException {
        Transaction save = businessTransactions.post(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @Operation(description = "Elimina una transacción por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transacción eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Transaction> findById = transactionRepository.findById(id);
        if (findById.isPresent()) {
            transactionRepository.delete(findById.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
