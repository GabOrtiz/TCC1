/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lhries
 */
public abstract class DaoBD {
    protected Connection con;
    protected PreparedStatement comando;
   
    protected void conectar(String sql) {
        try {
            con = ConnectionFactory.getConnection();
            comando = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void conectarComGetKeys(String sql) {
        try {
            con = ConnectionFactory.getConnection();
            comando = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(DaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void fechar() {
        try {
            comando.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
