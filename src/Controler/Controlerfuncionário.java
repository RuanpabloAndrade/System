/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Salvarfuncionariodao;
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
    
}
