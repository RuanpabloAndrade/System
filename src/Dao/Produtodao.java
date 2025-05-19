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
String sql = "INSERT INTO Produto (descricao_produto, codigo_barras, quantidade, unidade_medida, validade, fornecedor, lote, custo, venda, atacado, promocao, estoque_critico, lucro_venda, peso, categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
    pst.setDouble(13, produto.getLucroVenda());
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
    
    
    
    
}
