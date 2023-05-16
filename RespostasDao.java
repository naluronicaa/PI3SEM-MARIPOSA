
package com.mysql.conexion.RespostasDao;

import com.classes.clientes.Cliente;
import com.classes.respostas.Respostas;
import com.mysql.conexion.DBConexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RespostasDao {
    public void registrarRespostas(Cliente c, Respostas r) throws SQLException{
        Connection con = DBConexion.getConnection();
        PreparedStatement stmt = null;
        String sql = "insert into respostas (id_usuario, id_pergunta, resposta) values (?, ?, ?)";
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "" + c.getId());
            stmt.setString(2, r.getIdP());
            stmt.setString(3, "" + r.getResposta());
            
            stmt.executeUpdate();
                       
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao registrar as respostas: "+e);
        
        } finally{
            DBConexion.closeConnection(con, stmt);
        }
    }
    
    public boolean verificaRespostas (Cliente c) throws SQLException{
        Connection con = DBConexion.getConnection();
        String sql = "Select id_pergunta from respostas where id_usuario=?";
        boolean existe = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "" + c.getId());
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
    
    
}
