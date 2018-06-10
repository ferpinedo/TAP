package org.tapbej.proyectofinal.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.tapbej.proyectofinal.modelo.GeneradorDatos;
import org.tapbej.proyectofinal.modelo.SortMethod;
import org.tapbej.proyectofinal.modelo.SortingChart;

import java.util.Iterator;


public class SortingGridController extends Controller
{
	@FXML
	private GridPane gridPane;

	@FXML
	private TextField txtDataQuantity;

	@FXML
	private TextField txtMillis;

	@FXML
	private TextField txtSortedPercentage;

	@FXML
	private Label lblSelectionSort;

	@FXML
	private Label lblInsertionSort;

	@FXML
	private Label lblBubbleSort;

	@FXML
	private Label lblShakerSort;

	@FXML
	private Label lblQuickSort;

	@FXML
	private Label lblWorstCase;

	@FXML
	private Label lblBestCase;

	@FXML
	private Label lblRandomCase;

	@FXML
	private Label lblMixedCase;


	private SortingChart[][] sortingCharts;
	int[][] cases;
	SortMethod[] sortMethods = {SortMethod.SELECTION_SORT, SortMethod.BUBBLE_SORT,
			  							 SortMethod.SHAKER_SORT, SortMethod.INSERTION_SORT, SortMethod.QUICK_SORT};
	private int interval;
	private int size;
	private int percent;


	@Override
	void setKeyListener()
	{
//		txtDataQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
//			size = Integer.parseInt(newValue);
//			System.out.println("New size: " + size);
//			drawCharts();
//		});
//
//		txtMillis.textProperty().addListener((observable, oldValue, newValue) -> {
//			interval = Integer.parseInt(newValue);
//			System.out.println("New interval: " + interval);
//			drawCharts();
//		});
//
//		txtSortedPercentage.textProperty().addListener((observable, oldValue, newValue) -> {
//			percent = Integer.parseInt(newValue);
//			System.out.println("New sorted percentage: " + percent);
//			drawCharts();
//		});
	}

	@Override
	void setDefaultCloseOperation()
	{
		mainApp.getPrimaryStage().setOnCloseRequest(e -> stopSorting());
	}

	@Override
	void runSpecificOperations()
	{
		lblBestCase.setTooltip(new Tooltip("Arreglo de enteros ordenados\n" +
				  											 "de forma ascendente"));
		lblWorstCase.setTooltip(new Tooltip("Arreglo de enteros ordenados\n" +
															  "de forma descentende"));
		lblRandomCase.setTooltip(new Tooltip("Arreglo de enteros ordenados\n" +
															  "de forma pseudorandom. Los elementos pueden repetirse\n" +
															  "pero no salirse del límite especificado"));
		lblMixedCase.setTooltip(new Tooltip("Genera un arreglo de enteros con un porcentaje de ellos ordenado y otro con " +
															  "\nelementos pseudorandom"));

		lblSelectionSort.setTooltip(new Tooltip("Encuentra el valor menor de la parte " +
																  "\nposiblemente no ordenada y lo intercambia " +
																  "\ncon la primera posición de dicha parte."));
		lblBubbleSort.setTooltip(new Tooltip("Compara valores adyaccentes y los intercambia " +
															  "\nsi no están ordenados correctamente"));
		lblInsertionSort.setTooltip(new Tooltip("Se inserta elemento por elmento en su posición " +
																  "\ncorrecta. También conocido como \"Baraja\""));
		lblShakerSort.setTooltip(new Tooltip("Compara valores adyaccentes y los intercambia si no " +
															  "\nestán ordenados correctamente, yendo de un" +
															  "\nextremo a otro y viceversa hasta terminar de ordenarlos."));
		lblQuickSort.setTooltip(new Tooltip("Es un algoritmo basado en la técnica de divide y vencerás, " +
															  "\nque permite, en promedio, ordenar n elementos en un tiempo " +
															  "\nproporcional a n log n. "));
		drawCharts();
	}

