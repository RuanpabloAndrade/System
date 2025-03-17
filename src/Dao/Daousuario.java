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

    public boolean Salvarusuario(Modelusuario usuarios) {
        con = Classeconexao.conector();
        String sql = "insert into tbusuario2 (loginusuario,senha,administrador,telefone,data_nascimento,cpf,cargo_funcao,nome_usuario,email,data_admissao) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuarios.getUsuario());
            pst.setString(2, usuarios.getSenha());
            pst.setString(3, usuarios.getAdm());
            pst.setString(4, usuarios.getTelefone());
            pst.setString(5, usuarios.getDatanasimento());
            pst.setString(6, usuarios.getCpf());
            pst.setString(7, usuarios.getCargo());
            pst.setString(8, usuarios.getNomeusuario());
            pst.setString(9, usuarios.getEmail());
            pst.setString(10, usuarios.getDataadmissao());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
            return false;
        }
        return true;
    }
    
}
