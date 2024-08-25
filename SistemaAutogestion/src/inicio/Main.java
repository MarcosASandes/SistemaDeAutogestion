package inicio;

import classes.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import sistemaAutogestion.*;

public class Main {

    public static void main(String[] args) {

        Prueba p = new Prueba(); //Inicializamos prueba
        Sistema s = new Sistema(); //Inicializamos el sistema
        p.inicializarResultadosPrueba(); //Ponemos los contadores en 0 nuevamente.
        
//        System.out.println("[P1]Creación de sistema.");
//        p1_CreacionSistema(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P2]Registro de medico.");
//        p2_RegistroMedico(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P3]Eliminacion de medico.");
//        p3_EliminarMedico(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P4]Registro de paciente.");
//        p4_RegistroPaciente(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P5]Eliminacion de paciente.");
//        p5_EliminarPaciente(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//        
//        
//        System.out.println("[P6]Listado de medicos.");
//        p6_ListadoMedico(s);
//        System.out.println("-|-|-|-|-|-|-");
//        
//        
//        System.out.println("[P7]Listado de pacientes.");
//        p7_ListadoPacientes(s);
//        System.out.println("-|-|-|-|-|-|-");
//        
//        
//        System.out.println("[P8]Registro dia de consulta.");
//        p8_RegistrarDiaConsulta(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P9]Registro de consulta.");
//        p9_RegistrarConsulta(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P10]Cancelar consulta.");
//        p10_CancelarReserva(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P11]Anunciar llegada.");
//        p11_AnunciarLlegada(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P12]Terminar consulta.");
//        p12_TerminarConsulta(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P13]Cerrar consulta.");
//        p13_CerrarConsulta(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P14]Listar consultas.");
//        p14_ListarConsultas(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P15]Listar pacientes en espera.");
//        p15_ListarPacientesEnEspera(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P16]Listar consultas pendientes.");
//        p16_ListarConsultasPendientesPaciente(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P17]Listar historial clinico.");
//        p17_ListarHistorialClinicoPaciente(p, s);
//        System.out.println("-|-|-|-|-|-|-");
//
//        
//        System.out.println("[P18]Mostrar matriz.");
//        p18_ReporteDePacientesMatriz(p, s);
//        System.out.println("-|-|-|-|-|-|-");

          //MostrarNoAsistidas(p, s);
          
          
          MostrarMatrizFecha(s);

        
        //ProbarCancelar(p, s); //Testeo interno.
        p.imprimirResultadosPrueba();
        //System.out.println("------------");
        //p6_ListadoMedico(s);
        //System.out.println("------------");
        //p7_ListadoPacientes(s);

        //Solo para comprobar que funcione.
//        p10_ListadoConsultasPaciente(s);
    }

    public static void p1_CreacionSistema(Prueba p, Sistema s) {
        p.ver(s.crearSistemaDeAutogestion(5).resultado, Retorno.Resultado.OK, "Se crea correctamente el sistema con capacidad 5.");
        p.ver(s.crearSistemaDeAutogestion(0).resultado, Retorno.Resultado.ERROR_1, "No se crea, debe ser mayor a cero y menor a quince.");
        p.ver(s.crearSistemaDeAutogestion(16).resultado, Retorno.Resultado.ERROR_1, "No se crea, debe ser mayor a cero y menor a quince.");
    }

