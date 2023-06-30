/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmicalc.services.model;

import java.sql.*;

/**
 *
 * @author Andreas Bayu P
 */
public class HistoryBMI extends MyModel{
    private int id;
    private Timestamp tanggal;
    private double berat_badan;
    private double tinggi_badan;
    private int acc_id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public HistoryBMI(double berat_badan, double tinggi_badan, int acc_id) {
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.acc_id = acc_id;
    }

    public HistoryBMI(int id, Timestamp tanggal, double berat_badan, double tinggi_badan) {
        this.id = id;
        this.tanggal = tanggal;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
    }
    
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement)MyModel.conn.prepareStatement("insert into history_bmi(berat_badan, tinggi_badan, account_id) values(?, ?, ?)");
                sql.setDouble(1, this.berat_badan);
                sql.setDouble(2, this.tinggi_badan);
                sql.setInt(3, this.acc_id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error di insertData " + e);
        }
    }
}
