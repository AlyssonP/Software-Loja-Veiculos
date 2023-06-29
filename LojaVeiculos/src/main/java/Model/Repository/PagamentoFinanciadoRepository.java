package Model.Repository;

import Model.Entity.PagamentoFinanciado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alyss
 */
public class PagamentoFinanciadoRepository {
    
    private static final FormaPagamentoRepository formaPagamentoRepository = new FormaPagamentoRepository();
    
    public boolean createPagamentoFinanciado(PagamentoFinanciado pagamentoFinanciado) {
        Connection connection = null;
        
        int idTipoPagamento = formaPagamentoRepository.getIdTipoPagamento("Financiado");
        if(idTipoPagamento == -1) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql = "INSERT INTO forma_pagamento (id_tipo_pagamento, nome) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, idTipoPagamento);
            preparedStatement.setString(2, pagamentoFinanciado.getNome());
            preparedStatement.executeUpdate();
            
            int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoFinanciado.getNome());
            if(idPagamento != -1) {
                codeSql = "INSERT INTO pagamento_financiado (id_forma_pagamento, taxa_juros, quantidade_parcelas) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement02 = connection.prepareStatement(codeSql);
                preparedStatement02.setInt(1,idPagamento);
                preparedStatement02.setDouble(2, pagamentoFinanciado.getTaxaJuros());
                preparedStatement02.setInt(3, pagamentoFinanciado.getQuantidadeParcelas());
                preparedStatement02.executeUpdate();
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao criar um forma de pagamento Financiado: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    
    public PagamentoFinanciado getPagamentoFinanciado(int id) {
        PagamentoFinanciado pagamentoFinanciado = null;
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento fp, pagamento_financiado pf WHERE fp.id = pf.id_forma_pagamento AND id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                pagamentoFinanciado = new PagamentoFinanciado(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("taxa_juros"),
                        resultSet.getInt("quantidade_parcelas")
                );
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao consultar Forma Pagamento: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return pagamentoFinanciado;
        }
    }
    
    public ArrayList<PagamentoFinanciado> getAll() {
        ArrayList<PagamentoFinanciado> pagamentos = new ArrayList<PagamentoFinanciado>();
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento fp, pagamento_financiado pf WHERE fp.id = pf.id_forma_pagamento;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                pagamentos.add(
                        new PagamentoFinanciado(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("taxa_juros"),
                        resultSet.getInt("quantidade_parcelas")
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro lista as Formas de Pagamentos Financiados: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return pagamentos;
        }
        
    }
            
    public boolean updatePagamentoFinanciado(PagamentoFinanciado pagamentoFinanciado) {
        int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoFinanciado.getNome());
        if(idPagamento == -1) {
            return false;
        }
        
        Connection connection = null;
        try {
            String codeSql = "UPDATE forma_pagamento SET nome=? WHARE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, pagamentoFinanciado.getNome());
            preparedStatement.setInt(2,idPagamento);
            preparedStatement.executeUpdate();
            
            if(idPagamento != -1) {
                codeSql = "INSERT INTO pagamento_financiado (id_forma_pagamento, taxa_juros, quantidade_parcelas) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement02 = connection.prepareStatement(codeSql);
                preparedStatement02.setInt(1,idPagamento);
                preparedStatement02.setDouble(2, pagamentoFinanciado.getTaxaJuros());
                preparedStatement02.setInt(3, pagamentoFinanciado.getQuantidadeParcelas());
                preparedStatement02.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao atualizar os dados de pagemento Financiado: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }  

    public boolean deletePagamentoFinanciado(PagamentoFinanciado pagamentoFinanciado) {
        int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoFinanciado.getNome());
        if(idPagamento == -1) {
            return false;
        }
        return formaPagamentoRepository.deleteFormaPagamento(idPagamento);
    }
}
    
