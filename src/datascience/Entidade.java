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
        this.getAtributos().add(0, atributo);

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

        //criando DATA DE INCLUÇÃO
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

        //criando DATA DE ATUALIZAÇÃO
        atributo = new Atributo();
        atributo.setNome("DTATUALIZACAO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE ATUALIZAÇÃO");
        atributo.setTipo("DATETIME");
        atributo.setTamanho(0);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

        //criando VERSÃO
        atributo = new Atributo();
        atributo.setNome("VERSAO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE VERSÃO");
        atributo.setTipo("INT");
        atributo.setTamanho(0);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

        //criando PROCESSADO
        atributo = new Atributo();
        atributo.setNome("PROCESSADO");
        atributo.setDescricao("ATRIBUTO PARA CONTROLE DE PROCESSAMENTO");
        atributo.setTipo("CHAR");
        atributo.setTamanho(1);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("S");
        atributo.setChavePrimaria("N");
        atributo.setChaveEstrangeira("N");
        atributo.setValorSequencial("N");
        this.getAtributos().add(atributo);

        //criando MENSAGEM
        atributo = new Atributo();
        atributo.setNome("MENSAGEM");
        atributo.setDescricao("ATRIBUTO PARA MENSAGEM DE PROCESSAMENTO");
        atributo.setTipo("VARCHAR");
        atributo.setTamanho(300);
        atributo.setPrecisao(0);
        atributo.setObrigatorio("N");
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
        copiaEntidade.setConexao(this.conexao == null ? null : this.conexao.copia());

        List<Atributo> copiaAtributos = new ArrayList<Atributo>();
        for (Atributo atributo : this.atributos) {
            copiaAtributos.add(atributo.copia());
        }
        copiaEntidade.setAtributos(copiaAtributos);

        return copiaEntidade;

    }

    public String getSQLCreateCode() {

        int qtdeAtributo;
        String codeSQL = "CREATE TABLE ";
        String codeSQLPK = "";
        String codeSQLFK = "";
        codeSQL += this.nome + " (\n";

        //Lendo atributos
        qtdeAtributo = 0;
        for (Atributo atributo : atributos) {
            codeSQL += "    ";
            if (qtdeAtributo > 0) {
                codeSQL += ", ";
            }
            codeSQL += atributo.getSQLCreateCode() + "\n";
            //VERIFICANDO PK
            if (atributo.getChavePrimaria().equals("S")) {
                if (!codeSQLPK.equals("")) {
                    codeSQLPK += "  , ";
                }
                codeSQLPK += atributo.getNome();
            }
            //VERIFICANDO FK
            if ((atributo.getChaveEstrangeira().equals("S")) && (atributo.getReferenciaEntidade() != null) && (atributo.getReferenciaAtributo() != null)) {
                if (!codeSQLFK.equals("")) {
                    codeSQLFK += "  , ";
                }
                codeSQLFK += "FOREIN KEY (" + atributo.getNome() + ") REFERENCES " + atributo.getReferenciaEntidade().getNome() + "(" + atributo.getReferenciaAtributo().getNome() + ")\n";
            }
            qtdeAtributo++;
        }

        //Lendo Chaves Primarias
        if (!codeSQLPK.equals("")) {
            codeSQL += "    , PRIMARY KEY(" + codeSQLPK + ")\n";
        }
        //Lendo Chaves Estrangeiras
        if (!codeSQLFK.equals("")) {
            codeSQL += "    , " + codeSQLFK + " ";
        }
        codeSQL += ");\n";

        return codeSQL;
    }

    public String getSQLCreateCodeMySql() {
        String codeSQL = this.getSQLCreateCode();
        
        for (Atributo atributo : atributos) {

            if (atributo.getValorSequencial().equals("S")){
                
                String vAtributo = atributo.getSQLCreateCode() + " AUTO_INCREMENT ";
                codeSQL = codeSQL.replace(atributo.getSQLCreateCode(), vAtributo);
            }

        } 

        return codeSQL;
    }

    public String getSQLCreateCodeOracle() {
        String codeSQL = this.getSQLCreateCode();

        return codeSQL;
    }

    public String getSQLCreateCodeMsSqlServer() {
        String codeSQL = this.getSQLCreateCode();

        for (Atributo atributo : atributos) {

            if (atributo.getValorSequencial().equals("S")){
                
                String vAtributo = atributo.getSQLCreateCode() + " IDENTITY(1,1) ";
                codeSQL = codeSQL.replace(atributo.getSQLCreateCode(), vAtributo);
            }

        } 

        return codeSQL;
    }

    public String getSQLCreateSACodeMySql() {
        String codeSQL = this.getSQLCreateCodeMySql();

        codeSQL += " \n";
        codeSQL += "DELIMITER $$ \n";
        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BI BEFORE INSERT ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += "	SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	-- ATUALIZANDO CAMPO DE CONTROLE DE ALTERACAO \n";
        codeSQL += "	SET NEW.DTINCLUSAO = NOW(); \n";
        codeSQL += "	SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "	SET NEW.VERSAO = 1; \n";
        codeSQL += "	SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "	SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += "END $$ \n";
        codeSQL += " \n";
        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BU BEFORE UPDATE ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += " \n";
        codeSQL += "SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	IF (IFNULL(NEW.CONTROLE, '') IN ('INTERNO', 'ERRO')) THEN \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.CONTROLE = OLD.CONTROLE; \n";
        codeSQL += " \n";
        codeSQL += "	ELSE \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "		SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += " 		SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "		SET NEW.VERSAO = IFNULL(NEW.VERSAO, 0) + 1; \n";
        codeSQL += " \n";
        codeSQL += "	END IF; \n";
        codeSQL += "END $$ \n";
        codeSQL += "DELIMITER ; \n";
        
        return codeSQL;
    }

    public String getSQLCreateSACodeMsSqlServer() {
        String codeSQL = this.getSQLCreateCodeMsSqlServer();

        codeSQL += " \n";
        codeSQL += "DELIMITER $$ \n";
        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BI BEFORE INSERT ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += "	SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	-- ATUALIZANDO CAMPO DE CONTROLE DE ALTERACAO \n";
        codeSQL += "	SET NEW.DTINCLUSAO = NOW(); \n";
        codeSQL += "	SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "	SET NEW.VERSAO = 1; \n";
        codeSQL += "	SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "	SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += "END $$ \n";
        codeSQL += " \n";
        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BU BEFORE UPDATE ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += " \n";
        codeSQL += "SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	IF (IFNULL(NEW.CONTROLE, '') IN ('INTERNO', 'ERRO')) THEN \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.CONTROLE = OLD.CONTROLE; \n";
        codeSQL += " \n";
        codeSQL += "	ELSE \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "		SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += " 		SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "		SET NEW.VERSAO = IFNULL(NEW.VERSAO, 0) + 1; \n";
        codeSQL += " \n";
        codeSQL += "	END IF; \n";
        codeSQL += "END $$ \n";
        codeSQL += "DELIMITER ; \n";
        
        return codeSQL;
    }

    public String getSQLCreateSACodeOracle() {
        String codeSQL = this.getSQLCreateCodeOracle();

        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BI BEFORE INSERT ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += "	SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	-- ATUALIZANDO CAMPO DE CONTROLE DE ALTERACAO \n";
        codeSQL += "	SET NEW.DTINCLUSAO = NOW(); \n";
        codeSQL += "	SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "	SET NEW.VERSAO = 1; \n";
        codeSQL += "	SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "	SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += "END $$ \n";
        codeSQL += " \n";
        codeSQL += " \n";
        codeSQL += "CREATE TRIGGER TR_" + this.nome + "_BU BEFORE UPDATE ON " + this.nome + " FOR EACH ROW \n";
        codeSQL += "BEGIN \n";
        codeSQL += " \n";
        codeSQL += "SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
        codeSQL += " \n";
        codeSQL += "	IF (IFNULL(NEW.CONTROLE, '') IN ('INTERNO', 'ERRO')) THEN \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.CONTROLE = OLD.CONTROLE; \n";
        codeSQL += " \n";
        codeSQL += "	ELSE \n";
        codeSQL += " \n";
        codeSQL += "		SET NEW.PROCESSADO = 'N'; \n";
        codeSQL += "		SET NEW.MENSAGEM = ''; \n";
        codeSQL += " \n";
        codeSQL += " 		SET NEW.DTATUALIZACAO = NOW(); \n";
        codeSQL += "		SET NEW.VERSAO = IFNULL(NEW.VERSAO, 0) + 1; \n";
        codeSQL += " \n";
        codeSQL += "	END IF; \n";
        codeSQL += "END $$ \n";
        codeSQL += "DELIMITER ; \n";
        
        return codeSQL;
    }

}
