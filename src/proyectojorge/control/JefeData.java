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
import proyectojorge.modelo.Jefe;

/**
 *
 * @author kevin
 */
public class JefeData {
    Connection con;

    public JefeData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarJefe(Jefe jefe){
        try {
            String sql = "INSERT INTO jefe (dni, nombre, email, activo) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setLong(1, jefe.getDni());
            ps.setString(2, jefe.getNombre());
            ps.setString(3, jefe.getEmail());
            ps.setBoolean(4, jefe.isActivo());
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Se agrego correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el jefe");
        }
    }
    
    public List<Jefe> traerJefes(){
        List<Jefe> jefes = new ArrayList<>();
        Jefe jefe;
        try {
            
            String sql = "SELECT * FROM jefe";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                jefe = new Jefe();
                jefe.setDni(rs.getLong(1));
                jefe.setNombre(rs.getString(2));
                jefe.setEmail(rs.getString(3));
                jefe.setActivo(rs.getBoolean(4));
                jefes.add(jefe);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(JefeData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jefes;
    }
    
    public Jefe buscarJefe(long dni){
        Jefe jefe = new Jefe();
        try {
            String sql = "SELECT * FROM jefe WHERE dni=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                jefe.setDni(rs.getLong(1));
                jefe.setNombre(rs.getString(2));
                jefe.setEmail(rs.getString(3));
                jefe.setActivo(rs.getBoolean(4));
               // JOptionPane.showMessageDialog(null, "Se encontro el jefe correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el jefe");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar jefe");
        }
        return jefe;
    }
    
    public void bajaJefe(long dni){
        try {
            String sql = "UPDATE jefe SET activo = false WHERE dni = ?";
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
