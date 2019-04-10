package datascience;

import java.util.List;

public class Entidade {

    public Entidade() {
    }
        
    private int id;
    private String nome;
    private String descricao;
    private Conexao conexaoOrigem;
    private Consulta consultaOrigem;
    private Conexao conexaoDestino;             
    private List<Atributo> atributos;

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

    public Conexao getConexaoOrigem() {
        return conexaoOrigem;
    }

    public void setConexaoOrigem(Conexao conexaoOrigem) {
        this.conexaoOrigem = conexaoOrigem;
    }

    public Consulta getConsultaOrigem() {
        return consultaOrigem;
    }

    public void setConsultaOrigem(Consulta consultaOrigem) {
        this.consultaOrigem = consultaOrigem;
    }

    public Conexao getConexaoDestino() {
        return conexaoDestino;
    }

    public void setConexaoDestino(Conexao conexaoDestino) {
        this.conexaoDestino = conexaoDestino;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    

}
