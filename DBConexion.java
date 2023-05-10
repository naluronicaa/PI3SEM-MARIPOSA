package com.mysql.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConexion {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String SENHA = "*uaAOYF45dg1";
    private static String URL = "jdbc:mysql://localhost:3306/dbmariposa";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro com o Driver" + e);
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
            JOptionPane.showMessageDialog(null, "Conexão Bem Sucedida");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro com a Conexão" + e);
        }
        return con;
    }
}
