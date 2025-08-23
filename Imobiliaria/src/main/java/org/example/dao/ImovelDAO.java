package org.example.dao;

import org.example.Imovel;
import org.example.database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {

    public void adicionarImovel(Imovel imovel) {
        String sql = "INSERT INTO Imovel (id, endereco, tipo, quartos, banheiros, areaUtil, precoAluguel, disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, imovel.getId());
            pstmt.setString(2, imovel.getEndereco());
            pstmt.setString(3, imovel.getTipo());
            pstmt.setInt(4, imovel.getQuartos());
            pstmt.setInt(5, imovel.getBanheiros());
            pstmt.setDouble(6, imovel.getAreaUtil());
            pstmt.setDouble(7, imovel.getPrecoAluguel());
            pstmt.setBoolean(8, imovel.isDisponivel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar imóvel: " + e.getMessage());
        }
    }

    public Imovel buscarImovel(int id) {
        String sql = "SELECT * FROM Imovel WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Imovel(
                    rs.getInt("id"),
                    rs.getString("endereco"),
                    rs.getString("tipo"),
                    rs.getInt("quartos"),
                    rs.getInt("banheiros"),
                    rs.getDouble("areaUtil"),
                    rs.getDouble("precoAluguel")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar imóvel: " + e.getMessage());
        }
        return null;
    }

    public List<Imovel> listarTodosImoveis() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM Imovel";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Imovel imovel = new Imovel(
                        rs.getInt("id"),
                        rs.getString("endereco"),
                        rs.getString("tipo"),
                        rs.getInt("quartos"),
                        rs.getInt("banheiros"),
                        rs.getDouble("areaUtil"),
                        rs.getDouble("precoAluguel")
                );
                imovel.setDisponivel(rs.getBoolean("disponivel"));
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar imóveis: " + e.getMessage());
        }
        return imoveis;
    }

    public List<Imovel> listarImoveisDisponiveis() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM Imovel WHERE disponivel = TRUE";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Imovel imovel = new Imovel(
                        rs.getInt("id"),
                        rs.getString("endereco"),
                        rs.getString("tipo"),
                        rs.getInt("quartos"),
                        rs.getInt("banheiros"),
                        rs.getDouble("areaUtil"),
                        rs.getDouble("precoAluguel")
                );
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar imóveis disponíveis: " + e.getMessage());
        }
        return imoveis;
    }
}
