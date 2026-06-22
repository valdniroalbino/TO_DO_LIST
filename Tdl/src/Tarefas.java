public class Tarefas {
    private int id;
    private String titulo;
    private String data;
     private String categoria;
    private String prioridade;
    private String status;

    public Tarefas(int id, String titulo, String data, String categoria, String prioridade){
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = "pendente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}