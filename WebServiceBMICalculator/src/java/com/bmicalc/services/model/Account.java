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
public class Account extends MyModel {

    private int id;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Account(String email) {
        this.email = email;
    }

    public void insertAccount() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("insert into account(email, password) values(?, ?)");
                sql.setString(1, this.email);
                sql.setString(2, this.password);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error di insertAccount" + e);
        }
    }

    public ArrayList<Object> cekEmail(String email) {
        ArrayList<Object> collections = new ArrayList<Object>();
        try {
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT * from account where email = ?");
            sql.setString(1, email);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                Account temp = new Account(this.result.getString("email"));
                collections.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Error di cekEmail " + ex);
        }
        return collections;
    }

    public ArrayList<Object> cekLogin() {
        ArrayList<Object> coll = new ArrayList<>();
        try {
            //this.statement = (Statement) MyModel.conn.createStatement();
            //this.result = this.statement.executeQuery("SELECT * from account WHERE email = " + this.email + " AND password = " + this.password);
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT * from account where email = ? AND password = ?");
            sql.setString(1, this.email);
            sql.setString(2, this.password);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                Account acc = new Account(this.email, this.password);
                coll.add(acc);
            }
        } catch (Exception e) {
            System.out.println("Error di ceklogin " + e);
        }

        return coll;
    }
}
