package alexandra.fixacao.com.fixacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import consumer.VeiculoConsumer;
import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandra on 09/11/17.
 */

public class Tela2 extends Activity {
    private EditText placa, cor;
    private Spinner marca;
    private RadioGroup rg;
    private Button cadastrar;

    private Veiculo veiculo;
    private VeiculoConsumer veiculoConsumer;

    ArrayAdapter<String> arrayAdapter;
    private static String[] arrayMarca = {"Chevrolet","Fiat","Ford","Volkswagen"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);
        iniciaComponentes();

        ArrayAdapter marcasAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayMarca);
        marca.setAdapter(marcasAdapter);

        this.cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PEGAR VALORES DOS EDITTEXT E ADICIONAR EM UM OBJ VEICULO
                veiculo.setPlaca(placa.getText().toString());
                veiculo.setCor(cor.getText().toString());
                veiculo.setMarca(String.valueOf(marca.getSelectedItem()));


                //VERIFICAR RADIOBUTTON
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.radioButtonNovo:
                        veiculo.setNovo(true);
                        break;
                    case R.id.radioButtonSemi:
                        veiculo.setNovo(false);
                        break;
                }

                // Cadastra novo veiculo
                veiculoConsumer.postCadastrar(veiculo).enqueue(new Callback<Veiculo>() {
                    @Override
                    public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Tela2.this, "Cadastrado com sucesso c:", Toast.LENGTH_SHORT).show();
                            Intent irTela1 = new Intent(Tela2.this, Tela1.class);
                            startActivity(irTela1);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Veiculo> call, Throwable t) {
                        Toast.makeText(Tela2.this, "Deu ruim ao cadastrar:c", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    public void iniciaComponentes(){
        this.placa = (EditText) findViewById(R.id.editTextPlaca);
        this.cor = (EditText) findViewById(R.id.editTextCor);
        this.marca = (Spinner) findViewById(R.id.spinnerMarca);
        this.rg = (RadioGroup) findViewById(R.id.radioGroupMarca);
        this.cadastrar = (Button) findViewById(R.id.buttonCadastrar);

        this.veiculo =  new Veiculo();
        this.veiculoConsumer = new VeiculoConsumer();

    }

}