	@FXML
	public void initialize()
	{
		interval = Integer.parseInt(txtMillis.getText());
		size = Integer.parseInt(txtDataQuantity.getText());
		percent = Integer.parseInt(txtSortedPercentage.getText());
		Platform.runLater(this::drawCharts);
	}

	@FXML
	public void handleTextChanged()
	{
		interval = Integer.parseInt(txtMillis.getText());
		size = Integer.parseInt(txtDataQuantity.getText());
		percent = Integer.parseInt(txtSortedPercentage.getText());
	}

	public void drawCharts()
	{
		System.out.println("Drawing charts");
		cases = new int[4][size];
		cases[0] = GeneradorDatos.mejorCaso(size);
		cases[1] = GeneradorDatos.peorCaso(size);
		cases[2] = GeneradorDatos.casoPromedio(size);
		cases[3] = GeneradorDatos.casoMixto(size, percent);

		/* [method][case] */
		sortingCharts = new SortingChart[sortMethods.length][cases.length];

		for (int i = 0; i < sortMethods.length; i++)
		{
			for (int j = 0; j < cases.length; j++)
			{
				sortingCharts[i][j] = new SortingChart(cases[j], sortMethods[i]);
				AnchorPane anchorPane = new AnchorPane();
				gridPane.add(anchorPane, i + 1, j + 1);

				if (!mainApp.getSecondaryStage().isMaximized() && !mainApp.getSecondaryStage().isFullScreen())
				{
					sortingCharts[i][j].setPrefHeight(107.0);
					sortingCharts[i][j].setPrefWidth(167.0);
					sortingCharts[i][j].setMinHeight(107.0);
					sortingCharts[i][j].setMinWidth(167.0);
				}

				anchorPane.getChildren().add(sortingCharts[i][j]);

				AnchorPane.setTopAnchor(sortingCharts[i][j], 0.0);
				AnchorPane.setBottomAnchor(sortingCharts[i][j], 0.0);
				AnchorPane.setLeftAnchor(sortingCharts[i][j], 0.0);
				AnchorPane.setRightAnchor(sortingCharts[i][j], 0.0);

			}
		}
	}

	public void handleBack()
	{
		stopSorting();
		mainApp.showTopicSelectionView();
		mainApp.getSecondaryStage().close();
	}

	public void handleSort()
	{
		clearCharts();
		interval = Integer.parseInt(txtMillis.getText());
		size = Integer.parseInt(txtDataQuantity.getText());
		percent = Integer.parseInt(txtSortedPercentage.getText());
		drawCharts();
		System.out.println("New size: " + size);


		for (int i = 1; i < sortMethods.length + 1; i++)
		{
			for (int j = 1; j < cases.length + 1; j++)
			{
				((SortingChart) getNodeFromGridPane(gridPane, i, j)).sort(interval);
			}
		}
	}

	public void stopSorting()
	{
		for (int i = 1; i < sortMethods.length + 1; i++)
		{
			for (int j = 1; j < cases.length + 1; j++)
			{
				((SortingChart) getNodeFromGridPane(gridPane, i, j)).stopSorting();
			}
		}
	}

	private void clearCharts()
	{
		Iterator<Node> nodeIterator = gridPane.getChildren().iterator();
		while(nodeIterator.hasNext())
		{
			Node chart = nodeIterator.next();
			if (GridPane.getColumnIndex(chart) != null && GridPane.getRowIndex(chart) != null)
				if (GridPane.getColumnIndex(chart) != 0 && GridPane.getRowIndex(chart) != 0)
					nodeIterator.remove();
		}
	}


	private Node getNodeFromGridPane(GridPane gridPane, int col, int row)
	{
		for (Node node : gridPane.getChildren())
			if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null)
				if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
					return ((AnchorPane)node).getChildren().get(0);
		return null;
	}


}

