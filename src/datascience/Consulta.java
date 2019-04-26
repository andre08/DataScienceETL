package datascience;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta extends Object implements Cloneable{

    /*informações sobre a consulta*/
    private String nome;
    private String descricao;
    private String sql;
    
    /*obtendo dados do resutado*/
    private Conexao conexao;
    private Entidade entidade;

    public Consulta() {
        this.nome = "";
        this.descricao = "";
        this.sql = "";
        this.conexao = new Conexao();
        this.entidade = new Entidade();
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

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.descricao);
        hash = 19 * hash + Objects.hashCode(this.sql);
        hash = 19 * hash + Objects.hashCode(this.conexao);
        hash = 19 * hash + Objects.hashCode(this.entidade);
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.sql, other.sql)) {
            return false;
        }
        if (!Objects.equals(this.conexao, other.conexao)) {
            return false;
        }
        if (!Objects.equals(this.entidade, other.entidade)) {
            return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public Consulta Copia() {
        try {
            return (Consulta) this.clone(); //To change body of generated methods, choose Tools | Templates.
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
