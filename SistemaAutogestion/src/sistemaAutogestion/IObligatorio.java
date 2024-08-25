package sistemaAutogestion;

import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    /*
    Pre:- El valor que se obtiene por parámetro debe ser un int.
    Pos:- Se crea el sistema de autogestión.
    */
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
     
    /*
    Pre:- Los valores que se obtienen por parámetro deben ser int, exceptuando el nombre.
    Pos:- Se registra un médico y se agrega a una lista de médicos.
    */
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
    
    /*
    Pre:- El valor que se obtiene por parámetro debe ser un int.
    Pos:- Se elimina el médico (si existe y cumple las condiciones) del sistema.
    */
    public  Retorno eliminarMedico(int codMedico); 
    
    /*
    Pre:- El tipo de los valores obtenidos por parámetro deben concordar con el tipo que se pide.
    Pos:- Se agrega un nuevo paciente a una lista de pacientes, se agrega al final para al mostrarse facilitar que se haga por orden de registro.
    */
    public Retorno agregarPaciente(String nombre, int CI, String direccion); 
    
    /*
    Pre:- El valor que se obtiene por parámetro debe ser un int y también ser una CI con formato correcto.
    Pos:- Se da de baja el paciente en el sistema, siempre y cuando se cumplan las condiciones.
    */
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- Registra un día en el que un médico podrá agendar consultas con pacientes.
    */
    public Retorno registrarDiaDeConsulta(int codMedico,Date fecha);

    
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- Se crea una consulta médica para ese paciente, con ese médico y en la fecha designada. Tener en cuenta que
    si no hay lugares disponibles, esta consulta pasará a una lista de espera.
    */
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);
    
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- Se elimina esa consulta y el lugar queda libre. Ese lugar pasará a ser ocupado por la primera consulta de la cola de espera.
    */
    public Retorno cancelarReserva(int codMedico, int ciPaciente); 
    
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- El estado de la consulta pasa a ser "En espera".
    */
    public Retorno anunciaLlegada(int codMedico, int CIPaciente); 
    
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- El estado de la consulta pasa a ser "Terminada" y es agregada al historial clinico del paciente.
    */
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);   
    
    /*
    Pre:- Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- El estado de la consulta pasa a ser "Cerrada".
    */
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta); 
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Retorna la lista de médicos ordenada alfabéticamente.
    */
    public Retorno listarMédicos(); 
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Retorna la lista de pacientes ordenada por registro del mismo.
    */
    public Retorno listarPacientes();     
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Retorna todas las consultas de ese médico agrupadas por dia.
    */
    public Retorno listarConsultas(int codMedico); 
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Muestra los pacientes que tienen una consulta con ese médico en esa fecha y el estado de la consulta es "En espera"
    Se muestran en orden por número de reserva.
    */
    public Retorno listarPacientesEnEspera (String codMedico, Date fecha);    
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Retorna las consultas próximas a realizarse de ese paciente.
    */
    public Retorno consultasPendientesPaciente(int CIPaciente);   
    
    /*
    Pre:- El sistema debe estar creado.
    Pos:- Muestra el historial clínico completo del paciente.
    */
    public Retorno historiaClínicaPaciente (int ci);    
    
    /*
    Pre:- El sistema debe estar creado.
        - Los valores obtenidos por parámetro deben concordar en tipo con los que se espera obtener.
    Pos:- Muestra la cantidad de pacientes que fueron atendidos (consultas cerradas) por cada especialidad.
    */
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año); 
     
}
