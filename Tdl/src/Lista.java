import java.util.ArrayList;

public class Lista {
    
    private ArrayList<Tarefas> tarefas;

    public Lista(){
        this.tarefas = new ArrayList<>();
    }

    public void inserir(Tarefas tarefas){
        
    }

    public void ver(){
       
    }

    public void atualizar(int id, String campo, String novoValor) {
        
    }

    public void remover(int id) {
        
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

}