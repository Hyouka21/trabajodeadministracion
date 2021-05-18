/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.modelo;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class ParteDiario {
    
    private long ordenTrabajo;
    private long numeroTarea;
    private String descripcion;
    private String detalles;
    private LocalDate fecha;
    private Jefe jefe;

    public ParteDiario(long ordenTrabajo, long numeroTarea, String descripcion, String detalles, LocalDate fecha, Jefe jefe) {
        this.ordenTrabajo = ordenTrabajo;
        this.numeroTarea = numeroTarea;
        this.descripcion = descripcion;
        this.detalles = detalles;
        this.fecha = fecha;
        this.jefe = jefe;
    }

    public ParteDiario() {
    }

    public long getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(long ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public long getNumeroTarea() {
        return numeroTarea;
    }

    public void setNumeroTarea(long numeroTarea) {
        this.numeroTarea = numeroTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Jefe getJefe() {
        return jefe;
    }

    public void setJefe(Jefe jefe) {
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return "ParteDiario{" + "ordenTrabajo=" + ordenTrabajo + ", numeroTarea=" + numeroTarea + ", jefe=" + jefe + '}';
    }
}
