
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

        JButton btnAgregarUbicacion = new JButton("Agregar Ubicaci�n");
        btnAgregarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.agregarUbicacion(new Ubicacion(nombre));
                    JOptionPane.showMessageDialog(null, "Ubicaci�n agregada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
                }
            }
        });

        JButton btnEliminarUbicacion = new JButton("Eliminar Ubicaci�n");
        btnEliminarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n a eliminar:");
                if (nombre != null && !nombre.isEmpty()) {
                    grafo.eliminarUbicacion(nombre);
                    JOptionPane.showMessageDialog(null, "Ubicaci�n eliminada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
                }
            }
        });

        JButton btnModificarUbicacion = new JButton("Modificar Ubicaci�n");
        btnModificarUbicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreViejo = JOptionPane.showInputDialog("Ingrese el nombre de la ubicaci�n a modificar:");
                if (nombreViejo != null && !nombreViejo.isEmpty()) {
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la ubicaci�n:");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        grafo.modificarUbicacion(nombreViejo, nuevoNombre);
                        JOptionPane.showMessageDialog(null, "Ubicaci�n modificada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nuevo nombre v�lido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v�lido.");
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


