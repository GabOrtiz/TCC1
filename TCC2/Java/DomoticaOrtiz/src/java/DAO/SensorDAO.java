/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Usuario;
import Classes.Sensor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Criptografia.Cryptography;
import Criptografia.CryptographyGeneric;
import Criptografia.CryptographySHA512;
import DAO.DaoBD;
import DAO.UsuarioDAO;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
/**
 *
 * @author Ortiz
 */
public class SensorDAO extends DaoBD{

public List<Sensor> visualizarTodos(){
        List<Sensor> sensores = new ArrayList<Sensor>();
        conectar("SELECT * FROM Sensor");
        ResultSet rs;
        try {            
            rs = comando.executeQuery();
            while (rs.next()) {
                Sensor s = new Sensor(
                        rs.getInt("uid"),
                        rs.getString("tipo"),
                        rs.getString("valor"),
                        rs.getString("data"));
                        
                sensores.add(s);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return sensores;         
    }


public List<Sensor> visualizarUID(int uid){
        List<Sensor> sensores = new ArrayList<Sensor>();
        conectar("SELECT * FROM Sensor WHERE uid = ?");
        ResultSet rs;
        try {
            comando.setInt(1, uid);
            rs = comando.executeQuery();
            while (rs.next()) {
                Sensor s = new Sensor(
                        rs.getInt("uid"),
                        rs.getString("tipo"),
                        rs.getString("valor"),
                        rs.getString("data"));
                        
                sensores.add(s);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return sensores;
    }


public List<Sensor> visualizarSUID(int uid, String tipo){
        List<Sensor> sensores = new ArrayList<Sensor>();
        conectar("SELECT * FROM Sensor WHERE uid = ? AND tipo = ?");
        ResultSet rs;
        try {
            comando.setInt(1, uid);
            comando.setString(2, tipo);
            rs = comando.executeQuery();
            while (rs.next()) {
                Sensor s = new Sensor(
                        rs.getInt("uid"),
                        rs.getString("tipo"),
                        rs.getString("valor"),
                        rs.getString("data"));
                        
                sensores.add(s);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return sensores;         
    }



public void inserir(int uid, String tipo, String valor, String data){
        conectar("INSERT INTO Sensor(uid, tipo ,valor, data)VALUES (?, ?, ?, ?)");
        try {
            //String senhacrip = cryptography.encrypt(senha);
            comando.setInt(1, uid);
            comando.setString(2, tipo);
            comando.setString(3, valor);
            comando.setString(4, data);
            comando.execute();
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            fechar();
        }
    }
    
}
