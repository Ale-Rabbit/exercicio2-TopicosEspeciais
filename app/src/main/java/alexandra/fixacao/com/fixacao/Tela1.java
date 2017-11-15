package alexandra.fixacao.com.fixacao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Tela1 extends ListActivity {

    private ListAdapter adapter;
    private String menu[] = {"Cadastrar","Editar","Buscar", "Excluir"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(Tela1.this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);

    }

        protected void onListItemClick(ListView l, View v, int position, long id){
            super.onListItemClick(l, v,position,id);


            Intent go;
            String toDo = "";

            if(position == 0){
                // ir para Tela 2 [CADASTRAR]
                go  = new Intent(Tela1.this,Tela2.class);
            } else{
                // ir para Tela 3 [EDITAR | BUSCAR | EXCLUIR]
                go  = new Intent(Tela1.this,Tela3.class);

                // [EDITAR | BUSCAR | EXCLUIR]
                if(position == 1){
                    toDo = "Editar";
                } else if(position == 2){
                    toDo = "Buscar";
                }else{
                    toDo = "Excluir";
                }

            }

            // Enviar se estou em [EDITAR | BUSCAR | EXCLUIR]
            Bundle data = new Bundle();
            data.putString("toDo",toDo);
            go.putExtras(data);
            startActivity(go);

        }

    }

