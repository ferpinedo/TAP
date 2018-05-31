package org.tapbej.proyectofinal.modelo;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.util.Duration;
import org.tapbej.proyectofinal.util.Sorter;

import java.util.Deque;

public class SortingChart extends BarChart
{
	private Bar[] bars;
	private Sorter sorter;
	private Timeline timeline;

	private String movingColor = "red";
	private String defaultColor = "#000000";
	private String finishColor = "blue";

	private int cursorBar = 0;

	public SortingChart()
	{
		super(new CategoryAxis(), new NumberAxis());
	}

	/**
	 * Constructor for SortingChart
	 * @param values array of values thar are going to be graphed
	 * @param method the method used to sort the bars
	 */
	public SortingChart(int[] values, SortMethod method)
	{
		super(new CategoryAxis(), new NumberAxis());
		this.bars = new Bar[values.length];
		for (int i = 0; i < bars.length; i++)
			this.bars[i] = new Bar(values[i], defaultColor);

		this.sorter = new Sorter(values, method);
		graphArray();
		setDefaults();
	}

	/**
	 * Sort the bars of the bar chart
	 * @param interval of time to use for each movement
	 */
	public void sort(int interval)
	{
		sorter.sort();
		Deque<Movement> movements = sorter.getMovements();

		timeline = new Timeline(new KeyFrame(Duration.millis(interval), action ->
		{
			try
			{
				if (movements.size() != 0)
				{
					Movement movement = movements.pollFirst();
					System.out.println("intercambiando " + movement.from + ", " + movement.to);
					swapBars(movement.from, movement.to);
				}
				else
				{
					bars[cursorBar].setColor(defaultColor);
					colorizeAllBars(finishColor);
					showStats(sorter.getTranscurredMicros(), sorter.getTotalMovements());
					timeline.stop();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * Set default style, hiding the axises and the legend, and
	 * quitting the animations.
	 */
	private void setDefaults()
	{
		this.setAnimated(false);
		this.setLegendVisible(false);
		hideAxis();
		setGaps(1, 1);
	}

	/**
	 * Swap two bars position.
	 *
	 * @param firstPosition
	 * @param secondPosition
	 */
	public void swapBars(int firstPosition, int secondPosition)
	{
		bars[cursorBar].setColor(defaultColor);

		Bar temp = bars[firstPosition];
		cursorBar = firstPosition;
		bars[firstPosition] = bars[secondPosition];
		bars[firstPosition].setColor(movingColor);
		bars[secondPosition] = temp;
		graphArray();
	}

	/**
	 * Colorizes bar
	 *
	 * @param position
	 * @param color
	 */
	public void colorizeBar(int position, String color)
	{
		bars[position].setColor(color);
		graphArray();
	}

	/**
	 * Colorize all bars
	 *
	 * @param color
	 */
	public void colorizeAllBars(String color)
	{
		for (int i = 0; i < bars.length; i++)
			this.bars[i].setColor(color);
		graphArray();
	}

	/**
	 * Sets and displays time passed
	 *
	 * @param microsPassed til bar chart sorted
	 * @param movements used to sort the bar chart
	 */
	public void showStats(double microsPassed, long movements)
	{
		this.setTitle(microsPassed + "micros, en " + movements + " movimientos");
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
		Series<String, Number> dataSeries = new Series<>();
		for (Bar bar : bars)
		{
			dataSeries.getData().add(new Data<>(position + "", bar.getValue()));
			position++;
		}
		this.getData().add(dataSeries);

		position = 0;
		for (Bar bar : bars)
		{
			final Data<String, Number> chartBar = (Data<String, Number>) ((Series) this.getData().get(0)).getData().get(position);
			System.out.println("Colorizing bar " + position + " (value: " + chartBar.getYValue() + ") to defaultColor " + bar.getColor());
			chartBar.getNode().setStyle("-fx-bar-fill: " + bar.getColor() + ";");
			position++;
		}
		System.out.println("Bars: ");
//		System.out.println(barsToString());
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
	 *
	 * @param categoryGap
	 * @param barGap
	 */
	public void setGaps(double categoryGap, double barGap)
	{
		this.setCategoryGap(categoryGap);
		this.setBarGap(barGap);
	}

	public String barsToString()
	{
		String barsString = "[";

		for (Bar bar : bars)
		{
			barsString += bar.toString() + ", ";
		}
		barsString = barsString.substring(0, barsString.length()-1) + "]";

		return barsString;
	}

	public Bar[] getBars()
	{
		return bars;
	}

	public void setBars(Bar[] bars)
	{
		this.bars = bars;
	}

	public Sorter getSorter()
	{
		return sorter;
	}

	public void setSorter(Sorter sorter)
	{
		this.sorter = sorter;
	}

	public Timeline getTimeline()
	{
		return timeline;
	}

	public void setTimeline(Timeline timeline)
	{
		this.timeline = timeline;
	}

	public String getMovingColor()
	{
		return movingColor;
	}

	public void setMovingColor(String movingColor)
	{
		this.movingColor = movingColor;
	}

	public String getDefaultColor()
	{
		return defaultColor;
	}
}
