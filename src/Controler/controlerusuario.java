/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;
import Dao.Daousuario;
import java.util.List;
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

    public boolean Salvarusuarios(Modelusuario usuarios) {
         return this.dao.Salvarusuario(usuarios);
    }

    public List<Modelusuario> Listarusuarioscontroler() {
        return this.dao.Carregarusuariotabela();
    }

    public Modelusuario carregarDadosPorId(int codigo) {
       return dao.carregarusuarios(codigo); 
    }

    public List<Modelusuario> Listarusuarioscontrolervizualizar() {
        return this.dao.Carregarusuariotabelavizualizar();
    }

    public boolean excluirusuario(int codigo) {
        return this.dao.Excluirusuario(codigo);
    }

    public Modelusuario Exibircadastrousuario(int codigo) {
        return this.dao.ExibirCadastrousuario(codigo);
    }

    public boolean Editarusuariocontroler(Modelusuario usuarios) {
      return this.dao.EditarUsuario(usuarios);
    }
    
}
