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
    private String ArquivoAtual;
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
    
    public void addConexao (Conexao conexao){
        this.conexoes.add(conexao);
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
        ArquivoAtual = "";
        Pendente = "N";        
        
    }
        
    public void CarregarJson(Frame parent){
        
        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.LOAD);
        localizarArquivo.setDirectory("c:\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();
        if (nomearquivo != null){

            String conteudoJson = "";
            try {
                conteudoJson = new String(Files.readAllBytes(Paths.get(nomearquivo)));
            } catch (IOException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject projetoJson = new JSONObject(conteudoJson);

            JSONArray JsonArrayConexao;
            JSONObject JsonObjConexao;

            JsonArrayConexao = projetoJson.getJSONArray("conexao");
            for (int i = 0; i < JsonArrayConexao.length(); i++) {
                JsonObjConexao = (JSONObject) JsonArrayConexao.get(i);
                System.out.println(JsonObjConexao.getString("SGDB"));
                Conexao conexao = new Conexao();
                conexao.setId(JsonObjConexao.getInt("id"));
                conexao.setPorta(JsonObjConexao.getInt("porta"));
                conexao.setSGDB(JsonObjConexao.getString("SGDB"));
                
                addConexao(conexao);

            }            

            JSONArray JsonArrayConsulta;
            JsonArrayConsulta = projetoJson.getJSONArray("consulta");

            JSONArray JsonArrayEntidade;
            JSONArray JsonArrayAtributo;
            JSONObject JsonObjEntidade;
            JSONObject JsonObjAtrinbuto;

            JsonArrayEntidade = projetoJson.getJSONArray("entidade");
            for (int i = 0; i < JsonArrayEntidade.length(); i++) {
                JsonObjEntidade = (JSONObject) JsonArrayEntidade.get(i);

                System.out.println(JsonObjEntidade.getInt("id"));
                System.out.println(JsonObjEntidade.getString("nome"));


                JsonArrayAtributo = JsonObjEntidade.getJSONArray("atributos");
                for (int j = 0; j < JsonArrayAtributo.length(); j++) {
                    JsonObjAtrinbuto = (JSONObject) JsonArrayAtributo.get(i);

                    System.out.println(JsonObjAtrinbuto.getInt("id"));
                    System.out.println(JsonObjAtrinbuto.getString("nome"));
                    System.out.println(JsonObjAtrinbuto.getString("tipo"));

                }

            }
            
            this.Pendente = "N";   
            this.ArquivoAtual = nomearquivo;
            
        }
        
    }
    
    public void SalvarJson(Frame parent){
        
        FileDialog localizarArquivo = new FileDialog(parent, "Localizar arquivo do projeto", FileDialog.SAVE);
        localizarArquivo.setDirectory("c:\\");
        localizarArquivo.setFile("*.json");
        localizarArquivo.setVisible(true);
        String nomearquivo = localizarArquivo.getDirectory() + "\\" + localizarArquivo.getFile();

        System.out.println(nomearquivo);
        
        if (nomearquivo != null){
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
                JsonObjConexao.put("id",conexao.getId());
                JsonObjConexao.put("nome",conexao.getNome());
                JsonObjConexao.put("descricao",conexao.getDescricao());
                JsonObjConexao.put("url",conexao.getUrl());
                JsonObjConexao.put("porta",conexao.getPorta());
                JsonObjConexao.put("nomeBanco",conexao.getNomeBanco());
                JsonObjConexao.put("SID",conexao.getSID());
                JsonObjConexao.put("usename",conexao.getUsename());
                JsonObjConexao.put("password",conexao.getPassword());
                JsonObjConexao.put("SGDB",conexao.getSGDB());
                
                JsonArrayConexao.put(JsonObjConexao);
            }            

            for (Entidade entidade: this.entidades) {

                JsonObjEntidade = new JSONObject();
                JsonObjEntidade.put("id",entidade.getId());
                JsonObjEntidade.put("nome",entidade.getNome());
                JsonObjEntidade.put("descricao",entidade.getDescricao());

                JsonArrayAtributo = new JSONArray();
                
                if (entidade.getAtributos() != null){

                    for (Atributo atributo : entidade.getAtributos()) {

                        JsonObjAtrinbuto = new JSONObject();
                        JsonObjAtrinbuto.put("id", atributo.getId());
                        JsonObjAtrinbuto.put("nome", atributo.getNome());
                        JsonObjAtrinbuto.put("tipo", atributo.getTipo());
                        JsonObjAtrinbuto.put("tamanho", atributo.getTamanho());
                        JsonObjAtrinbuto.put("precisao", atributo.getPrecisao());
                        JsonObjAtrinbuto.put("observacao", atributo.getObservacao());

                        JsonArrayAtributo.put(JsonObjAtrinbuto);
                    }
                }
                JsonObjEntidade.put("atributos", JsonArrayAtributo);

                JsonArrayEntidade.put(JsonObjEntidade);
            }

            arquivoJson.put("projeto", projetoJson);
            arquivoJson.put("conexao", JsonArrayConexao);
            arquivoJson.put("consulta", JsonArrayConsulta);
            arquivoJson.put("entidade", JsonArrayEntidade);

            try {
                FileWriter arq;
                arq = new FileWriter(nomearquivo);
                arq.write(arquivoJson.toString());
                arq.close();        
            } catch (IOException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            }        

            this.Pendente = "N";        
            this.ArquivoAtual = nomearquivo;
            
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
