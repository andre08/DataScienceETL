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

public class Controle {
    
    //arquivos de controles
    private String Pendente;

    //itens 
    private List<Conexao> conexoes;
    private List<Consulta> consultas;
    private List<Entidade> entidades;

    public Controle() {
        this.conexoes = new ArrayList<Conexao>();
        this.consultas = new ArrayList<Consulta>();
        this.entidades = new ArrayList<Entidade>();        
    }
    
    public void AddEntidade (Entidade entidade){
        this.entidades.add(entidade);
    }
    
    public void addConexao (Conexao conexaoAnterior, Conexao conexaoNova){
        
        if(conexaoAnterior == null){
            this.conexoes.add(conexaoNova);
        }else{
            this.conexoes.set(conexoes.indexOf(conexaoAnterior), conexaoNova);
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

    public List<Entidade> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidade> entidades) {
        this.Pendente = "S";        
        this.entidades = entidades;
    }
    
    public void NovoJson(){
        
        /*inicializando lista de conexões*/
        this.conexoes = new ArrayList<Conexao>();
        
        /*inicializando lista de consultas*/
        this.consultas = new ArrayList<Consulta>();
        
        /*inicializando lista de entidades*/
        this.entidades = new ArrayList<Entidade>();        
        
        /*inicializando variaveis de controle*/
        Pendente = "N";        
        
    }
        
    public void CarregarJson(Frame parent){
        
        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.LOAD);
        localizarArquivo.setDirectory("c:\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();
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
                
                addConexao(null, conexao);

            }            

            JSONArray JsonArrayConsulta;
            JsonArrayConsulta = projetoJson.getJSONArray("CONSULTA");

            JSONArray JsonArrayEntidade;
            JSONArray JsonArrayAtributo;
            JSONObject JsonObjEntidade;
            JSONObject JsonObjAtrinbuto;

            JsonArrayEntidade = projetoJson.getJSONArray("ENTIDADE");
            for (int i = 0; i < JsonArrayEntidade.length(); i++) {
                JsonObjEntidade = (JSONObject) JsonArrayEntidade.get(i);

                System.out.println(JsonObjEntidade.getString("NOME"));


                JsonArrayAtributo = JsonObjEntidade.getJSONArray("ATRIBUTOS");
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
        localizarArquivo.setDirectory("c:\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();

        if ((localizarArquivo.getDirectory() != null) && (localizarArquivo.getFile() != null)){
            JSONObject arquivoJson = new JSONObject();
            JSONObject JsonObjConexao;
            JSONObject JsonObjConsulta;
            JSONObject JsonObjEntidade;
            JSONObject JsonObjAtrinbuto;

            JSONArray projetoJson = new JSONArray();
            JSONArray JsonArrayConexao = new JSONArray();
            JSONArray JsonArrayConsulta = new JSONArray();
            JSONArray JsonArrayEntidade = new JSONArray();
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
                
                JsonArrayConexao.put(JsonObjConexao);
            }            

            for (Entidade entidade: this.entidades) {

                JsonObjEntidade = new JSONObject();
                JsonObjEntidade.put("NOME",entidade.getNome());
                JsonObjEntidade.put("DESCRICAO",entidade.getDescricao());

                JsonArrayAtributo = new JSONArray();
                
                if (entidade.getAtributos() != null){

                    for (Atributo atributo : entidade.getAtributos()) {

                        JsonObjAtrinbuto = new JSONObject();
                        JsonObjAtrinbuto.put("NOME", atributo.getNome());
                        JsonObjAtrinbuto.put("TIPO", atributo.getTipo());
                        JsonObjAtrinbuto.put("TAMANHO", atributo.getTamanho());
                        JsonObjAtrinbuto.put("PRECISAO", atributo.getPrecisao());
                        JsonObjAtrinbuto.put("OBSERVACAO", atributo.getObservacao());

                        JsonArrayAtributo.put(JsonObjAtrinbuto);
                    }
                }
                JsonObjEntidade.put("ATRIBUTOS", JsonArrayAtributo);

                JsonArrayEntidade.put(JsonObjEntidade);
            }

            arquivoJson.put("PROJETO", projetoJson);
            arquivoJson.put("CONEXAO", JsonArrayConexao);
            arquivoJson.put("CONSULTA", JsonArrayConsulta);
            arquivoJson.put("ENTIDADE", JsonArrayEntidade);

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
