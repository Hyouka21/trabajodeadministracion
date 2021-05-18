/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectojorge.modelo.Empleado;

/**
 *
 * @author kevin
 */
public class EmpleadoData {
    Connection con;

    public EmpleadoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    
    public void guardarEmpleado(Empleado empleado){
        try {
            String sql = "INSERT INTO empleado (dni, nombre, tipo, activo) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setLong(1, empleado.getDni());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getTipo());
            ps.setBoolean(4, empleado.isActivo());
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Se agrego correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el empleado");
        }
    }
}
