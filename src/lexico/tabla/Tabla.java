package lexico.tabla;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tabla {
    private final List<Fila> filas = new ArrayList<>();

    public void agregarFila(Fila fila) {
        String nombre = fila.getNombre();
        if (buscarFila(nombre) == null) {
            filas.add(fila);
        }
    }

    public Fila buscarFila(String nombre) {
        for (Fila fila : filas) {
            if (fila.getNombre().equals(nombre)) {
                return fila;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Línea superior
        sb.append("+").append("-".repeat(32)).append("+").append("-".repeat(15))
          .append("+").append("-".repeat(12)).append("+").append("-".repeat(32))
          .append("+").append("-".repeat(8)).append("+").append("\n");

        // Encabezado
        sb.append(String.format("| %-30s | %-13s | %-10s | %-30s | %-6s |\n",
                               "NOMBRE", "TOKEN", "TIPO", "VALOR", "LARGO"));

        // Línea separadora
        sb.append("+").append("-".repeat(32)).append("+").append("-".repeat(15))
          .append("+").append("-".repeat(12)).append("+").append("-".repeat(32))
          .append("+").append("-".repeat(8)).append("+").append("\n");

        // Filas de datos
        for (Fila fila : filas) {
            sb.append("| ").append(fila.toString()).append(" |\n");
        }

        // Línea inferior
        sb.append("+").append("-".repeat(32)).append("+").append("-".repeat(15))
          .append("+").append("-".repeat(12)).append("+").append("-".repeat(32))
          .append("+").append("-".repeat(8)).append("+").append("\n");

        return sb.toString();
    }

    public void crearArchivo() {
        try {
            FileWriter writer = new FileWriter("ts.txt");
            writer.write(this.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
