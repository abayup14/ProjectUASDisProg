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
public class HistoryHitungIdeal extends MyModel {
    private int id_hitung_ideal;
    private Timestamp tanggal;
    private double tinggi_badan;
    private double berat_ideal;
    private int acc_id;

    public int getId_hitung_ideal() {
        return id_hitung_ideal;
    }

    public void setId_hitung_ideal(int id_hitung_ideal) {
        this.id_hitung_ideal = id_hitung_ideal;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public double getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(double tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public double getBerat_ideal() {
        return berat_ideal;
    }

    public void setBerat_ideal(double berat_ideal) {
        this.berat_ideal = berat_ideal;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public HistoryHitungIdeal(double tinggi_badan, double berat_ideal, int acc_id) {
        this.tinggi_badan = tinggi_badan;
        this.berat_ideal = berat_ideal;
        this.acc_id = acc_id;
    }
    
    
    
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement)MyModel.conn.prepareStatement("insert into history_hitung_ideal(tinggi_badan, berat_ideal, account_id) values(?, ?, ?)");
                sql.setDouble(1, this.tinggi_badan);
                sql.setDouble(2, this.berat_ideal);
                sql.setInt(3, this.acc_id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error di insertData " + e);
        }
    }
}
