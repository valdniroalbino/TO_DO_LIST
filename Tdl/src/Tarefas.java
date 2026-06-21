import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Tarefas {
    private int id;
    private String titulo;
    private String data;
    private String prioridade;
    private String status;

    public Tarefas(int id, String titulo, String data, String prioridade){
        this.id = id;
        this.titulo = titulo;
        this.data = data;
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

    public LocalDateTime getData() {
      DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/aaaa HH:mm");
      
        return LocalDateTime.parse(data, form);
    }

    public void setData(String data) {
        this.data = data;
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