import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/*<<<<<<< HEAD
import java.util.Collections;
import java.util.Comparator;
=======
import java.time.LocalDate;
>>>>>>> ce5bce0e7eb5babc6a348144aa4739ac99453d1a*/

public class Lista {
    
    private ArrayList<Tarefas> tarefas; // para armazar a lista de tarefas. 

    public Lista(){
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String titulo, String data, String categoria, String prioridade){
        if(titulo == null || titulo.isEmpty()){
            System.out.println("tarefa invalida!");
            return;
        }

        String[] dataVeri = data.split("/");
        if(dataVeri.length != 3){
            System.out.println("data invalida! use o formato dd/mm/aaaa");
            return;
        }

        String dia = dataVeri[0];
        String mes = dataVeri[1];
        String ano = dataVeri[2];

        //LocalDate hoje = LocalDate.now();
        

        if(dia.length() != 2 || mes.length() != 2 || ano.length() != 4){
            if((Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31  ) && (Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12) && (Integer.parseInt(ano) < 2026) )
                System.out.println("data invalida!");
             return;
        }

        if(!prioridade.equalsIgnoreCase("baixa") && !prioridade.equalsIgnoreCase("media") && !prioridade.equalsIgnoreCase("alta")){
            System.out.println("prioridade invalida!use apenas: baixa, media ou alta");
            return;
        }

        int novoId = tarefas.size() + 1;  // o id é gerado, ou seja, aqui o usuario nao interfere. 
        Tarefas trf = new Tarefas(novoId,titulo,data,categoria,prioridade);
        tarefas.add(trf);
        System.out.println("tarefa adicionada com sucesso!");
    }

