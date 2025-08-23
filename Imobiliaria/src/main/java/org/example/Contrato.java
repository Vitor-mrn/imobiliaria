import java.util.Date;

public class Contrato {
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private double valorAluguel;
    private Date dataInicio;
    private Date dataFim;
    private boolean ativo;

    public Contrato(int id, Imovel imovel, Cliente cliente, double valorAluguel, Date dataInicio, Date dataFim) {
        this.id = id;
        this.imovel = imovel;
        this.cliente = cliente;
        this.valorAluguel = valorAluguel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = true;
    }

    public void finalizarContrato() {
        this.ativo = false;
    }

    public boolean isExpirado() {
        return new Date().after(dataFim);
    }

    public int getId() { return id; }
    public Date getDataFim() { return dataFim; }
    public boolean isAtivo() { return ativo; }
    public Imovel getImovel() { return imovel; }
    public Cliente getCliente() { return cliente; }
    public double getValorAluguel() { return valorAluguel; }
    public Date getDataInicio() { return dataInicio; }
}
