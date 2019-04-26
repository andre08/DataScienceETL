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

public class Controle  extends Object implements Cloneable{
    
    //arquivos de controles
    private String Pendente;

    //itens 
    private List<Conexao> conexoes;
    private List<Consulta> consultas;
    private List<Entidade> entidadesSA;
    private List<Entidade> entidadesDW;

    public Controle() {
        this.Pendente = "S";
        this.conexoes = new ArrayList<Conexao>();
        this.consultas = new ArrayList<Consulta>();
        this.entidadesSA = new ArrayList<Entidade>();        
        this.entidadesDW = new ArrayList<Entidade>();        
    }
    
    public void addConexao (Conexao conexaoAnterior, Conexao conexaoNova){
        
        if(conexaoAnterior == null){
            this.conexoes.add(conexaoNova);
        }else{
            this.conexoes.set(this.conexoes.indexOf(conexaoAnterior), conexaoNova);
            // atualizando a conexões nas consultas
            for (Consulta consulta : this.consultas) {
                if(consulta.getConexao().equals(conexaoAnterior)){
                    consulta.setConexao(conexaoNova);
                    int indice = this.consultas.indexOf(consulta);
                    this.consultas.set(indice, consulta);
                }
            }
            // atualizando a conexões nas entidades Staging Area
            for (Entidade entidade : this.entidadesSA) {
                if(entidade.getConexao().equals(conexaoAnterior)){
                    entidade.setConexao(conexaoNova);
                    int indice = this.entidadesSA.indexOf(entidade);
                    this.entidadesSA.set(indice, entidade);
                }
            }
            // atualizando a conexões nas entidades Staging Area
            for (Entidade entidade : this.entidadesDW) {
                if(entidade.getConexao().equals(conexaoAnterior)){
                    entidade.setConexao(conexaoNova);
                    int indice = this.entidadesDW.indexOf(entidade);
                    this.entidadesSA.set(indice, entidade);
                }
            }
        }
    }
    
    public void addConsulta (Consulta consultaAnterior, Consulta consultaNova){
        
        if(consultaAnterior == null){
            this.consultas.add(consultaNova);
        }else{
            this.consultas.set(consultas.indexOf(consultaAnterior), consultaNova);
        }
        
    }
    
    public void AddEntidadeSA (Entidade entidade){
        this.Pendente = "S";
        this.entidadesSA.add(entidade);
    }
    
