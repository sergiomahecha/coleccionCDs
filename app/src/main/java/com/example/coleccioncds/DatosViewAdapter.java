package com.example.coleccioncds;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;

public class DatosViewAdapter implements ViewBinder {

	@Override
	public boolean setViewValue(View arg0, Object arg1, String arg2) {
		if (arg1 instanceof Estilo){
			ImageView view=(ImageView)arg0;
			Estilo estilo=(Estilo) arg1;
			view.setImageResource(estilo.getImagen());
			return true;
		}
		return false;
	}

}
