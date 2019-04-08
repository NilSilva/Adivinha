package nil.s.adivinha;

import java.util.Random;

public class GeradorNumerosAdivinhar {
    private Random random = new Random();

    public int getProximoNumeroAdivinhar(){
        return random.nextInt(10);//Erro esta a dar um numero entre 0 e 9
    }
}
