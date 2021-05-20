/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<Empleado> traerEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado;
        try {
            String sql = "SELECT * FROM empleado";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                empleado = new Empleado();
                empleado.setDni(rs.getLong(1));
                empleado.setNombre(rs.getString(2));
                empleado.setTipo(rs.getString(3));
                empleado.setActivo(rs.getBoolean(4));
                empleados.add(empleado);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }
    
    public Empleado buscarEmpleado(long dni){
        Empleado empleado = new Empleado();
        try {
            String sql = "SELECT * FROM empleado WHERE dni=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                empleado.setDni(rs.getLong(1));
                empleado.setNombre(rs.getString(2));
                empleado.setTipo(rs.getString(3));
                empleado.setActivo(rs.getBoolean(4));
                JOptionPane.showMessageDialog(null, "Se encontra el empleado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el empleado");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar empleado");
        }
        return empleado;
    }
    
    public void bajaEmpleado(long dni){
        try {
            String sql = "UPDATE empleado SET activo = false WHERE dni = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dni);
            
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "se dio de baja correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "no se dio la baja");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al dar la baja");
        }
    }
}
