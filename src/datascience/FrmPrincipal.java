package datascience;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class FrmPrincipal extends javax.swing.JFrame {

    Controle controle;
    Conexao conexaoSelecionado;
    Consulta consultaSelecionada;
    Entidade entidadeSelecionada;
    
    public FrmPrincipal() {
        initComponents(); 
        controle = new Controle();
        controle.NovoJson();    
        AtualizarTela();
    }
    
    private void AtualizarTela(){
      
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Projeto");

        DefaultMutableTreeNode conexaoNode = new DefaultMutableTreeNode("Conexões");
        DefaultMutableTreeNode consultaNode = new DefaultMutableTreeNode("Consultas");
        DefaultMutableTreeNode entidadeNode = new DefaultMutableTreeNode("Entidades");
        
        DefaultMutableTreeNode itensNode;
        
        for (Conexao conexao : controle.getConexoes()) {
            itensNode = new DefaultMutableTreeNode(conexao.getNome());
            itensNode.setUserObject(conexao);
            conexaoNode.add(itensNode);
        }
        
        for (Consulta consulta : controle.getConsultas()) {
            itensNode = new DefaultMutableTreeNode(consulta.getNome());
            itensNode.setUserObject(consulta);
            consultaNode.add(itensNode);
        }

        for (Entidade entidade : controle.getEntidades()) {
            itensNode = new DefaultMutableTreeNode(entidade.getNome());
            itensNode.setUserObject(entidade);
            entidadeNode.add(itensNode);
        }

        rootNode.add(conexaoNode);
        rootNode.add(consultaNode);
        rootNode.add(entidadeNode);
        
        DefaultTreeModel modelo = new DefaultTreeModel(rootNode);
        modelo.reload(rootNode);
        lstProjeto.setModel(modelo); 
        
        for(int i=0;i<lstProjeto.getRowCount();++i){
            lstProjeto.expandRow(i);
        }        

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProjeto = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniNovo = new javax.swing.JMenuItem();
        mniAbrir = new javax.swing.JMenuItem();
        mniSalvar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniFechar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniDefinicaoProjeto = new javax.swing.JMenuItem();
        mniGerarSqlStage = new javax.swing.JMenuItem();
        mniGerarSqlDW = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniGerenciarConexao = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mniGerenciarConsulta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mniGerenciarEntidade = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu8.setText("jMenu8");

        jMenuItem1.setText("jMenuItem1");

        lstProjeto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstProjetoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstProjeto);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Construtor de Staging Area para processo de ETL");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(547, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        jMenu1.setText("Arquivo");

        mniNovo.setText("Novo");
        mniNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNovoActionPerformed(evt);
            }
        });
        jMenu1.add(mniNovo);

        mniAbrir.setText("Abrir");
        mniAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(mniAbrir);

        mniSalvar.setText("Salvar");
        mniSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(mniSalvar);
        jMenu1.add(jSeparator1);

        mniFechar.setText("Fechar");
        mniFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFecharActionPerformed(evt);
            }
        });
        jMenu1.add(mniFechar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Projeto");

        mniDefinicaoProjeto.setText("Definição do Projeto");
        jMenu2.add(mniDefinicaoProjeto);

        mniGerarSqlStage.setText("Gerar SQL Staging Area");
        jMenu2.add(mniGerarSqlStage);

        mniGerarSqlDW.setText("Gerar SQL Data Ware House");
        jMenu2.add(mniGerarSqlDW);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Conexões");

        mniGerenciarConexao.setText("Gerenciar Conexões");
        mniGerenciarConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerenciarConexaoActionPerformed(evt);
            }
        });
        jMenu4.add(mniGerenciarConexao);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Consultas");

        mniGerenciarConsulta.setText("Gerenciar Consultas");
        mniGerenciarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerenciarConsultaActionPerformed(evt);
            }
        });
        jMenu5.add(mniGerenciarConsulta);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Entidade Destinos");

        mniGerenciarEntidade.setText("Gerenciar Entidades de Destino");
        jMenu6.add(mniGerenciarEntidade);

        jMenuBar1.add(jMenu6);

        jMenu9.setText("Mapeamento");

        jMenuItem3.setText("Configurar Mapeamento");
        jMenu9.add(jMenuItem3);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
               
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mniAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAbrirActionPerformed
        if ((controle.getConexoes().size() > 0)||(controle.getConsultas().size() > 0)||(controle.getEntidades().size() > 0)){
            
            int resultado = JOptionPane.showConfirmDialog(this,"Deseja salvar os dados antes de criar um novo","Confirmação",JOptionPane.YES_NO_OPTION);
            if(resultado == JOptionPane.YES_OPTION){
                controle.SalvarJson(this);
                controle.NovoJson();
                controle.CarregarJson(this);
            } else{
                controle.NovoJson();
                controle.CarregarJson(this);
            }          
        }else{   
            controle.NovoJson();
            controle.CarregarJson(this);
        }
        AtualizarTela();
    }//GEN-LAST:event_mniAbrirActionPerformed

    private void mniFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFecharActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_mniFecharActionPerformed

    private void mniNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNovoActionPerformed

        if ((controle.getConexoes().size() > 0)||(controle.getConsultas().size() > 0)||(controle.getEntidades().size() > 0)){
            
            int resultado = JOptionPane.showConfirmDialog(this,"Deseja salvar os dados antes de criar um novo","Confirmação",JOptionPane.YES_NO_OPTION);
            if(resultado == JOptionPane.YES_OPTION){
                controle.SalvarJson(this);
                controle.NovoJson(); 
                AtualizarTela();
            }else{
                controle.NovoJson(); 
                AtualizarTela();
            }
            
        }else{                    
          controle.NovoJson();  
          AtualizarTela();
        }
        
    }//GEN-LAST:event_mniNovoActionPerformed

    private void mniGerenciarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarConexaoActionPerformed

        FrmConexao frmConexao = new FrmConexao(this, true);
        frmConexao.controle = this.controle;    
        if(this.conexaoSelecionado != null){
            frmConexao.SetConexaoSelecionada(this.conexaoSelecionado);
        }
        frmConexao.pack();
        frmConexao.setLocationRelativeTo(null);      
        frmConexao.setVisible(true);
        this.controle = frmConexao.controle;
        AtualizarTela();
        
    }//GEN-LAST:event_mniGerenciarConexaoActionPerformed

    private void mniSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSalvarActionPerformed

        controle.SalvarJson(this);
        AtualizarTela();
    }//GEN-LAST:event_mniSalvarActionPerformed

    
    private void lstProjetoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProjetoMouseClicked

        this.conexaoSelecionado = null;
        this.consultaSelecionada = null;
        this.entidadeSelecionada = null;
        DefaultMutableTreeNode selecao = (DefaultMutableTreeNode)lstProjeto.getLastSelectedPathComponent();        
        
        if (selecao != null){
        
            if(selecao.getUserObject() instanceof Conexao){
                this.conexaoSelecionado = (Conexao) selecao.getUserObject();
            }

            if(selecao.getUserObject() instanceof Consulta){
                this.consultaSelecionada = (Consulta) selecao.getUserObject();
            }

            if(selecao.getUserObject() instanceof Entidade){
                this.entidadeSelecionada = (Entidade) selecao.getUserObject();
            }

        }

    }//GEN-LAST:event_lstProjetoMouseClicked

    private void mniGerenciarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarConsultaActionPerformed
        // TODO add your handling code here:
        FrmConsulta frmConsulta = new FrmConsulta(this, true);
        frmConsulta.controle = this.controle;    
        frmConsulta.AtualizarConexao();
        if(this.consultaSelecionada != null){
            frmConsulta.SetConsultaSelecionada(this.consultaSelecionada);
        }
        frmConsulta.pack();
        frmConsulta.setLocationRelativeTo(null);      
        frmConsulta.setVisible(true);
        this.controle = frmConsulta.controle;
        AtualizarTela();
        
        
    }//GEN-LAST:event_mniGerenciarConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree lstProjeto;
    private javax.swing.JMenuItem mniAbrir;
    private javax.swing.JMenuItem mniDefinicaoProjeto;
    private javax.swing.JMenuItem mniFechar;
    private javax.swing.JMenuItem mniGerarSqlDW;
    private javax.swing.JMenuItem mniGerarSqlStage;
    private javax.swing.JMenuItem mniGerenciarConexao;
    private javax.swing.JMenuItem mniGerenciarConsulta;
    private javax.swing.JMenuItem mniGerenciarEntidade;
    private javax.swing.JMenuItem mniNovo;
    private javax.swing.JMenuItem mniSalvar;
    // End of variables declaration//GEN-END:variables
}
