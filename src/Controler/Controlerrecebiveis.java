/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import java.util.List;
import model.Modelrecebiveis;
import Dao.Daorecebiveis;
import model.Modeldashboard;
/**
 *
 * @author ruan
 */
public class Controlerrecebiveis {
   Daorecebiveis dao = new Daorecebiveis();
    public List<Modelrecebiveis> Listarusucontroler() {
        return this.dao.GetListausuariodao();
    }

    public boolean Salvarjuros(Modelrecebiveis recebuveus) {
       return this.dao.salvarjurosdao(recebuveus);
    }

    public List<Modelrecebiveis> ListarJuros() {
        return this.dao.GetListajuros();
    }

    public boolean Salvarconta(Modelrecebiveis recebiveis) {
        return this.dao.salvarcontadao(recebiveis);
    }

    public List<Modelrecebiveis> Listarrecebivel() {
        return this.dao.listarecebiveis();
    }

    public List<Modelrecebiveis> cadastrorecebivel(int codigoCliente) {
         return this.dao.listarecebivelcadastro(codigoCliente);
    }

    public boolean excluirConta(int codigo, int idconta) {
         return this.dao.Quitarconta(codigo, idconta);
    }

    public Modelrecebiveis ExibirContas(int codigo) {
        return this.dao.ExibirContas(codigo);
    }

    public List<Modelrecebiveis> Exibirselecao(int id_codigo) {
         return this.dao.selecaoconta(id_codigo);
    }

    public boolean Editarrecebiveis(Modelrecebiveis recebiveis) {
        return this.dao.Editarrecebiveis(recebiveis); //To change body of generated methods, choose Tools | Templates.
    }

    public Modelrecebiveis carregarDadosconta(int codigo) {
          return dao.carregarconta(codigo);
    }

    public Modeldashboard carregarDashboarconta() {
         return dao.carregardahsboard();
    }

    

    
    
}
