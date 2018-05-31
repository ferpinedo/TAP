package org.tapbej.proyectofinal.modelo;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;


public class SortingChart extends BarChart
{
	private ArrayList<Integer> items;

	public SortingChart(Axis x, Axis y)
	{
		super(x, y);
		items = new ArrayList<>();
		this.setAnimated(false);
		hideAxis();
		setGaps(1,1);
	}

	/**
	 * Adds an item or bar to the chart
	 * @param value
	 */
	public void addItem(int value)
	{
		items.add(value);
//		System.out.println(items.toString());
		graphArray();
	}

	/**
	 * Swap two bars position.
	 * @param firstPosition
	 * @param secondPosition
	 */
	public void swapItems(int firstPosition, int secondPosition)
	{
		Integer temp = items.get(firstPosition);
		items.set(firstPosition, items.get(secondPosition));
		items.set(secondPosition, temp);
		graphArray();
	}

	/**
	 * Converts the values of the items array list to a chart
	 */
	private void graphArray()
	{
		this.getData().clear();
		int position = 0;
		XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
		for (int item: items)
		{
			dataSeries.getData().add(new XYChart.Data<>(position + "", item));
//			System.out.println("Bar added: <" + position + ", " + item + ">" );
			position++;
		}
		this.getData().add(dataSeries);
	}

	/**
	 * Completely hide both X and Y axises.
	 */
	private void hideAxis()
	{
		this.setHorizontalGridLinesVisible(false);
		this.setVerticalGridLinesVisible(false);
		this.getXAxis().setTickLabelsVisible(false);
		this.getYAxis().setTickLabelsVisible(false);
		this.getYAxis().setTickMarkVisible(false);
		this.getYAxis().setVisible(false);
		this.getYAxis().setOpacity(0);
		this.getXAxis().setOpacity(0);
	}

	/**
	 * Sets the spacing between each bar.
	 * @param categoryGap
	 * @param barGap
	 */
	public void setGaps(double categoryGap, double barGap )
	{
		this.setCategoryGap(categoryGap);
		this.setBarGap(barGap);
	}



}
