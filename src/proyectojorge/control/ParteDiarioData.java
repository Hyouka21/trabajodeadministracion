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
import java.time.LocalDate;
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
    JefeData jd;

    public ParteDiarioData(Conexion conexion, JefeData jd) {
        con = conexion.getConnection();
        this.jd = jd;
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
    
    public ParteDiario buscarParteDiario(long ot){
        ParteDiario pt = new ParteDiario();
        try {
            String sql = "SELECT * FROM parte_diario WHERE orden_trabajo=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, ot);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pt.setOrdenTrabajo(rs.getLong(1));
                pt.setNumeroTarea(rs.getLong(2));
                pt.setDescripcion(rs.getString(3));
                pt.setDetalles(rs.getString(4));
                pt.setFecha(LocalDate.parse(String.valueOf(rs.getDate(5))));
                pt.setJefe(jd.buscarJefe(rs.getLong(6)));
                JOptionPane.showMessageDialog(null, "se encontro con exito el parte diario");
            }else{
                JOptionPane.showMessageDialog(null, "No se el parte diario");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el parte diario");
        }
        return pt;
    }
}
