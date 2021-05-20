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
}
