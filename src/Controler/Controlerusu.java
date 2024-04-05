/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Daousu;
import model.modelusu;

/**
 *
 * @author ruan
 */
public class Controlerusu {
    Daousu dao = new Daousu();
    public boolean salvarusuariocontroler(modelusu modelusuario) {
      return this.dao.salvarusuario(modelusuario);
    }
    
}
