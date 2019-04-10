package datascience;

public class Consulta {

    /*informações sobre a consulta*/
    private int id;
    private String nome;
    private String descricao;
    private String sql;
    
    /*obtendo dados do resutado*/
    private Conexao conexao;
    private Entidade entidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
    
    
}
