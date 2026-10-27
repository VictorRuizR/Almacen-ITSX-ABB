// Nodo.java

package Base_de_Datos_Almacen;

// Cada nodo del árbol ES un producto del almacén.
public class Nodo {
    String nombre;  // Llave alfabética única (rige el orden del ArbBinBusq)
    int stock;      // Cantidad disponible en el almacén
    double precio;  // Costo unitario
    Nodo izq;       // Rama izquierda (nombres que van antes en el alfabeto)
    Nodo der;       // Rama derecha (nombres que van después en el alfabeto)

    public Nodo(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.izq = null;
        this.der = null;
    }
}
