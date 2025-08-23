package org.example.dao;

import org.example.Cliente;
import org.example.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ClienteDAO {

    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id, nome, cpf) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public Cliente buscarCliente(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public Map<Cliente, Integer> clientesComMaisContratos() {
        Map<Cliente, Integer> clientesCount = new HashMap<>();
        String sql = "SELECT cliente_id, COUNT(*) AS num_contratos FROM Contrato GROUP BY cliente_id ORDER BY num_contratos DESC";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                int numContratos = rs.getInt("num_contratos");
                Cliente cliente = buscarCliente(clienteId);
                if (cliente != null) {
                    clientesCount.put(cliente, numContratos);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes com mais contratos: " + e.getMessage());
        }
        return clientesCount;
    }
}
