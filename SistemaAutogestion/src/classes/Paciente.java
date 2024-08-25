
package classes;

import tads.*;

public class Paciente implements Comparable<Paciente> {

    /*Region Atributos.*/
    private int CI;
    private String Nombre;
    private String Direccion;
    private Lista<ConsultaMedica> HistorialClinico;
    private Lista<ConsultaMedica> Agendas;
    
    /*Region Constructores.*/
    public Paciente(){
        this.HistorialClinico = new Lista<ConsultaMedica>();
        this.Agendas = new Lista<ConsultaMedica>();
    }
    
    
    public Paciente(int id){
        this.CI = id;
        this.HistorialClinico = new Lista<ConsultaMedica>();
        this.Agendas = new Lista<ConsultaMedica>();
    }
    
    public Paciente(int ci, String nombre, String direccion){
        this.CI = ci;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.HistorialClinico = new Lista<ConsultaMedica>();
        this.Agendas = new Lista<ConsultaMedica>();
    }
    
    /*Region Geters y Seters.*/
    public Lista<ConsultaMedica> getHistorial(){
        return HistorialClinico;
    }
    
    public Lista<ConsultaMedica> getAgendas(){
        return Agendas;
    }
    
    
    
    public int getCI() {
        return CI;
    }

   
    public void setCI(int CI) {
        this.CI = CI;
    }

    
    public String getNombre() {
        return Nombre;
    }

    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    
    public String getDireccion() {
        return Direccion;
    }

    
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /*Region Equals y CompareTo.*/
    public boolean equals(Object o){
        Paciente unPac;
        boolean ret = false;
        if(o instanceof Paciente){
            unPac = (Paciente)o;
        }
        else{
            return false;
        }
        
        if(this.getCI() == unPac.getCI()){
            ret = true;
        }
        
        return ret;
    }

    @Override
    public int compareTo(Paciente p) {
        return this.getNombre().compareTo(p.getNombre());
    }
    
    @Override
    public String toString() {
        return "CI: " + this.CI + ", Nombre: " + this.Nombre +
               ", Direccion: " + this.Direccion;
    }
}
