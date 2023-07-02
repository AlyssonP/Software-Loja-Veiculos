package Model.Repository;

import Model.Entity.Cliente;
import Model.Entity.FormaPagamento;
import Model.Entity.Veiculo;
import Model.Entity.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author alyss
 */
public class VendaRepository {
    
    private static final VeiculoRepository veiculoRepository = new VeiculoRepository();
    private static final ClienteRepository clienteRepository = new ClienteRepository();
    private static final FormaPagamentoRepository formaPagamentoRepository = new FormaPagamentoRepository();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public boolean venderVeiculo(Venda venda) {
        Veiculo veiculo = venda.getVeiculo();
        Cliente cliente = venda.getCliente();
        FormaPagamento formaPagamento = venda.getFormaPagamento();
        boolean isVendido = false;
        
        if(veiculo.getVendido() == true) {
            return isVendido;
        }
        
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "INSERT INTO venda (id_veiculo, id_cliente, id_forma_pagamento, valor_final, data_venda) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1,veiculo.getId());
            preparedStatement.setInt(2,cliente.getId());
            preparedStatement.setInt(3,formaPagamento.getId());
            preparedStatement.setDouble(4, venda.getValorPagamento());
            preparedStatement.setDate(5,Date.valueOf(venda.getDataVenda()));
            
            isVendido = veiculoRepository.venderVeiculo(veiculo);
            if(isVendido) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao vender o veiculo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isVendido;
        }
    }
    
    public Venda getVenda(int id) {
        Connection connection = null;
        Venda venda = null;
        try{
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM venda where id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                Veiculo veiculo = veiculoRepository.getVeiculo(resultSet.getInt("id_veiculo"));
                Cliente cliente = clienteRepository.getCliente(resultSet.getInt("id_cliente"));
                FormaPagamento formaPagamento = formaPagamentoRepository.getFormaPagamento(resultSet.getInt("id_forma_pagamento"));
                String dataVenda = formatter.format(resultSet.getDate("data_venda"));
              
                venda = new Venda(resultSet.getInt("id"), veiculo, cliente, formaPagamento, dataVenda);
            }
            
        } catch (SQLException e) {
            System.out.println("Houve um erro ao buscar uma venda: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return venda;
        }
    }
    
    public Venda getVenda(String placaVeiculo) {
        Connection connection = null;
        Venda venda = null;
        Veiculo isVeiculo = veiculoRepository.getVeiculo(placaVeiculo);
        if(isVeiculo == null) {
            return null;
        }
        try{
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM venda where id_veiculo=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, isVeiculo.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                Veiculo veiculo = veiculoRepository.getVeiculo(resultSet.getInt("id_veiculo"));
                Cliente cliente = clienteRepository.getCliente(resultSet.getInt("id_cliente"));
                FormaPagamento formaPagamento = formaPagamentoRepository.getFormaPagamento(resultSet.getInt("id_forma_pagamento"));
                String dataVenda = formatter.format(resultSet.getDate("data_venda"));
              
                venda = new Venda(resultSet.getInt("id"), veiculo, cliente, formaPagamento, dataVenda);
            }
            
        } catch (SQLException e) {
            System.out.println("Houve um erro ao buscar uma venda: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return venda;
        }
    }
    
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM venda;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                Veiculo veiculo = veiculoRepository.getVeiculo(resultSet.getInt("id_veiculo"));
                Cliente cliente = clienteRepository.getCliente(resultSet.getInt("id_cliente"));
                FormaPagamento formaPagamento = formaPagamentoRepository.getFormaPagamento(resultSet.getInt("id_forma_pagamento"));
                String dataVenda = formatter.format(resultSet.getDate("data_venda"));
              
                vendas.add(new Venda(resultSet.getInt("id"), veiculo, cliente, formaPagamento, dataVenda));
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao listar as vendas: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return vendas;
        }
    }
    
    public boolean updateVenda(Venda venda) {
        Veiculo veiculo = venda.getVeiculo();
        Cliente cliente = venda.getCliente();
        FormaPagamento formaPagamento = venda.getFormaPagamento();
        venda.setValorPagamento();
        boolean isUpdate = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "UPDATE venda SET id_veiculo = ?, id_cliente = ?, id_forma_pagamento = ?, valor_final = ?, data_venda = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1,veiculo.getId());
            preparedStatement.setInt(2,cliente.getId());
            preparedStatement.setInt(3,formaPagamento.getId());
            preparedStatement.setDouble(4, venda.getValorPagamento());
            preparedStatement.setDate(5, (Date) Date.valueOf(venda.getDataVenda()));
            preparedStatement.setInt(6, venda.getId());
            
            preparedStatement.executeUpdate();
            isUpdate = true;
        } catch (SQLException e) {
            System.out.println("Houve um erro ao atualizar a venda do veiculo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isUpdate;
        }
    }
    
    public boolean deleteVenda(Venda venda) {
        Veiculo veiculo = venda.getVeiculo();
        boolean isDesvendido = false;
        
        int idVenda = venda.getId();
        if(idVenda == 0) {
            return false;
        }
        
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql = "DELETE FROM venda WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, idVenda);
            
            isDesvendido = veiculoRepository.desvenderVeiculo(veiculo);
            if(isDesvendido) {
                preparedStatement.executeUpdate();
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao deletar a venda: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isDesvendido;
        }
    }
}
