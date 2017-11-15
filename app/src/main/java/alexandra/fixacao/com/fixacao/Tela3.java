package alexandra.fixacao.com.fixacao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import consumer.VeiculoConsumer;
import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandra on 13/11/17.
 */

public class Tela3 extends ListActivity {
    private List<Veiculo> arrayVeiculo;
    private VeiculoConsumer veiculoConsumer;
    private ArrayAdapter<Veiculo> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // não precisa de layout

        this.veiculoConsumer = new VeiculoConsumer();

        // busca veiculos cadastrados
        this.veiculoConsumer.buscarTodos().enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Call<List<Veiculo>> call, Response<List<Veiculo>> response) {
                if(response.isSuccessful()){
                    arrayVeiculo = response.body();
                    adapter = new ArrayAdapter<Veiculo>(Tela3.this, android.R.layout.simple_list_item_1, arrayVeiculo);
                    setListAdapter(adapter); // para setar
                }
            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {
                Toast.makeText(Tela3.this, "Não rolou buscar todos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String toDo = getIntent().getExtras().getString("toDo");

        if(toDo.equals("Editar")){
            Veiculo veiculoSelecionado = arrayVeiculo.get(position);
            Intent goTela4 =  new Intent(Tela3.this, Tela4.class);
            Bundle data = new Bundle();
            data.putSerializable("veiculo", veiculoSelecionado);
            goTela4.putExtras(data);
            startActivity(goTela4);

        } if(toDo.equals("Excluir")){
            final Veiculo veiculo = arrayVeiculo.get(position);
            this.veiculoConsumer.deletePorId(veiculo.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(Tela3.this, "Excluido com sucesso c:", Toast.LENGTH_SHORT).show();
                        adapter.remove(veiculo);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(Tela3.this, "Não rolou apagar :c", Toast.LENGTH_SHORT).show();
                }
            });

        } if(toDo.equals("Buscar")){
            Toast.makeText(Tela3.this, "Sem interação", Toast.LENGTH_SHORT).show();
        }
    }
}
