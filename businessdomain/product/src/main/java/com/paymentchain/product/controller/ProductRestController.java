/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.controller;

import com.paymentchain.product.business.transactions.BusinessTransactions;
import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BusinessRuleException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.product.respository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@Tag(name = "Product API", description = "Esta API despliega todas las funcionalidades para manejar productos")
@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BusinessTransactions businessTransactions;

    @Operation(description = "Retorna todos los productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "204", description = "Lista vacía")
    })
    @GetMapping()
    public ResponseEntity<?> list() {
        List<Product> findAll = productRepository.findAll();
        if (findAll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Obtiene un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Product> findById = productRepository.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(description = "Actualiza un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "500", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Product input) {
        Product find = productRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setName(input.getName());
        }
        Product save = productRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @Operation(description = "Crea un nuevo producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "412", description = "Faltan datos")
    })
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) throws BusinessRuleException, UnknownHostException {
        Product post = businessTransactions.post(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @Operation(description = "Elimina un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Product> findById = productRepository.findById(id);
        if (findById.isPresent()) {
            productRepository.delete(findById.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
