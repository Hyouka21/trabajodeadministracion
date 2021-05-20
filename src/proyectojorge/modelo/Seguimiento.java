/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojorge.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author kevin
 */
public class Seguimiento {
    
    private int idSeguimiento;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private Empleado empleado;
    private ParteDiario parteDiario;
    private int horasNormales;
    private int horas_50;
    private int horas_100;

    public Seguimiento(int idSeguimiento, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal, Empleado empleado, ParteDiario parteDiario, int horasNormales, int horas_50, int horas_100) {
        this.idSeguimiento = idSeguimiento;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.empleado = empleado;
        this.parteDiario = parteDiario;
        this.horasNormales = horasNormales;
        this.horas_50 = horas_50;
        this.horas_100 = horas_100;
    }

    public Seguimiento(LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal, Empleado empleado, ParteDiario parteDiario, int horasNormales, int horas_50, int horas_100) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.empleado = empleado;
        this.parteDiario = parteDiario;
        this.horasNormales = horasNormales;
        this.horas_50 = horas_50;
        this.horas_100 = horas_100;
    }

    public Seguimiento(LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal, Empleado empleado, ParteDiario parteDiario) {
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

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
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

    public int getHorasNormales() {
        return horasNormales;
    }

    public void setHorasNormales(int horasNormales) {
        this.horasNormales = horasNormales;
    }

    public int getHoras_50() {
        return horas_50;
    }

    public void setHoras_50(int horas_50) {
        this.horas_50 = horas_50;
    }

    public int getHoras_100() {
        return horas_100;
    }

    public void setHoras_100(int horas_100) {
        this.horas_100 = horas_100;
    }

    @Override
    public String toString() {
        return "Seguimiento{" + "idSeguimiento=" + idSeguimiento + '}';
    }
    
    
}
