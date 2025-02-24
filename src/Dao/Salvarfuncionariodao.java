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
/**
 *
 * @author ruan
 */
public class Salvarfuncionariodao extends Classeconexao {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public boolean SalvarFuncionarioDao(Modelfuncionariocadastro modelfuncionario) {
        conexao = Classeconexao.conector();
        String sql = "insert into Funcionarios (Nome,telefone,datas,cpf,rg,cidade,numero,bairro,complemento,cep,cargo,chavepix) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modelfuncionario.getNome());
            pst.setString(2, modelfuncionario.getTelefone());
            pst.setString(3, modelfuncionario.getData());
            pst.setString(4, modelfuncionario.getCpf());
            pst.setString(5, modelfuncionario.getRg());
            pst.setString(6, modelfuncionario.getCidade());
            pst.setInt(7, modelfuncionario.getNumero());
            pst.setString(8, modelfuncionario.getBairro());
            pst.setString(9, modelfuncionario.getComplemeto());
            pst.setString(10, modelfuncionario.getCep());
            pst.setString(11, modelfuncionario.getCargo());
            pst.setString(12, modelfuncionario.getChavepix());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
            return false;
        }
        return true;
    }
    public boolean EditarFucnionarioDao(Modelfuncionariocadastro modelfuncionario){
        conexao = Classeconexao.conector();
        String sql = "update Funcionarios set Nome=?,telefone=?,datas=?,cpf=?,rg=?,cidade=?,numero=?,bairro=?,complemento=?,cep=?,cargo=?,chavepix=? where Nome=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modelfuncionario.getNome());
            pst.setString(2, modelfuncionario.getTelefone());
            pst.setString(3, modelfuncionario.getData());
            pst.setString(4, modelfuncionario.getCpf());
            pst.setString(5, modelfuncionario.getRg());
            pst.setString(6, modelfuncionario.getCidade());
            pst.setInt(7, modelfuncionario.getNumero());
            pst.setString(8, modelfuncionario.getBairro());
            pst.setString(9, modelfuncionario.getComplemeto());
            pst.setString(10, modelfuncionario.getCep());
            pst.setString(11, modelfuncionario.getCargo());
            pst.setString(12, modelfuncionario.getChavepix());
            pst.setString(13, modelfuncionario.getNome());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
        }
        return true;
    }

    public List<Modelfuncionariocadastro> gettabelaFuncionario() {
        conexao = Classeconexao.conector();
        List<Modelfuncionariocadastro> listafuncionario = new ArrayList<>();
        Modelfuncionariocadastro modelfuncionario = new Modelfuncionariocadastro();

        String sql = "SELECT Nome, "
                + "telefone, "
                + "datas, "
                + "cpf "
                + "FROM Funcionarios";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelfuncionario = new Modelfuncionariocadastro();
                modelfuncionario.setNome(rs.getString(1));
                modelfuncionario.setTelefone(rs.getString(2));
                modelfuncionario.setData(rs.getString(3));
                modelfuncionario.setCpf(rs.getString(4));
                listafuncionario.add(modelfuncionario);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listafuncionario;
    }

    public List<Modelfuncionariocadastro> gettabelaFuncionario2() {
        conexao = Classeconexao.conector();
        List<Modelfuncionariocadastro> listafuncionario = new ArrayList<>();
        Modelfuncionariocadastro modelfuncionario = new Modelfuncionariocadastro();

        String sql = "SELECT cod, "
                + "Nome, "
                + "cargo, "
                + "telefone, "
                + "chavepix "
                + "FROM Funcionarios";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelfuncionario = new Modelfuncionariocadastro();
                modelfuncionario.setCodigo(rs.getInt(1));
                modelfuncionario.setNome(rs.getString(2));
                modelfuncionario.setCargo(rs.getString(3));
                modelfuncionario.setTelefone(rs.getString(4));
                modelfuncionario.setChavepix(rs.getString(5));
                listafuncionario.add(modelfuncionario);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return listafuncionario;

    }

    public boolean Excluirfuncionario(int codigo) {
        conexao = Classeconexao.conector();
        String sql = "delete from Funcionarios where cod ='" + codigo + "'";
        try {
            pst = conexao.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }

    public Modelfuncionariocadastro carregarFuncionarioPorId(String nome) {
        Modelfuncionariocadastro funcionario = new Modelfuncionariocadastro();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT * FROM Funcionarios WHERE Nome = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setChavepix(rs.getString("chavepix"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setData(rs.getString("datas"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setComplemeto(rs.getString("complemento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCargo(rs.getString("cargo"));
                
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return funcionario;
    }

    public Modelfuncionariocadastro buscarPorCodigo(int codigo) {
    Modelfuncionariocadastro funcionario = new Modelfuncionariocadastro();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        conexao = Classeconexao.conector();
        String sql = "SELECT * FROM Funcionarios WHERE Codigo = ?"; // Use Codigo como parâmetro
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, codigo); // Busca pelo código, não pelo nome
        rs = pst.executeQuery();

        if (rs.next()) {
            funcionario.setCodigo(rs.getInt("Codigo"));
            funcionario.setNome(rs.getString("Nome"));
            funcionario.setChavepix(rs.getString("chavepix"));
            funcionario.setTelefone(rs.getString("telefone"));
            // Preencha todos os campos...
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pst != null) pst.close(); } catch (Exception e) {}
        try { if (conexao != null) conexao.close(); } catch (Exception e) {}
    }

    return funcionario;
}

    public Modelfuncionariocadastro ExibirCadastro(String nome) {
        Modelfuncionariocadastro funcionario = new Modelfuncionariocadastro();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT * FROM Funcionarios WHERE Nome = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setChavepix(rs.getString("chavepix"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setData(rs.getString("datas"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setComplemeto(rs.getString("complemento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCargo(rs.getString("cargo"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return funcionario;
       
        
    }


}

    
    



