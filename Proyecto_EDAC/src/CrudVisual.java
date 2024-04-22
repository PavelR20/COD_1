import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CrudVisual extends JFrame {
    private GrafoNoDirigido grafo;
    private Set<String> nombresUbicaciones;

    public CrudVisual() {
        setTitle("CRUD Visual");
        setTitle("Menu");
        setSize(800, 600); // Tama�o inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grafo = new GrafoNoDirigido();
        nombresUbicaciones = new HashSet<>();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton btnAgregarUbicacion = new JButton("Agregar Ubicaci�n");
        customizeButton(btnAgregarUbicacion);

        btnAgregarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.agregarUbicacion(new Ubicacion(nombre));
                    nombresUbicaciones.add(nombre);
                    JOptionPane.showMessageDialog(null, "Ubicaci�n agregada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
                }
            }
        });
        panel.add(btnAgregarUbicacion);

        JButton btnAgregarArista = new JButton("Agregar Arista con Peso y Tiempo");
        customizeButton(btnAgregarArista);
        btnAgregarArista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay ubicaciones agregadas para asignarle un peso.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen v�lido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino v�lido.");
                    return;
                }
                int peso, tiempo;
                try {
                    peso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el peso de la arista:"));
                    tiempo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de la arista:"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores num�ricos v�lidos para el peso y el tiempo.");
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
        
        

        JButton btnImprimirUbicaciones = new JButton("Imprimir Ubicaciones");
        customizeButton(btnImprimirUbicaciones);
        btnImprimirUbicaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grafo.imprimirUbicaciones();
            }
        });
        panel.add(btnImprimirUbicaciones);

        JButton btnModificarUbicacion = new JButton("Modificar Ubicaci�n");
        customizeButton(btnModificarUbicacion);
        btnModificarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreViejo = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n a modificar:");
                if (nombreViejo != null && !nombreViejo.isEmpty()) {
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la ubicaci�n:");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        grafo.modificarUbicacion(nombreViejo, nuevoNombre);
                        nombresUbicaciones.remove(nombreViejo);
                        nombresUbicaciones.add(nuevoNombre);
                        JOptionPane.showMessageDialog(null, "Ubicaci�n modificada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nuevo nombre v�lido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
                }
            }
        });
        panel.add(btnModificarUbicacion);

        JButton btnEliminarUbicacion = new JButton("Eliminar Ubicaci�n");
        customizeButton(btnEliminarUbicacion);
        btnEliminarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n a eliminar:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.eliminarUbicacion(nombre);
                    nombresUbicaciones.remove(nombre);
                    JOptionPane.showMessageDialog(null, "Ubicaci�n eliminada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
                }
            }
        });
        panel.add(btnEliminarUbicacion);
        
        
     // Modificar Peso
        JButton btnModificarPeso = new JButton("Modificar Peso de Arista");
        customizeButton(btnModificarPeso);
        btnModificarPeso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay aristas agregadas para modificar el peso.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen v�lido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino v�lido.");
                    return;
                }
                String nuevoPesoStr = JOptionPane.showInputDialog("Ingrese el nuevo peso de la arista:");
                try {
                    int nuevoPeso = Integer.parseInt(nuevoPesoStr);
                    if (nuevoPeso <= 0) {
                        JOptionPane.showMessageDialog(null, "El peso debe ser un n�mero positivo mayor que cero.");
                        return;
                    }
                    grafo.modificarPesoArista(nombreOrigen, nombreDestino, nuevoPeso);
                    JOptionPane.showMessageDialog(null, "Peso de arista modificado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un n�mero v�lido para el peso.");
                }
            }
        });
        panel.add(btnModificarPeso);

     // Modificar Tiempo
        JButton btnModificarTiempo = new JButton("Modificar Tiempo de Arista");
        customizeButton(btnModificarTiempo);
        btnModificarTiempo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nombresUbicaciones.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay aristas agregadas para modificar el tiempo.");
                    return;
                }
                String nombreOrigen = JOptionPane.showInputDialog("Ingrese el nombre del origen:");
                if (nombreOrigen == null || nombreOrigen.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de origen v�lido.");
                    return;
                }
                String nombreDestino = JOptionPane.showInputDialog("Ingrese el nombre del destino:");
                if (nombreDestino == null || nombreDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de destino v�lido.");
                    return;
                }
                String nuevoTiempoStr = JOptionPane.showInputDialog("Ingrese el nuevo tiempo de la arista:");
                try {
                    int nuevoTiempo = Integer.parseInt(nuevoTiempoStr);
                    if (nuevoTiempo <= 0) {
                        JOptionPane.showMessageDialog(null, "El tiempo debe ser un n�mero positivo mayor que cero.");
                        return;
                    }
                    grafo.modificarTiempoArista(nombreOrigen, nombreDestino, nuevoTiempo);
                    JOptionPane.showMessageDialog(null, "Tiempo de arista modificado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un n�mero v�lido para el tiempo.");
                }
            }
        });
        panel.add(btnModificarTiempo);


        JButton btnPrim = new JButton("Calcular �rbol de Expansi�n M�nima (Prim)");
        customizeButton(btnPrim);
        btnPrim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarPrim();
            }
        });
        panel.add(btnPrim);

        JButton btnKruskal = new JButton("Calcular �rbol de Expansi�n M�nima (Kruskal)");
        customizeButton(btnKruskal);
        btnKruskal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarKruskal();
            }
        });
        panel.add(btnKruskal);

        JButton btnRutaMasCorta = new JButton("Calcular Ruta M�s Corta");
        customizeButton(btnRutaMasCorta);
        btnRutaMasCorta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarRutaMasCorta();
            }
        });
        panel.add(btnRutaMasCorta);
        
        
        JButton btnPlanificarDistancia = new JButton("Planificar Ruta Minimizando Distancia");
        customizeButton(btnPlanificarDistancia);
        btnPlanificarDistancia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                planificarRuta(false);
            }
        });
        panel.add(btnPlanificarDistancia);

        JButton btnPlanificarTiempo = new JButton("Planificar Ruta Minimizando Tiempo");
        customizeButton(btnPlanificarTiempo);
        btnPlanificarTiempo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                planificarRuta(true);
            }
        });
        panel.add(btnPlanificarTiempo);
        
        

        add(panel);
        setVisible(true);
    }
    
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
    
    
 // M�todo para planificar ruta corta
    private void planificarRuta(boolean minimizarTiempo) {
        String mensaje = minimizarTiempo ? "minimizando el tiempo" : "minimizando la distancia";
        String nombreOrigen = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicaci�n de origen para planificar la ruta:");
        String nombreDestino = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicaci�n de destino para planificar la ruta:");

        if (nombreOrigen == null || nombreDestino == null || nombreOrigen.isEmpty() || nombreDestino.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han proporcionado ubicaciones para planificar rutas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigen);
        Ubicacion ubicacionDestino = new Ubicacion(nombreDestino);

        if (grafo.existeUbicacion(ubicacionOrigen) && grafo.existeUbicacion(ubicacionDestino)) {
            JOptionPane.showMessageDialog(null, "Calculando ruta m�s corta " + mensaje + " desde " + nombreOrigen + " a " + nombreDestino + "...");
            Map<Ubicacion, Integer> distancias = grafo.planificarRuta(ubicacionOrigen, ubicacionDestino, minimizarTiempo);
            String metrica = minimizarTiempo ? "Tiempo" : "Distancia";
            StringBuilder ruta = new StringBuilder();
            ruta.append(metrica).append(" a ");
            for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                ruta.append(entry.getKey().getNombre()).append(": ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(null, ruta.toString(), "Ruta Calculada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Una o ambas ubicaciones no existen en el grafo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // M�todo para personalizar los botones
    private void customizeButton(JButton button) {
        button.setBackground(new Color(240, 240, 240)); // Color de fondo
        button.setForeground(Color.BLACK); // Color del texto
        button.setFocusPainted(false); // Elimina el efecto de enfoque
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borde del bot�n
    }

    // M�todo para ejecutar el algoritmo de Prim y mostrar el peso del �rbol de expansi�n m�nima
    private void ejecutarPrim() {
        String nombreOrigenPrim = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n de origen para el �rbol de Expansi�n M�nima (Prim):");
        Ubicacion ubicacionOrigenPrim = new Ubicacion(nombreOrigenPrim);
        if (grafo.existeUbicacion(ubicacionOrigenPrim)) {
            AlgoritmosGrafo algoritmos = new AlgoritmosGrafo();
            int mstWeight = algoritmos.prim(grafo.generarMatrizAdyacencia(ubicacionOrigenPrim));
            JOptionPane.showMessageDialog(null, "Peso del �rbol de expansi�n m�nima desde " + nombreOrigenPrim + ": " + mstWeight);
        } else {
            JOptionPane.showMessageDialog(null, "La ubicaci�n de origen no existe en el grafo.");
        }
    }

    // M�todo para ejecutar el algoritmo de Kruskal y mostrar el peso total del �rbol de expansi�n m�nima
    private void ejecutarKruskal() {
        String nombreOrigenKruskal = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n de origen para el �rbol de Expansi�n M�nima (Kruskal):");
        Ubicacion ubicacionOrigenKruskal = new Ubicacion(nombreOrigenKruskal);
        if (grafo.existeUbicacion(ubicacionOrigenKruskal)) {
        	Set<Arista> arbolExpansionMinima = new HashSet<>(grafo.kruskal());
            int totalWeight = 0;
            StringBuilder sb = new StringBuilder();
            for (Arista arista : arbolExpansionMinima) {
                sb.append(arista.getOrigen().getNombre()).append(" - ").append(arista.getDestino().getNombre()).append(": ").append(arista.getPeso()).append("\n");
                totalWeight += arista.getPeso();
            }
            JOptionPane.showMessageDialog(null, sb.toString() + "Peso total del �rbol de expansi�n m�nima desde " + nombreOrigenKruskal + ": " + totalWeight);
        } else {
            JOptionPane.showMessageDialog(null, "La ubicaci�n de origen no existe en el grafo.");
        }
    }

    // M�todo para ejecutar el algoritmo de la ruta m�s corta desde una ubicaci�n y mostrar las distancias
    private void ejecutarRutaMasCorta() {
        String nombreOrigenRuta = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n de origen para calcular la ruta m�s corta:");
        Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
        if (grafo.existeUbicacion(ubicacionOrigen)) {
            Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                sb.append("Distancia a ").append(entry.getKey().getNombre()).append(": ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "La ubicaci�n de origen no existe en el grafo.");
        }
    }
    
    

    
}
