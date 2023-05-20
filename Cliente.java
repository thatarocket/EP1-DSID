import java.rmi.Naming;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Global.IPartRepository;
import Global.Part;

public class Cliente {

    static Scanner scanner = new Scanner(System.in);
    static ContextoAtual contextoAtual;
    public static void main(String[] args) throws Exception{
        try {
            contextoAtual = new ContextoAtual();
            contextoAtual.subtAtual = new HashMap<Part,Integer>();
            opcoes();
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void opcoes() throws Exception{
        System.out.println("--------------------------------------------------");
        System.out.println("Escolha a opção desejada! (Pelo número)");
        System.out.println("1 - bind");
        System.out.println("2 - listp");
        System.out.println("3 - getp");
        System.out.println("4 - showp");
        System.out.println("5 - clearlist");
        System.out.println("6 - addsubpart");
        System.out.println("7 - addp");
        System.out.println("8 - showsub");
        System.out.println("9 - unbind");
        System.out.println("10 - quit");
        select();
    }

    public static void select() throws Exception {
        int input = Integer.parseInt(scanner.nextLine());
        
        switch(input) {
            case 1:
                conectar();
                break;
            case 2:
                listP();
                break;
            case 3:
                getP();
                break;
            case 4:
                showP();
                break;
            case 5:
                clearList();
                break;
            case 6:
                addSubPart();
                break;
            case 7:
                addP();
                break;
            case 8:
                showSub();
                break;
            case 9:
                desconectar();
                break;
            case 10:
                quit();
            default:
                System.out.println("Opção inválida!");
                select();
                break;           
        }
    }

    public static void conectar() throws Exception{
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome do repositorio: ");
            String nomeRep = scanner.nextLine();

            String objName = "rmi://localhost:1099/" + nomeRep;
            IPartRepository part = (IPartRepository) Naming.lookup(objName);
            contextoAtual.repAtual = part;
            contextoAtual.nomeRepAtual = nomeRep;

            System.out.println("Conectado com sucesso!");
            opcoes();
        }
        catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Nome incorreto ou servidor não encontrado!");
            opcoes();
        }
    }

    public static void desconectar() throws Exception {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Desconectando do repositório " + contextoAtual.nomeRepAtual);
            String objName = "rmi://localhost:1099/" + contextoAtual.nomeRepAtual;
            Naming.unbind(objName);

            contextoAtual.repAtual = null;
            contextoAtual.nomeRepAtual = null;
            
            System.out.println("Desconectado com sucesso!");
            opcoes();
        }
        catch(Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            opcoes();
        }
    }

    public static void listP() throws Exception{
        try {
            System.out.println("--------------------------------------------------");
            List<Part> result = contextoAtual.repAtual.listP(); 
            if(result == null) {
                System.out.println("Não há nenhuma part no repositório corrente " + contextoAtual.nomeRepAtual + "!");
            }
            else {
                System.out.println("Listando as parts do repositório corrente: " + contextoAtual.nomeRepAtual);
                for(Part part : result) {
                    System.out.println(part);
                }
            }
            opcoes();
        }
        catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            opcoes();
        }
    }

    public static void getP() throws Exception {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Repositório corrente: " + contextoAtual.nomeRepAtual);
            System.out.println("Digite o código da peça a ser buscada: ");
            String codigo = scanner.nextLine();
            int cod = Integer.parseInt(codigo);                        
            Part alteracao = contextoAtual.repAtual.getP(cod);
            
            if(alteracao != null) {
                System.out.println("Part encontrada!");
                contextoAtual.partAtual = alteracao;
                System.out.println("o Part atual foi atualizada!");
            }
            else {
                System.out.println("Part não encontrada!");
            }
            opcoes();
        }
        catch(Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            opcoes();
        }
    }

    public static void showP() throws Exception {
        System.out.println("--------------------------------------------------");
        if(contextoAtual.partAtual != null) {
            System.out.println(contextoAtual.partAtual);
        }
        else {
            System.out.println("Não há part");
        }
        opcoes();
    }

    public static void showSub() throws Exception {
        System.out.println("--------------------------------------------------");
        if(contextoAtual.subtAtual.size() > 0) {
            StringBuilder subparts = new StringBuilder();
            contextoAtual.subtAtual.forEach((part, quant) -> {
                subparts.append(String.format(
                        "\tCodigo: %s; Nome: %s; Quant: %s",
                    part.getCodigo(),
                    part.getNome(),
                    quant
                ));
            });            
            System.out.println("Subparts: ");
            System.out.println(subparts);
        }
        else {
            System.out.println("Não há subparts");
        }
        opcoes();
    }

    public static void clearList() throws Exception {
        System.out.println("--------------------------------------------------");
        contextoAtual.subtAtual.clear();
        System.out.println("Subparts apagadas com sucesso!");
        opcoes();
    }

    public static void addSubPart() throws Exception {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome da peça a ser adicionada na subpeça: ");
            String nome = scanner.nextLine();
            System.out.println("Digite a descrição da peça a ser adicionada: ");
            String descricao = scanner.nextLine();
            System.out.println("Digite a quantidade da peça a ser adicionada: ");
            int quant = Integer.parseInt(scanner.nextLine());

            Part p = new Part(nome, descricao);
            contextoAtual.partAtual = p;
            contextoAtual.subtAtual.put(p,quant);

            System.out.println("Subpart adicionada com sucesso!");
            opcoes();
        }
        catch(Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            opcoes();
        }
    }

    public static void addP() throws Exception {
        System.out.println("--------------------------------------------------");
        System.out.println("Repositório corrente: " + contextoAtual.nomeRepAtual);
        System.out.println("Digite o nome da peça a ser adicionada: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a descrição da peça a ser adicionada: ");
        String descricao = scanner.nextLine();

        Part p = new Part(nome, descricao);        
        contextoAtual.partAtual = p;
        contextoAtual.partAtual.setSubparts(contextoAtual.subtAtual);
        contextoAtual.repAtual.addP(contextoAtual.partAtual);

        System.out.println("Part adicionada com sucesso!");
        opcoes();
    }

    public static void quit() throws Exception {
        System.out.println("Saindo do programa...");
        System.exit(0);
    }

}

