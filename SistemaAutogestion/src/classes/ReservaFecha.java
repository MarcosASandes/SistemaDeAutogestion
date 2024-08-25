package classes;

import java.util.Date;
import tads.*;

public class ReservaFecha implements Comparable<ReservaFecha> {

    /*Region Atributos.*/
    private Date FechaConsultas;
    private int CodMedico;
    private Lista<ConsultaMedica> ConsultasEnLaFecha;
    private Cola<ConsultaNoConfirmada> ColaEspera;
    
    
    /*Region Constructores.*/
    public ReservaFecha(){
        this.ConsultasEnLaFecha = new Lista<ConsultaMedica>();
        this.ColaEspera = new Cola<ConsultaNoConfirmada>();
    }
    
    public ReservaFecha(Date fecha){
        this.FechaConsultas = fecha;
    }
    
    public ReservaFecha(Date fecha, int CodMedico){
        this.FechaConsultas = fecha;
        this.CodMedico = CodMedico;
        this.ConsultasEnLaFecha = new Lista<ConsultaMedica>();
        this.ColaEspera = new Cola<ConsultaNoConfirmada>();
    }
    
    public ReservaFecha(int codMed, Date fecha){
        this.CodMedico = codMed;
        this.FechaConsultas = fecha;
    }

    /*Region Geters y Seters.*/
    public Date getFechaConsultas() {
        return FechaConsultas;
    }

    public void setFechaConsultas(Date FechaConsultas) {
        this.FechaConsultas = FechaConsultas;
    }
    
    public int getCodMed(){
        return CodMedico;
    }
    

    public Lista<ConsultaMedica> getConsultasEnLaFecha() {
        return ConsultasEnLaFecha;
    }

   
    public Cola<ConsultaNoConfirmada> getListaEspera() {
        return ColaEspera;
    }

    /*Region Equals y CompareTo.*/
    public boolean equals(Object o){
        ReservaFecha unaFechaReserva;
        boolean ret = false;
        if(o instanceof ReservaFecha){
            unaFechaReserva = (ReservaFecha)o;
        }
        else{
            return false;
        }
        
        if(this.getFechaConsultas() == unaFechaReserva.getFechaConsultas()){
            ret = true;
        }
        
        return ret;
    }

   

    @Override
    public int compareTo(ReservaFecha r) {
        return this.getFechaConsultas().compareTo(r.getFechaConsultas());
    }

    
}