    public void listarTarefas(){
       // LocalDateTime hoje = LocalDateTime.now();
        System.out.println("LISTA DE TAREFAS: ");
       for(int i = 0; i < tarefas.size(); i++){
       // LocalDateTime vencimento = tarefas.get(i).getData();
        System.out.printf("%dº %s; | estado: %s\n",i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getStatus());
        /*if(vencimento.isBefore(hoje))
            System.out.println("- ATRASADA");
        else if(vencimento.isEqual(hoje))
            System.out.println("- VENCE HOJE");
        else System.out.println("- NO PRAZO");*/
       }
    }//funcao para printar a lista de tarefas com tds os campos.

    
    // Atualiza um campo específico de uma tarefa existente. campo é o que queremos alterar e novoValor é a nova coisa que desejamos colocar. 
    public void atualizar(int id, String campo, String novoValor) {
        if(id >= 1 && id <= tarefas.size()){
            Tarefas trf = tarefas.get(id-1);

            if(campo.equalsIgnoreCase("titulo")){
                trf.setTitulo(novoValor);
                System.out.println("titulo atualizado com sucesso!");
            }else if(campo.equalsIgnoreCase("data")){
                trf.setData(novoValor);
                /*try{
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/aaaa HH:mm");
                    LocalDateTime.parse(novoValor, form);

                    trf.setData(novoValor);
                    System.out.println("data atualizada com succeso!");
                    }catch(Exception e){
                        System.out.println("Erro no formato! Use o formato dd/MM/aaaa HH:mm");
                    }*/
                
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
    
    //organiza novamente novamente os ids apos uma remocao para nao ter erros(lacunas)
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

    public void marcarPendente(int id){
         if(id>=1 && id <= tarefas.size()){
            tarefas.get(id-1).setStatus("PENDENTE");
            System.out.println("Tarefa pendente!");
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
/*<<<<<<< HEAD

    public void ordemDatas(){
        Collections.sort(tarefas, Comparator.comparing(Tarefas::getData));
        System.out.println("Tarefas ordenadas por data!");
    }

    public void listarAtrasadas(){
        LocalDateTime hoje = LocalDateTime.now();
        System.out.println("\n=== Tarefas Vencidas ===");
        for(Tarefas t : tarefas){
            if(t.getData().isBefore(hoje)){
                System.out.println(t.getId() + " - " + t.getTitulo());
            }
        }
    }

    public void listarVenceHoje(){
        LocalDateTime hoje = LocalDateTime.now();
        System.out.println("\n=== Vencem Hoje ===");
        for(Tarefas t : tarefas){
            if(t.getData().isEqual(hoje)){
                System.out.println(t.getId() + " - " + t.getTitulo());
            }
        }
    }

    public void listarNoPrazo(){
        LocalDateTime hoje = LocalDateTime.now();
        System.out.println("\n=== Vencem Hoje ===");
        for(Tarefas t : tarefas){
            if(t.getData().isAfter(hoje)){
                System.out.println(t.getId() + " - " + t.getTitulo());
            }
        }
    }

=======
    
    //Guarda todas as tarefas no ficheiro tarefas.txt.
    //Cada tarefa é guardada numa linha com os atributos separados por ponto e vírgula.
>>>>>>> ce5bce0e7eb5babc6a348144aa4739ac99453d1a*/
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

    //Carrega as tarefas do ficheiro tarefas.txt para a lista.
    //Cada linha é dividida pelo separador ";" para reconstruir os objetos Tarefas.
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
                String categoria = partes[4];
                String estado = partes[5];
                Tarefas trf = new Tarefas(id,titulo,data,categoria,prioridade);
                trf.setStatus(estado);
                tarefas.add(trf);
                linha = reader.readLine();

            }  
            reader.close();
            System.out.println("bem-vindo de volta! Tarefas carregas.");
            contadorDeTarefas();
            percentagem();


        }catch(IOException e){
            System.out.println("nenhuma tarefa guardada encontrada");
        }
    }

    // Mostra o total de tarefas, quantas estão concluídas e quantas estão pendentes
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

    // Calcula e mostra a percentagem de tarefas concluídas
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
        int percentagem = (ctd * 100) / tarefas.size();

        System.out.println("foi concluido "+percentagem+"% das tarefas");

        if(percentagem == 0){
            System.out.println("vamos comecar? Toda a grande conquista comeca com a priemira tarefa.");
        }else if(percentagem >0 && percentagem < 50){
            System.out.println("Estas no caminho certo. Continua, o progresso vem passo a passo.");
        }else if(percentagem > 49 && percentagem < 100){
            System.out.println("Grande trabalho! falta pouco para completares tudo.");
        }else if(percentagem == 100){
            System.out.println("Missao cumprida! Todas as tarefas foram concluidas.");
        }else{
            System.out.println("percentagem invalida!");
        }

    }

    // Filtra e mostra as tarefas com uma determinada prioridade
    public void filtrarPorPrioridade(String prioridade){
        for(int i= 0; i < tarefas.size() ; i++){
            if(tarefas.get(i).getPrioridade().equalsIgnoreCase(prioridade)){
                System.out.println((i+1)+ "º "+tarefas.get(i).getTitulo()+";");
            }
        }
    } 


    //Remove todas as tarefas concluídas da lista.
    public void limparConcluidas(){
        for(int i = tarefas.size() -1; i >= 0 ; i--){ //Percorre de trás para a frente para evitar saltar elementos ao remover.
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

    //Ordena as tarefas por data usando Bubble Sort.
    //As datas são reformatadas nessa ordem contraria para facilitar na hora da comparacao usando o camparateTo
    public void ordenarPorData(){
        for(int i= 0; i < tarefas.size() - 1; i++){ //"-1" pq se for sem o menos um na ultima comparacao a segunda variavel vai pular para uma variavel que nao existe. 
            for(int j=0; j<tarefas.size()-1-i;j++){ // como é estilo bobble sorte, dps de compara vai para o final, entao esse "-i" aí evita comparar com quem ja foi organizado. 

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

    //mostra as tarefas que estao em atraso seguindo a data do dia que o utlizador estiver a testar. 
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

    public  List<Tarefas> filtrarPorDatas(String data){
        return tarefas.stream().filter(tarefas -> 
            tarefas.getData().equals(data)).collect(Collectors.toList());
    }
    

}