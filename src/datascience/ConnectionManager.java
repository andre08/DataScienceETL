package datascience;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;

public class ConnectionManager {

    public static Connection getConnection(java.awt.Frame parent, Conexao conexao) {
    
        return null;
    }
    
    public static void testConnection(java.awt.Frame parent, Conexao conexao) {

        Connection conn = null;
        String STR_DRIVER;
        String STR_CONEX;
        String USER;
        String PASSWORD;
        String DATABASENAME;
        String SID;

        FrmMensagem frmMensagem = new FrmMensagem(parent, true);
        if(conexao.getSGDB().equals("ORACLE")){
            
            STR_DRIVER = "oracle.jdbc.driver.OracleDriver";
            STR_CONEX = "jdbc:oracle:thin:@" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + ":" + conexao.getSID();
            USER = conexao.getUsename();
            PASSWORD = conexao.getPassword();
            
            try {
                Class.forName(STR_DRIVER);
                conn = DriverManager.getConnection(STR_CONEX, USER, PASSWORD);
                conn.close();
                frmMensagem.Mostrar("Conexão realizada com sucesso!");
            } catch (Exception ex) {
                frmMensagem.Mostrar(ex.toString());
            }
            
        }else if(conexao.getSGDB().equals("MySQL")){

            STR_DRIVER = "org.gjt.mm.mysql.Driver";
            STR_CONEX = "jdbc:mysql://" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + "/" + conexao.getNomeBanco();
            USER = conexao.getUsename();
            PASSWORD = conexao.getPassword();

            try {
                Class.forName(STR_DRIVER);
                conn = DriverManager.getConnection(STR_CONEX, USER, PASSWORD);
                conn.close();
                frmMensagem.Mostrar("Conexão realizada com sucesso!");
            } catch (Exception ex) {
                frmMensagem.Mostrar(ex.getMessage());
            }

        }else if(conexao.getSGDB().equals("MS SQL Server")){

            STR_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
            STR_CONEX = "jdbc:jtds:sqlserver://" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + "/" + conexao.getNomeBanco();
            USER = conexao.getUsename();
            PASSWORD = conexao.getPassword();

            try {
                Class.forName(STR_DRIVER);
                conn = DriverManager.getConnection(STR_CONEX, USER, PASSWORD);
                conn.close();
                frmMensagem.Mostrar("Conexão realizada com sucesso!");
            } catch (Exception ex) {
                frmMensagem.Mostrar(ex.getMessage());
            }

        }else{
            frmMensagem.Mostrar("Tipo de conexão não identificada");
        }
        frmMensagem.setVisible(true);
    }
}
