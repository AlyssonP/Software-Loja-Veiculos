package Model.Entity;
/**
 * @author Alysson Pereira
 */
public abstract class FormaPagamento {
    private int id;
    private String nome;
   
    public FormaPagamento(int id,String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Método Abstrato (Obrigatório)
    public abstract double calcularPagamento(double precoVenda);

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
    
    public String toString() {
        return String.format("Forma de pagamento: %s;",nome);
    }
    
}
