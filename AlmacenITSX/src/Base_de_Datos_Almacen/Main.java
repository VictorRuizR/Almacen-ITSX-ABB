// Main.java
package Base_de_Datos_Almacen;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArbBinBusq inventario = new ArbBinBusq();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("  Elige una opción (1-5): ");
            System.out.println();

            switch (opcion) {
                case 1:
                    registrarProducto(inventario);
                    break;
                case 2:
                    buscarProducto(inventario);
                    break;
                case 3:
                    eliminarProducto(inventario);
                    break;
                case 4:
                    mostrarInventario(inventario);
                    break;
                case 0:
                case 5:
                    System.out.println("  Saliendo del sistema... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("  Opción inválida. Elige un número del 1 al 5.");
            }
        } while (opcion != 5);
    }

    // ------------------------------------------------------------------
    // MENÚ
    // ------------------------------------------------------------------
    static void mostrarMenu() {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("                ALMACÉN ITSX               ");
        System.out.println("  ==========================================");
        System.out.println("   1. Registrar / Insertar nuevo producto");
        System.out.println("   2. Buscar producto por nombre");
        System.out.println("   3. Dar de baja / Eliminar producto");
        System.out.println("   4. Mostrar inventario completo (InOrder)");
        System.out.println("   0 o 5. Salir");
        System.out.println("  ==========================================");
    }

    // ------------------------------------------------------------------
    // OPCIONES
    // ------------------------------------------------------------------
    static void registrarProducto(ArbBinBusq inventario) {
        System.out.println("  --- REGISTRAR PRODUCTO (Pulsa 0 para salir) --- ");
        String nombre = leerNombre("  Nombre del producto: ");
        if (nombre.equals("0") || nombre.isEmpty()) {
            System.out.println(" Operación Cancelada. Volviendo al menú ...");
            return;
        }
        if (inventario.buscar(nombre) != null) {
            System.out.println("  El producto \"" + nombre + "\" ya existe en el inventario.");
            return;
        }
        int stock = leerEntero("  Stock (cantidad) [0 para cancelar]: ");
        if (stock == 0) {
            System.out.print("Operación cancelada. Volviendo al menu ...");
            return;
        }

        double precio = leerDouble("  Precio unitario: $");
        if (precio == 0){
            System.out.println("Operacion canelada. Volviendo al menú ...");
        }

        if (stock < 0 || precio < 0) {
            System.out.println("  El stock y el precio no pueden ser negativos.");
            return;
        }

        inventario.insertar(nombre, stock, precio);
        System.out.println("  Producto \"" + nombre + "\" registrado correctamente.");
    }

    static void buscarProducto(ArbBinBusq inventario) {
        System.out.println("  --- BUSCAR PRODUCTO (Pulsa 0 para cancelar)---");
        String nombre = leerNombre("  Nombre del producto: ");
        if (nombre.equals("0") || nombre.isEmpty()) {
            System.out.println("Operacion canelada. Volviendo al menú ...");
        }

        Nodo encontrado = inventario.buscar(nombre);

        if (encontrado == null) {
            System.out.println("  El producto \"" + nombre + "\" no existe.");
        } else {
            System.out.println("  Producto encontrado:");
            System.out.println("    Nombre : " + encontrado.nombre);
            System.out.println("    Stock  : " + encontrado.stock);
            System.out.printf("    Precio : $%.2f%n", encontrado.precio);
        }
    }

    static void eliminarProducto(ArbBinBusq inventario) {
        System.out.println("  --- ELIMINAR PRODUCTO (Pulsa 0 para cancelar) ---");
        String nombre = leerNombre("  Nombre del producto a dar de baja: ");
        if (nombre.equals("0") || nombre.isEmpty()) {
            System.out.println("Operacion cancelada. Volviendo al menú ...");
        }

        if (inventario.eliminar(nombre)) {
            System.out.println("  Producto \"" + nombre + "\" eliminado correctamente.");
        } else {
            System.out.println("  El producto \"" + nombre + "\" no existe.");
        }
    }

    static void mostrarInventario(ArbBinBusq inventario) {
        System.out.println("  --- INVENTARIO (orden alfabético) ---");
        if (inventario.estaVacio()) {
            System.out.println("  El inventario está vacío.");
        } else {
            inventario.imprimirInOrder();
        }
    }

    // ------------------------------------------------------------------
    // LECTURA SEGURA DE DATOS
    // ------------------------------------------------------------------

    // Normaliza el nombre: sin espacios sobrantes y con formato.
    // Por ejemplo si se ingresará el producto "Playeras".
    // Así "playeras", "PLAYERAS" y " Playeras " cuentan como el mismo producto
    // y compareTo() ordena bien (las mayúsculas van antes que las minúsculas en Unicode).
    static String leerNombre(String mensaje) {
        System.out.print(mensaje);
        String texto = sc.nextLine().trim();
        if (texto.isEmpty() || texto.equals("0")) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa un número entero válido.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa un número válido (ej. 149.90).");
            }
        }
    }
}
