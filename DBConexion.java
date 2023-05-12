package com.mysql.conexion;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBConexion {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String SENHA = "*uaAOYF45dg1";
    private static String URL = "jdbc:mysql://localhost:3306/dbmariposa";

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Erro na conexão com o banco de dados: "+e);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if (con  != null){
                con.close();
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dado+ " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try{
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dado+ " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try{
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dado+ " + e);
        }
    }
    
    
}
