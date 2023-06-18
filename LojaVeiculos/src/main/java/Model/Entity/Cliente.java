package Model.Entity;

import java.util.Date;

/**
 * @author Alysson Pereira
 */
public class Cliente extends Pessoa{
    private Date dataNascimento;
    
    public Cliente(String cpf, String nome, String celular, String email, Date dataNascimento) {
        super(cpf, nome, celular, email);
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + " Data nascimento: %s", dataNascimento);
    }
}
