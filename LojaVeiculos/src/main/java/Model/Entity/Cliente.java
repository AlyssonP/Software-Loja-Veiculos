package Model.Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Alysson Pereira
 */
public class Cliente extends Pessoa{
    private LocalDate dataNascimento;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public Cliente(int id,String cpf, String nome, String celular, String email, String dataNascimento) {
        super(id, cpf, nome, celular, email);
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }
    
    public Cliente(String cpf, String nome, String celular, String email, String dataNascimento) {
        super(cpf, nome, celular, email);
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + " Data nascimento: %s", dataNascimento.format(formatter));
    }
}
