package lexico.tabla;

public class Fila {
    private String nombre;
    private String token;
    private String tipo;
    private String valor;
    private Integer largo;

    public Fila(String nombre, String token, String tipo, String valor, Integer largo) {
        this.nombre = nombre;
        this.token = token;
        this.tipo = tipo;
        this.valor = valor;
        this.largo = largo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("%-32s %-15s %-12s %-32s %-8s",
                            nombre != null ? nombre : "",
                            token != null ? token : "",
                            tipo != null ? tipo : "",
                            valor != null ? valor : "",
                            largo != null ? largo.toString() : "");
    }
}
