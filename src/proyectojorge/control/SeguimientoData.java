/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.control;

import java.sql.Connection;

/**
 *
 * @author kevin
 */
public class SeguimientoData {
    Connection con;

    public SeguimientoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
}
