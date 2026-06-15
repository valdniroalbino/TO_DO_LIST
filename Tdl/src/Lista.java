import java.util.ArrayList;

public class Lista {
    
    private ArrayList<Tarefas> tarefas;

    public Lista(){
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefas t){
        if(t == null){
            System.out.println("tarefa invalida!");
            return;
        }
        int newid = tarefas.size() + 1;
        Tarefas te = new Tarefas(newid, t.getTitulo(), t.getData(), t.getPrioridade());
        tarefas.add(te);
        System.out.println("tarefa adicionada com sucesso!");
    }

    public void listarTarefas(){
        System.out.println("LISTA DE TAREFAS: ");
       for(int i = 0; i < tarefas.size(); i++){
        System.out.printf("%dº %s; | status: %s\n",i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getStatus());
       }
    }

    public void atualizar(int id, String campo, String novoValor) {
        
    }
    
    public void atualizarIds(){
        for(int i=0; i < tarefas.size(); i++){
            tarefas.get(i).setId(i + 1);
        }
    }

    public void removerTarefa(int indice) {
        if(indice >= 0 && tarefas.size() > indice){
            tarefas.remove(indice);
            atualizarIds();
            System.out.println("tarefa removida com sucesso!");
        }else{
            System.out.println("indice invalido!");
        } 
    }

    public ArrayList<Tarefas> obterTarefas() {
        return tarefas;
    }

    public void marcarConcluida(int id){
        if(id>=0 && id < tarefas.size()){
            tarefas.get(id).setStatus("CONCLUÍDA");
            System.out.println("Tarefa concluída!");
        }else{
            System.out.println("ID inexistente!");
        }
    }
    
    public void filtrarConcluidas(){
        System.out.println("=== Tarefas Concluídas ===");
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().compareToIgnoreCase("CONCLUÍDA")==1){
                System.out.println(i+" - "+tarefas.get(i).getTitulo());
            }
        }
    }

    public void filtrarPendentes(){
        System.out.println("=== Tarefas Concluídas ===");
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().compareToIgnoreCase("PENDENTE")==1){
                System.out.println(i+" - "+tarefas.get(i).getTitulo());
            }
        }
    }

}