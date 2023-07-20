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
    private String jenis_kelamin;
    ArrayList<Object> coll;
    Enkripsi enk;

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

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public Account() {
        this.id = 1;
        this.email = "";
        this.password = "";
        this.jenis_kelamin = "";
    }

    public Account(int acc_id) {
        this.id = acc_id;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password, String jenis_kelamin) {
        this.email = email;
        this.password = password;
        this.jenis_kelamin = jenis_kelamin;
    }

    public Account(int id, String email, String password, String jenis_kelamin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.jenis_kelamin = jenis_kelamin;
    }

    public Account(String email) {
        this.email = email;
    }

    public void insertAccount() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("insert into account(email, password, jenis_kelamin) values(?, ?, ?)");
                sql.setString(1, this.email);
                sql.setString(2, this.password);
                sql.setString(3, this.jenis_kelamin);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error di insertAccount " + e);
        }
    }

    public ArrayList<Object> cekEmail(String email) {
        coll = new ArrayList<Object>();
        try {
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT * from account where email = ?");
            sql.setString(1, email);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                Account temp = new Account(this.result.getString("email"));
                coll.add(temp);
                break;
            }
        } catch (Exception ex) {
            System.out.println("Error di cekEmail " + ex);
        }
        return coll;
    }

    public ArrayList<Object> cekLogin() {
        coll = new ArrayList<Object>();
        try {
            //this.statement = (Statement) MyModel.conn.createStatement();
            //this.result = this.statement.executeQuery("SELECT * from account WHERE email = " + this.email + " AND password = " + this.password);
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT * from account where email = ? AND password = ?");
            sql.setString(1, this.email);
            sql.setString(2, this.password);
            this.result = sql.executeQuery();
            while (this.result.next()) {
                Account acc = new Account(this.result.getInt("id_account"), this.result.getString("email"), this.result.getString("password"), this.result.getString("jenis_kelamin"));
                coll.add(acc);
                break;
            }
        } catch (Exception e) {
            System.out.println("Error di ceklogin " + e);
        }

        return coll;
    }

    public boolean blockAccount(String email) {
        enk = new Enkripsi();
        boolean cekStatus = false;
        try {
            PreparedStatement sql1 = (PreparedStatement) MyModel.conn.prepareStatement("SELECT id_account from account where email=?");
            sql1.setString(1, enk.encryptData(email));
            this.result = sql1.executeQuery();
            while (this.result.next()) {
                if (this.result.getString("id_account").equals("")) {
                    cekStatus = false;
                } else {
                    int acc_id_block = this.result.getInt("id_account");
                    PreparedStatement sql2 = (PreparedStatement) MyModel.conn.prepareStatement("INSERT INTO cek_blok(account_id_user1, account_id_user2, status) values(?, ?, ?)");
                    sql2.setInt(1, this.id);
                    sql2.setInt(2, acc_id_block);
                    sql2.setString(3, "Block");
                    sql2.executeUpdate();
                    sql2.close();
                    cekStatus = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error di blockAccount : " + e);
        }

        return cekStatus;
    }

    public ArrayList<Integer> cekBlock() {
        ArrayList<Integer> list_block = new ArrayList<Integer>();
        try {
            PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement("SELECT account_id_user1 from cek_blok where account_id_user2=? AND status=?");
            sql.setInt(1, this.id);
            sql.setString(2, "Block");
            this.result = sql.executeQuery();
            while (this.result.next()) {
                int acc_block = this.result.getInt("account_id_user1");
                list_block.add(acc_block);
            }
        } catch (Exception e) {
            System.out.println("Error di cekBlock : " + e);
        }
        return list_block;
    }
}
