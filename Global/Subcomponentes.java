package Global;
public class Subcomponentes {
    private Part subPart;
    private int quantidade;

    public Subcomponentes(Part subPart, int quantidade) {
        this.subPart = subPart;
        this.quantidade = quantidade;
    }   

    public Part getSubPart() {
        return subPart;
    }

    public void setSubPart(Part subPart) {
        this.subPart = subPart;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantidade = quantidade;
    }
}
