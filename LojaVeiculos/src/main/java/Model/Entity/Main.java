package Model.Entity;

import Model.Repository.CarroRepository;
import Model.Repository.ClienteRepository;
import java.util.ArrayList;

/**
 *
 * @author Alysson Pereira
 */
public class Main {
    public static void main(String[] args) {
        ClienteRepository clienteRepository = new ClienteRepository();
        CarroRepository carroRepository = new CarroRepository();
        
//        FormaPagamento avista = new PagamentoAvista(1,"Avista", 10);
//        FormaPagamento finaciado = new PagamentoFinanciado(2,"Financiado", 1.5, 24);
//        
//        Veiculo carro01 = new Carro(1,"OAO1A58", "VW", "POLO", 2022, 80400, "Branco", 30, 5,"Flex", "Automatico");
//        Veiculo moto01 = new Moto(2,"AAA1S34", "Honda", "POP", 2023, 11000, "Branco", 0.9 ,110);
//        
//        Pessoa cli01 = new Cliente("111.222.333-44", "Alysson", "(83)91111-2222", "alysson@gmail.com", new Date(2003,11,21 ));
//        
//        Pessoa fun01 = new Funcionario(1,"444.333.222-11", "Jose", "(83)93333-2211", "jose@gmail.com", "j12345");
//        
//        System.out.println("Loja de Veículos.");
//        System.out.println(carro01);
//        System.out.println(moto01);
//        
//        System.out.println("Formas de Pagamentos");
//        System.out.println(avista);
//        System.out.println(finaciado);
//        
//        System.out.println("Clientes");
//        System.out.println(cli01);
//        
//        System.out.println("Funcionários");
//        System.out.println(fun01);
//        
//        Venda venda01 = new Venda(carro01, cli01, avista, new Date(2023,06,17));
//        System.out.println("Vendas");
//        System.out.println(venda01);
//        System.out.println(carro01);
        
        Cliente cli01 = new Cliente("111.222.333-44", "Alysson", "(83)91111-2222", "alysson@gmail.com", "2003-11-21");
        Cliente cli02 = new Cliente("100.200.304-40", "fulanildo", "(83)98888-9022", "fulannildo@gmail.com", "2002-10-10");

        //clienteRepository.createCliente(cli01);
        
//        Cliente clienteConsultado = clienteRepository.getCliente("100.200.304-40");
//        clienteConsultado.setNome("Fulanildo da Silva");
//        clienteRepository.updateCliente(clienteConsultado);
        
//        ArrayList<Cliente> clientes = clienteRepository.readAll();
//        for(Cliente cliente: clientes) {
//            System.out.println("ID: "+cliente.getId()+" = "+cliente);
//        }
        
        //clienteRepository.deleteCliente("100.200.304-40");
        
        System.out.println(carroRepository.getCarro("OAO1A58"));
    }
}
