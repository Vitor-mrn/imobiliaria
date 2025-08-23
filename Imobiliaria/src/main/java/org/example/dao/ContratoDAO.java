package org.example.dao;

import org.example.Contrato;
import org.example.Imovel;
import org.example.Cliente;
import org.example.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContratoDAO {

    public void adicionarContrato(Contrato contrato) {
        String sql = "INSERT INTO Contrato (id, imovel_id, cliente_id, valorAluguel, dataInicio, dataFim, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, contrato.getId());
            pstmt.setInt(2, contrato.getImovel().getId());
            pstmt.setInt(3, contrato.getCliente().getId());
            pstmt.setDouble(4, contrato.getValorAluguel());
            pstmt.setDate(5, new java.sql.Date(contrato.getDataInicio().getTime()));
            pstmt.setDate(6, new java.sql.Date(contrato.getDataFim().getTime()));
            pstmt.setBoolean(7, contrato.isAtivo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar contrato: " + e.getMessage());
        }
    }

    public List<Contrato> listarContratosAtivos() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT * FROM Contrato WHERE ativo = TRUE";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ImovelDAO imovelDAO = new ImovelDAO();
            ClienteDAO clienteDAO = new ClienteDAO();

            while (rs.next()) {
                Imovel imovel = imovelDAO.buscarImovel(rs.getInt("imovel_id"));
                Cliente cliente = clienteDAO.buscarCliente(rs.getInt("cliente_id"));
                Contrato contrato = new Contrato(
                    rs.getInt("id"),
                    imovel,
                    cliente,
                    rs.getDouble("valorAluguel"),
                    rs.getDate("dataInicio"),
                    rs.getDate("dataFim")
                );
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos ativos: " + e.getMessage());
        }
        return contratos;
    }

    public List<Contrato> contratosExpirandoEm30Dias() {
        List<Contrato> expirando = new ArrayList<>();
        String sql = "SELECT * FROM Contrato WHERE ativo = TRUE AND dataFim BETWEEN DATE('now') AND DATE('now', '+30 days')";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ImovelDAO imovelDAO = new ImovelDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            
            while (rs.next()) {
                Imovel imovel = imovelDAO.buscarImovel(rs.getInt("imovel_id"));
                Cliente cliente = clienteDAO.buscarCliente(rs.getInt("cliente_id"));
                Contrato contrato = new Contrato(
                    rs.getInt("id"),
                    imovel,
                    cliente,
                    rs.getDouble("valorAluguel"),
                    rs.getDate("dataInicio"),
                    rs.getDate("dataFim")
                );
                expirando.add(contrato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos expirando: " + e.getMessage());
        }
        return expirando;
    }
}
