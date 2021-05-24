/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.vista;

import java.time.LocalDate;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import proyectojorge.control.Conexion;
import proyectojorge.control.ParteDiarioData;
import proyectojorge.modelo.ParteDiario;

/**
 *
 * @author kevin
 */
public class asda extends javax.swing.JFrame {
    
    /**
     * Creates new form asda
     */
    Conexion con;
    ParteDiarioData pd ;
    DefaultMutableTreeNode anios = new DefaultMutableTreeNode("años");
    public asda() {
        con = new Conexion();
        pd = new ParteDiarioData(con);
        initComponents();
        cargarTree();
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
        jTree1 = new javax.swing.JTree(anios);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(584, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     */
     
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
            java.util.logging.Logger.getLogger(asda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new asda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
