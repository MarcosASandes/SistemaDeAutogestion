package tads;

import classes.Medico;
import classes.Paciente;

public class Lista<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public Lista() {
        cantElementos = 0;
        inicio = null;
        fin = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    @Override
    public void vaciar() {
        this.inicio = null;
        this.fin = null;
        cantElementos = 0;
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
            cantElementos--;
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {

                Nodo actual = inicio;

                while (actual.getSiguiente().getSiguiente() != null) {
                    actual = actual.getSiguiente();

                }
                actual.setSiguiente(null);
                cantElementos--;
            }
        }
    }

    @Override
    public int cantidadElementos() {
        return cantElementos;
    }

    @Override
    public void agregarFinal(T x) {
        Nodo nuevo = new Nodo(x);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);

        }
        cantElementos++;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        cantElementos++;
    }

    @Override
    public void mostrar() {
        Nodo mostrar = inicio;

        while (mostrar != null) {
            System.out.println(mostrar.getDato().toString());
            mostrar = mostrar.getSiguiente();
        }
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;
        Nodo aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }
    
    
    public T obtenerElemento(T dato) {
        Nodo<T> nodoNuevo = getInicio();
        boolean encontre = false;
        while (!encontre && nodoNuevo != null) {

            if (nodoNuevo.getDato().equals(dato)) {
                encontre = true;
            } else {
                nodoNuevo = nodoNuevo.getSiguiente();
            }
        }
        return nodoNuevo.getDato();
    }


    @Override
    public void eliminarElemento(T x) {

        if (!esVacia()) {
            if (inicio.getDato().equals(x)) {
                this.eliminarInicio();
            } else {

                Nodo aux = inicio;

                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(x)) {
                    aux = aux.getSiguiente();
                }

                if (aux.getSiguiente() != null) { //Enbcontre el elemento
                    Nodo aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantElementos--;

                }
            }

        }

    }

    @Override
    public void agregarOrdenado(T x) {

        if (esVacia() || inicio.getDato().compareTo(x) >= 0) {
            this.agregarInicio(x);
        } else {

            Nodo aux = inicio;

            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(x) < 0) {
                aux = aux.getSiguiente();
            }

            if (aux.getSiguiente() == null) {
                this.agregarFinal(x);
            } else {

                Nodo nuevo = new Nodo(x);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantElementos++;

            }

        }

    }

}
