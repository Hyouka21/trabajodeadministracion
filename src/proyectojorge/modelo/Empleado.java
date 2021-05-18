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
public class Empleado {
    
    private long dni;
    private String nombre;
    private String tipo;
    private boolean activo;

    public Empleado(long dni, String nombre, String tipo, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.activo = activo;
    }

    public Empleado() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "dni=" + dni + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
}
