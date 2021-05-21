/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectojorge.modelo.*;

/**
 *
 * @author kevin
 */
public class SeguimientoData {
    Connection con;

    public SeguimientoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarSeguimiento(Seguimiento seg){
        try {
            String sql = "INSERT INTO seguimiento(fecha, hora_inicio, hora_final, orden_trabajo, numero_tarea, dni_empleado) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(seg.getFecha()));
            ps.setTime(2, Time.valueOf(seg.getHoraInicio()));
            ps.setTime(3, Time.valueOf(seg.getHoraFinal()));
            ps.setLong(4, seg.getParteDiario().getOrdenTrabajo());
            ps.setLong(5, seg.getParteDiario().getNumeroTarea());
            ps.setLong(6, seg.getEmpleado().getDni());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                seg.setIdSeguimiento(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "el seguimiento se agrego correctamente");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al agregar el seguimiento");
        }
    }
    
    public LocalTime horasEmpleado(long dni, LocalDate fecha){
        LocalTime horas = null;
        try {
            String sql = "select d.nombreCompleto,SEC_TO_TIME(sum(TIME_TO_SEC(d.horasTrabajadas))) as TotalHoras from ( select e.nombre as nombreCompleto,TIMEDIFF( s.hora_final ,s.hora_inicio) as horasTrabajadas from seguimiento s , empleado e where s.dni_empleado = ? and s.dni_empleado = e.dni and s.fecha = ? ) as d";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dni);
            ps.setDate(2, Date.valueOf(fecha));
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                horas = LocalTime.parse(String.valueOf(rs.getTime(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeguimientoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return horas;
    }
    
}
