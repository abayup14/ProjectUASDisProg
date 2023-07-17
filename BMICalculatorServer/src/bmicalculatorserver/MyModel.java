/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmicalculatorserver;

/**
 *
 * @author Andreas Bayu P
 */
import java.sql.*;
public class MyModel {
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;
    
    public MyModel() {
        this.conn = this.getConnection();
        this.statement = null;
        this.result = null;
    }
    
    private Connection getConnection(){
        if (MyModel.conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                return DriverManager.getConnection("jdbc:mysql://localhost/uas_disprog", "root", "");
            } catch (Exception e) {
                System.out.println("Error di getConnection " + e);
            }
        }
        return MyModel.conn;
    }
}
