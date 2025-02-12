/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.business.transactions;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BusinessRuleException;
import com.paymentchain.product.respository.ProductRepository;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;



@Service
public class BusinessTransactions {
    
    @Autowired
    ProductRepository productRepository;

    public Product post(@RequestBody Product input) throws BusinessRuleException, UnknownHostException {
        if (input.getName().isBlank() || input.getCode().isBlank()) {
            BusinessRuleException bussinesRuleException = new BusinessRuleException("1001", HttpStatus.PRECONDITION_FAILED, "Error de validacion, se deben de indicar el nombre del producto y el codigo correctamente ");
            throw bussinesRuleException;
        } 
        Product save = productRepository.save(input);
        return save;
    }
    
}
