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
    //campos para estatistica da tabela
    private int numRegistros;

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
        atributo.setControle(true);
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
        atributo.setControle(true);
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
        atributo.setControle(true);
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
        atributo.setControle(true);
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
        atributo.setControle(true);
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
        atributo.setControle(true);
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

    public int getNumRegistros(){
        return this.numRegistros;
    }
    
    public void setNumRegistros(int numRegistros){
        this.numRegistros = numRegistros;
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
        hash = 37 * hash + Objects.hashCode(this.numRegistros);
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
        if (!Objects.equals(this.numRegistros, other.numRegistros)) {
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

        copiaEntidade.setNumRegistros(this.numRegistros);
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
            codeSQL += atributo.getSQLCreateCode(true) + "\n";
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
                codeSQLFK += "CONSTRAINT FK_" + this.nome + "_" + atributo.getReferenciaEntidade().getNome() + " FOREIN KEY (" + atributo.getNome() + ") REFERENCES " + atributo.getReferenciaEntidade().getNome() + " (" + atributo.getReferenciaAtributo().getNome() + ")\n";
            }
            qtdeAtributo++;
        }

        //Lendo Chaves Primarias
        if (!codeSQLPK.equals("")) {
            codeSQL += "    , CONSTRAINT PK_" + this.nome + " PRIMARY KEY (" + codeSQLPK + ")\n";
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
                
                String vAtributo = atributo.getSQLCreateCode(true) + " AUTO_INCREMENT ";
                codeSQL = codeSQL.replace(atributo.getSQLCreateCode(true), vAtributo);
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
                
                String vAtributo = atributo.getSQLCreateCode(true) + " IDENTITY(1,1) ";
                codeSQL = codeSQL.replace(atributo.getSQLCreateCode(true), vAtributo);
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
        codeSQL += "	SET NEW.CONTROLE = UPPER(NEW.CONTROLE); \n";
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
        codeSQL += " \n";
        codeSQL += " \n";
        codeSQL += "CREATE PROCEDURE STP_" + this.nome + "() \n";
        codeSQL += "BEGIN \n";
        codeSQL += "	-- CAMPOS PK E FK DA TABELA \n";
        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (atributo.getChavePrimaria().equals("S") || atributo.getChaveEstrangeira().equals("S") ) {
                    codeSQL += "	DECLARE V" + atributo.getSQLCreateCode(false).trim() + ";\n";
                }
            }
        }                   
        codeSQL += " \n";
        codeSQL += "	-- CAMPO PARA QUANTIDADE DE REGISTRO \n";
        codeSQL += "	DECLARE VQTDE INT DEFAULT 0; \n";
        codeSQL += "	-- CAMPO DE MENSAGEM DE RETORNO \n";
        codeSQL += "	DECLARE VMENSAGEM VARCHAR(255); \n";
        codeSQL += "	DECLARE VCONTROLE VARCHAR(10); \n";
        codeSQL += " \n";

        codeSQL += "	-- TODOS OS CAMPOS DA TABELA \n";
        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (atributo.getChavePrimaria().equals("N") && atributo.getChaveEstrangeira().equals("N") ) {
                    codeSQL += "	DECLARE V" + atributo.getSQLCreateCode(false).trim() + ";\n";
                }
            }                   
        }                   
        codeSQL += " \n";
        
        codeSQL += "	-- CURSOR \n";
        codeSQL += "	DECLARE VFIMCURSOR INTEGER DEFAULT 0; \n";
        codeSQL += "	DECLARE VCURSOR CURSOR FOR	SELECT \n";
        int qtdeAtributo;
        qtdeAtributo = 0;
        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (qtdeAtributo > 0) {
                    codeSQL += "	                          	, "+ atributo.getNome() + " \n";
                }else{
                    codeSQL += "	                          	" + atributo.getNome() + " \n";
                }
                qtdeAtributo++;
            }
        }                   
        codeSQL += "	                          FROM " + this.nome + " \n";
        codeSQL += "	                          WHERE PROCESSADO = 'N'; \n";
        codeSQL += " \n";

        codeSQL += "	DECLARE CONTINUE HANDLER FOR NOT FOUND SET VFIMCURSOR = 1; \n";
        codeSQL += " \n";

        codeSQL += "	OPEN VCURSOR; \n";
        codeSQL += " \n";

        codeSQL += "	FETCH VCURSOR INTO ";
        qtdeAtributo = 0;
        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (qtdeAtributo > 0) {
                    codeSQL += ", V"+ atributo.getNome();
                }else{
                    codeSQL += "V" + atributo.getNome();
                }
                qtdeAtributo++;
            }                   
        }                   
        codeSQL += "; \n";
        
        
        codeSQL += "	WHILE (VFIMCURSOR <> 1) DO \n";

        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (atributo.getChaveEstrangeira().equals("S") ) {
                    codeSQL += "	DECLARE V" + atributo.getSQLCreateCode(false).trim() + ";\n";
                    codeSQL += " \n";
                }
            }
        }                   
        codeSQL += " \n";

       
        codeSQL += "		UPDATE " + this.nome + " SET PROCESSADO = 'S', MENSAGEM = VMENSAGEM, CONTROLE = VCONTROLE WHERE";
        qtdeAtributo = 0;
        for (Atributo atributo : atributos) {            
            if(!atributo.getControle()){
                if (atributo.getChavePrimaria().equals("S")) {
                    if (qtdeAtributo > 0) {
                        codeSQL += " and";
                    }
                    codeSQL += " " + atributo.getNome() + " = V" + atributo.getNome();
                }
            }  
        }  
        codeSQL += " \n";
        
        codeSQL += "		FETCH VCURSOR INTO ";
        for (Atributo atributo : atributos) {
            if(!atributo.getControle()){
                if (qtdeAtributo > 0) {
                    codeSQL += ", V"+ atributo.getNome();
                }else{
                    codeSQL += "V" + atributo.getNome();
                }
                qtdeAtributo++;
            }                   
        }                   
        codeSQL += "; \n";        
        codeSQL += "	END WHILE; \n";
        codeSQL += "	CLOSE VCURSOR; \n";
        codeSQL += " \n";
        
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
