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
    
}
