/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Produtodao;
import model.modelproduto;
/**
 *
 * @author ruan
 */
public class Controlerproduto {
     Produtodao dao = new Produtodao();

    public boolean Salvarproduto(modelproduto produto) {
        return this.dao.SalvarFuncionarioDao(produto);
    }
}
