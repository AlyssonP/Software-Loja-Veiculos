package Model.Repository;

import Model.Entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alyss
 */
public class VeiculoRepository {
    
    private static final CarroRepository carroRepository = new CarroRepository();
    private static final MotoRepository motoRepository = new MotoRepository();
    
    public int getIdVeiculo(String placa) {
        int idVeiculo = -1;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo WHERE placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, placa);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                idVeiculo = resultSet.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return idVeiculo;
        }
    }
    
    public Veiculo getVeiculo(int id) {
        Connection connection = null;
        Veiculo veiculo = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                int tipo_veiculo = resultSet.getInt("id_tipo_veiculo");
                if(tipo_veiculo == 1) {
                    veiculo = carroRepository.getCarro(resultSet.getString("placa"));
                } else if (tipo_veiculo == 2) {
                    veiculo = motoRepository.getMoto(resultSet.getString("placa"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar o veiculo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return veiculo;
        }
    }
    
    public Veiculo getVeiculo(String placa) {
        Connection connection = null;
        Veiculo veiculo = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM veiculo WHERE placa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, placa);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                int tipo_veiculo = resultSet.getInt("id_tipo_veiculo");
                if(tipo_veiculo == 1) {
                    veiculo = carroRepository.getCarro(resultSet.getString("placa"));
                } else if (tipo_veiculo == 2) {
                    veiculo = motoRepository.getMoto(resultSet.getString("placa"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar o veiculo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return veiculo;
        }
    }
    
    public boolean venderVeiculo(Veiculo veiculo) {
        Connection connection = null;
        boolean isVendido = false;
        
        int idVeiculo = veiculo.getId();
        if(idVeiculo == -1) {
            return isVendido;
        }
        
        veiculo.venderVeiculo();
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "UPDATE veiculo SET vendido=? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, idVeiculo);
            preparedStatement.executeUpdate();
            
            isVendido = true;
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao vender veiculo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isVendido;
        }
    }
    
    public boolean deleteVeiculo(String placa) {
        
        int idVeiculo = getIdVeiculo(placa);
        if( idVeiculo == -1) {
            return false;
        }
        
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            
            String codeSql = "DELETE FROM veiculo WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, idVeiculo);
            
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Houve um erro ao deletar o veículo: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
}
