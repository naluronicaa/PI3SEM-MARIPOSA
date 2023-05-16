package com.mysql.conexion.ClienteDao;

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
            
            posDados(c);
            
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
        
    }
    
    public void posDados (Cliente c) {
        Connection con = DBConexion.getConnection();
        String sql = "Select id_usuario from usuarios where email=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        try{
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, c.getEmail());
            
            rs = stmt.executeQuery();
            
            if (rs.next()){
                int id = rs.getInt("id_usuario");
                c.setId(id);
            } else{
                JOptionPane.showMessageDialog(null, "DEU MT RUIM :D");
                c.setId(0);
            }
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum erro: "+ e);
        } finally {
            DBConexion.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean fazerLogin(Cliente c) throws SQLException {
        Connection con = DBConexion.getConnection();
        String sql = "Select * from usuarios where email=? and senha=?";
        String sql2 = "Select id_usuario from usuarios where email=?";
        boolean log = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, c.getEmail());
            stmt.setString(2, c.getSenha());
            
            rs = stmt.executeQuery();
            if (rs.next()){
                log = true;
                stmt = con.prepareStatement(sql2);
                stmt.setString(1, c.getEmail());
                
                rs = stmt.executeQuery();
                if (rs.next()){
                    int id = rs.getInt("id_usuario");
                    c.setId(id);
                } else{
                    JOptionPane.showMessageDialog(null, "deu ruim");
                }
                
            }else{
                log = false;
                c.setId(0);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum erro ao fazer o Login" + e.toString());
        } finally{
            DBConexion.closeConnection(con, stmt, rs);
        
        }
        
        return log;    
    }
    
    public void trazerDados(Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        String sql = "Select email, senha from usuarios where id_usuario=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ""+c.getId());
            rs = stmt.executeQuery();
            if(rs.next()){
                String email = rs.getString("email");
                c.setEmail(email);
                String senha = rs.getString("senha");
                c.setSenha(senha);
            } else{
                JOptionPane.showMessageDialog(null, "Houve algum erro");
                c.setEmail("");
                c.setSenha("");
            }
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum" + e.toString());
        } finally{
            DBConexion.closeConnection(con, stmt, rs);
        }
            
     }
    
    public boolean verificaEmail(Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        String sql = "Select email from usuarios where email=?";
        boolean existe = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getEmail());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                existe = true;
            } else {
                existe = false;
            }
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum erro " + e.toString());
        } finally {
            DBConexion.closeConnection(con, stmt, rs);
        }
        
        return existe;
    }
    
    public void alterarRegistro(Cliente c) throws SQLException {
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql1 = "update usuarios set senha=? where id_usuario=?";
        String sql2 = "update usuarios set email=? where id_usuario=?";
        String sql3 = "update usuarios set email=?, senha=? where id_usuario=?";
        try{
            if(c.getEmail().equals("")){
                stmt = con.prepareStatement(sql1);
                stmt.setString(1, c.getSenha());
                stmt.setString(2, ""+ c.getId());
                
                stmt.executeUpdate();
                
                
            } else if (c.getSenha().equals("")){
                stmt = con.prepareStatement(sql2);
                stmt.setString(1, c.getEmail());
                stmt.setString(2, "" + c.getId());
                
                stmt.executeUpdate();
                
            }else{
                stmt = con.prepareStatement(sql3);
                stmt.setString(1, c.getEmail());
                stmt.setString(2, c.getSenha());
                stmt.setString(3, "" + c.getId());
                
                stmt.executeUpdate();
                
                
            }
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve algum erro ao atualizar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
    
    public void deletarDados2(Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql = "delete from usuarios where id_usuario = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "" + c.getId());        
            stmt.executeUpdate();
            c.setId(0);
                        
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao deletar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
    
    public void deletarDados1(Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql = "delete from respostas where id_usuario = ?";
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "" + c.getId());
            
            stmt.executeUpdate();
            
            deletarDados2(c);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao deletar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
    
    public void deletarDados3(Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql = "delete from respostas where id_usuario = ?";
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "" + c.getId());
            
            stmt.executeUpdate();
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao deletar seus dados" + e.toString());
        }finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
}
