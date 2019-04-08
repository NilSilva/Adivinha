package nil.s.adivinha;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private int numerosAdivinhar;
    private  GeradorNumerosAdivinhar geradorNumeros;
    private int tentativas;

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
        Toast.makeText(this, getString(R.string.jogo_iniciado), Toast.LENGTH_SHORT).show();
        tentativas = 0;
    }

    private void adivinha() {

        EditText editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        String textoNumero = editTextNumero.getText().toString();

        if(textoNumero.isEmpty()){
            editTextNumero.setError(getString(R.string.Erro_num_intr));
            editTextNumero.requestFocus();
            return;
        }

        int numero;

        try {
            numero = Integer.parseInt(textoNumero);
        } catch (NumberFormatException e) {
            editTextNumero.setError(getString(R.string.Erro_num_intr));
            editTextNumero.requestFocus();
            return;
        }

        if(1 > numero || numero > 10){
            editTextNumero.setError(getString(R.string.Erro_num_intr));
            editTextNumero.requestFocus();
            return;
        }

        verificaAcertou(numero);

    }

    private void verificaAcertou(int numero) {
        tentativas++;
        if(numero == numerosAdivinhar){
            acertou();
        }else if(numero < numerosAdivinhar){
            Toast.makeText(this, getString(R.string.num_maior), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getString(R.string.num_menor), Toast.LENGTH_SHORT).show();
        }
    }

    private void acertou() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.acertou));
        builder.setMessage(getString(R.string.num_tent_1) + tentativas + getString(R.string.num_tent_2));

        builder.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                novoJogo();
            }
        });

        builder.setNegativeButton(getString(R.string.não), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
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
            Toast.makeText(this, "Adivinha - versão 0.1", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_novo){
            actionNovo();
            return true;
        }else if(id == R.id.action_estatisticas){
            actionEstatisticas();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionEstatisticas() {
        //todo: mostrar a estatistica
    }

    private void actionNovo() {
        //todo: perguntar se quer desistir e começar um novo jogo
    }
}
