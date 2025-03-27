/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import model.modelclientes;
import Dao.daoclientes;
import java.util.List;
import model.modelhistóricoclientes;
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

    public modelclientes carregarDadosclientes(String nomevariavel) {
        return dao.carregarusuarios(nomevariavel);  
    }

    public boolean Editarclientecontroler(modelclientes clientes) {
        return this.dao.EditarClienteDao(clientes);
    }

    public List<modelclientes> ListarclientesClientes() {
       return this.dao.Carregarclientetabelavizualizar();
    }

    public boolean excluircliente(int codigo) {
        return this.dao.Excluircliente(codigo); 
    }

    public modelclientes ExibirFornecedor(int codigo) {
       return this.dao.ExibirCadastro(codigo); 
    }

    public List<modelhistóricoclientes> Exibirhistoricocliente(int codigo) {
         return this.dao.lisarhistoricocliente(codigo);
    }
    
}
