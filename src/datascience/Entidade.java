package datascience;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entidade extends Object implements Cloneable {

    private String nome;
    private String descricao;
    private Conexao conexao;
    private List<Atributo> atributos;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashConexao;

    public Entidade() {
        this.nome = "";
        this.descricao = "";
        this.conexao = new Conexao();
        this.atributos = new ArrayList<Atributo>();
    }

    public void AddAtributosSA() {
        Atributo atributo;

        //criando identificador
        atributo = new Atributo();
        atributo.setNome("ID" + this.nome);
        atributo.setDescricao("ATRIBUTO IDENTIFICADOR");
        atributo.setTipo("INT");
        atributo.setTamanho(0);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("S");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("S");
        this.getAtributos().add(0,atributo);

        //criando ação
        atributo = new Atributo();
        atributo.setNome("ACAO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE ATUALIZAÇÃO");
        atributo.setTipo("VARCHAR");
        atributo.setTamanho(20);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

        //criando DATA DE INCLUCAO
        atributo = new Atributo();
        atributo.setNome("DTINCLUSAO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE ATUALIZAÇÃO");
        atributo.setTipo("DATETIME");
        atributo.setTamanho(0);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

        //criando DATA DE ALTGERACAO
        atributo = new Atributo();
        atributo.setNome("DTALTERACAO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE ATUALIZAÇÃO");
        atributo.setTipo("DATETIME");
        atributo.setTamanho(0);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

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

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public int getHashConexao() {
        return hashConexao;
    }

    public void setHashConexao(int hashConexao) {
        this.hashConexao = hashConexao;
    }
    
    public void addAtributo(Atributo atributoAnterior, Atributo atributoNovo) {

        if (atributoAnterior == null) {
            this.atributos.add(atributoNovo);
        } else {
            this.atributos.set(this.atributos.indexOf(atributoAnterior), atributoNovo);
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + Objects.hashCode(this.conexao);
        hash = 37 * hash + Objects.hashCode(this.atributos);
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
        if (!Objects.equals(this.conexao, other.conexao)) {
            return false;
        }
        if (!Objects.equals(this.atributos, other.atributos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public Entidade copia() {
        
        Entidade copiaEntidade = new Entidade();
        
        copiaEntidade.setNome(this.nome);
        copiaEntidade.setDescricao(this.descricao);
        copiaEntidade.setConexao(this.conexao==null?null:this.conexao.copia());
        
        List<Atributo> copiaAtributos = new ArrayList<Atributo>();
        for (Atributo atributo : this.atributos) {
            copiaAtributos.add(atributo.copia());
        }
        copiaEntidade.setAtributos(copiaAtributos);
        
        return copiaEntidade;

    }
}
