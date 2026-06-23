import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lista {
    
    private ArrayList<Tarefas> tarefas; // para armazar a lista de tarefas. 

    public Lista(){
        this.tarefas = new ArrayList<>();
    }

    public boolean adicionarTarefa(String titulo, String data, String categoria, String prioridade){
        if(titulo == null || titulo.trim().isEmpty()){
            System.out.println("tarefa invalida!");
            return false;
        }

        String[] dataVeri = data.split("/");
        if(dataVeri.length != 3){
            System.out.println("data invalida! use o formato dd/mm/aaaa");
            return false;
        }

        String dia = dataVeri[0];
        String mes = dataVeri[1];
        String ano = dataVeri[2];

        //LocalDate hoje = LocalDate.now();
        

        // validacao se sao numeros
        if(!dia.matches("\\d+") || !mes.matches("\\d+") || !ano.matches("\\d+")){
            System.out.println("data invalida! a data deve conter apenas numeros.");
            return false;
        }

        int diaInt = Integer.parseInt(dia);
        int mesInt = Integer.parseInt(mes);
        int anoInt = Integer.parseInt(ano);


        if(diaInt < 1 || diaInt > 31){
        System.out.println("dia invalido! deve ser entre 01 e 31.");
        return false;
        }
        if(mesInt < 1 || mesInt > 12){
            System.out.println("mes invalido! deve ser entre 01 e 12.");
            return false;
        }
        if(anoInt < 2026){
            System.out.println("ano invalido! deve ser igual ou superior a 2026.");
            return false;
        }
        //aqui vamos validar apenas data do dia de hoje ou futuras.
        LocalDate hoje = LocalDate.now();
        LocalDate dataTarefa = LocalDate.of(anoInt, mesInt, diaInt);

        if(dataTarefa.isBefore(hoje)){
            System.out.println("Essa data ja esta no passado! tente novamente com uma data presente ou futura.");
            return false;
        }

        if(!prioridade.equalsIgnoreCase("baixa") && !prioridade.equalsIgnoreCase("media") && !prioridade.equalsIgnoreCase("alta")){
            System.out.println("prioridade invalida!use apenas: baixa, media ou alta");
            return false;
        }

        

        // se chegou ate aqui, quer dizer que esta tudo valido. Adiciona 
        int novoId = tarefas.size() + 1;  // o id é gerado, ou seja, aqui o usuario nao interfere. 
        Tarefas trf = new Tarefas(novoId,titulo,data,categoria,prioridade);
        tarefas.add(trf);
        System.out.println("===============================================");
        System.out.println("       Tarefa adicionada com sucesso!✅");
        System.out.println("===============================================");
        return true;
    }

    public void listarTarefas(){
       // LocalDateTime hoje = LocalDateTime.now();
       if(tarefas.size() == 0 ){
            System.out.println("lista de tarefas vazia!");
            return;
        }
         System.out.println("===============================================");
        System.out.println("                LISTA DE TAREFAS");
          System.out.println("===============================================");
       for(int i = 0; i < tarefas.size(); i++){
       // LocalDateTime vencimento = tarefas.get(i).getData();
        System.out.printf("%dº titulo: %s; | prioridade: %s| estado: %s| data: %s| categoria: %s\n"
            ,i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getPrioridade(),tarefas.get(i).getStatus(),
            tarefas.get(i).getData(),tarefas.get(i).getCategoria());
          System.out.println("===============================================");
       }
    }//funcao para printar a lista de tarefas com tds os campos.


    public void listarPorCategoria(String categoria){
        if(tarefas.size() == 0){
            System.out.println("lista de tarefas vazia!");
            return;
        }
        if((categoria == null || categoria.trim().isEmpty()) && !categoria.equalsIgnoreCase("casa") && !categoria.equalsIgnoreCase("trabalho") && !categoria.equalsIgnoreCase("estudos") && !categoria.equalsIgnoreCase("outros")){
            System.out.println("categoria mal digitada! opcao validas: : Casa, Estudos, trabalho ou outros.");
            return;
        }

        for(int i = 0; i < tarefas.size(); i++){
            if(tarefas.get(i).getCategoria().equalsIgnoreCase(categoria)){
                System.out.printf("%dº titulo: %s; | prioridade: %s| estado: %s| data: %s\n"
                ,i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getPrioridade(),tarefas.get(i).getStatus(),
                tarefas.get(i).getData());
            }
        }
    }

    
    // Atualiza um campo específico de uma tarefa existente. campo é o que queremos alterar e novoValor é a nova coisa que desejamos colocar. 
    public void atualizar(int id, String campo, String novoValor) {
        if(id >= 1 && id <= tarefas.size()){
            Tarefas trf = tarefas.get(id-1);

            if(campo.equalsIgnoreCase("titulo")){
                if(novoValor == null || novoValor.trim().isEmpty()){
                    System.out.println("titulo invalido!");
                    return;
                } 
                trf.setTitulo(novoValor);
                    System.out.println("===============================================");
                System.out.println("           Titulo atualizado com sucesso!");
                    System.out.println("===============================================");
            }else if(campo.equalsIgnoreCase("data")){
                String[] dataVeri = novoValor.split("/");
            if(dataVeri.length != 3){
                System.out.println("data invalida! use o formato dd/mm/aaaa");
                return;
            }

            String dia = dataVeri[0];
            String mes = dataVeri[1];
            String ano = dataVeri[2];

            //LocalDate hoje = LocalDate.now();
            

            // validacao se sao numeros
            if(!dia.matches("\\d+") || !mes.matches("\\d+") || !ano.matches("\\d+")){
                System.out.println("data invalida! a data deve conter apenas numeros.");
                return;
            }

            int diaInt = Integer.parseInt(dia);
            int mesInt = Integer.parseInt(mes);
            int anoInt = Integer.parseInt(ano);
            

            if(diaInt < 1 || diaInt > 31){
            System.out.println("dia invalido! deve ser entre 01 e 31.");
            return ;
            }
            if(mesInt < 1 || mesInt > 12){
                System.out.println("mes invalido! deve ser entre 01 e 12.");
                return;
            }
            if(anoInt < 2026){
                System.out.println("ano invalido! deve ser igual ou superior a 2026.");
                return;
            }
            //aqui vamos validar apenas data do dia de hoje ou futuras.
            LocalDate hoje = LocalDate.now();
            LocalDate dataTarefa = LocalDate.of(anoInt, mesInt, diaInt);

            if(dataTarefa.isBefore(hoje)){
                System.out.println("Essa data ja esta no passado! tente novamente com uma data presente ou futura.");
                return;
            }

            trf.setData(novoValor);
            System.out.println("atualizacao feita com sucesso!");
            }else if(campo.equalsIgnoreCase("prioridade")){
                if(!novoValor.equalsIgnoreCase("baixa") && !novoValor.equalsIgnoreCase("media") && !novoValor.equalsIgnoreCase("alta")){
                    System.out.println("prioridade invalida!use apenas: baixa, media ou alta");
                    return ;
                }
                trf.setPrioridade(novoValor);
                    System.out.println("===============================================");
                System.out.println("          prioridade ataulizada com sucesso!");
                    System.out.println("===============================================");
            }else{
                    System.out.println("===============================================");
                System.out.println("campo invalide! use: titulo, data ou prioridade");
                    System.out.println("===============================================");
            }
             
        }else{
                System.out.println("===============================================");
            System.out.println(                   "id invalido!");
                System.out.println("===============================================");
        }
    }
    
    //organiza novamente novamente os ids apos uma remocao para nao ter erros(lacunas)
    public void atualizarIds(){
        for(int i=0; i < tarefas.size(); i++){
            tarefas.get(i).setId(i + 1);
        }
    }

    public void removerTarefa(int id) { //wis, o nome id vem pq é o que o usuario irá ver aao selecionar a tarefa que deseja remover. 
        if(tarefas.size() == 0){
            System.out.println("lista de tarefas vazia");
            return;
        }
        
        if(id > 0 && tarefas.size() >= id){
            tarefas.remove(id-1); // -1 pq isso é arraylist começa do 0
            atualizarIds();
              System.out.println("===============================================");
            System.out.println("         Tarefa removida com sucesso!✅");
              System.out.println("===============================================");
        }else{
              System.out.println("===============================================");
            System.out.println("                  Id invalido!⚠️");
              System.out.println("===============================================");
        } 
    }

    public ArrayList<Tarefas> obterTarefas() {
        return tarefas;
    }

    public void marcarConcluida(int id){
        if(id>=1 && id <= tarefas.size()){
            tarefas.get(id-1).setStatus("CONCLUIDA");
             System.out.println("===============================================");
            System.out.println("               Tarefa concluída!");
               System.out.println("===============================================");
        }else{
         System.out.println("===============================================");
            System.out.println("               ID inexistente!");
               System.out.println("===============================================");
        }
    }

    public void marcarPendente(int id){
         if(id>=1 && id <= tarefas.size()){
            tarefas.get(id-1).setStatus("PENDENTE");
               System.out.println("===============================================");
            System.out.println("                 Tarefa pendente!");
               System.out.println("===============================================");
        }else{
               System.out.println("===============================================");
            System.out.println("                 ID inexistente!");
               System.out.println("===============================================");
        }
    }
    
    public void filtrarConcluidas(){
       
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("CONCLUÍDA")){
                   System.out.println("===============================================");
                System.out.println((i+1)+" - "+tarefas.get(i).getTitulo());
                   System.out.println("===============================================");
            }
        }
    }

    public void filtrarPendentes(){ 
    
        for(int i=0; i< tarefas.size(); i++){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("PENDENTE")){
                   System.out.println("===============================================");
                System.out.println((i+1)+" - "+tarefas.get(i).getTitulo());
                   System.out.println("===============================================");
            }
        }
    }


    //Procurar a tarefa, caso encontre, retorna o indice dela, o titulo e a situação

