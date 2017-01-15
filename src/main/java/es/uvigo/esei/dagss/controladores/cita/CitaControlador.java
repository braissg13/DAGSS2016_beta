/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.cita;

import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import javax.ejb.EJB;
import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.controladores.medico.MedicoControlador;
import es.uvigo.esei.dagss.controladores.prescripcion.PrescripcionControlador;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.TipoUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
/*
 Proyecto Java EE, DAGSS-2013
 */

/**
 *
 * @author xsgonzalez
 */
@Named(value = "citaControlador")
@SessionScoped
public class CitaControlador implements Serializable{
    
    @Inject
    private MedicoControlador medicoControlador;
    
    @Inject
    private PrescripcionControlador prescripcionControlador;
     
    @EJB
    private CitaDAO citaDAO;
    
    private List<Prescripcion> presc;
    private List<Cita> citas;
    private Long idActual;
    private Cita citaActual;
    
    public List<Cita> showCitasHOY(){
     String DATE_FORMAT = "yyyyMMdd";
     SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
     Date hoy = Calendar.getInstance().getTime();
        long id = medicoControlador.getMedicoActual().getId();
        return citaDAO.buscarCitasHoy(id,hoy);
    }
    
     public String doVerCita(Cita cita) {
        citaActual = cita;
        
        return "medicos/cita_actual";
    }
     
     public String doVerPrescripcion() {
        presc = prescripcionControlador.prescripcionesPaciente();
         
        return "confecciona_tratamiento";
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Cita getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(Cita citaActual) {
        this.citaActual = citaActual;
    }
    
    /*
       Usado en la lista desplegable con los estados de una cita
    */
    public EstadoCita[]  getEstadosCitas() {
        return EstadoCita.values();
    }
    
    public void modificarEstado() {
        citaActual = citaDAO.actualizar(citaActual);
    }
    
 
}
