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
        CobaIP ip = new CobaIP();
        String ipKu = ip.getServerIP();
        System.out.println("IPku adalah : " + ipKu);
    }

    public String getServerIP() {
        String ipAddress = null;
        InetAddress addressKu;
        try {
            addressKu = InetAddress.getLocalHost();
            String hostname = addressKu.getHostName();
            addressKu = InetAddress.getByName(hostname); // Replace "other-pc-name" with the hostname or IP address of the other PC
            ipAddress = addressKu.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Error di getServerIP : " + e);
        }
        return ipAddress;
    }

}
