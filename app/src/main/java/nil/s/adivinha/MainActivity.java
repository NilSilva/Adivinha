package nil.s.adivinha;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int numerosAdivinhar;
    private  GeradorNumerosAdivinhar geradorNumeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adivinha();
            }
        });

        geradorNumeros = new GeradorNumerosAdivinhar();

        novoJogo();

    }

    private void novoJogo() {
        numerosAdivinhar = geradorNumeros.getProximoNumeroAdivinhar();
    }

    private void adivinha() {

        EditText editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        String textoNumero = editTextNumero.getText().toString();

        if(textoNumero.isEmpty()){
            editTextNumero.setError("Introduza um numero entre 1 e 10");
            editTextNumero.requestFocus();
            return;
        }

        int numero;

        try {
            numero = Integer.parseInt(textoNumero);
        } catch (NumberFormatException e) {
            editTextNumero.setError("Numero invalido. Introduza um numero entre 1 e 10");
            editTextNumero.requestFocus();
            return;
        }

        if(1 > numero || numero > 10){
            editTextNumero.setError("Numero invalido. Introduza um numero entre 1 e 10");
            editTextNumero.requestFocus();
            return;
        }

        verificaAcertou(numero);

    }

    private void verificaAcertou(int numero) {
        if(numero == numerosAdivinhar){
            acertou();
        }else if(numero < numerosAdivinhar){
            Toast.makeText(this, "Não, é maior.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não, é menor.", Toast.LENGTH_SHORT).show();
        }
    }

    private void acertou() {
        Toast.makeText(this, "Parabens acertou!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
