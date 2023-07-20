/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculator;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Steven Christopher
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String jenis_kelamin;
    
    public User() {
        this.id = 1;
        this.email = "";
        this.password = "";
        this.jenis_kelamin = "";
    }
    
    public User(int id, String email, String password, String jenis_kelamin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.jenis_kelamin = jenis_kelamin;
    }

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
    public void centerFormOnScreen(JFrame frame) {
        // Get the dimensions of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Calculate the X and Y coordinates for the form to appear in the center
        int formWidth = frame.getWidth();
        int formHeight = frame.getHeight();
        int x = (screenWidth - formWidth) / 2;
        int y = (screenHeight - formHeight) / 2;

        // Set the form's location to the calculated X and Y coordinates
        frame.setLocation(x, y);
    }
}
