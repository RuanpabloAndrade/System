/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Classeconexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Modelrecebiveis;
import model.modelclientes;
import model.modelproduto;

/**
 *
 * @author ruan
 */
public class Produtodao  extends Classeconexao{
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean SalvarFuncionarioDao(modelproduto produto) {
        conexao = Classeconexao.conector();
String sql = "INSERT INTO Produto (descricao_produto, codigo_barras, quantidade, unidade_medida, validade, fornecedor, lote, custo, venda, atacado, promocao, estoque_critico, datafabricacao, peso, categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

try {
    pst = conexao.prepareStatement(sql);
    pst.setString(1, produto.getDescricaoProduto());
    pst.setString(2, produto.getCodigoBarras());
    pst.setInt(3, produto.getQuantidade());
    pst.setString(4, produto.getUnidadeMedida());
    pst.setString(5, produto.getDataValidade());
    pst.setString(6, produto.getFornecedor());
    pst.setString(7, produto.getLote());
    pst.setDouble(8, produto.getPrecoCusto());
    pst.setDouble(9, produto.getPrecoVenda());
    pst.setDouble(10, produto.getPrecoAtacado());
    pst.setDouble(11, produto.getPrecoPromocao());
    pst.setInt(12, produto.getEstoqueCritico());
    pst.setString(13, produto.getDatafabricacao());
    pst.setDouble(14, produto.getPeso());
    pst.setString(15, produto.getCategoria());
    pst.executeUpdate();
} catch (Exception e) {
    System.err.println(e);
    JOptionPane.showMessageDialog(null, "Produto não cadastrado. Verifique os dados e tente novamente.");
    return false;
}
return true;
    }

    public List<modelproduto> Carregarprodutos() {
        conexao = Classeconexao.conector();
        List<modelproduto> listaproduto = new ArrayList<>();
        modelproduto produto = new modelproduto();

        String sql = "SELECT descricao_produto, "
                + "quantidade, "
                + "validade, "
                + "venda "
                + "FROM Produto";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new modelproduto();
                produto.setDescricaoProduto(rs.getString(1));
                produto.setQuantidade(rs.getInt(2));
                produto.setDataValidade(rs.getString(3));
                produto.setPrecoVenda(rs.getDouble(4));
                listaproduto.add(produto);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listaproduto;
    }

    public boolean SalvarloteDao(modelproduto produto) {
          conexao = Classeconexao.conector();
String sql = "INSERT INTO Lotes (fornecedor, nomeacao_lote, lote, validade, codigo_barras, data_fabricacao) VALUES (?, ?, ?, ?, ?, ?)";

try {
    pst = conexao.prepareStatement(sql);
    pst.setString(1, produto.getFornecedor());
    pst.setString(2, produto.getNomeacaolote());
    pst.setString(3, produto.getLote());
    pst.setString(4, produto.getDataValidade());
    pst.setString(5, produto.getCodigoBarras());
    pst.setString(6, produto.getDatafabricacao());

    pst.executeUpdate();
} catch (Exception e) {
    System.err.println(e);
    JOptionPane.showMessageDialog(null, "Produto não cadastrado. Verifique os dados e tente novamente.");
    return false;
}
return true;
    }

    public List<modelproduto> Carregarlotes() {
        conexao = Classeconexao.conector();
        List<modelproduto> listalotes = new ArrayList<>();
        modelproduto produto = new modelproduto();

        String sql = "SELECT\n" +
"    l.id AS id_lote,\n" +
"    p.descricao_produto,\n" +
"    p.id AS id_produto,\n" +
"    l.nomeacao_lote,\n" +
"    l.lote,\n" +
"    l.validade,\n" +
"    p.quantidade\n" +
"FROM\n" +
"    Produto p\n" +
"RIGHT JOIN\n" +
"    Lotes l ON p.lote_id = l.id;";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new modelproduto();
                produto.setId(rs.getInt(1));
                produto.setDescricaoProduto(rs.getString(2));
                produto.setId(rs.getInt(3));
                produto.setNomeacaolote(rs.getString(4));
                produto.setLote(rs.getString(5));
                produto.setDataValidade(rs.getString(6));
                produto.setQuantidade(rs.getInt(7));
                listalotes.add(produto);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listalotes;
    }

    public modelproduto Exibircamposproduto(int codigo) {
        conexao = Classeconexao.conector();
        modelproduto produto = new modelproduto();
    
    String sql = "SELECT * FROM produto WHERE id = ?";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, codigo);
        rs = pst.executeQuery();
        while (rs.next()) {
            produto = new modelproduto();
            produto.setDescricaoProduto(rs.getString(1));
        }
    } catch (Exception e) {
        System.err.println(e);
    } 
    
    return produto;
    }
    

    }
    
    
    


