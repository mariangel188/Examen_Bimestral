import java.util.List;

public class ResultadoPartido {
    private final String jugador1;
    private final String jugador2;
    private final List<String> sets;
    private final String ganador;

    public ResultadoPartido(String jugador1, String jugador2, List<String> sets, String ganador) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.sets = sets;
        this.ganador = ganador;
    }

    public String getGanador() {
        return ganador;
    }

    public void imprimir() {
        System.out.println(jugador1 + " vs " + jugador2);
        for (String set : sets) {
            System.out.println(set);
        }
        System.out.println("Ganador del partido: " + ganador + "\n");
    }
}