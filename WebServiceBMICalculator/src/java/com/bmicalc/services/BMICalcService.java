/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.bmicalc.services;

import com.bmicalc.services.model.Account;
import com.bmicalc.services.model.HistoryBMI;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Andreas Bayu P
 */
@WebService(serviceName = "BMICalcService")
public class BMICalcService {
    Account acc;
    HistoryBMI bmi;
    ArrayList<Object> coll;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertAccount")
    public boolean insertAccount(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        coll = new ArrayList<>();
        acc = new Account(email, password);
        coll = acc.cekEmail(email);
        if (coll.isEmpty()) {
            acc.insertAccount();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cekLogin")
    public boolean cekLogin(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        coll = new ArrayList<>();
        acc = new Account(email, password);
        coll = acc.cekLogin();
        if (!coll.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertData")
    public boolean insertData(@WebParam(name = "berat_badan") double berat_badan, @WebParam(name = "tinggi_badan") double tinggi_badan, @WebParam(name = "account_id") int account_id) {
        bmi = new HistoryBMI(berat_badan, tinggi_badan, account_id);
        bmi.insertData();
        return true;
    }
    
    
}
