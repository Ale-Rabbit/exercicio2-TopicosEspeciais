package consumer;

import java.util.List;

import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandra on 09/11/17.
 */

public class VeiculoConsumer {

    private VeiculoService veiculoService;
    private Retrofit retrofit;

    public VeiculoConsumer() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(veiculoService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.veiculoService = retrofit.create(VeiculoService.class);
    }

    public Call<Veiculo> postCadastrar(Veiculo veiculo) {
        return this.veiculoService.postCadastrar(veiculo);
    }

    public Call<Void> putAtualizar(Veiculo veiculo) {
        return veiculoService.putAtualizar(veiculo);
    }

    public Call<List<Veiculo>> buscarTodos() {
        return this.veiculoService.buscarTodos();
    }

    public Call<Void> deletePorId(long id) {
        return this.veiculoService.deletePorId(id);
    }
}
