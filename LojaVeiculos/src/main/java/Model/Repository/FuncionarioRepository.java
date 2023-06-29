package Model.Repository;

import Model.Entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alyss
 */
public class FuncionarioRepository {
    
    public boolean createFuncionario(Funcionario funcionario) {
        Connection connection = null;
        
        if(getFuncionario(funcionario.getCpf()) != null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "INSERT INTO funcionario (cpf, nome, celular, email, senha) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3, funcionario.getCelular());
            preparedStatement.setString(4, funcionario.getEmail());
            preparedStatement.setString(5, funcionario.getSenhaAcesso());
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao criar um funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    
    public Funcionario getFuncionario(String cpf) {
        Funcionario funcionario = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM funcionario WHERE cpf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                funcionario = new Funcionario(
                    resultSet.getInt("id"),
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("celular"),
                    resultSet.getString("email"),
                    resultSet.getString("senha")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return funcionario;
        }
    }
    
    public ArrayList<Funcionario> getAll() {
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM funcionario ORDER BY nome";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                funcionarios.add(
                    new Funcionario(
                    resultSet.getInt("id"),
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("celular"),
                    resultSet.getString("email"),
                    resultSet.getString("senha")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao consultar os funcionários: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return funcionarios;
        }
    }
    
    public boolean updateFuncionario(Funcionario funcionario) {
        Connection connection = null;
        
        if(getFuncionario(funcionario.getCpf()) == null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "UPDATE funcionario SET cpf=?, nome=?, celular=?, email=?, senha=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3, funcionario.getCelular());
            preparedStatement.setString(4, funcionario.getEmail());
            preparedStatement.setString(5, funcionario.getSenhaAcesso());
            preparedStatement.setInt(6, funcionario.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao atualizar dados do funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    
    public boolean deleteFuncionario(String cpf) {
        Connection connection = null;
        
        if(getFuncionario(cpf) == null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "DELETE FROM funcionario WHERE cpf=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cpf);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Houve algum erro ao deletar o funcionário: " + e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
}
