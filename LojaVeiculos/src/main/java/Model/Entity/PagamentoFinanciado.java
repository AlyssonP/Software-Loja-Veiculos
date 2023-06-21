package Model.Entity;
/**
 * @author Alysson Pereira
 */
public class PagamentoFinanciado extends FormaPagamento{
    private double taxaJuros; 
    private int quantidadeParcelas;
    
    public PagamentoFinanciado(int id, String nome, double taxaJuros, int quantidadeParcelas) {
        super(id, nome);
        this.taxaJuros = taxaJuros;
        this.quantidadeParcelas = quantidadeParcelas;
    }
    
    // Gets e Sets
    public double getTaxaJuros() {
        return taxaJuros;
    }
    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }
    
    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }
    public void setQuantiadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
    
    // Calula o valor da parcela com Juros.
    public double calcularValorParcelaJuros(double precoVenda) {
        return precoVenda/quantidadeParcelas + calcularJurosParcela(precoVenda);
    }
    
    // Calulo de juros da parcela
    public double calcularJurosParcela(double precoVenda) {
        return (precoVenda/quantidadeParcelas) * (taxaJuros/100);
    }
    
    // Preço total dos juros.
    public double calcularJurosTotal(double precoVenda){
        return calcularJurosParcela(precoVenda) * quantidadeParcelas;
    }
    
    // Calculo de preço final com o juros. Métodos Obrigatório.
    @Override
    public double calcularPagamento(double precoVenda) {
        return precoVenda + calcularJurosTotal(precoVenda);
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + " TaxaJuros: %.2f, Quantidade de Parcelas: %d;", taxaJuros, quantidadeParcelas);
    }
}
