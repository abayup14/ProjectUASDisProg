/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculatorserver;

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
}
