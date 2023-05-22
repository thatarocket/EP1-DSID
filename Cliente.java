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

            conectar(); 
            if(contextoAtual.partAtual != null) opcoes();
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void opcoes() throws Exception{
        System.out.println();
        System.out.println("Repositorio corrente: " + contextoAtual.nomeRepAtual);
        if(contextoAtual.partAtual != null) System.out.println("Peca corrente: "+ contextoAtual.partAtual.getNome());

        System.out.println("--------------------------------------------------");
        System.out.println("Escolha a opcao desejada! (Pelo numero)");
        System.out.println("1 - bind");        // Faz o cliente se conectar a outro servidor e muda o repositorio corrente
        System.out.println("2 - listp");       //lista as pecas do repositorio corrente.
        System.out.println("3 - infoRep");     // Informacoes do repositorio
        System.out.println("4 - getp");        // Busca uma peca por codigo
        System.out.println("5 - showp");       //  Mostra atributos da peca corrente.
        System.out.println("6 - clearlist");   //  Esvazia a lista de sub-pecas corrente
        System.out.println("7 - addsubpart");  // Adiciona a lista de sub-pecas corrente n unidades da peca corrente
        System.out.println("8 - addp");        // Adiciona uma peca ao reposit´orio corrente.
        System.out.println("9 - qtdsub");
        System.out.println("10 - showsub");     // Mostra as subpartes
        System.out.println("11 - unbind");     // desconectar do repositorio
        System.out.println("12 - quit");       // Encerra a execucao do cliente
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
                infoRep();
                break;
            case 4:
                getP();
                break;
            case 5:
                showP();
                break;
            case 6:
                clearList();
                break;
            case 7:
                addSubPart();
                break;
            case 8:
                addP();
                break;
            case 9:
                qtdSub();
                break;
            case 10:
                showSub();
                break;
            case 11:
                desconectar();
                break;
            case 12:
                quit();
            default:
                System.out.println("Opcao invalida! Coloque uma opcao valida:");
                select();
                break;           
        }
    }

    public static void conectar() throws Exception{
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome do repositorio: ");
            String nomeRep = scanner.nextLine();

            System.out.println("Digite o numero da porta: ");
            int numPorta = Integer.parseInt(scanner.nextLine());
            
            String objName = "rmi://localhost:" + numPorta + "/" + nomeRep;

            IPartRepository part = (IPartRepository) Naming.lookup(objName);
            contextoAtual.repAtual = part;
            contextoAtual.nomeRepAtual = nomeRep;
            contextoAtual.portaAtual = numPorta;

            System.out.println("Conectado com sucesso!");
            opcoes();
        }
        catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Nome incorreto ou servidor nao encontrado!");
            System.out.println(contextoAtual.partAtual);
            if(contextoAtual.partAtual == null) conectar();
            else opcoes();
        }
    }

    public static void desconectar() throws Exception {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("Desconectando do repositorio " + contextoAtual.nomeRepAtual);
            String objName = "rmi://localhost:"+ contextoAtual.portaAtual +"/" + contextoAtual.nomeRepAtual;
            Naming.unbind(objName);

            contextoAtual.repAtual = null;
            contextoAtual.nomeRepAtual = null;
            
            System.out.println("Desconectado com sucesso!");
            conectar();
        }
        catch(Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Erro: " + e.getMessage());
            opcoes();
        }
    }

    public static void infoRep() throws Exception{
        try {
            System.out.println("--------------------------------------------------");
            List<Part> result = contextoAtual.repAtual.listP(); 
            System.out.println("Repositorio atual: " + contextoAtual.nomeRepAtual);
            
            if(result == null) {
                System.out.println("Quantidade de parts do repositorio corrente: 0");
            }
            else {
                int qtd = 0;
                for(Part part : result) {
                    qtd++;
                }

                System.out.println("Quantidade de parts do repositorio corrente: " + qtd);

            }
            opcoes();
        }
        catch (Exception e) {
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
                System.out.println("Nao ha nenhuma part no repositorio corrente " + contextoAtual.nomeRepAtual + "!");
            }
            else {
                System.out.println("Listando as parts do repositorio corrente: " + contextoAtual.nomeRepAtual);
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
            System.out.println("Repositorio corrente: " + contextoAtual.nomeRepAtual);
            System.out.println("Digite o codigo da peca a ser buscada: ");
            String codigo = scanner.nextLine();
            int cod = Integer.parseInt(codigo);                        
            Part alteracao = contextoAtual.repAtual.getP(cod);
            
            if(alteracao != null) {
                System.out.println("Part encontrada!");
                contextoAtual.partAtual = alteracao;
                System.out.println("o Part atual foi atualizada!");
            }
            else {
                System.out.println("Part nao encontrada!");
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
            System.out.println("Nao ha part");
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
            System.out.println("Nao ha subparts");
        }
        opcoes();
    }

    public static void qtdSub() throws Exception{
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
            System.out.println("Digite o nome da peca a ser adicionada na subpeca: ");
            String nome = scanner.nextLine();
            System.out.println("Digite a descricao da peca a ser adicionada: ");
            String descricao = scanner.nextLine();
            System.out.println("Digite a quantidade da peca a ser adicionada: ");
            int quant = Integer.parseInt(scanner.nextLine());

            Part p = new Part(nome, descricao);
            //contextoAtual.partAtual = p;
            contextoAtual.subtAtual.put(p,quant);
            //contextoAtual.partAtual.setSubparts(contextoAtual.subtAtual);
            //contextoAtual.repAtual.addP(p);

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
        System.out.println("Repositorio corrente: " + contextoAtual.nomeRepAtual);
        System.out.println("Digite o nome da peca a ser adicionada: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a descricao da peca a ser adicionada: ");
        String descricao = scanner.nextLine();

        Part p = new Part(nome, descricao);        
        contextoAtual.partAtual = p;
        contextoAtual.partAtual.setSubparts(contextoAtual.subtAtual);
        contextoAtual.repAtual.addP(contextoAtual.partAtual);

        p.setSubparts(contextoAtual.subtAtual);         // adiciona a lista de sub-pecas corrente na lista de subcomponentes diretos da peca

        System.out.println("Part adicionada com sucesso!");
        opcoes();
    }

    public static void quit() throws Exception {
        System.out.println("Saindo do programa...");
        System.exit(0);
    }

}

