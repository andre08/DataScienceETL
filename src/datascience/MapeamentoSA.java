package datascience;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapeamentoSA {
    
    private Consulta consultaOrigem;
    private Entidade entidadeSADestino;
    private List<MapeamentoAtributo> mapeamentosAtributos;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashConsulta;
    private int hashEntidade;

    public MapeamentoSA() {
        this.consultaOrigem = new Consulta(); 
        this.entidadeSADestino = new Entidade();
        this.mapeamentosAtributos = new ArrayList<MapeamentoAtributo>();
    }

    public Consulta getConsultaOrigem() {
        return consultaOrigem;
    }

    public void setConsultaOrigem(Consulta consultaOrigem) {
        this.consultaOrigem = consultaOrigem;
    }

    public Entidade getEntidadeSADestino() {
        return entidadeSADestino;
    }

    public void setEntidadeSADestino(Entidade entidadeSADestino) {
        this.entidadeSADestino = entidadeSADestino;
    }

    public int getHashConsulta() {
        return hashConsulta;
    }

    public void setHashConsulta(int hashConsulta) {
        this.hashConsulta = hashConsulta;
    }

    public int getHashEntidade() {
        return hashEntidade;
    }

    public void setHashEntidade(int hashEntidade) {
        this.hashEntidade = hashEntidade;
    }

    public List<MapeamentoAtributo> getMapeamentosAtributos() {
        return mapeamentosAtributos;
    }

    public void setMapeamentosAtributos(List<MapeamentoAtributo> mapeamentosAtributos) {
        this.mapeamentosAtributos = mapeamentosAtributos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.consultaOrigem);
        hash = 47 * hash + Objects.hashCode(this.entidadeSADestino);
        hash = 47 * hash + Objects.hashCode(this.mapeamentosAtributos);
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
        final MapeamentoSA other = (MapeamentoSA) obj;
        if (!Objects.equals(this.consultaOrigem, other.consultaOrigem)) {
            return false;
        }
        if (!Objects.equals(this.entidadeSADestino, other.entidadeSADestino)) {
            return false;
        }
        if (!Objects.equals(this.mapeamentosAtributos, other.mapeamentosAtributos)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return consultaOrigem.getNome() + " -> " + entidadeSADestino.getNome();
    }

    public MapeamentoSA Copia(){        

        MapeamentoSA copiaMapeamentoSA = new MapeamentoSA();
        copiaMapeamentoSA.setConsultaOrigem(this.consultaOrigem==null?null:this.consultaOrigem.copia());
        copiaMapeamentoSA.setEntidadeSADestino(this.entidadeSADestino==null?null:this.entidadeSADestino.copia());

        List<MapeamentoAtributo> copiaMapas = new ArrayList<MapeamentoAtributo>();
        for (MapeamentoAtributo mapa : this.getMapeamentosAtributos()) {
            copiaMapas.add(mapa.copia());
        }
        copiaMapeamentoSA.setMapeamentosAtributos(copiaMapas);

        return copiaMapeamentoSA;

    }
    
}
