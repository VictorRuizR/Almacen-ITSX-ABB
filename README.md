# Árbol Binario de Búsqueda (ABB) en Java - Almacén ITSX

Implementación de un sistema de gestión de inventarios para un almacén basado en la estructura de datos **Árbol Binario de Búsqueda** (**ABB**). Los productos se ordenan alfabéticamente por su nombre, garantizando búsquedas, inserciones y eliminaciones eficientes mediante el enfoque de *Divide y Vencerás*.

**Alumnos:** Vann Lucca Mata Hernandez, Victor Ruiz Ruiz, Marco Antonio Pavia Flores  
**Materia:** AED-1026 Estructura de Datos
**Institución:** Instituto Tecnológico Superior de Xalapa  

---

## Lenguaje de programación

**Java** (JDK 17 o superior recomendado).

---

## Estructura del proyecto

El proyecto está organizado bajo el paquete `Base_de_Datos_Almacen`:

```text
Almacen-ITSX-ABB/
└── AlmacenITSX/
    └── src/
        └── Base_de_Datos_Almacen/
            ├── Nodo.java         # Estructura del nodo (producto) con punteros izq/der.
            ├── ArbBinBusq.java   # Operaciones del ABB (insertar, buscar, eliminar, recorrido).
            └── Main.java         # Interfaz de usuario por consola y menú interactivo.
```

| Archivo | Descripción |
|---|---|
| `Nodo.java` | Representa un producto del almacén con atributos `nombre`, `stock` y `precio`, además de las referencias `izq` y `der`. |
| `ArbBinBusq.java` | Lógica del árbol binario de búsqueda: `insertar`, `buscar`, `eliminar`, `imprimirInOrder` y `estaVacio`. |
| `Main.java` | Clase principal interactiva que gestiona el menú del sistema y la lectura segura de datos. |

---

## Conceptos clave del ABB

| Concepto | Descripción |
|---|---|
| **Estructura** | Cada nodo almacena un producto y dos referencias (`izq` y `der`). |
| **Criterio de orden** | La clave de ordenamiento es el `nombre` del producto (orden alfabético Unicode con `compareTo`). |
| **Subárbol izquierdo** | Contiene productos cuyos nombres son alfabéticamente menores que el nodo actual. |
| **Subárbol derecho** | Contiene productos cuyos nombres son alfabéticamente mayores que el nodo actual. |
| **Complejidad** | Búsqueda, inserción y eliminación con complejidad promedio de $\mathcal{O}(\log n)$. |
| **InOrder** | Recorrido que visita `Izquierda -> Raíz -> Derecha`, lo que permite listar el inventario en orden alfabético estricto. |

---

## Métodos principales del sistema

- `insertar(nombre, stock, precio)`: Agrega un nuevo nodo respetando el orden alfabético. Evita nombres duplicados.
- `buscar(nombre)`: Recorre recursivamente el árbol hacia la izquierda o derecha según la comparación del nombre.
- `eliminar(nombre)`: Elimina un nodo manejando los 3 casos estándar (hoja, 1 hijo, o 2 hijos utilizando el sucesor inorden).
- `imprimirInOrder()`: Imprime en formato de tabla alineada todo el inventario ordenado de la A a la Z.

---

## Cómo compilar y ejecutar

### Desde la terminal

1. Clona el repositorio (o descarga los archivos en tu equipo):
   ```bash
   git clone https://github.com/VictorRuizR/Almacen-ITSX-ABB.git
   cd Almacen-ITSX-ABB/AlmacenITSX/src
   ```
2. Compila el paquete completo:
   ```bash
   javac Base_de_Datos_Almacen/*.java
   ```
3. Ejecuta la aplicación:
   ```bash
   java Base_de_Datos_Almacen.Main
   ```

### Desde IntelliJ IDEA

1. Abre el proyecto seleccionando la carpeta `AlmacenITSX`.
2. Navega a `src/Base_de_Datos_Almacen/Main.java`.
3. Haz clic en el botón verde ▶ (Run) junto al método `main`.

---

## Ejemplo de ejecución e interfaz del sistema

```text
  ==========================================
                ALMACÉN ITSX               
  ==========================================
   1. Registrar / Insertar nuevo producto
   2. Buscar producto por nombre
   3. Dar de baja / Eliminar producto
   4. Mostrar inventario completo (InOrder)
   0 o 5. Salir
  ==========================================
  Elige una opción (1-5): 1

  --- REGISTRAR PRODUCTO (Pulsa 0 para salir) --- 
  Nombre del producto: libretas
  Stock (cantidad) [0 para cancelar]: 50
  Precio unitario: $12.50
  Producto "Libretas" registrado correctamente.

  ==========================================
                ALMACÉN ITSX               
  ==========================================
   1. Registrar / Insertar nuevo producto
   2. Buscar producto por nombre
   3. Dar de baja / Eliminar producto
   4. Mostrar inventario completo (InOrder)
   0 o 5. Salir
  ==========================================
  Elige una opción (1-5): 1

  --- REGISTRAR PRODUCTO (Pulsa 0 para salir) --- 
  Nombre del producto: Lápices
  Stock (cantidad) [0 para cancelar]: 120
  Precio unitario: $5.00
  Producto "Lápices" registrado correctamente.

  ==========================================
                ALMACÉN ITSX               
  ==========================================
   1. Registrar / Insertar nuevo producto
   2. Buscar producto por nombre
   3. Dar de baja / Eliminar producto
   4. Mostrar inventario completo (InOrder)
   0 o 5. Salir
  ==========================================
  Elige una opción (1-5): 4

  --- INVENTARIO (orden alfabético) ---
  PRODUCTO                      STOCK         PRECIO
  ---------------------------------------------------
  Libretas                         50         $12.50
  Lápices                         120          $5.00

  ==========================================
                ALMACÉN ITSX               
  ==========================================
   1. Registrar / Insertar nuevo producto
   2. Buscar producto por nombre
   3. Dar de baja / Eliminar producto
   4. Mostrar inventario completo (InOrder)
   0 o 5. Salir
  ==========================================
  Elige una opción (1-5): 2

  --- BUSCAR PRODUCTO (Pulsa 0 para cancelar)---
  Nombre del producto: libretas
  Producto encontrado:
    Nombre : Libretas
    Stock  : 50
    Precio : $12.50

  ==========================================
                ALMACÉN ITSX               
  ==========================================
   1. Registrar / Insertar nuevo producto
   2. Buscar producto por nombre
   3. Dar de baja / Eliminar producto
   4. Mostrar inventario completo (InOrder)
   0 o 5. Salir
  ==========================================
  Elige una opción (1-5): 5

  Saliendo del sistema... ¡Hasta pronto!
```