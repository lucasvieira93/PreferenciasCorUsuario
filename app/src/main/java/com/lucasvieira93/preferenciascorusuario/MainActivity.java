package com.lucasvieira93.preferenciascorusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;
    private ConstraintLayout layout;
    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroupId);
        botaoSalvar = findViewById(R.id.botaoSalvarId);
        layout = findViewById(R.id.layoutId);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();

                if (idRadioButtonEscolhido > 0){
                    radioButtonSelecionado = findViewById(idRadioButtonEscolhido);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String corEscolhida = radioButtonSelecionado.getText().toString();
                    editor.putString("corEscolhida", radioButtonSelecionado.getText().toString());
                    editor.commit();
                    setBackgroud(corEscolhida);

                }

            }
        });

        //recuperar a cor salva
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("corEscolhida")){
        String corRecuperada = sharedPreferences.getString("corEscolhida", "Laranja");
        setBackgroud(corRecuperada);
        }
    }

    private void setBackgroud(String cor){

        if (cor.equals("Azul")) {
            layout.setBackgroundColor(Color.parseColor("#2196F3"));
        } else if (cor.equals("Laranja")) {
            layout.setBackgroundColor(Color.parseColor("#FF5722"));
        } else {
            layout.setBackgroundColor(Color.parseColor("#4CAF50"));
        }

    }
}
