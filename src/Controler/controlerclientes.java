/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import model.modelclientes;
import Dao.daoclientes;
import java.util.List;
/**
 *
 * @author ruan
 */
public class controlerclientes {
    daoclientes dao = new daoclientes();
    
    
    public boolean Salvarcliente(modelclientes clientes) {
         return this.dao.salvarclientes(clientes);
    }

    public List<modelclientes> Listarclientecontroler() {
        return this.dao.Carregarclientetabela();
    }

    public modelclientes carregarDadosclientes(String cpfvariavel) {
        return dao.carregarusuarios(cpfvariavel);  
    }

    public boolean Editarclientecontroler(modelclientes clientes) {
        return this.dao.EditarClienteDao(clientes);
    }
    
}
