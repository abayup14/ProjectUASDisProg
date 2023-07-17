/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classBMICalculator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas Bayu P
 */
public class CobaIP {

    public static void main(String[] args) {
        InetAddress ipAddress;
        try {
            ipAddress = InetAddress.getLocalHost();
            String hostAddress = ipAddress.getHostAddress();
            System.out.println("IP Address: " + hostAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CobaIP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
