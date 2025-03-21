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
import javax.swing.JOptionPane;
import model.Modelusuario;
import model.modelclientes;

/**
 *
 * @author ruan
 */
public class daoclientes extends Classeconexao{
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    public boolean salvarclientes(modelclientes clientes) {
        conexao = Classeconexao.conector();
        String sql = "insert into tabelaclientes (nome,rg,cpf,sexo,email,data_cadastro,rua,complemento,bairro,cidadde,telefone,datanasimento) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, clientes.getNome());
            pst.setString(2, clientes.getRg());
            pst.setString(3, clientes.getCpf());
            pst.setString(4, clientes.getSexo());
            pst.setString(5, clientes.getEmail());
            pst.setString(6, clientes.getDatacadastro());
            pst.setString(7, clientes.getRua());
            pst.setString(8, clientes.getComplemento());
            pst.setString(9, clientes.getBairro());
            pst.setString(10, clientes.getCidade());
            pst.setString(11, clientes.getTelefone());
            pst.setString(12, clientes.getNascimento());  
         
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado! veja se já consta na tabela do banco no botão vizualizar!");
            return false;
        }
        return true;
    }

    public List<modelclientes> Carregarclientetabela() {
        conexao = Classeconexao.conector();
        List<modelclientes> listacliente = new ArrayList<>();
        modelclientes clientes = new modelclientes();

        String sql = "SELECT nome, "
                + "telefone, "
                + "cpf, "
                + "email "
                + "FROM tabelaclientes";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                clientes = new modelclientes();
                clientes.setNome(rs.getString(1));
                clientes.setTelefone(rs.getString(2));
                clientes.setCpf(rs.getString(3));
                clientes.setEmail(rs.getString(4));
                listacliente.add(clientes);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listacliente;
    }

    public modelclientes carregarusuarios(String cpfvariavel) {
            modelclientes clientes = new modelclientes();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT * FROM tabelaclientes WHERE cpf = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cpfvariavel);
            rs = pst.executeQuery();
            if (rs.next()) {
                clientes.setCodigo(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setRg(rs.getString("rg"));
                clientes.setCpf(rs.getString("cpf"));
                clientes.setSexo(rs.getString("sexo"));
                clientes.setEmail(rs.getString("email"));
                clientes.setDatacadastro(rs.getString("data_cadastro"));
                clientes.setRua(rs.getString("rua"));
                clientes.setComplemento(rs.getString("complemento"));
                clientes.setBairro(rs.getString("bairro"));
                clientes.setCidade(rs.getString("cidadde")); 
                clientes.setTelefone(rs.getString("telefone")); 
                clientes.setNascimento(rs.getString("datanasimento")); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return clientes; 
    }

    public boolean EditarClienteDao(modelclientes clientes) {
    conexao = Classeconexao.conector();
    String sql = "update tabelaclientes set id=?, nome=?, rg=?, cpf=?, sexo=?, email=?, data_cadastro=?, rua=?, complemento=?, bairro=?, cidadde=?, telefone=?, datanasimento=? where id=?";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, clientes.getCodigo());
        pst.setString(2, clientes.getNome());
        pst.setString(3, clientes.getRg());
        pst.setString(4, clientes.getCpf());
        pst.setString(5, clientes.getSexo());
        pst.setString(6, clientes.getEmail());
        pst.setString(7, clientes.getDatacadastro());
        pst.setString(8, clientes.getRua());
        pst.setString(9, clientes.getComplemento());
        pst.setString(10, clientes.getBairro());
        pst.setString(11, clientes.getCidade());
        pst.setString(12, clientes.getTelefone());
        pst.setString(13, clientes.getNascimento());
        pst.setInt(14, clientes.getCodigo()); 
        pst.executeUpdate();
    } catch (Exception e) {
        System.err.println(e);
        JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
        return false;
    }
    return true;
    }
    
}
