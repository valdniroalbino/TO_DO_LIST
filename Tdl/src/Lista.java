import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

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
        if(id >= 1 && id <= tarefas.size()){
            Tarefas trf = tarefas.get(id-1);

            if(campo.equalsIgnoreCase("titulo")){
                trf.setTitulo(novoValor);
                System.out.println("titulo atualizado com sucesso!");
            }else if(campo.equalsIgnoreCase("data")){
                trf.setData(novoValor);
                System.out.println("data atualizada com succesoi!");
            }else if(campo.equalsIgnoreCase("prioridade")){
                trf.setPrioridade(novoValor);
                System.out.println("prioridade ataulizada com sucesso!");
            }else{
                System.out.println("campo invalide! use: titulo, data ou prioridade");
            }
             
        }else{
            System.out.println("id invalido!");
        }
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
  
    public void guardarTarefas(){
        try{
            PrintWriter writer = new PrintWriter("tarefas.txt");
            for(int i = 0; i < tarefas.size(); i++){
                Tarefas trf = tarefas.get(i);
                writer.println(trf.getId() + ";" + trf.getTitulo() + ";" + trf.getData() + ";" + trf.getPrioridade() + ";" + trf.getStatus());

            }
            writer.close();
            System.out.println("tarefas guardadas com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao guardar a lista de tarefas.");
        }
    }

    public void carregarTarefas(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"));
            String linha = reader.readLine();
            while(linha != null){
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]); // veio como String, converto para int pq as outras funcoes esperam por um int
                String titulo = partes[1];
                String data = partes[2];
                String prioridade = partes[3];
                String estado = partes[4];
                Tarefas trf = new Tarefas(id,titulo,data,prioridade);
                trf.setStatus(estado);
                tarefas.add(trf);
                linha = reader.readLine();

            }  
            reader.close();
            System.out.println("bem-vindo de volta! Tarefas carregas.");

        }catch(IOException e){
            System.out.println("nenhuma tarefa guardada encontrada");
        }
    }

    public void contadorDeTarefas(){
        int ctdConcluidas = 0;
        int ctdPendentes = 0;

        for(Tarefas t : tarefas){
            if(t.getStatus().equalsIgnoreCase("pendente")){
                ctdPendentes++;
            }else{
                ctdConcluidas++;
            }
        }

        System.out.println("=== TAREFAS ===");
        System.out.println("total: "+tarefas.size());
        System.out.println("concluidas: "+ctdConcluidas);
        System.out.println("pendentes: "+ctdPendentes);
    }

    public void percentagem(){
        if(tarefas.isEmpty()){
            System.out.println("0% de tarefas concluidas");
            return;
        }
        int ctd = 0;
        for(Tarefas t : tarefas){
            if(t.getStatus().equalsIgnoreCase("concluida"))
                ctd++;
        }
        int result = (ctd * 100) / tarefas.size();

        System.out.println("foi concluido "+result+"% das tarefas");

    }

    public void filtrarPorPrioridade(String prioridade){
        for(int i= 0; i < tarefas.size() ; i++){
            if(tarefas.get(i).getPrioridade().equalsIgnoreCase(prioridade)){
                System.out.println((i+1)+ "º "+tarefas.get(i).getTitulo()+";");
            }
        }
    } 

    public void limparConcluidas(){
        for(int i = tarefas.size() -1; i >= 0 ; i--){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("concluida"))
                tarefas.remove(i);
        }
        atualizarIds();
        System.out.println("limpeza feita com sucesso!");
    }

    public void ordenarPorPrioridade(){
        System.out.println("=== ALTA ===");
        filtrarPorPrioridade("alta");
        System.out.println("=== MEDIA ===");
        filtrarPorPrioridade("media");
        System.out.println("=== BAIXA ===");
        filtrarPorPrioridade("baixa");
    }

    public void ordenarPorData(){
        for(int i= 0; i < tarefas.size() - 1; i++){
            for(int j=0; j<tarefas.size()-1-i;j++){

                String data1 = tarefas.get(j).getData();
                String data2 = tarefas.get(j+1).getData();

                String[] partes1 = data1.split("/");
                String[] partes2 = data2.split("/");
                String dataFormatada1 = partes1[2] + "/" + partes1[1] + "/" + partes1[0];
                String dataFormatada2 = partes2[2] + "/" + partes2[1] + "/" + partes2[0];

                if(dataFormatada1.compareTo(dataFormatada2 ) > 0){
                    Tarefas temp = tarefas.get(j);
                    tarefas.set(j,tarefas.get(j+1));
                    tarefas.set(j+1, temp);
                }
            }
        }

        System.out.println("tarefas ordenas por data com sucesso!");
        listarTarefas();
    }

    public void tarefasEmAtraso(){
        LocalDate hoje = LocalDate.now();

        System.out.println("=== TAREFAS EM ATRASO ===");

        for(int i = 0; i < tarefas.size(); i++){
            String data1 = tarefas.get(i).getData();
                
            String[] partes1 = data1.split("/");
            //String dataFormatada1 = partes1[2] + "/" + partes1[1] + "/" + partes1[0];
            String aux1 = partes1[0];
            String aux2 = partes1[1];
            String aux3 = partes1[2];

            int dia = Integer.parseInt(aux1);
            int mes = Integer.parseInt(aux2);
            int ano = Integer.parseInt(aux3);

            LocalDate dataTarefa = LocalDate.of(ano,mes,dia);

            if(dataTarefa.isBefore(hoje) && tarefas.get(i).getStatus().equalsIgnoreCase("pendente")){
                System.out.println((i+1)+"ª "+tarefas.get(i).getTitulo()+";");
            }
            }
        
    }
    

}