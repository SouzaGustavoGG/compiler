/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author eerissi
 */
public class CompiladorTela extends javax.swing.JFrame {

	private ControladorIde controller = new ControladorIde(); 
    /**
     * Creates new form NewJFrame
     */
    public CompiladorTela() {
        initComponents();
        setTitle("Compilador - novo.djt");
        jTextEntrada.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();
                int linenum = 1;
                int columnnum = 1;
                try {

                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);
                    linenum += 1;
                }
                catch(Exception ex) { }
                jLabelLinhaColuna.setText("linha: "+ linenum + ", coluna: "+  columnnum);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextSaída = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextEntrada = new javax.swing.JTextArea();
        jButtonSalvarComoIcon = new javax.swing.JButton();
        jButtonSairIcon = new javax.swing.JButton();
        jButtonAbrirIcon = new javax.swing.JButton();
        jButtonNovoIcon = new javax.swing.JButton();
        jButtonSalvarIcon = new javax.swing.JButton();
        jButtonCopiarIcon = new javax.swing.JButton();
        jButtonColarIcon = new javax.swing.JButton();
        jButtonRecortarIcon = new javax.swing.JButton();
        jButtonCompilarIcon = new javax.swing.JButton();
        jButtonExecutarIcon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonSobreIcon = new javax.swing.JButton();
        jLabelLinhaColuna = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuAquivo = new javax.swing.JMenu();
        jMenuNovo = new javax.swing.JMenuItem();
        jMenuAbrir = new javax.swing.JMenuItem();
        jMenuSalvar = new javax.swing.JMenuItem();
        jMenuSalvarComo = new javax.swing.JMenuItem();
        jMenuSair = new javax.swing.JMenuItem();
        jMenuEdicao = new javax.swing.JMenu();
        jMenuCopiar = new javax.swing.JMenuItem();
        jMenuColar = new javax.swing.JMenuItem();
        jMenuRecortar = new javax.swing.JMenuItem();
        jMenuCompilacao = new javax.swing.JMenu();
        jMenuCompilar = new javax.swing.JMenuItem();
        jMenuExecutar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");

        jTextSaída.setEditable(false);
        jTextSaída.setColumns(20);
        jTextSaída.setRows(5);
        jScrollPane1.setViewportView(jTextSaída);

        jTextEntrada.setColumns(20);
        jTextEntrada.setRows(5);
        jScrollPane2.setViewportView(jTextEntrada);

        jButtonSalvarComoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-as.png"))); // NOI18N
        jButtonSalvarComoIcon.setToolTipText("Salvar Como");
        jButtonSalvarComoIcon.setBorder(null);
        jButtonSalvarComoIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarComoIconActionPerformed(evt);
            }
        });

        jButtonSairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButtonSairIcon.setToolTipText("Sair");
        jButtonSairIcon.setBorder(null);
        jButtonSairIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairIconActionPerformed(evt);
            }
        });

        jButtonAbrirIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.png"))); // NOI18N
        jButtonAbrirIcon.setToolTipText("Abrir");
        jButtonAbrirIcon.setBorderPainted(false);
        jButtonAbrirIcon.setFocusable(false);
        jButtonAbrirIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrirIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrirIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirIconActionPerformed(evt);
            }
        });

        jButtonNovoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png"))); // NOI18N
        jButtonNovoIcon.setToolTipText("Novo");
        jButtonNovoIcon.setFocusable(false);
        jButtonNovoIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNovoIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNovoIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoIconActionPerformed(evt);
            }
        });

        jButtonSalvarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        jButtonSalvarIcon.setToolTipText("Salvar");
        jButtonSalvarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarIconActionPerformed(evt);
            }
        });

        jButtonCopiarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.png"))); // NOI18N
        jButtonCopiarIcon.setToolTipText("Copiar");
        jButtonCopiarIcon.setFocusable(false);
        jButtonCopiarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCopiarIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCopiarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopiarIconActionPerformed(evt);
            }
        });

        jButtonColarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.png"))); // NOI18N
        jButtonColarIcon.setToolTipText("Colar");
        jButtonColarIcon.setFocusable(false);
        jButtonColarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonColarIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonColarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColarIconActionPerformed(evt);
            }
        });

        jButtonRecortarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.png"))); // NOI18N
        jButtonRecortarIcon.setToolTipText("Recortar");
        jButtonRecortarIcon.setFocusable(false);
        jButtonRecortarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRecortarIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRecortarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecortarIconActionPerformed(evt);
            }
        });

        jButtonCompilarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compile.png"))); // NOI18N
        jButtonCompilarIcon.setToolTipText("Compilar");
        jButtonCompilarIcon.setFocusable(false);
        jButtonCompilarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCompilarIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCompilarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompilarIconActionPerformed(evt);
            }
        });

        jButtonExecutarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.png"))); // NOI18N
        jButtonExecutarIcon.setToolTipText("Executar");
        jButtonExecutarIcon.setFocusable(false);
        jButtonExecutarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonExecutarIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonExecutarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecutarIconActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonSobreIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about.png"))); // NOI18N
        jButtonSobreIcon.setToolTipText("Sobre");
        jButtonSobreIcon.setFocusable(false);
        jButtonSobreIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSobreIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSobreIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSobreIconActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 255));

        jMenuAquivo.setText("Arquivo");

        jMenuNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png"))); // NOI18N
        jMenuNovo.setText("Novo");
        jMenuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovoActionPerformed(evt);
            }
        });
        jMenuAquivo.add(jMenuNovo);

        jMenuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.png"))); // NOI18N
        jMenuAbrir.setText("Abrir");
        jMenuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAbrirActionPerformed(evt);
            }
        });
        jMenuAquivo.add(jMenuAbrir);

        jMenuSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        jMenuSalvar.setText("Salvar");
        jMenuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSalvarActionPerformed(evt);
            }
        });
        jMenuAquivo.add(jMenuSalvar);

        jMenuSalvarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuSalvarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-as.png"))); // NOI18N
        jMenuSalvarComo.setText("Salvar Como");
        jMenuSalvarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSalvarComoActionPerformed(evt);
            }
        });
        jMenuAquivo.add(jMenuSalvarComo);

        jMenuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jMenuSair.setText("Sair");
        jMenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSairActionPerformed(evt);
            }
        });
        jMenuAquivo.add(jMenuSair);

        jMenuBar1.add(jMenuAquivo);

        jMenuEdicao.setText("Edição");

        jMenuCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.png"))); // NOI18N
        jMenuCopiar.setText("Copiar");
        jMenuCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCopiarActionPerformed(evt);
            }
        });
        jMenuEdicao.add(jMenuCopiar);

        jMenuColar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuColar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.png"))); // NOI18N
        jMenuColar.setText("Colar");
        jMenuColar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuColarActionPerformed(evt);
            }
        });
        jMenuEdicao.add(jMenuColar);

        jMenuRecortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuRecortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.png"))); // NOI18N
        jMenuRecortar.setText("Recortar");
        jMenuRecortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRecortarActionPerformed(evt);
            }
        });
        jMenuEdicao.add(jMenuRecortar);

        jMenuBar1.add(jMenuEdicao);

        jMenuCompilacao.setText("Compilação");

        jMenuCompilar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compile.png"))); // NOI18N
        jMenuCompilar.setText("Compilar");
        jMenuCompilacao.add(jMenuCompilar);

        jMenuExecutar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuExecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.png"))); // NOI18N
        jMenuExecutar.setText("Executar");
        jMenuExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExecutarActionPerformed(evt);
            }
        });
        jMenuCompilacao.add(jMenuExecutar);

        jMenuBar1.add(jMenuCompilacao);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtonNovoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAbrirIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvarComoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSairIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCopiarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonColarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRecortarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCompilarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExecutarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSobreIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelLinhaColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 209, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNovoIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSalvarIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSalvarComoIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAbrirIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSairIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCopiarIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonColarIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRecortarIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCompilarIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExecutarIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSobreIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabelLinhaColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAbrirActionPerformed
        jButtonAbrirIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuAbrirActionPerformed
//ok
    private void jButtonExecutarIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecutarIconActionPerformed
        JOptionPane.showMessageDialog(null, "A opção Executar será especificada posteriormente");
    }//GEN-LAST:event_jButtonExecutarIconActionPerformed
//ok
    private void jButtonNovoIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoIconActionPerformed
        nome_arquivo = controller.novoArquivo(jTextEntrada,jTextSaída, this, nome_arquivo, diretorio);
        
//diretorio = "";
    }//GEN-LAST:event_jButtonNovoIconActionPerformed
//ok
    private void jMenuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoActionPerformed
        jButtonNovoIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuNovoActionPerformed
//ok
    private void jButtonRecortarIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecortarIconActionPerformed
      jTextEntrada.cut();
    }//GEN-LAST:event_jButtonRecortarIconActionPerformed
//ok
    private void jButtonColarIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColarIconActionPerformed
        jTextEntrada.paste();
    }//GEN-LAST:event_jButtonColarIconActionPerformed
//ok
    private void jButtonCopiarIconActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        jTextEntrada.copy();
    }                                                 

    private void jButtonCompilarIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopiarIconActionPerformed
        if("".equals(jTextEntrada.getText())){
            JOptionPane.showMessageDialog(null, "Um arquivo vazio não pode ser compilado!");
        }else{    
            LexicAnalyzer lexic_analyzer = new LexicAnalyzer();
            String output = lexic_analyzer.analyze(jTextEntrada.getText());

            if (output.equals("")){
                SyntacticAnalyzer syntatic_analyzer = SyntacticAnalyzer.getInstance();
                jTextSaída.setText(syntatic_analyzer.analyze(jTextEntrada.getText()));
            } else {
                jTextSaída.setText(output);
            }

        }
    }//GEN-LAST:event_jButtonCopiarIconActionPerformed
