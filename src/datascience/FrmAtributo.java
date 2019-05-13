package datascience;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class FrmAtributo extends javax.swing.JDialog {

    private java.awt.Frame parent;

    public Controle controle;
    public Entidade entidadeSelecionada;
    public Atributo atributoSelecionado;
    public Atributo atributoAtual;
    private String usado;

    public FrmAtributo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parent = parent;

        this.usado = "";

        //desativando controles
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnFechar.setEnabled(true);
        btnNovo.setEnabled(true);

    }

    public void LimparTela() {

        this.atributoSelecionado = null;
        //crinando um novo objeto
        this.atributoAtual = new Atributo();

        txtNome.setText("");
        txtDescricao.setText("");
        txtObservacao.setText("");
        txtTamanho.setText("");
        txtPrecisao.setText("");
        cbxTipo.setSelectedIndex(-1);
        cbxEntidade.setSelectedIndex(-1);
        cbxAtributo.setSelectedIndex(-1);
        chkObrigatorio.setSelected(false);
        chkPK.setSelected(false);
        chkFK.setSelected(false);
        chkSequencia.setSelected(false);

        //ativando controles
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnNovo.setEnabled(true);

        AtualizarEntidade();
    }

    public void AtualizarEntidade() {

        //identificando a origem da chamada da tela e habilitar os controle de acordo com a origem
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        switch (this.usado) {
            case "CO":
                if (this.controle != null) {
                    if (controle.getConsultas() != null) {
                        for (Consulta consulta : controle.getConsultas()) {
                            if (consulta.getEntidade() != null) {
                                model.addElement(consulta.getEntidade());
                            }
                        }
                    }
                }
                break;
            case "SA":
                if (this.controle != null) {
                    if (controle.getEntidadesSA() != null) {
                        for (Entidade entidade : controle.getEntidadesSA()) {
                            model.addElement(entidade);
                        }
                    }
                }
                break;
            case "DW":
                if (this.controle != null) {
                    if (controle.getEntidadesDW() != null) {
                        for (Entidade entidade : controle.getEntidadesDW()) {
                            model.addElement(entidade);
                        }
                    }
                }
                break;
        }
        cbxEntidade.setModel(model);

    }

    public void AtualizarAtributos() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        Object[] modelSelected = cbxEntidade.getSelectedObjects();
        if (modelSelected.length > 0) {
            Entidade entidade = (Entidade) modelSelected[0];

            if (entidade != null) {
                for (Atributo atributo : entidade.getAtributos()) {
                    if (atributo != null) {
                        model.addElement(atributo);
                    }
                }
            }
        }
        cbxAtributo.setModel(model);
    }

    public void SetAtributoSelecionado(Atributo atributo, String usado) {
        this.usado = usado;
        this.atributoSelecionado = atributo;
        this.atributoAtual = atributo.copia();

        txtNome.setText(atributoAtual.getNome());
        txtDescricao.setText(atributoAtual.getDescricao());
        txtObservacao.setText(atributoAtual.getObservacao());
        txtTamanho.setText(Integer.toString(atributoAtual.getTamanho()));
        txtPrecisao.setText(Integer.toString(atributoAtual.getPrecisao()));
        cbxTipo.setSelectedItem(atributoAtual.getTipo());

        AtualizarEntidade();
        cbxEntidade.setSelectedItem(atributoAtual.getReferenciaEntidade());

        AtualizarAtributos();
        cbxAtributo.setSelectedItem(atributoAtual.getReferenciaAtributo());

        chkObrigatorio.setSelected(atributoAtual.getObrigatorio().equals("S"));
        chkPK.setSelected(atributoAtual.getChavePrimaria().equals("S"));
        chkFK.setSelected(atributoAtual.getChaveEstrangeira().equals("S"));
        chkSequencia.setSelected(atributoAtual.getValorSequencial().equals("S"));

        //ativando controles
        btnNovo.setVisible(!this.usado.equals("CO"));
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(true);

    }

    public void AtualizaAtual() {

        this.atributoAtual.setNome(txtNome.getText());
        this.atributoAtual.setDescricao(txtDescricao.getText());
        this.atributoAtual.setObservacao(txtObservacao.getText());
        this.atributoAtual.setTamanho(Integer.parseInt(txtTamanho.getText()));
        this.atributoAtual.setPrecisao(Integer.parseInt(txtPrecisao.getText()));
        if (cbxTipo.getSelectedIndex() != -1) {
            this.atributoAtual.setTipo(cbxTipo.getSelectedItem().toString());
        }

        this.atributoAtual.setObrigatorio(chkObrigatorio.isSelected() ? "S" : "N");
        this.atributoAtual.setChavePrimaria(chkPK.isSelected() ? "S" : "N");
        this.atributoAtual.setChaveEstrangeira(chkFK.isSelected() ? "S" : "N");
        this.atributoAtual.setValorSequencial(chkSequencia.isSelected() ? "S" : "N");

        if (cbxEntidade.getSelectedIndex() != -1) {
            atributoAtual.setReferenciaEntidade((Entidade) cbxEntidade.getSelectedItem());
        }

        if (cbxAtributo.getSelectedIndex() != -1) {
            atributoAtual.setReferenciaAtributo((Atributo) cbxAtributo.getSelectedItem());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        cbxEntidade = new javax.swing.JComboBox<>();
        cbxAtributo = new javax.swing.JComboBox<>();
        chkObrigatorio = new javax.swing.JCheckBox();
        chkPK = new javax.swing.JCheckBox();
        chkFK = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        chkSequencia = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTamanho = new javax.swing.JFormattedTextField();
        txtPrecisao = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão de Atributos");

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(434, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)))
        );

        jLabel1.setText("Nome:");

        jLabel2.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel3.setText("Tamanho:");

        jLabel9.setText("Tipo:");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHAR", "VARCHAR", "INT", "FLOAT", "NUMERIC", "DECIMAL", "NUMBER", "DATE", "TIME", "DATETIME", "TIMESTAMP", "CLOB", "BLOB" }));

        jLabel4.setText("Precisão:");

        jLabel5.setText("Observação:");

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        cbxEntidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEntidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEntidadeItemStateChanged(evt);
            }
        });

        cbxAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkObrigatorio.setText("Obrigatório");

        chkPK.setText("Chave Primária");

        chkFK.setText("Chave Estrangeira");

        jLabel10.setText("Opções:");

        chkSequencia.setText("Gerar Valor Sequencial");

        jLabel11.setText("Entidade Referência:");

        jLabel12.setText("Atributo Referência:");

        txtTamanho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));

        txtPrecisao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxAtributo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxEntidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkObrigatorio))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(1, 1, 1)
                                .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecisao, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(chkPK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(chkFK)
                                .addGap(40, 40, 40)))
                        .addComponent(chkSequencia)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkObrigatorio)
                    .addComponent(chkPK)
                    .addComponent(jLabel10)
                    .addComponent(chkFK)
                    .addComponent(chkSequencia))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed

        dispose();

    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        AtualizaAtual();
        this.entidadeSelecionada.addAtributo(this.atributoSelecionado, this.atributoAtual);
        this.atributoSelecionado = this.atributoAtual;
        JOptionPane.showMessageDialog(this, "Atributo salva com sucesso.");
        dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        LimparTela();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.entidadeSelecionada.getAtributos().remove(this.atributoSelecionado);
        this.atributoSelecionado = null;
        dispose();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void cbxEntidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEntidadeItemStateChanged
        // TODO add your handling code here:
        AtualizarAtributos();
    }//GEN-LAST:event_cbxEntidadeItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmAtributo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAtributo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAtributo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAtributo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAtributo dialog = new FrmAtributo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxAtributo;
    private javax.swing.JComboBox<String> cbxEntidade;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JCheckBox chkFK;
    private javax.swing.JCheckBox chkObrigatorio;
    private javax.swing.JCheckBox chkPK;
    private javax.swing.JCheckBox chkSequencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JFormattedTextField txtPrecisao;
    private javax.swing.JFormattedTextField txtTamanho;
    // End of variables declaration//GEN-END:variables
}
