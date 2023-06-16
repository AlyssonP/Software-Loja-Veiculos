package Model.Entity;
/**
 *
 * @author Alysson Pereira
 */
public abstract class Veiculo {
    private String marca, modelo, cor;
    private double quilomentragem, precoVenda;
    private int ano;
    private boolean vendido;
    
    public Veiculo(String marca, String modelo, int ano, double precoVenda,String cor, double quilomentragem, boolean vendido) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precoVenda = precoVenda;
        this.cor = cor;
        this.quilomentragem = quilomentragem;
        this.vendido = vendido;
    }
    
    // Métodos Get e Set
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
        return String.format("Marca: %s; Modelo: %s; Ano: %s; Cor: %s; Preço de venda: %.2f; Quilomentragem: %.2f; Esta disponivel? %s;", marca, modelo, ano, cor, precoVenda, quilomentragem, vendido);
    }
    
}
