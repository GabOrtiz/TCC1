/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Ortiz
 */
public class Usuario {
    private int id;
    public String nome, endereco, cpf, email;
    private String senha;

    public Usuario(int id, String nome, String endereco, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String endereco, String cpf, String email, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha + '}';
    }

    
}
