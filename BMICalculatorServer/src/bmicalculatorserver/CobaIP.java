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
        /*CobaIP ip = new CobaIP();
        String ipKu = ip.getServerIP();
        System.out.println(ipKu);*/
    }

    public String getServerIP() {
        String ipAddress = null;
        try {
            // Replace "other-pc-name" with the hostname or IP address of the other PC
            ipAddress = InetAddress.getByName("LAPTOP-ABAYUP").getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Error di getServerIP : " + e);
        }
        return ipAddress;
    }
}
