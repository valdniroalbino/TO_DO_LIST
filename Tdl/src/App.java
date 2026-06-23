import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ent = new Scanner(System.in);
        Lista list = new Lista();
        int op;

          
        do{
            System.out.println("================== MENU TO-DO-LIST ==================");
            System.out.println();
            System.out.println("              📝 1. Adicionar Tarefa");
            System.out.println("              📄 2. Ver Tarefas");
            System.out.println("              ❌ 3. Remover Tarefa");
            System.out.println("              ✅ 4. Marcar Tarefa Concluída");
            System.out.println("              ⏳ 5. Filtrar Pendentes"); //dentro do ver
            System.out.println("              🏆 6. Filtrar Concluídas"); //dentro do ver
            System.out.println("              ✏️  7. Atualizar Tarefa");
            System.out.println("              🔍 8. Procurar Tarefa");
            System.out.println("              🚪 0. Sair");
            System.out.println();
            System.out.println("===============================================");
             System.out.print("               Selecione a opção: ");
              op = ent.nextInt();
            ent.nextLine();
            System.out.println("===============================================");
         
            switch (op) {
                 case 1:
                  String titulo;
                   while(true){

                System.out.print("Título: ");
                    titulo = ent.nextLine();

                    if(list.validarTitulo(titulo)){
                        break;
                    }

}
                     System.out.println("===============================================");
                     String data;

                  while(true){

                    System.out.print("Data (dd/mm/aaaa): ");
                   data = ent.nextLine();

                    if(list.validarData(data)){
                        break;
                    }

}
int opCategoria;

while (true) {

    System.out.println("===============================================");
    System.out.println("Categoria da tarefa:");
    System.out.println("===============================================");
    System.out.println("                    1. Casa");
    System.out.println("                    2. Estudos");
    System.out.println("                    3. Trabalho");
    System.out.println("                    4. Outros");
    System.out.println("===============================================");
    System.out.print("Escolher opção: ");

    try {
        opCategoria = Integer.parseInt(ent.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("⚠️ Entrada inválida! Digite um número entre 1 e 4.");
        continue;
    }

    if (opCategoria < 1 || opCategoria > 4) {
        System.out.println("⚠️ Opção inválida! Escolha entre 1 e 4.");
        continue;
    }

    break;
}

String categoria;

switch (opCategoria) {

    case 1:
        categoria = "Casa";
        break;

    case 2:
        categoria = "Estudos";
        break;

    case 3:
        categoria = "Trabalho";
        break;

    case 4:
        categoria = "Outros";
        break;

    default:
        categoria = ""; // nunca chega aqui, mas evita warning
}

    String prioridade;

    while(true){
        System.out.println("===============================================");
        System.out.print("Prioridade (baixa/media/alta): ");
        prioridade = ent.nextLine();

        if(list.validarPrioridade(prioridade)){
            break;
        }
    }

                    System.out.println("===============================================");
                    list.adicionarTarefa(titulo, data, categoria, prioridade);

                 break;

                 case 2:
                    System.out.println("A ver tarefas...");
                    list.listarTarefas();
                    break;
                 
                case 3:
                 
                    System.out.print("Índice da tarefa a remover: ");
                    int indiceRemover = ent.nextInt();
                    ent.nextLine();
            
                    list.removerTarefa(indiceRemover);
                    break;

                case 4:
                    System.out.print("Índice da tarefa a concluir: ");
                    int indiceConcluir = ent.nextInt();
                    ent.nextLine();

                    list.marcarConcluida(indiceConcluir);
                    break;

                case 5:
                    list.filtrarPendentes();
                    break;

                case 6:
                    list.filtrarConcluidas();
                    break;

                case 7:
                    System.out.print("Índice da tarefa: ");
                    int indiceAtualizar = ent.nextInt();
                    ent.nextLine();
    System.out.println("===============================================");
                    System.out.print("Campo a atualizar: ");
                    String campo = ent.nextLine();
    System.out.println("===============================================");
                    System.out.print("Novo valor: ");
                    String novoValor = ent.nextLine();

                    list.atualizar(indiceAtualizar, campo, novoValor);
                    break;

                    case 8:

                     
                        System.out.print("Digite o título da tarefa: ");
                        String tituloPesquisa = ent.nextLine();

                        list.procurarTarefa(tituloPesquisa);

                        break;

                case 0:
                    System.out.println("Saindo...🏃");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
            
        }while (op!=0); 
        
        


    }
}
