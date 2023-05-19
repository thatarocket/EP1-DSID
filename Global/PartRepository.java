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

    public boolean funcionando() throws RemoteException {
        return true;
    }

    @Override
    public void addP(Part part) throws RemoteException {
        partRep.add(part);
    }

    @Override
    public Part getP(int codigo) throws RemoteException {
        System.out.println("Entrei no getP");
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

    @Override
    public List<Part> clearList(List<Part> subParts) throws RemoteException {
       subParts.clear();
       return subParts;
    }

    @Override
    public void addSubPart(List<Part> subParts, Part part) throws RemoteException {
        subParts.add(part);
    }

    @Override
    public void showPart(Part part) throws RemoteException {
        System.out.println("Código part: " + part.getCodigo());
        System.out.println("Nome part: " + part.getNome());
        System.out.println("Descrição part: " + part.getDescricao());
        System.out.println("Subparts: " + part.getSubparts());
        System.out.println("--------------------------------------------------");
    }
}
