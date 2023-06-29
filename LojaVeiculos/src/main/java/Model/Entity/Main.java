package Model.Entity;

import Model.Repository.CarroRepository;
import Model.Repository.ClienteRepository;
import Model.Repository.FormaPagamentoRepository;
import Model.Repository.MotoRepository;
import Model.Repository.PagamentoAvistaRepository;
import Model.Repository.PagamentoFinanciadoRepository;
import Model.Repository.VeiculoRepository;
import Model.Repository.VendaRepository;
import java.util.ArrayList;

/**
 *
 * @author Alysson Pereira
 */
public class Main {
    public static void main(String[] args) {
        CarroRepository carroRepository = new CarroRepository();
        MotoRepository motoRepository = new MotoRepository();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        FormaPagamentoRepository formaPagamentoRepository = new FormaPagamentoRepository();
        PagamentoAvistaRepository avistaRepository = new PagamentoAvistaRepository();
        PagamentoFinanciadoRepository financiadoRepository = new PagamentoFinanciadoRepository();
        VendaRepository vendaRepository = new VendaRepository();
        
        
//        PagamentoAvista avista = new PagamentoAvista("Avista Pix", 10);
//        //System.out.println(avistaRepository.createPagamentoAvista(avista));
//        for(PagamentoAvista pa: avistaRepository.getAll()) {
//            System.out.println(pa.getId()+":"+pa);
//        }
        
//        PagamentoFinanciado financiado = new PagamentoFinanciado("Financiado", 2.2, 24);
//        //System.out.println(financiadoRepository.createPagamentoFinanciado(financiado));
//        for(PagamentoFinanciado pf: financiadoRepository.getAll()) {
//            System.out.println(pf.getId()+":"+pf);
//        }
        
        System.out.println("Formas de pagamento");
        for(FormaPagamento pagamento: formaPagamentoRepository.getAll()) {
            System.out.println(pagamento.getId()+":"+pagamento);
        }
        
        //System.out.println(formaPagamentoRepository.getIdFormaPagamento("Pagamento Financiado"));
        
        System.out.println("Veiculos");
        //carroRepository.deleteCarro("OAO1A58");
//        Carro carro01 = new Carro("OAO1A58", "VW", "POLO", 2022, 80400, "Branco", 30, 5,"Flex", "Automatico");
//        System.out.println(carroRepository.createCarro(carro01));
        
        //Update Carro
//        Carro carro = carroRepository.getCarro("OAO1A58");
//        System.out.println(carro);
//        carro.setPrecoVenda(80300);
//        carroRepository.updateCarro(carro);
//        System.out.println(carroRepository.getCarro("OAO1A58"));
        
        
        for(Carro c: carroRepository.getAll()) {
            System.out.println(c);
        }
        
        //Moto moto01 = new Moto("AAA1S34", "Honda", "POP", 2023, 11000, "Branco", 0.9 ,110);
        for(Moto moto: motoRepository.getAll()) {
            System.out.println(moto);
        }
        
        //System.out.println("Moto cadastrada: " + motoRepository.createMoto(moto01));
//        Moto updateMoto = motoRepository.getMoto("AAA1S34");
//        updateMoto.setPrecoVenda(11400);
//        System.out.println("Moto atualizada: " + motoRepository.updateMoto(updateMoto));
//        
//        System.out.println(motoRepository.getMoto("AAA1S34"));
//        
//        System.out.println(veiculoRepository.getIdVeiculo("AAA1S34"));
        
        
        System.out.println("Clientes");
//        Cliente cli01 = new Cliente("111.222.333-44", "Alysson", "(83)91111-2222", "alysson@gmail.com", "2003-11-21");
//        Cliente cli02 = new Cliente("100.200.304-40", "fulanildo", "(83)98888-9022", "fulannildo@gmail.com", "2002-10-10");
//        
//        clienteRepository.createCliente(cli01);
//        clienteRepository.createCliente(cli02);
        
//        Cliente clienteConsultado = clienteRepository.getCliente("100.200.304-40");
//        clienteConsultado.setNome("Fulanildo da Silva");
//        clienteRepository.updateCliente(clienteConsultado);
        
        ArrayList<Cliente> clientes = clienteRepository.readAll();
        for(Cliente cliente: clientes) {
            System.out.println("ID: "+cliente.getId()+" = "+cliente);
        }
        
        //clienteRepository.deleteCliente("100.200.304-40");
        
        Funcionario fun01 = new Funcionario(1,"444.333.222-11", "Jose", "(83)93333-2211", "jose@gmail.com", "j12345");
        
        
        // Venda Veiculo
        Veiculo veiculoVenda = veiculoRepository.getVeiculo("OAO1A58");
//       
        Cliente clienteComprador = clienteRepository.getCliente("100.200.304-40");
        int idFormaPagamento = formaPagamentoRepository.getIdFormaPagamento("Avista Pix");
        FormaPagamento formaPagamento = formaPagamentoRepository.getFormaPagamento(idFormaPagamento);
        Venda venda01 = new Venda(veiculoVenda, clienteComprador, formaPagamento, "2023-06-28");
        System.out.println(vendaRepository.venderVeiculo(venda01));
      
        
        System.out.println("Vendas realizadas");
        for(Venda venda: vendaRepository.getAll()) {
            System.out.println(venda);
//            System.out.println(vendaRepository.deleteVenda(venda));
        }
        
        System.out.println(veiculoRepository.getVeiculo("OAO1A58"));

        
    }
}
