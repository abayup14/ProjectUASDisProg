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
    
    public HistoryHitungIdeal(int acc_id) {
        this.acc_id = acc_id;
    }
    
    public HistoryHitungIdeal(Timestamp tanggal, double tinggi_badan, double berat_ideal) {
        this.tanggal = tanggal;
        this.tinggi_badan = tinggi_badan;
        this.berat_ideal = berat_ideal;
    }
    
    public HistoryHitungIdeal(double tinggi_badan, int acc_id) {
        this.tinggi_badan = tinggi_badan;
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
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("insert into history_hitung_ideal(tinggi_badan, berat_ideal, account_id) values(?, ?, ?)");
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

    public double calculateBeratIdeal() {
        double berat_ideal = 0;
        try {
            String jenis_kelamin = "";
            double persentase = 0;
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("select * from account where id_account=?");
            sql.setInt(1, this.acc_id);
            this.result = sql.executeQuery();
            while(this.result.next()) {
                jenis_kelamin = this.result.getString("jenis_kelamin");
            }
            if (jenis_kelamin.equals("L")) {
                persentase = 0.1;
            } else if (jenis_kelamin.equals("P")) {
                persentase = 0.15;
            }
            berat_ideal = (this.tinggi_badan - 100.0) - ((this.tinggi_badan - 100.0) * persentase);
        } catch (Exception e) {
            System.out.println("Error di calculateBeratIdeal " + e);
        }
        return berat_ideal;
    }
    
    public ArrayList<Object> viewListData() {
        ArrayList<Object> coll = new ArrayList<Object>();
        try {
            //this.statement = (Statement) MyModel.conn.createStatement();
            //this.result = this.statement.executeQuery("SELECT * from account WHERE email = " + this.email + " AND password = " + this.password);
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT tanggal, tinggi_badan, berat_ideal from history_hitung_ideal where account_id=?");
            sql.setInt(1, this.acc_id);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                HistoryHitungIdeal ideal = new HistoryHitungIdeal(this.result.getTimestamp("tanggal"),this.result.getDouble("tinggi_badan"), this.result.getDouble("berat_ideal"));
                coll.add(ideal);
            }
        } catch (Exception e) {
            System.out.println("Error di viewListData " + e);
        }

        return coll;
    }
}
