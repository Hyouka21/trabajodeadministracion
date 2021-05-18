/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge;

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
        Jefe j1 = new Jefe(12312312,"Hernan","hernanylachampons@gamil.com",true);
        Jefe j2 = new Jefe(32132132,"Roman","Roman@gamil.com",true);
        EmpleadoData ed = new EmpleadoData(con);
        JefeData jd = new JefeData(con);
        
//        ed.guardarEmpleado(e1); // se agrego un empleado.
//        jd.guardarJefe(j1);
//        jd.guardarJefe(j2);
        for(Jefe a:jd.traerJefes()){
            System.out.println(a.toString());
        }
    }
    
}
