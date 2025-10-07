import lexico.Lexico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class InterfazGrafica {

    //Frames
    JFrame ventanaPrincipal;
    JFrame ventanaEditor;
    JFrame pantallaRespuesta;
    JFrame ventanaTabla;

    //Contenedores
    JPanel contPrincipal;
    JPanel contRespuesta;
    JPanel contTitulo;

    //Entrada de texto
    JTextArea editorTexto;
    JTextArea editorTextoRespuesta;
    JTextArea editorTextoTabla;

    //Entrada de archivo
    JFileChooser fileChooser;

    //Botones
    JButton cargar;
    JButton crear;
    JButton guardar;
    JButton compilar;
    JButton salir;
    JButton salirRespuesta;
    JButton salirTabla;

    //Scroll
    JScrollPane panelScrolleable;
    JScrollPane panelScrolleableEditor;
    JScrollPane panelScrolleableRespuesta;
    JScrollPane getPanelScrolleableTabla;



    //Archivo a cargar
    File archivo;

    //Variable de tipo Lexico
    Lexico lexico;

    //Variable de tipo ArrayList para la respuesta del Lexico
    List<String> respuesta;

    // ----------------------- PANTALLA PRINCIPAL -------------------------

    public InterfazGrafica() {

        ventanaPrincipal = new JFrame("TP 1 - Teoria de la computación");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(450, 300);
        contPrincipal = new JPanel();
        contPrincipal.setLayout(new BorderLayout());

        contTitulo = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("TRABAJO PRACTICO N1 - TEORIA DE LA COMPUTACIÓN");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);


        contTitulo.add(titulo, BorderLayout.CENTER);
        crear = new JButton("Crear Archivo nuevo");
        cargar = new JButton("Cargar Archivo");
        JPanel contBotones = new JPanel(new BorderLayout());
        contBotones.add(crear, BorderLayout.WEST);
        contBotones.add(cargar, BorderLayout.EAST);

        contPrincipal.add(contTitulo, BorderLayout.NORTH);
        contPrincipal.add(contBotones, BorderLayout.SOUTH);

        panelScrolleable = new JScrollPane(contPrincipal);
        ventanaPrincipal.add(panelScrolleable);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        //Acciones:
        // Crear Archivo
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearEditor();
                ventanaEditor.setVisible(true);
            }
        });

        // Cargar Archivo
        cargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearEditor();
                cargarArchivo();
                ventanaEditor.setVisible(true);
            }
        });

//     ----------------- LE DAMOS UN TOQUE DE ESTILO ------------------
        //contPrincipal.setBackground(Color.DARK_GRAY);
        //contTitulo.setBackground(Color.DARK_GRAY);
        //contTitulo.setForeground(Color.lightGray);
    }

    // ---------------- PANTALLA RESPUESTA -------------------------------

    public void pantallaRespuesta() {
        //Creo ventana
        pantallaRespuesta = new JFrame("Tokens");
        pantallaRespuesta.setSize(800, 600);
        pantallaRespuesta.setLocationRelativeTo(null);
        //Creo panel de la ventana
        contRespuesta = new JPanel(new BorderLayout());
        //Creo el editor para mostrar la respuesta
        editorTextoRespuesta = new JTextArea();
        editorTextoRespuesta.setEditable(false);
        editorTextoRespuesta.setFont(new Font("Monospaced", Font.PLAIN, 12));

        //Creo boton para salir
        salirRespuesta = new JButton("Salir");

        //Llamo al metodo para obtener la respuesta en el
        cargarTexto();

        contRespuesta.add(editorTextoRespuesta);

        //Barra de scroll
        panelScrolleableRespuesta = new JScrollPane(contRespuesta);

        //Ventana de respuesta
        pantallaRespuesta.add(panelScrolleableRespuesta, BorderLayout.CENTER);
        pantallaRespuesta.add(salirRespuesta, BorderLayout.SOUTH);
        pantallaRespuesta.setVisible(true);

        salirRespuesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarEditor(pantallaRespuesta);
            }
        });
    }

    public void crearEditor() {
        editorTexto = new JTextArea();
        editorTexto.setText(""); // limpiar antes
        editorTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panelScrolleableEditor = new JScrollPane(editorTexto);

        JPanel contbotonesEditor = new JPanel(new BorderLayout());
        guardar = new JButton("Guardar cambios");
        compilar = new JButton("Compilar");
        salir = new JButton("Salir");
        contbotonesEditor.add(guardar, BorderLayout.WEST);
        contbotonesEditor.add(compilar, BorderLayout.CENTER);
        contbotonesEditor.add(salir, BorderLayout.EAST);

        ventanaEditor = new JFrame("Editor de texto");
        ventanaEditor.setLocationRelativeTo(null);
        ventanaEditor.setSize(800, 600);
        ventanaEditor.add(panelScrolleableEditor, BorderLayout.CENTER);
        ventanaEditor.add(contbotonesEditor, BorderLayout.SOUTH);

        //Accion Botones editor
        compilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
                try {
                    compilarArchivo();
                } catch (IOException ex) {
                    throw new Error("Error al compilar el archivo");
                }
                pantallaRespuesta();
            }
        });
        guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarEditor(ventanaEditor);
            }
        });
    }

    public void crearTabla() {
        editorTextoTabla = new JTextArea();
        editorTextoTabla.setEditable(false);
        editorTextoTabla.setText(lexico.stringTabla());
        editorTextoTabla.setFont(new Font("Monospaced", Font.PLAIN, 12));

        getPanelScrolleableTabla = new JScrollPane(editorTextoTabla);
        salirTabla = new JButton("Salir");

        ventanaTabla = new JFrame("Tabla de Simbolos");
        ventanaTabla.setLayout(new BorderLayout());
        ventanaTabla.add(getPanelScrolleableTabla, BorderLayout.CENTER);
        ventanaTabla.add(salirTabla, BorderLayout.SOUTH);
        ventanaTabla.setSize(800, 600);
        ventanaTabla.setLocationRelativeTo(null);
        ventanaTabla.setVisible(true);

        salirTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarEditor(ventanaTabla);
            }
        });
    }


    // ---------------- METODOS DE LOS BOTONES ---------------------------
    public void guardarArchivo() {
        if (archivo != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                bw.write(editorTexto.getText());
                JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
            }
        } else {
            try {
                new File("./").mkdirs(); // crea la carpeta si no existe
                archivo = new File("./archivo.txt");

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                    bw.write(editorTexto.getText());
                    JOptionPane.showMessageDialog(null, "Archivo guardado correctamente en:\n" + archivo.getAbsolutePath());
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }

    public void cargarArchivo() {
        fileChooser = new JFileChooser("./");
        int opcion = fileChooser.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    editorTexto.append(linea + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }
        }
    }

    public void cerrarEditor(JFrame editor) {
        editor.dispose();
    }

    public void cargarTexto() {
        editorTextoRespuesta = new JTextArea();
        for (String linea : lexico.getRespuesta()) {
            editorTextoRespuesta.append(linea + "\n");
        }
    }

    public void compilarArchivo() throws IOException {
        if (archivo == null || !archivo.exists()) {
            throw new FileNotFoundException("No se encontró el archivo a compilar.");
        }
        lexico = new Lexico(new FileReader(this.archivo));
        lexico.next_token();
        lexico.crearArchivoTabla();
        cargarTexto();
        crearTabla();
    }


    // --------------- STYLE ------------------------------


}
