package Model.Entity;
/**
 * @author AlyssonP
 */
public abstract class Veiculo {
    private String placa, marca, modelo, cor;
    private double quilomentragem, precoVenda;
    private int id, ano;
    private boolean vendido;
    
    public Veiculo(int id, String placa, String marca, String modelo, int ano, double precoVenda,String cor, double quilomentragem) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precoVenda = precoVenda;
        this.cor = cor;
        this.quilomentragem = quilomentragem;
        this.vendido = false;
    }
    // Métodos Especiais
    public boolean venderVeiculo() {
        if(!vendido) {
            this.vendido = true;
            return true;
        }
        return false;
    }
    
    // Métodos Get e Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public double getQuilomentragem() {
        return quilomentragem;
    }
    public void setQuilomentragem(double quilomentragem) {
        this.quilomentragem = quilomentragem;
    }
    
    public boolean getVendido() {
        return vendido;
    }
    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
    
    // toString
    public String toString() {
        return String.format("Placa: %s; Marca: %s; Modelo: %s; Ano: %s; Cor: %s; Preço de venda: %.2f; Quilomentragem: %.2f; Esta disponivel? %s;", placa, marca, modelo, ano, cor, precoVenda, quilomentragem, !vendido);
    }
}
