/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ortiz
 */
public class ConnectionFactory {

    
    //Local
   
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/DomoticaOrtiz";
        String login = "root";
        String senha = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, login, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    
    //KingHost
     /**
    public static Connection getConnection() {
        String url = "jdbc:mysql://mysql.domoticaortiz.kinghost.net/domoticaortiz";
        String login = "domoticaortiz";
        String senha = "1234qwer";
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection(url, login, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    **/
    
    
}
