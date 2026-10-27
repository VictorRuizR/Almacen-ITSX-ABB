package Base_de_Datos_Almacen;

public class ArbBinBusq {
    private Nodo raiz;

    public ArbBinBusq() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // ==========================================================================
    // 1. INSERCIÓN: devuelve true si se insertó, false si el producto ya existía.
    // ==========================================================================
    public boolean insertar(String nombre, int stock, double precio) {
        if (buscar(nombre) != null) {
            return false; // Evitamos duplicados
        }
        this.raiz = insertarRecursivo(this.raiz, nombre, stock, precio);
        return true;
    }

    private Nodo insertarRecursivo(Nodo actual, String nombre, int stock, double precio) {
        // CASO BASE: hueco vacío, aquí se crea el nodo
        if (actual == null) {
            return new Nodo(nombre, stock, precio);
        }

        int comparacion = nombre.compareTo(actual.nombre);

        if (comparacion < 0) {          // Alfabéticamente menor -> izquierda
            actual.izq = insertarRecursivo(actual.izq, nombre, stock, precio);
        } else if (comparacion > 0) {   // Alfabéticamente mayor -> derecha
            actual.der = insertarRecursivo(actual.der, nombre, stock, precio);
        }
        // comparacion == 0 -> ya existe, no se hace nada
        return actual;
    }

    // ==========================================================================
    // 2. BÚSQUEDA (Divide y Vencerás, O(log n)): devuelve el nodo o null.
    // ==========================================================================
    public Nodo buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Nodo buscarRecursivo(Nodo actual, String nombre) {
        if (actual == null) {
            return null; // No existe
        }

        int comparacion = nombre.compareTo(actual.nombre);

        if (comparacion == 0) {
            return actual; // ¡Encontrado!
        }
        if (comparacion < 0) {
            return buscarRecursivo(actual.izq, nombre);
        }
        return buscarRecursivo(actual.der, nombre);
    }

    // ==========================================================================
    // 3. ELIMINACIÓN: devuelve true si se eliminó, false si no existía.
    // ==========================================================================
    public boolean eliminar(String nombre) {
        if (buscar(nombre) == null) {
            return false;
        }
        this.raiz = eliminarRecursivo(this.raiz, nombre);
        return true;
    }

    private Nodo eliminarRecursivo(Nodo actual, String nombre) {
        if (actual == null) return null;

        int comparacion = nombre.compareTo(actual.nombre);

        if (comparacion < 0) {
            actual.izq = eliminarRecursivo(actual.izq, nombre);
        } else if (comparacion > 0) {
            actual.der = eliminarRecursivo(actual.der, nombre);
        } else {
            // CASO 1: HOJA (0 hijos)
            if (actual.izq == null && actual.der == null) {
                return null;
            }

            // CASO 2: UN SOLO HIJO
            if (actual.izq == null) return actual.der;
            if (actual.der == null) return actual.izq;

            // CASO 3: DOS HIJOS -> sucesor inorden (1 a la derecha, todo a la izquierda)
            Nodo sucesor = obtenerMinimo(actual.der);
            // Copiamos TODOS los datos del producto sucesor al nodo actual
            actual.nombre = sucesor.nombre;
            actual.stock = sucesor.stock;
            actual.precio = sucesor.precio;
            // Borramos el sucesor original de la rama derecha
            actual.der = eliminarRecursivo(actual.der, sucesor.nombre);
        }
        return actual;
    }

    private Nodo obtenerMinimo(Nodo actual) {
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

    // ==========================================================================
    // 4. RECORRIDO INORDER: muestra el inventario ordenado alfabéticamente.
    // ==========================================================================
    public void imprimirInOrder() {
        System.out.printf("  %-25s %10s %14s%n", "PRODUCTO", "STOCK", "PRECIO");
        System.out.println("  " + "-".repeat(51));
        imprimirRec(raiz);
    }

    private void imprimirRec(Nodo actual) {
        if (actual != null) {
            imprimirRec(actual.izq);
            System.out.printf("  %-25s %10d %14s%n",
                    actual.nombre, actual.stock, String.format("$%.2f", actual.precio));
            imprimirRec(actual.der);
        }
    }
}
