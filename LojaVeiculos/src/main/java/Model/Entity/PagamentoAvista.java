package Model.Entity;
/**
 * @author Alysson Pereira
 */
public class PagamentoAvista extends FormaPagamento{
    private double percentualDisconto;
    
    public PagamentoAvista(int id,String nome, double percentualDisconto) {
        super(id, nome);
        this.percentualDisconto = percentualDisconto;
    }
    
    public PagamentoAvista(String nome, double percentualDisconto){
        super(nome);
        this.percentualDisconto = percentualDisconto;
    }
    
    public double getPercentualDisconto() {
        return percentualDisconto;
    }
    public void setPercentualDisconto(double percentualDisconto) {
        this.percentualDisconto = percentualDisconto;
    }
    
    public double calcularValorDisconto(double precoVenda) {
        return precoVenda*(percentualDisconto / 100);
    }
    
    // Calculo de preço final com Disconto. Métodos Obrigatório.
    @Override
    public double calcularPagamento(double precoVenda) {
        return precoVenda - calcularValorDisconto(precoVenda);
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + " Percentual de Disconto: %.2f",percentualDisconto);
    }
}
