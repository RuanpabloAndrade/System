/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexao.Classeconexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Modelfuncionariocadastro;
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

    public List<Modelusuario> Carregarusuariotabela() {
        con = Classeconexao.conector();
        List<Modelusuario> listausuario = new ArrayList<>();
        Modelusuario modelusuario = new Modelusuario();

        String sql = "SELECT cod, "
                + "nome_usuario, "
                + "telefone, "
                + "email "
                + "FROM tbusuario2";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelusuario = new Modelusuario();
                modelusuario.setCodigo(rs.getInt(1));
                modelusuario.setNomeusuario(rs.getString(2));
                modelusuario.setTelefone(rs.getString(3));
                modelusuario.setEmail(rs.getString(4));
                listausuario.add(modelusuario);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listausuario;
    }

    public Modelusuario carregarusuarios(int codigo) {
        Modelusuario modelusuario = new Modelusuario();
        try {
            con = Classeconexao.conector();
            String sql = "SELECT * FROM tbusuario2 WHERE cod = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                modelusuario.setCodigo(rs.getInt("cod"));
                modelusuario.setUsuario(rs.getString("loginusuario"));
                modelusuario.setSenha(rs.getString("senha"));
                modelusuario.setAdm(rs.getString("administrador"));
                modelusuario.setTelefone(rs.getString("telefone"));
                modelusuario.setDatanasimento(rs.getString("data_nascimento"));
                modelusuario.setCpf(rs.getString("cpf"));
                modelusuario.setCargo(rs.getString("cargo_funcao"));
                modelusuario.setNomeusuario(rs.getString("nome_usuario"));
                modelusuario.setEmail(rs.getString("email"));
                modelusuario.setDataadmissao(rs.getString("data_admissao")); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return modelusuario;
    }

    public List<Modelusuario> Carregarusuariotabelavizualizar() {
        con = Classeconexao.conector();
        List<Modelusuario> listausuario = new ArrayList<>();
        Modelusuario modelusuario = new Modelusuario();

        String sql = "SELECT cod, "
                + "loginusuario, "
                + "telefone, "
                + "cpf, "
                + "nome_usuario, "
                + "email "
                + "FROM tbusuario2";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelusuario = new Modelusuario();
                modelusuario.setCodigo(rs.getInt(1));
                modelusuario.setUsuario(rs.getString(2));
                modelusuario.setTelefone(rs.getString(3));
                modelusuario.setCpf(rs.getString(4));
                modelusuario.setNomeusuario(rs.getString(5));
                modelusuario.setEmail(rs.getString(6));
                listausuario.add(modelusuario);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listausuario;
    }

    public boolean Excluirusuario(int codigo) {
        con = Classeconexao.conector();
        String sql = "delete from tbusuario2 where cod ='" + codigo + "'";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }

    public Modelusuario ExibirCadastrousuario(int codigo) {
          Modelusuario modelusuario = new Modelusuario();
        try {
            con = Classeconexao.conector();
            String sql = "SELECT * FROM tbusuario2 WHERE cod = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                modelusuario.setCodigo(rs.getInt("cod"));
                modelusuario.setUsuario(rs.getString("loginusuario"));
                modelusuario.setSenha(rs.getString("senha"));
                modelusuario.setAdm(rs.getString("administrador"));
                modelusuario.setTelefone(rs.getString("telefone"));
                modelusuario.setDatanasimento(rs.getString("data_nascimento"));
                modelusuario.setCpf(rs.getString("cpf"));
                modelusuario.setCargo(rs.getString("cargo_funcao"));
                modelusuario.setNomeusuario(rs.getString("nome_usuario"));
                modelusuario.setEmail(rs.getString("email"));
                modelusuario.setDataadmissao(rs.getString("data_admissao"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return modelusuario;
    }

   public boolean EditarUsuario(Modelusuario usuarios) {
    con = Classeconexao.conector();
    String sql = "update tbusuario2 set cod=?, loginusuario=?, senha=?, administrador=?, telefone=?, data_nascimento=?, cpf=?, cargo_funcao=?, nome_usuario=?, email=?, data_admissao=? where cod=?";
    try {
        pst = con.prepareStatement(sql);
        pst.setInt(1, usuarios.getCodigo());
        pst.setString(2, usuarios.getUsuario());
        pst.setString(3, usuarios.getSenha());
        pst.setString(4, usuarios.getAdm());
        pst.setString(5, usuarios.getTelefone());
        pst.setString(6, usuarios.getDatanasimento());
        pst.setString(7, usuarios.getCpf());
        pst.setString(8, usuarios.getCargo());
        pst.setString(9, usuarios.getNomeusuario());
        pst.setString(10, usuarios.getEmail());
        pst.setString(11, usuarios.getDataadmissao());
        pst.setInt(12, usuarios.getCodigo()); // O código para o WHERE vem por ultimo.
        pst.executeUpdate();
    } catch (Exception e) {
        System.err.println(e);
        JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
        return false;
    }
    return true;
}
    
}
