package Model.Entity;
/**
 * @author Alysson Pereira
 */
public abstract class FormaPagamento {
    private String nome;
   
    public FormaPagamento(String nome) {
       this.nome = nome;
    }
    
    // Método Abstrato (Obrigatório)
    public abstract double calcularPagamento(double precoVenda);
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String toString() {
        return String.format("Forma de pagamento: %s;",nome);
    }
    
}
