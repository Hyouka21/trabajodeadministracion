package proyectojorge.vistas.EMAIL;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyectojorge.control.Conexion;
import proyectojorge.control.JefeData;
import proyectojorge.modelo.Jefe;

//********************************TIPOS DE ARCHIVOS BLOQUEADOS POR GMAIL******************************//

//.ade, .adp, .apk, .appx, .appxbundle, .bat, .cab, .chm,
//.cmd, .com, .cpl, .dll, .dmg, .ex, .ex_, .exe, .hta, .ins,
//.isp, .iso, .jar, .js, .jse, .lib, .lnk, .mde, .msc, .msi,
//.msix, .msixbundle, .msp, .mst, .nsh, .pif, .ps1, .scr, .sct,
//.shb, .sys, .vb, .vbe, .vbs, .vxd, .wsc, .wsf, .wsh

//********************************TIPOS DE ARCHIVOS BLOQUEADOS POR GMAIL******************************//

public class vista extends javax.swing.JFrame {

    String direccion="";
    ArrayList<String> pathName = new ArrayList<String>();
    ArrayList<String> fileName = new ArrayList<String>();
    ArrayList<String> destinatarios = new ArrayList<String>();
    JefeData jefedata;
    String muestra="";
    Jefe jefe = null;

    
    public vista() {
        initComponents();
        this.setVisible(true); 
        Conexion c=new Conexion();
        jefedata = new JefeData(c);
        this.cargarCBjefe();
    }

    public void setPathName(ArrayList<String> direccionAdjunta){
        pathName = new ArrayList<String>(direccionAdjunta);
    }

    public List<String> getPathName(){ 
        return pathName;
    }

    public List<String> getfileName(){ 
        return fileName;
    }

    public void setfileName(ArrayList<String> name){
         fileName = new ArrayList<String>(name);

    }
    public void limpiarCampos(){
        jLabelDirAdjunto.setText("");
        jTextAreaMensaje.setText("");
        jTextFieldAsunto.setText("");
        this.getfileName().clear();
        this.getPathName().clear();
        destinatarios.clear();
        this.jComboBoxJefes.setSelectedItem(jefe);
        muestra ="";
        jLabelEmails.setText(muestra);
        
         
    }
   
    public void cargarCBjefe(){
        jComboBoxJefes.addItem(jefe);
        List<Jefe> jefes=new ArrayList<>();
        jefes.addAll(jefedata.traerJefes());
        for(Jefe item:jefes){
              jComboBoxJefes.addItem(item);
        }
    } 
    
    public void borrarUltimo(){
        int ultimo=destinatarios.size()-1;
        if(ultimo>-1){
            System.out.println(ultimo);
            destinatarios.remove(ultimo);
            System.out.println(destinatarios);
        }
    }
    public void mostrar(){
    muestra="";
        for (int x = 0; x < destinatarios.size(); x++) {
            muestra += " ("+ destinatarios.get(x)+") ; ";
            System.out.println(muestra);
            jLabelEmails.setText(muestra);      
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jSpinner1 = new javax.swing.JSpinner();
        jTextFieldAsunto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMensaje = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();
        jButtonAdjuntar = new javax.swing.JButton();
        jLabelDirAdjunto = new javax.swing.JLabel();
        jButtonLimpiar = new javax.swing.JButton();
        jComboBoxJefes = new javax.swing.JComboBox<>();
        jLabelEmails = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonBorrar = new javax.swing.JButton();
        scrollbar2 = new java.awt.Scrollbar();
        scrollbar3 = new java.awt.Scrollbar();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTextFieldAsunto.setToolTipText("Asunto");
        jTextFieldAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAsuntoActionPerformed(evt);
            }
        });

        jLabel2.setText("Asunto:");

        jTextAreaMensaje.setColumns(20);
        jTextAreaMensaje.setRows(5);
        jTextAreaMensaje.setToolTipText("Mensaje..");
        jScrollPane1.setViewportView(jTextAreaMensaje);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jButtonAdjuntar.setText("Adjuntar Archivo");
        jButtonAdjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdjuntarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar Campos");
        jButtonLimpiar.setToolTipText("Resetear");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jComboBoxJefes.setToolTipText("Seleccionar");
        jComboBoxJefes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJefesActionPerformed(evt);
            }
        });

        jLabelEmails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmails.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelEmails.setAutoscrolls(true);
        jLabelEmails.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelEmails.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabelEmails.setInheritsPopupMenu(false);
        jLabelEmails.setVerifyInputWhenFocusTarget(false);
        jLabelEmails.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel1.setText("Para:");

        jButtonBorrar.setText("Borrar Ultimo");
        jButtonBorrar.setToolTipText("Borrar Ultimo Seleccionado");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxJefes, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelEmails, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonAdjuntar)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelDirAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldAsunto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(58, 58, 58)
                                .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBoxJefes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBorrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEmails, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                    .addComponent(jButtonLimpiar))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAdjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabelDirAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jLabelDirAdjunto.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        
        
        //***************************Listas de Direcciones Adjuntas y Nombres de archivos****************************//
                
        ArrayList<String> dirAdjuntoo = new ArrayList<String>(this.getPathName());    
        ArrayList<String> listnombreArchivo = new ArrayList<String>(this.getfileName());
        ArrayList<String> listaSelec = new ArrayList<String>(destinatarios);  
        ArrayList<Jefe> correos = new ArrayList<Jefe>(jefedata.traerJefes());
        String nombreArchivo = "";
        
        //*****************************************************************************************************//
        
        //--------------------propiedades para enviar un correo a GMAIL---------------------//
        Properties propiedad = new Properties();
        propiedad.put("mail.smtp.host", "smtp.gmail.com");   //no tenemos un host
        propiedad.put("mail.transport.protocol","smtp");
        propiedad.put("mail.smtp.starttls.enable", "true");
        propiedad.put("mail.smtp.port", "587");
        propiedad.put("mail.smtp.auth", "true");
