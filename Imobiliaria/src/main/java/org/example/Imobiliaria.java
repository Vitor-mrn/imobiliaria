package org.example;

import org.example.dao.ImovelDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.ContratoDAO;

import java.util.List;
import java.util.Map;

public class Imobiliaria {
    private ImovelDAO imovelDAO;
    private ClienteDAO clienteDAO;
    private ContratoDAO contratoDAO;

    public Imobiliaria() {
        this.imovelDAO = new ImovelDAO();
        this.clienteDAO = new ClienteDAO();
        this.contratoDAO = new ContratoDAO();
    }

    public void adicionarImovel(Imovel imovel) {
        imovelDAO.adicionarImovel(imovel);
    }

    public void adicionarCliente(Cliente cliente) {
        clienteDAO.adicionarCliente(cliente);
    }

    public void adicionarContrato(Contrato contrato) {
        contratoDAO.adicionarContrato(contrato);
    }

    public List<Imovel> listarImoveisDisponiveis() {
        return imovelDAO.listarImoveisDisponiveis();
    }

    public List<Contrato> listarContratosAtivos() {
        return contratoDAO.listarContratosAtivos();
    }

    public Map<Cliente, Integer> clientesComMaisContratos() {
        return clienteDAO.clientesComMaisContratos();
    }

    public List<Contrato> contratosExpirandoEm30Dias() {
        return contratoDAO.contratosExpirandoEm30Dias();
    }
}
