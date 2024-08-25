
package classes;



public class ConsultaNoConfirmada implements Comparable<ConsultaNoConfirmada>{
    private Medico medicoConsulta;
    private Paciente pacienteConsulta;
    
    public ConsultaNoConfirmada(){
        
    }
    
    public ConsultaNoConfirmada(Medico unMedico, Paciente unPaciente){
        this.medicoConsulta = unMedico;
        this.pacienteConsulta = unPaciente;
    }
    
    
    public Medico getMedico(){
        return medicoConsulta;
    }
    
    public Paciente getPaciente(){
        return pacienteConsulta;
    }
    
    public void setMedico(Medico unM){
        this.medicoConsulta = unM;
    }
    
    public void setPaciente(Paciente unP){
        this.pacienteConsulta = unP;
    }

    @Override
    public int compareTo(ConsultaNoConfirmada o) {
        return this.getMedico().compareTo(o.medicoConsulta);
    }
    
    public String toString() {
        return "Paciente: " + this.getPaciente().getNombre() + "Medico: " + this.getMedico().getNombre();
    }
    
}
