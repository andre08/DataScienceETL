package datascience;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entidade {

    private String nome;
    private String descricao;
    private Conexao conexaoOrigem;
    private Conexao conexaoDestino;             
    private Consulta consultaOrigem;
    private List<Atributo> atributos;

    public Entidade() {
        this.nome = "";
        this.descricao = "";
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

    public Conexao getConexaoDestino() {
        return conexaoDestino;
    }

    public void setConexaoDestino(Conexao conexaoDestino) {
        this.conexaoDestino = conexaoDestino;
    }

    public Consulta getConsultaOrigem() {
        return consultaOrigem;
    }

    public void setConsultaOrigem(Consulta consultaOrigem) {
        this.consultaOrigem = consultaOrigem;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    @Override
    public String toString() {
        return "Entidade{" + "nome=" + nome + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.conexaoOrigem);
        hash = 53 * hash + Objects.hashCode(this.conexaoDestino);
        hash = 53 * hash + Objects.hashCode(this.consultaOrigem);
        hash = 53 * hash + Objects.hashCode(this.atributos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entidade other = (Entidade) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.conexaoOrigem, other.conexaoOrigem)) {
            return false;
        }
        if (!Objects.equals(this.conexaoDestino, other.conexaoDestino)) {
            return false;
        }
        if (!Objects.equals(this.consultaOrigem, other.consultaOrigem)) {
            return false;
        }
        if (!Objects.equals(this.atributos, other.atributos)) {
            return false;
        }
        return true;
    }

}