//        propiedad.put("mail.smtp.host", "smtp.gmail.com");
//        propiedad.put("mail.smtp.socketFactory.port", "465");
//        propiedad.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        propiedad.put("mail.smtp.auth", "true");
//        propiedad.put("mail.smtp.port", "465");
//        propiedad.put("mail.debug", "false");
        
        //--------------------propiedades para enviar un correo a GMAIL-----------------------//
        
        //Session sesion = Session.getDefaultInstance(propiedad,null);   //seteo las propiedades
        String user = "joni2210122@gmail.com";   //mi correo
        String password = "winterfell22";        //mi contra
        
        Session sesion = Session.getInstance(propiedad,
                
            new javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);}
            });
        sesion.setDebug(true);
        
        
        //--------------------Datos que traigo del JinternalFrame------------------------------//
        
        for (int x = 0; x < listaSelec.size(); x++) {
            String correo = listaSelec.get(x);
            System.out.println(correo);
        }
        
        
        //String destinatario = JTextFieldRecibe.getText();   
        String asunto = jTextFieldAsunto.getText();
        String mensaje = jTextAreaMensaje.getText();
        //String valCorreo = "^([A-Za-z0-9_.-]+)@([\\da-z.-]+).([a-z.]{2,6})$";
        
        
        if(!destinatarios.isEmpty()){
            
        //------------------------Datos que traigo del JinternalFrame---------------------------//


            try {
                BodyPart texto = new MimeBodyPart();                                            //bodypart la uso para poder adjuntar un archivo y el texto
                texto.setText(mensaje);                                                         //seteo el texto 
                
                List<BodyPart> listaAdjuntos = new LinkedList<BodyPart>();                      //creamos una lista de adjuntos
                // Se compone el adjunto con la imagen
            
                for(int i=0;i<dirAdjuntoo.size();i++){
                    
                    BodyPart adjunto = new MimeBodyPart();
                    System.out.println(dirAdjuntoo.get(i));
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(dirAdjuntoo.get(i))));  // aca uso un manejador(DataHandler) para traer el archivo que selecciono en adjuntar
                    
                    nombreArchivo = listnombreArchivo.get(i);
                    System.out.println(nombreArchivo);
                    
                    adjunto.setFileName(nombreArchivo);
                    listaAdjuntos.add(adjunto);                                                     //añadimos el elemento a la lista
                }

                MimeMultipart multiParte = new MimeMultipart();                                     //multipart lo uso para unficar el cuerpo del mensaje + el archivo adjunto

                multiParte.addBodyPart(texto);                                                      //unfico en multiParte el texto
                
                Iterator it = listaAdjuntos.iterator();                                             //<------------la iteramos
                    while(it.hasNext()){
                        
                        BodyPart archivo =(BodyPart)it.next();                                       //<------------obtenemos el objeto
                        multiParte.addBodyPart(archivo);                                             //<-----------------finalmente lo añadimos al mensaje
                    }

                //-------------------------Aca seteo el email y lo envio con Transport*********************************//

                MimeMessage mail = new MimeMessage(sesion);                                       //creo la sesion

                mail.setFrom(new InternetAddress(user));                                   //el correo de la sesion
                
                for (String correo : listaSelec) {
                    System.out.println(correo);
                    mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));   //aca va el email del destinatario
                }
                    
                
                mail.setSubject(asunto);                                                          //set asunto
                mail.setContent(multiParte);                                                      //set mensaje

                Transport transporte = sesion.getTransport("smtp");                               //esto va siempre
                transporte.connect(user, password);                                          //mi correo y la contra mia
                transporte.sendMessage(mail, mail.getAllRecipients()); 
                transporte.close();
                
