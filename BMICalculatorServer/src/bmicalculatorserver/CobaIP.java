/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculatorserver;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *
 * @author Andreas Bayu P
 */
public class CobaIP {

    public static void main(String[] args) {

    }

    public String getServerIP() {
        String ipAddress = null;
        InetAddress addressKu;
        try {
            addressKu = InetAddress.getByName("LAPTOP-ABAYUP"); // Replace "other-pc-name" with the hostname or IP address of the other PC
            ipAddress = addressKu.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Error di getServerIP : " + e);
        }
        return ipAddress;
    }
}
