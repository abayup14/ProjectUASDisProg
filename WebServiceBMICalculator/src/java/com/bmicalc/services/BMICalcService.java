/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.bmicalc.services;

//import com.bmicalc.services.model.Account;
import com.bmicalc.services.model.HistoryBMI;
import com.bmicalc.services.model.HistoryHitungIdeal;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import bmicalculatorserver.Account;

/**
 *
 * @author Andreas Bayu P
 */
@WebService(serviceName = "BMICalcService")
public class BMICalcService {
    Account acc;
    HistoryBMI bmi;
    HistoryHitungIdeal ideal;
    ArrayList<Object> coll;
    /**
     * This is a sample web service operation
     */
    /*@WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }*/

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertAccount")
    public boolean insertAccount(@WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "jenis_kelamin") String jenis_kelamin) {
        //TODO write your implementation code here:
        coll = new ArrayList<Object>();
        acc = new Account();
        coll = acc.cekEmail(email);
        if (coll.isEmpty()) {
            acc = new Account(email, password, jenis_kelamin);
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
        coll = new ArrayList<Object>();
        acc = new Account(email, password);
        coll = acc.cekLogin();
        if (coll.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertDataBMI")
    public boolean insertDataBMI(@WebParam(name = "berat_badan") double berat_badan, @WebParam(name = "tinggi_badan") double tinggi_badan, @WebParam(name = "hasil_bmi") double hasil_bmi, @WebParam(name = "account_id") int account_id) {
        bmi = new HistoryBMI(berat_badan, tinggi_badan, hasil_bmi, account_id);
        bmi.insertData();
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertDataIdeal")
    public boolean insertDataIdeal(@WebParam(name = "tinggi_badan") double tinggi_badan, @WebParam(name = "berat_ideal") double berat_ideal, @WebParam(name = "account_id") int account_id) {
        //TODO write your implementation code here:
        ideal = new HistoryHitungIdeal(berat_ideal, tinggi_badan, account_id);
        ideal.insertData();
        return true;
    }
}
