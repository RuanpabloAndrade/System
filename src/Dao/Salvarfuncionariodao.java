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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Modelfuncionariocadastro;

/**
 *
 * @author ruan
 */
public class Salvarfuncionariodao extends Classeconexao{
 Connection conexao = null;
 PreparedStatement pst = null;
 ResultSet rs = null;     
    
 
    public boolean SalvarFuncionarioDao(Modelfuncionariocadastro modelfuncionario) {
       conexao=Classeconexao.conector();
       String sql="insert into Funcionarios (Nome,telefone,datas,cpf,rg,cidade,numero,bairro,complemento,cep,cargo) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,modelfuncionario.getNome());
            pst.setString(2,modelfuncionario.getTelefone());
            pst.setString(3,modelfuncionario.getData());
            pst.setString(4,modelfuncionario.getCpf());
            pst.setString(5,modelfuncionario.getRg());
            pst.setString(6,modelfuncionario.getCidade());
            pst.setInt(7,modelfuncionario.getNumero());
            pst.setString(8,modelfuncionario.getBairro());
            pst.setString(9,modelfuncionario.getComplemeto());
            pst.setString(10,modelfuncionario.getCep());
            pst.setString(11,modelfuncionario.getCargo());
            pst.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Usuario Não cadastrado, Veja se Já consta na tabela abaixo");
        }
        return true;
    }
    
}
