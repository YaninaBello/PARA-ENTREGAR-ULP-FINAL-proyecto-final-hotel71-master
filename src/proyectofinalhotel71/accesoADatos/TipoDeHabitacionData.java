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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyectofinalhotel71.entidades.Habitacion;
import proyectofinalhotel71.entidades.TipoDeHabitacion;

/**
 *
 * @author Bello
 */
public class TipoDeHabitacionData {

    private Connection con = null;
    private Object tipoDeHabitacion;
    private TipoDeHabitacion th= null;

    public TipoDeHabitacionData() {

        this.con = Conexion.getConexion();
    }

    public void altaTipoHabitacion(TipoDeHabitacion tipoDeHabitacion) {

        String sql = "INSERT INTO tipodehabitacion (cantPersonas, cantCamas, tipoCamas, tipoDeHabitacion, precio, estado) VALUES(? ,? ,? ,? ,? ,?)";

        try {
            PreparedStatement ps =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, tipoDeHabitacion.getCantPersonas());
            ps.setInt(2, tipoDeHabitacion.getCantCamas());
            ps.setString(3, tipoDeHabitacion.getTipoCamas());
            ps.setString(4, tipoDeHabitacion.getTipoDehabitacion());
            ps.setInt(5, tipoDeHabitacion.getPrecio());
            ps.setBoolean(6, tipoDeHabitacion.isActivo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                tipoDeHabitacion.setIdTipoDeHabitacion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Categoria registrada");

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tipo de habitacion 1");
        }
    }

    public void modificarTipoDeHabitacion(TipoDeHabitacion tipoDeHabitacion) {

        String sql = "UPDATE tipodehabitacion SET cantPersonas = ?, cantCamas = ?, tipoCamas = ?, precio = ? WHERE idTipoDeHabitacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, tipoDeHabitacion.getCantPersonas());
            ps.setInt(2, tipoDeHabitacion.getCantCamas());
            ps.setString(3, tipoDeHabitacion.getTipoCamas());
            ps.setDouble(4, tipoDeHabitacion.getPrecio());
            ps.setInt(5, tipoDeHabitacion.getIdTipoDeHabitacion());
            int exito = ps.executeUpdate();
            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Habitacion modificada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tipo de habitacion 2");
        }
    }

    public void bajaHabitacion(int id) {
        String sql = "UPDATE tipodehabitacion SET estado = 0 WHERE idTipoDeHabitacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Habitacion eliminada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tipo de habitacion 3");
        }

    }
    
        public TipoDeHabitacion buscarTipoDeHabitacionPorId(int id){
        
        String sql="SELECT cantPersonas, cantCamas, tipoCamas, tipoDeHabitacion, precio, estado FROM tipodehabitacion WHERE idTipoDeHabitacion = ? AND estado = 1";
        TipoDeHabitacion tipoDeHabitacion=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               
                tipoDeHabitacion=new TipoDeHabitacion();
                
                tipoDeHabitacion.setIdTipoDeHabitacion(id);
                tipoDeHabitacion.setCantPersonas(rs.getInt("cantPersonas"));
                tipoDeHabitacion.setCantCamas(rs.getInt("cantCamas"));
                tipoDeHabitacion.setTipoCamas("tipoCamas");
                tipoDeHabitacion.setTipoDeHabitacion("tipoDeHabitacion");
                tipoDeHabitacion.setPrecio(rs.getInt("precio"));
                tipoDeHabitacion.setActivo(true);
                              
            } else{
                
                JOptionPane.showMessageDialog(null, "No existe esa Habitacion");
            }
            ps.close();
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tipoDehabitacion");  
        }
         
        
        return tipoDeHabitacion;
    }
        
//        public List<TipoDeHabitacion> obtenerTipoDeHabitaciondisponible(int idTipoDeHabitacion){
//        ArrayList<TipoDeHabitacion> tipoDeHabitaciones=new ArrayList<>();
//        
//        String sql = "SELECT * FROM tipodehabitacion";
//                 
//        
//        try {
//            PreparedStatement ps=con.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//            
//                TipoDeHabitacion tipoDeHabitaciones=new tipoDeHabitacion();
//                tipoDeHabitacion.setIdTipoDeHabitacion(rs.getInt("idTipoDeHabitacion"));
//                tipoDeHabitacion.setCantPersonas(rs.getInt("cantPersonas"));
//                tipoDeHabitacion.setCantCamas(rs.getInt("cantCamas"));
//                tipoDeHabitacion.setTipoCamas(rs.getInt("tipoCamas"));
//                tipoDeHabitacion.setTipoHabitacion(rs.getInt("tipoHabitacion"));
//                tipoDeHabitacion.setPrecio(rs.getInt("precio"));
//                tipoDeHabitacion.setActivo(true);
//                tipoDeHabitaciones.add(tipoDeHabitaciones);                
//            }
//            
//            ps.close();
//            
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tipo de Habitacion");
//        }
//        return tipoDeHabitaciones;
//    
//        }
}
