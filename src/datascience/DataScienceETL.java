package datascience;

public class DataScienceETL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            FrmPrincipal frmPrincipal = new FrmPrincipal();
            frmPrincipal.pack();
            frmPrincipal.setLocationRelativeTo(null);      
            frmPrincipal.setVisible(true);
            
        } catch (RuntimeException ex) {
            
            FrmMensagem frmMensagem = new FrmMensagem(null, true);
            frmMensagem.Mostrar(ex.getMessage());
            frmMensagem.setVisible(true);
            
        }
    }
}
