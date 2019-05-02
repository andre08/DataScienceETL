package datascience;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmConsulta extends javax.swing.JDialog {

    private java.awt.Frame parent;

    public Controle controle;
    public Consulta consultaSelecionada;
    public Consulta consultaAtual;
    public Atributo atributoSelecionado;

    public FrmConsulta(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        this.parent = parent;

        //desativando controles
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnTeste.setEnabled(true);
        btnAtributo.setEnabled(false);
        btnFechar.setEnabled(true);

        this.atributoSelecionado = null;
    }

    public void AtualizarConexao() {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (controle != null) {
            if (controle.getConexoes() != null) {
                for (Conexao conexao : controle.getConexoes()) {
                    model.addElement(conexao);
                }
            }
        }
        cbxConexão.setModel(model);
    }

    private void CarregaAtributos() {

        Entidade entidade = this.consultaAtual.getEntidade();
        DefaultTableModel modelAtributo = (DefaultTableModel) this.tblAtributos.getModel();
        tblAtributos.getColumnModel().getColumn(4).setWidth(0);
        tblAtributos.getColumnModel().getColumn(4).setMinWidth(0);
        tblAtributos.getColumnModel().getColumn(4).setMaxWidth(0);

        //ativando controles
        btnAtributo.setEnabled(false);
        if (entidade.getAtributos().size() > 0) {
            modelAtributo.setRowCount(0);
            for (Atributo atributo : entidade.getAtributos()) {
                modelAtributo.setRowCount(modelAtributo.getRowCount() + 1);
                modelAtributo.setValueAt(atributo.getNome(), modelAtributo.getRowCount() - 1, 0);
                modelAtributo.setValueAt(atributo.getTipo(), modelAtributo.getRowCount() - 1, 1);
                modelAtributo.setValueAt(atributo.getTamanho(), modelAtributo.getRowCount() - 1, 2);
                modelAtributo.setValueAt(atributo.getPrecisao(), modelAtributo.getRowCount() - 1, 3);
                modelAtributo.setValueAt(atributo, modelAtributo.getRowCount() - 1, 4);
            }
            tblAtributos.setModel(modelAtributo);
            btnAtributo.setEnabled(true);
        }
    }

    public void LimparTela() {

        this.consultaSelecionada = null;
        //crinando um novo objeto
        this.consultaAtual = new Consulta();

        AtualizarConexao();
        cbxConexão.setSelectedIndex(-1);

        txtNome.setText("");
        txtDescricao.setText("");
        txtConsulta.setText("");
        txtEntidade.setText("");

        CarregaAtributos();

        //ativando controles
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);

    }

    public void AtualizaAtual() {

        Object[] model = cbxConexão.getSelectedObjects();
        if (model.length > 0) {
            Conexao conexao = (Conexao) model[0];
            this.consultaAtual.setConexao(conexao);
        }

        this.consultaAtual.setNome(txtNome.getText());
        this.consultaAtual.setDescricao(txtDescricao.getText());
        this.consultaAtual.setSql(txtConsulta.getText());
        this.consultaAtual.getEntidade().setNome(txtEntidade.getText());

    }

    public void SetConsultaSelecionada(Consulta consulta) {

        this.consultaSelecionada = consulta;
        this.consultaAtual = consulta.copia();

        AtualizarConexao();

        if (this.consultaAtual != null) {

            cbxConexão.setSelectedItem(consultaAtual.getConexao());
            txtNome.setText(consultaAtual.getNome());
            txtDescricao.setText(consultaAtual.getDescricao());
            txtConsulta.setText(consultaAtual.getSql());
            txtEntidade.setText(consultaAtual.getEntidade().getNome());
            CarregaAtributos();

            //ativando controles
            btnNovo.setEnabled(true);
            btnSalvar.setEnabled(true);
            btnExcluir.setEnabled(true);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxConexão = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtEntidade = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtConsulta = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnTeste = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtributo = new javax.swing.JButton();
        btnGerarEntidadeSA = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão de Consultas");

        jLabel1.setText("Nome:");

        jLabel2.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel3.setText("Entidade:");

        jLabel9.setText("Conexão:");

        jLabel4.setText("Atributos:");

        tblAtributos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Tipo", "Tamanho", "Precisão", "Objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAtributos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAtributosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAtributos);
        if (tblAtributos.getColumnModel().getColumnCount() > 0) {
            tblAtributos.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel5.setText("Consulta:");

        txtConsulta.setColumns(20);
        txtConsulta.setRows(5);
        jScrollPane4.setViewportView(txtConsulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(txtEntidade)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxConexão, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxConexão, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnTeste.setText("Executar Consulta");
        btnTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesteActionPerformed(evt);
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

        btnAtributo.setText("Atributo");
        btnAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtributoActionPerformed(evt);
            }
        });

        btnGerarEntidadeSA.setText("Gerar destino em SA");
        btnGerarEntidadeSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarEntidadeSAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTeste)
                .addGap(18, 18, 18)
                .addComponent(btnGerarEntidadeSA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtributo)
                .addGap(38, 38, 38)
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnTeste)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnAtributo)
                    .addComponent(btnGerarEntidadeSA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        if (this.consultaSelecionada != null) {
            controle.getConsultas().remove(consultaSelecionada);
            this.consultaSelecionada = null;
            dispose();
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        LimparTela();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        AtualizaAtual();
        controle.addConsulta(this.consultaSelecionada, this.consultaAtual);
        this.consultaSelecionada = this.consultaAtual;

        JOptionPane.showMessageDialog(this, "Consulta salva com sucesso.");

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesteActionPerformed

        AtualizaAtual();

        if (consultaAtual != null) {
            try {
                Connection conn = ConnectionManager.getConnection(this.parent, this.consultaAtual.getConexao());
                Statement statement;
                if (conn != null) {

                    statement = conn.createStatement();
                    statement.setFetchSize(10);
                    ResultSet resultSet = statement.executeQuery(this.consultaAtual.getSql());
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    List<Atributo> atributos = new ArrayList<Atributo>();
                    //laço para inserir os dados dos objetos na Tabela
                    for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                        Atributo atributo = new Atributo();
                        atributo.setNome(metaData.getColumnName(i));
                        atributo.setTipo(metaData.getColumnTypeName(i));
                        atributo.setPrecisao(metaData.getScale(i));
                        atributo.setTamanho(metaData.getColumnDisplaySize(i));
                        atributos.add(atributo);
                    }

                    resultSet.close();
                    statement.close();
                    conn.close();
                    Entidade entidade = this.consultaAtual.getEntidade();
                    entidade.setAtributos(atributos);
                    this.consultaAtual.setEntidade(entidade);
                    CarregaAtributos();

                    JOptionPane.showMessageDialog(this, "Consulta executada com sucesso, a lista de campos foi atualizada.");
                }

            } catch (SQLException ex) {
                FrmMensagem frmMensagem = new FrmMensagem(parent, true);
                frmMensagem.Mostrar(ex.toString());
                frmMensagem.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTesteActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed

        dispose();

    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtributoActionPerformed

        if (this.atributoSelecionado != null) {
            FrmAtributo frmAtributo = new FrmAtributo(this.parent, true);
            frmAtributo.controle = this.controle;
            frmAtributo.entidadeSelecionada = consultaAtual.getEntidade();
            frmAtributo.LimparTela();
            frmAtributo.SetAtributoSelecionado(this.atributoSelecionado, "CO");
            frmAtributo.pack();
            frmAtributo.setLocationRelativeTo(null);
            frmAtributo.setVisible(true);
            CarregaAtributos();
        }
    }//GEN-LAST:event_btnAtributoActionPerformed

    private void tblAtributosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAtributosMouseClicked
        // TODO add your handling code here:
        Atributo tempAtributo = new Atributo();
        if (tblAtributos.getSelectedRow() >= 0) {
            for (Atributo atributo : this.consultaAtual.getEntidade().getAtributos()) {
                if (atributo.equals((Atributo) tblAtributos.getValueAt(tblAtributos.getSelectedRow(), 4))) {
                    tempAtributo = atributo;
                }
            }
        }
        if (this.atributoSelecionado != null) {
            if (this.atributoSelecionado.equals(tempAtributo)) {
                btnAtributoActionPerformed(null);
            } else {
                this.atributoSelecionado = tempAtributo;
            }
        } else {
            this.atributoSelecionado = tempAtributo;
        }
    }//GEN-LAST:event_tblAtributosMouseClicked

    private void btnGerarEntidadeSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarEntidadeSAActionPerformed
        // TODO add your handling code here:
        AtualizaAtual();
        if (this.consultaSelecionada.equals(this.consultaAtual)) {

            MapeamentoSA mapeamentoSA = null;
            for (MapeamentoSA mapa : this.controle.getMapeamentosSA()) {
                if (mapa.getConsultaOrigem().equals(this.consultaSelecionada)) {
                    mapeamentoSA = mapa;
                }
            }

            if (mapeamentoSA == null) {

                Entidade novaEntidade = new Entidade();
                novaEntidade = this.consultaSelecionada.getEntidade().copia();
                novaEntidade.setNome("SA_"+novaEntidade.getNome());
                novaEntidade.AddAtributosSA();
                this.controle.AddEntidadeSA(null, novaEntidade);

                mapeamentoSA = new MapeamentoSA();
                mapeamentoSA.setConsultaOrigem(this.consultaSelecionada);
                mapeamentoSA.setEntidadeDestino(novaEntidade);
                this.controle.AddMapeamentoSA(null, mapeamentoSA);

                JOptionPane.showMessageDialog(this, "Entidade copiada para Staging Area.");
            }

            FrmEntidadeSA frmEntidadeSA = new FrmEntidadeSA(this.parent, true);
            frmEntidadeSA.controle = this.controle;
            frmEntidadeSA.mapeamentoSelecionado = mapeamentoSA;
            frmEntidadeSA.LimparTela();
            frmEntidadeSA.SetEntidadeSelecionada(mapeamentoSA.getEntidadeDestino());
            frmEntidadeSA.pack();
            frmEntidadeSA.setLocationRelativeTo(null);
            frmEntidadeSA.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Salve a consulta antes de acessar o mapeamento para Staging Area.");
        }

    }//GEN-LAST:event_btnGerarEntidadeSAActionPerformed

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
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmConsulta dialog = new FrmConsulta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAtributo;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGerarEntidadeSA;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnTeste;
    private javax.swing.JComboBox<String> cbxConexão;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblAtributos;
    private javax.swing.JTextArea txtConsulta;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtEntidade;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
