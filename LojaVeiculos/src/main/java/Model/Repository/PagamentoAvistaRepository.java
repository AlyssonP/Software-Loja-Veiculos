package Model.Repository;

import Model.Entity.PagamentoAvista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author alyss
 */
public class PagamentoAvistaRepository {
    
    private static final FormaPagamentoRepository formaPagamentoRepository = new FormaPagamentoRepository();
    
    public boolean createPagamentoAvista(PagamentoAvista pagamentoAvista) {
        Connection connection = null;
        boolean isCadastrou = false;
        
        int idTipoPagamento = formaPagamentoRepository.getIdTipoPagamento("A vista");
        if(idTipoPagamento == -1) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql = "INSERT INTO forma_pagamento (id_tipo_pagamento, nome) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, idTipoPagamento);
            preparedStatement.setString(2, pagamentoAvista.getNome());
            preparedStatement.executeUpdate();
            
            int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoAvista.getNome());
            if(idPagamento != -1) {
                codeSql = "INSERT INTO pagamento_avista (id_forma_pagamento, percentual_desconto) VALUES (?, ?)";
                PreparedStatement preparedStatement02 = connection.prepareStatement(codeSql);
                preparedStatement02.setInt(1,idPagamento);
                preparedStatement02.setDouble(2, pagamentoAvista.getPercentualDisconto());
                preparedStatement02.executeUpdate();
            }
            isCadastrou = true;
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao criar um forma de pagamento avista: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isCadastrou;
        }
    }
    
    public PagamentoAvista getPagamentoAvista(int id) {
        PagamentoAvista pagamentoAvista = null;
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento fp, pagamento_avista pa WHERE fp.id = pa.id_forma_pagamento AND id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                pagamentoAvista = new PagamentoAvista(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("percentual_desconto")
                );
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao consultar Forma Pagamento: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return pagamentoAvista;
        }
    }
    
    public ArrayList<PagamentoAvista> getAll() {
        ArrayList<PagamentoAvista> pagamentos = new ArrayList<PagamentoAvista>();
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM forma_pagamento fp, pagamento_avista pa WHERE fp.id = pa.id_forma_pagamento;";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                pagamentos.add(
                        new PagamentoAvista(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("percentual_desconto")
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro lista as Formas de Pagamentos Avista: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return pagamentos;
        }
        
    }
            
    public boolean updatePagamentoAvista(PagamentoAvista pagamentoAvista) {
        int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoAvista.getNome());
        if(idPagamento == -1) {
            return false;
        }
        
        Connection connection = null;
        try {
            String codeSql = "UPDATE forma_pagamento SET nome=? WHARE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, pagamentoAvista.getNome());
            preparedStatement.setInt(2,idPagamento);
            preparedStatement.executeUpdate();
            
            if(idPagamento != -1) {
                codeSql = "UPDATE pagamento_avista SET percentual_desconto=? WHERE id_forma_pagamento=?";
                PreparedStatement preparedStatement02 = connection.prepareStatement(codeSql);
                preparedStatement02.setDouble(1, pagamentoAvista.getPercentualDisconto());
                preparedStatement02.setInt(2,idPagamento);
                preparedStatement02.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao atualizar os dados de pagemento Avista: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }  

    public boolean deletePagamentoAvista(PagamentoAvista pagamentoAvista) {
        int idPagamento = formaPagamentoRepository.getIdFormaPagamento(pagamentoAvista.getNome());
        if(idPagamento == -1) {
            return false;
        }
        return formaPagamentoRepository.deleteFormaPagamento(idPagamento);
    }
}
