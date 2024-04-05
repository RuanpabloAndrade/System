/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexao.Classeconexao;
import model.modelusu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ruan
 */
public class Daousu extends Classeconexao{
   Connection conexao = null;
   PreparedStatement pst=null;
   
    public boolean salvarusuario(modelusu pmodelusuario) {
      conexao=Classeconexao.conector();
      String sql=("insert into tbusuario2(loginusuario,senha,administrador,telefone,datanascimento,cpf,nome,admissãp,email,cargo) values(?,?,?,?,?,?,?,?,?,?)");
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, pmodelusuario.getLoginusu());
            pst.setString(2, pmodelusuario.getSenha());
            pst.setString(3, pmodelusuario.getAdmim());
            pst.setString(4, pmodelusuario.getTelefone());
            pst.setString(5, pmodelusuario.getNascimento());
            pst.setString(6, pmodelusuario.getCpf());
            pst.setString(7, pmodelusuario.getNome());
            pst.setString(8, pmodelusuario.getAdmiss());
            pst.setString(9, pmodelusuario.getEmail());
            pst.setString(10, pmodelusuario.getCargo());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Daousu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
