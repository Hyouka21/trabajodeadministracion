/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package escritorio;

import proyectojorge.control.Conexion;
import proyectojorge.control.SeguimientoData;
import com.itextpdf.io.image.ImageData;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import static com.itextpdf.kernel.pdf.PdfName.Image;
import com.itextpdf.kernel.pdf.PdfWriter;



import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectojorge.control.SeguimientoData;
import proyectojorge.modelo.Seguimiento;

/**
 *
 * @author Hernan14
 */
public class VistaSeguimiento extends javax.swing.JInternalFrame {

    private Conexion con;
    private SeguimientoData sd;
    private  Seguimiento seguimiento;
    private DefaultTableModel modelo = new DefaultTableModel();
    /** Creates new form vistaEjemplo */
    public VistaSeguimiento() {
        initComponents();
        con=new Conexion();
        sd = new SeguimientoData(con);
        seguimiento = new Seguimiento();
        jBPdf.setEnabled(false);
        ArmarCabeceraTabla();
    }

    public void CargarSeguimientos(){
        Integer ot = Integer.valueOf(jTOt.getText());
        List<Seguimiento> seg = sd.traerSeguimientosOt(ot);
        for(Seguimiento se:seg){
            modelo.addRow(new Object[]{se.getEmpleado().getNombre(),se.getFecha(),se.getHoraInicio(),se.getHoraFinal(),se.getHoras_100(),se.getHoras_50(),se.getHorasNormales()}); 
        }
    }
    
