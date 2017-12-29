package com.example.coleccioncds;

import java.util.Comparator;

public class ComparadorPublicacion implements Comparator<Disco> {

	@Override
	public int compare(Disco lhs, Disco rhs) {
		
		return ((Integer)lhs.get(ClavesDisco.publicacion.toString())).compareTo((Integer)rhs.get(ClavesDisco.publicacion.toString()));
	}

}
