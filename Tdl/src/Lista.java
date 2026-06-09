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
        tarefas.add(t);
        System.out.println("tarefa adicionada com sucesso!");
    }

    public void listarTarefas(){
        System.out.println("LISTA DE TAREFAS: ");
       for(int i = 0; i < tarefas.size(); i++){
        System.out.printf("%dº %s;\n",i+1,tarefas.get(i).getTitulo());
       }
    }

    public void atualizar(int id, String campo, String novoValor) {
        
    }

    public void removerTarefa(int indice) {
        if(indice >= 0 && tarefas.size() > indice){
            tarefas.remove(indice);

            System.out.println("tarefa removida com sucesso!");
        }else{
            System.out.println("indice invalido!");
        } 
    }

    public ArrayList<Tarefas> obterTarefas() {
        return tarefas;
    }

}