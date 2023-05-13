package Global;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPartRepository extends Remote{
    public String funcionando() throws RemoteException;
    
}
