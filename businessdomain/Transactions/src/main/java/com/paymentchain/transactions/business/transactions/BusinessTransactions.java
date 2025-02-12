/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.business.transactions;

import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.exception.BusinessRuleException;
import com.paymentchain.transactions.respository.TransactionRepository;
import java.net.UnknownHostException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BusinessTransactions {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction post(@RequestBody Transaction input) throws BusinessRuleException, UnknownHostException {
        if (input.getAmount() < 0) {
            input.setFee(0.98);
        }
        
        if (input.getChannel().isBlank() || input.getStatus().isBlank() || input.getDescription().isBlank() || input.getIbanAccount().isBlank() || input.getReference().isBlank() || input.getDate() == null) {
            BusinessRuleException bussinesRuleException = new BusinessRuleException("1001", HttpStatus.PRECONDITION_FAILED, "Error de validacion, se deben de indicar todas las propiedades de la transaccion correctamente ");
            throw bussinesRuleException;
        }
        if (calcularSaldoConComision(input) <= 0) {
            BusinessRuleException bussinesRuleException = new BusinessRuleException("1002", HttpStatus.PRECONDITION_FAILED, "Error, el saldo de la cuenta no puede quedarse en negativo o 0");
            throw bussinesRuleException;
        }
        
        Transaction save = transactionRepository.save(input);
        return save;
    }
    
    //Metodo que calcula si es posible realizar la transaccion. Se podra hacer si hay saldo suficiente en la cuenta
    public double calcularSaldoConComision(Transaction input){
        double total = 0;
        double comision = 0;
        List<Transaction> findByIbanAccount = transactionRepository.findByIbanAccount(input.getIbanAccount());
        for (Transaction transaction : findByIbanAccount) {
            total += transaction.getAmount();
        }
        if (input.getAmount() < 0) {
            comision = Math.abs(input.getAmount()) * 0.0098;
        }        
        return total + input.getAmount() + comision;
        
    }
}