public void procurarTarefa(String titulo){

    boolean encontrada = false;

    for(int i = 0; i < tarefas.size(); i++){

        Tarefas trf = tarefas.get(i);

        if(trf.getTitulo().equalsIgnoreCase(titulo)){

            System.out.println("===============================================");
            System.out.println("              Tarefa encontrada!");
            System.out.println("===============================================");

            System.out.println("Índice: " + (i + 1));
            System.out.println("Título: " + trf.getTitulo());
            System.out.println("Estado: " + trf.getStatus());

            System.out.println("===============================================");

            encontrada = true;
        }
    }


    if(!encontrada){

        System.out.println("===============================================");
        System.out.println("        Tarefa não encontrada!");
        System.out.println("===============================================");

    }

}

    /*public void listarVenceHoje(){
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
    }*/


    
    //Guarda todas as tarefas no ficheiro tarefas.txt.
    //Cada tarefa é guardada numa linha com os atributos separados por ponto e vírgula.
    public void guardarTarefas(){
        try{
            PrintWriter writer = new PrintWriter("tarefas.txt");
            for(int i = 0; i < tarefas.size(); i++){
                Tarefas trf = tarefas.get(i);
                writer.println(trf.getId() + ";" + trf.getTitulo() + ";" + trf.getData() + ";" +trf.getCategoria()+ ";" + trf.getPrioridade() + ";" + trf.getStatus());

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
                String categoria = partes[3];
                String prioridade = partes[4];
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

        if(tarefas.size() == 0 ){
            System.out.println("lista de tarefas vazia!");
            return;
        }
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

        if(tarefas.size() == 0){
            System.out.println("Lista vazia!");
            return;
        }

        if(!prioridade.equalsIgnoreCase("baixa") && !prioridade.equalsIgnoreCase("media") && !prioridade.equalsIgnoreCase("alta")){
            System.out.println("prioridade invalida!opcoes validas: baixa, media ou alta");
            return;
        }
        for(int i= 0; i < tarefas.size() ; i++){
            if(tarefas.get(i).getPrioridade().equalsIgnoreCase(prioridade)){
                System.out.printf("%dº titulo: %s; | prioridade: %s| estado: %s| data: %s| categoria: %s\n"
                ,i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getPrioridade(),tarefas.get(i).getStatus(),
                tarefas.get(i).getData(),tarefas.get(i).getCategoria());
            }
        }
    } 


    //Remove todas as tarefas concluídas da lista.
    public void limparConcluidas(){

        if(tarefas.size() == 0){
            System.out.println("lista vazia!");
            return;
        }

        int ctd = 0;

        for(int i = 0; i < tarefas.size(); i++){
            if(tarefas.get(i).getStatus().equalsIgnoreCase("concluida")) ctd++;
        }

        if(ctd == 0){
            System.out.println("nao ha tarefas concluidas");
            return;
        }

        for(int i = tarefas.size() -1; i >= 0 ; i--){ //Percorre de trás para a frente para evitar saltar elementos ao remover.
            if(tarefas.get(i).getStatus().equalsIgnoreCase("concluida"))
                tarefas.remove(i);
        }
        atualizarIds();
        System.out.println("limpeza feita com sucesso!");
    }

    public void ordenarPorPrioridade(){
        if(tarefas.size() == 0){
            System.out.println("lista vazia!");
            return;
        }

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
        if(tarefas.size() == 0){
            System.out.println("lista vazia!");
            return;
        }

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

        if(tarefas.size() == 0){
            System.out.println("lista vazia!");
            return;
        }

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
                System.out.printf("%dº titulo: %s; | prioridade: %s| estado: %s| data: %s| categoria: %s\n"
                ,i+1,tarefas.get(i).getTitulo(),tarefas.get(i).getPrioridade(),tarefas.get(i).getStatus(),
                tarefas.get(i).getData(),tarefas.get(i).getCategoria());
            }
            }
        
    }

    public  List<Tarefas> filtrarPorDatas(String data){
        return tarefas.stream().filter(tarefas -> 
            tarefas.getData().equals(data)).collect(Collectors.toList());
    }
    

}