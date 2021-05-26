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
        this.nt = nt; 
        this.ot = ot;
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
        List<Seguimiento> seguimientos = sd.traerSeguimientosOrdTrabajoNumTar(ots,nts);
        if(seguimientos.size()<0){   
        }else{
            for(Seguimiento se:seguimientos){
                modelo.addRow(new Object[]{se.getEmpleado().getNombre(),se.getEmpleado().getTipo(),se.getEmpleado().getDni(),se.getFecha(),se.getHoraInicio(),se.getHoraFinal()}); 
            }
        }  
    }
    
    private List<Integer> horas100(){
        List<Integer> horas = new ArrayList<Integer>();
        Integer horainicio,horafinal,horastotales;
        if(modelo.getRowCount()!=-1){
            for(int i = 0;i<modelo.getRowCount();i++){
                String[] parts = modelo.getValueAt(i, 4).toString().split(":");
                String[] parts2 = modelo.getValueAt(i, 5).toString().split(":");
                horainicio = Integer.valueOf(parts[0]);
                horafinal = Integer.valueOf(parts2[0]);
                horastotales = horafinal - horainicio;
                if(horastotales > 8){
                    horastotales = 8;
                }
                horas.add(horastotales);
            }
        }
        return horas;
    }
    
    private List<Integer> horas50(){
        List<Integer> horas = new ArrayList<Integer>();
        int horainicio,horafinal,horastotales;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                String[] parts = modelo.getValueAt(i, 4).toString().split(":");
                String[] parts2 = modelo.getValueAt(i, 5).toString().split(":");
                horainicio = Integer.valueOf(parts[0]);
                horafinal = Integer.valueOf(parts2[0]);
                horastotales = horafinal - horainicio;
                if(horastotales > 8){
                    horastotales-=8;
                    horas.add(horastotales);
                }else{
                    horastotales = 0;
                    horas.add(horastotales);
                }
            }
        }
        return horas;
    }
    
    private int horasTotalesAyudante50(){
        int horastotales = 0;
        long dni;
        String fechat,tipo;
        LocalDate fecha;
        LocalTime horas;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                fechat = modelo.getValueAt(i, 3).toString();
                fecha = LocalDate.parse(fechat);
                dni = Long.valueOf(modelo.getValueAt(i, 2).toString());
                tipo = modelo.getValueAt(i, 1).toString();
                if(tipo == "ayudante"){
                    horas = sd.horasEmpleado(dni, fecha);
                    String[] hs = horas.toString().split(":");
                    if(Integer.valueOf(hs[0]) > 8){
                        horastotales += Math.abs(Integer.valueOf(hs[0])-8);
                    }
                }
            }
        }
        return horastotales;
    }
    
    private int horasTotalesOficial50(){
        int horastotales = 0;
        long dni;
        String fechat,tipo;
        LocalDate fecha;
        LocalTime horas;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                fechat = modelo.getValueAt(i, 3).toString();
                fecha = LocalDate.parse(fechat);
                dni = Long.valueOf(modelo.getValueAt(i, 2).toString());
                tipo = modelo.getValueAt(i, 1).toString();
                if(tipo == "oficial"){
                    horas = sd.horasEmpleado(dni, fecha);
                    String[] hs = horas.toString().split(":");
                    if(Integer.valueOf(hs[0]) > 8){
                        horastotales += Math.abs(Integer.valueOf(hs[0])-8);
                    }
                }
            }
        }
        return horastotales;
    }
    
    private int horasTotalesAyudante100(){
        int horastotales = 0;
        long dni;
        String fechat,tipo;
        LocalDate fecha;
        LocalTime horas;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                fechat = modelo.getValueAt(i, 3).toString();
                fecha = LocalDate.parse(fechat);
                dni = Long.valueOf(modelo.getValueAt(i, 2).toString());
                tipo = modelo.getValueAt(i, 1).toString();
                if(tipo == "ayudante"){
                    horas = sd.horasEmpleado(dni, fecha);
                    String[] hs = horas.toString().split(":");
                    if(Integer.valueOf(hs[0]) > 8){
                        horastotales += 8;
                    }else{
                        horastotales += Integer.valueOf(hs[0]);
                    }
                }
            }
        }
        return horastotales;
    }
    
    private int horasTotalesOficial100(){
        int horastotales = 0;
        long dni;
        String fechat,tipo;
        LocalDate fecha;
        LocalTime horas;
        if(modelo.getRowCount()!= -1){
            for(int i = 0;i<modelo.getRowCount();i++){
                fechat = modelo.getValueAt(i, 3).toString();
                fecha = LocalDate.parse(fechat);
                dni = Long.valueOf(modelo.getValueAt(i, 2).toString());
                tipo = modelo.getValueAt(i, 1).toString();
                if(tipo == "oficial"){
                    horas = sd.horasEmpleado(dni, fecha);
                    String[] hs = horas.toString().split(":");
                    if(Integer.valueOf(hs[0]) > 8){
                        horastotales += 8;
                    }else{
                        horastotales += Integer.valueOf(hs[0]);
                    }
                }
            }
        }
        return horastotales;
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jBModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBGuardar)))
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(80, 80, 80))
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
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jCBEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
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
        String dniu = jTOt.getText()+"-"+jTNt.getText();
        List<Integer> horas100 = horas100();
        List<Integer> horas50 = horas50();
        try {
            String ruta = System.getProperty("user.home");
            File file = new File(ruta+"/desktop/ProyectoTrabajo/"+dniu+".pdf"); //RUTA A DEFINIR POR EL USUARIO
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document documento = new Document(pdf,PageSize.A3);
            documento.setMargins(5,5,5,5);
            //imagen
            ImageData imagen = null;
            imagen = ImageDataFactory.create("\\Users\\Hernan14\\Documents\\NetBeansProjects\\trabajodeadministracion\\src\\imagenes\\electriccompany.png"); //RUTA A DEFINIR POR EL USUARIO
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
                tabla.addCell(new Cell(1,2).add(new Paragraph(modelo.getValueAt(i,3).toString())));
                tabla.addCell(modelo.getValueAt(i,4).toString());
                tabla.addCell(modelo.getValueAt(i,5).toString());
                tabla.addCell(horas100.get(i).toString());
                tabla.addCell(horas50.get(i).toString());
                tabla.addCell("0");
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
            tabla.addCell(new Cell(4,5).add(herramientas.addCell(new Cell(1,1).add(new Paragraph("Herramientas de trabajo: ")).setMaxWidth(600f).setHeight(130))).setPaddings(4,4,4,4));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ayudante:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesAyudante100()))).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesAyudante50()))).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/oficial:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesOficial100()))).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesOficial50()))).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ayud nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ofic nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33));
            tabla.addCell(new Cell(1,9).add(observaciones.addCell(new Cell(1,9).add(new Paragraph("observaciones: ")).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderTop(bd))).setPaddings(4,4,4,4));
        tablacont.addCell(tabla);
        documento.add(tablacont);
        documento.close();
        } catch (FileNotFoundException | MalformedURLException ex) {
           
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
        List<Integer> horas100 = horas100();
        List<Integer> horas50 = horas50();
        Integer dni;
        Long ot = Long.valueOf(jTOt.getText());
        LocalTime horainicio,horafinal;
        LocalDate fecha;
        String fechatabla;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(modelo.getRowCount()>-1){
            for(int i = 0;i<modelo.getRowCount();i++){
                dni = Integer.valueOf(modelo.getValueAt(i, 2).toString()); 
                fechatabla = modelo.getValueAt(i, 3).toString();
                fecha =  LocalDate.parse(fechatabla,formatter);
                String[] part1 = modelo.getValueAt(i, 5).toString().split(":");
                horafinal = LocalTime.of(Integer.valueOf(part1[0]),0);
                String[] part2 = modelo.getValueAt(i, 4).toString().split(":");
                horainicio = LocalTime.of(Integer.valueOf(part2[0]),0);
                JOptionPane.showMessageDialog(this,horainicio);
                seguimiento = new Seguimiento();
                seguimiento.setEmpleado(ed.buscarEmpleado(dni));
                seguimiento.setFecha(fecha);
                seguimiento.setHoraFinal(horafinal);
                seguimiento.setHoraInicio(horainicio);
                //seguimiento.setHorasNormales(0);
                seguimiento.setHoras_100(horas100.get(i));
                seguimiento.setHoras_50(horas50.get(i));
                seguimiento.setParteDiario(pd.buscarParteDiario(ot));
                sd.guardarSeguimientoCompleto(seguimiento);
            }
        }
        jBModificar.setEnabled(true);
        modelo = new DefaultTableModel();
        jBGuardar.setEnabled(false);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTabla;
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
