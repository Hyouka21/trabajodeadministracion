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
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hernan14
 */
public class GenerarPDF {
        String ot,nt;
        private int horasTotalesAyudante50, horasTotalesAyudanteNormales,horasTotalesOficial50, horasTotalesOficialNormales;
        private List<Integer> horas50BD;
        private List<Integer> horasNormalesBD;
        private DefaultTableModel modelo;

    public GenerarPDF(String ot, String nt, int horasTotalesAyudante50, int horasTotalesAyudanteNormales, int horasTotalesOficial50, int horasTotalesOficialNormales, List<Integer> horas50BD, List<Integer> horasNormalesBD, DefaultTableModel modelo) {
        this.ot = ot;
        this.nt = nt;
        this.horasTotalesAyudante50 = horasTotalesAyudante50;
        this.horasTotalesAyudanteNormales = horasTotalesAyudanteNormales;
        this.horasTotalesOficial50 = horasTotalesOficial50;
        this.horasTotalesOficialNormales = horasTotalesOficialNormales ;  
        this.horas50BD = horas50BD;
        this.horasNormalesBD = horasNormalesBD;
        this.modelo = modelo;
    }
        
    public void GenerarPdf() throws IOException{
            String dniu = ot+"-"+nt;
            PrintStream ps = null; 
            //JOptionPane.showMessageDialog(this,horasTotalesAyudanteNormales); 
            try {
                String ruta = System.getProperty("user.home");
                File file = new File(ruta+"/desktop/ProyectoTrabajo/"+dniu+".pdf"); //RUTA A DEFINIR POR EL USUARIO
                PdfWriter writer = new PdfWriter(file);
                PdfDocument pdf = new PdfDocument(writer);
                ps = new PrintStream(writer);
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
                    tabla.addCell("0");
                    tabla.addCell(horas50BD.get(i).toString());
                    tabla.addCell(horasNormalesBD.get(i).toString());
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
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf("0"))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesAyudante50))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesAyudanteNormales))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/oficial:")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf("0"))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesOficial50))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph(String.valueOf(horasTotalesOficialNormales))).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ayud nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("Total horas/ofic nocturnas:")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,1).add(new Paragraph("  ")).setHeight(33).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
                tabla.addCell(new Cell(1,9).add(observaciones.addCell(new Cell(1,9).add(new Paragraph("observaciones: ")).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                    .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderBottom(bd))
                    .addCell(new Cell(1,9).setMaxWidth(950f).setHeight(17).setBorderTop(bd))).setPaddings(4,4,4,4));
            tablacont.addCell(tabla);
            documento.add(tablacont);
            documento.close();
            JOptionPane.showMessageDialog(null,"PDF Creado"); 
            } catch (MalformedURLException ex) {
                Logger.getLogger(VistaSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(ps != null){
                    ps.close();
                }
            }
    }
}
