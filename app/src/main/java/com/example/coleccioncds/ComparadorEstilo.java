package com.example.coleccioncds;

import java.util.Comparator;

public class ComparadorEstilo implements Comparator<Disco> {

	@Override
	public int compare(Disco lhs, Disco rhs) {
		return lhs.get(ClavesDisco.estilo.toString()).toString().compareTo(rhs.get(ClavesDisco.estilo.toString()).toString());
		
	}

}
