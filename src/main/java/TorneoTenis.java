import java.util.*;
import java.util.concurrent.*;

public class TorneoTenis {

    private static final int NUM_JUGADORES = 16;
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> jugadores = new ArrayList<>();
        for (int i = 1; i <= NUM_JUGADORES; i++) {
            jugadores.add("Jugador " + i);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);
        int ronda = 1;

        while (jugadores.size() > 1) {
            mostrarNombreRonda(jugadores.size());

            List<Future<ResultadoPartido>> resultadosFutures = new ArrayList<>();
            List<String> ganadores = new ArrayList<>();

            List<String> emparejados = new ArrayList<>();

            if (jugadores.size() == 16) {
                for (int i = 0; i < jugadores.size() / 2; i++) {
                    emparejados.add(jugadores.get(i));
                    emparejados.add(jugadores.get(jugadores.size() - 1 - i));
                }
            } else {
                emparejados = new ArrayList<>(jugadores);
            }

            for (int i = 0; i < emparejados.size(); i += 2) {
                Partido partido = new Partido(emparejados.get(i), emparejados.get(i + 1));
                Future<ResultadoPartido> future = executor.submit(partido);
                resultadosFutures.add(future);
            }

            // Esperamos resultados
            for (Future<ResultadoPartido> future : resultadosFutures) {
                ResultadoPartido resultado = future.get();
                resultado.imprimir();
                ganadores.add(resultado.getGanador());
            }

            jugadores = ganadores;
            Collections.reverse(jugadores); // Mantener el orden visual
            ronda++;
        }


        executor.shutdown();
        System.out.println("\n🏆 ¡Campeón del torneo: " + jugadores.get(0) + "!");
    }

    private static void mostrarNombreRonda(int cantidadJugadores) {
        String nombreRonda = switch (cantidadJugadores) {
            case 16 -> "===== OCTAVOS DE FINAL =====";
            case 8 -> "===== CUARTOS DE FINAL =====";
            case 4 -> "===== SEMIFINAL =====";
            case 2 -> "===== FINAL =====";
            default -> "===== RONDA =====";
        };
        System.out.println("\n" + nombreRonda);
    }
}