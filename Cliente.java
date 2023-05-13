import java.rmi.Naming;

import Global.IPartRepository;

public class Cliente {
    public static void main(String[] args) throws Exception{
        try {
            String objName = "rmi://localhost:1099/PartRepository";
            IPartRepository part = (IPartRepository) Naming.lookup(objName);
            System.out.println(part.funcionando());
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
