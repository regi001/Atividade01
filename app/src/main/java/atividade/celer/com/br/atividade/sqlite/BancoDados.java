package atividade.celer.com.br.atividade.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {

	// Nome do banco
	private static final String NOME_BANCO = "atividade";
	// Controle de versao
	private static final int VERSAO_BANCO = 1;
	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
			"DROP TABLE IF EXISTS tbl_pessoa;"
																		 };

	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"CREATE TABLE tbl_pessoa(_id integer primary key, nome text, endereco text, telefone text, site text);"
	};

	private static SQLiteDatabase db;

	public static SQLiteDatabase getDB(Context ctx) {
		if (db == null) {			
			SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
			db = dbHelper.getWritableDatabase();
		}
		return db;
		
	}
	
	

}
