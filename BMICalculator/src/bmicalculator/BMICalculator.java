/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bmicalculator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vince
 */
public class BMICalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            // TODO code application logic here
            Socket s;
            BufferedReader input;
            DataOutputStream output;
            
            s = new Socket("192.168.117.85", 10013); //string host dan int port
            output = new DataOutputStream(s.getOutputStream());
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            System.out.println(input.readLine());
        } catch (IOException ex) {
            Logger.getLogger(BMICalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
