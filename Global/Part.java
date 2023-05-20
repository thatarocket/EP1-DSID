package Global;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;

public class Part implements IPart, Serializable {   
    private int codigo; //automaticamente gerado
    private String nome;
    private String descricao;
    private HashMap<Part, Integer> subparts;
    // primitiva: lista de componentes vazia

    public Part() throws RemoteException {
        this.codigo = gerarCodigo();
    }

    public Part(String nome, String descricao) throws RemoteException {
        this.codigo = gerarCodigo();
        this.nome = nome;
        this.descricao = descricao;
    }

    public int gerarCodigo() throws RemoteException {
        UUID uuid = UUID.randomUUID();
        long codigoLong = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        return (int) codigoLong;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HashMap<Part, Integer> getSubparts() {
        return subparts;
    }

    public void setSubparts(HashMap<Part, Integer> subparts) {
        this.subparts = subparts;
    }

    @Override
    public String toString() {
        StringBuilder subparts = new StringBuilder();

        if (this.subparts != null) {
            this.subparts.forEach((part, quant) -> {
                subparts.append(String.format(
                    "\tCodigo: %s; Nome: %s; Quant: %s",
                    part.codigo,
                    part.nome,
                    quant
                ));
            });
        }

        return String.format(
            "Codigo: %s\nNome: %s\nDescricao: %s\nSubparts:\n%s",
            this.codigo,
            this.nome,
            this.descricao,
            subparts
        );
    }
}
