package org.tapbej.proyectofinal.ordenamiento;

import com.sun.prism.PixelFormat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.*;
import org.tapbej.proyectofinal.util.Sorter;

import java.util.*;

public class BarChartController
{

	@FXML
	private GridPane gridPane;

	private Timeline timeline;
	Deque<int[]> pasos;

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
				pasos.add(new int[]{indiceMinimo, i});
				int temp = numeros[indiceMinimo];
				numeros[indiceMinimo] = numeros[i];
				numeros[i] = temp;
			}
		}
	}



	@FXML
	public void initialize()
	{
		int size = 50;
		int interval = 100;
		int[] data = GeneradorDatos.casoPromedio(size);

		SortingChart sortingChart = new SortingChart(data, SortMethod.SELECTION_SORT);
		gridPane.add(sortingChart, 0, 0);
		sortingChart.sort(interval);

		SortingChart sortingChart2 = new SortingChart(data, SortMethod.BUBBLE_SORT);
		gridPane.add(sortingChart2, 1, 0);
		sortingChart2.sort(interval);

		SortingChart sortingChart3 = new SortingChart(data, SortMethod.INSERTION_SORT);
		gridPane.add(sortingChart3, 0, 1);
		sortingChart3.sort(interval);

		SortingChart sortingChart4 = new SortingChart(data, SortMethod.SHAKER_SORT);
		gridPane.add(sortingChart4, 1, 1);
		sortingChart4.sort(interval);

	}

}
