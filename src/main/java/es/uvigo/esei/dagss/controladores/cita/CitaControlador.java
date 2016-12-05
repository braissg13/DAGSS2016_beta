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
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
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
    
    private List<Cita> citas;
    private Long idActual;
    
    @Inject
    private MedicoControlador medicoControlador;
     
    @EJB
    private CitaDAO citaDAO;
    
    public List<Cita> showCitasHOY(){
     String DATE_FORMAT = "yyyyMMdd";
     SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
     Date hoy = Calendar.getInstance().getTime();
        long id = medicoControlador.getMedicoActual().getId();
        return citaDAO.buscarCitasHoy(id,hoy);
    }
    
     public String doVerCita(Long id) {
        idActual = id;
        
        return "/medicos/cita_actual";
    }
}