    public void ArmarCabeceraTabla(){
        List<Object> columna = new ArrayList();
        columna.add("Empleado");
        columna.add("Fecha");
        columna.add("Tipo");
        columna.add("Hora Inicio");
        columna.add("Hora Final");
        columna.add("Horas/100");
        columna.add("Horas/50");
        columna.add("Horas Normales");
        for(Object ob:columna){
            modelo.addColumn(ob);
        }
        JTabla.setModel(modelo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        JlBuscarUsuario = new javax.swing.JLabel();
        jBBuscar = new javax.swing.JButton();
        jBPdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabla = new javax.swing.JTable();
        jTOt = new javax.swing.JTextField();

        JlBuscarUsuario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        JlBuscarUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlBuscarUsuario.setText("BUSCAR SEGUIMIENTOS OT:");

        jBBuscar.setText("BUSCAR");
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBPdf.setText("CONVERTIR A PDF ");
        jBPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPdfActionPerformed(evt);
            }
        });

        JTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTabla);

        jTOt.setText("123123");
        jTOt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTOtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JlBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTOt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jBBuscar)
                                .addGap(0, 223, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jBPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JlBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTOt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jBPdf)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        if(jTOt.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "el Campo OT esta vacio");
            jTOt.requestFocus();
        }else{
            CargarSeguimientos();
            jBPdf.setEnabled(true);
         }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPdfActionPerformed
        String dniu = jTOt.getText();
        try {
            String ruta = System.getProperty("user.home");
            File file = new File(ruta+"/desktop/ProyectoTrabajo/"+dniu+".pdf"); //RUTA A DEFINIR POR EL USUARIO
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document documento = new Document(pdf,PageSize.A3);
            documento.setMargins(5,5,5,5);
            //imagen
            ImageData imagen = null;
            imagen = ImageDataFactory.create("\\Users\\Hernan14\\Documents\\NetBeansProjects\\PrototipoPrograma\\src\\imagenes\\electriccompany.png"); //RUTA A DEFINIR POR EL USUARIO
            Image imagen2 = new Image(imagen);
            imagen2.setHeight(200f);
            //tablas celdas
            Table tabla = new Table(9);
            Table tablacont = new Table(9);
            Table tablainfech = new Table(3);
            Table tablainfot = new Table(1);
            Table descripcion = new Table(1);
            Table herramientas = new Table(1);
            Table observaciones = new Table(1);
            Table horas = new Table(4);
            Cell celdahoras = new Cell(1,4);
            Cell celdainfot = new Cell(1,4);
            Cell celdafecha = new Cell(1,3);
            Cell celdainfech = new Cell(1,3);
            Cell celdainfo = new Cell(1,3);
            Cell celdaimg = new Cell(1,3);
            Cell celdajefe = new Cell(1,1);
            Cell celdasector = new Cell(1,1);
            Cell celdaOT = new Cell(1,1);
            Cell asd = new Cell(1,1); 
            asd.setMaxWidth(400f);
            Border b1 = new SolidBorder(2);
            Border bd = new DottedBorder(1);
            tablacont.setBorder(b1).setPaddings(2,2,2,2);
            //Tablas anidadas
            Paragraph infofecha = new Paragraph("fecha");
            Paragraph jefe = new Paragraph("Carlos");
            Paragraph sector = new Paragraph("");
            Paragraph ot = new Paragraph("11756176");
            celdajefe.add(jefe).setTextAlignment(TextAlignment.CENTER);
            celdasector.add(sector).setTextAlignment(TextAlignment.CENTER);
            celdaOT.add(ot).setTextAlignment(TextAlignment.CENTER);
            tablainfot.addCell(new Cell(1,4).add(new Paragraph("N° de OT")).setTextAlignment(TextAlignment.CENTER));
            tablainfot.addCell(celdaOT);
            tablainfot.addCell(asd.add(new Paragraph("Nombre solicitante de la tarea")).setTextAlignment(TextAlignment.CENTER));
            tablainfot.addCell(celdajefe.setHeight(40));
            tablainfot.addCell(new Cell(1,4).add(new Paragraph("Sector de la Tarea")).setTextAlignment(TextAlignment.CENTER));
            tablainfot.addCell(celdasector.setHeight(40));
            tablainfot.setMaxWidth(400f);
            celdainfech.add(infofecha);
            celdainfech.setVerticalAlignment(VerticalAlignment.MIDDLE);
            tablainfech.addCell(celdainfech);
            tablainfech.setVerticalAlignment(VerticalAlignment.MIDDLE);
            tablainfech.setHorizontalAlignment(HorizontalAlignment.CENTER);  
            //propiedades celdas
            Paragraph info = new Paragraph("informacion importante de contacto");
            celdainfot.add(tablainfot);
            celdafecha.add(tablainfech);
            celdafecha.setVerticalAlignment(VerticalAlignment.MIDDLE);
            celdainfo.add(info).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            celdainfo.setPaddingLeft(25);
            celdaimg.add(imagen2.setAutoScale(true));
            //agregar celdas a tablas
            tabla.addCell(celdaimg);
            tabla.addCell(celdainfo);
            tabla.addCell(celdafecha);
            tabla.addCell(celdainfot.setPaddings(2,3,2,3));
            tabla.addCell(new Cell(1,5).add(descripcion.addCell(new Cell(1,1).add(new Paragraph("Detalle de la tarea")).setTextAlignment(TextAlignment.CENTER)
                    .setMaxWidth(550f)).addCell(new Cell(1,1).setHeight(150f).setMarginTop(1f))).setPaddings(2,3,2,3));
            tabla.addCell(new Cell(1,2).add(new Paragraph("OPERARIO")));
            tabla.addCell(new Cell(1,2).add(new Paragraph("FECHA")).setBorderTopLeftRadius(new BorderRadius(4)));
            tabla.addCell("INICIO");
            tabla.addCell("FIN");
            tabla.addCell(new Cell(1,1).add(new Paragraph("HS/100")).setMaxWidth(10));
            tabla.addCell(new Cell(1,1).add(new Paragraph("HS/50")).setMaxWidth(10));
            tabla.addCell(new Cell(1,1).add(new Paragraph("HS. N")).setMaxWidth(10));
            int cantidad = 22;
            for(int i=0;i<modelo.getRowCount();i++){
                tabla.addCell(new Cell(1,2).add(new Paragraph(modelo.getValueAt(i,0).toString())));
                tabla.addCell(new Cell(1,2).add(new Paragraph(modelo.getValueAt(i,1).toString())));
                tabla.addCell(modelo.getValueAt(i,2).toString());
                tabla.addCell(modelo.getValueAt(i,3).toString());
                tabla.addCell(modelo.getValueAt(i,4).toString());
                tabla.addCell(modelo.getValueAt(i,5).toString());
                tabla.addCell(modelo.getValueAt(i,6).toString());
                cantidad--;
            }
            for(int x = 0;x<cantidad;x++){
                tabla.addCell(new Cell(1,2).setHeight(20));
                tabla.addCell(new Cell(1,2).setHeight(20));
                tabla.addCell(new Cell(1,1).setHeight(20));
                tabla.addCell(new Cell(1,1).setHeight(20));
                tabla.addCell(new Cell(1,1).setHeight(20));
                tabla.addCell(new Cell(1,1).setHeight(20));
                tabla.addCell(new Cell(1,1).setHeight(20));
            }
            tabla.addCell(new Cell(4,5).add(herramientas.addCell(new Cell(1,1).add(new Paragraph("Herramientas de trabajo: ")).setMaxWidth(600f).setHeight(150))).setPaddings(4,4,4,4));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ayudante:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/oficial:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ayud nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ofic nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(35));
            tabla.addCell(new Cell(1,9).add(observaciones.addCell(new Cell(1,9).add(new Paragraph("observaciones: ")).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                    .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                    .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderTop(bd))).setPaddings(4,4,4,4));
            tablacont.addCell(tabla);
            documento.add(tablacont);
            documento.close();
        } catch (FileNotFoundException | MalformedURLException ex) {
            Logger.getLogger(VistaSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_jBPdfActionPerformed

    private void jTOtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTOtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTOtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTabla;
    private javax.swing.JLabel JlBuscarUsuario;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBPdf;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTOt;
    // End of variables declaration//GEN-END:variables

}
