import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Partido implements Callable<ResultadoPartido> {
    private final String jugador1;
    private final String jugador2;
    private final Random random = new Random();

    public Partido(String jugador1, String jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    @Override
    public ResultadoPartido call() throws Exception {
        List<String> resultadosSet = new ArrayList<>();
        int sets1 = 0;
        int sets2 = 0;

        for (int i = 1; i <= 3; i++) {
            if (sets1 == 2 || sets2 == 2) break;

            Thread.sleep((long) (1500 + random.nextInt(500))); // 1.5 - 2s

            boolean gana1 = random.nextBoolean();
            if (gana1) {
                sets1++;
                resultadosSet.add("Set " + i + ": " + jugador1);
            } else {
                sets2++;
                resultadosSet.add("Set " + i + ": " + jugador2);
            }
        }

        String ganador = sets1 > sets2 ? jugador1 : jugador2;
        return new ResultadoPartido(jugador1, jugador2, resultadosSet, ganador);
    }
}
