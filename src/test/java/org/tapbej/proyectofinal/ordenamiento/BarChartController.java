package org.tapbej.proyectofinal.ordenamiento;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.SortingChart;

import java.util.ArrayList;
import java.util.Stack;

public class BarChartController
{

	@FXML
	private GridPane gridPane;

	private Timeline timeline;



	@FXML
	public void initialize()
	{
		final NumberAxis yAxis2 = new NumberAxis();
		final CategoryAxis xAxis2 = new CategoryAxis();
		SortingChart sortingChart = new SortingChart(xAxis2, yAxis2);
		sortingChart.addBar(1);
		sortingChart.addBar(2);
		sortingChart.addBar(3);
		sortingChart.addBar(4);
		sortingChart.addBar(5);
		sortingChart.addBar(6);
		sortingChart.addBar(8);
		sortingChart.addBar(8);
		sortingChart.addBar(8);
		sortingChart.addBar(9);
		sortingChart.addBar(10);
		gridPane.add(sortingChart, 1, 2);

		sortingChart.colorizeBar(4, "blue");
		sortingChart.colorizeBar(5, "blue");
		sortingChart.colorizeBar(6, "blue");
		sortingChart.setTimePassed(5.4);

		Stack<Integer[]> pasos = new Stack<>();

		for (int j = 0; j < 100; j++)
		{
			for (int i = 0; i < 11; i++)
			{
//				System.out.println("Creating step: " + i + ", " + (10-i));
				pasos.add(new Integer[]{i, 10 - i});
			}
		}

		timeline = new Timeline(new KeyFrame(Duration.millis(1000), action ->
		{
			try
			{
				if (pasos.size() != 0)
				{
					Integer[] paso = pasos.pop();
					System.out.println(paso[0] + ", " + paso[1]);
					sortingChart.swapBars(paso[0], paso[1]);
					sortingChart.colorizeBar(5, "blue");
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
