/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Daousuario;
import model.Modelusuario;

/**
 *
 * @author ruan
 */
public class controlerusuario {
    Daousuario dao = new Daousuario();
    
    public boolean Telalogin(Modelusuario model) {
        return this.dao.Logar(model);
    }
    
}
