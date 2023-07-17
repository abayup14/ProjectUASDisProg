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
                    output.writeBytes("berhasil~"+dataToString(part[1], part[2])+"\n");
                } else {
                    output.writeBytes("gagal\n");
                }
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
            }
            else if (part[0].equals("bmi")) {
                int id = Integer.parseInt(part[1]);
                double tinggi = Double.parseDouble(part[2]);
                double berat = Double.parseDouble(part[3]);
                
                String data_bmi = hitungBMI(tinggi, berat);
                String[] split_data = data_bmi.split("~");
                double hasil_bmi = Double.parseDouble(split_data[1]);
                boolean insertData = insertDataBMI(berat, tinggi, hasil_bmi, id);
                output.writeBytes(data_bmi);
            }
            else if (part[0].equals("ideal")) {
                int id = Integer.parseInt(part[1]);
                String jenis_kelamin = part[2];
                double tinggi = Double.parseDouble(part[3]);
                
                String data_ideal = hitungBeratIdeal(tinggi, id);
                String[] split_data = data_ideal.split("~");
                double berat_ideal = Double.parseDouble(split_data[1]);
                boolean insertData = insertDataIdeal(tinggi, berat_ideal, id);
                output.writeBytes(data_ideal);
            }
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

    private static String dataToString(java.lang.String email, java.lang.String password) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.dataToString(email, password);
    }

    private static String hitungBMI(double tinggi, double berat) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.hitungBMI(tinggi, berat);
    }

    private static boolean insertDataBMI(double beratBadan, double tinggiBadan, double hasilBmi, int accountId) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.insertDataBMI(beratBadan, tinggiBadan, hasilBmi, accountId);
    }

    private static String hitungBeratIdeal(double tinggi, int accId) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.hitungBeratIdeal(tinggi, accId);
    }

    private static boolean insertDataIdeal(double tinggiBadan, double beratIdeal, int accountId) {
        bmicalculatorserver.BMICalcService_Service service = new bmicalculatorserver.BMICalcService_Service();
        bmicalculatorserver.BMICalcService port = service.getBMICalcServicePort();
        return port.insertDataIdeal(tinggiBadan, beratIdeal, accountId);
    }
    
    
}
