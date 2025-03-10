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
import model.Modelfornecedor;
import model.Modelfuncionariocadastro;

/**
 *
 * @author ruan
 */
public class Daofornecedor extends Classeconexao{
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    public boolean salvarfornecedor(Modelfornecedor fornecedor) {
        conexao = Classeconexao.conector();
        String sql = "insert into cadastro_empresa (razao_social,inscricao_estadual,telefone,nome_fantasia,cnpj,fax,email,chave_pix,logradouro,numero,bairro,complemento,cidade,estado,cep,observacoes,status1,data_cadastro,categoria) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, fornecedor.getRazaosocial());
            pst.setString(2, fornecedor.getIncrocaoestadual());
            pst.setString(3, fornecedor.getTelefone());
            pst.setString(4, fornecedor.getNomefantasia());
            pst.setString(5, fornecedor.getCnpj());
            pst.setString(6, fornecedor.getFax());
            pst.setString(7, fornecedor.getEmail());
            pst.setString(8, fornecedor.getPixchave());
            pst.setString(9, fornecedor.getLogradouto());
            pst.setInt(10, fornecedor.getNumero());
            pst.setString(11, fornecedor.getBairro());
            pst.setString(12, fornecedor.getComplemento());
            pst.setString(13, fornecedor.getCidade());
            pst.setString(14, fornecedor.getEstado());
            pst.setString(15, fornecedor.getCep());
            pst.setString(16, fornecedor.getObservacao());
            pst.setString(17, fornecedor.getStatus());
            pst.setString(18, fornecedor.getData());
            pst.setString(19, fornecedor.getCategoria());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado! veja se já consta na tabela do banco no botão vizualizar!");
            return false;
        }
        return true;
    }

    public List<Modelfornecedor> gettabelaFornecedor() {
        conexao = Classeconexao.conector();
        List<Modelfornecedor> listafornecedor = new ArrayList<>();
        Modelfornecedor modelfornecedor = new Modelfornecedor();

        String sql = "SELECT id, "
                + "nome_fantasia, "
                + "telefone, "
                + "categoria, "
                + "cnpj "
                + "FROM cadastro_empresa";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelfornecedor = new Modelfornecedor();
                modelfornecedor.setCodigo(rs.getInt(1));
                modelfornecedor.setNomefantasia(rs.getString(2));
                modelfornecedor.setTelefone(rs.getString(3));
                modelfornecedor.setCategoria(rs.getString(4));
                modelfornecedor.setCnpj(rs.getString(5));
                listafornecedor.add(modelfornecedor);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return listafornecedor;
    }

    public boolean Excluirfornecedor(int codigo) {
        conexao = Classeconexao.conector();
        String sql = "delete from cadastro_empresa where id ='" + codigo + "'";
        try {
            pst = conexao.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }

    public Modelfornecedor ExibirCadastro(String nome) {
         Modelfornecedor modelfornecedor = new Modelfornecedor();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT * FROM cadastro_empresa WHERE nome_fantasia = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                modelfornecedor.setRazaosocial(rs.getString("razao_social"));
                modelfornecedor.setIncrocaoestadual(rs.getString("inscricao_estadual"));
                modelfornecedor.setNomefantasia(rs.getString("nome_fantasia"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return modelfornecedor;
       
    }

    
    
}
