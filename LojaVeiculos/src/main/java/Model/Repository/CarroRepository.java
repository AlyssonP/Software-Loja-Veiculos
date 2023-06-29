package Model.Repository;

import Model.Entity.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author alyss
 */
public class CarroRepository {
    
    private static final VeiculoRepository veiculoRepository = new VeiculoRepository();
    
    public boolean createCarro(Carro carro) {
        Connection connection = null;
        
        if(getCarro(carro.getPlaca()) != null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql01 = "INSERT INTO veiculo (id_tipo_veiculo, placa, marca, modelo, ano, cor, preco_venda, quilometragem, vendido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql01);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, carro.getPlaca());
            preparedStatement.setString(3, carro.getMarca());
            preparedStatement.setString(4, carro.getModelo());
            preparedStatement.setInt(5, carro.getAno());
            preparedStatement.setString(6, carro.getCor());
            preparedStatement.setDouble(7, carro.getPrecoVenda());
            preparedStatement.setDouble(8, carro.getQuilomentragem());
            preparedStatement.setBoolean(9, carro.getVendido());
            
            preparedStatement.executeUpdate();
            
            int idVeiculo = veiculoRepository.getIdVeiculo(carro.getPlaca());
            
            if(idVeiculo != -1 ) {
                String codeSql02 = "INSERT INTO carro (id_veiculo, quantidade_portas, tipo_combustivel, cambio) values ( ?, ?, ?, ?);";
                PreparedStatement preparedStatement01 = connection.prepareStatement(codeSql02);
                preparedStatement01.setInt(1, idVeiculo);
                preparedStatement01.setInt(2, carro.getQuatidadePorta());
                preparedStatement01.setString(3, carro.getTipoCombustivel());
                preparedStatement01.setString(4, carro.getCambio());
                
                preparedStatement01.executeUpdate();
                
            }
            
        } catch (SQLException e) {
            System.out.println("Houve um erro ao cadastrar o carro: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    
    public Carro getCarro(String placa) {
        Carro carro = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo as v, carro as c WHERE v.id = c.id_veiculo and placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, placa);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                
                carro = new Carro(
                        resultSet.getInt("id"),
                        resultSet.getString("placa"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("ano"),
                        resultSet.getDouble("preco_venda"),
                        resultSet.getString("cor"),
                        resultSet.getDouble("quilometragem"),
                        resultSet.getInt("quantidade_portas"),
                        resultSet.getString("tipo_combustivel"),
                        resultSet.getString("cambio"),
                        resultSet.getBoolean("vendido")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return carro;
        }
    }
    
    public ArrayList<Carro> getAll() {
        ArrayList<Carro> carros = new ArrayList<Carro>();
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo as v, carro as c WHERE v.id = c.id_veiculo";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                carros.add(
                        new Carro(
                            resultSet.getInt("id"),
                            resultSet.getString("placa"),
                            resultSet.getString("marca"),
                            resultSet.getString("modelo"),
                            resultSet.getInt("ano"),
                            resultSet.getDouble("preco_venda"),
                            resultSet.getString("cor"),
                            resultSet.getDouble("quilometragem"),
                            resultSet.getInt("quantidade_portas"),
                            resultSet.getString("tipo_combustivel"),
                            resultSet.getString("cambio"),
                            resultSet.getBoolean("vendido")
                        ));
            }
            
        } catch(SQLException e) {
            System.out.println("Houve um erro ao listar todos os carros: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return carros;
        }
    }
    
    public boolean updateCarro(Carro carro) {
        
        if (getCarro(carro.getPlaca()) == null) {
            return false;
        }
        
        int idVeiculo = veiculoRepository.getIdVeiculo(carro.getPlaca());
        
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql01 = "UPDATE veiculo SET placa=?, marca=?, modelo=?, ano=?, cor=?, preco_venda=?, quilometragem=?, vendido=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql01);
            preparedStatement.setString(1, carro.getPlaca());
            preparedStatement.setString(2, carro.getMarca());
            preparedStatement.setString(3, carro.getModelo());
            preparedStatement.setInt(4, carro.getAno());
            preparedStatement.setString(5, carro.getCor());
            preparedStatement.setDouble(6, carro.getPrecoVenda());
            preparedStatement.setDouble(7, carro.getQuilomentragem());
            preparedStatement.setBoolean(8, carro.getVendido());
            preparedStatement.setInt(9, idVeiculo);
            
            preparedStatement.executeUpdate();
            
            if(idVeiculo != -1 ) {
                String codeSql02 = "UPDATE carro SET quantidade_portas=?, tipo_combustivel=?, cambio=? WHERE id_veiculo = ?";
                PreparedStatement preparedStatement01 = connection.prepareStatement(codeSql02);
                preparedStatement01.setInt(1, carro.getQuatidadePorta());
                preparedStatement01.setString(2, carro.getTipoCombustivel());
                preparedStatement01.setString(3, carro.getCambio());
                preparedStatement01.setInt(4, idVeiculo);
                
                preparedStatement01.executeUpdate();
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao atualizar os dados do carro: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
        
    }
    
    public boolean deleteCarro(String placa) {
        return veiculoRepository.deleteVeiculo(placa);
    }

}