    public void AddEntidadeDW (Entidade entidade){
        this.Pendente = "S";
        this.entidadesDW.add(entidade);
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
    
    public void NovoJson(){
        
        /*inicializando lista de conexões*/
        this.conexoes = new ArrayList<Conexao>();
        
        /*inicializando lista de consultas*/
        this.consultas = new ArrayList<Consulta>();
        
        /*inicializando lista de entidades Staging Area*/
        this.entidadesSA = new ArrayList<Entidade>();        
        
        /*inicializando lista de entidades Data Warehose*/
        this.entidadesDW = new ArrayList<Entidade>();        
        
        /*inicializando variaveis de controle*/
        Pendente = "N";        
        
    }
        
    public void CarregarJson(Frame parent){
        
        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.LOAD);
        localizarArquivo.setDirectory("C:\\Users\\Pessoal\\Documents\\NetBeansProjects\\DataScienceETL\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.pack();
        localizarArquivo.setLocationRelativeTo(null);      
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();
        System.out.println(localizarArquivo.getDirectory());
        if ((localizarArquivo.getDirectory() != null) && (localizarArquivo.getFile() != null)){

            String conteudoJson = "";
            try {
                conteudoJson = new String(Files.readAllBytes(Paths.get(nomearquivo)));
            } catch (IOException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject projetoJson = new JSONObject(conteudoJson);

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
                
                addConexao(null, conexao);

            }            

            JSONArray JsonArrayConsulta;
            JSONObject JsonObjConsulta;
            JsonArrayConsulta = projetoJson.getJSONArray("CONSULTA");

            for (int i = 0; i < JsonArrayConsulta.length(); i++) {

                JsonObjConsulta = (JSONObject) JsonArrayConsulta.get(i);
                Consulta consulta = new Consulta();
                consulta.setNome(JsonObjConsulta.getString("NOME"));
                consulta.setDescricao(JsonObjConsulta.getString("DESCRICAO"));
                consulta.setSql(JsonObjConsulta.getString("CONSULTA"));
                
                JSONObject JsonObjEntidadeConsulta = (JSONObject) JsonObjConsulta.getJSONObject("ENTIDADE") ;
                
                Entidade entidadeConsulta = new Entidade();
                entidadeConsulta.setNome(JsonObjEntidadeConsulta.getString("NOME"));
                entidadeConsulta.setDescricao(JsonObjEntidadeConsulta.getString("DESCRICAO"));
                
                JSONArray JsonArrayAtributoConsulta = JsonObjEntidadeConsulta.getJSONArray("ATRIBUTOS");
                List<Atributo> atributosConsulta = new ArrayList<Atributo>();
                
                for (int j = 0; j < JsonArrayAtributoConsulta.length(); j++) {
                    JSONObject JsonObjAtrinbutoConsulta = (JSONObject) JsonArrayAtributoConsulta.get(j);
                    Atributo atributoConsulta = new Atributo();
                    atributoConsulta.setNome(JsonObjAtrinbutoConsulta.getString("NOME"));
                    atributoConsulta.setDescricao(JsonObjAtrinbutoConsulta.getString("DESCRICAO"));
                    atributoConsulta.setObservacao(JsonObjAtrinbutoConsulta.getString("OBSERVACAO"));
                    atributoConsulta.setTipo(JsonObjAtrinbutoConsulta.getString("TIPO"));
                    atributoConsulta.setTamanho(JsonObjAtrinbutoConsulta.getInt("TAMANHO"));
                    atributoConsulta.setPrecisao(JsonObjAtrinbutoConsulta.getInt("PRECISAO"));
                    atributosConsulta.add(atributoConsulta);
                }
                entidadeConsulta.setAtributos(atributosConsulta);
                
                consulta.setEntidade(entidadeConsulta);

                for (Conexao conexao : conexoes) {
                    
                    if (conexao.hashCode() == JsonObjConsulta.getInt("CONEXAO")){
                        consulta.setConexao(conexao);
                   }
                    
                }
                
                addConsulta(null, consulta);

            }
                        
            JSONArray JsonArrayEntidadeSA;
            JSONArray JsonArrayEntidadeDW;
            JSONArray JsonArrayAtributo;
            JSONObject JsonObjEntidadeSA;
            JSONObject JsonObjEntidadeDW;
            JSONObject JsonObjAtrinbuto;

            JsonArrayEntidadeSA = projetoJson.getJSONArray("ENTIDADE SA");
            for (int i = 0; i < JsonArrayEntidadeSA.length(); i++) {
                JsonObjEntidadeSA = (JSONObject) JsonArrayEntidadeSA.get(i);

                System.out.println(JsonObjEntidadeSA.getString("NOME"));


                JsonArrayAtributo = JsonObjEntidadeSA.getJSONArray("ATRIBUTOS");
                for (int j = 0; j < JsonArrayAtributo.length(); j++) {
                    JsonObjAtrinbuto = (JSONObject) JsonArrayAtributo.get(i);

                    System.out.println(JsonObjAtrinbuto.getString("NOME"));
                    System.out.println(JsonObjAtrinbuto.getString("TIPO"));

                }

            }
            
            JsonArrayEntidadeDW = projetoJson.getJSONArray("ENTIDADE DW");
            for (int i = 0; i < JsonArrayEntidadeDW.length(); i++) {
                JsonObjEntidadeDW = (JSONObject) JsonArrayEntidadeDW.get(i);

                System.out.println(JsonObjEntidadeDW.getString("NOME"));


                JsonArrayAtributo = JsonObjEntidadeDW.getJSONArray("ATRIBUTOS");
                for (int j = 0; j < JsonArrayAtributo.length(); j++) {
                    JsonObjAtrinbuto = (JSONObject) JsonArrayAtributo.get(i);

                    System.out.println(JsonObjAtrinbuto.getString("NOME"));
                    System.out.println(JsonObjAtrinbuto.getString("TIPO"));

                }

            }
            
            this.Pendente = "N";   

        }
        
    }
    
    public void SalvarJson(Frame parent){
        
        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.SAVE);
        localizarArquivo.setDirectory("C:\\Users\\Pessoal\\Documents\\NetBeansProjects\\DataScienceETL\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.pack();
        localizarArquivo.setLocationRelativeTo(null);      
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();

        if ((localizarArquivo.getDirectory() != null) && (localizarArquivo.getFile() != null)){
            JSONObject arquivoJson = new JSONObject();
            JSONObject JsonObjConexao;
            JSONObject JsonObjConsulta;
            JSONObject JsonObjEntidade;
            JSONObject JsonObjAtributo;

            JSONArray projetoJson = new JSONArray();
            JSONArray JsonArrayConexao = new JSONArray();
            JSONArray JsonArrayConsulta = new JSONArray();
            JSONArray JsonArrayEntidadeSA = new JSONArray();
            JSONArray JsonArrayEntidadeDW = new JSONArray();
            JSONArray JsonArrayAtributo = new JSONArray();
            
            for (Conexao conexao: this.conexoes) {

                JsonObjConexao = new JSONObject();
                JsonObjConexao.put("NOME",conexao.getNome());
                JsonObjConexao.put("DESCRICAO",conexao.getDescricao());
                JsonObjConexao.put("URL",conexao.getUrl());
                JsonObjConexao.put("PORTA",conexao.getPorta());
                JsonObjConexao.put("NOMEBANCO",conexao.getNomeBanco());
                JsonObjConexao.put("SID",conexao.getSID());
                JsonObjConexao.put("USUARIO",conexao.getUsename());
                JsonObjConexao.put("SENHA",conexao.getPassword());
                JsonObjConexao.put("SGDB",conexao.getSGDB());
                JsonObjConexao.put("OBJETIVO",conexao.getObjetivo());
                
                JsonArrayConexao.put(JsonObjConexao);
            }            
            
            for (Consulta consulta: this.consultas) {

                JsonObjConsulta = new JSONObject();
                JsonObjConsulta.put("NOME",consulta.getNome());
                JsonObjConsulta.put("DESCRICAO",consulta.getDescricao());
                JsonObjConsulta.put("CONSULTA",consulta.getSql());
                
                JsonObjConsulta.put("CONEXAO",consulta.getConexao().hashCode());

                JsonObjEntidade = new JSONObject();
                Entidade entidadeConsulta = consulta.getEntidade();
                JsonObjEntidade.put("NOME",entidadeConsulta.getNome());
                JsonObjEntidade.put("DESCRICAO",entidadeConsulta.getDescricao());            
                
                JsonArrayAtributo = new JSONArray();
                for (Atributo atributoConsulta : entidadeConsulta.getAtributos()) {
                    JsonObjAtributo = new JSONObject();
                    JsonObjAtributo.put("NOME", atributoConsulta.getNome());
                    JsonObjAtributo.put("DESCRICAO", atributoConsulta.getDescricao());
                    JsonObjAtributo.put("OBSERVACAO", atributoConsulta.getObservacao());
                    JsonObjAtributo.put("TIPO", atributoConsulta.getTipo());
                    JsonObjAtributo.put("TAMANHO", atributoConsulta.getTamanho());
                    JsonObjAtributo.put("PRECISAO", atributoConsulta.getPrecisao());
                    JsonArrayAtributo.put(JsonObjAtributo);
                }

                JsonObjEntidade.put("ATRIBUTOS", JsonArrayAtributo);
                JsonObjConsulta.put("ENTIDADE",JsonObjEntidade);                
                
                JsonArrayConsulta.put(JsonObjConsulta);
            }

            for (Entidade entidade: this.entidadesSA) {

                JsonObjEntidade = new JSONObject();
                JsonObjEntidade.put("NOME",entidade.getNome());
                JsonObjEntidade.put("DESCRICAO",entidade.getDescricao());

                JsonObjEntidade.put("CONEXAO",entidade.getConexao().hashCode());

                JsonArrayAtributo = new JSONArray();
                
                if (entidade.getAtributos() != null){

                    for (Atributo atributo : entidade.getAtributos()) {

                        JsonObjAtributo = new JSONObject();
                        JsonObjAtributo.put("NOME", atributo.getNome());
                        JsonObjAtributo.put("TIPO", atributo.getTipo());
                        JsonObjAtributo.put("TAMANHO", atributo.getTamanho());
                        JsonObjAtributo.put("PRECISAO", atributo.getPrecisao());
                        JsonObjAtributo.put("OBSERVACAO", atributo.getObservacao());

                        JsonArrayAtributo.put(JsonObjAtributo);
                    }
                }
                JsonObjEntidade.put("ATRIBUTOS", JsonArrayAtributo);

                JsonArrayEntidadeSA.put(JsonObjEntidade);
            }

            for (Entidade entidade: this.entidadesDW) {

                JsonObjEntidade = new JSONObject();
                JsonObjEntidade.put("NOME",entidade.getNome());
                JsonObjEntidade.put("DESCRICAO",entidade.getDescricao());

                JsonObjEntidade.put("CONEXAO",entidade.getConexao().hashCode());

                JsonArrayAtributo = new JSONArray();
                
                if (entidade.getAtributos() != null){

                    for (Atributo atributo : entidade.getAtributos()) {

                        JsonObjAtributo = new JSONObject();
                        JsonObjAtributo.put("NOME", atributo.getNome());
                        JsonObjAtributo.put("TIPO", atributo.getTipo());
                        JsonObjAtributo.put("TAMANHO", atributo.getTamanho());
                        JsonObjAtributo.put("PRECISAO", atributo.getPrecisao());
                        JsonObjAtributo.put("OBSERVACAO", atributo.getObservacao());

                        JsonArrayAtributo.put(JsonObjAtributo);
                    }
                }
                JsonObjEntidade.put("ATRIBUTOS", JsonArrayAtributo);

                JsonArrayEntidadeDW.put(JsonObjEntidade);
            }

            arquivoJson.put("PROJETO", projetoJson);
            arquivoJson.put("CONEXAO", JsonArrayConexao);
            arquivoJson.put("CONSULTA", JsonArrayConsulta);
            arquivoJson.put("ENTIDADE SA", JsonArrayEntidadeSA);
            arquivoJson.put("ENTIDADE DW", JsonArrayEntidadeDW);

            try {
                FileWriter arq;
                arq = new FileWriter(nomearquivo);
                arq.write(arquivoJson.toString());
                arq.close();        
            } catch (IOException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            }        

            this.Pendente = "N";        
            
        }
    }
    
    public void GerarDataMart(Entidade entidade){
        
        try {
            FileWriter arq;
            arq = new FileWriter("C:\\temp\\StageArea.sql");
            if(entidade != null){
                arq.write("CREATE TABLE " + entidade.getNome() + " (" + System.getProperty( "line.separator" ));
                
                for (Atributo atributo : entidade.getAtributos()) {
                    arq.write(" " + atributo.getNome() + " " + atributo.getTipo() + System.getProperty( "line.separator" ));
                }              
                
                arq.write(");");
            }
           
            arq.close();        
        } catch (IOException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
