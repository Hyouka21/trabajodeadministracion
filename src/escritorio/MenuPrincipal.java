/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escritorio;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.tree.DefaultMutableTreeNode;
import proyectojorge.control.Conexion;
import proyectojorge.control.ParteDiarioData;
import proyectojorge.modelo.ParteDiario;

/**
 *
 * @author Hernan14
 */
public class MenuPrincipal extends javax.swing.JFrame {
    Panel panel;
    ActionListener accion;
    DefaultMutableTreeNode anios = new DefaultMutableTreeNode("Partes Diarios");
    ParteDiarioData pd ;
    Conexion con;
    
    public MenuPrincipal() {
        initComponents();
        con = new Conexion();
        pd = new ParteDiarioData(con);
        this.setSize(new Dimension(1700, 1000));
        Escritorio.setLayout(null);
        jTPanel.setBounds(270,100,1000,800);
        cargarTree();
    }
    
    //AGREGAR PANEL
    private void agregarPanel(String ot){
            String[] partes = ot.split("/");
            String ote = partes[0];
            String nt = partes[1];
            if(jTPanel.indexOfTab(ot)>-1){ }
            else{
                panel = new Panel(ote,nt);
                jTPanel.add(ot,panel);
                int index = jTPanel.indexOfTab(ot);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(ot);
                JButton btnClose = new JButton("x");
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;
                pnlTab.add(lblTitle, gbc);
                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);
                jTPanel.setTabComponentAt(index, pnlTab);
                accion = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int index = jTPanel.indexOfTab(ot);
                        if (index >= 0) {
                            jTPanel.removeTabAt(index);
                        }
                    }
                };
                btnClose.addActionListener(accion);
            }
    }

        //AGREGAR TREES
    public void cargarTree(){
            DefaultMutableTreeNode anio = new DefaultMutableTreeNode("2021");
            anios.add(anio);
            anioMes(anio,31,"enero");
            anioMes(anio,28,"febrero");
            anioMes(anio,31,"marzo");
            anioMes(anio,30,"abril");
            anioMes(anio,31,"mayo");
            anioMes(anio,30,"junio");
            anioMes(anio,31,"julio");
            anioMes(anio,31,"agosto");
            anioMes(anio,30,"septiembre");
            anioMes(anio,31,"octubre");
            anioMes(anio,30,"noviembre");
            anioMes(anio,31,"diciembre");
            anio = new DefaultMutableTreeNode("2022");
            anios.add(anio);
            anioMes(anio,31,"enero");
            anioMes(anio,28,"febrero");
            anioMes(anio,31,"marzo");
            anioMes(anio,30,"abril");
            anioMes(anio,31,"mayo");
            anioMes(anio,30,"junio");
            anioMes(anio,31,"julio");
            anioMes(anio,31,"agosto");
            anioMes(anio,30,"septiembre");
            anioMes(anio,31,"octubre");
            anioMes(anio,30,"noviembre");
            anioMes(anio,31,"diciembre");
    }
    public void anioMes(DefaultMutableTreeNode anio, int dia,String mes){
    DefaultMutableTreeNode meses = new DefaultMutableTreeNode(mes);
        anio.add(meses);
        mesDia(meses,dia);
    
    }
    public void mesDia(DefaultMutableTreeNode a ,int dias){
        int numero=1;
        for(int x = 0 ; x<dias;x++){
        DefaultMutableTreeNode dia = new DefaultMutableTreeNode(String.valueOf(numero));
        a.add(dia);
        int mes=0;
            switch(a.toString()){
                case "enero": mes=1;break;
                case "febrero": mes=2;break;
                case "marzo": mes=3;break;
                case "abril": mes=4;break;
                case "mayo": mes=5;break;
                case "junio": mes=6;break;
                case "julio": mes=7;break;
                case "agosto": mes=8;break;
                case "septiembre": mes=9;break;
                case "octubre": mes=10;break;
                case "noviembre": mes=11;break;
                case "diciembre": mes=12;break;
                
                
            }
        List<ParteDiario> lista= pd.traerParteDiarioFecha(LocalDate.of(2021, mes, numero));
        for (int z = 0 ; z<lista.size();z++){
        DefaultMutableTreeNode parteD = new DefaultMutableTreeNode(lista.get(z).getOrdenTrabajo()+"/" + lista.get(z).getNumeroTarea());
        dia.add(parteD);
        }
        numero++;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JDesktopPane();
        jTPanel = new javax.swing.JTabbedPane();
        JlBuscarUsuario = new javax.swing.JLabel();
        jTOt2 = new javax.swing.JTextField();
        jBBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(anios);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JlBuscarUsuario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        JlBuscarUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlBuscarUsuario.setText("BUSCAR SEGUIMIENTOS OT:");

        jTOt2.setText("123123");
        jTOt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTOt2ActionPerformed(evt);
            }
        });

        jBBuscar.setText("BUSCAR");
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        Escritorio.setLayer(jTPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(JlBuscarUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(jTOt2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(jBBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JlBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTOt2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jBBuscar)
                .addContainerGap(480, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JlBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTOt2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(jTPanel))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTOt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTOt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTOt2ActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        /*SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        if(jTOt2.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "el Campo OT esta vacio");
            jTOt2.requestFocus();
        }else{
        }*/
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if(jTree1.getSelectionModel().getSelectionPath().getPath().length <5){}else{
                String modelo = jTree1.getSelectionModel().getSelectionPath().getPath()[4].toString();
                agregarPanel(modelo);
            }
            evt.consume();
        }
    }//GEN-LAST:event_jTree1MouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JLabel JlBuscarUsuario;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTOt2;
    private javax.swing.JTabbedPane jTPanel;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
