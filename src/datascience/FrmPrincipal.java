package datascience;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FrmPrincipal extends javax.swing.JFrame {

    public Controle controle;
    public Conexao conexaoSelecionado;
    public Consulta consultaSelecionada;
    public Entidade entidadeDWSelecionada;
    public Entidade entidadeSASelecionada;
    public MapeamentoDW mapeamentoDWSelecionada;
    public MapeamentoSA mapeamentoSASelecionada;

    public FrmPrincipal() {
        initComponents();
        controle = new Controle(this);
        controle.NovoJson();
        AtualizarTela();

    }

    private void AtualizarTela() {

        this.conexaoSelecionado = null;
        this.consultaSelecionada = null;
        this.entidadeDWSelecionada = null;
        this.entidadeSASelecionada = null;
        this.mapeamentoDWSelecionada = null;
        this.mapeamentoSASelecionada = null;

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Projeto");

        DefaultMutableTreeNode conexaoNode = new DefaultMutableTreeNode("Conexões");
        DefaultMutableTreeNode consultaNode = new DefaultMutableTreeNode("Consultas");
        DefaultMutableTreeNode entidadeSANode = new DefaultMutableTreeNode("Entidades Staging Area");
        DefaultMutableTreeNode entidadeDMNode = new DefaultMutableTreeNode("Entidades Data Mart");
        DefaultMutableTreeNode entidadeDLNode = new DefaultMutableTreeNode("Entidades Data Lake");
        DefaultMutableTreeNode entidadeDWNode = new DefaultMutableTreeNode("Entidades Data Warehouse");
        DefaultMutableTreeNode mapeamentoORtoSANode = new DefaultMutableTreeNode("Mapeamento Origem -> Staging Area");
        DefaultMutableTreeNode mapeamentoORtoDMNode = new DefaultMutableTreeNode("Mapeamento Origem -> Data Mart");
        DefaultMutableTreeNode mapeamentoORtoDLNode = new DefaultMutableTreeNode("Mapeamento Origem -> Data Lake");
        DefaultMutableTreeNode mapeamentoORtoDWNode = new DefaultMutableTreeNode("Mapeamento Origem -> Data Warehouse");
        DefaultMutableTreeNode mapeamentoSAtoDMNode = new DefaultMutableTreeNode("Mapeamento Staging Area -> Data Mart");
        DefaultMutableTreeNode mapeamentoSAtoDLNode = new DefaultMutableTreeNode("Mapeamento Staging Area -> Data Lake");
        DefaultMutableTreeNode mapeamentoSAtoDWNode = new DefaultMutableTreeNode("Mapeamento Staging Area -> Data Warehouse");
        DefaultMutableTreeNode mapeamentoDWtoDMNode = new DefaultMutableTreeNode("Mapeamento Data Warehouse -> Data Mart");
        DefaultMutableTreeNode mapeamentoDWtoDLNode = new DefaultMutableTreeNode("Mapeamento Data Warehouse -> Data Lake");

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

        for (Entidade entidade : controle.getEntidadesSA()) {
            itensNode = new DefaultMutableTreeNode(entidade.getNome());
            itensNode.setUserObject(entidade);
            entidadeSANode.add(itensNode);
        }

        for (Entidade entidade : controle.getEntidadesDW()) {
            itensNode = new DefaultMutableTreeNode(entidade.getNome());
            itensNode.setUserObject(entidade);
            entidadeDWNode.add(itensNode);
        }
        /*
        for (MapeamentoSA mapeamentoSA : controle.getMapeamentosSA()) {
            itensNode = new DefaultMutableTreeNode(mapeamentoSA.toString());
            itensNode.setUserObject(mapeamentoSA);
            mapeamentoSANode.add(itensNode);
        }

        for (MapeamentoDW mapeamentoDW : controle.getMapeamentosDW()) {
            itensNode = new DefaultMutableTreeNode(mapeamentoDW.toString());
            itensNode.setUserObject(mapeamentoDW);
            mapeamentoDWNode.add(itensNode);
        }
         */
        rootNode.add(conexaoNode);
        rootNode.add(consultaNode);
        rootNode.add(entidadeSANode);
        rootNode.add(entidadeDMNode);
        rootNode.add(entidadeDLNode);
        rootNode.add(entidadeDWNode);
        rootNode.add(mapeamentoORtoSANode);
        rootNode.add(mapeamentoORtoDMNode);
        rootNode.add(mapeamentoORtoDLNode);
        rootNode.add(mapeamentoORtoDWNode);
        rootNode.add(mapeamentoSAtoDMNode);
        rootNode.add(mapeamentoSAtoDLNode);
        rootNode.add(mapeamentoSAtoDWNode);
        rootNode.add(mapeamentoDWtoDMNode);
        rootNode.add(mapeamentoDWtoDLNode);

        DefaultTreeModel modelo = new DefaultTreeModel(rootNode);
        modelo.reload(rootNode);
        lstProjeto.setModel(modelo);

        for (int i = 0; i < lstProjeto.getRowCount(); ++i) {
            lstProjeto.expandRow(i);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mniExcluir = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProjeto = new javax.swing.JTree();
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
        mniGerenciarEntidadeSA = new javax.swing.JMenuItem();
        mniGerenciarEntidadeDW = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        mniMapeamentoSA = new javax.swing.JMenuItem();
        mniMapeamentoDW = new javax.swing.JMenuItem();

        mniExcluir.setText("Excluir");
        mniExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExcluirActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mniExcluir);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Construtor de Staging Area para processo de ETL");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        lstProjeto.setComponentPopupMenu(jPopupMenu1);
        lstProjeto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstProjetoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstProjeto);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );

        jMenu1.setText("Arquivo");

        mniNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mniNovo.setText("Novo Projeto");
        mniNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNovoActionPerformed(evt);
            }
        });
        jMenu1.add(mniNovo);

        mniAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mniAbrir.setText("Abrir Projeto");
        mniAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(mniAbrir);

        mniSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mniSalvar.setText("Salvar Projeto");
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

        mniGerarSqlStage.setText("Gerar SQL StagingArea");
        mniGerarSqlStage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerarSqlStageActionPerformed(evt);
            }
        });
        jMenu2.add(mniGerarSqlStage);

        mniGerarSqlDW.setText("Gerar SQL DataMart");
        mniGerarSqlDW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerarSqlDWActionPerformed(evt);
            }
        });
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

        mniGerenciarEntidadeSA.setText("Gerenciar Entidade Staging Area");
        mniGerenciarEntidadeSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerenciarEntidadeSAActionPerformed(evt);
            }
        });
        jMenu6.add(mniGerenciarEntidadeSA);

        mniGerenciarEntidadeDW.setText("Gerenciar Entidade Dimensão/Fato");
        mniGerenciarEntidadeDW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGerenciarEntidadeDWActionPerformed(evt);
            }
        });
        jMenu6.add(mniGerenciarEntidadeDW);

        jMenuBar1.add(jMenu6);

        jMenu9.setText("Mapeamento");

        mniMapeamentoSA.setText("Configurar Mapeamento StagingArea");
        mniMapeamentoSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMapeamentoSAActionPerformed(evt);
            }
        });
        jMenu9.add(mniMapeamentoSA);

        mniMapeamentoDW.setText("Configurar Mapeamento DataMart");
        mniMapeamentoDW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMapeamentoDWActionPerformed(evt);
            }
        });
        jMenu9.add(mniMapeamentoDW);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAbrirActionPerformed
        if ((controle.getConexoes().size() > 0) || (controle.getConsultas().size() > 0) || (controle.getEntidadesSA().size() > 0) || (controle.getEntidadesDW().size() > 0)) {

            int resultado = JOptionPane.showConfirmDialog(this, "Deseja salvar os dados antes de criar um novo", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resultado == JOptionPane.YES_OPTION) {
                controle.SalvarJson(this);
                controle.NovoJson();
                controle.CarregarJson(this);
            } else {
                controle.NovoJson();
                controle.CarregarJson(this);
            }
        } else {
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

        if ((controle.getConexoes().size() > 0) || (controle.getConsultas().size() > 0) || (controle.getEntidadesSA().size() > 0) || (controle.getEntidadesDW().size() > 0)) {

            int resultado = JOptionPane.showConfirmDialog(this, "Deseja salvar os dados antes de criar um novo", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resultado == JOptionPane.YES_OPTION) {
                controle.SalvarJson(this);
                controle.NovoJson();
                AtualizarTela();
            } else {
                controle.NovoJson();
                AtualizarTela();
            }

        } else {
            controle.NovoJson();
            AtualizarTela();
        }

    }//GEN-LAST:event_mniNovoActionPerformed

    private void mniGerenciarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarConexaoActionPerformed

        FrmConexao frmConexao = new FrmConexao(this, true);
        frmConexao.controle = this.controle;
        if (this.conexaoSelecionado != null) {
            frmConexao.SetConexaoSelecionada(this.conexaoSelecionado);
        }
        frmConexao.pack();
        frmConexao.setLocationRelativeTo(null);
        frmConexao.setVisible(true);
        AtualizarTela();

    }//GEN-LAST:event_mniGerenciarConexaoActionPerformed

    private void mniSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSalvarActionPerformed

        controle.SalvarJson(this);
        AtualizarTela();

    }//GEN-LAST:event_mniSalvarActionPerformed


    private void lstProjetoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProjetoMouseClicked

        DefaultMutableTreeNode selecao = (DefaultMutableTreeNode) lstProjeto.getLastSelectedPathComponent();

        if (selecao != null) {

            if (selecao.getUserObject() instanceof Conexao) {
                this.consultaSelecionada = null;
                this.entidadeSASelecionada = null;
                this.entidadeDWSelecionada = null;
                this.mapeamentoSASelecionada = null;
                this.mapeamentoDWSelecionada = null;
                if (this.conexaoSelecionado != null) {
                    if (this.conexaoSelecionado.equals((Conexao) selecao.getUserObject())) {
                        mniGerenciarConexaoActionPerformed(null);
                    } else {
                        this.conexaoSelecionado = (Conexao) selecao.getUserObject();
                    }
                } else {
                    this.conexaoSelecionado = (Conexao) selecao.getUserObject();
                }
            }

            if (selecao.getUserObject() instanceof Consulta) {
                this.conexaoSelecionado = null;
                this.entidadeSASelecionada = null;
                this.entidadeDWSelecionada = null;
                this.mapeamentoSASelecionada = null;
                this.mapeamentoDWSelecionada = null;
                if (this.consultaSelecionada != null) {
                    if (this.consultaSelecionada.equals((Consulta) selecao.getUserObject())) {
                        mniGerenciarConsultaActionPerformed(null);
                    } else {
                        this.consultaSelecionada = (Consulta) selecao.getUserObject();
                    }
                } else {
                    this.consultaSelecionada = (Consulta) selecao.getUserObject();
                }
            }

            if (selecao.getUserObject() instanceof Entidade) {
                if (selecao.getParent().toString().equals("Entidades StagingArea")) {
                    this.conexaoSelecionado = null;
                    this.consultaSelecionada = null;
                    this.entidadeDWSelecionada = null;
                    this.mapeamentoSASelecionada = null;
                    this.mapeamentoDWSelecionada = null;

                    if (this.entidadeSASelecionada != null) {
                        if (this.entidadeSASelecionada.equals((Entidade) selecao.getUserObject())) {
                            mniGerenciarEntidadeSAActionPerformed(null);
                        } else {
                            this.entidadeSASelecionada = (Entidade) selecao.getUserObject();
                        }
                    } else {
                        this.entidadeSASelecionada = (Entidade) selecao.getUserObject();
                    }
                }

                if (selecao.getParent().toString().equals("Entidades DataMart")) {
                    this.conexaoSelecionado = null;
                    this.consultaSelecionada = null;
                    this.entidadeSASelecionada = null;
                    this.mapeamentoSASelecionada = null;
                    this.mapeamentoDWSelecionada = null;

                    if (this.entidadeDWSelecionada != null) {
                        if (this.entidadeDWSelecionada.equals((Entidade) selecao.getUserObject())) {
                            mniGerenciarEntidadeDWActionPerformed(null);
                        } else {
                            this.entidadeDWSelecionada = (Entidade) selecao.getUserObject();
                        }
                    } else {
                        this.entidadeDWSelecionada = (Entidade) selecao.getUserObject();
                    }
                }
            }

            if (selecao.getUserObject() instanceof MapeamentoSA) {
                this.conexaoSelecionado = null;
                this.consultaSelecionada = null;
                this.entidadeSASelecionada = null;
                this.entidadeDWSelecionada = null;
                this.mapeamentoDWSelecionada = null;

                if (this.mapeamentoSASelecionada != null) {
                    if (this.mapeamentoSASelecionada.equals((MapeamentoSA) selecao.getUserObject())) {
                        mniMapeamentoSAActionPerformed(null);
                    } else {
                        this.mapeamentoSASelecionada = (MapeamentoSA) selecao.getUserObject();
                    }
                } else {
                    this.mapeamentoSASelecionada = (MapeamentoSA) selecao.getUserObject();
                }
            }

            if (selecao.getUserObject() instanceof MapeamentoDW) {
                this.conexaoSelecionado = null;
                this.consultaSelecionada = null;
                this.entidadeSASelecionada = null;
                this.entidadeDWSelecionada = null;
                this.mapeamentoSASelecionada = null;

                if (this.mapeamentoDWSelecionada != null) {
                    if (this.mapeamentoDWSelecionada.equals((MapeamentoDW) selecao.getUserObject())) {
                        mniMapeamentoDWActionPerformed(null);
                    } else {
                        this.mapeamentoDWSelecionada = (MapeamentoDW) selecao.getUserObject();
                    }
                } else {
                    this.mapeamentoDWSelecionada = (MapeamentoDW) selecao.getUserObject();
                }
            }
        }
    }//GEN-LAST:event_lstProjetoMouseClicked

    private void mniGerenciarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarConsultaActionPerformed
        // TODO add your handling code here:
        FrmConsulta frmConsulta = new FrmConsulta(this, true);
        frmConsulta.controle = this.controle;
        frmConsulta.LimparTela();
        if (this.consultaSelecionada != null) {
            frmConsulta.SetConsultaSelecionada(this.consultaSelecionada);
        }
        frmConsulta.pack();
        frmConsulta.setLocationRelativeTo(null);
        frmConsulta.setVisible(true);
        AtualizarTela();
    }//GEN-LAST:event_mniGerenciarConsultaActionPerformed

    private void mniMapeamentoSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMapeamentoSAActionPerformed
        // TODO add your handling code here:
        if (mapeamentoSASelecionada != null) {
            FrmMapeamentoConsultatoSA frmMapeamentoConsultatoSA = new FrmMapeamentoConsultatoSA(this, true);
            frmMapeamentoConsultatoSA.controle = this.controle;
            frmMapeamentoConsultatoSA.LimparTela();
            frmMapeamentoConsultatoSA.SetMapeamentoSelecionado(mapeamentoSASelecionada);
            frmMapeamentoConsultatoSA.pack();
            frmMapeamentoConsultatoSA.setLocationRelativeTo(null);
            frmMapeamentoConsultatoSA.setVisible(true);
            AtualizarTela();
        }
    }//GEN-LAST:event_mniMapeamentoSAActionPerformed

    private void mniGerarSqlStageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerarSqlStageActionPerformed
        // TODO add your handling code here:
        FrmGerarStagingArea frmGerarStagingArea = new FrmGerarStagingArea(this, true);
        frmGerarStagingArea.controle = this.controle;
        frmGerarStagingArea.AtualizarEntidadesSA();
        frmGerarStagingArea.pack();
        frmGerarStagingArea.setLocationRelativeTo(null);
        frmGerarStagingArea.setVisible(true);
    }//GEN-LAST:event_mniGerarSqlStageActionPerformed

    private void mniGerarSqlDWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerarSqlDWActionPerformed
        // TODO add your handling code here:
        FrmGerarDataWarehouse frmGerarDataWarehouse = new FrmGerarDataWarehouse(this, true);
        frmGerarDataWarehouse.controle = this.controle;
        frmGerarDataWarehouse.AtualizarEntidades();
        frmGerarDataWarehouse.pack();
        frmGerarDataWarehouse.setLocationRelativeTo(null);
        frmGerarDataWarehouse.setVisible(true);
    }//GEN-LAST:event_mniGerarSqlDWActionPerformed

    private void mniGerenciarEntidadeSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarEntidadeSAActionPerformed
        // TODO add your handling code here:
        if (entidadeSASelecionada != null) {
            FrmEntidadeSA frmEntidadeSA = new FrmEntidadeSA(this, true);
            frmEntidadeSA.controle = this.controle;

            for (MapeamentoSA mapaSA : this.controle.getMapeamentosSA()) {
                if (mapaSA.getEntidadeSADestino().equals(entidadeSASelecionada)) {
                    frmEntidadeSA.mapeamentoSelecionado = mapaSA;
                }
            }
            frmEntidadeSA.LimparTela();
            frmEntidadeSA.SetEntidadeSelecionada(entidadeSASelecionada);
            frmEntidadeSA.pack();
            frmEntidadeSA.setLocationRelativeTo(null);
            frmEntidadeSA.setVisible(true);
            AtualizarTela();
        }
    }//GEN-LAST:event_mniGerenciarEntidadeSAActionPerformed

    private void mniGerenciarEntidadeDWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGerenciarEntidadeDWActionPerformed
        // TODO add your handling code here:
        if (entidadeDWSelecionada != null) {
            FrmEntidadeDW frmEntidadeDW = new FrmEntidadeDW(this, true);
            frmEntidadeDW.controle = this.controle;

            for (MapeamentoDW mapaDW : this.controle.getMapeamentosDW()) {
                if (mapaDW.getEntidadeDWDestino().equals(entidadeDWSelecionada)) {
                    frmEntidadeDW.mapeamentoSelecionado = mapaDW;
                }
            }
            frmEntidadeDW.LimparTela();
            frmEntidadeDW.SetEntidadeSelecionada(entidadeDWSelecionada);
            frmEntidadeDW.pack();
            frmEntidadeDW.setLocationRelativeTo(null);
            frmEntidadeDW.setVisible(true);
            AtualizarTela();
        }
    }//GEN-LAST:event_mniGerenciarEntidadeDWActionPerformed

    private void mniMapeamentoDWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMapeamentoDWActionPerformed
        // TODO add your handling code here:
        if (mapeamentoDWSelecionada != null) {
            FrmMapeamentoSAtoDW frmMapeamentoSAtoDW = new FrmMapeamentoSAtoDW(this, true);
            frmMapeamentoSAtoDW.controle = this.controle;
            frmMapeamentoSAtoDW.LimparTela();
            frmMapeamentoSAtoDW.SetMapeamentoSelecionado(mapeamentoDWSelecionada);
            frmMapeamentoSAtoDW.pack();
            frmMapeamentoSAtoDW.setLocationRelativeTo(null);
            frmMapeamentoSAtoDW.setVisible(true);
            AtualizarTela();
        }
    }//GEN-LAST:event_mniMapeamentoDWActionPerformed

    private void mniExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExcluirActionPerformed
        // TODO add your handling code here:
        if (this.conexaoSelecionado != null) {
            controle.getConexoes().remove(conexaoSelecionado);
        }
        if (this.consultaSelecionada != null) {
            controle.getConsultas().remove(consultaSelecionada);
        }
        if (this.entidadeSASelecionada != null) {
            controle.getEntidadesSA().remove(entidadeSASelecionada);
        }
        if (this.entidadeDWSelecionada != null) {
            controle.getEntidadesDW().remove(entidadeDWSelecionada);
        }
        if (this.mapeamentoSASelecionada != null) {
            controle.getMapeamentosSA().remove(mapeamentoSASelecionada);
        }

        if (this.mapeamentoDWSelecionada != null) {
            controle.getMapeamentosDW().remove(mapeamentoDWSelecionada);
        }

        AtualizarTela();
    }//GEN-LAST:event_mniExcluirActionPerformed

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
                System.out.println(info.getName());
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree lstProjeto;
    private javax.swing.JMenuItem mniAbrir;
    private javax.swing.JMenuItem mniDefinicaoProjeto;
    private javax.swing.JMenuItem mniExcluir;
    private javax.swing.JMenuItem mniFechar;
    private javax.swing.JMenuItem mniGerarSqlDW;
    private javax.swing.JMenuItem mniGerarSqlStage;
    private javax.swing.JMenuItem mniGerenciarConexao;
    private javax.swing.JMenuItem mniGerenciarConsulta;
    private javax.swing.JMenuItem mniGerenciarEntidadeDW;
    private javax.swing.JMenuItem mniGerenciarEntidadeSA;
    private javax.swing.JMenuItem mniMapeamentoDW;
    private javax.swing.JMenuItem mniMapeamentoSA;
    private javax.swing.JMenuItem mniNovo;
    private javax.swing.JMenuItem mniSalvar;
    // End of variables declaration//GEN-END:variables
}
