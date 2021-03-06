/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.gavin.ui;

import cn.gavin.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author luoyuan
 */
public class ClientMainView extends javax.swing.JFrame {

    /**
     * Creates new form ClientMainView
     */
    public ClientMainView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        prevPageButton = new javax.swing.JButton();
        nextPageButton = new javax.swing.JButton();
        currentPage = new javax.swing.JTextField();
        totalPage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        searchMenu = new javax.swing.JMenu();
        searchMeunItem = new javax.swing.JMenuItem();
        quitMenu = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(sqlite!=null){
                    sqlite.close();
                }
                System.exit(0);
            }
        });
        setTitle("数据列表");
        setResizable(false);
//
//        dataTable.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null}
//                },
//                new String[]{
//                        "Title 1", "Title 2", "Title 3", "Title 4"
//                }
//        ));
        jScrollPane1.setViewportView(dataTable);

        prevPageButton.setText("上一页");
        prevPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevPageButtonActionPerformed(evt);
            }
        });

        nextPageButton.setText("下一页");
        nextPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageButtonActionPerformed(evt);
            }
        });

        currentPage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        currentPage.setText("10");
        currentPage.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }

            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                currentPageInputMethodTextChanged(evt);
            }
        });

        totalPage.setText("1000");

        jLabel2.setText("/");

        searchMenu.setText("选项");

        searchMeunItem.setText("查找");
        searchMeunItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMenuItemActionPerformed(evt);
            }
        });
        searchMenu.add(searchMeunItem);

        quitMenu.setText("退出");
        quitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuActionPerformed(evt);
            }
        });
        searchMenu.add(quitMenu);

        jMenuBar1.add(searchMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(prevPageButton)
                                .addGap(133, 133, 133)
                                .addComponent(currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalPage, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nextPageButton)
                                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(prevPageButton)
                                        .addComponent(nextPageButton)
                                        .addComponent(currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalPage)
                                        .addComponent(jLabel2))
                                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageButtonActionPerformed
        int total = Integer.parseInt(totalPage.getText());
        if (page < total) {
            nextPageButton.setEnabled(true);
            page++;
            loadData(page);
        } else {
            nextPageButton.setEnabled(false);

        }
        if(page > 1){
            prevPageButton.setEnabled(true);
        }
    }//GEN-LAST:event_nextPageButtonActionPerformed

    private void prevPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevPageButtonActionPerformed
        if (page > 1) {
            prevPageButton.setEnabled(true);
            page -= 1;
            loadData(page);
        } else {
            prevPageButton.setEnabled(false);
        }
        if(page < Integer.parseInt(totalPage.getText())){
            nextPageButton.setEnabled(true);
        }
    }//GEN-LAST:event_prevPageButtonActionPerformed

    private void currentPageInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_currentPageInputMethodTextChanged

    }//GEN-LAST:event_currentPageInputMethodTextChanged

    private void searchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void quitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuActionPerformed
        sqlite.close();
        this.dispose();
    }//GEN-LAST:event_quitMenuActionPerformed

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
            java.util.logging.Logger.getLogger(ClientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientMainView clientMainView = new ClientMainView();
                clientMainView.checkRegister();
            }
        });
    }

    public void setVisible(boolean show) {
        if (show) {
            try {
                decode = new Decode(ComputeKey.readKey());
                initData();
            } catch (InvalidKeyException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        super.setVisible(show);
    }

    private void checkRegister() {
        readUtils = new ReadUtils();
        String key = ComputeKey.readKey();
        String source = readUtils.read("source");
        if (key.trim().isEmpty() || source == null || source.isEmpty()) {
            File file = new File(PathUtils.getJarRoot() + "/key");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    String publicKey = Decode.geneKeys();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(publicKey);
                    writer.flush();
                    writer.close();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            JDialog dialog = new JDialog(this);
            dialog.setSize(419, 189);
            KeyPanel keyPanel = new KeyPanel(this, dialog);
            dialog.add(keyPanel);
            dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            dialog.setUndecorated(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            setVisible(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField currentPage;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem searchMeunItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JButton prevPageButton;
    private javax.swing.JMenuItem quitMenu;
    private javax.swing.JMenu searchMenu;
    private javax.swing.JLabel totalPage;
    private Decode decode;
    private ReadUtils readUtils;
    private SqliteHelper sqlite;
    private int page;

    public ReadUtils getReadUtils() {
        return readUtils;
    }

    private void loadData(int page) {
        if (sqlite != null) {
            this.page = page;
            currentPage.setText(page + "");
            ArrayList<String[]> resultSet= sqlite.query(page, 50);
            Object[][] cells = new Object[50][11];
            try {
                int i = 0;
                for(String[] result : resultSet) {
                    Object[] cell = cells[i];
                    for (int j = 0; j < 11; j++) {
                        if(j != 0) {
                            cell[j] = decode.decode(result[j]);
                        }else{
                            cell[j] = result[j];
                        }
                        System.out.println(cell[j]);
                    }
                    cells[i] = cell;
                    i++;
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (BadPaddingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            TableModel model = dataTable.getModel();
            if (model == null || !(model instanceof DefaultTableModel) || model.getRowCount() == 0) {
                model = new DefaultTableModel(cells, new String[]{"id","联系人", "手机"
                        , "固定电话", "公司名称", "主营产品", "公司地址", "注册资本", "法人代表", "员工人数",
                        "创建时间"});
                dataTable.setModel(model);
            } else {
                ((DefaultTableModel) model).setRowCount(0);
                for (Object[] row : cells) {
                    ((DefaultTableModel) model).addRow(row);
                }
            }
        }
    }

    public void initData() {
        try {
            sqlite = new SqliteHelper(PathUtils.getJarRoot() + "/" + readUtils.read("source"));
            totalPage.setText(sqlite.getTotalPage(50) + "");
            loadData(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    // End of variables declaration//GEN-END:variables
}
