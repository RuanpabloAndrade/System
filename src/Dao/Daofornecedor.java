/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Classeconexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Modelfornecedor;

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
        String sql = "insert into cadastro_empresa (razao_social,inscricao_estadual,telefone,nome_fantasia,cnpj,fax,email,chave_pix,logradouro,numero,bairro,complemento,cidade,estado,cep,observacoes,status1,data_cadastro) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado! veja se já consta na tabela do banco no botão vizualizar!");
            return false;
        }
        return true;
    }

    
    
}
