package lexico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InterfazGrafica {

    //Frames
    JFrame ventanaPrincipal;
    JFrame ventanaEditor;

    //Contenedores
    JPanel contPrincipal;
    JPanel contEditor;

    //Entrada de texto
    JTextArea editorTexto;

    //Entrada de archivo
    JFileChooser fileChooser;

    //Botones
    JButton cargar;
    JButton crear;
    JButton guardar;
    JButton compilar;
    JButton salir;

    //Archivo a cargar
    File archivo;


    public InterfazGrafica() {

        ventanaPrincipal = new JFrame("TP 1 - Teoria de la computación");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(300, 300);

        contPrincipal = new JPanel();
        contPrincipal.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("TRABAJO PRACTICO N1 - TEORIA DE LA COMPUTACIÓN");
        crear = new JButton ("Crear Archivo nuevo");
        cargar = new JButton ("Cargar Archivo");
        JPanel contBotones = new JPanel(new BorderLayout());
        contBotones.add(crear, BorderLayout.WEST);
        contBotones.add(cargar, BorderLayout.EAST);

        contPrincipal.add(titulo, BorderLayout.NORTH);
        contPrincipal.add(contBotones, BorderLayout.SOUTH);
        ventanaPrincipal.add(contPrincipal);
        ventanaPrincipal.setVisible(true);

        cargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser("C:/Users/feder/IdeaProjects/TP_Teoria1/prueba.txt");
                int opcion = fileChooser.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    archivo = fileChooser.getSelectedFile();
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        editorTexto = new JTextArea();
                        editorTexto.setText(""); // limpiar antes
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            editorTexto.append(linea + "\n");
                        }
                        contEditor = new JPanel(new BorderLayout());

                        contEditor.add(editorTexto);
                        JPanel contbotonesEditor = new JPanel(new BorderLayout());
                        guardar = new JButton("Guardar cambios");
                        compilar = new JButton("Compilar");
                        salir = new JButton("Salir");
                        contbotonesEditor.add(guardar,BorderLayout.WEST);
                        contbotonesEditor.add(compilar,BorderLayout.CENTER);
                        contbotonesEditor.add(salir,BorderLayout.EAST);
                        contEditor.add(contbotonesEditor, BorderLayout.SOUTH);

//                        contEditor.remove(contBotones);
//                        contEditor.remove(titulo);
//                        contEditor.revalidate();

                        //Creamos la ventana nueva del editor de texto
                        ventanaEditor = new JFrame("Editor de texto");
                        ventanaEditor.setSize(500,500);
                        ventanaEditor.add(contEditor);
                        ventanaEditor.setVisible(true);

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo");
                    }
                }




                guardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (archivo != null) {
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                                bw.write(editorTexto.getText());
                                JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero debe cargar un archivo.");
                        }
                    }
                });
            }
        });
    }
}
