package datascience;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Controle extends Object implements Cloneable {

    //arquivos de controles
    private String Pendente;

    //itens 
    private List<Conexao> conexoes;
    private List<Consulta> consultas;
    private List<Entidade> entidadesSA;
    private List<Entidade> entidadesDW;
    private List<MapeamentoSA> mapeamentosSA;
    private List<MapeamentoDW> mapeamentosDW;

    public Controle() {
        this.Pendente = "S";
        this.conexoes = new ArrayList<Conexao>();
        this.consultas = new ArrayList<Consulta>();
        this.entidadesSA = new ArrayList<Entidade>();
        this.entidadesDW = new ArrayList<Entidade>();
        this.mapeamentosSA = new ArrayList<MapeamentoSA>();
        this.mapeamentosDW = new ArrayList<MapeamentoDW>();
    }

    public void addConexao(Conexao conexaoAnterior, Conexao conexaoNova) {
        this.Pendente = "S";
        if (conexaoAnterior == null) {
            this.conexoes.add(conexaoNova);
        } else {
            this.conexoes.set(this.conexoes.indexOf(conexaoAnterior), conexaoNova);
            // atualizando a conexões nas consultas
            for (Consulta consulta : this.consultas) {
                if (consulta.getConexao().equals(conexaoAnterior)) {
                    consulta.setConexao(conexaoNova);
                    int indice = this.consultas.indexOf(consulta);
                    this.consultas.set(indice, consulta);
                }
            }
            // atualizando a conexões nas entidades Staging Area
            for (Entidade entidade : this.entidadesSA) {
                if (entidade.getConexao().equals(conexaoAnterior)) {
                    entidade.setConexao(conexaoNova);
                    int indice = this.entidadesSA.indexOf(entidade);
                    this.entidadesSA.set(indice, entidade);
                }
            }
            // atualizando a conexões nas entidades Staging Area
            for (Entidade entidade : this.entidadesDW) {
                if (entidade.getConexao().equals(conexaoAnterior)) {
                    entidade.setConexao(conexaoNova);
                    int indice = this.entidadesDW.indexOf(entidade);
                    this.entidadesSA.set(indice, entidade);
                }
            }
        }
    }

    public void addConsulta(Consulta consultaAnterior, Consulta consultaNova) {
        this.Pendente = "S";
        if (consultaAnterior == null) {
            this.consultas.add(consultaNova);
        } else {
            this.consultas.set(this.consultas.indexOf(consultaAnterior), consultaNova);
        }
        //atualizando as consultas nos mapeamentos
        for (MapeamentoSA mapaMapeamentoSA : this.mapeamentosSA) {
            if (mapaMapeamentoSA.getConsultaOrigem().equals(consultaAnterior)) {
                mapaMapeamentoSA.setConsultaOrigem(consultaNova);
            }
        }
    }

    public void AddEntidadeSA(Entidade entidadeAnterior, Entidade entidadeNova) {
        this.Pendente = "S";
        if (entidadeAnterior == null) {
            this.entidadesSA.add(entidadeNova);
        } else {
            this.entidadesSA.set(this.entidadesSA.indexOf(entidadeAnterior), entidadeNova);
        }
        //atualizando as entidade nos mapeamentos
        for (MapeamentoSA mapaMapeamentoSA : this.mapeamentosSA) {
            if (mapaMapeamentoSA.getEntidadeDestino().equals(entidadeAnterior)) {
                mapaMapeamentoSA.setEntidadeDestino(entidadeNova);
            }
        }
        //atualizando as entidade nos mapeamentos
        for (MapeamentoDW mapaMapeamentoDW : this.mapeamentosDW) {
            if (mapaMapeamentoDW.getEntidadeSAOrigem().equals(entidadeAnterior)) {
                mapaMapeamentoDW.setEntidadeSAOrigem(entidadeNova);
            }
        }
    }

    public void AddEntidadeDW(Entidade entidadeAnterior, Entidade entidadeNova) {
        this.Pendente = "S";
        if (entidadeAnterior == null) {
            this.entidadesDW.add(entidadeNova);
        } else {
            this.entidadesDW.set(this.entidadesDW.indexOf(entidadeAnterior), entidadeNova);
        }
        //atualizando as entidade nos mapeamentos
        for (MapeamentoDW mapaMapeamentoDW : this.mapeamentosDW) {
            if (mapaMapeamentoDW.getEntidadeDWDestino().equals(entidadeAnterior)) {
                mapaMapeamentoDW.setEntidadeDWDestino(entidadeNova);
            }
        }
    }

    public void AddMapeamentoSA(MapeamentoSA mapeamentoAnterior, MapeamentoSA mapeamentoNova) {
        this.Pendente = "S";
        if (mapeamentoAnterior == null) {
            this.mapeamentosSA.add(mapeamentoNova);
        } else {
            this.mapeamentosSA.set(this.mapeamentosSA.indexOf(mapeamentoAnterior), mapeamentoNova);
        }
    }

    public void AddMapeamentoDW(MapeamentoDW mapeamentoAnterior, MapeamentoDW mapeamentoNova) {
        this.Pendente = "S";
        if (mapeamentoAnterior == null) {
            this.mapeamentosDW.add(mapeamentoNova);
        } else {
            this.mapeamentosDW.set(this.mapeamentosDW.indexOf(mapeamentoAnterior), mapeamentoNova);
        }
    }

    public void RefazerRelacionamento() {

        //Localizando e refazendo relacionamento de conexão
        for (Conexao conexao : this.conexoes) {
            //verificando as conexões das consultas
            for (Consulta consulta : this.consultas) {
                if (consulta.getHashConexao() == conexao.hashCode()) {
                    consulta.setConexao(conexao);
                }
            }
            //verificando as conexões da entidade das consultas
            for (Consulta consulta : this.consultas) {
                if (consulta.getEntidade().getHashConexao() == conexao.hashCode()) {
                    consulta.getEntidade().setConexao(conexao);
                }
            }
            //verificando as conexões da entidade SA
            for (Entidade entidadeSA : this.entidadesSA) {
                if (entidadeSA.getHashConexao() == conexao.hashCode()) {
                    entidadeSA.setConexao(conexao);
                }
            }
            //verificando as conexões da entidade DW
            for (Entidade entidadeDW : this.entidadesDW) {
                if (entidadeDW.getHashConexao() == conexao.hashCode()) {
                    entidadeDW.setConexao(conexao);
                }
            }
        }

        //Localizando e regazendo consultas do Mapeamentos SA
        for (Consulta consulta : this.consultas) {
            for (MapeamentoSA mapeamentoSA : this.mapeamentosSA) {
                if (mapeamentoSA.getHashConsulta() == consulta.hashCode()) {
                    mapeamentoSA.setConsultaOrigem(consulta);
                }
            }
        }

        //Localizando e refazendo relacionamento das Entidades SA
        for (Entidade entidade : this.entidadesSA) {
            for (MapeamentoSA mapeamentoSA : this.mapeamentosSA) {
                if (mapeamentoSA.getHashEntidade() == entidade.hashCode()) {
                    mapeamentoSA.setEntidadeDestino(entidade);
                }
            }
            for (MapeamentoDW mapeamentoDW : this.mapeamentosDW) {
                if (mapeamentoDW.getHashEntidadeSAOrigem() == entidade.hashCode()) {
                    mapeamentoDW.setEntidadeSAOrigem(entidade);
                }
            }
        }

        //Localizando e refazendo relacionamento das Entidades SA
        for (Entidade entidade : this.entidadesDW) {
            for (MapeamentoDW mapeamentoDW : this.mapeamentosDW) {
                if (mapeamentoDW.getHashEntidadeDWDestino() == entidade.hashCode()) {
                    mapeamentoDW.setEntidadeDWDestino(entidade);
                }
            }
        }

        //verificando entidades de consulta e relacionamentos
        for (Consulta consultaOrigem : this.consultas) {
            for (Atributo atributoOrigem : consultaOrigem.getEntidade().getAtributos()) {
                for (Consulta consultaReferencia : this.consultas) {
                    if (atributoOrigem.getHashReferenciaEntidade() == consultaReferencia.getEntidade().hashCode()) {
                        atributoOrigem.setReferenciaEntidade(consultaReferencia.getEntidade());
                        for (Atributo atributoDestino : consultaReferencia.getEntidade().getAtributos()) {
                            if (atributoOrigem.getHashReferenciaAtributo() == atributoDestino.hashCode()) {
                                atributoOrigem.setReferenciaAtributo(atributoDestino);
                            }
                        }
                    }
                }
            }
        }

        //verificando entidades SA e relacionamentos
        for (Entidade entidadeOrigem : this.entidadesSA) {
            for (Atributo atributoOrigem : entidadeOrigem.getAtributos()) {
                for (Entidade entidadeReferencia : this.entidadesSA) {
                    if (atributoOrigem.getHashReferenciaEntidade() == entidadeReferencia.hashCode()) {
                        atributoOrigem.setReferenciaEntidade(entidadeReferencia);
                        for (Atributo atributoDestino : entidadeReferencia.getAtributos()) {
                            if (atributoOrigem.getHashReferenciaAtributo() == atributoDestino.hashCode()) {
                                atributoOrigem.setReferenciaAtributo(atributoDestino);
                            }
                        }
                    }
                }
            }
        }

        //verificando entidades DW e relacionamentos
        for (Entidade entidadeOrigem : this.entidadesDW) {
            for (Atributo atributoOrigem : entidadeOrigem.getAtributos()) {
                for (Entidade entidadeReferencia : this.entidadesDW) {
                    if (atributoOrigem.getHashReferenciaEntidade() == entidadeReferencia.hashCode()) {
                        atributoOrigem.setReferenciaEntidade(entidadeReferencia);
                        for (Atributo atributoDestino : entidadeReferencia.getAtributos()) {
                            if (atributoOrigem.getHashReferenciaAtributo() == atributoDestino.hashCode()) {
                                atributoOrigem.setReferenciaAtributo(atributoDestino);
                            }
                        }
                    }
                }
            }
        }

    }

    public List<Conexao> getConexoes() {
        return conexoes;
    }

    public void setConexoes(List<Conexao> conexoes) {
        this.Pendente = "S";
        this.conexoes = conexoes;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.Pendente = "S";
        this.consultas = consultas;
    }

    public List<Entidade> getEntidadesSA() {
        return entidadesSA;
    }

    public void setEntidadesSA(List<Entidade> entidadesSA) {
        this.Pendente = "S";
        this.entidadesSA = entidadesSA;
    }

    public List<Entidade> getEntidadesDW() {
        return entidadesDW;
    }

    public void setEntidadesDW(List<Entidade> entidadesDW) {
        this.Pendente = "S";
        this.entidadesDW = entidadesDW;
    }

    public List<MapeamentoSA> getMapeamentosSA() {
        return mapeamentosSA;
    }

    public void setMapeamentosSA(List<MapeamentoSA> mapeamentoSA) {
        this.mapeamentosSA = mapeamentoSA;
    }

    public List<MapeamentoDW> getMapeamentosDW() {
        return mapeamentosDW;
    }

    public void setMapeamentosDW(List<MapeamentoDW> mapeamentoDW) {
        this.mapeamentosDW = mapeamentoDW;
    }

    public void NovoJson() {

        /*inicializando lista de conexões*/
        this.conexoes = new ArrayList<Conexao>();

        /*inicializando lista de consultas*/
        this.consultas = new ArrayList<Consulta>();

        /*inicializando lista de entidades Staging Area*/
        this.entidadesSA = new ArrayList<Entidade>();

        /*inicializando lista de entidades Data Warehose*/
        this.entidadesDW = new ArrayList<Entidade>();

        /*inicializando lista de Mapeamentos Staging Area*/
        this.mapeamentosSA = new ArrayList<MapeamentoSA>();

        /*inicializando lista de Mapeamentos Data Warehose*/
        this.mapeamentosDW = new ArrayList<MapeamentoDW>();

        /*inicializando variaveis de controle*/
        Pendente = "N";

    }

    public void CarregarJson(Frame parent) {

        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.LOAD);
        localizarArquivo.setDirectory("C:\\Users\\Pessoal\\Documents\\NetBeansProjects\\DataScienceETL\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.pack();
        localizarArquivo.setLocationRelativeTo(null);
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();
        System.out.println(localizarArquivo.getDirectory());

        if ((localizarArquivo.getDirectory() != null) && (localizarArquivo.getFile() != null)) {

            String conteudoJson = "";
            try {
                conteudoJson = new String(Files.readAllBytes(Paths.get(nomearquivo)));

            } catch (IOException ex) {
                Logger.getLogger(Controle.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject projetoJson = new JSONObject(conteudoJson);

            /**
             * Carregando Conexão
             */
            JSONArray JsonArrayConexao;
            JSONObject JsonObjConexao;

            JsonArrayConexao = projetoJson.getJSONArray("CONEXAO");
            for (int i = 0; i < JsonArrayConexao.length(); i++) {

                JsonObjConexao = (JSONObject) JsonArrayConexao.get(i);
                Conexao conexao = new Conexao();
                conexao.setNome(JsonObjConexao.getString("NOME"));
                conexao.setDescricao(JsonObjConexao.getString("DESCRICAO"));
                conexao.setUrl(JsonObjConexao.getString("URL"));
                conexao.setPorta(JsonObjConexao.getInt("PORTA"));
                conexao.setNomeBanco(JsonObjConexao.getString("NOMEBANCO"));
                conexao.setSID(JsonObjConexao.getString("SID"));
                conexao.setUsename(JsonObjConexao.getString("USUARIO"));
                conexao.setPassword(JsonObjConexao.getString("SENHA"));
                conexao.setSGDB(JsonObjConexao.getString("SGDB"));
                conexao.setObjetivo(JsonObjConexao.getString("OBJETIVO"));

                this.addConexao(null, conexao);

            }

            /**
             * Carregando Consultas
             */
            JSONArray JsonArrayConsulta;
            JSONObject JsonObjConsulta;

            JsonArrayConsulta = projetoJson.getJSONArray("CONSULTA");
            for (int i = 0; i < JsonArrayConsulta.length(); i++) {

                JsonObjConsulta = (JSONObject) JsonArrayConsulta.get(i);
                Consulta consulta = new Consulta();
                consulta.setNome(JsonObjConsulta.getString("NOME"));
                consulta.setDescricao(JsonObjConsulta.getString("DESCRICAO"));
                consulta.setSql(JsonObjConsulta.getString("SQL"));

                //entidade gerada pela consulta
                JSONObject JsonObjEntidadeConsulta = (JSONObject) JsonObjConsulta.getJSONObject("ENTIDADE");
                Entidade entidadeConsulta = new Entidade();
                entidadeConsulta.setNome(JsonObjEntidadeConsulta.getString("NOME"));
                entidadeConsulta.setDescricao(JsonObjEntidadeConsulta.getString("DESCRICAO"));
                entidadeConsulta.setHashConexao(JsonObjEntidadeConsulta.getInt("CONEXAO"));

                JSONArray JsonArrayAtributoConsulta = JsonObjEntidadeConsulta.getJSONArray("ATRIBUTOS");
                List<Atributo> atributosConsulta = new ArrayList<Atributo>();

                for (int j = 0; j < JsonArrayAtributoConsulta.length(); j++) {
                    JSONObject JsonObjAtrinbutoConsulta = (JSONObject) JsonArrayAtributoConsulta.get(j);
                    Atributo atributoConsulta = new Atributo();
                    atributoConsulta.setNome(JsonObjAtrinbutoConsulta.getString("NOME"));
                    atributoConsulta.setDescricao(JsonObjAtrinbutoConsulta.getString("DESCRICAO"));
                    atributoConsulta.setTipo(JsonObjAtrinbutoConsulta.getString("TIPO"));
                    atributoConsulta.setTamanho(JsonObjAtrinbutoConsulta.getInt("TAMANHO"));
                    atributoConsulta.setPrecisao(JsonObjAtrinbutoConsulta.getInt("PRECISAO"));
                    atributoConsulta.setObrigatorio(JsonObjAtrinbutoConsulta.getString("OBRIGATORIO"));
                    atributoConsulta.setChavePrimaria(JsonObjAtrinbutoConsulta.getString("CHAVEPRIMARIA"));
                    atributoConsulta.setValorSequencial(JsonObjAtrinbutoConsulta.getString("VALORSEQUENCIAL"));
                    atributoConsulta.setObservacao(JsonObjAtrinbutoConsulta.getString("OBSERVACAO"));
                    atributoConsulta.setChaveEstrangeira(JsonObjAtrinbutoConsulta.getString("CHAVEESTRANGEIRA"));
                    atributoConsulta.setHashReferenciaEntidade(JsonObjAtrinbutoConsulta.getInt("REFERENCIAENTIDADE"));
                    atributoConsulta.setHashReferenciaAtributo(JsonObjAtrinbutoConsulta.getInt("REFERENCIAATRIBUTO"));
                    atributosConsulta.add(atributoConsulta);
                }
                entidadeConsulta.setAtributos(atributosConsulta);
                consulta.setEntidade(entidadeConsulta);
                //conexão com a consulta
                consulta.setHashConexao(JsonObjConsulta.getInt("CONEXAO"));

                this.addConsulta(null, consulta);

            }

            /**
             * Carregando EntidadeSA
             */
            JSONArray JsonArrayEntidadeSA;
            JSONObject JsonObjEntidadeSA;

            JsonArrayEntidadeSA = projetoJson.getJSONArray("ENTIDADE SA");
            for (int i = 0; i < JsonArrayEntidadeSA.length(); i++) {
                JsonObjEntidadeSA = (JSONObject) JsonArrayEntidadeSA.get(i);

                Entidade entidadeSA = new Entidade();
                entidadeSA.setNome(JsonObjEntidadeSA.getString("NOME"));
                entidadeSA.setDescricao(JsonObjEntidadeSA.getString("DESCRICAO"));
                entidadeSA.setHashConexao(JsonObjEntidadeSA.getInt("CONEXAO"));

                JSONArray JsonArrayAtributoEntidadeSA = JsonObjEntidadeSA.getJSONArray("ATRIBUTOS");
                List<Atributo> atributosEntidadeSA = new ArrayList<Atributo>();

                for (int j = 0; j < JsonArrayAtributoEntidadeSA.length(); j++) {
                    JSONObject JsonObjAtributoEntidadeSA = (JSONObject) JsonArrayAtributoEntidadeSA.get(j);
                    Atributo atributoEntidadeSA = new Atributo();
                    atributoEntidadeSA.setNome(JsonObjAtributoEntidadeSA.getString("NOME"));
                    atributoEntidadeSA.setDescricao(JsonObjAtributoEntidadeSA.getString("DESCRICAO"));
                    atributoEntidadeSA.setTipo(JsonObjAtributoEntidadeSA.getString("TIPO"));
                    atributoEntidadeSA.setTamanho(JsonObjAtributoEntidadeSA.getInt("TAMANHO"));
                    atributoEntidadeSA.setPrecisao(JsonObjAtributoEntidadeSA.getInt("PRECISAO"));
                    atributoEntidadeSA.setObrigatorio(JsonObjAtributoEntidadeSA.getString("OBRIGATORIO"));
                    atributoEntidadeSA.setChavePrimaria(JsonObjAtributoEntidadeSA.getString("CHAVEPRIMARIA"));
                    atributoEntidadeSA.setValorSequencial(JsonObjAtributoEntidadeSA.getString("VALORSEQUENCIAL"));
                    atributoEntidadeSA.setObservacao(JsonObjAtributoEntidadeSA.getString("OBSERVACAO"));
                    atributoEntidadeSA.setChaveEstrangeira(JsonObjAtributoEntidadeSA.getString("CHAVEESTRANGEIRA"));
                    atributoEntidadeSA.setHashReferenciaEntidade(JsonObjAtributoEntidadeSA.getInt("REFERENCIAENTIDADE"));
                    atributoEntidadeSA.setHashReferenciaAtributo(JsonObjAtributoEntidadeSA.getInt("REFERENCIAATRIBUTO"));
                    atributosEntidadeSA.add(atributoEntidadeSA);
                }
                entidadeSA.setAtributos(atributosEntidadeSA);

                this.AddEntidadeSA(null, entidadeSA);

            }

            /**
             * Carregando EntidadeDW
             */
            JSONArray JsonArrayEntidadeDW;
            JSONObject JsonObjEntidadeDW;

            JsonArrayEntidadeDW = projetoJson.getJSONArray("ENTIDADE DW");
            for (int i = 0; i < JsonArrayEntidadeDW.length(); i++) {
                JsonObjEntidadeDW = (JSONObject) JsonArrayEntidadeDW.get(i);

                Entidade entidadeDW = new Entidade();
                entidadeDW.setNome(JsonObjEntidadeDW.getString("NOME"));
                entidadeDW.setDescricao(JsonObjEntidadeDW.getString("DESCRICAO"));
                entidadeDW.setHashConexao(JsonObjEntidadeDW.getInt("CONEXAO"));

                JSONArray JsonArrayAtributoEntidadeDW = JsonObjEntidadeDW.getJSONArray("ATRIBUTOS");
                List<Atributo> atributosEntidadeDW = new ArrayList<Atributo>();

                for (int j = 0; j < JsonArrayAtributoEntidadeDW.length(); j++) {
                    JSONObject JsonObjAtributoEntidadeDW = (JSONObject) JsonArrayAtributoEntidadeDW.get(j);
                    Atributo atributoEntidadeDW = new Atributo();
                    atributoEntidadeDW.setNome(JsonObjAtributoEntidadeDW.getString("NOME"));
                    atributoEntidadeDW.setDescricao(JsonObjAtributoEntidadeDW.getString("DESCRICAO"));
                    atributoEntidadeDW.setTipo(JsonObjAtributoEntidadeDW.getString("TIPO"));
                    atributoEntidadeDW.setTamanho(JsonObjAtributoEntidadeDW.getInt("TAMANHO"));
                    atributoEntidadeDW.setPrecisao(JsonObjAtributoEntidadeDW.getInt("PRECISAO"));
                    atributoEntidadeDW.setObrigatorio(JsonObjAtributoEntidadeDW.getString("OBRIGATORIO"));
                    atributoEntidadeDW.setChavePrimaria(JsonObjAtributoEntidadeDW.getString("CHAVEPRIMARIA"));
                    atributoEntidadeDW.setValorSequencial(JsonObjAtributoEntidadeDW.getString("VALORSEQUENCIAL"));
                    atributoEntidadeDW.setObservacao(JsonObjAtributoEntidadeDW.getString("OBSERVACAO"));
                    atributoEntidadeDW.setChaveEstrangeira(JsonObjAtributoEntidadeDW.getString("CHAVEESTRANGEIRA"));
                    atributoEntidadeDW.setHashReferenciaEntidade(JsonObjAtributoEntidadeDW.getInt("REFERENCIAENTIDADE"));
                    atributoEntidadeDW.setHashReferenciaAtributo(JsonObjAtributoEntidadeDW.getInt("REFERENCIAATRIBUTO"));
                    atributosEntidadeDW.add(atributoEntidadeDW);
                }
                entidadeDW.setAtributos(atributosEntidadeDW);

                this.AddEntidadeDW(null, entidadeDW);

            }

            /**
             * Carregando MapeamentoSA
             */
            JSONArray JsonArrayMapeamentoSA;
            JSONObject JsonObjMapeamentoSA;

            JsonArrayMapeamentoSA = projetoJson.getJSONArray("MAPEAMENTO SA");
            for (int i = 0; i < JsonArrayMapeamentoSA.length(); i++) {
                JsonObjMapeamentoSA = (JSONObject) JsonArrayMapeamentoSA.get(i);

                MapeamentoSA mapeamentoSA = new MapeamentoSA();
                mapeamentoSA.setHashConsulta(JsonObjMapeamentoSA.getInt("CONSULTAORIGEM"));
                mapeamentoSA.setHashEntidade(JsonObjMapeamentoSA.getInt("ENTIDADEDESTINO"));

                this.AddMapeamentoSA(null, mapeamentoSA);
            }

            /**
             * Carregando MapeamentoDW
             */
            JSONArray JsonArrayMapeamentoDW;
            JSONObject JsonObjMapeamentoDW;

            JsonArrayMapeamentoDW = projetoJson.getJSONArray("MAPEAMENTO DW");
            for (int i = 0; i < JsonArrayMapeamentoDW.length(); i++) {
                JsonObjMapeamentoDW = (JSONObject) JsonArrayMapeamentoDW.get(i);

                MapeamentoDW mapeamentoDW = new MapeamentoDW();
                mapeamentoDW.setHashEntidadeSAOrigem(JsonObjMapeamentoDW.getInt("ENTIDADESAORIGEM"));
                mapeamentoDW.setHashEntidadeDWDestino(JsonObjMapeamentoDW.getInt("ENTIDADEDWDESTINO"));

                this.AddMapeamentoDW(null, mapeamentoDW);
            }
            this.RefazerRelacionamento();
            this.Pendente = "N";

        }

    }

    public void SalvarJson(Frame parent) {

        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.SAVE);
        localizarArquivo.setDirectory("C:\\Users\\Pessoal\\Documents\\NetBeansProjects\\DataScienceETL\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.pack();
        localizarArquivo.setLocationRelativeTo(null);
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();

        if ((localizarArquivo.getDirectory() != null) && (localizarArquivo.getFile() != null)) {

            JSONObject arquivoJson = new JSONObject();

            /**
             * Salvando Projeto
             */
            JSONObject projetoJson = new JSONObject();
            arquivoJson.put("PROJETO", projetoJson);

            /**
             * Salvando Conexão
             */
            JSONArray JsonArrayConexao = new JSONArray();
            JSONObject JsonObjConexao;
            for (Conexao conexao : this.conexoes) {

                JsonObjConexao = new JSONObject();
                JsonObjConexao.put("NOME", conexao.getNome());
                JsonObjConexao.put("DESCRICAO", conexao.getDescricao());
                JsonObjConexao.put("URL", conexao.getUrl());
                JsonObjConexao.put("PORTA", conexao.getPorta());
                JsonObjConexao.put("NOMEBANCO", conexao.getNomeBanco());
                JsonObjConexao.put("SID", conexao.getSID());
                JsonObjConexao.put("USUARIO", conexao.getUsename());
                JsonObjConexao.put("SENHA", conexao.getPassword());
                JsonObjConexao.put("SGDB", conexao.getSGDB());
                JsonObjConexao.put("OBJETIVO", conexao.getObjetivo());

                JsonArrayConexao.put(JsonObjConexao);
            }
            arquivoJson.put("CONEXAO", JsonArrayConexao);

            /**
             * Salvando Consultas
             */
            JSONArray JsonArrayConsulta = new JSONArray();
            JSONObject JsonObjConsulta;

            for (Consulta consulta : this.consultas) {

                JsonObjConsulta = new JSONObject();
                JsonObjConsulta.put("NOME", consulta.getNome());
                JsonObjConsulta.put("DESCRICAO", consulta.getDescricao());
                JsonObjConsulta.put("SQL", consulta.getSql());
                JsonObjConsulta.put("CONEXAO", consulta.getConexao().hashCode());

                //Entidade da consulta
                Entidade entidadeConsulta = consulta.getEntidade();
                JSONObject JsonObjEntidadeConsulta = new JSONObject();
                JsonObjEntidadeConsulta.put("NOME", entidadeConsulta.getNome());
                JsonObjEntidadeConsulta.put("DESCRICAO", entidadeConsulta.getDescricao());
                JsonObjEntidadeConsulta.put("CONEXAO", entidadeConsulta.getConexao().hashCode());

                //Atributos da entidade da consulta
                JSONArray JsonArrayAtributoEntidadeConsulta = new JSONArray();
                for (Atributo atributoConsulta : entidadeConsulta.getAtributos()) {
                    JSONObject JsonObjAtributoEntidadeConsulta = new JSONObject();
                    JsonObjAtributoEntidadeConsulta.put("NOME", atributoConsulta.getNome());
                    JsonObjAtributoEntidadeConsulta.put("DESCRICAO", atributoConsulta.getDescricao());
                    JsonObjAtributoEntidadeConsulta.put("TIPO", atributoConsulta.getTipo());
                    JsonObjAtributoEntidadeConsulta.put("TAMANHO", atributoConsulta.getTamanho());
                    JsonObjAtributoEntidadeConsulta.put("PRECISAO", atributoConsulta.getPrecisao());
                    JsonObjAtributoEntidadeConsulta.put("OBRIGATORIO", atributoConsulta.getObrigatorio());
                    JsonObjAtributoEntidadeConsulta.put("CHAVEPRIMARIA", atributoConsulta.getChavePrimaria());
                    JsonObjAtributoEntidadeConsulta.put("VALORSEQUENCIAL", atributoConsulta.getValorSequencial());
                    JsonObjAtributoEntidadeConsulta.put("OBSERVACAO", atributoConsulta.getObservacao());
                    JsonObjAtributoEntidadeConsulta.put("CHAVEESTRANGEIRA", atributoConsulta.getChaveEstrangeira());
                    JsonObjAtributoEntidadeConsulta.put("REFERENCIAENTIDADE", (atributoConsulta.getReferenciaEntidade()==null?0:atributoConsulta.getReferenciaEntidade().hashCode()));
                    JsonObjAtributoEntidadeConsulta.put("REFERENCIAATRIBUTO", (atributoConsulta.getReferenciaAtributo()==null?0:atributoConsulta.getReferenciaAtributo().hashCode()));
                    JsonArrayAtributoEntidadeConsulta.put(JsonObjAtributoEntidadeConsulta);
                }

                JsonObjEntidadeConsulta.put("ATRIBUTOS", JsonArrayAtributoEntidadeConsulta);
                JsonObjConsulta.put("ENTIDADE", JsonObjEntidadeConsulta);

                JsonArrayConsulta.put(JsonObjConsulta);
            }
            arquivoJson.put("CONSULTA", JsonArrayConsulta);

            /**
             * Carregando EntidadeSA
             */
            JSONArray JsonArrayEntidadeSA = new JSONArray();
            JSONObject JsonObjEntidadeSA;

            for (Entidade entidadeSA : this.entidadesSA) {

                JsonObjEntidadeSA = new JSONObject();
                JsonObjEntidadeSA.put("NOME", entidadeSA.getNome());
                JsonObjEntidadeSA.put("DESCRICAO", entidadeSA.getDescricao());
                JsonObjEntidadeSA.put("CONEXAO", entidadeSA.getConexao().hashCode());

                JSONArray JsonArrayAtributoEntidadeSA = new JSONArray();
                if (entidadeSA.getAtributos() != null) {
                    for (Atributo atributoEntidadeSA : entidadeSA.getAtributos()) {

                        JSONObject JsonObjAtributoEntidadeSA = new JSONObject();
                        JsonObjAtributoEntidadeSA.put("NOME", atributoEntidadeSA.getNome());
                        JsonObjAtributoEntidadeSA.put("DESCRICAO", atributoEntidadeSA.getDescricao());
                        JsonObjAtributoEntidadeSA.put("TIPO", atributoEntidadeSA.getTipo());
                        JsonObjAtributoEntidadeSA.put("TAMANHO", atributoEntidadeSA.getTamanho());
                        JsonObjAtributoEntidadeSA.put("PRECISAO", atributoEntidadeSA.getPrecisao());
                        JsonObjAtributoEntidadeSA.put("OBRIGATORIO", atributoEntidadeSA.getObrigatorio());
                        JsonObjAtributoEntidadeSA.put("CHAVEPRIMARIA", atributoEntidadeSA.getChavePrimaria());
                        JsonObjAtributoEntidadeSA.put("VALORSEQUENCIAL", atributoEntidadeSA.getValorSequencial());
                        JsonObjAtributoEntidadeSA.put("OBSERVACAO", atributoEntidadeSA.getObservacao());
                        JsonObjAtributoEntidadeSA.put("CHAVEESTRANGEIRA", atributoEntidadeSA.getChaveEstrangeira());
                        JsonObjAtributoEntidadeSA.put("REFERENCIAENTIDADE", (atributoEntidadeSA.getReferenciaEntidade()==null?0:atributoEntidadeSA.getReferenciaEntidade().hashCode()));
                        JsonObjAtributoEntidadeSA.put("REFERENCIAATRIBUTO", (atributoEntidadeSA.getReferenciaAtributo()==null?0:atributoEntidadeSA.getReferenciaAtributo().hashCode()));

                        JsonArrayAtributoEntidadeSA.put(JsonObjAtributoEntidadeSA);
                    }
                }
                JsonObjEntidadeSA.put("ATRIBUTOS", JsonArrayAtributoEntidadeSA);
                JsonArrayEntidadeSA.put(JsonObjEntidadeSA);
            }
            arquivoJson.put("ENTIDADE SA", JsonArrayEntidadeSA);

            /**
             * Carregando EntidadeDW
             */
            JSONArray JsonArrayEntidadeDW = new JSONArray();
            JSONObject JsonObjEntidadeDW;

            for (Entidade entidadeDW : this.entidadesDW) {

                JsonObjEntidadeDW = new JSONObject();
                JsonObjEntidadeDW.put("NOME", entidadeDW.getNome());
                JsonObjEntidadeDW.put("DESCRICAO", entidadeDW.getDescricao());
                JsonObjEntidadeDW.put("CONEXAO", entidadeDW.getConexao().hashCode());

                JSONArray JsonArrayAtributoEntidadeDW = new JSONArray();
                if (entidadeDW.getAtributos() != null) {
                    for (Atributo atributoEntidadeDW : entidadeDW.getAtributos()) {

                        JSONObject JsonObjAtributoEntidadeDW = new JSONObject();
                        JsonObjAtributoEntidadeDW.put("NOME", atributoEntidadeDW.getNome());
                        JsonObjAtributoEntidadeDW.put("DESCRICAO", atributoEntidadeDW.getDescricao());
                        JsonObjAtributoEntidadeDW.put("TIPO", atributoEntidadeDW.getTipo());
                        JsonObjAtributoEntidadeDW.put("TAMANHO", atributoEntidadeDW.getTamanho());
                        JsonObjAtributoEntidadeDW.put("PRECISAO", atributoEntidadeDW.getPrecisao());
                        JsonObjAtributoEntidadeDW.put("OBRIGATORIO", atributoEntidadeDW.getObrigatorio());
                        JsonObjAtributoEntidadeDW.put("CHAVEPRIMARIA", atributoEntidadeDW.getChavePrimaria());
                        JsonObjAtributoEntidadeDW.put("VALORSEQUENCIAL", atributoEntidadeDW.getValorSequencial());
                        JsonObjAtributoEntidadeDW.put("OBSERVACAO", atributoEntidadeDW.getObservacao());
                        JsonObjAtributoEntidadeDW.put("CHAVEESTRANGEIRA", atributoEntidadeDW.getChaveEstrangeira());
                        JsonObjAtributoEntidadeDW.put("REFERENCIAENTIDADE", (atributoEntidadeDW.getReferenciaEntidade()==null?0:atributoEntidadeDW.getReferenciaEntidade().hashCode()));
                        JsonObjAtributoEntidadeDW.put("REFERENCIAATRIBUTO", (atributoEntidadeDW.getReferenciaAtributo()==null?0:atributoEntidadeDW.getReferenciaAtributo().hashCode()));

                        JsonArrayAtributoEntidadeDW.put(JsonObjAtributoEntidadeDW);
                    }
                }
                JsonObjEntidadeDW.put("ATRIBUTOS", JsonArrayAtributoEntidadeDW);
                JsonArrayEntidadeDW.put(JsonObjEntidadeDW);
            }
            arquivoJson.put("ENTIDADE DW", JsonArrayEntidadeDW);

            /**
             * Carregando Mapeamento SA
             */
            JSONArray JsonArrayMapeamentoSA = new JSONArray();
            JSONObject JsonObjMapeamentoSA;

            for (MapeamentoSA mapemaMapeamentoSA : this.mapeamentosSA) {

                JsonObjMapeamentoSA = new JSONObject();
                JsonObjMapeamentoSA.put("CONSULTAORIGEM", mapemaMapeamentoSA.getConsultaOrigem().hashCode());
                JsonObjMapeamentoSA.put("ENTIDADEDESTINO", mapemaMapeamentoSA.getEntidadeDestino().hashCode());

                JsonArrayMapeamentoSA.put(JsonObjMapeamentoSA);
            }
            arquivoJson.put("MAPEAMENTO SA", JsonArrayMapeamentoSA);

            /**
             * Carregando Mapeamento DW
             */
            JSONArray JsonArrayMapeamentoDW = new JSONArray();
            JSONObject JsonObjMapeamentoDW;

            for (MapeamentoDW mapemaMapeamentoDW : this.mapeamentosDW) {

                JsonObjMapeamentoDW = new JSONObject();
                JsonObjMapeamentoDW.put("ENTIDADESAORIGEM", mapemaMapeamentoDW.getEntidadeSAOrigem().hashCode());
                JsonObjMapeamentoDW.put("ENTIDADEDWDESTINO", mapemaMapeamentoDW.getEntidadeDWDestino().hashCode());
                
                JsonArrayMapeamentoDW.put(JsonObjMapeamentoDW);
            }
            arquivoJson.put("MAPEAMENTO DW", JsonArrayMapeamentoDW);
            
            try {
                FileWriter arq;
                arq = new FileWriter(nomearquivo);
                arq.write(arquivoJson.toString());
                arq.close();

            } catch (IOException ex) {
                Logger.getLogger(Controle.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            this.Pendente = "N";

        }
    }

    public void GerarDataMart(Entidade entidade) {

        try {
            FileWriter arq;
            arq = new FileWriter("C:\\temp\\StageArea.sql");
            if (entidade != null) {
                arq.write("CREATE TABLE " + entidade.getNome() + " (" + System.getProperty("line.separator"));

                for (Atributo atributo : entidade.getAtributos()) {
                    arq.write(" " + atributo.getNome() + " " + atributo.getTipo() + System.getProperty("line.separator"));
                }

                arq.write(");");
            }

            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Controle.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
