/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculator;

/**
 *
 * @author Steven Christopher
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String jenis_kelamin;
    
    public User() {
        this.id = 1;
        this.username = "";
        this.password = "";
        this.jenis_kelamin = "";
    }
    
    public User(int id, String username, String password, String jenis_kelamin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
