package Model.Entity;
/**
 * @author AlyssonP
 */
public class Moto extends Veiculo {
    private int cilindrada;
    
    public Moto(String placa, String marca, String modelo, int ano, double precoVenda, String cor, double quilomentragem, int cilindrada) {
        super(placa,marca, modelo, ano, precoVenda, cor, quilomentragem);
        this.cilindrada = cilindrada;
    }
    
    public int getCilindrada() {
        return cilindrada;
    }
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
    
    @Override
    public String toString() {
        return String.format(super.toString()+" Cilindrada: %d;",cilindrada);
    }
}
