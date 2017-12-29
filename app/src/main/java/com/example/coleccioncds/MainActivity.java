package com.example.coleccioncds;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity {
    private ListView lista;
    private SimpleAdapter adaptador;
    private GestoraDisco gestora;

    public GestoraDisco getGestora() {
        return gestora;
    }

    public SimpleAdapter getAdaptador() {
        return adaptador;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leerFichero();
        registerForContextMenu(lista);
    }

    private void leerFichero() {
        //MEJOR CON UN STREAM DE OBJETOS
        try {
            FileInputStream fos =
                    openFileInput("discos");
            ObjectInputStream in=new ObjectInputStream(fos);
            gestora=(GestoraDisco)in.readObject();
            Log.i("PRUEBAS", "OBJETO LEIDO");
            in.close();
        } catch (Exception e) {
            gestora=new GestoraDisco();
        }
        lista = (ListView) this.findViewById(R.id.listView1);
        int[] to = {R.id.checkBoxComprado, R.id.imageViewEstilo, R.id.textViewAutor, R.id.textViewTitulo, R.id.textViewPublicacion};
        adaptador = new SimpleAdapter(this, gestora, R.layout.para_listview, ClavesDisco.claves(), to);
        adaptador.setViewBinder(new DatosViewAdapter());
        lista.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        Log.i("PRUEBAS", "Menú contextual creado");
        super.onCreateContextMenu(menu, v, menuInfo);
        final MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        //pos es la posición que ocupa el View sobre el que el usuario hizo click largo

        int pos = info.position;
        this.gestora.remove(pos);
        adaptador.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                DialogoEntrada dialogo = new DialogoEntrada();
                dialogo.show(getFragmentManager(), "NUEVO DISCO");
                break;
            }
            case R.id.orden1: {
                this.gestora.ordenaEstilo();
                this.adaptador.notifyDataSetChanged();
                break;
            }
            case R.id.orden2: {
                this.gestora.ordenaPublicacion();
                this.adaptador.notifyDataSetChanged();
            }
        }
        return true;
    }

    @Override
    protected void onStop() {

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();*/
         /*CHAPUCERO 1
         int numeroDiscos = gestora.size();
        editor.putInt("numeroDiscos", numeroDiscos);
        for (int x = 0; x < numeroDiscos; x++) {
            Disco uno = gestora.get(x);
            editor.putBoolean(ClavesDisco.comprado.toString()+ x, (Boolean) uno.get(ClavesDisco.comprado.toString()));
            editor.putInt(ClavesDisco.publicacion.toString()+ x, (Integer) uno.get(ClavesDisco.publicacion.toString()));
            editor.putString(ClavesDisco.autor.toString() + x, (String) uno.get(ClavesDisco.autor.toString()));
            editor.putString(ClavesDisco.titulo.toString()+ x, (String) uno.get(ClavesDisco.titulo.toString()));
            editor.putString(ClavesDisco.estilo.toString() + x, ((Estilo) uno.get(ClavesDisco.estilo.toString())).name());
        }*/
       /* UN POCO MEJOR
       Set<String> conjunto=new HashSet<>();
        String[] claves=ClavesDisco.claves();
       for (Disco uno:gestora){
           String unDisco=uno.get(claves[0])+"#"+uno.get(claves[1])+"#"+uno.get(claves[2])+"#"+uno.get(claves[3])+"#"+uno.get(claves[4]);
           conjunto.add(unDisco);
       }
        editor.putStringSet("conjunto",conjunto);
        editor.commit();*/
        //mejor un stream de objetos
        escribirFichero();
        super.onStop();
    }

    private void escribirFichero() {
        try {
            FileOutputStream fos =
                    openFileOutput("discos",
                            Context.MODE_PRIVATE);
            ObjectOutputStream out=new ObjectOutputStream(fos);
            out.writeObject(gestora);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        this.leerFichero();
        super.onStart();
    }
}
