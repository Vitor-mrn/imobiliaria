package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.ImovelDAO;
import org.example.dao.ContratoDAO;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // Inicializa o banco de dados e cria as tabelas se não existirem
        // Você pode executar o arquivo imobiliaria.sql uma vez para criar as tabelas
        // manualmente.
        
        Imobiliaria imobiliaria = new Imobiliaria();

        System.out.println("Imóveis disponíveis:");
        for (Imovel imovel : imobiliaria.listarImoveisDisponiveis()) {
            System.out.println("Endereço: " + imovel.getEndereco());
        }

        System.out.println("\nContratos ativos:");
        for (Contrato contrato : imobiliaria.listarContratosAtivos()) {
            System.out.println("Imóvel: " + contrato.getImovel().getEndereco() + " | Cliente: " + contrato.getCliente().getNome());
        }

        System.out.println("\nClientes com mais contratos:");
        Map<Cliente, Integer> clientesMaisContratos = imobiliaria.clientesComMaisContratos();
        for (Map.Entry<Cliente, Integer> entry : clientesMaisContratos.entrySet()) {
            System.out.println(entry.getKey().getNome() + ": " + entry.getValue() + " contratos");
        }
        
        System.out.println("\nContratos expirando em 30 dias:");
        List<Contrato> contratosExpirando = imobiliaria.contratosExpirandoEm30Dias();
        if (contratosExpirando.isEmpty()) {
            System.out.println("Nenhum contrato expira nos próximos 30 dias.");
        } else {
            for (Contrato contrato : contratosExpirando) {
                System.out.println("Contrato ID: " + contrato.getId() + " | Cliente: " + contrato.getCliente().getNome() + " | Data de Fim: " + contrato.getDataFim());
            }
        }
    }
}
