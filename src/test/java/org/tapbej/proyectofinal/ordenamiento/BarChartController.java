package org.tapbej.proyectofinal.ordenamiento;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.GeneradorDatos;
import org.tapbej.proyectofinal.modelo.SortingChart;

import java.lang.reflect.Array;
import java.util.*;

public class BarChartController
{

	@FXML
	private GridPane gridPane;

	private Timeline timeline;
	Queue<Integer[]> pasos;

	public void seleccionDirecta(int[] numeros)
	{
		// Uno por uno se verifican los elementos del arreglo si están ordenada
		for (int i = 0; i < numeros.length - 1; i++)
		{
			// Se busca el índice del número más pequeño de la parte no ordenada
			int indiceMinimo = i; // Se inicializa el valor mínimo
			for (int j = i + 1; j < numeros.length; j++)
			{
				if (numeros[j] < numeros[indiceMinimo])
					indiceMinimo = j;
			}
			// Si se encuentra un número menor al que se encuentra en la posición actual a verificar,
			// se intercambian los valores
			if (indiceMinimo != i)
			{
				pasos.add(new Integer[]{indiceMinimo, i});
				int temp = numeros[indiceMinimo];
				numeros[indiceMinimo] = numeros[i];
				numeros[i] = temp;
			}
		}
	}



	@FXML
	public void initialize()
	{
		pasos = new ArrayDeque<>();

		int[] datos = GeneradorDatos.casoPromedio(20);
		GeneradorDatos.imprimirDatos(datos);
//		SortingChart sortingChart = new SortingChart( new CategoryAxis(), new NumberAxis(), new ArrayList<>(Arrays.asList(datos)) );
		SortingChart sortingChart = new SortingChart( new CategoryAxis(), new NumberAxis(), datos );
		gridPane.add(sortingChart, 1, 2);

		seleccionDirecta(datos);
		System.out.println("------------- Datos ordenados: ");
		GeneradorDatos.imprimirDatos(datos);

//		sortingChart.colorizeBar(4, "blue");
//		sortingChart.colorizeBar(5, "blue");
//		sortingChart.colorizeBar(6, "blue");
//		sortingChart.setTimePassed(5.4);


		timeline = new Timeline(new KeyFrame(Duration.millis(500), action ->
		{
			try
			{
				if (pasos.size() != 0)
				{
					Integer[] paso = pasos.poll();
					System.out.println("intercambiando " + paso[0] + ", " + paso[1]);
					sortingChart.swapBars(paso[0], paso[1]);
//					sortingChart.colorizeBar(5, "blue");
				}
				else
				{
					timeline.stop();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}
