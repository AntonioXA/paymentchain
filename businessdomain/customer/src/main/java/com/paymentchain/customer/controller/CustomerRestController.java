/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.business.transactions.BusinessTransactions;
import com.paymentchain.customer.exception.BusinessRuleException;
import com.paymentchain.customer.respository.CustomerRepository;
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
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Customer API", description = "Esta API despliega todas las funcionalidades para manejar clientes")
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BusinessTransactions businessTransactions;

    @Autowired
    private Environment env;

    @GetMapping("/check")
    public String check() {
        return "Property value is: " + env.getProperty("custom.activeprofileName");
    }

    @Operation(description = "Obtiene todos los clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "204", description = "Lista de clientes vacía")
    })
    @GetMapping()
    public ResponseEntity<?> list() {
        List<Customer> findAll = customerRepository.findAll();
        if (findAll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Obtiene un cliente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Customer> findById = customerRepository.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(description = "Actualiza un cliente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "412", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Customer input) {
        Customer find = customerRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setName(input.getName());
            find.setIban(input.getIban());
            find.setPhone(input.getPhone());
            find.setSurname(input.getSurname());
        }
        Customer save = customerRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @Operation(description = "Crea un nuevo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
        @ApiResponse(responseCode = "412", description = "Faltan datos")
    })
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer input) throws BusinessRuleException, UnknownHostException {
        Customer post = businessTransactions.post(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @Operation(description = "Elimina un cliente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(description = "Obtiene un cliente por su código")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/full")
    public ResponseEntity<?> getByCode(@RequestParam(name = "code") String code) {
        Customer customer = businessTransactions.getByCode(code);
        return ResponseEntity.ok(customer);
    }

}
