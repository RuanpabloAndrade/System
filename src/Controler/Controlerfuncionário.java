/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Salvarfuncionariodao;
import java.util.List;
import model.Modelfuncionariocadastro;
/**
 *
 * @author ruan
 */
public class Controlerfuncionário {
   Salvarfuncionariodao dao = new Salvarfuncionariodao();

    public boolean Salvarfuncionariocontroler(Modelfuncionariocadastro modelfuncionario) {
        return this.dao.SalvarFuncionarioDao(modelfuncionario);
    }

    public List<Modelfuncionariocadastro> Listarusucontroler() {
         return this.dao.gettabelaFuncionario();
    }

    public List<Modelfuncionariocadastro> Listarusucontroler2() {
         return this.dao.gettabelaFuncionario2();
    }

    public boolean excluirfuncionario(int codigo) {
       return this.dao.Excluirfuncionario(codigo);
    }

   

    public Modelfuncionariocadastro carregarDadosPorId(String nome) {
         return dao.carregarFuncionarioPorId(nome); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    

    
}
