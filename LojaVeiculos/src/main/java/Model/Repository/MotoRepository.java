package Model.Repository;

import Model.Entity.Moto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alyss
 */
public class MotoRepository {
    
    private static final VeiculoRepository veiculoRepository = new VeiculoRepository();
    
    public boolean createMoto(Moto moto) {
        Connection connection = null;
                
        if(getMoto(moto.getPlaca()) != null) {
            return false;
        }
        try {
            connection = ConnectionDB.getConnection();
            String codeSql01 = "INSERT INTO veiculo (id_tipo_veiculo, placa, marca, modelo, ano, cor, preco_venda, quilometragem, vendido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql01);
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, moto.getPlaca());
            preparedStatement.setString(3, moto.getMarca());
            preparedStatement.setString(4, moto.getModelo());
            preparedStatement.setInt(5, moto.getAno());
            preparedStatement.setString(6, moto.getCor());
            preparedStatement.setDouble(7, moto.getPrecoVenda());
            preparedStatement.setDouble(8, moto.getQuilomentragem());
            preparedStatement.setBoolean(9, moto.getVendido());
            
            preparedStatement.executeUpdate();
            
            int idVeiculo = veiculoRepository.getIdVeiculo(moto.getPlaca());
            
            if(idVeiculo != -1 ) {
                String codeSql02 = "INSERT INTO  moto (id_veiculo, cilindrada) values ( ?, ?);";
                PreparedStatement preparedStatement01 = connection.prepareStatement(codeSql02);
                preparedStatement01.setInt(1, idVeiculo);
                preparedStatement01.setInt(2, moto.getCilindrada());
                
                preparedStatement01.executeUpdate();
                
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao cadastrar Moto: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    
    public ArrayList<Moto> getAll() {
        ArrayList<Moto> motos = new ArrayList<Moto>();
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo as v, moto as m WHERE v.id = m.id_veiculo";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                motos.add(new Moto(
                        resultSet.getInt("id"),
                        resultSet.getString("placa"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("ano"),
                        resultSet.getDouble("preco_venda"),
                        resultSet.getString("cor"),
                        resultSet.getDouble("quilometragem"),
                        resultSet.getInt("cilindrada"),
                        resultSet.getBoolean("vendido")
                ));
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao listar as motos: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return motos;
        }
    }
    
    public Moto getMoto(String placa) {
        Moto moto = null;
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo as v, moto as m WHERE v.id = m.id_veiculo and placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, placa);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                moto = new Moto(
                        resultSet.getInt("id"),
                        resultSet.getString("placa"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("ano"),
                        resultSet.getDouble("preco_venda"),
                        resultSet.getString("cor"),
                        resultSet.getDouble("quilometragem"),
                        resultSet.getInt("cilindrada"),
                        resultSet.getBoolean("vendido")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Houve um erro ao consultar moto: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return moto;
        }
    }
    
    public boolean updateMoto(Moto moto) {
        
        if (getMoto(moto.getPlaca()) == null) {
            return false;
        }
        
        int idVeiculo = veiculoRepository.getIdVeiculo(moto.getPlaca());
        
        Connection connection = null;
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql01 = "UPDATE veiculo SET placa=?, marca=?, modelo=?, ano=?, cor=?, preco_venda=?, quilometragem=?, vendido=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql01);
            preparedStatement.setString(1, moto.getPlaca());
            preparedStatement.setString(2, moto.getMarca());
            preparedStatement.setString(3, moto.getModelo());
            preparedStatement.setInt(4, moto.getAno());
            preparedStatement.setString(5, moto.getCor());
            preparedStatement.setDouble(6, moto.getPrecoVenda());
            preparedStatement.setDouble(7, moto.getQuilomentragem());
            preparedStatement.setBoolean(8, moto.getVendido());
            preparedStatement.setInt(9, idVeiculo);
            
            preparedStatement.executeUpdate();
            
            if(idVeiculo != -1 ) {
                String codeSql02 = "UPDATE moto SET cilindrada = ? WHERE id_veiculo = ?";
                PreparedStatement preparedStatement01 = connection.prepareStatement(codeSql02);
                preparedStatement01.setInt(1, moto.getCilindrada());
                preparedStatement01.setInt(2, idVeiculo);
                
                preparedStatement01.executeUpdate();
            }
        } catch(SQLException e) {
            System.out.println("Houve um erro ao atualizar os dados da Moto: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
        
    }
    
    public boolean deleteMoto(String placa) {
        return veiculoRepository.deleteVeiculo(placa);
    }
}
