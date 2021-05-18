/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.modelo;

/**
 *
 * @author kevin
 */
public class Jefe {
    
    private long dni;
    private String nombre;
    private String email;
    private boolean activo;

    public Jefe(long dni, String nombre, String email, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }
    
    public Jefe() {
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    @Override
    public String toString() {
        return "Jefe{" + "nombre=" + nombre + '}';
    }
}
