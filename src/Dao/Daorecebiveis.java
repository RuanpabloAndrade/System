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
import model.modelclientes;

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
        
       String sql = "SELECT\n" +
"    t.nome AS nome_cliente,                      -- Nome do Cliente\n" +
"    t.telefone,                                  -- Telefone do Cliente (verifique o nome exato da coluna)\n" +
"    CAST(c.valor AS DECIMAL(10,2)) AS valor,     -- Valor da conta\n" +
"    DATE_FORMAT(c.vencimento, '%d/%m/%Y') AS vencimento_formatado -- Data de vencimento formatada\n" +
"FROM\n" +
"    Contasreceber c\n" +
"JOIN\n" +
"    tabelaclientes t ON c.chavecliente = t.id    -- Junta com a tabela de clientes para obter nome e telefone\n" +
"ORDER BY\n" +
"    c.vencimento ASC ";
       
        try {
             pst=conexao.prepareStatement(sql);
             rs=pst.executeQuery();
             while(rs.next()){
                 model = new Modelrecebiveis();
                 model.setNomecliente(rs.getString(1));
                 model.setTelefone(rs.getString(2));
                 model.setValor(rs.getDouble(3));
                 model.setVencimento(rs.getString(4));
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

    public List<Modelrecebiveis> listarecebiveis() {
       conexao = Classeconexao.conector();
        List<Modelrecebiveis> listarecebiveis = new ArrayList<>();
        Modelrecebiveis recebiveis = new Modelrecebiveis();

        String sql = "SELECT \n" +
"    c.id AS id_conta_receber,  -- Incluindo o id da tabela Contasreceber\n" +
"    t.nome AS nome_cliente,\n" +
"    c.descricao_venda,\n" +
"    CAST(c.valor AS DECIMAL(10,2)) AS valor,\n" +
"    DATE_FORMAT(c.vencimento, '%d/%m/%Y') AS vencimento,\n" +
"    ROUND(\n" +
"        CAST(c.valor AS DECIMAL(10,2)) * \n" +
"        (1 + ((IFNULL(j.jurosmensal, 0) / IFNULL(j.diasmensal, 30)) / 100 * \n" +
"        GREATEST(0, DATEDIFF(CURDATE(), c.vencimento)))),\n" +
"        2\n" +
"    ) AS valor_com_juros\n" +
"FROM \n" +
"    Contasreceber c\n" +
"JOIN \n" +
"    tabelaclientes t ON c.chavecliente = t.id\n" +
"LEFT JOIN \n" +
"    juros j ON c.chavejuros = j.id\n" +
"ORDER BY\n" +
"    c.vencimento ASC;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                recebiveis = new Modelrecebiveis();
                recebiveis.setCod(rs.getInt(1));
                recebiveis.setNomecliente(rs.getString(2));
                recebiveis.setDescricao(rs.getString(3));
                recebiveis.setValor(rs.getDouble(4));
                recebiveis.setVencimento(rs.getString(5));
                recebiveis.setJuros(rs.getDouble(6));
                listarecebiveis.add(recebiveis);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return listarecebiveis;
    }

    public List<Modelrecebiveis> listarecebivelcadastro(int codigoCliente) {
            conexao = Classeconexao.conector();
    List<Modelrecebiveis> listarecebivel = new ArrayList<>();
    Modelrecebiveis modelrecebiveis = new Modelrecebiveis();
    
    String sql = "SELECT \n" +
"    c.id AS codigo,\n" +
"    c.descricao_venda AS parcela_descricao,\n" +
"    DATE_FORMAT(c.vencimento, '%d/%m/%Y') AS vencimento,\n" +
"    CAST(c.valor AS DECIMAL(10,2)) AS valor_original\n" +
"FROM \n" +
"    Contasreceber c\n" +
"LEFT JOIN \n" +
"    juros j ON c.chavejuros = j.id\n" +
"WHERE \n" +
"    c.chavecliente = ?;";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, codigoCliente);
        rs = pst.executeQuery();
        
        while (rs.next()) {
            modelrecebiveis = new Modelrecebiveis();
            modelrecebiveis.setCod(rs.getInt(1));
            modelrecebiveis.setDescricao(rs.getString(2));
            modelrecebiveis.setVencimento(rs.getString(3));
            modelrecebiveis.setValor(rs.getDouble(4));
            listarecebivel.add(modelrecebiveis);
        }
    } catch (Exception e) {
        System.err.println(e);
    } 
    
    return listarecebivel;
    }

    public boolean Quitarconta(int codigo) {
        conexao = Classeconexao.conector();
        String sql = "DELETE FROM Contasreceber WHERE id = '" + codigo + "'";
        try {
            pst = conexao.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }
}
