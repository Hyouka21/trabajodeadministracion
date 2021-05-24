
package proyectojorge.vistas.EMAIL;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import proyectojorge.control.Conexion;
import proyectojorge.control.JefeData;
import proyectojorge.modelo.Jefe;


public class EnviarEmail extends javax.swing.JInternalFrame {
    //-------------------------------Variables-----------------------------------------//
    String direccion="";
    ArrayList<String> pathName = new ArrayList<String>();
    ArrayList<String> fileName = new ArrayList<String>();
    ArrayList<String> destinatarios = new ArrayList<String>();
    JefeData jefedata;
    String muestra="";
    Jefe jefe = null;
    Icon gif = new ImageIcon(getClass().getResource("/Image/spinner.gif"));
    Envia enviando=new Envia();
    
//-------------------------------Variables---------------------------------------------//
 
    public EnviarEmail() {
        initComponents();
        initComponents();
        this.setVisible(true); 
        Conexion c=new Conexion();
        jefedata = new JefeData(c);
        this.setLocation(350, 100);
        this.cargarCBjefe();
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);//activar la x
        this.setFrameIcon(null); //sacar la flecha izquierda
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI(); Container north = (Container)ui.getNorthPane(); north.remove(0); north.validate(); north.repaint();//repasar
        jLabelGIF.setVisible(true);
    }
    
    
  //-----------------------------------METODOS-----------------------------------------//
    
    
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
        jTextAreaAdjunto.setText("");
        //jLabelDirAdjunto.setText("");
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
            muestra += "<html> ("+ destinatarios.get(x)+") ; <html>";
            System.out.println(muestra);
            jLabelEmails.setText(muestra);      
        }
    }
    public void borrarUltimoAdjunto(){
        int ultimoPath=pathName.size()-1;
        int ultimoFile=fileName.size()-1;
        if(ultimoPath>-1){
            System.out.println("Ultimo Path: "+ultimoPath);
            pathName.remove(ultimoPath);
            System.out.println(pathName);
        }
        if(ultimoFile>-1){
            System.out.println("Ultimo Path: "+ultimoFile);
            fileName.remove(ultimoFile);
            System.out.println(fileName);
        }
    }
    
    public void enviarEmail(){
        
                //***************************Listas de Direcciones Adjuntas y Nombres de archivos****************************//
                
        ArrayList<String> dirAdjuntoo = new ArrayList<String>(this.getPathName());    
        ArrayList<String> listnombreArchivo = new ArrayList<String>(this.getfileName());
        ArrayList<String> listaSelec = new ArrayList<String>(destinatarios);  
        //ArrayList<Jefe> correos = new ArrayList<Jefe>(jefedata.traerJefes());
        String nombreArchivo = "";
        
        //*****************************************************************************************************//
        
        //--------------------propiedades para enviar un correo a GMAIL---------------------//
        Properties propiedad = new Properties();
        propiedad.put("mail.smtp.host", "smtp.gmail.com");   //no tenemos un host
        propiedad.put("mail.transport.protocol","smtp");
        propiedad.put("mail.smtp.starttls.enable", "true");
        propiedad.put("mail.smtp.port", "587");
        propiedad.put("mail.smtp.auth", "true");
        
        //--------------------propiedades para enviar un correo a GMAIL-----------------------//
        
        String user = "";   //mi correo
        String password = "";        //mi contra
        
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
                JOptionPane.showMessageDialog(null, "No se pudo enviar el correo");
            } 
            
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un destinatario");
        }  
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxJefes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButtonBorrar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAsunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMensaje = new javax.swing.JTextArea();
        jButtonAdjuntar = new javax.swing.JButton();
        jButtonBorrarAd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaAdjunto = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();
        jLabelEmails = new javax.swing.JLabel();
        jLabelGIF = new javax.swing.JLabel();

        setClosable(true);

        jComboBoxJefes.setSelectedIndex(-1);
        jComboBoxJefes.setToolTipText("Seleccionar");
        jComboBoxJefes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJefesActionPerformed(evt);
            }
        });

        jLabel1.setText("Para:");

        jButtonBorrar.setText("Borrar Ultimo");
        jButtonBorrar.setToolTipText("Borrar ultimo seleccionado");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar campos");
        jButtonLimpiar.setToolTipText("Resetear");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jLabel2.setText("Asunto:");

        jTextFieldAsunto.setToolTipText("Asunto");
        jTextFieldAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAsuntoActionPerformed(evt);
            }
        });

        jTextAreaMensaje.setColumns(20);
        jTextAreaMensaje.setRows(5);
        jTextAreaMensaje.setToolTipText("Mensaje..");
        jScrollPane1.setViewportView(jTextAreaMensaje);

        jButtonAdjuntar.setText("Adjuntar Archivo");
        jButtonAdjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdjuntarActionPerformed(evt);
            }
        });

        jButtonBorrarAd.setText("Borrar Adjunto");
        jButtonBorrarAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarAdActionPerformed(evt);
            }
        });

        jTextAreaAdjunto.setEditable(false);
        jTextAreaAdjunto.setColumns(20);
        jTextAreaAdjunto.setLineWrap(true);
        jTextAreaAdjunto.setRows(5);
        jTextAreaAdjunto.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaAdjunto);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jLabelEmails.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelEmails.setAutoscrolls(true);

        jLabelGIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/spinner.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(jLabelEmails, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBoxJefes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelGIF, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonBorrar))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldAsunto)))
                    .addGap(34, 34, 34)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdjuntar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jButtonBorrarAd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxJefes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBorrar))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEmails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jLabelGIF, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGap(5, 5, 5)
                .addComponent(jButtonLimpiar)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdjuntar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBorrarAd)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed

        this.enviarEmail();
            
  
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        this.limpiarCampos();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        this.borrarUltimo();
        muestra="";
        for (int x = 0; x < destinatarios.size(); x++) {
            if(destinatarios.isEmpty()){
                muestra ="";
                jLabelEmails.setText(muestra);
            }else{
               muestra += "<html>("+ destinatarios.get(x)+") ; <html>";
               jLabelEmails.setText(muestra);  
            }
        }
        if(destinatarios.isEmpty()){
            muestra ="";
            jLabelEmails.setText(muestra);
        }
    }//GEN-LAST:event_jButtonBorrarActionPerformed

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

    private void jTextFieldAsuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAsuntoActionPerformed
        // TODO add your handling code here:
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
                    
                //jLabelDirAdjunto.setText("<html> "+nombreArchivo+" <html>");
                jTextAreaAdjunto.setText("\n"+nombreArchivo+" \n");
                this.setfileName(fileName);
                this.setPathName(dirAdjuntoo);
                
            }catch (IOException ex){
			       
            }
        }
    }//GEN-LAST:event_jButtonAdjuntarActionPerformed

    private void jButtonBorrarAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarAdActionPerformed
        this.borrarUltimoAdjunto();
        muestra="";
        for (int x = 0; x < fileName.size(); x++) {
            if(fileName.isEmpty()){
                muestra ="";
                jTextAreaAdjunto.setText(muestra);
            }else{
               muestra += "\n"+ fileName.get(x)+" ; ";
               jTextAreaAdjunto.setText(muestra); 
            }
        }
        if(fileName.isEmpty()){
            muestra ="";
            jTextAreaAdjunto.setText(muestra);
        }
    }//GEN-LAST:event_jButtonBorrarAdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdjuntar;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonBorrarAd;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<Jefe> jComboBoxJefes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelEmails;
    private javax.swing.JLabel jLabelGIF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaAdjunto;
    private javax.swing.JTextArea jTextAreaMensaje;
    private javax.swing.JTextField jTextFieldAsunto;
    // End of variables declaration//GEN-END:variables
}
