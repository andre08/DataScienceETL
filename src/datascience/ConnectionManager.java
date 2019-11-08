package datascience;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection(java.awt.Frame parent, Conexao conexao) {

        Connection conn = null;
        String STR_DRIVER = "";
        String STR_CONEX = "";
        String USER = conexao.getUsename();
        String PASSWORD = conexao.getPassword();

        switch (conexao.getSGDB()) {
            case "Oracle":
                STR_DRIVER = "oracle.jdbc.driver.OracleDriver";
                STR_CONEX = "jdbc:oracle:thin:@" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + ":" + conexao.getSID();
                break;
            case "MySQL":
                STR_DRIVER = "org.gjt.mm.mysql.Driver";
                STR_CONEX = "jdbc:mysql://" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + "/" + conexao.getNomeBanco();
                break;
            case "MS SQL Server":
                STR_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
                STR_CONEX = "jdbc:jtds:sqlserver://" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + "/" + conexao.getNomeBanco();
                break;
            case "Postgre SQL":
                STR_DRIVER = "org.postgresql.Driver";
                STR_CONEX = "jdbc:postgresql://" + conexao.getUrl() + ":" + Integer.toString(conexao.getPorta()) + "/" + conexao.getNomeBanco();
                break;
            default:
                break;
        }

        try {
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CONEX, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            FrmMensagem frmMensagem = new FrmMensagem(parent, true);
            frmMensagem.Mostrar(ex.toString());
            frmMensagem.setVisible(true);
        }

        return conn;
    }

    public static void testConnection(java.awt.Frame parent, Conexao conexao) {

        FrmMensagem frmMensagem = new FrmMensagem(parent, true);

        try {
            Connection conn = getConnection(parent, conexao);
            conn.close();
            frmMensagem.Mostrar("Conexão realizada com sucesso!");
        } catch (Exception ex) {
            frmMensagem.Mostrar(ex.toString());
        }

        frmMensagem.setVisible(true);
    }
}
