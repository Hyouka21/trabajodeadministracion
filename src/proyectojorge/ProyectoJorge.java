/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import proyectojorge.control.*;
import proyectojorge.modelo.*;

/**
 *
 * @author kevin
 */
public class ProyectoJorge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion con = new Conexion();
        Empleado e1 = new Empleado(39137254,"kevin","oficial",true);
        Empleado e2 = new Empleado(66666666,"monolo","ayudante",true);
        Jefe j1 = new Jefe(12312312,"Hernan","hernanylachampons@gamil.com",true);
        Jefe j2 = new Jefe(32132132,"Roman","Roman@gamil.com",true);
        Jefe j3 = new Jefe(44444444,"joni","joni2210122@gmail.com",true);
        ParteDiario pd1 = new ParteDiario(12345678, 87654321, "trabajo de cañeria","no", LocalDate.of(2020, Month.MARCH, 21),j1);
        Seguimiento s1 = new Seguimiento(LocalDate.of(2021, Month.MARCH, 22), LocalTime.of(8, 0), LocalTime.of(17, 0), e1, pd1);
        
        LocalDate fechaPrueba = LocalDate.of(2020, Month.MARCH, 21);
        
        
        //clases data.
        EmpleadoData ed = new EmpleadoData(con);
        JefeData jd = new JefeData(con);
        ParteDiarioData pdd = new ParteDiarioData(con);
        SeguimientoData sd = new SeguimientoData(con);
        
        
//        //saca las horas que hizo un empleado en una fecha particular;
//        System.out.println(sd.horasEmpleado(39137254, fechaPrueba));

//        //traer seguimientos por fecha.
//        for(ParteDiario pd:pdd.traerParteDiarioFecha(fechaPrueba)){
//            System.out.println(pd);
//        }

//        Seguimiento[] seguimientos = new Seguimiento[sd.traerSeguimientosDni(39137254).size()];
//        
//        seguimientos = sd.traerSeguimientosDni(39137254).toArray(seguimientos);
//        
//        System.out.println(seguimientos[0].getEmpleado().getNombre());
        
//        ed.guardarEmpleado(e1); // se agrego un empleado.
//        ed.guardarEmpleado(e2); // se agrego un empleado.
//        jd.guardarJefe(j3); // se agrega un jefe.
//        jd.guardarJefe(j2); // se agrega un jefe.

//        //Guardar un parte diario.
//        pdd.guardarParteDiario(pd1);
        
//        //Guardar un seguimiento.
//        sd.guardarSeguimiento(s1);

//        //traer los jefes.
//        for(Jefe a:jd.traerJefes()){
//            System.out.println(a);
//        }
//        
//        //buscar un jefe.
//        System.out.println(jd.buscarJefe(12312312));
//        
//        //dar baja de un jefe.
//        jd.bajaJefe(32132132);
//        
//        //traer los empleados.
//        for(Empleado e:ed.traerEmpleados()){
//            System.out.println(e);
//        }
//        
//        //buscar un empleado
//        System.out.println(ed.buscarEmpleado(39137254));
//        
//        //dar de baja un empleado.
//        ed.bajaEmpleado(66666666);
    }
    
}
