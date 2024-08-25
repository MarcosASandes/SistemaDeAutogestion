
package classes;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ConsultaMedica implements Comparable<ConsultaMedica> {
    
    
    /*Region Atributos.*/
    private int NumeroReserva;
    private String Estado;
    private Medico DoctorConsulta;
    private Paciente PacienteConsulta;
    private Date FechaConsulta;
    private String Detalle;
    
    
    /*Region Constructores.*/
    public ConsultaMedica(){
        
    }
    
    public ConsultaMedica(int numero, String estado, Medico medico, Paciente paciente, Date fecha){
        this.NumeroReserva = numero;
        this.Estado = estado;
        this.DoctorConsulta = medico;
        this.PacienteConsulta = paciente;
        this.FechaConsulta = fecha;
        this.Detalle = null;
    }
    
    public ConsultaMedica(Medico m, Paciente p){
        this.DoctorConsulta = m;
        this.PacienteConsulta = p;
    }
    
    public ConsultaMedica(Medico m, Paciente p, Date f){
        this.DoctorConsulta = m;
        this.PacienteConsulta = p;
        this.FechaConsulta = f;
    }
    
    public ConsultaMedica(Medico m, Date fecha){
        this.DoctorConsulta = m;
        this.FechaConsulta = fecha;
    }
    
    
    /*Region Geters y Seters.*/
    public int getNumeroReserva() {
        return NumeroReserva;
    }


    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Medico getDoctorConsulta() {
        return DoctorConsulta;
    }

    public void setDoctorConsulta(Medico DoctorConsulta) {
        this.DoctorConsulta = DoctorConsulta;
    }

    public Paciente getPacienteConsulta() {
        return PacienteConsulta;
    }

    public void setPacienteConsulta(Paciente PacienteConsulta) {
        this.PacienteConsulta = PacienteConsulta;
    }

    public Date getFechaConsulta() {
        return FechaConsulta;
    }

    public void setFechaConsulta(Date FechaConsulta) {
        this.FechaConsulta = FechaConsulta;
    }
    
    public String getDetalle(){
        if(this.Detalle != null){
            return this.Detalle;
        }else{
            return "No encontrado.";
        }
    }
    
    public void setDetalle(String detalle){
        this.Detalle = detalle;
    }
    
    
    /*Region Equals y CompareTo.*/
    public boolean equals(Object o){
        ConsultaMedica unaConsulta;
        boolean ret = false;
        if(o instanceof ConsultaMedica){
            unaConsulta = (ConsultaMedica)o;
        }
        else{
            return false;
        }
        
        if(this.getNumeroReserva()== unaConsulta.getNumeroReserva()){
            ret = true;
        }else if(this.DoctorConsulta == unaConsulta.DoctorConsulta && this.PacienteConsulta == unaConsulta.PacienteConsulta){
            ret = true;
        }
        
        return ret;
    }


    @Override
    public int compareTo(ConsultaMedica o) {
        return this.getFechaConsulta().compareTo(o.getFechaConsulta());
    }
     
       
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = dateFormat.format(this.FechaConsulta);
        return "Fecha: " + fechaFormateada + " | " + "Numero: "  + this.NumeroReserva + " | " + "Estado: " + this.Estado + " | " + "\n" + "Doctor: " + this.DoctorConsulta + " | " +  "\n"  + "Paciente: " + this.PacienteConsulta + " | " + "Detalle: " + this.getDetalle() + "\n" + "-*-*-*-*-*";
    }
    
}
