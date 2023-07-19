/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Andreas Bayu P
 */
public class HistoryBMI extends MyModel{
    private int id_bmi;
    private Timestamp tanggal;
    private double berat_badan;
    private double tinggi_badan;
    private double hasil_bmi;
    private int acc_id;
    
    public int getId_bmi() {
        return id_bmi;
    }

    public void setId_bmi(int id_bmi) {
        this.id_bmi = id_bmi;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public double getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(double berat_badan) {
        this.berat_badan = berat_badan;
    }

    public double getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(double tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }
    
    public double getHasil_bmi() {
        return hasil_bmi;
    }

    public void setHasil_bmi(double hasil_bmi) {
        this.hasil_bmi = hasil_bmi;
    }
    
    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }
    
    public HistoryBMI(int acc_id) {
        this.acc_id = acc_id;
    }
    
    public HistoryBMI(double berat_badan, double tinggi_badan) {
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
    }
    
    public HistoryBMI(Timestamp tanggal, double hasil_bmi) {
        this.tanggal = tanggal;
        this.hasil_bmi = hasil_bmi;
    }

    public HistoryBMI(double berat_badan, double tinggi_badan, double hasil_bmi, int acc_id) {
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.hasil_bmi = hasil_bmi;
        this.acc_id = acc_id;
    }

    public HistoryBMI(int id_bmi, Timestamp tanggal, double berat_badan, double tinggi_badan, double hasil_bmi, int acc_id) {
        this.id_bmi = id_bmi;
        this.tanggal = tanggal;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.acc_id = acc_id;
    }

    public HistoryBMI(Timestamp tanggal, double berat_badan, double tinggi_badan, double hasil_bmi) {
        this.tanggal = tanggal;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.hasil_bmi = hasil_bmi;
    }
    
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement)MyModel.conn.prepareStatement("insert into history_bmi(berat_badan, tinggi_badan, hasil_bmi, account_id) values(?, ?, ?, ?)");
                sql.setDouble(1, this.berat_badan);
                sql.setDouble(2, this.tinggi_badan);
                sql.setDouble(3, this.hasil_bmi);
                sql.setInt(4, this.acc_id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error di insertData " + e);
        }
    }
    public double calculateBMI() {
        double hasil_bmi = this.berat_badan / Math.pow((this.tinggi_badan / 100.0), 2.0);
        return hasil_bmi;
    }
    public String kategoriBMI(double hasil_bmi) {
        if (hasil_bmi < 18.5) {
            return "Underweight";
        } else if (hasil_bmi >= 18.5 && hasil_bmi < 25) {
            return "Normal";
        } else if (hasil_bmi >= 25 && hasil_bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
    
    public ArrayList<Object> viewListData() {
        ArrayList<Object> coll = new ArrayList<Object>();
        try {
            //this.statement = (Statement) MyModel.conn.createStatement();
            //this.result = this.statement.executeQuery("SELECT * from account WHERE email = " + this.email + " AND password = " + this.password);
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT tanggal, berat_badan, tinggi_badan, hasil_bmi from history_bmi where account_id=?");
            sql.setInt(1, this.acc_id);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                HistoryBMI bmi = new HistoryBMI(this.result.getTimestamp("tanggal"), this.result.getDouble("berat_badan"), this.result.getDouble("berat_badan"), this.result.getDouble("hasil_bmi"));
                coll.add(bmi);
            }
        } catch (Exception e) {
            System.out.println("Error di viewListData " + e);
        }

        return coll;
    }
    
    public ArrayList<Object> getDataUntukGrafik() {
        ArrayList<Object> coll = new ArrayList<Object>();
        try {
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT tanggal, hasil_bmi from history_bmi where account_id=?");
            sql.setInt(1, this.acc_id);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                HistoryBMI bmi = new HistoryBMI(this.result.getTimestamp("tanggal"), this.result.getDouble("hasil_bmi"));
                coll.add(bmi);
            }
        } catch (Exception e) {
            System.out.println("Error di getDataUntukGrafik " + e);
        }
        
        return coll;
    }
}
