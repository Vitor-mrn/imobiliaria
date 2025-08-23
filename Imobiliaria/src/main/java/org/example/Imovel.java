public class Imovel {
    private int id;
    private String endereco;
    private String tipo;
    private int quartos;
    private int banheiros;
    private double areaUtil;
    private double precoAluguel;
    private boolean disponivel;

    public Imovel(int id, String endereco, String tipo, int quartos, int banheiros, double areaUtil, double precoAluguel) {
        this.id = id;
        this.endereco = endereco;
        this.tipo = tipo;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.areaUtil = areaUtil;
        this.precoAluguel = precoAluguel;
        this.disponivel = true;
    }

    public void alugar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }

    public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
}

    // Getters e Setters
    public int getId() { return id; }
    public String getEndereco() { return endereco; }
    public String getTipo() { return tipo; }
    public double getPrecoAluguel() { return precoAluguel; }
    public boolean isDisponivel() { return disponivel; }
}
