import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ent = new Scanner(System.in);
        Lista list = new Lista();
        int op;
          
        do{
            System.out.println("====== MENU TO-DO-LIST ======");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Ver Tarefas");
            System.out.println("0. Sair");
            System.out.print("Selecione a opção: ");
            op = ent.nextInt();
            ent.nextLine();
            switch (op) {
                 case 1:
                    /*System.out.println("ID da tarefa: ");
                    int id = ent.nextInt();
                    ent.nextLine();*/  
                    int id=0;
                    System.out.print("Descrição da tarefa: ");
                    String descricao = ent.nextLine();
                    System.out.print("Data de vencimento (dd/mm/aaaa): ");
                    String data = ent.nextLine();
                    System.out.print("Prioridade (baixa/média/alta): ");
                    String prioridade = ent.nextLine();
                    Tarefas task = new Tarefas(id, descricao, data, prioridade);
                    list.adicionarTarefa(task);
                 break;
                 case 2:
                    System.out.println("A ver tarefas...");
                    list.listarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while (op!=0); 


    }
}
 