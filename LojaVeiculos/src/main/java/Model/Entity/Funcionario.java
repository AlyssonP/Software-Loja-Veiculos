package Model.Entity;
/**
 * @author Alysson Pereira
 */
public class Funcionario extends Pessoa{
    private String senhaAcesso;
    
    public Funcionario(int id, String cpf, String nome, String celular, String email, String senhaAcesso) {
        super(id, cpf, nome, celular, email);
        this.senhaAcesso = senhaAcesso;
    } 

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }
   
}
