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
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Modeldashboard;
import model.Modelrecebiveis;
import model.modelclientes;
import model.Modeldashboard;

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

try {
    // Primeiro, buscar o maior id_conta atual no banco
    String sqlBuscaIdConta = "SELECT IFNULL(MAX(id_conta), 0) + 1 AS novo_id_conta FROM Contasreceber";
    PreparedStatement pstBuscaIdConta = conexao.prepareStatement(sqlBuscaIdConta);
    ResultSet rsBuscaId = pstBuscaIdConta.executeQuery();

    int novoIdConta = 1; // valor padrão caso não tenha nenhuma conta ainda
    if (rsBuscaId.next()) {
        novoIdConta = rsBuscaId.getInt("novo_id_conta");
    }

    // Agora, salva as parcelas usando o novo id_conta
    String sqlParcelas = "INSERT INTO Contasreceber (id_conta, chavecliente, rua, cpf, telefone, origem, descricao_venda, valor, data_emissao, vencimento, numero_parcelas, chavejuros) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";

    PreparedStatement pstParcelas = conexao.prepareStatement(sqlParcelas);

    int totalParcelas = recebiveis.getParcelas();
    double valorTotal = recebiveis.getValor();
    double valorParcela = valorTotal / totalParcelas;

    for (int i = 1; i <= totalParcelas; i++) {
        pstParcelas.setInt(1, novoIdConta); // Gerado automaticamente
        pstParcelas.setInt(2, recebiveis.getChavecliente());
        pstParcelas.setString(3, recebiveis.getEndereco());
        pstParcelas.setString(4, recebiveis.getCpf());
        pstParcelas.setString(5, recebiveis.getTelefone());
        pstParcelas.setString(6, recebiveis.getOrigem());

        String descricao = recebiveis.getDescricao() + " - Parcela " + i + "/" + totalParcelas;
        pstParcelas.setString(7, descricao);

        pstParcelas.setDouble(8, valorParcela);

        pstParcelas.setString(9, recebiveis.getEmissaao());

        LocalDate dataBase = LocalDate.parse(recebiveis.getVencimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate vencParcela = dataBase.plusMonths(i - 1);
        String vencimentoFormatado = vencParcela.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        pstParcelas.setString(10, vencimentoFormatado);

        pstParcelas.setInt(11, totalParcelas);
        pstParcelas.setInt(12, 1);

        pstParcelas.addBatch();
    }

    pstParcelas.executeBatch();

    return true;

} catch (SQLException e) {
    System.err.println("Erro ao salvar no banco: " + e.getMessage());
    e.printStackTrace();
    return false;
    }
    }
    public List<Modelrecebiveis> listarecebiveis() {
       conexao = Classeconexao.conector();
        List<Modelrecebiveis> listarecebiveis = new ArrayList<>();
        Modelrecebiveis recebiveis = new Modelrecebiveis();

        String sql =  "SELECT \n" +
"    c.id AS id_parcela,\n" +
"    c.id_conta,\n" +//coluna oculta
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
                recebiveis.setCod(rs.getInt(1));         // id_parcela
                recebiveis.setIdconta(rs.getInt(2));     // novo: id_conta coluna oculta 
                recebiveis.setNomecliente(rs.getString(3));
                recebiveis.setDescricao(rs.getString(4));
                recebiveis.setValor(rs.getDouble(5));
                recebiveis.setVencimento(rs.getString(6));
                recebiveis.setJuros(rs.getDouble(7));
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

   public boolean Quitarconta(int codigo, int idconta) {
    conexao = Classeconexao.conector();
    boolean sucesso = false;
    Statement stmt = null; // Defina o Statement aqui, para fechá-lo depois

    try {
        // Começa uma transação
        conexao.setAutoCommit(false);

        // Desabilita o SQL_SAFE_UPDATES
        stmt = conexao.createStatement();
        stmt.execute("SET SQL_SAFE_UPDATES = 0");

        // Atualizar o número de parcelas antes de apagar
        String sqlAtualizar = "UPDATE Contasreceber SET numero_parcelas = numero_parcelas - 1 WHERE id_conta = ?";
        PreparedStatement pstAtualizar = conexao.prepareStatement(sqlAtualizar);
        pstAtualizar.setInt(1, idconta);

        // Executa a atualização, independentemente do número de parcelas
        int linhasAtualizadas = pstAtualizar.executeUpdate();

        if (linhasAtualizadas > 0 || true) { // Permitir o update mesmo quando for a última parcela
            // Apaga a parcela paga
            String sqlDelete = "DELETE FROM Contasreceber WHERE id = ?";
            PreparedStatement pstDelete = conexao.prepareStatement(sqlDelete);
            pstDelete.setInt(1, codigo);
            pstDelete.execute();

            // Se tudo ocorreu bem, comita a transação
            conexao.commit();
            sucesso = true;
        } else {
            // Se não houve atualização nas parcelas, faz o rollback
            conexao.rollback();
        }

        // Reabilita o SQL_SAFE_UPDATES
        stmt.execute("SET SQL_SAFE_UPDATES = 1");

    } catch (Exception e) {
        System.err.println("Erro: " + e.getMessage());
        try {
            // Caso haja erro, faz o rollback da transação
            conexao.rollback();
        } catch (SQLException rollbackException) {
            System.err.println("Erro ao fazer rollback: " + rollbackException.getMessage());
        }
    } finally {
        // Fecha o Statement e restaura o autocommit após a transação
        try {
            if (stmt != null) stmt.close();
            conexao.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Erro ao restaurar autocommit ou fechar o statement: " + e.getMessage());
        }
    }
    return sucesso;
}

    public Modelrecebiveis ExibirContas(int codigo) {
           Modelrecebiveis modelrecebiveis = new Modelrecebiveis();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT\n" +
"    c.id AS id_conta_receber,\n" +
"    t.id AS id_cliente,\n" +
"    t.nome,\n" +
"    c.rua,\n" +
"    c.cpf,\n" +
"    c.telefone,\n" +
"    c.origem,\n" +
"    c.descricao_venda,\n" +
"    (\n" +
"        SELECT CAST(SUM(cr.valor) AS DECIMAL(10,2))\n" +
"        FROM Contasreceber cr\n" +
"        WHERE cr.id_conta = c.id_conta\n" +
"    ) AS valor_total,\n" +
"    DATE_FORMAT(STR_TO_DATE(c.data_emissao, '%d/%m/%Y'), '%d/%m/%Y') AS data_emissao,\n" +
"    DATE_FORMAT(c.vencimento, '%d/%m/%Y') AS vencimento,\n" +
"    c.numero_parcelas\n" +
"FROM\n" +
"    Contasreceber c\n" +
"JOIN\n" +
"    tabelaclientes t ON c.chavecliente = t.id\n" +
"WHERE\n" +
"    c.id = ?;";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                modelrecebiveis.setCod(rs.getInt("id_conta_receber"));
                modelrecebiveis.setNomecliente(rs.getString("nome"));
                modelrecebiveis.setChavecliente(rs.getInt("id_cliente"));
                modelrecebiveis.setEndereco(rs.getString("rua"));
                modelrecebiveis.setCpf(rs.getString("cpf"));
                modelrecebiveis.setTelefone(rs.getString("telefone"));
                modelrecebiveis.setOrigem(rs.getString("origem"));
                modelrecebiveis.setDescricao(rs.getString("descricao_venda"));
                modelrecebiveis.setValor(rs.getDouble("valor_total"));
                modelrecebiveis.setEmissaao(rs.getString("data_emissao"));    
                modelrecebiveis.setVencimento(rs.getString("vencimento"));
                modelrecebiveis.setParcelas(rs.getInt("numero_parcelas"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return modelrecebiveis;
    }

    public List<Modelrecebiveis> selecaoconta(int id_codigo) {
    conexao = Classeconexao.conector();
    List<Modelrecebiveis> selecaocontas = new ArrayList<>();

    String sql = "SELECT \n" +
        "    c.id AS codigo,\n" +
        "    c.descricao_venda AS parcela,\n" +
        "    DATE_FORMAT(c.vencimento, '%d/%m/%Y') AS vencimento,\n" +
        "    CAST(c.valor AS DECIMAL(10,2)) AS valor\n" +
        "FROM \n" +
        "    Contasreceber c\n" +
        "WHERE \n" +
        "    c.id_conta = ?\n" +
        "ORDER BY \n" +
        "    c.vencimento ASC;";
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, id_codigo);
        rs = pst.executeQuery();

        while (rs.next()) {
            Modelrecebiveis recebiveis = new Modelrecebiveis();
            recebiveis.setCod(rs.getInt("codigo"));
            recebiveis.setDescricao(rs.getString("parcela"));
            recebiveis.setVencimento(rs.getString("vencimento"));
            recebiveis.setValor(rs.getDouble("valor"));
            
            // Adiciona cada registro à lista
            selecaocontas.add(recebiveis);
        }
    } catch (Exception e) {
        System.err.println("Erro ao buscar parcelas: " + e.getMessage());
    }

    return selecaocontas;
}

    public boolean Editarrecebiveis(Modelrecebiveis recebiveis) {
       Connection conexao = Classeconexao.conector();
boolean sucesso = false;
Statement stmt = null;

try {
    conexao.setAutoCommit(false);
    stmt = conexao.createStatement();
    stmt.execute("SET SQL_SAFE_UPDATES = 0");

    // 1º UPDATE: Atualiza rua, CPF, origem e descrição para TODAS as parcelas com o mesmo id_conta
     String sqlAtualizarGeral = "UPDATE Contasreceber SET rua = ?, cpf = ?, origem = ?, telefone=? WHERE id_conta = ?";
     PreparedStatement pstGeral = conexao.prepareStatement(sqlAtualizarGeral);
     pstGeral.setString(1, recebiveis.getEndereco());
     pstGeral.setString(2, recebiveis.getCpf());
     pstGeral.setString(3, recebiveis.getOrigem());
     pstGeral.setString(4, recebiveis.getTelefone());
     pstGeral.setInt(5, recebiveis.getIdconta());
     pstGeral.executeUpdate();

    // 2º UPDATE: Atualiza vencimento, valor e número de parcelas apenas para a conta específica
    String sqlAtualizarParcela = "UPDATE Contasreceber SET vencimento = ?, valor = ?, descricao_venda= ? WHERE id = ?";
    PreparedStatement pstParcela = conexao.prepareStatement(sqlAtualizarParcela);
    pstParcela.setString(1, (recebiveis.getVencimento()));
    System.out.println("Data formatada recebida: " + recebiveis.getVencimento());// Ex: "2025-05-20"
    pstParcela.setDouble(2, recebiveis.getValor());
    pstParcela.setString(3, recebiveis.getDescricao());
    pstParcela.setInt(4, recebiveis.getCod());
    pstParcela.executeUpdate();
int linhas = pstParcela.executeUpdate();
System.out.println("Linhas atualizadas: " + linhas);
    
    
    conexao.commit();
    sucesso = true;

    stmt.execute("SET SQL_SAFE_UPDATES = 1");

} catch (Exception e) {
    System.err.println("Erro: " + e.getMessage());
     e.printStackTrace();
    try {
        conexao.rollback();
    } catch (SQLException rollbackException) {
        System.err.println("Erro ao fazer rollback: " + rollbackException.getMessage());
    }
} finally {
    try {
        if (stmt != null) stmt.close();
        conexao.setAutoCommit(true);
    } catch (SQLException e) {
        System.err.println("Erro ao restaurar autocommit ou fechar statement: " + e.getMessage());
    }
}

return sucesso;
    }

    public Modelrecebiveis carregarconta(int codigo) {
              Modelrecebiveis recebiveis = new Modelrecebiveis();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT \n" +
"    id,\n" +
"    id_conta, \n" +
"    valor,\n" +
"    descricao_venda,\n" +
"    DATE_FORMAT(vencimento, '%d/%m/%Y') AS vencimento\n" +
"FROM \n" +
"    Contasreceber \n" +
"WHERE \n" +
"    id = ?;";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                recebiveis.setCod(rs.getInt(1));
                recebiveis.setIdconta(rs.getInt(2));
                recebiveis.setValor(rs.getDouble(3));
                recebiveis.setDescricao(rs.getString(4));
                recebiveis.setVencimento(rs.getString(5));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return recebiveis;
    }

    public Modeldashboard carregardahsboard() {
           Modeldashboard dashboardcontas = new Modeldashboard();
        try {
            conexao = Classeconexao.conector();
            String sql = "SELECT\n" +
"    SUM(valor) AS total_geral,\n" +
"    \n" +
"    SUM(CASE \n" +
"            WHEN STR_TO_DATE(vencimento, '%Y-%m-%d') = CURDATE()\n" +
"            THEN valor\n" +
"            ELSE 0\n" +
"        END) AS total_hoje,\n" +
"        \n" +
"    SUM(CASE \n" +
"            WHEN MONTH(STR_TO_DATE(vencimento, '%Y-%m-%d')) = MONTH(CURDATE())\n" +
"             AND YEAR(STR_TO_DATE(vencimento, '%Y-%m-%d')) = YEAR(CURDATE())\n" +
"            THEN valor\n" +
"            ELSE 0\n" +
"        END) AS total_mes,\n" +
"        \n" +
"    SUM(CASE \n" +
"            WHEN STR_TO_DATE(vencimento, '%Y-%m-%d') < CURDATE()\n" +
"            THEN valor\n" +
"            ELSE 0\n" +
"        END) AS total_vencidas\n" +
"FROM ContasReceber;";
            
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                dashboardcontas.setTotalcontas(rs.getDouble("total_geral"));
dashboardcontas.setTotaldodia(rs.getDouble("total_hoje"));
dashboardcontas.setTotaldomes(rs.getDouble("total_mes"));
dashboardcontas.setTotalvencidas(rs.getDouble("total_vencidas"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionário: " + e.getMessage());
        }
        return dashboardcontas;
    }
}
