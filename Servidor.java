
import Global.IPartRepository;
import Global.PartRepository;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class Servidor {
    public static void main(String[] args) {
        try {
            IPartRepository part = new PartRepository();
            String objName = "rmi://localhost/PartRepository";
           
            LocateRegistry.createRegistry(1099);
            Naming.rebind(objName, part);

            System.out.println("Aguardando clientes...");
        }
        catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}

