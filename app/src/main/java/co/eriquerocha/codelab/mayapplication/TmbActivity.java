package co.eriquerocha.codelab.mayapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.tiagoaguiar.codelab.myapplication.R;

public class TmbActivity extends AppCompatActivity {
    private EditText editHeight;
    private EditText editWeight;
    private EditText editAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);
        editHeight = findViewById(R.id.edit_tmb_height);
        editWeight = findViewById(R.id.edit_tmb_weight);
        editAge = findViewById(R.id.edit_tmb_age);

        Button btnSend = findViewById(R.id.btn_tmb_send);

        btnSend.setOnClickListener(view -> {
            if(!validate()){
                Toast.makeText(TmbActivity.this, R.string.fields_messages, Toast.LENGTH_LONG).show();
                return;
            }
            String sAltura = editHeight.getText().toString();
            String sPeso = editWeight.getText().toString();
            String sIdade = editAge.getText().toString();

            int altura = Integer.parseInt(sAltura);
            int peso = Integer.parseInt(sPeso);
            int idade = Integer.parseInt(sIdade);

            double result = calculateTmb(peso, altura, idade);
            double resultPouco = calculateTmbPouco(peso, altura, idade);
            double resultModerado = calculateTmbModerado(peso, altura, idade);
            double resultIntenso = calculateTmbIntenso(peso, altura, idade);
            double resultMuitoIntenso = calculateTmbMuitoIntenso(peso, altura, idade);
            Log.d("teste","nenhuma"+result);
            Log.d("teste","pouco"+resultPouco);
            Log.d("teste","moderado"+resultModerado);
            Log.d("teste","intenso"+resultIntenso);
            Log.d("teste","muito intenso"+resultMuitoIntenso);

           // int tmbResponseId = tmbResponse(result);

            // Toast.makeText(ImcActivity.this, imcResponseId, Toast.LENGTH_LONG).show();

            AlertDialog dialog = new AlertDialog.Builder(TmbActivity.this)
                    .setTitle(getString(R.string.title_tmb))
                    .setMessage(getString(R.string.tmb_response, result) +
                            getString(R.string.tmb_response2, resultPouco)+
                            getString(R.string.tmb_response3, resultModerado)+
                            getString(R.string.tmb_response4, resultIntenso)+
                            getString(R.string.tmb_response5, resultMuitoIntenso))


                    .setPositiveButton(android.R.string.ok, (dialog1, which) -> {

                    })
                    .create();
            dialog.show();

            //gerenciando o teclado
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
        });
    }

    private double calculateTmb(int peso, int altura, int idade){
        return 66 + (((double)13.8 *peso) + ((double)5 * altura) - ((double)6.8 * idade)*2);
    }
    private double calculateTmbPouco(int peso, int altura, int idade){
        return 66 + (((double)13.8 *peso) + ((double)5 * altura) - ((double)6.8 * idade)*1.725);
    }
    private double calculateTmbModerado(int peso, int altura, int idade){
        return 66 + (((double)13.8 *peso) + ((double)5 * altura) - ((double)6.8 * idade)*1.55);
    }
    private double calculateTmbIntenso(int peso, int altura, int idade){
        return 66 + (((double)13.8 *peso) + ((double)5 * altura) - ((double)6.8 * idade)*1.375);
    }
    private double calculateTmbMuitoIntenso(int peso, int altura, int idade){
        return 66 + (((double)13.8 *peso) + ((double)5 * altura) - ((double)6.8 * idade)*1);
    }

    private boolean validate(){
        if(!editHeight.getText().toString().startsWith("0")
                && !editWeight.getText().toString().startsWith("0")
                && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty()
        ) {
            return true;
        }
        return false;
    }

}
