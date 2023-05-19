package Global;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPartRepository extends Remote {
    public void addP(Part part) throws RemoteException;
    public Part getP(int codigo) throws RemoteException;
    public List<Part> listP() throws Exception;  
}