//ok
    private void jMenuCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCopiarActionPerformed
        jButtonCopiarIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuCopiarActionPerformed
//ok
    private void jMenuColarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        jButtonColarIconActionPerformed(evt);
    }                                          
//ok
    private void jMenuRecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRecortarActionPerformed
        jButtonRecortarIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuRecortarActionPerformed

    private void jButtonAbrirIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirIconActionPerformed
        ArrayList<String> obj_abrir = new ArrayList<>();
        obj_abrir  = controller.abrir(jTextEntrada, jTextSaída, nome_arquivo, diretorio, this);
        if (obj_abrir.size()==2){
            nome_arquivo = obj_abrir.get(0);
            diretorio = obj_abrir.get(1);
        }
    }//GEN-LAST:event_jButtonAbrirIconActionPerformed
//ok
    private void jButtonSalvarIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarIconActionPerformed

        ArrayList<String> obj_salvar = controller.salvar(nome_arquivo, diretorio, jTextEntrada, this);
        if (obj_salvar.size()==2){
            nome_arquivo = obj_salvar.get(0);
            diretorio = obj_salvar.get(1);
        }
        
    }//GEN-LAST:event_jButtonSalvarIconActionPerformed
        
    private void jMenuCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuColarActionPerformed
        jButtonCompilarIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuColarActionPerformed

    private void jMenuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSalvarActionPerformed
        jButtonSalvarIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuSalvarActionPerformed

    private void jButtonSalvarComoIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarComoIconActionPerformed
        ArrayList<String> obj_salvar_como = controller.salvarComo(jTextEntrada, nome_arquivo, diretorio, this);
        if (obj_salvar_como.size()==2){
            nome_arquivo = obj_salvar_como.get(0);
            diretorio = obj_salvar_como.get(1);
        }
    }//GEN-LAST:event_jButtonSalvarComoIconActionPerformed

    private void jMenuSalvarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSalvarComoActionPerformed
        jButtonSalvarComoIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuSalvarComoActionPerformed

    private void jButtonSairIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairIconActionPerformed
        controller.sair(jTextEntrada, nome_arquivo, diretorio, this);
    }//GEN-LAST:event_jButtonSairIconActionPerformed

    private void jMenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSairActionPerformed
        jButtonSairIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuSairActionPerformed

    private void jMenuExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExecutarActionPerformed
        jButtonExecutarIconActionPerformed(evt);
    }//GEN-LAST:event_jMenuExecutarActionPerformed

    private void jButtonSobreIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSobreIconActionPerformed
        JOptionPane.showMessageDialog(null, "Desenvolvido por:\nGustavo Souza\nMaykon Anschau\nÉvelyn Rissi");
    }//GEN-LAST:event_jButtonSobreIconActionPerformed
    
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
            java.util.logging.Logger.getLogger(CompiladorTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompiladorTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompiladorTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompiladorTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompiladorTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirIcon;
    private javax.swing.JButton jButtonColarIcon;
    private javax.swing.JButton jButtonCompilarIcon;
    private javax.swing.JButton jButtonCopiarIcon;
    private javax.swing.JButton jButtonExecutarIcon;
    private javax.swing.JButton jButtonNovoIcon;
    private javax.swing.JButton jButtonRecortarIcon;
    private javax.swing.JButton jButtonSairIcon;
    private javax.swing.JButton jButtonSalvarComoIcon;
    private javax.swing.JButton jButtonSalvarIcon;
    private javax.swing.JButton jButtonSobreIcon;
    private javax.swing.JLabel jLabelLinhaColuna;
    private javax.swing.JMenuItem jMenuAbrir;
    private javax.swing.JMenu jMenuAquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuColar;
    private javax.swing.JMenu jMenuCompilacao;
    private javax.swing.JMenuItem jMenuCompilar;
    private javax.swing.JMenuItem jMenuCopiar;
    private javax.swing.JMenu jMenuEdicao;
    private javax.swing.JMenuItem jMenuExecutar;
    private javax.swing.JMenuItem jMenuNovo;
    private javax.swing.JMenuItem jMenuRecortar;
    private javax.swing.JMenuItem jMenuSair;
    private javax.swing.JMenuItem jMenuSalvar;
    private javax.swing.JMenuItem jMenuSalvarComo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextEntrada;
    private javax.swing.JTextArea jTextSaída;
    // End of variables declaration//GEN-END:variables
String nome_arquivo = "novo.djt";
String diretorio = "";
    
}
