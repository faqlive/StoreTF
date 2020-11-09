/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.hand;

/**
 *
 * @author FAQ
 */
public class ExceptionNoDB extends Exception {
    
    public ExceptionNoDB(){}
    
    public ExceptionNoDB(String msj){
        super(msj);
    }
    
}
