package Global;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPart extends Remote {
    public int gerarCodigo() throws RemoteException;
    public String teste() throws RemoteException;
    public void adicionarSubcomponente(Part subPart, int quantidade) throws RemoteException;
}