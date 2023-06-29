package Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author aluno
 */
public class ConnectionDB {
    private static final String url = "jdbc:postgresql://localhost:5432/ShopMotors";
    private static final String username = "postgres";
    private static final String password = "1100100";
   
    // Fazer conexão
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
    
    // Fechar conexão
    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e){
                System.out.println("Houve um erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
