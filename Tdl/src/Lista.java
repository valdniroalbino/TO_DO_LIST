import java.util.ArrayList;

public class Lista {
    
    private ArrayList<Tarefas> tarefas;

    public Lista(){
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String titulo, String data, String prioridade){
        if(titulo == null || titulo.isEmpty()){
            System.out.println("tarefa invalida!");
            return;
        }
        int novoId = tarefas.size() + 1;
        Tarefas trf = new Tarefas(novoId,titulo,data,prioridade);
        tarefas.add(trf);
        System.out.println("tarefa adicionada com sucesso!");
    }

    public void listarTarefas(){
        System.out.println("LISTA DE TAREFAS: ");
       for(int i = 0; i < tarefas.size(); i++){
        System.out.printf("%dº %s; | estado: %s\n",i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getStatus());
       }
    }

    public void atualizar(int id, String campo, String novoValor) {
        
    }
    
    public void atualizarIds(){
        for(int i=0; i < tarefas.size(); i++){
            tarefas.get(i).setId(i + 1);
        }
    }

    public void removerTarefa(int id) { //wis, o nome id vem pq é o que o usuario irá ver aao selecionar a tarefa que deseja remover. 
        if(id >= 0 && tarefas.size() > id){
            tarefas.remove(id-1); // -1 pq isso é arraylist começa do 0
            atualizarIds();
            System.out.println("tarefa removida com sucesso!");
        }else{
            System.out.println("id invalido!");
        } 
    }

    public ArrayList<Tarefas> obterTarefas() {
        return tarefas;
    }

    public void marcarConcluida(int id){
        if(id>=1 && id <= tarefas.size()){
            tarefas.get(id-1).setStatus("CONCLUÍDA");
            System.out.println("Tarefa concluída!");
        }else{
            System.out.println("ID inexistente!");
        }
    }
    
    public void filtrarConcluidas(){
        System.out.println("=== Tarefas Concluídas ===");
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("CONCLUÍDA")){
                System.out.println((i+1)+" - "+tarefas.get(i).getTitulo());
            }
        }
    }

    public void filtrarPendentes(){
        System.out.println("=== Tarefas Pendentes ===");
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("PENDENTE")){
                System.out.println((i+1)+" - "+tarefas.get(i).getTitulo());
            }
        }
    }

}