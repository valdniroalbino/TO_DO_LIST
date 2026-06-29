import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner ent = new Scanner(System.in);
        Lista list = new Lista();

        int op;


        do {

            System.out.println("\n================ MENU TO-DO-LIST ================");
            System.out.println();
            System.out.println("              📝 1. Adicionar Tarefa");
            System.out.println("              📄 2. Ver Tarefas");
            System.out.println("              ❌ 3. Remover Tarefa");
            System.out.println("              ✅ 4. Marcar Tarefa Concluída");
            System.out.println("              ✏️  5. Atualizar Tarefa");
            System.out.println("              🚪 0. Sair");
            System.out.println();
            System.out.println("==================================================");

            System.out.print("Escolha uma opção: ");
            op = ent.nextInt();
            ent.nextLine();



            switch(op){


                // ==================================
                // ADICIONAR TAREFA
                // ==================================

                case 1:

                    String titulo;

                    while(true){

                        System.out.print("Título: ");
                        titulo = ent.nextLine();

                        if(list.validarTitulo(titulo)){
                            break;
                        }
                    }



                    String data;

                    while(true){

                        System.out.print("Data (dd/mm/aaaa): ");
                        data = ent.nextLine();

                        if(list.validarData(data)){
                            break;
                        }
                    }



                    int opCategoria;


                    while(true){

                        System.out.println("\n=========== CATEGORIA ===========");
                        System.out.println("1. Casa");
                        System.out.println("2. Estudos");
                        System.out.println("3. Trabalho");
                        System.out.println("4. Outros");


                        System.out.print("Escolha: ");


                        try{

                            opCategoria = Integer.parseInt(ent.nextLine());

                        }catch(Exception e){

                            System.out.println("Opção inválida!");
                            continue;
                        }


                        if(opCategoria >=1 && opCategoria <=4){
                            break;
                        }

                        System.out.println("Escolha entre 1 e 4.");
                    }



                    String categoria;


                    switch(opCategoria){

                        case 1:
                            categoria = "Casa";
                            break;

                        case 2:
                            categoria = "Estudos";
                            break;

                        case 3:
                            categoria = "Trabalho";
                            break;

                        default:
                            categoria = "Outros";
                    }




                    String prioridade;


                    while(true){

                        System.out.print("Prioridade (baixa/media/alta): ");

                        prioridade = ent.nextLine();


                        if(list.validarPrioridade(prioridade)){
                            break;
                        }

                    }


                    list.adicionarTarefa(
                            titulo,
                            data,
                            categoria,
                            prioridade
                    );


                    break;




                // Menu Para ver Tarefas

                case 2:


                    int opcaoVer;


                    do{


                        System.out.println("\n=============== VER TAREFAS ===============");
                        System.out.println();
                        System.out.println("              1. Ver todas");
                        System.out.println("              2. Ver pendentes");
                        System.out.println("              3. Ver concluídas");
                        System.out.println("              4. Procurar tarefa");
                        System.out.println("              5. Ver por categoria");
                        System.out.println("              6. Ver por prioridade");
                        System.out.println("              7. Ordenar tarefas");
                        System.out.println("              8. Ver tarefas em atraso");
                        System.out.println("              0. Voltar");
                        System.out.println();
                        System.out.println("==================================================");
                        System.out.print("Escolha: ");


                        opcaoVer = ent.nextInt();
                        ent.nextLine();



                        switch(opcaoVer){


                            case 1:

                                list.listarTarefas();

                                break;



                            case 2:

                                list.filtrarPendentes();

                                break;



                            case 3:

                                list.filtrarConcluidas();

                                break;



                            case 4:


                                System.out.print("Título da tarefa: ");

                                String pesquisa = ent.nextLine();


                                list.procurarTarefa(pesquisa);


                                break;




                            case 5:

                                System.out.println("========= Escolha a categoria ==========");
                                System.out.println();
                                System.out.println("              Categoria:");
                                System.out.println("                 Casa");
                                System.out.println("               Estudos");
                                System.out.println("               Trabalho");
                                System.out.println("                Outros");
                                System.out.println();
                                System.out.println("==================================================");
                                System.out.print("Digite: ");

                                String cat = ent.nextLine();


                                list.listarPorCategoria(cat);


                                break;



                            case 6:


                                System.out.print(
                                "Prioridade (baixa/media/alta): "
                                );


                                String pri = ent.nextLine();


                                list.filtrarPorPrioridade(pri);


                                break;




                            case 7:


                                int ordenar;


                                do{


                                    System.out.println("\n========== Ordenar ==========");
                                    System.out.println();
                                    System.out.println("        1. Por prioridade");
                                    System.out.println("        2. Por data");
                                    System.out.println("        0. Voltar");
                                    System.out.println();
                                    System.out.println("==================================================");

                                    System.out.print("Escolha: ");


                                    ordenar = ent.nextInt();
                                    ent.nextLine();



                                    switch(ordenar){


                                        case 1:

                                            list.ordenarPorPrioridade();

                                            break;



                                        case 2:

                                            list.ordenarPorData();

                                            break;


                                        case 0:

                                            break;



                                        default:

                                            System.out.println("Opção inválida!");

                                    }


                                }while(ordenar !=0);



                                break;




                            case 8:


                                list.tarefasEmAtraso();


                                break;



                            case 0:


                                System.out.println("Voltando...");

                                break;



                            default:

                                System.out.println("Opção inválida!");

                        }


                    }while(opcaoVer !=0);



                    break;





                // Remover tarefas
               

                case 3:



                    int remover;


                    do{


                        System.out.println("\n============== Remover ==============");
                        System.out.println();
                        System.out.println("       1. Remover uma tarefa");
                        System.out.println("       2. Limpar concluídas");
                        System.out.println("       0. Voltar");
                        System.out.println();
                    System.out.println("==================================================");

                        System.out.print("Escolha: ");


                        remover = ent.nextInt();
                        ent.nextLine();



                        switch(remover){


                            case 1:


                                System.out.print("ID da tarefa: ");

                                int id = ent.nextInt();

                                ent.nextLine();


                                list.removerTarefa(id);


                                break;



                            case 2:


                                list.limparConcluidas();


                                break;



                            case 0:

                                break;



                            default:

                                System.out.println("Opção inválida!");

                        }



                    }while(remover !=0);



                    break;




                // Concluir tarefa

                case 4:


                    System.out.print("ID da tarefa concluída: ");

                    int idConcluir = ent.nextInt();

                    ent.nextLine();


                    list.marcarConcluida(idConcluir);


                    break;




                // Actualizar

                case 5:


                    System.out.print("ID da tarefa: ");

                    int idAtualizar = ent.nextInt();

                    ent.nextLine();



                    System.out.print("Campo (titulo/data/prioridade): ");

                    String campo = ent.nextLine();



                    System.out.print("Novo valor: ");

                    String valor = ent.nextLine();



                    list.atualizar(
                            idAtualizar,
                            campo,
                            valor
                    );


                    break;




                case 0:


                    System.out.println("Saindo... 🏃");

                    break;



                default:

                    System.out.println("Opção inválida!");

            }



        }while(op !=0);



        ent.close();

    }

}