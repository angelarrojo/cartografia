package idealista.dron;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class DronTest {

	private Dron dron = new Dron();

	@Test
	public void obtenerUrbanizacionesRango1Test() {
		double latitud = 38.56889;
		double longitud = 40.511107;
		int rango = 1;

		ArrayList<Integer> recorridoDronRango1 = dron.obtenerUrbanizaciones(latitud, longitud, rango);
		List<Integer> urbanizacionesRango1 = obtenerRango1();

		assertEquals(urbanizacionesRango1, ordenarAscendente(recorridoDronRango1));
	}

	@Test
	public void obtenerUrbanizacionesRango2Test() {
		double latitud = 38.56889;
		double longitud = 40.511107;
		int rango = 2;

		ArrayList<Integer> recorridoDronRango2 = dron.obtenerUrbanizaciones(latitud, longitud, rango);
		List<Integer> urbanizacionesRango2 = obtenerRango2();

		assertEquals(urbanizacionesRango2, ordenarAscendente(recorridoDronRango2));
	}

	private List<Integer> obtenerRango2() {
		List<Integer> rango2 = Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 13, 15, 16, 20, 21, 22, 23, 24, 25);
		return rango2;
	}

	private List<Integer> obtenerRango1() {
		List<Integer> rango1 = Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19);
		return rango1;
	}

	private ArrayList<Integer> ordenarAscendente(ArrayList<Integer> urbanizaciones) {
		Collections.sort(urbanizaciones, new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				if (o1.intValue() < o2.intValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return urbanizaciones;
	}
}
