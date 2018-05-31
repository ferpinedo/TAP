package org.tapbej.proyectofinal.ordenamiento;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.GeneradorDatos;
import org.tapbej.proyectofinal.modelo.OptimizedSortingChart;
import org.tapbej.proyectofinal.modelo.SortMethod;
import org.tapbej.proyectofinal.modelo.SortingChart;
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

//		SortingChart sortingChart = new SortingChart(GeneradorDatos.peorCaso(30), SortMethod.SELECTION_SORT);
//		gridPane.add(sortingChart, 1, 2);
//		sortingChart.sort(200);
		SortingChart sortingChart2 = new SortingChart(GeneradorDatos.casoPromedio(100), SortMethod.SELECTION_SORT);
		gridPane.add(sortingChart2, 2, 2);
		sortingChart2.sort(100);

	}

}
