/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalhotel71.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import proyectofinalhotel71.entidades.Habitacion;
import proyectofinalhotel71.entidades.TipoDeHabitacion;

/**
 *
 * @author Bello
 */
public class HabitacionData {

    private Connection con = null;
    private Object habitaciones;

    public HabitacionData() {

        this.con = Conexion.getConexion();

    }

    public void agregarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO habitaciones(idTipoDeHabitacion,estado,piso) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, habitacion.getIdTipoDeHabitacion());
            ps.setBoolean(2, habitacion.isActivo());
            ps.setInt(3, habitacion.getPiso());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                habitacion.setIdHabitacion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Habitacion añadido con exito");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Habitacion " + ex.getMessage());
        }
    }

    public void modificarHabitacion(Habitacion habitacion){

        String sql = "UPDATE habitaciones SET idTipoDeHabitacion = ?, piso = ?, estado = ? WHERE idHabitacion = ?";

        try {

            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, habitacion.getIdTipoDeHabitacion());
            ps.setInt(2, habitacion.getPiso());
            ps.setInt(3, habitacion.getIdHabitacion());
            ps.setBoolean(4, habitacion.isActivo());
            int exito=ps.executeUpdate();

            if(exito==1){
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La Habitacion no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Habitacion " + ex.getMessage());
        }

    }

    public void deshabilitarHabitacion(int idHabitacion) {

        try {
            String sql = "UPDATE habitaciones SET estado = 0 WHERE idHabitacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHabitacion);
            int fila =ps.executeUpdate();

            if (fila==1){
                JOptionPane.showMessageDialog(null, "Habitacion Suspendida.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Habitacion");
        }
    }
    
    public Habitacion buscarHabitacionPorId(int idHabitacion){
        
        String sql="SELECT piso, estado, idTipoDeHabitacion FROM habitaciones WHERE idHabitacion = ? AND estado = 1";
        Habitacion habitacion=null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHabitacion);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               
                habitacion=new Habitacion();
                
                habitacion.setIdHabitacion(idHabitacion);
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setTipoDeHabitacion(rs.getInt("idTipoDeHabitacion"));
                habitacion.setActivo(true);
                              
            } else{
                
                JOptionPane.showMessageDialog(null, "No existe esa Habitacion");
            }
            ps.close();
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error al acceder a la tabla habitaciones");  
        }
        return habitacion;
    }
}