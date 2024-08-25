
package tads;


public interface ILista<T extends Comparable<T>> {
    
    /*
    Pre: - x != null.
    Pos: - Devuelve un boolean sobre la existencia del elemento.
    */
    public boolean existeElemento (T x);
    
    /*
    Pre: - x != null.
    Pos: - Elimina el elemento, si existe de la lista. (Elimina el nodo también)
    */
    public void eliminarElemento (T x);
    
    /*
    Pre: /
    Pos: - Devuelve un int con la cantidad de elementos en la lista.
    */
    public int cantidadElementos ();

    /*
    Pre: - x != null.
    Pos: - Agrega el elemento al final de la lista.
    */
    public void agregarFinal (T x); 
    
     /*
    Pre: /
    Pos: Devuelve un boolean sobre si la lista es vacía o no.
    */
    public boolean esVacia();
    
    /*
    Pre: - x != null.
    Pos: - Agrega el elemento al inicio de la lista.
    */
    public void agregarInicio (T x);
    
    /*
    Pre: /
    Pos: - Muestra los elementos existentes de la lista.
    */
    public void mostrar(); 
    
    /*
    Pre: /
    Pos: - Vacía la lista.
    */
    public void vaciar();
    
    /*
    Pre: /
    Pos: - Elimina el primer elemento de la lista.
    */
    public void eliminarInicio();
    
    /*
    Pre: /
    Pos: - Elimina el último elemento de la lista.
    */
    public void eliminarFinal();
    
    /*
    Pre: - x != null.
    Pos: - Agrega de manera ordenada según CompareTo el elemento en la lista.
    */
    public void agregarOrdenado(T x);
    
}
