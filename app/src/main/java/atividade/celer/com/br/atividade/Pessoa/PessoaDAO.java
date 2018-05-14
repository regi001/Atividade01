package atividade.celer.com.br.atividade.Pessoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import atividade.celer.com.br.atividade.sqlite.BancoDados;

/**
 * Created by regia on 11/05/2018.
 */

public class PessoaDAO {

    SQLiteDatabase db;

    public PessoaDAO(Context context){db = BancoDados.getDB(context);}

    public void salvar(Pessoa pes){
        ContentValues values = new ContentValues();
        values.put(Pessoa.NOME,pes.getNome());
        values.put(Pessoa.ENDERECO,pes.getEndereco());
        values.put(Pessoa.TELEFONE,pes.getTelefone());
        values.put(Pessoa.SITE,pes.getSite());

        db.insert(Pessoa.TABELA,null,values);
    }
    public Pessoa buscar(String id){

        String[] colunas = Pessoa.COLUNAS;
        String[] where = new  String[]{id};

        Cursor c = db.query(Pessoa.TABELA,colunas,"_id=?",where,null,null,null,null);
        c.moveToFirst();

        Pessoa pes = new Pessoa();
        pes.setId(Long.valueOf(c.getLong(c.getColumnIndex(Pessoa.ID))));
        pes.setNome(c.getString(c.getColumnIndex(Pessoa.NOME)));
        pes.setEndereco(c.getString(c.getColumnIndex(Pessoa.ENDERECO)));
        pes.setTelefone(c.getString(c.getColumnIndex(Pessoa.TELEFONE)));
        pes.setSite(c.getString(c.getColumnIndex(Pessoa.SITE)));

        return pes;
    }
    public void alterar(Pessoa pes){

        String[] where = new  String[]{String.valueOf(pes.getId())};
        ContentValues values = new ContentValues();

        values.put(Pessoa.NOME,pes.getNome());
        values.put(Pessoa.ENDERECO,pes.getEndereco());
        values.put(Pessoa.TELEFONE,pes.getTelefone());
        values.put(Pessoa.SITE,pes.getSite());

        db.update(Pessoa.TABELA,values,"_id=?",where);
    }
    public void excluir(String id){

        String[] where = new  String[]{id};
        db.delete(Pessoa.TABELA,"_id = ?",where);
    }
    public List<Pessoa> listar(){

        String[] colunas = Pessoa.COLUNAS;
        Cursor c = db.query(Pessoa.TABELA,colunas,null,null,null,null,null);
        List<Pessoa> pessoas=new ArrayList<Pessoa>();

        if (c.moveToFirst());
        do {
            Pessoa pes = new Pessoa();

            pes.setId(c.getLong(c.getColumnIndex(Pessoa.ID)));
            pes.setNome(c.getString(c.getColumnIndex(Pessoa.NOME)));
            pes.setEndereco(c.getString(c.getColumnIndex(Pessoa.ENDERECO)));
            pes.setTelefone(c.getString(c.getColumnIndex(Pessoa.TELEFONE)));
            pes.setSite(c.getString(c.getColumnIndex(Pessoa.SITE)));

            pessoas.add(pes);

            Log.i("lista",pes.getId()+ pes.getNome());
        }while (c.moveToNext());
        return pessoas;
    }

}
