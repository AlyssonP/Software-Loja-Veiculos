package Model.Repository;

import Model.Entity.Carro;
import Model.Entity.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author alyss
 */
public class CarroRepository {
    
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
                        resultSet.getString("cambio")
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
}
