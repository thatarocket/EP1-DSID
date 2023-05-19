package Global;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class Part extends UnicastRemoteObject implements IPart {   
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

    

}
