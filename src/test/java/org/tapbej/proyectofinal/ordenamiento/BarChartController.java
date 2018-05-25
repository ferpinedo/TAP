package org.tapbej.proyectofinal.ordenamiento;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class BarChartController
{
	@FXML
	private BarChart barChart1;

//	@FXML
//	private BarChart BarChart2;

	@FXML
	public void initialize()
	{
		XYChart.Series dataSeries1 = new XYChart.Series();
//		dataSeries1.setName("2014");

		dataSeries1.getData().add(new XYChart.Data("2", 2));
		dataSeries1.getData().add(new XYChart.Data("4"  , 4));
		dataSeries1.getData().add(new XYChart.Data("7"  , 7));
		dataSeries1.getData().add(new XYChart.Data("3", 3));
		dataSeries1.getData().add(new XYChart.Data("5"  , 5));
		dataSeries1.getData().add(new XYChart.Data("1"  , 1));
		dataSeries1.getData().add(new XYChart.Data("9", 9));
		dataSeries1.getData().add(new XYChart.Data("12"  , 12));
		dataSeries1.getData().add(new XYChart.Data("10"  , 10));


		barChart1.getData().add(dataSeries1);
	}


}
