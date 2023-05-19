package Global;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPartRepository extends Remote {
    public boolean funcionando() throws RemoteException;

    public void addP(Part part) throws RemoteException;
    public Part getP(int codigo) throws RemoteException;
    public List<Part> listP() throws Exception; 
    public List<Part> clearList(List<Part> subParts) throws RemoteException;
    public void addSubPart(List<Part> subParts, Part part) throws RemoteException; 
    public void showPart(Part part) throws RemoteException; //showp
}
