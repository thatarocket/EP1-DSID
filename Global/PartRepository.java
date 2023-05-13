package Global;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartRepository extends UnicastRemoteObject implements IPartRepository {

    public PartRepository() throws RemoteException {
        super();
    }

    public String funcionando() throws RemoteException {
        return "FUNCIONANDO!";
    }

    // Inserir uma nova part
    // Recuperar uma part
    // Obter uma lista de todas as parts
}
