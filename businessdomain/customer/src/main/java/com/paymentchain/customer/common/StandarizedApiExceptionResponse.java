/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StandarizedApiExceptionResponse {

    private String type;
    private String tittle;
    private String code;
    private String detail;
    private String instance;

    public StandarizedApiExceptionResponse(String type, String tittle, String code, String detail) {
        super();
        this.type = type;
        this.tittle = tittle;
        this.code = code;
        this.detail = detail;
    }
    
    

}
