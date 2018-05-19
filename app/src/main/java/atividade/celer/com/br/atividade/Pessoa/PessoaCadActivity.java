package atividade.celer.com.br.atividade.Pessoa;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import atividade.celer.com.br.atividade.R;

public class PessoaCadActivity extends Activity {

    EditText edtId,edtNome, edtEndereco, edtTelefone, edtSite;
    Button btnConfirmar, btnLigar, btnIr;
    PessoaDAO dao;

    final int MENU_SALVAR = 1;
    final int MENU_BUSCAR = 2;
    final int MENU_ALTERAR = 3;
    final int MENU_EXCLUIR = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cad);

        dao = new PessoaDAO(this);


        edtId = findViewById(R.id.pessoaCad_edtId);
        edtNome = findViewById(R.id.pessoaCad_edtNome);
        edtEndereco = findViewById(R.id.pessoaCad_edtEndereco);
        edtTelefone = findViewById(R.id.pessoaCad_edtTelefone);
        edtSite = findViewById(R.id.pessoaCad_edtSite);

        Intent it = getIntent();
        Long id = it.getLongExtra(Pessoa.ID,0);

        if (id!=0){
            edtId.setText(String.valueOf(id));
            buscarPessoa();
        }

        btnConfirmar = findViewById(R.id.pessoaCad_btnConfirmar);
        btnLigar = findViewById(R.id.pessoaCad_btnLigar);
        btnIr = findViewById(R.id.pessoaCad_btnIr);



        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_SALVAR,0,R.string.menu_salvar);
        menu.add(0,MENU_BUSCAR,0,R.string.menu_buscar);
        menu.add(0,MENU_ALTERAR,0,R.string.menu_alterar);
        menu.add(0,MENU_EXCLUIR,0,R.string.menu_excluir);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_SALVAR:
                salvarPessoa();
                break;

            case MENU_BUSCAR:
                buscarPessoa();
                break;

            case MENU_ALTERAR:
                alterarPessoa();
                break;

            case MENU_EXCLUIR:
                excluirPessoa();
                break;

        }
        return true;
    }
    public void salvarPessoa(){
        Pessoa pes = new Pessoa();

        pes.setNome(edtNome.getText().toString());
        pes.setEndereco(edtEndereco.getText().toString());
        pes.setTelefone(edtTelefone.getText().toString());
        pes.setSite(edtSite.getText().toString());

        dao.salvar(pes);
        finish();
    }
    public void buscarPessoa(){

        Pessoa pes = dao.buscar(edtId.getText().toString());

        edtNome.setText(pes.getNome());
        edtEndereco.setText(pes.getEndereco());
        edtTelefone.setText(pes.getTelefone());
        edtSite.setText(pes.getSite());

    }
    public void alterarPessoa(){
        Pessoa pes = new Pessoa();

        pes.setId(Long.valueOf(edtId.getText().toString()));
        pes.setNome(edtNome.getText().toString());
        pes.setEndereco(edtEndereco.getText().toString());
        pes.setTelefone(edtTelefone.getText().toString());
        pes.setSite(edtSite.getText().toString());

        dao.alterar(pes);
        finish();

    }
    public void excluirPessoa(){

        dao.excluir(edtId.getText().toString());
        finish();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void ligar (View view){
        fazerLigacao();
    }
    public void fazerLigacao(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
         if (permissionCheck != PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(this, new String[]{"Manifest.permission.CALL_PHONE"},1);
        }else{
        startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+edtTelefone.getText().toString())));
         }
         }

        @Override
         public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
           switch (requestCode){
              case 1:
                if ((grantResults.length>0)&&(grantResults[0]== PackageManager.PERMISSION_GRANTED)){
                    fazerLigacao();
         }
              break;
         }
           }

         public void navegar (View view){
          navegador();
    }
     public void navegador(){
       int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
       if (permissionCheck != PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(this, new String[]{"Manifest.permission.INTERNET"},1);
      }else{
    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://"+edtSite.getText().toString())));
      }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("appmain","passou resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("appmain","passou start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("appmain","passou pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("appmain","passou stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appmain","passou destroy");
    }
    }

