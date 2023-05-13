package Global;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// import java.util.List;
import java.util.UUID;

public class Part extends UnicastRemoteObject implements IPart {   
    private int codigo; //automaticamente gerado
    private String nome;
    private String descricao;
    // private List<Subcomponentes> subComp;

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

    public String teste() throws RemoteException {
        return "Teste";
    }

    public void adicionarSubcomponente(Part subPart, int quantidade) throws RemoteException {
        
    }
}
