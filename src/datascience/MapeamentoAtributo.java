package datascience;

import java.util.Objects;

public class MapeamentoAtributo {
    
    private Atributo atributoOrigem;
    private Atributo atributoDestino;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashAtributoOrigem;
    private int hashAtributoDestino;

    public MapeamentoAtributo() {
    }

    public Atributo getAtributoOrigem() {
        return atributoOrigem;
    }

    public void setAtributoOrigem(Atributo atributoOrigem) {
        this.atributoOrigem = atributoOrigem;
    }

    public Atributo getAtributoDestino() {
        return atributoDestino;
    }

    public void setAtributoDestino(Atributo atributoDestino) {
        this.atributoDestino = atributoDestino;
    }

    public int getHashAtributoOrigem() {
        return hashAtributoOrigem;
    }

    public void setHashAtributoOrigem(int hashAtributoOrigem) {
        this.hashAtributoOrigem = hashAtributoOrigem;
    }

    public int getHashAtributoDestino() {
        return hashAtributoDestino;
    }

    public void setHashAtributoDestino(int hashAtributoDestino) {
        this.hashAtributoDestino = hashAtributoDestino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.atributoOrigem);
        hash = 97 * hash + Objects.hashCode(this.atributoDestino);
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
        final MapeamentoAtributo other = (MapeamentoAtributo) obj;
        if (!Objects.equals(this.atributoOrigem, other.atributoOrigem)) {
            return false;
        }
        if (!Objects.equals(this.atributoDestino, other.atributoDestino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return atributoOrigem.getNome() + " - " + atributoDestino.getNome();
    }

    public MapeamentoAtributo copia(){        

        MapeamentoAtributo copiaMapeamentoAtributo = new MapeamentoAtributo();
        copiaMapeamentoAtributo.setAtributoOrigem(this.atributoOrigem==null?null:this.atributoOrigem.copia());
        copiaMapeamentoAtributo.setAtributoDestino(this.atributoDestino==null?null:this.atributoDestino.copia());

        return copiaMapeamentoAtributo;

    }
    
}
