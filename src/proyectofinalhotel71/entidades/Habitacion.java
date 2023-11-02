/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalhotel71.entidades;

/**
 *
 * @author Bello
 */
public class Habitacion {

    private int idHabitacion;
    private int piso;
    private boolean activo;
    private int idTipoDeHabitacion;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, int piso, boolean activo, int idTipoDeHabitacion) {
        this.idHabitacion = idHabitacion;
        this.piso = piso;
        this.activo = activo;
        this.idTipoDeHabitacion = idTipoDeHabitacion;
    }

    public Habitacion(int piso, boolean activo, int idTipoDeHabitacion) {
        this.piso = piso;
        this.activo = activo;
        this.idTipoDeHabitacion = idTipoDeHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdTipoDeHabitacion() {
        return idTipoDeHabitacion;
    }

    public void setTipoDeHabitacion(int idTipoDeHabitacion) {
        this.idTipoDeHabitacion = idTipoDeHabitacion;
    }

    @Override
    public String toString() {
        String habitacion = idHabitacion + " " + idTipoDeHabitacion;
        return habitacion;}
}
//.getIdTipoDeHabitacion() + ", " + idTipoDeHabitacion.getCantPersonas() + ", " + idTipoDeHabitacion.getCantCamas() + ", " + idTipoDeHabitacion.getTipoCamas() + ", " + idTipoDeHabitacion.getTipoDehabitacion() + ", " + idTipoDeHabitacion.getPrecio()