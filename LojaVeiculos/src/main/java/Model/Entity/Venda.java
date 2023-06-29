package Model.Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Alysson Pereira
 */
public class Venda {
    private int id;
    private Veiculo veiculo;
    private Cliente cliente;
    private FormaPagamento formaPagamento;
    private double valorPagamento;
    private LocalDate dataVenda;
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public Venda(Veiculo veiculo, Cliente cliente, FormaPagamento formaPagamento, String dataVenda) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.dataVenda = LocalDate.parse(dataVenda, formatter);
        
        valorPagamento = formaPagamento.calcularPagamento(veiculo.getPrecoVenda());
        
        //this.veiculo.setVendido(true);
    }
    public Venda(int id,Veiculo veiculo, Cliente cliente, FormaPagamento formaPagamento, String dataVenda) {
        this.id = id;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.dataVenda = LocalDate.parse(dataVenda, formatter);
        
        valorPagamento = formaPagamento.calcularPagamento(veiculo.getPrecoVenda());
        
        //this.veiculo.setVendido(true);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
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

    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(String dataVenda) {
        this.dataVenda = LocalDate.parse(dataVenda, formatter);
    }
    
    @Override
    public String toString() {
        return String.format("Nome veículo: %s; Placa Veículo: %s; Nome do Cliente: %s; Forma de Pagamento: %s; Valor Pagamento: %.2f; Data da Venda: %s;",
                veiculo.getModelo(),veiculo.getPlaca(), cliente.getNome(), formaPagamento.getNome(), valorPagamento, dataVenda);
    }
    
    
}
