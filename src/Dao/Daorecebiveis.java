/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexao.Classeconexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Modelrecebiveis;

/**
 *
 * @author ruan
 */
public class Daorecebiveis extends Classeconexao{
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null; 

    public List<Modelrecebiveis> GetListausuariodao() {
        conexao = Classeconexao.conector();
        List<Modelrecebiveis> listausuario = new ArrayList<>();
        Modelrecebiveis model = new Modelrecebiveis();
        
       String sql = "SELECT cod, "
            + "cliente, "
            + "telefone, "
            + "valor, "
            + "vencimento "
            + "FROM Contasreceber";
       
        try {
             pst=conexao.prepareStatement(sql);
             rs=pst.executeQuery();
             while(rs.next()){
                 model = new Modelrecebiveis();
                 model.setCod(rs.getInt(1));
                 //model.setChavecliente(rs.getString(2)+"rs");
                 model.setTelefone(rs.getString(3));
                 //model.setValor(rs.getString(4));
                 model.setVencimento(rs.getString(5));
                 listausuario.add(model);
             }
             
             
        } catch (Exception e) {
            System.err.println(e);
        }
        
        
        return listausuario;
    }

    public boolean salvarjurosdao(Modelrecebiveis recebuveus) {
        conexao = Classeconexao.conector();
        String sql = "INSERT INTO juros (id, jurosmensal, diasmensal)\n" +
"VALUES (?, ?, ?)\n" +
"ON DUPLICATE KEY UPDATE\n" +
"jurosmensal = VALUES(jurosmensal),\n" +
"diasmensal = VALUES(diasmensal);";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setDouble(2, recebuveus.getJuros());
            pst.setInt(3, recebuveus.getJurosdiasmensal());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
            return false;
        }
        return true;
    }

    public List<Modelrecebiveis> GetListajuros() {
        conexao = Classeconexao.conector();
        List<Modelrecebiveis> listajuros = new ArrayList<>();
        Modelrecebiveis model = new Modelrecebiveis();
        
       String sql = "SELECT \n" +
"    jurosmensal,\n" +
"    diasmensal,\n" +
"    (jurosmensal / diasmensal) AS jurosdiario\n" +
"FROM \n" +
"    juros;";
       
        try {
             pst=conexao.prepareStatement(sql);
             rs=pst.executeQuery();
             while(rs.next()){
                 model = new Modelrecebiveis();
                 model.setJuros(rs.getDouble(1));
                 model.setJurosdiasmensal(rs.getInt(2));
                 model.setDiario(rs.getDouble(3));
                 listajuros.add(model);
             }
             
             
        } catch (Exception e) {
            System.err.println(e);
        }
        return listajuros;
    }

    public boolean salvarcontadao(Modelrecebiveis recebiveis) {
       conexao = Classeconexao.conector();
String sql = "INSERT INTO Contasreceber (chavecliente, rua, cpf, telefone, origem, descricao_venda, valor, data_emissao, vencimento, numero_parcelas, chavejuros) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";
try {
    pst = conexao.prepareStatement(sql);

    int totalParcelas = recebiveis.getParcelas();
    double valorTotal = recebiveis.getValor();
    double valorParcela = valorTotal / totalParcelas;

    for (int i = 1; i <= totalParcelas; i++) {
        pst.setInt(1, recebiveis.getChavecliente());
        pst.setString(2, recebiveis.getEndereco());
        pst.setString(3, recebiveis.getCpf());
        pst.setString(4, recebiveis.getTelefone());
        pst.setString(5, recebiveis.getOrigem());

        // Descrição personalizada com número da parcela
        String descricao = recebiveis.getDescricao() + " - Parcela " + i + "/" + totalParcelas;
        pst.setString(6, descricao);

        pst.setDouble(7, valorParcela);
        pst.setString(8, recebiveis.getEmissaao());

        // Adiciona meses conforme número da parcela (ex: 1ª parcela = vencimento base, 2ª = +1 mês, etc.)
        LocalDate dataBase = LocalDate.parse(recebiveis.getVencimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate vencParcela = dataBase.plusMonths(i - 1);
        String vencimentoFormatado = vencParcela.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        pst.setString(9, vencimentoFormatado);

        pst.setInt(10, totalParcelas);
        pst.setInt(11, 1); // chavejuros

        pst.addBatch(); // adiciona ao lote
    }

    pst.executeBatch(); // executa todas as parcelas
} catch (Exception e) {
    System.err.println(e);
    JOptionPane.showMessageDialog(null, "Usuário Não cadastrado! Veja se já consta na tabela do banco no botão visualizar!");
    return false;
}
return true;
    }
    
}
