package consumer;

import java.util.List;

import pojo.Veiculo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by alexandra on 09/11/17.
 */

public interface VeiculoService {
    static final String URL_BASE = "http://192.168.1.4:8080/";

    @POST("veiculo/")
    Call<Veiculo> postCadastrar(@Body Veiculo veiculo);

    @GET("veiculo/")
    Call<List<Veiculo>> buscarTodos();

    @PUT("veiculo/")
    Call<Void> putAtualizar(@Body Veiculo veiculo);

    @DELETE("veiculo/{id}")
    Call<Void> deletePorId(@Path("id") long id);
}
