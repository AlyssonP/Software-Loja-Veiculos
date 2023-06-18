package Model.Entity;
/**
 * @author Alysson Pereira
 */
public abstract class Pessoa {
    private String cpf, nome, celular, email;

    public Pessoa(String cpf, String nome, String celular, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format("CPF: %s; Nome: %s; Celular: %s; Email: %s;", cpf, nome, celular, email);
    }
    
}
