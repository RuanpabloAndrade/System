/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexao.Classeconexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Modelusuario;
import viw.Telaprincipal;

/**
 *
 * @author ruan
 */
public class Daousuario extends Classeconexao{
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null; 
    
   public boolean Logar(Modelusuario model) {
    con = Classeconexao.conector();
    String sql = "SELECT loginusuario, senha FROM tbusuario2 WHERE loginusuario = ? AND senha = ?";

    try {
        pst = con.prepareStatement(sql);
        pst.setString(1, model.getUsuario());
        pst.setString(2, model.getSenha());
        rs = pst.executeQuery();
        if (rs.next()) {
            Telaprincipal principal = new Telaprincipal();
            principal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,"Login ou senha incorretos!");
        }
    } catch (Exception ex) {
        Logger.getLogger(Daousuario.class.getName()).log(Level.SEVERE, null, ex);
    }

    return true;
}
    
}
