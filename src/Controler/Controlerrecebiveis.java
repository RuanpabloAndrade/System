/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import java.util.List;
import model.Modelrecebiveis;
import Dao.Daorecebiveis;
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
    
}
