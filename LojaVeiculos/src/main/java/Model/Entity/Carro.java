package Model.Entity;

public class Carro extends Veiculo{
    private int quatidadePorta;
    
    public Carro(String marca, String modelo, int ano, double precoVenda,String cor, double quilomentragem, int quatidadePorta, boolean vendido) {
        super(marca, modelo, ano, precoVenda, cor, quilomentragem, vendido);
        this.quatidadePorta = quatidadePorta;
    }
    
    public int getQuatidadePorta() {
        return quatidadePorta;
    }
    public void setQuatidadePorta(int quantidadePorta) {
        this.quatidadePorta = quantidadePorta;
    }
    
    public String toString() {
        return String.format(super.toString() + " Quantidade de Porta: %d;", quatidadePorta);
    }
}
