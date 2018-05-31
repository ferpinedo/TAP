package org.tapbej.proyectofinal.modelo;


import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.tapbej.proyectofinal.util.Sorter;

import java.util.ArrayList;

public class OptimizedSortingChart extends BarChart
{
	private ArrayList<Integer> bars;
	private Sorter sorter;

	public OptimizedSortingChart(Axis x, Axis y)
	{
		super(x, y);
		bars = new ArrayList<>();
		this.setAnimated(false);
		hideAxis();
		setGaps(1,1);
	}

	public OptimizedSortingChart(Axis x, Axis y, ArrayList bars)
	{
		super(x, y);
		System.out.println("Bars: " + bars.toString());
		this.bars = bars;
		graphArray();
		this.setAnimated(false);
		hideAxis();
		setGaps(1,1);
	}

	public OptimizedSortingChart(Axis x, Axis y, int[] bars)
	{
		super(x, y);
		this.bars = new ArrayList<>();
		for (int i = 0; i < bars.length; i++)
		{
			addBar(bars[i]);
		}
		graphArray();
		this.setAnimated(false);
		hideAxis();
		setGaps(1,1);
	}

	/**
	 * Adds an item or bar to the chart
	 * @param value
	 */
	public void addBar(int value)
	{
		bars.add(value);
//		System.out.println(bars.toString());
		graphArray();
	}

	/**
	 * Swap two bars position.
	 * @param firstPosition
	 * @param secondPosition
	 */
	public void swapBars(int firstPosition, int secondPosition)
	{
		Integer temp = bars.get(firstPosition);
		bars.set(firstPosition, bars.get(secondPosition));
		bars.set(secondPosition, temp);
		graphArray();
	}

	/**
	 * Colorizes bar
	 * @param position
	 * @param color
	 */
	public void colorizeBar(int position, String color)
	{
		final XYChart.Data<String, Number> bar = (XYChart.Data<String, Number>) ((XYChart.Series)  this.getData().get(0)).getData().get(position);
		System.out.println("Colorizing bar " + position + " (value: " + bar.getYValue() + ") to color " + color);

		bar.getNode().setStyle("-fx-bar-fill: " + color + ";");

	}

	/**
	 * Sets and displays time passed
	 * @param timePassed
	 */
	public void setTimePassed(double timePassed)
	{
		this.setTitle(timePassed + "s");
	}

	/**
	 * Hides time passed
	 */
	public void hideTime()
	{
		this.setTitle("");
	}


	/**
	 * Converts the values of the bars array list to a chart
	 */
	private void graphArray()
	{
		this.getData().clear();
		int position = 0;
		XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
		for (int item: bars)
		{
			dataSeries.getData().add(new XYChart.Data<>(position + "", item));
//			System.out.println("Bar added: <" + position + ", " + item + ">" );
			position++;
		}
		this.getData().add(dataSeries);
		System.out.println("Bars: " + bars.toString());
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
