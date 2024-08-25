
package classes;

import tads.*;

public class Medico implements Comparable<Medico> {

    /*Region Atributos.*/
    private String Nombre;
    private int CodMed;
    private int Tel;
    private int Especialidad;
    private Lista<ReservaFecha> _consultas;
    
    /*Region Constructores.*/
    public Medico(){
        
    }
    
    public Medico(int id){
        this.CodMed = id;
    }
    
    public Medico(int codMed, String nombre, int tel, int especialidad){
        this.CodMed = codMed;
        this.Nombre = nombre;
        this.Tel = tel;
        this.Especialidad = especialidad;
        this._consultas = new Lista<ReservaFecha>();
    }
    
    /*Region Geters y Seters.*/
    public Lista<ReservaFecha> getConsultas() {
        return _consultas;
    }
    
    public String getNombre() {
        return Nombre;
    }

    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    
    public int getCodMed() {
        return CodMed;
    }

    
    public void setCodMed(int CodMed) {
        this.CodMed = CodMed;
    }

    
    public int getTel() {
        return Tel;
    }

    
    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    
    public int getEspecialidad() {
        return Especialidad;
    }

    
    public void setEspecialidad(int Especialidad) {
        this.Especialidad = Especialidad;
    }

    /*Region Equals y CompareTo.*/
    public boolean equals(Object o){
        Medico unMed;
        boolean ret = false;
        if(o instanceof Medico){
            unMed = (Medico)o;
        }
        else{
            return false;
        }
        
        if(this.getCodMed()== unMed.getCodMed()){
            ret = true;
        }
        
        return ret;
    }

    @Override
    public int compareTo(Medico m) {
        return this.getNombre().compareTo(m.getNombre());
    }
    
    @Override
    public String toString() {
        return "Nombre: " + this.Nombre + ", Codigo de Medico: " + this.CodMed +
               ", Telefono: " + this.Tel + ", Especialidad: " + this.Especialidad;
    }
    
}
