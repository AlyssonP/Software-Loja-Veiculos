package Model.Entity;
/**
 * @author AlyssonP
 */
public class Carro extends Veiculo{
    private int quatidadePorta;
    private String tipoCombustivel, cambio;
    
    public Carro(int id, String placa, String marca, String modelo, int ano, double precoVenda, String cor, double quilomentragem, int quatidadePorta, String tipoCombustivel, String cambio, boolean vendido) {
        super(id,placa, marca, modelo, ano, precoVenda, cor, quilomentragem, vendido);
        this.quatidadePorta = quatidadePorta;
        this.tipoCombustivel = tipoCombustivel;
        this.cambio = cambio;
    }
    public Carro(String placa, String marca, String modelo, int ano, double precoVenda, String cor, double quilomentragem, int quatidadePorta, String tipoCombustivel, String cambio) {
        super(placa, marca, modelo, ano, precoVenda, cor, quilomentragem);
        this.quatidadePorta = quatidadePorta;
        this.tipoCombustivel = tipoCombustivel;
        this.cambio = cambio;
    }
    
    public int getQuatidadePorta() {
        return quatidadePorta;
    }
    public void setQuatidadePorta(int quantidadePorta) {
        this.quatidadePorta = quantidadePorta;
    }
    
    public String getTipoCombustivel() {
        return tipoCombustivel;
    }
    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    } 
    
    public String getCambio() {
        return cambio;
    }
    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + " Quantidade de Porta: %d; Tipo de Combustível: %s; Câmbio: %s;", quatidadePorta, tipoCombustivel, cambio);
    }
}
