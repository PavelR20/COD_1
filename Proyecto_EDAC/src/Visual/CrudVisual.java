
/*
package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CrudVisual extends JFrame {
    private GrafoNoDirigido grafo;

    public CrudVisual() {
        setTitle("CRUD Visual");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grafo = new GrafoNoDirigido();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton btnAgregarUbicacion = new JButton("Agregar Ubicación");
        btnAgregarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.agregarUbicacion(new Ubicacion(nombre));
                    JOptionPane.showMessageDialog(null, "Ubicación agregada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });

        JButton btnEliminarUbicacion = new JButton("Eliminar Ubicación");
        btnEliminarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación a eliminar:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.eliminarUbicacion(nombre);
                    JOptionPane.showMessageDialog(null, "Ubicación eliminada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });

        JButton btnModificarUbicacion = new JButton("Modificar Ubicación");
        btnModificarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreViejo = JOptionPane.showInputDialog("Ingrese el nombre de la ubicación a modificar:");
                if (nombreViejo != null && !nombreViejo.isEmpty()) {
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la ubicación:");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        grafo.modificarUbicacion(nombreViejo, nuevoNombre);
                        JOptionPane.showMessageDialog(null, "Ubicación modificada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nuevo nombre válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                }
            }
        });

        JButton btnImprimirUbicaciones = new JButton("Imprimir Ubicaciones");
        btnImprimirUbicaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grafo.imprimirUbicaciones();
            }
        });

        panel.add(btnAgregarUbicacion);
        panel.add(btnEliminarUbicacion);
        panel.add(btnModificarUbicacion);
        panel.add(btnImprimirUbicaciones);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrudVisual frame = new CrudVisual();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
*/


