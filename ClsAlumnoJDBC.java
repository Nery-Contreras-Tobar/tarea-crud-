/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pkg_dominio.ClsAlumno;

/**
 *
 * @author leste
 */
public class ClsAlumnoJDBC {
    private static final String SQL_SELECT ="Select * from tb_alumnos";
    private static final String SQL_INSERT ="insert into tb_alumnos (nombre,nota1,nota2) values(?,?,?)";
    private static final String SQL_UPDATE ="update tb_alumnos set nombre=?, nota1=?, nota2=? where codigo=?";
    private static final String SQL_DELETE ="delete from tb_alumnos where codigo=?";
    
    //seleccionar alumnos
    public List<ClsAlumno> seleccion(){
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsAlumno alumno = null;
        List<ClsAlumno> alumnos = new ArrayList<>();
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
              int codigo = rs.getInt("codigo");
              String nombre = rs.getString("nombre");
              int nota1= rs.getInt("nota1");
              int nota2= rs.getInt("nota2");
              
              alumno = new ClsAlumno();
              alumno.setCodigo(codigo);
              alumno.setNombre(nombre);
              alumno.setNota1(nota1);
              alumno.setNota2(nota2);
              alumnos.add(alumno);
            }
            
            
        } catch (SQLException e) {
            System.out.println("Hay error insert"+e.getMessage());
        }
        finally{
            ClsConexion.close(rs);
            ClsConexion.close((ResultSet) stmt);
            ClsConexion.close((ResultSet) conn);
        }
    return alumnos;        
    }
    
    
    public int actualizar(ClsAlumno alumno){
        Connection conn= null;
        PreparedStatement stmt = null;
        int rows =0;
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, alumno.getNombre());
            stmt.setInt(2, alumno.getNota1());
            stmt.setInt(3, alumno.getNota2());
            stmt.setInt(4, alumno.getCodigo());
            rows = stmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Hay error update"+e.getMessage());
        }
        finally{
            ClsConexion.close((ResultSet) stmt);
            ClsConexion.close((ResultSet) conn);
        }
    return rows;        
    }
    
    
    public int borrar(ClsAlumno alumno){
        Connection conn= null;
        PreparedStatement stmt = null;
        int rows =0;
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, alumno.getNombre());
            stmt.setInt(2, alumno.getNota1());
            stmt.setInt(3, alumno.getNota2());
            stmt.setInt(4, alumno.getCodigo());
            rows = stmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Hay error delete"+e.getMessage());
        }
        finally{
            ClsConexion.close((ResultSet) stmt);
            ClsConexion.close((ResultSet) conn);
        }
    return rows; 
    }
}
