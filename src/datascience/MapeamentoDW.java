package datascience;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapeamentoDW{
    
    private Entidade entidadeSAOrigem;
    private Entidade entidadeDWDestino;
    private List<MapeamentoAtributo> mapeamentosAtributos;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashEntidadeSAOrigem;
    private int hashEntidadeDWDestino;

    public MapeamentoDW() {
        this.entidadeSAOrigem = new Entidade();
        this.entidadeDWDestino = new Entidade();
        this.mapeamentosAtributos = new ArrayList<MapeamentoAtributo>();
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

    public List<MapeamentoAtributo> getMapeamentosAtributos() {
        return mapeamentosAtributos;
    }

    public void setMapeamentosAtributos(List<MapeamentoAtributo> mapeamentosAtributos) {
        this.mapeamentosAtributos = mapeamentosAtributos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.entidadeSAOrigem);
        hash = 89 * hash + Objects.hashCode(this.entidadeDWDestino);
        hash = 89 * hash + Objects.hashCode(this.mapeamentosAtributos);
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
        if (!Objects.equals(this.mapeamentosAtributos, other.mapeamentosAtributos)) {
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

        List<MapeamentoAtributo> copiaMapas = new ArrayList<MapeamentoAtributo>();
        for (MapeamentoAtributo mapa : this.getMapeamentosAtributos()) {
            copiaMapas.add(mapa.copia());
        }
        copiaMapeamentoDW.setMapeamentosAtributos(copiaMapas);
        
        return copiaMapeamentoDW;
    }
    
    public String getSQLCreateCodeOracle(){
        return "";
    }

    public String getSQLCreateCodeMySql(){
        return "";
    }

    public String getSQLCreateCodeMsSqlServer(){
        return "";
    }

}
