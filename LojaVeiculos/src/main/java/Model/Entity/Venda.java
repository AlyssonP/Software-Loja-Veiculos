package Model.Entity;

import java.util.Date;

/**
 * @author Alysson Pereira
 */
public class Venda {
    private Veiculo veiculo;
    private Pessoa cliente;
    private FormaPagamento formaPagamento;
    private double valorPagamento;
    private Date dataVenda;
    
    public Venda(Veiculo veiculo, Pessoa cliente, FormaPagamento formaPagamento, Date dataVenda) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.dataVenda = dataVenda;
        
        valorPagamento = formaPagamento.calcularPagamento(veiculo.getPrecoVenda());
        
        this.veiculo.setVendido(true);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Pessoa getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    @Override
    public String toString() {
        return String.format("Nome veículo: %s; Placa Veículo: %s; Nome do Cliente: %s; Forma de Pagamento: %s; Valor Pagamento: %.2f; Data da Venda: %s;",
                veiculo.getModelo(),veiculo.getPlaca(), cliente.getNome(), formaPagamento.getNome(), valorPagamento, dataVenda);
    }
    
    
}
