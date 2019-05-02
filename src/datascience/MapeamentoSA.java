package datascience;

import java.util.Objects;

public class MapeamentoSA {
    
    private Consulta consultaOrigem;
    private Entidade entidadeDestino;
    //campos para armazenar o hash de relacionamentos e relacionar apos a importação dos dados
    private int hashConsulta;
    private int hashEntidade;

    public MapeamentoSA() {
    }

    public Consulta getConsultaOrigem() {
        return consultaOrigem;
    }

    public void setConsultaOrigem(Consulta consultaOrigem) {
        this.consultaOrigem = consultaOrigem;
    }

    public Entidade getEntidadeDestino() {
        return entidadeDestino;
    }

    public void setEntidadeDestino(Entidade entidadeDestino) {
        this.entidadeDestino = entidadeDestino;
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.consultaOrigem);
        hash = 73 * hash + Objects.hashCode(this.entidadeDestino);
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
        if (!Objects.equals(this.entidadeDestino, other.entidadeDestino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return consultaOrigem.getNome() + " -> " + entidadeDestino.getNome();
    }

    public MapeamentoSA copia(){        

        MapeamentoSA copiaMapeamentoSA = new MapeamentoSA();
        copiaMapeamentoSA.setConsultaOrigem(this.consultaOrigem==null?null:this.consultaOrigem.copia());
        copiaMapeamentoSA.setEntidadeDestino(this.entidadeDestino==null?null:this.entidadeDestino.copia());

        return copiaMapeamentoSA;

    }
    
}
