/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import model.Modelfornecedor;
import Dao.Daofornecedor;
/**
 *
 * @author ruan
 */
public class Controlerfornecedor {
Daofornecedor dao = new Daofornecedor();
    public boolean Salvarfornecedor(Modelfornecedor fornecedor) {
         return this.dao.salvarfornecedor(fornecedor);
    }
    
}
