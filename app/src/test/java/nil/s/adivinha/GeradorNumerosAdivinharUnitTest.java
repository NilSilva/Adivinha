package nil.s.adivinha;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GeradorNumerosAdivinharUnitTest {
    @Test
    public void getProximoNumeroAdivinhar_isCorrect() {
        GeradorNumerosAdivinhar gerador = new GeradorNumerosAdivinhar();

        int min = 10;
        int max = 1;

        for(int i = 0; i < 100000; i++) {
            int num = gerador.getProximoNumeroAdivinhar();

            if(num < min){
                min = num;
            }

            if(num > max){
                max = num;
            }

            assertTrue(num >= 1 && num <= 10);
        }

        assertEquals(1, min);
        assertEquals(10, max);
    }
}