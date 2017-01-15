package es.uvigo.esei.dagss.controladores.prescripcion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import es.uvigo.esei.dagss.controladores.cita.CitaControlador;
import es.uvigo.esei.dagss.controladores.medico.MedicoControlador;
import es.uvigo.esei.dagss.controladores.paciente.PacienteControlador;
import es.uvigo.esei.dagss.dominio.daos.MedicamentoDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.EstadoReceta;
import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.faces.view.ViewScoped;

@Named(value = "prescripcionControlador")
@SessionScoped
public class PrescripcionControlador implements Serializable {

    private List<Prescripcion> prescripciones;
    private Prescripcion prescripcionActual;
    private String dni;
   
    @Inject
    private MedicoControlador medicoControlador;
    
    @Inject
    private PacienteControlador pacienteControlador;

    @Inject
    private MedicamentoDAO medicamentoDAO;

    @Inject
    private CitaControlador citaControlador;

    @EJB
    private PrescripcionDAO prescripcionDAO;
    
    @EJB
    private RecetaDAO recetaDAO;

    /**
     * Creates a new instance of PrescripcionControlador
     */
    public PrescripcionControlador() {
    }

    public List<Prescripcion> prescripcionesPaciente() {
        return prescripciones = prescripcionDAO.buscarPorPaciente(citaControlador.getCitaActual().getPaciente().getId(), medicoControlador.getMedicoActual().getId());
    }

    public List<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }

    public Prescripcion getPrescripcionActual() {
        return prescripcionActual;
    }

    public void setPrescripcionActual(Prescripcion prescripcionActual) {
        this.prescripcionActual = prescripcionActual;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String doBuscarTratamientos() {
        if(dni.length() == 9){
            prescripciones = prescripcionDAO.buscarTratamientosPorDni(dni);
            
            return "listado_tratamientos";
        }else{
            return null;
        }
    }
    
    public void doEliminar() {
        prescripcionDAO.eliminar(prescripcionActual);
        prescripciones = prescripcionDAO.buscarTratamientosPorDni(dni);
    }

    public void doNuevo() {
        prescripcionActual = new Prescripcion(); // Prescripción vacía
        prescripcionActual.setMedico(medicoControlador.getMedicoActual());
        prescripcionActual.setPaciente(citaControlador.getCitaActual().getPaciente());
    }

    public void doEditar(Prescripcion prescripcion) {
        prescripcionActual = prescripcion;   // Otra alternativa: volver a refrescarlos desde el DAO
    }

   
    public void doGuardarNuevo() {
        if (prescripcionActual.getFechaInicio().compareTo(prescripcionActual.getFechaFin()) <= 0 ){
            prescripcionActual = prescripcionDAO.crear(prescripcionActual);
            doRecetaNueva(prescripcionActual);
            prescripciones = prescripcionDAO.buscarPorPaciente(citaControlador.getCitaActual().getPaciente().getId(), medicoControlador.getMedicoActual().getId());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha final debe superar a fecha inicio", ""));
        }
    }

    public void doGuardarEditado() {
        if (prescripcionActual.getFechaInicio().compareTo(prescripcionActual.getFechaFin()) <= 0 ){
            prescripcionActual = prescripcionDAO.actualizar(prescripcionActual);
            doRecetaNueva(prescripcionActual);
            prescripciones = prescripcionDAO.buscarTratamientosPorDni(dni);
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha final debe superar a fecha inicio", ""));
        }
    }
    
    public void doRecetaNueva(Prescripcion p){
        int dias = (int) TimeUnit.DAYS.convert((p.getFechaFin().getTime() -p.getFechaInicio().getTime()), TimeUnit.MILLISECONDS);
        int dosisDia = p.getDosis();
        int dosisMedicamento = p.getMedicamento().getNumeroDosis();
        
        int dosisTotal = dias * dosisDia;
        int numeroDeRecetas = (int) Math.ceil(((double) dosisTotal)/dosisMedicamento);
        for(int i = 0; i< numeroDeRecetas; i++){
            Receta r = new Receta(p,p.getMedicamento().getNumeroDosis(),p.getFechaInicio(),p.getFechaFin(),EstadoReceta.GENERADA);
            recetaDAO.crear(r);

        }
    }
    
    public String verTratamientos(){
        
        
        return "listado_prescripciones";
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentoDAO.buscarTodos();
    }


}