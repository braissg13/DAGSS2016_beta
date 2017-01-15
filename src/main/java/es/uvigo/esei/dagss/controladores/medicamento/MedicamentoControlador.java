/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.medicamento;


import es.uvigo.esei.dagss.controladores.cita.CitaControlador;
import es.uvigo.esei.dagss.dominio.daos.MedicamentoDAO;
import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author braissg13
 */
@Named(value = "medicamentoControlador")
@SessionScoped
public class MedicamentoControlador implements Serializable {
    @Inject
    private CitaControlador citaControlador;
     
    @EJB
    private MedicamentoDAO medicamentoDAO;
    
    private List<Medicamento> medicamentos;
    private String textoBusqueda;

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

     public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    
    public String doBuscarPorNombre() {
        medicamentos = medicamentoDAO.buscarPorNombre(textoBusqueda);
        return "confecciona_tratamiento";
    }


    
    
}
