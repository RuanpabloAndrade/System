/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package viw;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.modelclientes;
import model.Modelrecebiveis;
import Controler.Controlerrecebiveis;
import java.awt.Component;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author ruan
 */
public class Vizualizarcontasreceber extends javax.swing.JFrame {
List<Modelrecebiveis> listarecebivel = new ArrayList<>();
Controlerrecebiveis recebiveiscontroler = new Controlerrecebiveis();
Modelrecebiveis recebieisexibição = new Modelrecebiveis();
List<Modelrecebiveis> Selecaocontas = new ArrayList<>();
    /**
     * Creates new form Vizualizarcontasreceber
     */
    public Vizualizarcontasreceber() {
        initComponents();
         setLocationRelativeTo(this);
         Designtabelacontasreceber();
         carregarcontasrecebivel();
    }
    
    private void carregarcontasrecebivel(){
        listarecebivel = recebiveiscontroler.Listarrecebivel();
        DefaultTableModel modelo = (DefaultTableModel) tabelacontasreceber.getModel();
        modelo.setNumRows(0);
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        for (int i = 0; i <  listarecebivel.size(); i++) {
             modelo.addRow(new Object[]{
                listarecebivel.get(i).getCod(), 
                listarecebivel.get(i).getNomecliente(),
                listarecebivel.get(i).getDescricao(), 
                formatoMoeda.format(listarecebivel.get(i).getValor()),
                listarecebivel.get(i).getVencimento(),
                formatoMoeda.format(listarecebivel.get(i).getJuros()),
                listarecebivel.get(i).getIdconta()//coluna oculta 
            });
             tabelacontasreceber.getColumnModel().getColumn(6).setMinWidth(0);///coluna oculta /
             tabelacontasreceber.getColumnModel().getColumn(6).setMaxWidth(0);//coluna oculta 
             tabelacontasreceber.getColumnModel().getColumn(6).setWidth(0);//coluna oculta para deixar invisicel 
        }
        int colunaVencimento = 4;//para deixar vencimento em vermelho
tabelacontasreceber.getColumnModel().getColumn(colunaVencimento)
    .setCellRenderer(new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            try {
                if (value != null) { // <<<<<<<<<<<<<< ADICIONE ESTA VERIFICAÇÃO
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date vencimento = sdf.parse(value.toString());
                    Date hoje = new Date();

                    if (vencimento.before(hoje)) {
                        c.setForeground(Color.RED); // 🔴 Texto vermelho se vencido
                    } else {
                        c.setForeground(Color.BLACK); // Texto normal
                    }
                } else {
                    // Se value é null, define cores padrão
                    c.setForeground(Color.BLACK);
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    c.setBackground(Color.WHITE);
                }

            } catch (ParseException e) {
                c.setForeground(Color.BLACK);
                c.setBackground(Color.WHITE);
            }

            return c;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Quitarconta = new javax.swing.JButton();
        Exibircontas = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pesquisarconta = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacontasreceber = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel1.setText("Gerenciamento de Recebíveis");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar (1).png"))); // NOI18N
        jButton1.setText("Nova Conta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Quitarconta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Quitarconta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/apagar (1).png"))); // NOI18N
        Quitarconta.setText("Quitar");
        Quitarconta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarcontaActionPerformed(evt);
            }
        });

        Exibircontas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Exibircontas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ca (2).png"))); // NOI18N
        Exibircontas.setText("Exibir Conta");
        Exibircontas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibircontasActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorio (1).png"))); // NOI18N
        jButton4.setText("Relatório");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair (1).png"))); // NOI18N
        jButton7.setText("Sair");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Quitarconta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Exibircontas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quitarconta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exibircontas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel2.setText("Pesquisar Conta: ");

        pesquisarconta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pesquisarconta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pesquisarconta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisarcontaKeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Vencimento", "Valor" }));
        jComboBox1.setToolTipText("");
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel3.setText("Pesquisar Por:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(pesquisarconta, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarconta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tabelacontasreceber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabelacontasreceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Parcela/Descrição", "Valor Original", "Vencimento", "Valor Com juros", "idconta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacontasreceber.setFocusable(false);
        tabelacontasreceber.setRowHeight(25);
        tabelacontasreceber.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tabelacontasreceber.setShowVerticalLines(false);
        tabelacontasreceber.getTableHeader().setReorderingAllowed(false);
        tabelacontasreceber.setVerifyInputWhenFocusTarget(false);
        tabelacontasreceber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelacontasreceberKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelacontasreceber);
        if (tabelacontasreceber.getColumnModel().getColumnCount() > 0) {
            tabelacontasreceber.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void  Designtabelacontasreceber(){
tabelacontasreceber.getColumnModel().getColumn(0).setPreferredWidth(50);        
tabelacontasreceber.getColumnModel().getColumn(1).setPreferredWidth(150);
tabelacontasreceber.getColumnModel().getColumn(2).setPreferredWidth(280);
tabelacontasreceber.getColumnModel().getColumn(3).setPreferredWidth(90);
tabelacontasreceber.getColumnModel().getColumn(4).setPreferredWidth(100);
tabelacontasreceber.getColumnModel().getColumn(5).setPreferredWidth(110);
       tabelacontasreceber.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
       tabelacontasreceber.getTableHeader().setOpaque(false);
       tabelacontasreceber.getTableHeader().setBackground(new Color(32, 136, 203));
       tabelacontasreceber.getTableHeader().setForeground( new Color(255,255,255));
       tabelacontasreceber.setRowHeight(25);
    }
            
          
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Contasreceber receber = new Contasreceber();
       receber.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelacontasreceberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelacontasreceberKeyReleased
       
    }//GEN-LAST:event_tabelacontasreceberKeyReleased

    private void pesquisarcontaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarcontaKeyReleased
       DefaultTableModel model = (DefaultTableModel) tabelacontasreceber.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tabelacontasreceber.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(pesquisarconta.getText()));
    }//GEN-LAST:event_pesquisarcontaKeyReleased

    private void QuitarcontaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarcontaActionPerformed
        int linha = tabelacontasreceber.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado!");
            return;
        }
        int codigo=(int) tabelacontasreceber.getValueAt(linha,0);
        int idconta = (int) tabelacontasreceber.getValueAt(linha,6);
           int entrada = JOptionPane.showConfirmDialog(null, "Deseja Quitar essa conta?", "Confirmação", JOptionPane.YES_NO_OPTION);
           if(entrada==JOptionPane.YES_OPTION){
               recebiveiscontroler.excluirConta(codigo, idconta); 
               carregarcontasrecebivel();
               JOptionPane.showMessageDialog(null,"Conta Quitada!");
           }
    }//GEN-LAST:event_QuitarcontaActionPerformed

    private void ExibircontasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibircontasActionPerformed

        
        int linha = tabelacontasreceber.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado!");
            return;
        }
        int codigo = (int) tabelacontasreceber.getValueAt(linha,0);
        int id_codigo = (int) tabelacontasreceber.getValueAt(linha,6);
        recebieisexibição = recebiveiscontroler.ExibirContas(codigo);
        Selecaocontas = recebiveiscontroler.Exibirselecao(id_codigo);
        if(recebieisexibição != null){
                Contasreceber exibircontas = new Contasreceber();
                exibircontas.carregarClientesCombo();//segunda alteração para setar um cliente na combobox pelo outro jframe 
                exibircontas.preechercamposconta(recebieisexibição);
                exibircontas.selecaodeconta(Selecaocontas);
                exibircontas.setVisible(true);
        }
    }//GEN-LAST:event_ExibircontasActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vizualizarcontasreceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vizualizarcontasreceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vizualizarcontasreceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vizualizarcontasreceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vizualizarcontasreceber().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exibircontas;
    private javax.swing.JButton Quitarconta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField pesquisarconta;
    private javax.swing.JTable tabelacontasreceber;
    // End of variables declaration//GEN-END:variables
}
