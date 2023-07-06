package Model.Repository;

import Model.Entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * @author Alysson Pereira
 */
public class ClienteRepository {
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean createCliente(Cliente cliente) {
        Connection connection = null;
        
        Cliente isCliente = getCliente(cliente.getCpf());
        if (isCliente != null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "INSERT INTO cliente (cpf, nome, celular, email, data_nascimento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCelular());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setDate(5, Date.valueOf(cliente.getDataNascimento()));
            
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
    //Buscar cliente pelo cpf
    public Cliente getCliente(String cpf) {
        Connection connection = null;
        Cliente cliente = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM cliente WHERE cpf=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Date data_nascimento = resultSet.getDate("data_nascimento");
                cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("celular"),
                        resultSet.getString("email"),
                        formatter.format(data_nascimento));
            }            
            
        } catch(SQLException e) {
            System.out.println("Erro ao consultar um cliente: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return cliente;
        }
    }
    
    //Buscar cliente pelo id
    public Cliente getCliente(int id) {
        Connection connection = null;
        Cliente cliente = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM cliente WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Date data_nascimento = resultSet.getDate("data_nascimento");
                cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("celular"),
                        resultSet.getString("email"),
                        formatter.format(data_nascimento));
            }            
            
        } catch(SQLException e) {
            System.out.println("Erro ao consultar um cliente: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return cliente;
        }
    }
    
    public boolean isEmailCadastrado(String email) {
        Connection connection = null;
        boolean isCadastrado = false;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT cpf FROM cliente WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                isCadastrado = true;
            }            
            
        } catch(SQLException e) {
            System.out.println("Erro ao consultar um cliente: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isCadastrado;
        }
    }
    
    public ArrayList<Cliente> readAll() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "SELECT * FROM cliente ORDER BY nome";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                
                clientes.add( 
                        new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("celular"),
                        resultSet.getString("email"),
                        formatter.format(resultSet.getDate("data_nascimento")))
                );
            }
            
        } catch(SQLException e) {
            System.out.println("Houve algum ao lista os clientes: "+e.getMessage());
        } finally {
            if(connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return clientes;
        }
    }
    
    public boolean updateCliente(Cliente cliente) {
        Connection connection = null;
        Boolean isUpdate = false;
        Cliente isCliente = getCliente(cliente.getCpf());
        if (isCliente == null) {
            return false;
        }
        
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "UPDATE cliente SET cpf=?, nome=?, celular=?, email=?, data_nascimento=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCelular());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setDate(5, Date.valueOf(cliente.getDataNascimento()));
            preparedStatement.setInt(6, isCliente.getId());
            
            preparedStatement.executeUpdate();
            isUpdate = true;
        } catch(SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }
            return isUpdate;
        }
    }

    public boolean deleteCliente(String cpf) {
        
        Cliente isCliente = getCliente(cpf);
        if (isCliente == null) {
            return false;
        }
        
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            String codeSql = "DELETE FROM cliente WHERE cpf=?";
            PreparedStatement preparedStatement = connection.prepareStatement(codeSql);
            preparedStatement.setString(1, cpf);
            
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Houve um erro ao deletar cliente: "+ e.getMessage());
        } finally {
            if (connection != null) {
                ConnectionDB.closeConnection(connection);
                return true;
            }
            return false;
        }
    }
}