//------------------------------------------Limpiar vista-----------------------------------------------------------//

                JOptionPane.showMessageDialog(null, "Correo Enviado");
                this.limpiarCampos();
                this.setfileName(listnombreArchivo);
                this.setPathName(dirAdjuntoo);
                destinatarios.clear();
                this.jComboBoxJefes.setSelectedItem(jefe);
                       
//------------------------------------------Limpiar vista-----------------------------------------------------------//      
                
            } catch (AddressException ex) {
                Logger.getLogger(vista.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "No se pudo enviar");

            } catch (MessagingException ex) {
                Logger.getLogger(vista.class.getName()).log(Level.SEVERE, null, ex);
                
                JOptionPane.showMessageDialog(null, "NO SE PUEDE ENVIAR ARCHIVOS .RAR");
            } 
            
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un destinatario");
        } 
        
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jTextFieldAsuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAsuntoActionPerformed
        
        
    }//GEN-LAST:event_jTextFieldAsuntoActionPerformed

    private void jButtonAdjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdjuntarActionPerformed
        
        JFrame frame;
        JFileChooser fc = new JFileChooser();
	fc.setFileSelectionMode(0);
        frame = new JFrame();
        
        int respuesta = fc.showSaveDialog(frame);
        if (respuesta == 0){
            File archivoElegido = fc.getSelectedFile();
            try{
                
                ArrayList<String> dirAdjuntoo = new ArrayList<String>(this.getPathName());
                
                String pathName = archivoElegido.getCanonicalPath();
                pathName = pathName.replace("\\", "/");
                
                dirAdjuntoo.add(pathName);
                
                System.out.println("Adjuntos: " + dirAdjuntoo);

                ArrayList<String> fileName = new ArrayList<String>(this.getfileName());
                 
                String nombreArchivo = archivoElegido.getName();
                
                fileName.add(nombreArchivo);
                
                nombreArchivo ="";
                for (int x = 0; x < fileName.size(); x++) {
                    nombreArchivo += fileName.get(x)+ "; ";
                    System.out.println("Nombre: "+nombreArchivo);
		}
                    
                jLabelDirAdjunto.setText("Archivos seleccionados: "+nombreArchivo);
                this.setfileName(fileName);
                this.setPathName(dirAdjuntoo);
                
            }catch (IOException ex){
			       
            }
        }
    }//GEN-LAST:event_jButtonAdjuntarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        this.limpiarCampos();
        
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jComboBoxJefesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJefesActionPerformed

            boolean esta=false;
            jComboBoxJefes.removeItem(jefe);
            if(jComboBoxJefes.getSelectedItem()==null){
                //JOptionPane.showMessageDialog(null, "Selecciones un destinatario");
            }else{
                
                System.out.println("seleccionado: "+jComboBoxJefes.getSelectedItem());
                Jefe j = (Jefe)jComboBoxJefes.getSelectedItem();
                
                for(int x = 0;x<destinatarios.size();x++){
                    
                    if(j.getEmail().compareTo(destinatarios.get(x))==0){
                        esta=true;
                    }
                }
                if(!esta){
                    destinatarios.add(j.getEmail());
                }
                
                this.mostrar();
                
                System.out.println("Correos Guardados: "+destinatarios);
            }
         
        
    }//GEN-LAST:event_jComboBoxJefesActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        this.borrarUltimo();
        muestra="";
        for (int x = 0; x < destinatarios.size(); x++) {
            if(destinatarios.isEmpty()){
                muestra ="";
                jLabelEmails.setText(muestra);
            }else{
               muestra += "("+ destinatarios.get(x)+") ; ";
               jLabelEmails.setText(muestra);  
            }
        }
        if(destinatarios.isEmpty()){
            muestra ="";
            jLabelEmails.setText(muestra);
        }
       
    }//GEN-LAST:event_jButtonBorrarActionPerformed


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
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdjuntar;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<Jefe> jComboBoxJefes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDirAdjunto;
    private javax.swing.JLabel jLabelEmails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextAreaMensaje;
    private javax.swing.JTextField jTextFieldAsunto;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.Scrollbar scrollbar2;
    private java.awt.Scrollbar scrollbar3;
    // End of variables declaration//GEN-END:variables
}
