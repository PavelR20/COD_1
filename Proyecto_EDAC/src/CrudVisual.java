import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

// Clase principal que representa la interfaz gráfica CRUD
public class CrudVisual extends JFrame {
    private GrafoNoDirigido grafo; // Grafo utilizado para almacenar las ubicaciones y las aristas
    private Set<String> nombresUbicaciones; // Conjunto para almacenar los nombres de las ubicaciones

    // Constructor de la clase CrudVisual
    public CrudVisual() {
        setTitle("CRUD Visual"); // Título de la ventana
        setSize(800, 600); // Tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación cuando se cierra la ventana

        grafo = new GrafoNoDirigido(); // Inicializa el grafo
        nombresUbicaciones = new HashSet<>(); // Inicializa el conjunto de nombres de ubicaciones

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Diseño de cuadrícula para los botones

        // Botón para agregar una ubicación
        JButton btnAgregarUbicacion = new JButton("Agregar Ubicación");
        customizeButton(btnAgregarUbicacion); // Personaliza el botón
        btnAgregarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre un cuadro de diálogo para ingresar el nombre de la ubicación
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.agregarUbicacion(new Ubicacion(nombre)); // Agrega la ubicación al grafo
                    nombresUbicaciones.add(nombre); // Agrega el nombre al conjunto
                    JOptionPane.showMessageDialog(null, "Ubicación agregada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });
        panel.add(btnAgregarUbicacion); // Agrega el botón al panel

        // Botón para agregar una arista con peso y tiempo
        JButton btnAgregarArista = new JButton("Agregar Arista con Peso y Tiempo");
        customizeButton(btnAgregarArista);
        btnAgregarArista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario los nombres de origen y destino, peso y tiempo de la arista
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay ubicaciones agregadas para asignarle un peso.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen válido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino válido.");
                    return;
                }
                int peso, tiempo;
                try {
                    peso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el peso de la arista:"));
                    tiempo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de la arista:"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para el peso y el tiempo.");
                    return;
                }
                if (peso <= 0 || tiempo <= 0) {
                    JOptionPane.showMessageDialog(null, "El peso y el tiempo deben ser valores positivos.");
                    return;
                }
                if (grafo.existeArista(nombreOrigen, nombreDestino)) {
                    JOptionPane.showMessageDialog(null, "Ya se ha agregado un peso para la arista entre " + nombreOrigen + " y " + nombreDestino + ".");
                    return;
                }
                grafo.agregarArista(new Ubicacion(nombreOrigen), new Ubicacion(nombreDestino), peso, tiempo);
                JOptionPane.showMessageDialog(null, "Arista agregada correctamente.");
            }
        });
        panel.add(btnAgregarArista);

        // Botón para imprimir las ubicaciones
        JButton btnImprimirUbicaciones = new JButton("Imprimir Ubicaciones");
        customizeButton(btnImprimirUbicaciones);
        btnImprimirUbicaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grafo.imprimirUbicaciones();
            }
        });
        panel.add(btnImprimirUbicaciones);

        // Botón para modificar una ubicación
        JButton btnModificarUbicacion = new JButton("Modificar Ubicación");
        customizeButton(btnModificarUbicacion);
        btnModificarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario el nombre de la ubicación a modificar y el nuevo nombre
                String nombreViejo = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación a modificar:");
                if (nombreViejo != null && !nombreViejo.isEmpty()) {
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la ubicación:");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        grafo.modificarUbicacion(nombreViejo, nuevoNombre);
                        nombresUbicaciones.remove(nombreViejo);
                        nombresUbicaciones.add(nuevoNombre);
                        JOptionPane.showMessageDialog(null, "Ubicación modificada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nuevo nombre válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });
        panel.add(btnModificarUbicacion);

        // Botón para eliminar una ubicación
        JButton btnEliminarUbicacion = new JButton("Eliminar Ubicación");
        customizeButton(btnEliminarUbicacion);
        btnEliminarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario el nombre de la ubicación a eliminar
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación a eliminar:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.eliminarUbicacion(nombre);
                    nombresUbicaciones.remove(nombre);
                    JOptionPane.showMessageDialog(null, "Ubicación eliminada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });
        panel.add(btnEliminarUbicacion);

        // Botón para modificar el peso de una arista
        JButton btnModificarPeso = new JButton("Modificar Peso de Arista");
        customizeButton(btnModificarPeso);
        btnModificarPeso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario el nombre del origen y destino de la arista y el nuevo peso
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay aristas agregadas para modificar el peso.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen válido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino válido.");
                    return;
                }
                String nuevoPesoStr = JOptionPane.showInputDialog("Ingrese el nuevo peso de la arista:");
                try {
                    int nuevoPeso = Integer.parseInt(nuevoPesoStr);
                    if (nuevoPeso <= 0) {
                        JOptionPane.showMessageDialog(null, "El peso debe ser un número positivo mayor que cero.");
                        return;
                    }
                    grafo.modificarPesoArista(nombreOrigen, nombreDestino, nuevoPeso);
                    JOptionPane.showMessageDialog(null, "Peso de arista modificado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para el peso.");
                }
            }
        });
        panel.add(btnModificarPeso);

        // Botón para modificar el tiempo de una arista
        JButton btnModificarTiempo = new JButton("Modificar Tiempo de Arista");
        customizeButton(btnModificarTiempo);
        btnModificarTiempo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario el nombre del origen y destino de la arista y el nuevo tiempo
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay aristas agregadas para modificar el tiempo.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen válido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino válido.");
                    return;
                }
                String nuevoTiempoStr = JOptionPane.showInputDialog("Ingrese el nuevo tiempo de la arista:");
                try {
                    int nuevoTiempo = Integer.parseInt(nuevoTiempoStr);
                    if (nuevoTiempo <= 0) {
                        JOptionPane.showMessageDialog(null, "El tiempo debe ser un número positivo mayor que cero.");
                        return;
                    }
                    grafo.modificarTiempoArista(nombreOrigen, nombreDestino, nuevoTiempo);
                    JOptionPane.showMessageDialog(null, "Tiempo de arista modificado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para el tiempo.");
                }
            }
        });
        panel.add(btnModificarTiempo);

        // Botón para ejecutar el algoritmo de Prim
        JButton btnPrim = new JButton("Calcular Árbol de Expansión Mínima (Prim)");
        customizeButton(btnPrim);
        btnPrim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarPrim(); // Llama al método para ejecutar Prim
            }
        });
        panel.add(btnPrim);

        // Botón para ejecutar el algoritmo de Kruskal
        JButton btnKruskal = new JButton("Calcular Árbol de Expansión Mínima (Kruskal)");
        customizeButton(btnKruskal);
        btnKruskal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarKruskal(); // Llama al método para ejecutar Kruskal
            }
        });
        panel.add(btnKruskal);

        // Botón para calcular la ruta más corta
        JButton btnRutaMasCorta = new JButton("Calcular Ruta Más Corta");
        customizeButton(btnRutaMasCorta);
        btnRutaMasCorta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarRutaMasCorta(); // Llama al método para calcular la ruta más corta
            }
        });
        panel.add(btnRutaMasCorta);

        // Botón para planificar una ruta minimizando la distancia
        JButton btnPlanificarDistancia = new JButton("Planificar Ruta Minimizando Distancia");
        customizeButton(btnPlanificarDistancia);
        btnPlanificarDistancia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                planificarRuta(false); // Llama al método para planificar la ruta minimizando la distancia
            }
        });
        panel.add(btnPlanificarDistancia);

        // Botón para planificar una ruta minimizando el tiempo
        JButton btnPlanificarTiempo = new JButton("Planificar Ruta Minimizando Tiempo");
        customizeButton(btnPlanificarTiempo);
        btnPlanificarTiempo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                planificarRuta(true); // Llama al método para planificar la ruta minimizando el tiempo
            }
        });
        panel.add(btnPlanificarTiempo);

        add(panel); // Agrega el panel a la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrudVisual frame = new CrudVisual();
                    frame.setResizable(true); // Permite que la ventana sea redimensionable
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Método para planificar la ruta más corta minimizando la distancia o el tiempo
    private void planificarRuta(boolean minimizarTiempo) {
        // Solicita al usuario la ubicación de origen y destino para planificar la ruta
        String mensaje = minimizarTiempo ? "minimizando el tiempo" : "minimizando la distancia";
        String nombreOrigen = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicación de origen para planificar la ruta:");
        String nombreDestino = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicación de destino para planificar la ruta:");

        // Verifica si se proporcionaron ubicaciones válidas
        if (nombreOrigen == null || nombreDestino == null || nombreOrigen.isEmpty() || nombreDestino.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han proporcionado ubicaciones para planificar rutas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crea objetos Ubicacion para el origen y destino
        Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigen);
        Ubicacion ubicacionDestino = new Ubicacion(nombreDestino);

        // Verifica si las ubicaciones existen en el grafo
        if (grafo.existeUbicacion(ubicacionOrigen) && grafo.existeUbicacion(ubicacionDestino)) {
            JOptionPane.showMessageDialog(null, "Calculando ruta más corta " + mensaje + " desde " + nombreOrigen + " a " + nombreDestino + "...");
            // Planifica la ruta y obtiene las distancias
            Map<Ubicacion, Integer> distancias = grafo.planificarRuta(ubicacionOrigen, ubicacionDestino, minimizarTiempo);
            // Construye el mensaje con las distancias
            String metrica = minimizarTiempo ? "Tiempo" : "Distancia";
            StringBuilder ruta = new StringBuilder();
            ruta.append(metrica).append(" a ");
            for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                ruta.append(entry.getKey().getNombre()).append(": ").append(entry.getValue()).append("\n");
            }
            // Muestra el mensaje con la ruta calculada
            JOptionPane.showMessageDialog(null, ruta.toString(), "Ruta Calculada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Una o ambas ubicaciones no existen en el grafo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para personalizar los botones
    private void customizeButton(JButton button) {
        button.setBackground(new Color(240, 240, 240)); // Color de fondo
        button.setForeground(Color.BLACK); // Color del texto
        button.setFocusPainted(false); // Elimina el efecto de enfoque
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borde del botón
    }

    // Método para ejecutar el algoritmo de Prim y mostrar el peso del árbol de expansión mínima
    private void ejecutarPrim() {
        String nombreOrigenPrim = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación de origen para el Árbol de Expansión Mínima (Prim):");
        Ubicacion ubicacionOrigenPrim = new Ubicacion(nombreOrigenPrim);
        if (grafo.existeUbicacion(ubicacionOrigenPrim)) {
            AlgoritmosGrafo algoritmos = new AlgoritmosGrafo();
            int mstWeight = algoritmos.prim(grafo.generarMatrizAdyacencia(ubicacionOrigenPrim));
            JOptionPane.showMessageDialog(null, "Peso del árbol de expansión mínima desde " + nombreOrigenPrim + ": " + mstWeight);
        } else {
            JOptionPane.showMessageDialog(null, "La ubicación de origen no existe en el grafo.");
        }
    }

    // Método para ejecutar el algoritmo de Kruskal y mostrar el peso total del árbol de expansión mínima
    private void ejecutarKruskal() {
        String nombreOrigenKruskal = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación de origen para el Árbol de Expansión Mínima (Kruskal):");
        Ubicacion ubicacionOrigenKruskal = new Ubicacion(nombreOrigenKruskal);
        if (grafo.existeUbicacion(ubicacionOrigenKruskal)) {
            Set<Arista> arbolExpansionMinima = new HashSet<>(grafo.kruskal());
            int totalWeight = 0;
            StringBuilder sb = new StringBuilder();
            for (Arista arista : arbolExpansionMinima) {
                sb.append(arista.getOrigen().getNombre()).append(" - ").append(arista.getDestino().getNombre()).append(": ").append(arista.getPeso()).append("\n");
                totalWeight += arista.getPeso();
            }
            JOptionPane.showMessageDialog(null, sb.toString() + "Peso total del árbol de expansión mínima desde " + nombreOrigenKruskal + ": " + totalWeight);
        } else {
            JOptionPane.showMessageDialog(null, "La ubicación de origen no existe en el grafo.");
        }
    }

    // Método para ejecutar el algoritmo de la ruta más corta desde una ubicación y mostrar las distancias
    private void ejecutarRutaMasCorta() {
        String nombreOrigenRuta = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación de origen para calcular la ruta más corta:");
        Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
        if (grafo.existeUbicacion(ubicacionOrigen)) {
            Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                sb.append("Distancia a ").append(entry.getKey().getNombre()).append(": ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "La ubicación de origen no existe en el grafo.");
        }
    }
}
