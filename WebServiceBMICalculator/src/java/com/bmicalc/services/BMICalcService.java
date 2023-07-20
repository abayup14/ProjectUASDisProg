/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.bmicalc.services;

//import com.bmicalc.services.model.Account;
import com.bmicalc.services.model.Account;
import com.bmicalc.services.model.Enkripsi;
import com.bmicalc.services.model.HistoryBMI;
import com.bmicalc.services.model.HistoryHitungIdeal;
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
    ArrayList<Object> coll;
    Enkripsi enkripsi;

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
        enkripsi = new Enkripsi();
        coll = new ArrayList<Object>();
        acc = new Account();
        String encrypt_email = enkripsi.encryptData(email);
        String encrypt_pass = enkripsi.encryptData(password);
        coll = acc.cekEmail(encrypt_email);
        if (coll.isEmpty()) {
            acc = new Account(encrypt_email, encrypt_pass, jenis_kelamin);
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
        enkripsi = new Enkripsi();
        coll = new ArrayList<Object>();
        String encrypt_email = enkripsi.encryptData(email);
        String encrypt_pass = enkripsi.encryptData(password);
        acc = new Account(encrypt_email, encrypt_pass);
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
        HistoryBMI bmi;
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
        HistoryHitungIdeal ideal;
        ideal = new HistoryHitungIdeal(berat_ideal, tinggi_badan, account_id);
        ideal.insertData();
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dataToString")
    public String dataToString(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        enkripsi = new Enkripsi();
        String data = "";
        String encrypt_email = enkripsi.encryptData(email);
        String encrypt_pass = enkripsi.encryptData(password);
        acc = new Account(encrypt_email, encrypt_pass);
        ArrayList<Object> coll = acc.cekLogin();
        if (!coll.isEmpty()) {
            acc = (Account) coll.get(0);
            String decrypt_email = enkripsi.decryptData(encrypt_email);
            data = acc.getId() + "~" + decrypt_email + "~" + acc.getPassword() + "~" + acc.getJenis_kelamin();
        }
        return data;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "hitungBMI")
    public String hitungBMI(@WebParam(name = "tinggi") double tinggi, @WebParam(name = "berat") double berat) {
        //TODO write your implementation code here:
        HistoryBMI bmi;
        bmi = new HistoryBMI(berat, tinggi);
        double hasil_bmi = bmi.calculateBMI();
        String kategori = bmi.kategoriBMI(hasil_bmi);
        return "bmi~" + hasil_bmi + "~" + kategori + "\n";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "hitungBeratIdeal")
    public String hitungBeratIdeal(@WebParam(name = "tinggi") double tinggi, @WebParam(name = "acc_id") int acc_id) {
        //TODO write your implementation code here:
        HistoryHitungIdeal ideal;
        ideal = new HistoryHitungIdeal(tinggi, acc_id);
        double berat_ideal = ideal.calculateBeratIdeal();
        return "ideal~" + berat_ideal + "\n";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewListHistoryBMI")
    public String viewListHistoryBMI(@WebParam(name = "acc_id") int acc_id) {
        //TODO write your implementation code here:
        String hasil = "";
        HistoryBMI bmi = new HistoryBMI(acc_id);
        ArrayList<Object> coll = new ArrayList<Object>();
        coll = bmi.viewListData();
        for (Object obj : coll) {
            if (obj instanceof HistoryBMI) {
                HistoryBMI bmi_new = (HistoryBMI) obj;
                String data = String.valueOf(bmi_new.getTanggal()) + "#" + String.valueOf(bmi_new.getBerat_badan()) + "#" + String.valueOf(bmi_new.getTinggi_badan()) + "#" + String.valueOf(bmi_new.getHasil_bmi()) + "~";
                hasil += data;
            }
        }
        return "historybmi~" + hasil + "\n";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewListHistoryIdeal")
    public String viewListHistoryIdeal(@WebParam(name = "acc_id") int acc_id) {
        //TODO write your implementation code here:
        String hasil = "";
        HistoryHitungIdeal ideal = new HistoryHitungIdeal(acc_id);
        ArrayList<Object> coll = new ArrayList<Object>();
        coll = ideal.viewListData();
        for (Object obj : coll) {
            if (obj instanceof HistoryHitungIdeal) {
                HistoryHitungIdeal bmi_new = (HistoryHitungIdeal) obj;
                String data = String.valueOf(bmi_new.getTanggal()) + "#" + String.valueOf(bmi_new.getTinggi_badan()) + "#" + String.valueOf(bmi_new.getBerat_ideal()) + "~";
                hasil += data;
            }
        }
        return "historyideal~" + hasil + "\n";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dataUntukGrafik")
    public String dataUntukGrafik(@WebParam(name = "acc_id") int acc_id) {
        //TODO write your implementation code here:
        String hasil = "";
        HistoryBMI bmi = new HistoryBMI(acc_id);
        ArrayList<Object> coll = new ArrayList<Object>();
        coll = bmi.getDataUntukGrafik();
        for (Object obj : coll) {
            if (obj instanceof HistoryBMI) {
                HistoryBMI bmi_new = (HistoryBMI) obj;
                String data = String.valueOf(bmi_new.getTanggal()) + "#" + String.valueOf(bmi_new.getHasil_bmi()) + "~";
                hasil += data;
            }
        }
        return "grafikbmi~" + hasil + "\n";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "blockAccount")
    public String blockAccount(@WebParam(name = "acc_id_1") int acc_id_1, @WebParam(name = "email") String email) {
        //TODO write your implementation code here:
        acc = new Account(acc_id_1);
        boolean cek = acc.blockAccount(email);
        if (cek == true) {
            return "berhasil";
        } else {
            return "unknown";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listIdBlock")
    public String listIdBlock(@WebParam(name = "acc_id_user1") int acc_id_user1) {
        //TODO write your implementation code here:
        String hasil = "";
        acc = new Account(acc_id_user1);
        ArrayList<Integer> list_block = acc.cekBlock();
        for (int blk : list_block) {
            hasil += String.valueOf(blk) + "~";
        }
        return hasil;
    }
}
