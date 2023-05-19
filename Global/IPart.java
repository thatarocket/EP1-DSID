package Global;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPart extends Remote {
    public int gerarCodigo() throws RemoteException;
    public int getCodigo() throws RemoteException;
}