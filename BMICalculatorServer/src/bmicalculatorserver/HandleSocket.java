/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculatorserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class HandleSocket extends Thread {
    Socket s;
    BufferedReader input;
    DataOutputStream output;
    FormServer server;
    //GUI_Server parent;
    String message;
    Account acc;
    
    public HandleSocket(FormServer _parent, Socket s) {
        try {
            this.server = _parent;
            //this.parent = _parent;
            this.s = s;
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            output = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SendMessage(String message) {
        String[] part = message.split("~");
        try {
            if(part[0].equals("login")) {
                //u = new User();
                //u = u.CekLogin(part[1], part[2]);
                boolean cekLogin = cekLogin(part[1], part[2]);
                
                if (cekLogin == true) {
                    output.writeBytes("Berhasil Login\n");
                } else {
                    output.writeBytes("Gagal Login\n");
                }
                /*if(u != null) {
                    output.writeBytes("Berhasil Login!\n");
                }
                else {
                    output.writeBytes("Gagal Login!\n");
                }*/
            }
            else if (part[0].equals("register")) {
                String email = part[1];
                String password = part[2];
                String gender = part[3];
                
                boolean insertAccount = insertAccount(email, password, gender);
                
                if (insertAccount == true) {
                    output.writeBytes("Berhasil mendaftarkan akun\n");
                } else {
                    output.writeBytes("Gagal mendaftarkan akun. Akun sudah ada di dalam database\n");
                }
                /*u = new User(name, pass, email, answer);
                if(u.viewListData(u) == null) {
                    u.insert();
                    output.writeBytes("Registrasi berhasil!\n");
                }
                else {
                    output.writeBytes("Email telah digunakan\n");
                }*/
            }
            /*else if(part[0].equals("verif")) {
                u = new User();
                if(u.Verification(part[1], part[2]) == true) {
                    output.writeBytes("jawabanBenar\n");
                }
                else {
                    output.writeBytes("jawabanSalah\n");
                }
            }
            else if(part[0].equals("change")) {
                u = new User("", part[1], part[2], "");
                u.update();
                output.writeBytes("sukses\n");
            }*/
        } catch (IOException ex) {
            Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = input.readLine();
                SendMessage(message);
                server.ShowChat(message);
            } catch (IOException ex) {
                Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static boolean cekLogin(java.lang.String email, java.lang.String password) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.cekLogin(email, password);
    }

    private static boolean insertAccount(java.lang.String email, java.lang.String password, java.lang.String jenisKelamin) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.insertAccount(email, password, jenisKelamin);
    }
    
    
}
