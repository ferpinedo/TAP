package org.tapbej.proyectofinal.ordenamiento;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.Paso;
import org.tapbej.proyectofinal.modelo.SortingChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BarChartController
{
	@FXML
	private BarChart barChart1;

	@FXML
	private GridPane gridPane;

//	@FXML
//	private BarChart BarChart2;

	private ArrayList<Integer> xyValues;
	private Timeline timeline;



	@FXML
	public void initialize()
	{
//		xyValues = new ArrayList<>();
//		XYChart.Series dataSeries1 = new XYChart.Series();
////		dataSeries1.setName("2014");
//		dataSeries1.getData().add(new XYChart.Data("2", 2));
//		dataSeries1.getData().add(new XYChart.Data("4"  , 4));
//		dataSeries1.getData().add(new XYChart.Data("7"  , 7));
//		dataSeries1.getData().add(new XYChart.Data("3", 3));
//		dataSeries1.getData().add(new XYChart.Data("5"  , 5));
//		dataSeries1.getData().add(new XYChart.Data("1"  , 1));
//		dataSeries1.getData().add(new XYChart.Data("9", 9));
//		dataSeries1.getData().add(new XYChart.Data("12"  , 12));
//		dataSeries1.getData().add(new XYChart.Data("10"  , 10));
//		barChart1.getData().add(dataSeries1);
//
////		Node testNode = getNodeByRowColumnIndex(1, 2, gridPane);
//
//		final CategoryAxis xAxis = new CategoryAxis();
//		final NumberAxis yAxis = new NumberAxis();
//		final BarChart<String,Number> bc =
//				  new BarChart<String,Number>(xAxis,yAxis);

		final NumberAxis yAxis2 = new NumberAxis();
		final CategoryAxis xAxis2 = new CategoryAxis();
		SortingChart sortingChart = new SortingChart(xAxis2, yAxis2);
		sortingChart.addItem(1);
		sortingChart.addItem(2);
		sortingChart.addItem(3);
		sortingChart.addItem(4);
		sortingChart.addItem(5);
		sortingChart.addItem(6);
		sortingChart.addItem(8);
		sortingChart.addItem(8);
		sortingChart.addItem(8);
		sortingChart.addItem(9);
		sortingChart.addItem(10);
		gridPane.add(sortingChart, 1, 2);


		Stack<Integer[]> pasos = new Stack<>();

		for (int j = 0; j < 100; j++)
		{
			for (int i = 0; i < 11; i++)
			{
				System.out.println("Creating step: " + i + ", " + (10-i));
				pasos.add(new Integer[]{i, 10 - i});
			}
		}

		timeline = new Timeline(new KeyFrame(Duration.millis(100), action ->
		{
			try
			{
				if (pasos.size() != 0)
				{
					Integer[] paso = pasos.pop();
					System.out.println(paso[0] + ", " + paso[1]);
					sortingChart.swapItems(paso[0], paso[1]);
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
