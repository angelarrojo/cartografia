package idealista.dron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Dron {
	
	private static final String ARRIBA = "Arriba";
	private static final String IZQUIERDA = "Izquierda";
	private static final String ABAJO = "Abajo";
	private static final String DERECHA = "Derecha";
	private static final int ORIGEN_API_IDEALISTA = 13;
	
	public int obtenerIdentificadorUrbanizacion(double coordenadaX, double coordenadaY) {
		return ORIGEN_API_IDEALISTA;
	}

	public int obtenerAdyacente(int identificadorUrbanizacionOrigen, String direccion) {
		return obtenerAdyacenteApiIdealista(identificadorUrbanizacionOrigen, direccion);
	}

	public ArrayList<Integer> obtenerUrbanizaciones(double latitud, double longitud, int rango) {
		int origen = obtenerIdentificadorUrbanizacion(latitud, longitud);
		int urbanizacionInicioRecorrido = obtenerPrimeraUrbanizacionRecorrido(rango, origen);
		ArrayList<Integer> urbanizacionesParaVisitar = obtenerPerimetroUrbanizaciones(rango, origen, urbanizacionInicioRecorrido);
		return urbanizacionesParaVisitar;
	}
	
	private int obtenerPrimeraUrbanizacionRecorrido(int rango, int origen) {
		for (int i = 0; i < rango; i++) {
			origen = obtenerAdyacente(origen, ARRIBA);
		}
		for (int j = 0; j < rango; j++) {
			origen = obtenerAdyacente(origen, IZQUIERDA);
		}
		return origen;
	}
	
	private ArrayList<Integer> obtenerPerimetroUrbanizaciones(int rango, int origen, int urbanizacion) {
		List<String> direcciones = direccionesDron(rango);
		ArrayList<Integer> urbanizacionesParaVisitar = new ArrayList<Integer>();
		urbanizacionesParaVisitar.add(origen);
		
		for (int j=0;j<direcciones.size();j++)
		{
			urbanizacion = obtenerAdyacente(urbanizacion, direcciones.get(j));
			urbanizacionesParaVisitar.add(urbanizacion);
		}
		return urbanizacionesParaVisitar;
	}
	
	private List<String> direccionesDron(int rango) {
		
		int tamanioPerimetro = obtenerTamanioPerimetro(rango);
		String[] direccionesPerimetro = new String[tamanioPerimetro];
		
		int tramoPerimetro = tamanioPerimetro/4;
		IntStream.range(0, tramoPerimetro).forEach(i -> direccionesPerimetro[i] = DERECHA);
		IntStream.range(tramoPerimetro, 2*tramoPerimetro).forEach(i -> direccionesPerimetro[i] = ABAJO);
		IntStream.range(2*tramoPerimetro, 3*tramoPerimetro).forEach(i -> direccionesPerimetro[i] = IZQUIERDA);
		IntStream.range(3*tramoPerimetro, 4*tramoPerimetro).forEach(i -> direccionesPerimetro[i] = ARRIBA);
		
		List<String> direcciones = Arrays.asList(direccionesPerimetro);

		return direcciones;
	}
	
	private int obtenerTamanioPerimetro(int rango) {
		return 4*(2*rango);
	}	
	
	private int obtenerAdyacenteApiIdealista(int identificadorUrbanizacionOrigen, String direccion) {
		int identificadorUrbanizacionResultado = identificadorUrbanizacionOrigen;
		if (direccion == DERECHA) {
			identificadorUrbanizacionResultado = identificadorUrbanizacionOrigen + 1;
		} else if (direccion == IZQUIERDA) {
			identificadorUrbanizacionResultado = identificadorUrbanizacionOrigen - 1;
		} else if (direccion == ABAJO) {
			identificadorUrbanizacionResultado = identificadorUrbanizacionOrigen + 5;
		} else if (direccion == ARRIBA) {
			identificadorUrbanizacionResultado = identificadorUrbanizacionOrigen - 5;
		}

		return identificadorUrbanizacionResultado;
	}
}
