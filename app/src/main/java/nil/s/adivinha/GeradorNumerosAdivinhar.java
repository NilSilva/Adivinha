package nil.s.adivinha;

import java.util.Random;

/**
 * Esta classe gerar numeros entre 1 e 10
 */

public class GeradorNumerosAdivinhar {
    private Random random = new Random();

    /**
     * Obtem o proximo numero a adivinhar
     * @return um numero entre 1 e 10
     */
    public int getProximoNumeroAdivinhar(){
        return random.nextInt(10) + 1;//Erro esta a dar um numero entre 0 e 9
    }
}
