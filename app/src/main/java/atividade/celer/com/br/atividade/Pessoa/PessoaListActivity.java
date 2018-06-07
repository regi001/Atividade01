package atividade.celer.com.br.atividade.Pessoa;

import
        android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import atividade.celer.com.br.atividade.R;

public class PessoaListActivity extends ListActivity {

    PessoaListAdapter adapter;
    List<Pessoa> pessoas;
    PessoaDAO dao;

    final int MENU_NOVO = 1;
    final int MENU_CANCELAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list);

        dao = new PessoaDAO(this);
        pessoas = dao.listar();

        adapter = new PessoaListAdapter(
                this, R.layout.activity_pessoa_list_item, pessoas);

        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // para recuperar os elemtos da lista
        Pessoa pess = pessoas.get(position);

        Intent it = new Intent(this, PessoaCadActivity.class);
        it.putExtra(Pessoa.ID, pess.getId());
        startActivityForResult(it, 1);

    }
    public  void novo(){
        Intent it = new Intent(this, PessoaCadActivity.class);
        // startActivity(it);
        startActivityForResult(it, 1);
    }


    public void cancelar(){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,MENU_NOVO,0,R.string.menu_novo);
        menu.add(0,MENU_CANCELAR,0,R.string.menu_cancelar);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_NOVO:
                novo();

                break;

            case MENU_CANCELAR:
                cancelar();
                break;
        }
        return true;
    }

    public void atualizarLista() {

        pessoas.clear();
        pessoas.addAll(dao.listar());
        adapter.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizarLista();

    }
}
