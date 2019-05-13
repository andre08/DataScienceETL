package datascience;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmMapeamentoSAtoDW extends javax.swing.JDialog {

    private java.awt.Frame parent;
    public Controle controle;
    public MapeamentoDW mapeamentoDWSelecionada;
    public MapeamentoDW mapeamentoDWAtual;

    public void LimparTela() {
        this.mapeamentoDWSelecionada = null;
        this.mapeamentoDWAtual = new MapeamentoDW();
        AtualizarComboEntidadesSA();
        AtualizarComboEntidadesDW();
        atualizaMapeamentos();
    }

    public void atualizaMapeamentos() {
        DefaultTableModel modelMapeamento = (DefaultTableModel) this.tblMapeamento.getModel();
        tblMapeamento.getColumnModel().getColumn(1).setWidth(0);
        tblMapeamento.getColumnModel().getColumn(1).setMinWidth(0);
        tblMapeamento.getColumnModel().getColumn(1).setMaxWidth(0);
        modelMapeamento.setRowCount(0);

        if (this.mapeamentoDWAtual.getMapeamentosAtributos() != null) {
            if (this.mapeamentoDWAtual.getMapeamentosAtributos().size() > 0) {
                for (MapeamentoAtributo mapaAtributo : this.mapeamentoDWAtual.getMapeamentosAtributos()) {
                    modelMapeamento.setRowCount(modelMapeamento.getRowCount() + 1);
                    modelMapeamento.setValueAt(mapaAtributo.toString(), modelMapeamento.getRowCount() - 1, 0);
                    modelMapeamento.setValueAt(mapaAtributo, modelMapeamento.getRowCount() - 1, 1);
                }
            }
        }
        tblMapeamento.setModel(modelMapeamento);
    }

    public void atualizaAtributoSA() {

        DefaultTableModel modelAtributo = (DefaultTableModel) this.tblAtributoSA.getModel();
        tblAtributoSA.getColumnModel().getColumn(1).setWidth(0);
        tblAtributoSA.getColumnModel().getColumn(1).setMinWidth(0);
        tblAtributoSA.getColumnModel().getColumn(1).setMaxWidth(0);
        modelAtributo.setRowCount(0);

        //ativando controles
        if (this.mapeamentoDWAtual.getEntidadeSAOrigem().getAtributos().size() > 0) {
            for (Atributo atributo : this.mapeamentoDWAtual.getEntidadeSAOrigem().getAtributos()) {
                boolean Exists = false;
                for (MapeamentoAtributo mapaAtributo : this.mapeamentoDWAtual.getMapeamentosAtributos()) {
                    if (mapaAtributo.getAtributoOrigem().equals(atributo)) {
                        Exists = true;
                    }
                }

                if (!Exists) {
                    modelAtributo.setRowCount(modelAtributo.getRowCount() + 1);
                    modelAtributo.setValueAt(atributo.getNome(), modelAtributo.getRowCount() - 1, 0);
                    modelAtributo.setValueAt(atributo, modelAtributo.getRowCount() - 1, 1);
                }
            }
        }
        tblAtributoSA.setModel(modelAtributo);

    }

    public void atualizaAtributoDW() {

        DefaultTableModel modelAtributo = (DefaultTableModel) this.tblAtributoDW.getModel();
        tblAtributoDW.getColumnModel().getColumn(1).setWidth(0);
        tblAtributoDW.getColumnModel().getColumn(1).setMinWidth(0);
        tblAtributoDW.getColumnModel().getColumn(1).setMaxWidth(0);
        modelAtributo.setRowCount(0);

        //ativando controles
        if (this.mapeamentoDWAtual.getEntidadeDWDestino().getAtributos().size() > 0) {
            for (Atributo atributo : this.mapeamentoDWAtual.getEntidadeDWDestino().getAtributos()) {
                boolean Exists = false;
                for (MapeamentoAtributo mapaAtributo : this.mapeamentoDWAtual.getMapeamentosAtributos()) {
                    if (mapaAtributo.getAtributoDestino().equals(atributo)) {
                        Exists = true;
                    }
                }

                if (!Exists) {
                    modelAtributo.setRowCount(modelAtributo.getRowCount() + 1);
                    modelAtributo.setValueAt(atributo.getNome(), modelAtributo.getRowCount() - 1, 0);
                    modelAtributo.setValueAt(atributo, modelAtributo.getRowCount() - 1, 1);
                }
            }
        }
        tblAtributoDW.setModel(modelAtributo);

    }

    public void AtualizarComboEntidadesSA() {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (controle != null) {
            if (controle.getEntidadesSA() != null) {
                for (Entidade entidade : controle.getEntidadesSA()) {
                    model.addElement(entidade);
                }
            }
        }
        cbxEntidadeSA.setModel(model);
    }

    public void AtualizarComboEntidadesDW() {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (controle != null) {
            if (controle.getEntidadesDW() != null) {
                for (Entidade entidade : controle.getEntidadesDW()) {
                    model.addElement(entidade);
                }
            }
        }
        cbxEntidadeDW.setModel(model);
    }

    public void SetMapeamentoSelecionado(MapeamentoDW mapeamentoDW) {

        this.mapeamentoDWSelecionada = mapeamentoDW;
        this.mapeamentoDWAtual = mapeamentoDW.Copia();

        AtualizarComboEntidadesDW();
        AtualizarComboEntidadesSA();
        cbxEntidadeSA.setSelectedItem(this.mapeamentoDWAtual.getEntidadeSAOrigem());
        cbxEntidadeDW.setSelectedItem(this.mapeamentoDWAtual.getEntidadeDWDestino());
        atualizaAtributoDW();
        atualizaAtributoSA();
        atualizaMapeamentos();
        atualizaAtributoDW();
        atualizaAtributoSA();

        boolean podeEditar = true;
        for (MapeamentoAtributo mapa : mapeamentoDWAtual.getMapeamentosAtributos()) {
            if ((mapa.getAtributoDestino() != null) || (mapa.getAtributoOrigem() != null)) {
                podeEditar = false;
            }
        }
        cbxEntidadeDW.setEditable(podeEditar);
        cbxEntidadeSA.setEditable(podeEditar);

    }
    
    public void AtualizaAtual(){
        this.mapeamentoDWAtual.setEntidadeSAOrigem((Entidade) cbxEntidadeSA.getSelectedItem());
        this.mapeamentoDWAtual.setEntidadeDWDestino((Entidade) cbxEntidadeDW.getSelectedItem());
    }

    public FrmMapeamentoSAtoDW(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbxEntidadeSA = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbxEntidadeDW = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtributoSA = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtributoDW = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMapeamento = new javax.swing.JTable();
        btnAddRelacao = new javax.swing.JButton();
        btnDelRelacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mapeamento entre Staging Area e o Data Warehouse");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel11.setText("Tabela StagingArea:");

        cbxEntidadeSA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEntidadeSA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEntidadeSAItemStateChanged(evt);
            }
        });

        jLabel12.setText("Atributo DataMart:");

        cbxEntidadeDW.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEntidadeDW.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEntidadeDWItemStateChanged(evt);
            }
        });

        tblAtributoSA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAtributoSA);

        tblAtributoDW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAtributoDW);

        tblMapeamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mapeamento", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblMapeamento);

        btnAddRelacao.setText("Adicionar Relação");
        btnAddRelacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRelacaoActionPerformed(evt);
            }
        });

        btnDelRelacao.setText("Remover Relação");
        btnDelRelacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRelacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxEntidadeSA, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbxEntidadeDW, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddRelacao))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelRelacao)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEntidadeSA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(cbxEntidadeDW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddRelacao)
                        .addGap(19, 19, 19)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelRelacao)
                .addContainerGap(18, Short.MAX_VALUE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        this.controle.AddMapeamentoDW(this.mapeamentoDWSelecionada, this.mapeamentoDWAtual);
        this.mapeamentoDWSelecionada = this.mapeamentoDWAtual;
        JOptionPane.showMessageDialog(this, "Mapemaneto salvo com sucesso.");
        dispose();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        LimparTela();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (this.mapeamentoDWSelecionada != null) {
            controle.getMapeamentosDW().remove(this.mapeamentoDWSelecionada);
            this.mapeamentoDWSelecionada = null;
            dispose();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void cbxEntidadeSAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEntidadeSAItemStateChanged
        // TODO add your handling code here:
        AtualizaAtual();
        atualizaAtributoSA();
    }//GEN-LAST:event_cbxEntidadeSAItemStateChanged

    private void cbxEntidadeDWItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEntidadeDWItemStateChanged
        // TODO add your handling code here:
        AtualizaAtual();
        atualizaAtributoDW();
    }//GEN-LAST:event_cbxEntidadeDWItemStateChanged

    private void btnAddRelacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRelacaoActionPerformed
        // TODO add your handling code here:
        boolean jaExists = false;
        Atributo atributoOrigem = null;
        if (tblAtributoSA.getSelectedRow() >= 0) {
            atributoOrigem = (Atributo) tblAtributoSA.getValueAt(tblAtributoSA.getSelectedRow(), 1);
        }

        Atributo atributoDestino = null;
        if (tblAtributoDW.getSelectedRow() >= 0) {
            atributoDestino = (Atributo) tblAtributoDW.getValueAt(tblAtributoDW.getSelectedRow(), 1);
        }

        if (this.mapeamentoDWAtual.getMapeamentosAtributos() != null) {
            for (MapeamentoAtributo mapeamentoAtributo : this.mapeamentoDWAtual.getMapeamentosAtributos()) {
                if (mapeamentoAtributo.getAtributoOrigem() != null) {
                    //procurando atributo de origem
                    if (atributoOrigem != null) {
                        if (mapeamentoAtributo.getAtributoOrigem().equals(atributoOrigem)) {
                            jaExists = true;
                        }
                    }
                }
                //procurando atributo de destino
                if (mapeamentoAtributo.getAtributoDestino() != null) {
                    //procurando atributo de origem
                    if (atributoDestino != null) {
                        if (mapeamentoAtributo.getAtributoDestino().equals(atributoDestino)) {
                            jaExists = true;
                        }
                    }

                }
            }
        }

        if (jaExists) {

            JOptionPane.showMessageDialog(this, "Atributo já usando em um relacionamento.");

        } else if ((atributoOrigem != null) && (atributoDestino != null)) {
            MapeamentoAtributo novoMapeamentoAtributo = new MapeamentoAtributo();
            novoMapeamentoAtributo.setAtributoOrigem(atributoOrigem);
            novoMapeamentoAtributo.setAtributoDestino(atributoDestino);
            this.mapeamentoDWAtual.getMapeamentosAtributos().add(novoMapeamentoAtributo);
            atualizaMapeamentos();
            atualizaAtributoDW();
            atualizaAtributoSA();

        }

    }//GEN-LAST:event_btnAddRelacaoActionPerformed

    private void btnDelRelacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRelacaoActionPerformed
        // TODO add your handling code here:
        if (tblMapeamento.getSelectedRow() >= 0) {
            if (this.mapeamentoDWAtual.getMapeamentosAtributos() != null) {
                this.mapeamentoDWAtual.getMapeamentosAtributos().remove((MapeamentoAtributo) tblMapeamento.getValueAt(tblMapeamento.getSelectedRow(), 1));
            }
            atualizaMapeamentos();
            atualizaAtributoDW();
            atualizaAtributoSA();
        }

    }//GEN-LAST:event_btnDelRelacaoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMapeamentoSAtoDW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMapeamentoSAtoDW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMapeamentoSAtoDW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMapeamentoSAtoDW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMapeamentoSAtoDW dialog = new FrmMapeamentoSAtoDW(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAddRelacao;
    private javax.swing.JButton btnDelRelacao;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxEntidadeDW;
    private javax.swing.JComboBox<String> cbxEntidadeSA;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblAtributoDW;
    private javax.swing.JTable tblAtributoSA;
    private javax.swing.JTable tblMapeamento;
    // End of variables declaration//GEN-END:variables
}
