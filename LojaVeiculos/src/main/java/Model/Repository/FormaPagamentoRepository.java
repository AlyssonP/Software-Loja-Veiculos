package Model.Repository;

import Model.Entity.FormaPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author alyss
 */
public class FormaPagamentoRepository {
    
    private static final PagamentoAvistaRepository avistaRepository = new PagamentoAvistaRepository();
    private static final PagamentoFinanciadoRepository financiadoRepository = new PagamentoFinanciadoRepository();
    
    public int getIdTipoPagamento(String nome) {
        int idTipoPagamento = -1;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM tipo_pagamento WHERE nome=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                idTipoPagamento = resultSet.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println("Houve um erro ao listar as formas de pagamento: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return idTipoPagamento;
        }
    }
    
    public int getIdFormaPagamento(String nome) {
        int idPagamento = -1;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                idPagamento = resultSet.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return idPagamento;
        }
    }
    
    public FormaPagamento getFormaPagamento(int id) {
        FormaPagamento formaPagamento = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                if(resultSet.getInt("id_tipo_pagamento") == getIdTipoPagamento("A vista")) {
                    formaPagamento = avistaRepository.getPagamentoAvista(resultSet.getInt("id"));
                } else if(resultSet.getInt("id_tipo_pagamento") == getIdTipoPagamento("Financiado")) {
                    formaPagamento = financiadoRepository.getPagamentoFinanciado(resultSet.getInt("id"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return formaPagamento;
        }
    }
    
    public ArrayList<FormaPagamento> getAll(){
        ArrayList<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                if(resultSet.getInt("id_tipo_pagamento") == getIdTipoPagamento("A vista")) {
                    formasPagamento.add(
                        avistaRepository.getPagamentoAvista(resultSet.getInt("id")));
                } else if(resultSet.getInt("id_tipo_pagamento") == getIdTipoPagamento("Financiado")) {
                    formasPagamento.add(
                        financiadoRepository.getPagamentoFinanciado(resultSet.getInt("id")));
                }
                
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao listar as formas de pagamento: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return formasPagamento;
        }
    }
    
    public boolean deleteFormaPagamento(int id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql = "DELETE FROM forma_pagamento WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Houve um erro ao deletar a Forma de Pagamento: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
}
