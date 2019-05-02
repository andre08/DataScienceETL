package datascience;

import java.util.Objects;

public class MapeamentoDW{
    
    private Entidade entidadeSAOrigem;
    private Entidade entidadeDWDestino;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashEntidadeSAOrigem;
    private int hashEntidadeDWDestino;

    public MapeamentoDW() {

    }

    public Entidade getEntidadeSAOrigem() {
        return entidadeSAOrigem;
    }

    public void setEntidadeSAOrigem(Entidade entidadeSAOrigem) {
        this.entidadeSAOrigem = entidadeSAOrigem;
    }

    public Entidade getEntidadeDWDestino() {
        return entidadeDWDestino;
    }

    public void setEntidadeDWDestino(Entidade entidadeDWDestino) {
        this.entidadeDWDestino = entidadeDWDestino;
    }

    public int getHashEntidadeSAOrigem() {
        return hashEntidadeSAOrigem;
    }

    public void setHashEntidadeSAOrigem(int hashEntidadeSAOrigem) {
        this.hashEntidadeSAOrigem = hashEntidadeSAOrigem;
    }

    public int getHashEntidadeDWDestino() {
        return hashEntidadeDWDestino;
    }

    public void setHashEntidadeDWDestino(int hashEntidadeDWDestino) {
        this.hashEntidadeDWDestino = hashEntidadeDWDestino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.entidadeSAOrigem);
        hash = 97 * hash + Objects.hashCode(this.entidadeDWDestino);
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
        final MapeamentoDW other = (MapeamentoDW) obj;
        if (!Objects.equals(this.entidadeSAOrigem, other.entidadeSAOrigem)) {
            return false;
        }
        if (!Objects.equals(this.entidadeDWDestino, other.entidadeDWDestino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return entidadeSAOrigem.getNome() + " -> " + entidadeDWDestino.getNome();
    }

    public MapeamentoDW Copia(){        

        MapeamentoDW copiaMapeamentoDW = new MapeamentoDW();
        
        copiaMapeamentoDW.setEntidadeSAOrigem(this.entidadeSAOrigem==null?null:this.entidadeSAOrigem.copia());
        copiaMapeamentoDW.setEntidadeDWDestino(this.entidadeDWDestino==null?null:this.entidadeDWDestino.copia());

        return copiaMapeamentoDW;
    }
    
}
