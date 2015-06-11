/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Criptografia.Cryptography;
import Criptografia.CryptographyGeneric;
import Criptografia.CryptographySHA512;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ortiz
 */
public class UsuarioDAO extends DaoBD{
    
    // VISUALIZAR TODOS
    public List<Usuario> visualizarTodos(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        conectar("SELECT * FROM usuario");
        ResultSet rs;
        try {            
            rs = comando.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getString("senha"));
                        
                usuarios.add(u);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return usuarios;         
    }
    
    
    // BUSCA POR ID
    public List<Usuario> buscarID(int id){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        conectar("SELECT * FROM usuario WHERE id = ?");
        ResultSet rs;
        try {
            comando.setInt(1, id);
            rs = comando.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getString("senha"));
                        
                usuarios.add(u);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return usuarios;       
    }
    

    
    
    public Usuario buscar(String email, String senha){
        //Usuario u = null;
        Usuario u = new Usuario("","","","","");
        conectar("SELECT u.* FROM usuario u WHERE u.email = ? AND u.senha = ?");
        ResultSet rs;
        try {
            comando.setString(1, email);
            comando.setString(2, senha);
            rs = comando.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEndereco(rs.getString("endereco"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                System.out.println("Encontrou: "+u.getNome());
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechar();
        }
        return u;          
    }
    
    
    public void inserir(String nome, String endereco, String cpf, String email, String senha){
        Cryptography cryptography;
        conectar("INSERT INTO usuario( nome, endereco, cpf, email, senha)VALUES (?, ?, ?, ?, ?)");
        //Criptografia usando MD5
        //cryptography = new CryptographySHA512();
        try {
            //String senhacrip = cryptography.encrypt(senha);
            comando.setString(1, nome);
            comando.setString(2, endereco);
            comando.setString(3, cpf);
            comando.setString(4, email);
            comando.setString(5, senha);
            comando.execute();
        } catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            fechar();
        }
    }
    
    public void alterarEnd(int id, String novoend){
        conectar("UPDATE domoticaortiz.usuario SET endereco=? WHERE usuario.id=?");
        try{
            comando.setString(1, novoend);
            comando.setInt(2, id);
            comando.execute();
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            fechar();
        }
    }
    
    
    
    
    
     public void alterarEm(int id, String novoem){
        conectar("UPDATE domoticaortiz.usuario SET email=? WHERE usuario.id=?");
        try{
            comando.setString(1, novoem);
            comando.setInt(2, id);
            comando.execute();
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            fechar();
        }
    }
    
    
      public void alterarSenha(int id, String novaSenha){
        conectar("UPDATE domoticaortiz.usuario SET senha=? WHERE usuario.id=?");
        try{
            comando.setString(1, novaSenha);
            comando.setInt(2, id);
            comando.execute();
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            fechar();
        }
    }
     
     
     
}