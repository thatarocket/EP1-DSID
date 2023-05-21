
import Global.IPartRepository;
import Global.PartRepository;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Servidor {
    static Scanner scanner = new Scanner (System.in);
    public static void main(String[] args) {
        try {
            IPartRepository part = new PartRepository();
            System.out.println("Digite o nome do servidor: ");
            String nomeServ = scanner.nextLine();
           
            System.out.println("Digite o numero da porta: ");
            int numPorta = Integer.parseInt(scanner.nextLine());
            
            LocateRegistry.createRegistry(numPorta);
            
            String objName = "rmi://localhost:" + numPorta + "/" + nomeServ;

            Naming.rebind(objName, part);

            System.out.println("Aguardando clientes...");
        }
        catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}

