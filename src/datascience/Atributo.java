package datascience;

import java.util.Objects;

public class Atributo extends Object implements Cloneable {

    private String nome;
    private String descricao;
    private String tipo;
    private int tamanho;
    private int precisao;
    private String obrigatorio;
    private String chavePrimaria;
    private String chaveEstrangeira;
    private String valorSequencial;
    private Entidade referenciaEntidade;
    private Atributo referenciaAtributo;
    private String observacao;
    private boolean controle;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashReferenciaEntidade;
    private int hashReferenciaAtributo;
    //campos para estatistica
    private int comprimentoMaximo;
    private int qtdeElementosDiferentes;
    
    public Atributo() {
        this.nome = "";
        this.descricao = "";
        this.tipo = "";
        this.tamanho = 0;
        this.precisao = 0;
        this.obrigatorio = "N";
        this.chavePrimaria = "N";
        this.chaveEstrangeira = "N";
        this.valorSequencial = "N";
        this.observacao = "";
        this.controle = false;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPrecisao() {
        return precisao;
    }

    public void setPrecisao(int precisao) {
        this.precisao = precisao;
    }

    public String getObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(String obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public String getChavePrimaria() {
        return chavePrimaria;
    }

    public void setChavePrimaria(String chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    public String getChaveEstrangeira() {
        return chaveEstrangeira;
    }

    public void setChaveEstrangeira(String chaveEstrangeira) {
        this.chaveEstrangeira = chaveEstrangeira;
    }

    public String getValorSequencial() {
        return valorSequencial;
    }

    public void setValorSequencial(String valorSequencial) {
        this.valorSequencial = valorSequencial;
    }

    public Entidade getReferenciaEntidade() {
        return referenciaEntidade;
    }

    public void setReferenciaEntidade(Entidade referenciaEntidade) {
        this.referenciaEntidade = referenciaEntidade;
    }

    public Atributo getReferenciaAtributo() {
        return referenciaAtributo;
    }

    public void setReferenciaAtributo(Atributo referenciaAtributo) {
        this.referenciaAtributo = referenciaAtributo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean getControle() {
        return controle;
    }

    public void setControle(boolean controle) {
        this.controle = controle;
    }

    public int getHashReferenciaEntidade() {
        return hashReferenciaEntidade;
    }

    public void setHashReferenciaEntidade(int hashReferenciaEntidade) {
        this.hashReferenciaEntidade = hashReferenciaEntidade;
    }

    public int getHashReferenciaAtributo() {
        return hashReferenciaAtributo;
    }

    public void setHashReferenciaAtributo(int hashReferenciaAtributo) {
        this.hashReferenciaAtributo = hashReferenciaAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + this.tamanho;
        hash = 97 * hash + this.precisao;
        hash = 97 * hash + Objects.hashCode(this.obrigatorio);
        hash = 97 * hash + Objects.hashCode(this.chavePrimaria);
        hash = 97 * hash + Objects.hashCode(this.chaveEstrangeira);
        hash = 97 * hash + Objects.hashCode(this.valorSequencial);
        hash = 97 * hash + Objects.hashCode(this.referenciaEntidade);
        hash = 97 * hash + Objects.hashCode(this.referenciaAtributo);
        hash = 97 * hash + Objects.hashCode(this.observacao);
        hash = 97 * hash + Objects.hashCode(this.controle);
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
        final Atributo other = (Atributo) obj;
        if (this.tamanho != other.tamanho) {
            return false;
        }
        if (this.precisao != other.precisao) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.obrigatorio, other.obrigatorio)) {
            return false;
        }
        if (!Objects.equals(this.chavePrimaria, other.chavePrimaria)) {
            return false;
        }
        if (!Objects.equals(this.chaveEstrangeira, other.chaveEstrangeira)) {
            return false;
        }
        if (!Objects.equals(this.valorSequencial, other.valorSequencial)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.controle, other.controle)) {
            return false;
        }
        if (!Objects.equals(this.referenciaEntidade, other.referenciaEntidade)) {
            return false;
        }
        if (!Objects.equals(this.referenciaAtributo, other.referenciaAtributo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public String toJson(){
        return "";
    }

    public Atributo copia() {

        Atributo copiaAtributo = new Atributo();

        copiaAtributo.setNome(this.nome);
        copiaAtributo.setDescricao(this.descricao);
        copiaAtributo.setTipo(this.tipo);
        copiaAtributo.setTamanho(this.tamanho);
        copiaAtributo.setPrecisao(this.precisao);
        copiaAtributo.setObrigatorio(this.obrigatorio);
        copiaAtributo.setChavePrimaria(this.chavePrimaria);
        copiaAtributo.setChaveEstrangeira(this.chaveEstrangeira);
        copiaAtributo.setValorSequencial(this.valorSequencial);
        copiaAtributo.setObservacao(this.observacao);
        copiaAtributo.setControle(this.controle);
        copiaAtributo.setReferenciaEntidade(this.referenciaEntidade == null ? null : this.referenciaEntidade.copia());
        copiaAtributo.setReferenciaAtributo(this.referenciaAtributo == null ? null : this.referenciaAtributo.copia());

        return copiaAtributo;
    }

    public String getSQLCreateCode(boolean pOpcoes) {

        String codeSQL = "";
        codeSQL += this.nome + " ";
        codeSQL += this.tipo;
        switch (this.tipo) {
            case "CHAR":
                codeSQL += "(" + Integer.toString(this.tamanho) + ")";
                break;
            case "VARCHAR":
                codeSQL += "(" + Integer.toString(this.tamanho) + ")";
                break;
            case "INT":
                codeSQL += " ";
                break;
            case "FLOAT":
                codeSQL += "(" + Integer.toString(this.tamanho) + "," + Integer.toString(this.precisao) + ")";
                break;
            case "NUMERIC":
                codeSQL += "(" + Integer.toString(this.tamanho) + "," + Integer.toString(this.precisao) + ")";
                break;
            case "DECIMAL":
                codeSQL += "(" + Integer.toString(this.tamanho) + "," + Integer.toString(this.precisao) + ")";
                break;
            case "NUMBER":
                codeSQL += "(" + Integer.toString(this.tamanho) + "," + Integer.toString(this.precisao) + ")";
                break;
            case "DATE":
                codeSQL += " ";
                break;
            case "TIME":
                codeSQL += " ";
                break;
            case "DATETIME":
                codeSQL += " ";
                break;
            case "TIMESTAMP":
                codeSQL += " ";
                break;
            case "CLOB":
                codeSQL += " ";
                break;
            case "BLOB":
                codeSQL += " ";
                break;
            default:
                codeSQL += " ";
                break;
        }
        
        if(pOpcoes){
            codeSQL += (this.obrigatorio.equals("S") ? "NOT NULL " : "");
        }
        return codeSQL;
    }
    
}