    public static void p2_RegistroMedico(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);
        p.ver(s.registrarMedico("Eduardo", 10, 5987676, 6).resultado, Retorno.Resultado.OK, "Se crea el médico correctamente.");
        p.ver(s.registrarMedico("Felipe", 10, 6987878, 5).resultado, Retorno.Resultado.ERROR_1, "No se registra, ya existe ese CodMedico.");
        p.ver(s.registrarMedico("Gustavo", 15, 7487878, -1).resultado, Retorno.Resultado.ERROR_2, "La especialidad debe oscilar entre 1 a 20.");
        p.ver(s.registrarMedico("Amadeo", 16, 8965434, 21).resultado, Retorno.Resultado.ERROR_2, "La especialidad debe oscilar entre 1 a 20.");
    }

    public static void p3_EliminarMedico(Prueba p, Sistema s) {

        s.crearSistemaDeAutogestion(7);
        s.registrarMedico("Felipe", 1, 123456789, 5);
        s.registrarMedico("Julio", 2, 1123456, 4);
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Se elimina correctamente el médico.");
        p.ver(s.eliminarMedico(17).resultado, Retorno.Resultado.ERROR_1, "No existe un médico con ese código.");
    }

    public static void p4_RegistroPaciente(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);
        p.ver(s.agregarPaciente("Martin", 12345678, "Eduardo Acevedo 1289").resultado, Retorno.Resultado.OK, "Creación correcta del paciente.");
        p.ver(s.agregarPaciente("Lobezno", 12345678, "Acevedo Diaz 5674").resultado, Retorno.Resultado.ERROR_1, "Ya existe otro paciente con ese CI.");
    }

    public static void p5_EliminarPaciente(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);
        s.agregarPaciente("Lucia", 12345679, "Eduardo Acevedo 1289");
        p.ver(s.eliminarPaciente(12345679).resultado, Retorno.Resultado.OK, "Se elimina correctamente el paciente.");
        p.ver(s.eliminarPaciente(11223344).resultado, Retorno.Resultado.ERROR_1, "No hay ningún paciente con ese CI.");
    }

    public static void p6_ListadoMedico(Sistema s) {
        s.crearSistemaDeAutogestion(7);
        s.registrarMedico("Martin", 20, 193667897, 2);
        s.registrarMedico("Lucas", 21, 167667897, 3);
        s.registrarMedico("Alejandro", 22, 193642897, 4);
        s.registrarMedico("Xiomara", 23, 193662847, 5);
        s.registrarMedico("Pablo", 24, 193667047, 6);
        s.listarMédicos();
    }

    public static void p7_ListadoPacientes(Sistema s) {
        s.crearSistemaDeAutogestion(7);
        s.agregarPaciente("Pepe", 1877665545, "Flor de maronas");
        s.agregarPaciente("Jorge", 1377665534, "La comercial");
        s.agregarPaciente("Eusebio", 1127665514, "General Flores");
        s.agregarPaciente("Rafael", 1117665524, "Pocitos");
        s.agregarPaciente("Cristian", 1872165514, "La figurita");
        s.listarPacientes();
    }

    public static void p8_RegistrarDiaConsulta(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);
        //Error 1.
        p.ver(s.registrarDiaDeConsulta(988, new Date()).resultado, Retorno.Resultado.ERROR_1, "No se crea porque no existe un medico con ese codigo.");

        //OK.
        s.registrarMedico("Martin", 20, 193667897, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        
        p.ver(s.registrarDiaDeConsulta(20, date).resultado, Retorno.Resultado.OK, "Se crea correctamente.");
        s.MostrarReservaFecha(date, 20);

        s.registrarMedico("Emanuel", 101, 198637593, 1);
        
        p.ver(s.registrarDiaDeConsulta(101, date).resultado, Retorno.Resultado.OK, "Se crea correctamente.");
        s.MostrarReservaFecha(date, 101);

        //Error 2.
        p.ver(s.registrarDiaDeConsulta(20, date).resultado, Retorno.Resultado.ERROR_2, "No se crea porque ya existe una reserva con esa fecha y ese medico.");
    }

    public static void p9_RegistrarConsulta(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        s.registrarMedico("Martin", 20, 193667897, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(20, date);
        p.ver(s.reservaConsulta(20, 1, date).resultado, Retorno.Resultado.ERROR_1, "No se crea, no existe ese paciente.");

        //Error 2.
        s.agregarPaciente("Martin", 12345678, "Eduardo Acevedo 1289");
        p.ver(s.reservaConsulta(200, 12345678, date).resultado, Retorno.Resultado.ERROR_2, "No se crea, no existe ese medico.");

        //OK.
        p.ver(s.reservaConsulta(20, 12345678, date).resultado, Retorno.Resultado.OK, "Se crea correctamente.");

        //Error 3.
        p.ver(s.reservaConsulta(20, 12345678, date).resultado, Retorno.Resultado.OK, "Se crea correctamente.");
    }

    public static void p10_CancelarReserva(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //OK.
        s.registrarMedico("Martin", 20, 193667897, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(20, date);
        s.agregarPaciente("Martin", 12345678, "Eduardo Acevedo 1289");
        s.reservaConsulta(20, 12345678, date);
        p.ver(s.cancelarReserva(20, 12345678).resultado, Retorno.Resultado.OK, "Se eliminó correctamente.");

        //Error 1.
        p.ver(s.cancelarReserva(20, 989).resultado, Retorno.Resultado.ERROR_1, "No existe ese paciente.");

        //Error 2.
        s.agregarPaciente("Ernesto", 56789523, "Canelones 1212");
        p.ver(s.cancelarReserva(507, 56789523).resultado, Retorno.Resultado.ERROR_2, "No existe ese médico.");

        //Error 3.
        s.agregarPaciente("Julio", 77669933, "Eduardo Acevedo 1111");
        p.ver(s.cancelarReserva(20, 77669933).resultado, Retorno.Resultado.ERROR_3, "Ese paciente no tiene consulta con ese médico.");

        //Error 4.
        s.agregarPaciente("Manuel", 74637923, "Acevedo Diaz 4161");
        s.reservaConsulta(20, 74637923, date);
        ConsultaMedica unaConsulta = s.getConsultaParaTest(s.getMedicoPorCod(20), s.getPacientePorCod(74637923), date);
        unaConsulta.setEstado("No asistió");
        p.ver(s.cancelarReserva(20, 74637923).resultado, Retorno.Resultado.ERROR_4, "Esa reserva no está en estado pendiente.");
    }

    public static void p11_AnunciarLlegada(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //OK.       
        s.agregarPaciente("Manuel", 74637923, "Acevedo Diaz 4161");
        s.registrarMedico("Martin", 20, 193667897, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(20, date);
        s.reservaConsulta(20, 74637923, date);
        p.ver(s.anunciaLlegada(20, 74637923).resultado, Retorno.Resultado.OK, "Se anunció la llegada correctamente.");

        //Error 1.
        p.ver(s.anunciaLlegada(20, 81627325).resultado, Retorno.Resultado.ERROR_1, "No existe ese paciente.");

        //Error 2.
        s.agregarPaciente("Jose", 51423385, "Acevedo Diaz 6961");
        p.ver(s.anunciaLlegada(20, 51423385).resultado, Retorno.Resultado.ERROR_2, "Ese paciente no tiene reservada una consulta con ese medico.");
    }

    public static void p12_TerminarConsulta(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        s.registrarMedico("Giuliana", 87, 989564565, 16);
        Date fecha = new Date();
        s.registrarDiaDeConsulta(87, fecha);
        p.ver(s.terminarConsultaMedicoPaciente(9989, 87, "detalle consulta ejemplo1").resultado, Retorno.Resultado.ERROR_1, "No se termina la consulta, el paciente no existe.");

        //Error 2 por estado.
        s.agregarPaciente("Carla", 3453483, "Pocitos 2132");
        s.reservaConsulta(87, 3453483, fecha);
        p.ver(s.terminarConsultaMedicoPaciente(3453483, 87, "detalle consulta ejemplo2").resultado, Retorno.Resultado.ERROR_2, "No se termina la consulta, la consulta no está en espera.");

        //Error 2 por fecha.
        s.agregarPaciente("Carla", 3453483, "Pocitos 2132");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(87, date);
        s.reservaConsulta(87, 3453483, date);
        p.ver(s.terminarConsultaMedicoPaciente(3453483, 87, "detalle consulta ejemplo2").resultado, Retorno.Resultado.ERROR_2, "No se termina la consulta, la fecha de consulta está mal.");

        //OK.
        s.reservaConsulta(87, 3453483, fecha);
        s.anunciaLlegada(87, 3453483);
        p.ver(s.terminarConsultaMedicoPaciente(3453483, 87, "detalle consulta ejemplo3").resultado, Retorno.Resultado.OK, "Consulta terminada correctamente.");

    }

    public static void p13_CerrarConsulta(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        p.ver(s.cerrarConsulta("17", date).resultado, Retorno.Resultado.ERROR_1, "Ese codigo de medico no existe.");

        //Error 2.
        s.registrarMedico("Humberto", 71, 199111212, 12);
        s.registrarDiaDeConsulta(71, date);
        p.ver(s.cerrarConsulta("71", date).resultado, Retorno.Resultado.ERROR_2, "Ese medico no tiene consultas para esa fecha.");

        //OK.
        s.registrarMedico("Pepe", 72, 194614212, 13);
        s.agregarPaciente("Julian", 21446335, "18 de julio");
        s.agregarPaciente("Felipe", 67346256, "Cuareim 4367");
        s.registrarDiaDeConsulta(72, date);
        s.reservaConsulta(72, 21446335, date);
        s.reservaConsulta(72, 67346256, date);
        p.ver(s.cerrarConsulta("72", date).resultado, Retorno.Resultado.OK, "Cerrado correctamente.");
    }

    public static void p14_ListarConsultas(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        p.ver(s.listarConsultas(897).resultado, Retorno.Resultado.ERROR_1, "Ese médico no existe.");

        //OK.
        //Fecha 1.
        s.registrarMedico("Hector", 48, 123564222, 18);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();

        s.registrarDiaDeConsulta(48, date);

        s.agregarPaciente("Fernando", 53436455, "Libertador 2345");
        s.agregarPaciente("Estela", 27244653, "Amezaga 1367");

        s.reservaConsulta(48, 53436455, date);
        s.reservaConsulta(48, 27244653, date);
        s.anunciaLlegada(48, 27244653);
        //s.anunciaLlegada(48, 53436455);
        s.terminarConsultaMedicoPaciente(27244653, 48, "Consulta mensual de la presion.");
        s.cerrarConsulta("48", date);

        //Fecha 2.
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.AUGUST, 13);
        Date date2 = calendar2.getTime();

        s.registrarDiaDeConsulta(48, date2);

        s.agregarPaciente("Gustav", 34235632, "Bv Artigas 2345");
        s.agregarPaciente("Pedro", 43234436, "Cementerio del norte");

        s.reservaConsulta(48, 34235632, date2);
        s.reservaConsulta(48, 43234436, date2);
        s.cerrarConsulta("48", date2);

        p.ver(s.listarConsultas(48).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");
    }

    public static void p15_ListarPacientesEnEspera(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        s.registrarMedico("Roberto", 77, 124124214, 16);
        p.ver(s.listarPacientesEnEspera("77", new Date()).resultado, Retorno.Resultado.ERROR_1, "No hay fecha de consulta.");

        //Ok.
        s.registrarMedico("Camila", 78, 1164124213, 17);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(78, date);
        s.agregarPaciente("Julian", 21446335, "18 de julio");
        s.agregarPaciente("Carla", 3453483, "Pocitos 2132");
        s.agregarPaciente("Jose", 51423385, "Acevedo Diaz 6961");
        s.reservaConsulta(78, 21446335, date);
        s.reservaConsulta(78, 3453483, date);
        s.reservaConsulta(78, 51423385, date);
        s.anunciaLlegada(78, 21446335);
        s.anunciaLlegada(78, 51423385);
        p.ver(s.listarPacientesEnEspera("78", date).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");
    }

    public static void p16_ListarConsultasPendientesPaciente(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        p.ver(s.consultasPendientesPaciente(10).resultado, Retorno.Resultado.ERROR_1, "El paciente no existe.");

        //OK.
        s.registrarMedico("Jesus", 95, 1345462678, 2);
        s.agregarPaciente("Lucas", 82536347, "Hector Miranda 1212");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(95, date);
        s.reservaConsulta(95, 82536347, date);
        p.ver(s.consultasPendientesPaciente(82536347).resultado, Retorno.Resultado.OK, "Se muestran las consultas pendientes.");
    }

    public static void p17_ListarHistorialClinicoPaciente(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(7);

        //Error 1.
        p.ver(s.historiaClínicaPaciente(10).resultado, Retorno.Resultado.ERROR_1, "El paciente no existe.");

        //OK.
        //Ejemplo 1:
        s.registrarMedico("Manuela", 83, 1241154678, 5);
        s.registrarMedico("Elizabeth", 85, 1121568678, 8);
        s.agregarPaciente("Eliana", 34566335, "Constitucion");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26, 15, 30, 0);
        Date date = calendar.getTime();
        s.registrarDiaDeConsulta(83, date);
        s.registrarDiaDeConsulta(85, date);
        s.reservaConsulta(83, 34566335, date);
        s.reservaConsulta(85, 34566335, date);
        s.anunciaLlegada(83, 34566335);
        s.anunciaLlegada(85, 34566335);
        s.terminarConsultaMedicoPaciente(34566335, 83, "Consulta de revisión mensual.");
        s.terminarConsultaMedicoPaciente(34566335, 85, "Consulta de revisión mensual2.");

        //Ejemplo 2:
        s.registrarMedico("Juliana", 84, 1356744678, 6);
        s.agregarPaciente("Josefina", 45634385, "Miñones");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.AUGUST, 21, 15, 30, 0);
        Date date2 = calendar2.getTime();
        s.registrarDiaDeConsulta(84, date2);
        s.reservaConsulta(84, 45634385, date2);
        s.cerrarConsulta("84", date2);

        //Ejemplo 3:
        s.registrarMedico("Baltazar", 102, 184637596, 2);
        s.registrarMedico("Homero", 103, 124144798, 3);
        s.registrarMedico("Alejandro", 104, 144564551, 4);
        s.registrarMedico("Edward", 105, 112638136, 5);
        s.agregarPaciente("Julius", 75740375, "La paz 5676");

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.FEBRUARY, 03);
        Date date3 = calendar3.getTime();

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2023, Calendar.APRIL, 14);
        Date date4 = calendar4.getTime();

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(2023, Calendar.MARCH, 21);
        Date date5 = calendar5.getTime();

        s.registrarDiaDeConsulta(102, date3);
        s.registrarDiaDeConsulta(103, date4);
        s.registrarDiaDeConsulta(104, date5);
        s.registrarDiaDeConsulta(105, date3);

        s.reservaConsulta(102, 75740375, date3);
        s.reservaConsulta(103, 75740375, date4);
        s.reservaConsulta(104, 75740375, date5);
        s.reservaConsulta(105, 75740375, date3);

        s.anunciaLlegada(102, 75740375);
        s.anunciaLlegada(104, 75740375);

        s.terminarConsultaMedicoPaciente(75740375, 102, "Niveles estables de colesterol, siga asi.");
        s.terminarConsultaMedicoPaciente(75740375, 104, "Vacuna antitetánica.");
        s.cerrarConsulta("103", date4);

        p.ver(s.historiaClínicaPaciente(34566335).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");
        p.ver(s.historiaClínicaPaciente(45634385).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");
        p.ver(s.historiaClínicaPaciente(75740375).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");
    }

    public static void p18_ReporteDePacientesMatriz(Prueba p, Sistema s) {
        s.crearSistemaDeAutogestion(15);

        //Error 1.
        p.ver(s.reporteDePacientesXFechaYEspecialidad(65, 2022).resultado, Retorno.Resultado.ERROR_1, "No existe el mes 65");
        p.ver(s.reporteDePacientesXFechaYEspecialidad(5, 2019).resultado, Retorno.Resultado.ERROR_1, "El año debe ser entre 2020-Actual");
        
        
        //OK.
        //Abajo habrán más datos precargados para la matriz para poder probar. Dejamos este como principal para mostrar cuál fue el proceso
        //para que los datos se reflejen en la matriz. Abajo habrán muchos más datos y para cualquier dia, de cualquier mes, de cualquier año (2020-2023).
        s.registrarMedico("Manuela", 83, 1241154678, 5);
        s.registrarMedico("Elizabeth", 85, 1121568678, 8);
        s.registrarMedico("Baltazar", 102, 184637596, 2);
        s.registrarMedico("Homero", 103, 124144798, 3);
        s.registrarMedico("Alejandro", 104, 144564551, 4);
        s.registrarMedico("Edward", 105, 112638136, 5);
        s.registrarMedico("Kevin", 109, 121643136, 17);

        s.agregarPaciente("Eliana", 34566335, "Constitucion");
        s.agregarPaciente("Julian", 21446335, "18 de julio");
        s.agregarPaciente("Carla", 23453483, "Pocitos 2132");
        s.agregarPaciente("Jose", 51423385, "Acevedo Diaz 6961");
        s.agregarPaciente("Martin", 12345678, "Eduardo Acevedo 1289");
        s.agregarPaciente("Lobezno", 12345218, "Acevedo Diaz 5674");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26);
        Date date = calendar.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.OCTOBER, 30);
        Date date2 = calendar2.getTime();

        s.registrarDiaDeConsulta(83, date);
        s.registrarDiaDeConsulta(85, date);
        s.registrarDiaDeConsulta(102, date);
        s.registrarDiaDeConsulta(103, date);
        s.registrarDiaDeConsulta(104, date);
        s.registrarDiaDeConsulta(105, date);
        s.registrarDiaDeConsulta(109, date2);
        

        s.reservaConsulta(83, 34566335, date);
        s.reservaConsulta(85, 21446335, date);
        s.reservaConsulta(102, 23453483, date);
        s.reservaConsulta(103, 51423385, date);
        s.reservaConsulta(104, 12345678, date);
        s.reservaConsulta(105, 12345218, date);
        s.reservaConsulta(109, 12345218, date2);
        s.reservaConsulta(109, 23453483, date2);

        
        s.anunciaLlegada(83, 34566335);
        s.anunciaLlegada(85, 21446335);
        s.anunciaLlegada(102, 23453483);
        s.anunciaLlegada(103, 51423385);
        s.anunciaLlegada(104, 12345678);
        s.anunciaLlegada(105, 12345218);
        s.anunciaLlegada(109, 12345218);
        s.anunciaLlegada(109, 23453483);

        //En la matriz, según la letra se contabilizan las consultas que están terminadas.
        s.terminarConsultaMedicoPaciente(34566335, 83, "Detalle1");
        s.terminarConsultaMedicoPaciente(21446335, 85, "Detalle2");
        s.terminarConsultaMedicoPaciente(23453483, 102, "Detalle3");
        s.terminarConsultaMedicoPaciente(51423385, 103, "Detalle4");
        s.terminarConsultaMedicoPaciente(12345678, 104, "Detalle5");
        s.terminarConsultaMedicoPaciente(12345218, 105, "Detalle6");
        s.terminarConsultaMedicoPaciente(12345218, 109, "Detalle7");
        s.terminarConsultaMedicoPaciente(23453483, 109, "Detalle8");
        

        //Los datos se reflejarán en octubre, el día 26.
        p.ver(s.reporteDePacientesXFechaYEspecialidad(10, 2023).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");

        
        /*
            Mas juegos de pruebas para mostrar una matriz con mas datos.
            Para usarlo de una forma más efectiva, se debe seleccionar el mes que se desea mostrar y el año;
            haciendo esto se evita hacer muchas iteraciones innecesarias.
            El motivo por el cual este no es el juego de pruebas que evidencia el OK de primera mano, es porque me parecía
            conveniente de que en el OK se muestre todo el recorrido (Crear médico, crear paciente, etc), por eso los datos brindados
            en ese caso son menos.
        */
//        int mes = Calendar.JANUARY;
//        int año = 2023;
//        PrecargaMatriz(s, mes, año);
//        p.ver(s.reporteDePacientesXFechaYEspecialidad((mes+1), año).resultado, Retorno.Resultado.OK, "Se muestra correctamente.");

    }

    private static void PrecargaMatriz(Sistema s, int mes, int año) {
        s.registrarMedico("Roberto", 200, 123456789, 1);
        s.registrarMedico("Laura", 201, 987654321, 2);
        s.registrarMedico("José", 202, 111223344, 3);
        s.registrarMedico("María", 203, 555666777, 4);
        s.registrarMedico("Juan", 204, 999000111, 5);
        s.registrarMedico("Ana", 205, 222333444, 6);
        s.registrarMedico("Luis", 206, 777888999, 7);
        s.registrarMedico("Carmen", 207, 333444555, 8);
        s.registrarMedico("Carlos", 208, 666777888, 9);
        s.registrarMedico("Patricia", 209, 444555666, 10);
        s.registrarMedico("Fernando", 210, 111223344, 11);
        s.registrarMedico("Sonia", 211, 555666777, 12);
        s.registrarMedico("Jorge", 212, 999000111, 13);
        s.registrarMedico("Claudia", 213, 222333444, 14);
        s.registrarMedico("Ricardo", 214, 777888999, 15);
        s.registrarMedico("Adriana", 215, 333444555, 16);
        s.registrarMedico("Manuel", 216, 666777888, 17);
        s.registrarMedico("Beatriz", 217, 444555666, 18);
        s.registrarMedico("Daniel", 218, 123456789, 19);
        s.registrarMedico("Carolina", 219, 987654321, 20);
        s.registrarMedico("Eduardo", 220, 111223344, 1);
        s.registrarMedico("Marta", 221, 555666777, 2);
        s.registrarMedico("Gustavo", 222, 999000111, 3);
        s.registrarMedico("Laura", 223, 222333444, 4);
        s.registrarMedico("Andrés", 224, 777888999, 5);
        s.registrarMedico("Isabella", 225, 333444555, 6);
        s.registrarMedico("Pablo", 226, 666777888, 7);
        s.registrarMedico("Valeria", 227, 444555666, 8);
        s.registrarMedico("Sergio", 228, 123456789, 9);
        s.registrarMedico("Natalia", 229, 987654321, 10);
        s.registrarMedico("Antonio", 230, 111223344, 11);
        s.registrarMedico("Elisa", 231, 555666777, 12);
        s.registrarMedico("Carlos", 232, 999000111, 13);
        s.registrarMedico("Gabriela", 233, 222333444, 14);
        s.registrarMedico("Juan Carlos", 234, 777888999, 15);
        s.registrarMedico("Susana", 235, 333444555, 16);
        s.registrarMedico("Felipe", 236, 666777888, 17);
        s.registrarMedico("Paola", 237, 444555666, 18);
        s.registrarMedico("Gabriel", 238, 123456789, 19);
        s.registrarMedico("Laura", 239, 987654321, 20);

        
        s.agregarPaciente("Martín", 56789012, "Artigas");       
        s.agregarPaciente("Sofía", 12345678, "Reconquista");       
        s.agregarPaciente("Luis", 98765432, "Cerro Largo");       
        s.agregarPaciente("Valentina", 23456789, "Paysandú");        
        s.agregarPaciente("Andrés", 87654321, "Bulevar España");        
        s.agregarPaciente("Sara", 23456701, "Montevideo");        
        s.agregarPaciente("Carlos", 22334455, "San José");        
        s.agregarPaciente("Mariano", 12345601, "Tacuarembó");        
        s.agregarPaciente("Gabriel", 11223344, "Avenida Italia");        
        s.agregarPaciente("Camila", 34566335, "Rivera");        
        s.agregarPaciente("Laura", 54321678, "Pando");        
        s.agregarPaciente("Felipe", 55566677, "Canelones");
        s.agregarPaciente("Rocío", 56778901, "Treinta y Tres");
        s.agregarPaciente("Eduardo", 76543234, "Pando");
        s.agregarPaciente("Bianca", 78912345, "Cerro Largo");
        s.agregarPaciente("Valeria", 89012345, "Reconquista");
        s.agregarPaciente("Lucas", 90123456, "Artigas");
        s.agregarPaciente("Roberto", 90123457, "Bulevar España");
        s.agregarPaciente("Sofía", 90123458, "Paysandú");
        s.agregarPaciente("Gonzalo", 90123459, "Montevideo");
        s.agregarPaciente("Cecilia", 90123450, "San José");
        s.agregarPaciente("Julieta", 90123451, "Tacuarembó");
        s.agregarPaciente("Juan", 90123452, "Rivera");
        s.agregarPaciente("Diego", 90123453, "Reconquista");
        s.agregarPaciente("Sara", 90123454, "Avenida Italia");
        s.agregarPaciente("Mariano", 90123455, "Rivera");
        s.agregarPaciente("Lucía", 90123456, "Canelones");
        s.agregarPaciente("Diego", 90123457, "Cerro Largo");
        s.agregarPaciente("Andrés", 90123458, "Rivera");
        s.agregarPaciente("Natalia", 90123459, "Montevideo");
        s.agregarPaciente("Luis", 90123460, "San José");
        s.agregarPaciente("Felipe", 90123461, "Paysandú");
        s.agregarPaciente("Cecilia", 90123462, "Bulevar España");
        s.agregarPaciente("Roberto", 90123463, "Artigas");
        s.agregarPaciente("Gonzalo", 90123464, "Tacuarembó");
        s.agregarPaciente("Lucas", 90123465, "Treinta y Tres");
        s.agregarPaciente("Camila", 90123466, "Avenida Italia");
        s.agregarPaciente("Felipe", 90123467, "Pando");
        s.agregarPaciente("Valentina", 90123468, "Rivera");
        s.agregarPaciente("Mariano", 90123469, "Canelones");
        s.agregarPaciente("Sofía", 90123470, "Constitución");
        s.agregarPaciente("Valeria", 90123471, "Reconquista");
        s.agregarPaciente("Julieta", 90123472, "Treinta y Tres");
        s.agregarPaciente("Juan", 90123473, "Cerro Largo");
        s.agregarPaciente("Lucía", 90123474, "Pando");
        s.agregarPaciente("Gonzalo", 90123475, "Tacuarembó");
        s.agregarPaciente("Diego", 90123476, "Pando");

        PrecargaPorMesMatriz(s, mes, año);
    }
    
    /*
        Este método es para probar el correcto funcionamiento de CancelarReserva().
        Tal vez sea útil a la hora de testear.
    */
    public static void ProbarCancelar(Prueba p, Sistema s){
        s.crearSistemaDeAutogestion(7);
        
        s.registrarMedico("Roberto", 200, 123456789, 1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26);
        Date date = calendar.getTime();
        
        s.registrarDiaDeConsulta(200, date);
        
        s.agregarPaciente("Martín", 56789012, "Artigas");       
        s.agregarPaciente("Sofía", 12345678, "Reconquista");       
        s.agregarPaciente("Luis", 98765432, "Cerro Largo");       
        s.agregarPaciente("Valentina", 23456789, "Paysandú");        
        s.agregarPaciente("Andrés", 87654321, "Bulevar España");        
        s.agregarPaciente("Sara", 23456701, "Montevideo");        
        s.agregarPaciente("Carlos", 22334455, "San José");        
        s.agregarPaciente("Mariano", 12345601, "Tacuarembó");        
        s.agregarPaciente("Gabriel", 11223344, "Avenida Italia");        
        s.agregarPaciente("Camila", 34566335, "Rivera"); 
        
        s.reservaConsulta(200, 56789012, date);
        s.reservaConsulta(200, 12345678, date);
        s.reservaConsulta(200, 98765432, date);
        s.reservaConsulta(200, 23456789, date);
        s.reservaConsulta(200, 87654321, date);
        s.reservaConsulta(200, 23456701, date);
        s.reservaConsulta(200, 22334455, date);
        s.reservaConsulta(200, 12345601, date);
        s.reservaConsulta(200, 11223344, date);
        s.reservaConsulta(200, 34566335, date);
        
        s.cancelarReserva(200, 12345678);
        s.cancelarReserva(200, 23456789);
        
        s.anunciaLlegada(200, 11223344); //Efectivamente el estado cambia correctamente.
        s.anunciaLlegada(200, 87654321);
        s.terminarConsultaMedicoPaciente(87654321, 200, "detalle");
        
        
        s.VerListaEnFecha(200, date);
    }

    
    private static void PrecargaPorMesMatriz(Sistema s, int mesSeleccionado, int añoSeleccionado) {
        int mes = mesSeleccionado;
        int año = añoSeleccionado;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, año);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        while (calendar.get(Calendar.MONTH) == mes) {
            Date fecha = calendar.getTime();

            s.registrarDiaDeConsulta(200, fecha);
            s.registrarDiaDeConsulta(201, fecha);
            s.registrarDiaDeConsulta(202, fecha);
            s.registrarDiaDeConsulta(203, fecha);
            s.registrarDiaDeConsulta(204, fecha);
            s.registrarDiaDeConsulta(205, fecha);
            s.registrarDiaDeConsulta(206, fecha);
            s.registrarDiaDeConsulta(207, fecha);
            s.registrarDiaDeConsulta(208, fecha);
            s.registrarDiaDeConsulta(209, fecha);
            s.registrarDiaDeConsulta(210, fecha);
            s.registrarDiaDeConsulta(211, fecha);
            s.registrarDiaDeConsulta(212, fecha);
            s.registrarDiaDeConsulta(213, fecha);
            s.registrarDiaDeConsulta(214, fecha);
            s.registrarDiaDeConsulta(215, fecha);
            s.registrarDiaDeConsulta(216, fecha);
            s.registrarDiaDeConsulta(217, fecha);
            s.registrarDiaDeConsulta(218, fecha);
            s.registrarDiaDeConsulta(219, fecha);
            s.registrarDiaDeConsulta(220, fecha);
            s.registrarDiaDeConsulta(221, fecha);
            s.registrarDiaDeConsulta(222, fecha);
            s.registrarDiaDeConsulta(223, fecha);
            s.registrarDiaDeConsulta(224, fecha);
            s.registrarDiaDeConsulta(225, fecha);
            s.registrarDiaDeConsulta(226, fecha);
            s.registrarDiaDeConsulta(227, fecha);
            s.registrarDiaDeConsulta(228, fecha);
            s.registrarDiaDeConsulta(229, fecha);
            s.registrarDiaDeConsulta(230, fecha);
            s.registrarDiaDeConsulta(231, fecha);
            s.registrarDiaDeConsulta(232, fecha);
            s.registrarDiaDeConsulta(233, fecha);
            s.registrarDiaDeConsulta(234, fecha);
            s.registrarDiaDeConsulta(235, fecha);
            s.registrarDiaDeConsulta(236, fecha);
            s.registrarDiaDeConsulta(237, fecha);
            s.registrarDiaDeConsulta(238, fecha);
            s.registrarDiaDeConsulta(239, fecha);

            
            s.reservaConsulta(200, 56789012, fecha);               
            s.reservaConsulta(201, 12345678, fecha);               
            s.reservaConsulta(202, 98765432, fecha);                  
            s.reservaConsulta(203, 23456789, fecha);               
            s.reservaConsulta(204, 87654321, fecha);              
            s.reservaConsulta(205, 23456701, fecha);                 
            s.reservaConsulta(206, 22334455, fecha);                     
            s.reservaConsulta(207, 12345601, fecha);              
            s.reservaConsulta(208, 11223344, fecha);               
            s.reservaConsulta(209, 34566335, fecha);               
            s.reservaConsulta(210, 54321678, fecha);              
            s.reservaConsulta(211, 55566677, fecha);            
            s.reservaConsulta(212, 56778901, fecha);            
            s.reservaConsulta(213, 76543234, fecha);            
            s.reservaConsulta(214, 78912345, fecha);            
            s.reservaConsulta(215, 89012345, fecha);            
            s.reservaConsulta(216, 90123456, fecha);            
            s.reservaConsulta(217, 90123457, fecha);           
            s.reservaConsulta(218, 90123458, fecha);            
            s.reservaConsulta(219, 90123459, fecha);            
            s.reservaConsulta(220, 90123460, fecha);            
            s.reservaConsulta(221, 90123461, fecha);            
            s.reservaConsulta(222, 90123462, fecha);            
            s.reservaConsulta(223, 90123463, fecha);            
            s.reservaConsulta(224, 90123464, fecha);            
            s.reservaConsulta(225, 90123465, fecha);            
            s.reservaConsulta(226, 90123466, fecha);            
            s.reservaConsulta(227, 90123467, fecha);            
            s.reservaConsulta(228, 90123468, fecha);            
            s.reservaConsulta(229, 90123469, fecha);            
            s.reservaConsulta(230, 90123470, fecha);            
            s.reservaConsulta(231, 90123471, fecha);            
            s.reservaConsulta(232, 90123472, fecha);            
            s.reservaConsulta(233, 90123473, fecha);            
            s.reservaConsulta(234, 90123474, fecha);            
            s.reservaConsulta(235, 90123475, fecha);
            s.reservaConsulta(236, 90123476, fecha);

            
            s.anunciaLlegada(200, 56789012);                       
            s.anunciaLlegada(201, 12345678);                
            s.anunciaLlegada(202, 98765432);                  
            s.anunciaLlegada(203, 23456789);                  
            s.anunciaLlegada(204, 87654321);               
            s.anunciaLlegada(205, 23456701);                  
            s.anunciaLlegada(206, 22334455);                
            s.anunciaLlegada(207, 12345601);              
            s.anunciaLlegada(208, 11223344);                     
            s.anunciaLlegada(209, 34566335);                     
            s.anunciaLlegada(210, 54321678);           
            s.anunciaLlegada(211, 55566677);           
            s.anunciaLlegada(212, 56778901);            
            s.anunciaLlegada(213, 76543234);           
            s.anunciaLlegada(214, 78912345);           
            s.anunciaLlegada(215, 89012345);           
            s.anunciaLlegada(216, 90123456);           
            s.anunciaLlegada(217, 90123457);            
            s.anunciaLlegada(218, 90123458);           
            s.anunciaLlegada(219, 90123459);           
            s.anunciaLlegada(220, 90123460);            
            s.anunciaLlegada(221, 90123461);            
            s.anunciaLlegada(222, 90123462);            
            s.anunciaLlegada(223, 90123463);            
            s.anunciaLlegada(224, 90123464);            
            s.anunciaLlegada(225, 90123465);           
            s.anunciaLlegada(226, 90123466);            
            s.anunciaLlegada(227, 90123467);
            s.anunciaLlegada(228, 90123468);           
            s.anunciaLlegada(229, 90123469);           
            s.anunciaLlegada(230, 90123470);           
            s.anunciaLlegada(231, 90123471);           
            s.anunciaLlegada(232, 90123472);           
            s.anunciaLlegada(233, 90123473);           
            s.anunciaLlegada(234, 90123474);
            s.anunciaLlegada(235, 90123475);
            s.anunciaLlegada(236, 90123476);

            
            s.terminarConsultaMedicoPaciente(56789012, 200, "detalle");           
            s.terminarConsultaMedicoPaciente(12345678, 201, "detalle");          
            s.terminarConsultaMedicoPaciente(98765432, 202, "detalle");            
            s.terminarConsultaMedicoPaciente(23456789, 203, "detalle");            
            s.terminarConsultaMedicoPaciente(87654321, 204, "detalle");           
            s.terminarConsultaMedicoPaciente(23456701, 205, "detalle");           
            s.terminarConsultaMedicoPaciente(22334455, 206, "detalle");            
            s.terminarConsultaMedicoPaciente(12345601, 207, "detalle");           
            s.terminarConsultaMedicoPaciente(11223344, 208, "detalle");           
            s.terminarConsultaMedicoPaciente(34566335, 209, "detalle");           
            s.terminarConsultaMedicoPaciente(54321678, 210, "detalle");           
            s.terminarConsultaMedicoPaciente(55566677, 211, "detalle");
            s.terminarConsultaMedicoPaciente(56778901, 212, "detalle");
            s.terminarConsultaMedicoPaciente(76543234, 213, "detalle");
            s.terminarConsultaMedicoPaciente(78912345, 214, "detalle");
            s.terminarConsultaMedicoPaciente(89012345, 215, "detalle");
            s.terminarConsultaMedicoPaciente(90123456, 216, "detalle");
            s.terminarConsultaMedicoPaciente(90123457, 217, "detalle");
            s.terminarConsultaMedicoPaciente(90123458, 218, "detalle");
            s.terminarConsultaMedicoPaciente(90123459, 219, "detalle");
            s.terminarConsultaMedicoPaciente(90123460, 220, "detalle");
            s.terminarConsultaMedicoPaciente(90123461, 221, "detalle");
            s.terminarConsultaMedicoPaciente(90123462, 222, "detalle");
            s.terminarConsultaMedicoPaciente(90123463, 223, "detalle");
            s.terminarConsultaMedicoPaciente(90123464, 224, "detalle");
            s.terminarConsultaMedicoPaciente(90123465, 225, "detalle");
            s.terminarConsultaMedicoPaciente(90123466, 226, "detalle");
            s.terminarConsultaMedicoPaciente(90123467, 227, "detalle");
            s.terminarConsultaMedicoPaciente(90123468, 228, "detalle");
            s.terminarConsultaMedicoPaciente(90123469, 229, "detalle");
            s.terminarConsultaMedicoPaciente(90123470, 230, "detalle");
            s.terminarConsultaMedicoPaciente(90123471, 231, "detalle");
            s.terminarConsultaMedicoPaciente(90123472, 232, "detalle");
            s.terminarConsultaMedicoPaciente(90123473, 233, "detalle");
            s.terminarConsultaMedicoPaciente(90123474, 234, "detalle");
            s.terminarConsultaMedicoPaciente(90123475, 235, "detalle");
            s.terminarConsultaMedicoPaciente(90123476, 236, "detalle");

            calendar.add(Calendar.DAY_OF_MONTH, 1); //Aumentamos en uno el día.

            //En caso de que avance tanto, el mes cambia, a continuación se tiene en cuenta este escenario.
            if (calendar.get(Calendar.MONTH) != mes) {
                mes = calendar.get(Calendar.MONTH);
                año = calendar.get(Calendar.YEAR);

                if (año > 2023) {
                    break;
                }
            }
        }

    }

    
    
    public static void MostrarNoAsistidas(Prueba p, Sistema s){
        
        s.crearSistemaDeAutogestion(12);
        
        s.registrarMedico("Roberto", 200, 123456789, 1);
        s.registrarMedico("Laura", 201, 987654321, 2);
        s.registrarMedico("José", 202, 111223344, 3);
        
        s.agregarPaciente("Martín", 56789012, "Artigas");       
        s.agregarPaciente("Sofía", 12345678, "Reconquista");       
        s.agregarPaciente("Luis", 98765432, "Cerro Largo");       
        s.agregarPaciente("Valentina", 23456789, "Paysandú");        
        s.agregarPaciente("Andrés", 87654321, "Bulevar España");        
        s.agregarPaciente("Sara", 23456701, "Montevideo");        
        s.agregarPaciente("Carlos", 22334455, "San José");        
        s.agregarPaciente("Mariano", 12345601, "Tacuarembó");        
        s.agregarPaciente("Gabriel", 11223344, "Avenida Italia");
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 26);
        Date date = calendar.getTime();
        
        s.registrarDiaDeConsulta(200, date);
        s.registrarDiaDeConsulta(201, date);
        s.registrarDiaDeConsulta(202, date);
        
        s.reservaConsulta(200, 56789012, date);
        s.reservaConsulta(200, 12345678, date);
        s.reservaConsulta(200, 98765432, date);
        s.reservaConsulta(200, 23456789, date);
        s.reservaConsulta(200, 87654321, date);
        s.reservaConsulta(200, 23456701, date);
        s.reservaConsulta(200, 22334455, date);
        s.reservaConsulta(200, 12345601, date);
        s.reservaConsulta(200, 11223344, date);
        
        s.reservaConsulta(201, 56789012, date);
        s.reservaConsulta(201, 12345678, date);
        s.reservaConsulta(201, 98765432, date);
        s.reservaConsulta(201, 23456789, date);
        s.reservaConsulta(201, 87654321, date);
        s.reservaConsulta(201, 23456701, date);
        s.reservaConsulta(201, 22334455, date);
        s.reservaConsulta(201, 12345601, date);
        s.reservaConsulta(201, 11223344, date);
        
        s.reservaConsulta(202, 56789012, date);
        s.reservaConsulta(202, 12345678, date);
        s.reservaConsulta(202, 98765432, date);
        s.reservaConsulta(202, 23456789, date);
        s.reservaConsulta(202, 87654321, date);
        s.reservaConsulta(202, 23456701, date);
        s.reservaConsulta(202, 22334455, date);
        s.reservaConsulta(202, 12345601, date);
        s.reservaConsulta(202, 11223344, date);
        
//        s.anunciaLlegada(200, 56789012);
//        s.anunciaLlegada(201, 56789012);
//        s.anunciaLlegada(202, 56789012);
        
        s.cerrarConsulta("200", date);
        s.cerrarConsulta("201", date);
        s.cerrarConsulta("202", date);
        
        //s.MostrarNoAsistidos(56789012);
        s.MostrarNoAsistidosRec(56789012);
    }
    
    
    
    
    public static void MostrarMatrizFecha(Sistema s){
        s.crearSistemaDeAutogestion(15);
        
        s.registrarMedico("Manuela", 83, 1241154678, 5);
        s.registrarMedico("Elizabeth", 85, 1121568678, 8);
        s.registrarMedico("Baltazar", 102, 184637596, 2);
        s.registrarMedico("Homero", 103, 124144798, 3);
        s.registrarMedico("Alejandro", 104, 144564551, 4);
        s.registrarMedico("Edward", 105, 112638136, 5);
        s.registrarMedico("Kevin", 109, 121643136, 17);

        s.agregarPaciente("Eliana", 34566335, "Constitucion");
        s.agregarPaciente("Julian", 21446335, "18 de julio");
        s.agregarPaciente("Carla", 23453483, "Pocitos 2132");
        s.agregarPaciente("Jose", 51423385, "Acevedo Diaz 6961");
        s.agregarPaciente("Martin", 12345678, "Eduardo Acevedo 1289");
        s.agregarPaciente("Lobezno", 12345218, "Acevedo Diaz 5674");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 11);
        Date date = calendar.getTime();
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.OCTOBER, 12);
        Date date2 = calendar2.getTime();
        
        
        
        s.registrarDiaDeConsulta(83, date);
        s.registrarDiaDeConsulta(85, date);
        s.registrarDiaDeConsulta(102, date);
        s.registrarDiaDeConsulta(103, date);
        s.registrarDiaDeConsulta(104, date);
        s.registrarDiaDeConsulta(105, date);
        

        //s.reservaConsulta(83, 34566335, date2);
        
        s.reservaConsulta(83, 34566335, date);
        s.reservaConsulta(85, 21446335, date);
        s.reservaConsulta(102, 23453483, date);
        s.reservaConsulta(103, 51423385, date);
        s.reservaConsulta(104, 12345678, date);
        s.reservaConsulta(105, 12345218, date);

        
        s.anunciaLlegada(83, 34566335);
        s.anunciaLlegada(85, 21446335);
        s.anunciaLlegada(102, 23453483);
        s.anunciaLlegada(103, 51423385);
        s.anunciaLlegada(104, 12345678);
        s.anunciaLlegada(105, 12345218);

        //En la matriz, según la letra se contabilizan las consultas que están terminadas.
        s.terminarConsultaMedicoPaciente(34566335, 83, "Detalle1");
        s.terminarConsultaMedicoPaciente(21446335, 85, "Detalle2");
        s.terminarConsultaMedicoPaciente(23453483, 102, "Detalle3");
        s.terminarConsultaMedicoPaciente(51423385, 103, "Detalle4");
        s.terminarConsultaMedicoPaciente(12345678, 104, "Detalle5");
        s.terminarConsultaMedicoPaciente(12345218, 105, "Detalle6");
        
        s.registrarDiaDeConsulta(83, date2);
        s.reservaConsulta(83, 34566335, date2);
        s.anunciaLlegada(83, 34566335);
        s.terminarConsultaMedicoPaciente(34566335, 83, "Detallex");
        
        //s.MostrarFechaMatriz(11, 10, 2023);
        s.MostrarConsultasTerPorCodMed(85);
    }
    
    
    
    
}
