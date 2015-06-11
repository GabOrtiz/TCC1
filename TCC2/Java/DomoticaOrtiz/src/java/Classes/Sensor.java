/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;

/**
 *
 * @author Ortiz
 */
public class Sensor {
    
    public int uid;
    public String tipo, valor, data;
    

    public Sensor(int uid, String tipo, String valor, String data) {
        this.uid = uid;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    

    public Sensor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    
    
    @Override
    public String toString() {
        return tipo + " " + valor + " " + data;
    }
    
}
