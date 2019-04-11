/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datascience;

/**
 *
 * @author Pessoal
 */
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
            
        } catch (Exception ex) {
            FrmMensagem frmMensagem = new FrmMensagem(null, true);
            frmMensagem.Mostrar(ex.toString());
            frmMensagem.setVisible(true);
            
        }
        
    }
    
}
