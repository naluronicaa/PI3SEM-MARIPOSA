
package com.mysql.conexion.ClienteDao;

import VIEW.PaginaInicial;
import com.classes.clientes.Cliente;
import com.mysql.conexion.DBConexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class ClienteDao {
    public void criarRegistro(Cliente c) throws SQLException {
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql = "insert into usuarios (email , senha) values(? , ?)";
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getEmail());
            stmt.setString(2, c.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
    
    public void fazerLogin(Cliente c) throws SQLException {
        Connection con = DBConexion.getConnection();
        String sql = "Select * from usuarios where email=? and senha=?";
        String sql2 = "Select id_usuario from usuarios where email=?";
        try{
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, c.getEmail());
            stmt.setString(2, c.getSenha());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Login jeito com sucesso!");
                PreparedStatement sm = con.prepareStatement(sql2);
                sm.setString(1, c.getEmail());
                
                ResultSet rs2 = sm.executeQuery();
                if (rs2.next()){
                    int id = rs2.getInt("id_usuario");
                    c.setId(id);
                    JOptionPane.showMessageDialog(null, "ID logado: " + c.getId());
                } else{
                    JOptionPane.showMessageDialog(null, "deu ruim");
                }
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Não existe nenhum cadastro com esses dados!");
                c.setId(0);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum erro ao fazer o Login" + e.toString());
        } finally{
            DBConexion.closeConnection(con);
        }
    }
    
}