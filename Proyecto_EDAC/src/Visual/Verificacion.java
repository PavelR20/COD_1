package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Verificacion extends JFrame {

    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Verificacion frame = new Verificacion();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Verificacion() {
       
        setTitle("Rutas");
        setBackground(new Color(204, 255, 255));
        setBounds(100, 100, 527, 407);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(204, 255, 255));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JMenuBar menuBar = new JMenuBar();
            menuBar.setBackground(new Color(255, 255, 204));
            setJMenuBar(menuBar);
            {
                JMenu mnNewMenu = new JMenu("Ubicacion");
                menuBar.add(mnNewMenu);
                {
                    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Insertar Ubicacion");
                    mnNewMenu.add(mntmNewMenuItem_1);
                }
                {
                    JMenuItem mntmNewMenuItem_3 = new JMenuItem("Eliminar Ubicacion ");
                    mnNewMenu.add(mntmNewMenuItem_3);
                }
                {
                	JMenuItem mntmModificarUbicacion = new JMenuItem("Modificar Ubicacion ");
                	mnNewMenu.add(mntmModificarUbicacion);
                }
            }
            {
                JMenu mnNewMenu_2 = new JMenu("Peso");
                menuBar.add(mnNewMenu_2);
                {
                    JMenuItem mntmNewMenuItem = new JMenuItem("Insertar Peso");
                    mnNewMenu_2.add(mntmNewMenuItem);
                }
                {
                	JMenuItem mntmPeso = new JMenuItem("Eliminar Peso");
                	mnNewMenu_2.add(mntmPeso);
                }
                {
                	JMenuItem mntmModificarPeso = new JMenuItem("Modificar Peso");
                	mnNewMenu_2.add(mntmModificarPeso);
                }
           
           
            }
        }
    }
}
        
   



