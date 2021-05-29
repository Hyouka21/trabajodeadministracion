/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escritorio;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
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
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectojorge.control.Conexion;
import proyectojorge.control.EmpleadoData;
import proyectojorge.control.ParteDiarioData;
import proyectojorge.control.SeguimientoData;
import proyectojorge.modelo.Empleado;
import proyectojorge.modelo.Seguimiento;
import vistas.GenerarPDF;
/**
 *
 * @author Hernan14
 */
public class Panel extends javax.swing.JPanel {
    private Conexion con;
    private SeguimientoData sd;
    private  Seguimiento seguimiento;
    private ParteDiarioData pd;
    private EmpleadoData ed;
    private String ot, nt;
    private DefaultTableModel modelo;
    private Integer control;
    private List<Integer> horas50;
    private List<Integer> horasNormales;
    private List<Integer> horas50BD;
    private List<Integer> horasNormalesBD;
    private List<Long> idSeguimientos;
    private int horasTotalesAyudante50, horasTotalesAyudanteNormales,horasTotalesOficial50, horasTotalesOficialNormales;
    private GenerarPDF generarPdf;
   
    public Panel(String ot,String nt) {
        initComponents();
        modelo = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if( columnas== 2 || columnas==3 || columnas==4 || columnas==5){
                return true;
                }else{
                return false;
                }
            }
        };
        horasTotalesAyudante50 = 0; horasTotalesAyudanteNormales = 0;horasTotalesOficial50 = 0; horasTotalesOficialNormales = 0;
        this.nt = nt; this.ot = ot;
        horas50 = new ArrayList<Integer>(); horasNormales = new ArrayList<Integer>();
        idSeguimientos = new ArrayList<Long>(); 
        con=new Conexion();
        sd = new SeguimientoData(con);
        ed = new EmpleadoData(con);
        pd = new ParteDiarioData(con);
        seguimiento = new Seguimiento();
        ArmarCabeceraTabla();
        this.jTOt.setText(ot);
        this.jTNt.setText(nt);
        jBModificar.setEnabled(false);
        cargarEmpleados();
        cargarTabla();
        horas50YNormalesBD();
        horasTotales();
    }

    public void borrarFilasTabla(){
         int a =modelo.getRowCount()-1;
         for(int i=a;i>=0;i--){
            modelo.removeRow(i);
         }
     }
    
    public void ArmarCabeceraTabla(){
        List<Object> columna = new ArrayList();
        columna.add("Empleado");
        columna.add("Tipo");
        columna.add("Dni");
        columna.add("Fecha");
        columna.add("Hora Inicio");
        columna.add("Hora Final");
        for(Object ob:columna){
            modelo.addColumn(ob);
        }
        JTabla.setModel(modelo);
    }
    
    private void cargarEmpleados(){
        Empleado empleado = null;
        jCBEmpleado.addItem(empleado);
        List<Empleado> empleados = ed.traerEmpleados();
        for(Empleado emp:empleados){
            jCBEmpleado.addItem(emp);
        }
    }
    
    private void cargarTabla(){
        long ots = Long.valueOf(ot.toString());
        long nts = Long.valueOf(nt.toString());
        idSeguimientos = new ArrayList<Long>(); 
        List<Seguimiento> seguimientos = sd.traerSeguimientosOrdTrabajoNumTar(ots,nts);
        control = seguimientos.size();
        if(seguimientos.size()<0){   
        }else{
            for(Seguimiento se:seguimientos){
                modelo.addRow(new Object[]{se.getEmpleado().getNombre(),se.getEmpleado().getTipo(),se.getEmpleado().getDni(),se.getFecha(),se.getHoraInicio(),se.getHoraFinal(),
                se.getIdSeguimiento()}); 
                idSeguimientos.add(se.getIdSeguimiento());
            }
        }  
    }
    
    private void horas50YNormales(){
        horas50 = new ArrayList<Integer>(); horasNormales = new ArrayList<Integer>();
        LocalTime horasanteriores; 
        int horainicio,horafinal,horastotales,horasnuevas;
        long dni;
        String fechat;
        LocalDate fecha;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                String[] parts = modelo.getValueAt(i, 4).toString().split(":");
                String[] parts2 = modelo.getValueAt(i, 5).toString().split(":");
                horainicio = Integer.valueOf(parts[0]);
                horafinal = Integer.valueOf(parts2[0]);
                horastotales = horafinal - horainicio;
                fechat = modelo.getValueAt(i, 3).toString();
                fecha = LocalDate.parse(fechat);
                dni = Long.valueOf(modelo.getValueAt(i, 2).toString());
                horasanteriores = sd.horasEmpleado(dni, fecha);
                String[] hs = horasanteriores.toString().split(":");
                if(Integer.valueOf(hs[0]) >= 8){
                    horas50.add(horastotales);
                    horasNormales.add(0);
                }else{
                    int registrototal = horastotales + Integer.valueOf(hs[0]);
                    horasnuevas = Integer.valueOf(hs[0]);
                        if(registrototal > 8){
                            registrototal -=8; 
                            horasnuevas = Math.abs(horasnuevas-8);
                            horas50.add(registrototal);
                            horasNormales.add(horasnuevas);
                        }else{
                            horas50.add(0);
                            horasNormales.add(horastotales);
                        }
                }
            }
        }
    }
        
    private void horas50YNormalesBD(){
        horasNormalesBD = new ArrayList<Integer>();
        horas50BD = new ArrayList<Integer>();
        Seguimiento seg;
        for(int i = 0;i<modelo.getRowCount();i++){
            seg = sd.traerSeguimientosId(Integer.valueOf(idSeguimientos.get(i).toString()));
            horas50BD.add(seg.getHoras_50());
            horasNormalesBD.add(seg.getHorasNormales());
        }
    }
    
    private void horasTotales(){
        horasTotalesAyudante50 = 0; horasTotalesAyudanteNormales = 0;
        horasTotalesOficial50 = 0; horasTotalesOficialNormales = 0;
        String tipo;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                tipo = modelo.getValueAt(i, 1).toString();
                if("ayudante".equals(tipo)){
                    horasTotalesAyudante50+= horas50BD.get(i);
                    horasTotalesAyudanteNormales+= horasNormalesBD.get(i);
                }
                else if("oficial".equals(tipo)){
                    horasTotalesOficial50+= horas50BD.get(i);
                    horasTotalesOficialNormales+= horasNormalesBD.get(i);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabla = new javax.swing.JTable();
        jBPdf = new javax.swing.JButton();
        jTOt = new javax.swing.JTextField();
        jCBEmpleado = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jBGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jBModificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTNt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jBBorrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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

        jBPdf.setText("CONVERTIR A PDF ");
        jBPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPdfActionPerformed(evt);
            }
        });

        jCBEmpleado.setToolTipText("seleccionar");
        jCBEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEmpleadoActionPerformed(evt);
            }
        });

        jLabel1.setText("CARGAR EMPLEADO");

        jBGuardar.setText("GUARDAR ");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane2.setViewportView(jTADescripcion);

        jLabel2.setText("AGREGAR UNA DESCRIPCION:");

        jBModificar.setText("MODIFICAR");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });

        jLabel3.setText("ORDEN DE TRABAJO:");

        jLabel4.setText("NUMERO DE TAREA");

        jBBorrar.setText("BORRAR SEGUIMIENTO");
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(jTOt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(jTNt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jBPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jBModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTOt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTNt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jCBEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jBBorrar)
                                .addGap(18, 18, 18)
                                .addComponent(jBModificar)
                                .addGap(18, 18, 18)
                                .addComponent(jBGuardar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBPdf)
                        .addGap(133, 133, 133))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPdfActionPerformed
        generarPdf = new GenerarPDF(jTOt.getText(),jTNt.getText(),horasTotalesAyudante50, 
                horasTotalesAyudanteNormales,horasTotalesOficial50, horasTotalesOficialNormales, horas50BD, horasNormalesBD, modelo);
        try {
            generarPdf.GenerarPdf();
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBPdfActionPerformed

    private void jCBEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEmpleadoActionPerformed
        if(jCBEmpleado.getSelectedItem() == null){     
        }else{
           Empleado emp = (Empleado) jCBEmpleado.getSelectedItem();
            modelo.addRow(new Object[]{emp.getNombre(),emp.getTipo(),emp.getDni()}); 
        }  
    }//GEN-LAST:event_jCBEmpleadoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        Integer dni; 
        Long ot = Long.valueOf(jTOt.getText());
        LocalTime horainicio,horafinal;
        LocalDate fecha;
        String fechatabla;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        horas50YNormales();
        JOptionPane.showMessageDialog(this,horas50);
        if(modelo.getRowCount()>-1){
            for(int i = control;i<modelo.getRowCount();i++){
                dni = Integer.valueOf(modelo.getValueAt(i, 2).toString()); 
                fechatabla = modelo.getValueAt(i, 3).toString();
                fecha =  LocalDate.parse(fechatabla,formatter);
                String[] part1 = modelo.getValueAt(i, 5).toString().split(":");
                horafinal = LocalTime.of(Integer.valueOf(part1[0]),0);
                String[] part2 = modelo.getValueAt(i, 4).toString().split(":");
                horainicio = LocalTime.of(Integer.valueOf(part2[0]),0);
                seguimiento = new Seguimiento();
                seguimiento.setEmpleado(ed.buscarEmpleado(dni));
                seguimiento.setFecha(fecha);
                seguimiento.setHoraFinal(horafinal);
                seguimiento.setHoraInicio(horainicio);
                seguimiento.setHorasNormales(horasNormales.get(i));
                seguimiento.setHoras_100(0);
                seguimiento.setHoras_50(horas50.get(i));
                seguimiento.setParteDiario(pd.buscarParteDiario(ot));
                sd.guardarSeguimientoCompleto(seguimiento);
            }
        }        
        borrarFilasTabla();
        cargarTabla();
        horas50YNormalesBD();
        horasTotales();
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        jBGuardar.setEnabled(true);
        modelo = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if( columnas== 2 || columnas==3 || columnas==4 || columnas==5){
                return true;
                }else{
                return false;
                }
            }
        };
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        int filaSeleccionada = JTabla.getSelectedRow();
        if(filaSeleccionada!=-1){
            Long id = Long.valueOf(idSeguimientos.get(filaSeleccionada).toString());
            sd.eliminarSeguimiento(Integer.valueOf(id.toString()));
            borrarFilasTabla();
            cargarTabla();
            horas50YNormalesBD();
            horasTotales();
        }else{
            JOptionPane.showMessageDialog(this,"Seleccione una fila de la tabla para borrar seguimiento");
        } 
    }//GEN-LAST:event_jBBorrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTabla;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBPdf;
    private javax.swing.JComboBox<Empleado> jCBEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTNt;
    private javax.swing.JTextField jTOt;
    // End of variables declaration//GEN-END:variables
}
