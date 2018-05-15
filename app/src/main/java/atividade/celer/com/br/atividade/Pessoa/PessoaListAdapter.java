package atividade.celer.com.br.atividade.Pessoa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import atividade.celer.com.br.atividade.R;

/**
 * Created by regia on 11/05/2018.
 */

public class PessoaListAdapter extends ArrayAdapter<Pessoa>{

    Context context;
    int layout;
    List<Pessoa> pessoas;

    public PessoaListAdapter(@NonNull Context context, int resource, @NonNull List<Pessoa> objects) {
        super(context,resource,objects);
        this.context =context;
        this.layout = resource;
        this.pessoas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater
                = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(layout,null);
        TextView tvNome = item.findViewById(R.id.pessoa_list_item_nome);
        TextView tvEndereco = item.findViewById(R.id.pessoa_list_item_tvEndereco);
        TextView tvTelefone = item.findViewById(R.id.pessoa_list_item_tvTelefone);
        Pessoa pess = pessoas.get(position);


        tvNome.setText(pess.getNome());
        tvEndereco.setText(pess.getEndereco());
        tvTelefone.setText(pess.getTelefone());



        return item;
    }


}
