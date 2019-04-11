package datascience;

import java.util.Objects;

public class Conexao {

    private String nome;
    private String descricao;
    private String url;
    private int porta;
    private String nomeBanco;
    private String SID;
    private String usename;
    private String password;
    private String SGDB;

    public Conexao() {
        this.nome = "";
        this.descricao = "";
        this.url = "";
        this.porta = 0;
        this.nomeBanco = "";
        this.SID = "";
        this.usename = "";
        this.password = "";
        this.SGDB = "";
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSGDB() {
        return SGDB;
    }

    public void setSGDB(String SGDB) {
        this.SGDB = SGDB;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.descricao);
        hash = 43 * hash + Objects.hashCode(this.url);
        hash = 43 * hash + this.porta;
        hash = 43 * hash + Objects.hashCode(this.nomeBanco);
        hash = 43 * hash + Objects.hashCode(this.SID);
        hash = 43 * hash + Objects.hashCode(this.usename);
        hash = 43 * hash + Objects.hashCode(this.password);
        hash = 43 * hash + Objects.hashCode(this.SGDB);
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
        final Conexao other = (Conexao) obj;
        if (this.porta != other.porta) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.nomeBanco, other.nomeBanco)) {
            return false;
        }
        if (!Objects.equals(this.SID, other.SID)) {
            return false;
        }
        if (!Objects.equals(this.usename, other.usename)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.SGDB, other.SGDB)) {
            return false;
        }
        return true;
    }
    
}
