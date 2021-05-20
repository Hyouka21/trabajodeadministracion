/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectojorge.modelo.*;

/**
 *
 * @author kevin
 */
public class ParteDiarioData {
    Connection con;

    public ParteDiarioData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarParteDiario(ParteDiario pd){
        try {
            String sql = "INSERT INTO parte_diario(orden_trabajo, numero_tarea, descripcion, detalle, fecha, dni_jefe) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, pd.getOrdenTrabajo());
            ps.setLong(2, pd.getNumeroTarea());
            ps.setString(3, pd.getDescripcion());
            ps.setString(4, pd.getDetalles());
            ps.setDate(5, Date.valueOf(pd.getFecha()));
            ps.setLong(6, pd.getJefe().getDni());
            
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Se agrego correctamente parte diario");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar parte diario");
        }
    }
}
