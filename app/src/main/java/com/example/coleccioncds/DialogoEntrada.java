package com.example.coleccioncds;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DialogoEntrada extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Primero preparamos el interior de la ventana de diálogo XML
        String infService = Context.LAYOUT_INFLATER_SERVICE;

        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(
                infService);
        // Inflamos el componente compuesto definido en el XML
        final View layoutInflado = li.inflate(R.layout.para_nuevo_disco, null);
        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle(R.string.titulo_entrada);
        ventana.setView(layoutInflado);
        Spinner estilo = (Spinner) layoutInflado.findViewById(R.id.spinner1);
        final MainActivity activity = (MainActivity) getActivity();
        Integer[] id_nombres = Estilo.nombres();
        CharSequence[] nombres = new CharSequence[id_nombres.length];

        for (int x = 0; x < id_nombres.length; x++) {
            nombres[x] = getActivity().getResources().getString(id_nombres[x]);
        }

        estilo.setAdapter(new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, nombres));
        ventana.setPositiveButton(R.string.aceptar, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                CharSequence autor = ((EditText) layoutInflado.findViewById(R.id.editTextEntradaAutor)).getText().toString();
                CharSequence titulo = ((EditText) layoutInflado.findViewById(R.id.editTextEntradaTitulo)).getText().toString();
                Integer publicacion = Integer.parseInt(((EditText) layoutInflado.findViewById(R.id.editTextEntradaPublicacion)).getText().toString());
                CheckBox check = ((CheckBox) layoutInflado.findViewById(R.id.checkBox1));
                boolean comprado = check.isChecked() ? true : false;
                //Estilos estilo=Estilos.devuelveEstilo(((Spinner)layoutInflado.findViewById(R.id.spinner1)).getSelectedItem().toString());
                Estilo estilo = Estilo.values()[((Spinner) layoutInflado.findViewById(R.id.spinner1)).getSelectedItemPosition()];
                GestoraDisco gestora = activity.getGestora();
                Disco disco = new Disco(autor, titulo, publicacion, comprado, estilo);
                if (!gestora.existeDisco(disco)) {
                    activity.getGestora().add(disco);
                    activity.getAdaptador().notifyDataSetChanged();
                    Toast.makeText(
                            activity,
                            activity.getResources()
                                    .getString(
                                            R.string.mensaje_nuevodiscoalmacenado),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(
                            activity,
                            activity.getResources()
                                    .getString(
                                            R.string.mensaje_disco_ya_existe),
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        return ventana.create();
    }

}
