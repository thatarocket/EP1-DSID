package Global;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepository extends UnicastRemoteObject implements IPartRepository {

    private List<Part> partRep = new ArrayList<Part>();

    public PartRepository() throws RemoteException {
        super();
    }

    @Override
    public void addP(Part part) throws RemoteException {
        partRep.add(part);
    }

    @Override
    public Part getP(int codigo) throws RemoteException {
        if(partRep.isEmpty()) return null;
        for (Part part : partRep) {
            if (part.getCodigo() == codigo) return part;  
        }
        return null;
    }

    @Override
    public List<Part> listP() throws Exception {
        if (partRep.size() == 0) return null;
        return partRep;
    }
}
