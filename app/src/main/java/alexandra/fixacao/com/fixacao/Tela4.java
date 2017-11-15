package alexandra.fixacao.com.fixacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import consumer.VeiculoConsumer;
import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandra on 13/11/17.
 */

public class Tela4 extends Activity {

    private EditText placa, cor;
    private Spinner marca;
    private RadioGroup rg;
    private RadioButton rbNovo;
    private RadioButton rbSemi;
    private Button cadastrar;
    private Button excluir;

    private Veiculo veiculo;
    private VeiculoConsumer veiculoConsumer;

    private static String[] arrayMarca = {"Chevrolet","Fiat","Ford","Volkswagen"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);
        iniciaComponentes();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayMarca);
        marca.setAdapter(adapter);

        this.veiculo = (Veiculo) getIntent().getExtras().getSerializable("veiculo");

        // carregar com valores de veiculo selecionado

        this.cadastrar.setText("Atualizar");

        this.placa.setText(veiculo.getPlaca());
        this.cor.setText(veiculo.getCor());

        switch (veiculo.getMarca()){
            case "Volkswage":
                marca.setSelection(0);
               break;
            case "Fiat":
                marca.setSelection(1);
                break;
            case "Ford":
                marca.setSelection(2);
                break;
            case "Volkswagen":
                marca.setSelection(3);
                break;
        }

        if(veiculo.isNovo()){
            this.rbNovo.setChecked(true);
        } else{
            this.rbSemi.setChecked(true);
        }

        this.excluir.setVisibility(View.VISIBLE);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PEGAR (NOVOS) VALORES DOS EDITTEXT E ADICIONAR EM UM OBJ VEICULO
                veiculo.setPlaca(placa.getText().toString());
                veiculo.setCor(cor.getText().toString());
                veiculo.setMarca(String.valueOf(marca.getSelectedItem()));


                //VERIFICAR (MUDANÇA DE) RADIOBUTTON
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.radioButtonNovo:
                        veiculo.setNovo(true);
                        break;
                    case R.id.radioButtonSemi:
                        veiculo.setNovo(false);
                        break;
                }

                veiculoConsumer.putAtualizar(veiculo).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Tela4.this, "Atualizado com sucesso c:", Toast.LENGTH_SHORT).show();
                            Intent irTela1 = new Intent(Tela4.this, Tela1.class);
                            startActivity(irTela1);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tela4.this, "Deu ruim ao atualizar :c", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veiculoConsumer.deletePorId(veiculo.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Tela4.this, "Excluido com sucesso c:", Toast.LENGTH_SHORT).show();
                            Intent irTela1 = new Intent(Tela4.this, Tela1.class);
                            startActivity(irTela1);

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tela4.this, "Ops :c", Toast.LENGTH_SHORT).show();

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
        this.rbNovo = findViewById(R.id.radioButtonNovo);
        this.rbSemi = findViewById(R.id.radioButtonSemi);
        this.cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        this.excluir = (Button) findViewById(R.id.buttonExcluir);

        this.veiculo =  new Veiculo();
        this.veiculoConsumer = new VeiculoConsumer();
    }
}
