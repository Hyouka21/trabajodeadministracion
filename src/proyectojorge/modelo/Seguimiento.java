/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.modelo;

import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Seguimiento {
    
    private long idSeguimiento;
    private LocalDate fecha;
    private Time horaInicio;
    private Time horaFinal;
    private Empleado empleado;
    private ParteDiario parteDiario;

    public Seguimiento(long idSeguimiento, LocalDate fecha, Time horaInicio, Time horaFinal, Empleado empleado, ParteDiario parteDiario) {
        this.idSeguimiento = idSeguimiento;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.empleado = empleado;
        this.parteDiario = parteDiario;
    }

    public Seguimiento() {
    }

    public long getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ParteDiario getParteDiario() {
        return parteDiario;
    }

    public void setParteDiario(ParteDiario parteDiario) {
        this.parteDiario = parteDiario;
    }

    @Override
    public String toString() {
        return "Seguimiento{" + "idSeguimiento=" + idSeguimiento + '}';
    }
    
    
}
