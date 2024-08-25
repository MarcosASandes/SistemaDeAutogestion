package sistemaAutogestion;

import classes.*;
import java.util.Calendar;
import java.util.Date;
import tads.*;

public class Sistema implements IObligatorio {

    /*Listas*/
    public Lista<Medico> listaMedicos;
    public Lista<Paciente> listaPacientes;
    public static int MaximoLista;
    public Lista<ReservaFecha> listaTotalConsultas; //Acá estarán todas las consultas, sin importar de qué médico.


    /*Operación 2.1*/
    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        Retorno ret;
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            listaMedicos = new Lista<Medico>();
            listaPacientes = new Lista<Paciente>();
            MaximoLista = maxPacientesporMedico;
            listaTotalConsultas = new Lista<ReservaFecha>();
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /*Operación 2.2*/
    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno ret;

        Medico m = new Medico(codMedico, nombre, tel, especialidad);
        if (listaMedicos.existeElemento(m)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else if (especialidad < 1 || especialidad > 20) {
            ret = new Retorno(Retorno.Resultado.ERROR_2);
        } else {
            listaMedicos.agregarOrdenado(m);
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }


    /*Operación 2.3*/
    @Override
    public Retorno eliminarMedico(int codMedico) {

        Retorno ret;
        Medico medBuscado = new Medico(codMedico);
        if (!listaMedicos.existeElemento(medBuscado)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            if (!medEnLista.getConsultas().esVacia()) {
                ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                listaMedicos.eliminarElemento(medBuscado);
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*Operación 2.4*/
    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno ret;
        Paciente nuevoPaciente = new Paciente(CI, nombre, direccion);
        if (listaPacientes.esVacia()) {
            listaPacientes.agregarFinal(nuevoPaciente);
            ret = new Retorno(Retorno.Resultado.OK);
        } else {
            if (listaPacientes.existeElemento(nuevoPaciente)) {
                ret = new Retorno(Retorno.Resultado.ERROR_1);
            } else {
                listaPacientes.agregarFinal(nuevoPaciente);
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*Operación 2.5*/
    @Override
    public Retorno eliminarPaciente(int CI) {

        Retorno ret;
        Paciente pacBuscado = new Paciente(CI);
        //pacBuscado.setCI(CI);
        if (!listaPacientes.existeElemento(pacBuscado)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            if (!pacEnLista.getHistorial().esVacia()) {
                ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                listaPacientes.eliminarElemento(pacBuscado);
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    @Override
    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {
        Retorno ret;
        Medico medBuscado = new Medico(codMedico);
        if (!listaMedicos.existeElemento(medBuscado)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            ReservaFecha auxReserva = new ReservaFecha(fecha);
            if (medEnLista.getConsultas().existeElemento(auxReserva)) {
                ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                ReservaFecha fechaCreada = new ReservaFecha(fecha, codMedico);
                medEnLista.getConsultas().agregarOrdenado(fechaCreada);
                listaTotalConsultas.agregarOrdenado(fechaCreada);
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*Operación 2.6*/
    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        Retorno ret;

        if (!existeCi(ciPaciente)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else if (!existeCod(codMedico)) {
            ret = new Retorno(Retorno.Resultado.ERROR_2);
        } else {
            Medico medBuscado = new Medico(codMedico);
            Paciente pacBuscado = new Paciente(ciPaciente);
            ConsultaMedica unaConsultaAux = new ConsultaMedica(medBuscado, pacBuscado, fecha);

            if (pacBuscado.getAgendas().existeElemento(unaConsultaAux)) {
                ret = new Retorno(Retorno.Resultado.ERROR_3);
            } else {
                Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
                Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);

                ReservaFecha dato = new ReservaFecha(fecha);
                ReservaFecha reservasDelMedicoEnFecha = medEnLista.getConsultas().obtenerElemento(dato);
                if (reservasDelMedicoEnFecha.getConsultasEnLaFecha().cantidadElementos() < MaximoLista) {
                    ConsultaMedica consultaDefinitiva = new ConsultaMedica(asignarNumero(medEnLista, fecha), "Pendiente", medEnLista, pacEnLista, fecha);
                    reservasDelMedicoEnFecha.getConsultasEnLaFecha().agregarFinal(consultaDefinitiva);
                    pacEnLista.getAgendas().agregarOrdenado(consultaDefinitiva);
                    ret = new Retorno(Retorno.Resultado.OK);
                } else {
                    ConsultaNoConfirmada consultaParaCola = new ConsultaNoConfirmada(medEnLista, pacEnLista);
                    reservasDelMedicoEnFecha.getListaEspera().encolar(consultaParaCola);
                    ret = new Retorno(Retorno.Resultado.OK);
                }
            }
        }
        return ret;
    }

    private int asignarNumero(Medico med, Date fecha) {
        int retorno;
        ReservaFecha dato = new ReservaFecha(fecha);
        ReservaFecha reservasDelMedicoEnFecha = med.getConsultas().obtenerElemento(dato);
        retorno = reservasDelMedicoEnFecha.getConsultasEnLaFecha().cantidadElementos() + 1;
        return retorno;
    }


    /*Operación 2.7*/
    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (!existeCi(ciPaciente)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else if (!existeCod(codMedico)) {
            ret = new Retorno(Retorno.Resultado.ERROR_2);
        } else {
            Medico medBuscado = new Medico(codMedico);
            Paciente pacBuscado = new Paciente(ciPaciente);
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            ConsultaMedica consultaAux = new ConsultaMedica(medEnLista, pacEnLista);
            if (!pacEnLista.getAgendas().existeElemento(consultaAux)) {
                ret = new Retorno(Retorno.Resultado.ERROR_3);
            } else {
                ConsultaMedica consultaEncontrada = pacEnLista.getAgendas().obtenerElemento(consultaAux);
                if (consultaEncontrada.getEstado() != "Pendiente") {
                    ret = new Retorno(Retorno.Resultado.ERROR_4);
                } else {
                    int numeroReserva = consultaEncontrada.getNumeroReserva();
                    Date fechaDeLaReserva = consultaEncontrada.getFechaConsulta();
                    pacEnLista.getAgendas().eliminarElemento(consultaEncontrada);
                    ReservaFecha unaReservaAux = new ReservaFecha(fechaDeLaReserva, codMedico);

                    if (medEnLista.getConsultas().existeElemento(unaReservaAux)) {
                        ReservaFecha reservaEncontrada = medEnLista.getConsultas().obtenerElemento(unaReservaAux);
                        if (reservaEncontrada.getConsultasEnLaFecha().existeElemento(consultaEncontrada)) {
                            reservaEncontrada.getConsultasEnLaFecha().eliminarElemento(consultaEncontrada);
                            pacEnLista.getAgendas().eliminarElemento(consultaEncontrada);
                            if (!reservaEncontrada.getListaEspera().isEmpty()) {
                                ConsultaMedica consultaPaciente = getConsultaDesencolada(reservaEncontrada, numeroReserva);
                                reservaEncontrada.getConsultasEnLaFecha().agregarOrdenado(consultaPaciente);
                                Paciente pacConsultaDesenc = consultaPaciente.getPacienteConsulta();
                                pacConsultaDesenc.getAgendas().agregarOrdenado(consultaPaciente);
                            }
                            ret = new Retorno(Retorno.Resultado.OK);
                        }
                    }
                }
            }
        }
        return ret;
    }

    /*
        Metodo auxiliar: Verifica si existe algun paciente con ese CI.
     */
    public boolean existeCi(int cod) {
        boolean existe = false;
        Nodo<Paciente> unPacNodo = listaPacientes.getInicio();
        while (unPacNodo != null && !existe) {
            if (unPacNodo.getDato().getCI() == cod) {
                existe = true;
            } else {
                unPacNodo = unPacNodo.getSiguiente();
            }
        }
        return existe;
    }

    /*
        Metodo auxiliar: Verifica si existe algun medico con ese codigo.
     */
    public boolean existeCod(int cod) {
        boolean existe = false;
        Nodo<Medico> unMedNodo = listaMedicos.getInicio();
        while (unMedNodo != null && !existe) {
            if (unMedNodo.getDato().getCodMed() == cod) {
                existe = true;
            } else {
                unMedNodo = unMedNodo.getSiguiente();
            }
        }
        return existe;
    }

    /*
        Metodo auxiliar: Devuelve la consulta desencolada, se le agrega el numero correspondiente.
     */
    private ConsultaMedica getConsultaDesencolada(ReservaFecha reservaEncontrada, int numeroReserva) {
        ConsultaMedica ret;
        ConsultaNoConfirmada ConsultaDeLaColaAux = reservaEncontrada.getListaEspera().desencolar();
        Date fecha = reservaEncontrada.getFechaConsultas();
        ConsultaMedica nuevaConsulta = new ConsultaMedica(numeroReserva, "Pendiente", ConsultaDeLaColaAux.getMedico(), ConsultaDeLaColaAux.getPaciente(), fecha);
        return nuevaConsulta;
    }

    /*
        Metodo auxiliar: Devuelve una consulta en concreto, sirve para testear.
     */
    public ConsultaMedica getConsultaParaTest(Medico m, Paciente p, Date f) {
        ConsultaMedica nuevaConsulta = new ConsultaMedica(m, p, f);
        ConsultaMedica ret = null;
        if (p.getAgendas().existeElemento(nuevaConsulta)) {
            ret = p.getAgendas().obtenerElemento(nuevaConsulta);
        }
        return ret;
    }

    /*
        Metodo auxiliar: Devuelve un medico por codigo.
     */
    public Medico getMedicoPorCod(int codMed) {
        Medico med = null;
        Medico nuevoMedAux = new Medico(codMed);
        if (listaMedicos.existeElemento(nuevoMedAux)) {
            med = listaMedicos.obtenerElemento(nuevoMedAux);
        }
        return med;
    }

    /*
        Metodo auxiliar: Devuelve un paciente por codigo.
     */
    public Paciente getPacientePorCod(int codPac) {
        Paciente pac = null;
        Paciente nuevoPacienteAux = new Paciente(codPac);
        if (listaPacientes.existeElemento(nuevoPacienteAux)) {
            pac = listaPacientes.obtenerElemento(nuevoPacienteAux);
        }
        return pac;
    }

    /*Operación 2.8*/
    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (!existeCi(CIPaciente)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else if (!existeReservaConMedico(CIPaciente, codMedico)) {
            ret = new Retorno(Retorno.Resultado.ERROR_2);
        } else {
            Medico medBuscado = new Medico(codMedico);
            Paciente pacBuscado = new Paciente(CIPaciente);
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            ConsultaMedica consultaAux = new ConsultaMedica(medEnLista, pacEnLista);
            ConsultaMedica consultaEnLista = pacEnLista.getAgendas().obtenerElemento(consultaAux);
            if (consultaEnLista.getEstado() == "Pendiente") {
                consultaEnLista.setEstado("En espera");
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*
        Metodo auxiliar: Verifica si un paciente con ese CI tiene una consulta con un medico con ese codigo.
     */
    private boolean existeReservaConMedico(int CIPaciente, int codMed) {
        boolean retorno = false;
        Medico medBuscado = new Medico(codMed);
        Paciente pacBuscado = new Paciente(CIPaciente);
        Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
        Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
        ConsultaMedica consultaAux = new ConsultaMedica(medEnLista, pacEnLista);
        if (pacEnLista.getAgendas().existeElemento(consultaAux)) {
            retorno = true;
        }
        return retorno;
    }

    /*Operación 2.9*/
    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (!existeCi(CIPaciente)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Paciente pacBuscado = new Paciente(CIPaciente);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            Medico medBuscado = new Medico(codMedico);
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            ConsultaMedica consultaAux = new ConsultaMedica(medEnLista, pacEnLista);
            ConsultaMedica consultaEnLista = pacEnLista.getAgendas().obtenerElemento(consultaAux);
            Date fechaActual = new Date();

            if (consultaEnLista.getEstado() != "En espera" && consultaEnLista.getFechaConsulta() != fechaActual) {
                ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                consultaEnLista.setDetalle(detalleDeConsulta);
                consultaEnLista.setEstado("Terminada");
                pacEnLista.getHistorial().agregarOrdenado(consultaEnLista);
                pacEnLista.getAgendas().eliminarElemento(consultaAux);
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*Operación 2.10*/
    @Override
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        int codMedicoInt = Integer.parseInt(codMédico); //Me llega en String, y ya que no puedo modificar la firma, lo parseo manual.

        if (!existeCod(codMedicoInt)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Medico medBuscado = new Medico(codMedicoInt);
            Medico medEnLista = listaMedicos.obtenerElemento(medBuscado);
            ConsultaMedica consultaAux = new ConsultaMedica(medEnLista, fechaConsulta);
            ReservaFecha reservaAux = new ReservaFecha(codMedicoInt, fechaConsulta);
            Date today = new Date();
            if (medEnLista.getConsultas().obtenerElemento(reservaAux).getConsultasEnLaFecha().esVacia()) {
                ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                ReservaFecha reservaDelDia = medEnLista.getConsultas().obtenerElemento(reservaAux);
                Nodo<ConsultaMedica> nodoAux = reservaDelDia.getConsultasEnLaFecha().getInicio();
                while (nodoAux != null) {
                    ConsultaMedica unaConsulta = nodoAux.getDato();
                    if (unaConsulta.getEstado() == "Pendiente") {
                        unaConsulta.setEstado("No asistió");
                    }
                    Paciente pacConsulta = unaConsulta.getPacienteConsulta();
                    pacConsulta.getHistorial().agregarOrdenado(unaConsulta);
                    pacConsulta.getAgendas().eliminarElemento(consultaAux);
                    nodoAux = nodoAux.getSiguiente();
                }
                ret = new Retorno(Retorno.Resultado.OK);
            }
        }
        return ret;
    }

    /*Operación 3.1*/
    @Override
    public Retorno listarMédicos() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        listaMedicos.mostrar();
        return ret;
    }

    /*Operación 3.2*/
    @Override
    public Retorno listarPacientes() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        listaPacientes.mostrar();
        return ret;
    }

    /*Operación 3.3*/
    @Override
    public Retorno listarConsultas(int codMédico) {
        Retorno ret;

        if (!existeCod(codMédico)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Medico nuevoMed = new Medico(codMédico);
            Medico MedEnLista = listaMedicos.obtenerElemento(nuevoMed);
            listarConsultasRec(MedEnLista.getConsultas().getInicio());
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /* METODO RECURSIVO 1 UTILIZADO PARA LISTARCONSULTAS(3.3) */
    private void listarConsultasRec(Nodo<ReservaFecha> unNodo) {
        listarConsultasRecursivo(unNodo);
    }

    /* METODO RECURSIVO 2 UTILIZADO PARA LISTARCONSULTAS(3.3) */
    private void listarConsultasRecursivo(Nodo<ReservaFecha> unNodo) {
        if (unNodo != null) {
            ReservaFecha reserva = unNodo.getDato();

            System.out.println("Consultas del dia: " + reserva.getFechaConsultas().toString());
            Lista<ConsultaMedica> consultas = reserva.getConsultasEnLaFecha();
            Nodo<ConsultaMedica> nodoConsulta = consultas.getInicio();
            while (nodoConsulta != null) {
                ConsultaMedica consulta = nodoConsulta.getDato();
                System.out.println(consulta);
                nodoConsulta = nodoConsulta.getSiguiente();
            }

            listarConsultasRec(unNodo.getSiguiente());
        }
    }

    /*Operación 3.4*/
    @Override
    public Retorno listarPacientesEnEspera(String codMédico, Date fecha) {
        Retorno ret;
        if (!tieneConsultaEsaFecha(codMédico, fecha)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Lista<Paciente> pacientesEnEspera = obtenerPacientesEnEspera(codMédico, fecha);
            pacientesEnEspera.mostrar();
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /*
        Metodo auxiliar: Verifica si existen consultas en esa fecha para ese medico.
     */
    private boolean tieneConsultaEsaFecha(String codMed, Date fecha) {
        int codMedicoInt = Integer.parseInt(codMed);
        boolean ret = false;
        Medico nuevoMed = new Medico(codMedicoInt);
        Medico MedEnLista = listaMedicos.obtenerElemento(nuevoMed);
        Nodo<ReservaFecha> nodoAux = MedEnLista.getConsultas().getInicio();
        while (nodoAux != null) {
            if (nodoAux.getDato().getFechaConsultas() == fecha && nodoAux.getDato().getCodMed() == codMedicoInt) {
                ret = true;
                return ret;
            }
            nodoAux = nodoAux.getSiguiente();
        }
        return ret;
    }

    /*
        Metodo auxiliar: Devuelve los pacientes en espera en esa fecha con ese medico.
     */
    private Lista<Paciente> obtenerPacientesEnEspera(String codMed, Date fecha) {
        Lista<Paciente> retorno = new Lista<Paciente>();
        int codMedicoInt = Integer.parseInt(codMed);
        Medico nuevoMed = new Medico(codMedicoInt);
        Medico MedEnLista = listaMedicos.obtenerElemento(nuevoMed);
        ReservaFecha reservaABuscar = new ReservaFecha(codMedicoInt, fecha);
        ReservaFecha reservaEnLista = MedEnLista.getConsultas().obtenerElemento(reservaABuscar);
        Nodo<ConsultaMedica> consultaAux = reservaEnLista.getConsultasEnLaFecha().getInicio();
        while (consultaAux != null) {
            if (consultaAux.getDato().getEstado() == "En espera") {
                retorno.agregarFinal(consultaAux.getDato().getPacienteConsulta());
            }
            consultaAux = consultaAux.getSiguiente();
        }
        return retorno;
    }

    /*Operación 3.5*/
    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        Retorno ret;
        if (!existeCi(CIPaciente)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Paciente pacBuscado = new Paciente(CIPaciente);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            mostrarConsultasPendientes(pacEnLista.getAgendas().getInicio());
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /* METODO RECURSIVO 1 UTILIZADO PARA consultasPendientesPaciente(3.5) */
    private void mostrarConsultasPendientes(Nodo<ConsultaMedica> unNodo) {
        mostrarConsultasPendientesRecursivo(unNodo);
    }

    /* METODO RECURSIVO 2 UTILIZADO PARA consultasPendientesPaciente(3.5) */
    private void mostrarConsultasPendientesRecursivo(Nodo<ConsultaMedica> unNodo) {
        if (unNodo != null) {
            ConsultaMedica consulta = unNodo.getDato();
            if (consulta.getEstado() == "Pendiente") {
                System.out.println(consulta);
            }
            mostrarConsultasPendientes(unNodo.getSiguiente());
        }
    }

    /*Operación 3.6*/
    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        Retorno ret;
        if (!existeCi(ci)) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            Paciente pacBuscado = new Paciente(ci);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);
            mostrarHistoriaClinica(pacEnLista.getHistorial().getInicio());
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /* METODO RECURSIVO 1 UTILIZADO PARA historiaClínicaPaciente(3.6) */
    private void mostrarHistoriaClinica(Nodo<ConsultaMedica> nodo) {
        mostrarHistoriaClinicaRecursiva(nodo);
    }

    /* METODO RECURSIVO 2 UTILIZADO PARA historiaClínicaPaciente(3.6) */
    private void mostrarHistoriaClinicaRecursiva(Nodo<ConsultaMedica> nodo) {
        if (nodo != null) {
            ConsultaMedica consulta = nodo.getDato();
            System.out.println(consulta);
            mostrarHistoriaClinica(nodo.getSiguiente());
        }
    }

    /*Operación 3.7*/
    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        Retorno ret;
        if (mes <= 0 || mes > 12 || año < 2020 || año > 2023) {
            ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {

            //Creamos la matriz.
            int[][] mat = new int[getCantDiasMes(mes)][20];

            //Ponemos en 0 cada indice.
            for (int i = 0; i < getCantDiasMes(mes); i++) {
                for (int j = 0; j < 20; j++) {
                    mat[i][j] = 0;
                }
            }

            //Este método se encargará de acceder a cada consulta y comprobar los datos para generar el informe.
            LlenarMatriz(mat, mes, año);

            //Generamos el informe a partir de la matriz.
            String informe = "Informe de Pacientes Atendidos por Especialidad en el mes " + mes + " del año " + año + ":\n";
            for (int dia = 0; dia < getCantDiasMes(mes); dia++) {
                informe += "Fecha: " + (dia + 1) + "/" + mes + "/" + año + "\n";
                for (int especialidad = 0; especialidad < 20; especialidad++) {
                    informe += "  Especialidad: " + (especialidad + 1) + ", Pacientes Atendidos: " + mat[dia][especialidad] + "\n";
                }
            }

            //Mostramos el informe en consola.
            System.out.println(informe);
            obtenerMatriz(mes, año);
            ret = new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    /*
    Argumentos: Matriz, int mes, int año.
    Devuelve: void.
    Funcionamiento: Accede a cada una de las fechas registradas para crear consultas y las recorre.
                    Primero preguntamos si es en el mismo mes y en el mismo año para no recorrer de forma innecesaria.
                    Una vez hecho eso se vuelca en la matriz, quedando cargada con los datos necesarios.
     */
    private void LlenarMatriz(int[][] mat, int mes, int año) {
        Nodo<ReservaFecha> unNodo = listaTotalConsultas.getInicio();
        while (unNodo != null) {
            ReservaFecha r = unNodo.getDato();
            Date fechaReserva = r.getFechaConsultas();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaReserva);
            int mesReserva = cal.get(Calendar.MONTH) + 1;
            int añoReserva = cal.get(Calendar.YEAR);

            if (mesReserva == mes && añoReserva == año) {

                Nodo<ConsultaMedica> NodoConsultaMedica = unNodo.getDato().getConsultasEnLaFecha().getInicio();
                while (NodoConsultaMedica != null) {
                    if (NodoConsultaMedica.getDato().getEstado() == "Terminada") {
                        int dia = cal.get(Calendar.DAY_OF_MONTH) - 1; // Restar 1 para ajustar al índice de matriz
                        int especialidad = NodoConsultaMedica.getDato().getDoctorConsulta().getEspecialidad() - 1; // Implementa este método

                        // Actualizar la matriz con la cantidad de pacientes atendidos
                        mat[dia][especialidad]++;
                    }

                    NodoConsultaMedica = NodoConsultaMedica.getSiguiente();
                }
            }

            unNodo = unNodo.getSiguiente();
        }
    }

    /*
        Método auxiliar: Hay meses con diferentes cantidad de días, para ello se usa este método.
     */
    public int getCantDiasMes(int mes) {
        int cant = -1;
        if (mes == 1) {
            cant = 31;
        } else if (mes == 2) {
            cant = 28;
        } else if (mes == 3) {
            cant = 31;
        } else if (mes == 4) {
            cant = 30;
        } else if (mes == 5) {
            cant = 31;
        } else if (mes == 6) {
            cant = 30;
        } else if (mes == 7) {
            cant = 31;
        } else if (mes == 8) {
            cant = 31;
        } else if (mes == 9) {
            cant = 30;
        } else if (mes == 10) {
            cant = 31;
        } else if (mes == 11) {
            cant = 30;
        } else if (mes == 12) {
            cant = 31;
        }

        return cant;
    }

    /*
        Método auxiliar: Utilizado para confirmar que las lista de consultas y la cola de espera funcionan bien.
     */
    public void VerListaEnFecha(int codMed, Date f) {
        Medico nuevoMed = new Medico(codMed);
        Medico MedEnLista = listaMedicos.obtenerElemento(nuevoMed);
        ReservaFecha r = new ReservaFecha(f);
        ReservaFecha ReservaDef = MedEnLista.getConsultas().obtenerElemento(r);
        ReservaDef.getConsultasEnLaFecha().mostrar();
        System.out.println("*_*_*_**_*_*__*_*_*_");
        ReservaDef.getListaEspera().mostrar();
    }

    /*
        Metodo auxiliar: Utilizado para mostrar si se muestra correctamente las reservas de la fecha.
        Utilidad simplemente para testing.
     */
    public void MostrarReservaFecha(Date f, int codMed) {
        Medico nuevoMed = new Medico(codMed);
        Medico MedEnLista = listaMedicos.obtenerElemento(nuevoMed);
        ReservaFecha r = new ReservaFecha(f);
        ReservaFecha ReservaDef = MedEnLista.getConsultas().obtenerElemento(r);

        System.out.println("---------------------------------");

        System.out.println(f);
        System.out.println(MedEnLista.getNombre());
        System.out.println("/././././././././././");
        ReservaDef.getConsultasEnLaFecha().mostrar();
        System.out.println("/././././././././././");
        ReservaDef.getListaEspera().mostrar();
        System.out.println("/././././././././././");

        System.out.println("---------------------------------");
    }

    /*
    Preparación de la defensa de AED1.
     */
    //Mostrar las consultas que un paciente no asistió.
    //ITERATIVA
    public void MostrarNoAsistidos(int ci) {
        if (existeCi(ci)) {
            Paciente pacBuscado = new Paciente(ci);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);

            Nodo<ConsultaMedica> unNodo = pacEnLista.getHistorial().getInicio();
            while (unNodo != null) {
                ConsultaMedica c = unNodo.getDato();
                if (c.getEstado() == "No asistió") {
                    System.out.println(c);
                }
                unNodo = unNodo.getSiguiente();
            }

        }
    }

    //RECURSIVA
    public void MostrarNoAsistidosRec(int ci) {
        if (existeCi(ci)) {
            Paciente pacBuscado = new Paciente(ci);
            Paciente pacEnLista = listaPacientes.obtenerElemento(pacBuscado);

            noAsistidasRec(pacEnLista.getHistorial().getInicio());
        }
    }

    private void noAsistidasRec(Nodo<ConsultaMedica> unNodo) {
        mostrarRecNoAsistidas(unNodo);
    }

    private void mostrarRecNoAsistidas(Nodo<ConsultaMedica> unNodo) {
        if (unNodo != null) {
            System.out.println(unNodo.getDato());
            noAsistidasRec(unNodo.getSiguiente());
        }
    }

    private int[][] obtenerMatriz(int mes, int año) {
        int[][] matriz = new int[getCantDiasMes(mes)][20];

        for (int i = 0; i < getCantDiasMes(mes); i++) {
            for (int j = 0; j < 20; j++) {
                matriz[i][j] = 0;
            }
        }

        LlenarMatriz(matriz, mes, año);
        return matriz;
    }

    //Buscar en la matriz los datos de una sola fecha.
    public void MostrarFechaMatriz(int dia, int mes, int año) {
        int[][] matriz = obtenerMatriz(mes, año);

        for (int i = 0; i < getCantDiasMes(mes); i++) {
            if (i + 1 == dia + 1) {
                System.out.println(matriz[dia-1][5-1]);

            }
        }
    }

    
    
    //Mostrar todas las consultas terminadas de un médico.
    public void MostrarConsultasTerPorCodMed(int codMed){
        if(existeCod(codMed)){
            Medico medAux = new Medico(codMed);
            Medico medEnLista = listaMedicos.obtenerElemento(medAux);
            
            Nodo<ReservaFecha> unNodo = listaTotalConsultas.getInicio();
            while(unNodo != null){
                if(unNodo.getDato().getCodMed() == codMed){
                    ReservaFecha res = unNodo.getDato();
                    Lista<ConsultaMedica> terminadas = ObtenerTerminadas(res);
                    terminadas.mostrar();
                }
                unNodo = unNodo.getSiguiente();
            }
        }
    }
    
    
    private Lista<ConsultaMedica> ObtenerTerminadas(ReservaFecha res){
        Lista<ConsultaMedica> ret = new Lista<ConsultaMedica>();       
        Nodo<ConsultaMedica> unNodo = res.getConsultasEnLaFecha().getInicio();
        while(unNodo != null){           
            ConsultaMedica consulta = unNodo.getDato();
            if(consulta.getEstado() == "Terminada"){
                ret.agregarFinal(consulta);
            }
            unNodo = unNodo.getSiguiente();
        }       
        return ret;    
    }
    
    
    
}
