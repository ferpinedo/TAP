package org.tapbej.proyectofinal.controlador;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.tapbej.proyectofinal.modelo.*;

import java.util.*;

public class SearchViewController extends Controller
{

	@FXML
	private ChoiceBox<String> choiceBoxMethod;

	@FXML
	private ChoiceBox<String> choiceBoxDataType;

	@FXML
	private VBox vBoxPercentage;

	@FXML
	private VBox vBoxHashSpecs;

	@FXML
	private VBox vBoxNotHashSpecs;

	@FXML
	private HBox hBoxLegend;

	@FXML
	private HBox hBoxHashView;

	@FXML
	private AnchorPane anchorPaneChart;

	@FXML
	private TextField txtDataQuantity;

	@FXML
	private TextField txtTarget;

	@FXML
	private TextField txtInterval;

	@FXML
	private TextField txtPercentage;

	@FXML
	private TextField txtInsert;

	@FXML
	private TextField txtDelete;

	@FXML
	private TextField txtSearch;

	private HashTable hashTable;
	Animation hashAnimation = null;


	// data types indexes
	private final int ORDERED_INDEX = 0;
	private final int UNORDERED_INDEX = 1;
	private final int RANDOM_INDEX = 2;
	private final int MIXED_INDEX = 3;
	private ObservableList<String> dataTypes = FXCollections.observableArrayList("Mejor", "Peor", "Promedio", "Mixto");

	// methods indexes
	private final int SEQUENTIAL_INDEX = 0;
	private final int BST_INDEX = 1;
	private final int HASH_TABLE_INDEX = 2;
	private ObservableList<String> searchMethods = FXCollections.observableArrayList("Secuencial", "Búsqueda binaria", "Tabla hash");

	private SearchingChart searchingChart;


	@FXML
	public void initialize()
	{
		searchingChart = new SearchingChart(SearchMethod.SEQUENTIAL_SEARCH, DataType.ORDERED_LIST, GeneradorDatos.mejorCaso(Integer.parseInt(txtDataQuantity.getText())), 0);
		hashTable = new HashTable();
		setCenter(searchingChart);

		switchToNotHash();

		choiceBoxDataType.setValue(dataTypes.get(0));
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxMethod.setValue(searchMethods.get(0));
		choiceBoxMethod.setItems(searchMethods);


		vBoxPercentage.setVisible(false); // to avoid redundancy


		searchingChart.drawBars();
		searchingChart.colorizeAllBars("#38383852");

		txtDataQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals("")) // to prevent exceptions
			{
				return;
			}

			int data[] = new int[0];
			switch (searchingChart.getDataType())
			{
				case ORDERED_LIST:
					data = GeneradorDatos.mejorCaso(Integer.parseInt(newValue));
					break;
				case UNORDERED_LIST:
					data = GeneradorDatos.peorCaso(Integer.parseInt(newValue));
					break;
				case RANDOM_LIST:
					data = GeneradorDatos.casoPromedio(Integer.parseInt(newValue));
					break;
				case MIXED_LIST:
					data = GeneradorDatos.casoMixto(Integer.parseInt(newValue), Integer.parseInt(txtPercentage.getText()));
					break;
			}
			searchingChart.setBars(data);
			try
			{
				paintSetup(Integer.parseInt(txtTarget.getText()));
			}
			catch (NumberFormatException e)
			{
				paintSetup(0);
			}
		});

		txtTarget.textProperty().addListener((observable, oldValue, newValue) -> {
			searchingChart.setTarget(Integer.parseInt(newValue) - 1);
			paintSetup(Integer.parseInt(newValue));
		});

		txtPercentage.textProperty().addListener((observable, oldValue, newValue) -> {
			choiceBoxMethod.getSelectionModel().select(SEQUENTIAL_INDEX);
			choiceBoxDataType.getSelectionModel().select(ORDERED_INDEX); // to trigger the event
			choiceBoxDataType.getSelectionModel().select(MIXED_INDEX);
		});

		choiceBoxDataType.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
			int index = (Integer) newValue;
			int data[] = new int[0];
			vBoxPercentage.setVisible(false); // to avoid redundancy
			switch (index)
			{
				case ORDERED_INDEX:
					searchingChart.setDataType(DataType.ORDERED_LIST);
					data = GeneradorDatos.mejorCaso(Integer.parseInt(txtDataQuantity.getText()));
					break;
				case UNORDERED_INDEX:
					searchingChart.setDataType(DataType.UNORDERED_LIST);
					data = GeneradorDatos.peorCaso(Integer.parseInt(txtDataQuantity.getText()));
					break;
				case RANDOM_INDEX:
					searchingChart.setDataType(DataType.RANDOM_LIST);
					data = GeneradorDatos.casoPromedio(Integer.parseInt(txtDataQuantity.getText()));
					break;
				case MIXED_INDEX:
					vBoxPercentage.setVisible(true);
					searchingChart.setDataType(DataType.MIXED_LIST);
					data = GeneradorDatos.casoMixto(Integer.parseInt(txtDataQuantity.getText()), Integer.parseInt(txtPercentage.getText()));
					break;
			}
			searchingChart.setBars(data);
			try
			{
				paintSetup(Integer.parseInt(txtTarget.getText()));
			}
			catch (NumberFormatException e)
			{
				paintSetup(0);
			}
		});

		choiceBoxMethod.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
			int index = (Integer) newValue;
			unlockDataTypes(); // avoid redundancy
			switchToNotHash(); // avoid redundancy
			switch (index)
			{
				case SEQUENTIAL_INDEX:
					searchingChart.setMethod(SearchMethod.SEQUENTIAL_SEARCH);
					break;
				case BST_INDEX:
					lockDataTypes();
					searchingChart.setMethod(SearchMethod.BINARY_SEARCH);
					break;
				case HASH_TABLE_INDEX:
					switchToHash();
					searchingChart.setMethod(SearchMethod.HASH_TABLE_SEARCH);
					break;
			}
		});
	}

	private void lockDataTypes()
	{
		dataTypes = FXCollections.observableArrayList("Mejor");
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxDataType.getSelectionModel().selectFirst();
	}

	private void unlockDataTypes()
	{
		if (choiceBoxDataType.getItems().size() == 1)
		{
			dataTypes = FXCollections.observableArrayList("Mejor", "Peor", "Promedio", "Mixto");
			choiceBoxDataType.setItems(dataTypes);
			choiceBoxDataType.getSelectionModel().selectFirst();
		}
	}


	public void handleSearch()
	{
		System.out.println("Displaying search");

		try
		{
			paintSetup(Integer.parseInt(txtTarget.getText()));
			searchingChart.setTarget(Integer.parseInt(txtTarget.getText()));
		}
		catch (NumberFormatException e)
		{
			paintSetup(0);
		}


		int interval = Integer.parseInt(txtInterval.getText());

		searchingChart.search(interval);
	}

	public void handleBack()
	{
		mainApp.getSecondaryStage().close();
		mainApp.getPrimaryStage().requestFocus();
	}

	private void paintSetup(int target)
	{
		searchingChart.colorizeAllBars("#38383852");
		for (int i = 0; i < searchingChart.getBars().length; i++)
		{
			if (target == searchingChart.getBars()[i])
			{
				searchingChart.colorizeBar(i, "#fc2810");
				break;
			}
		}
	}


	private void switchToNotHash()
	{
		// hide the not hash things
		vBoxHashSpecs.setVisible(false);
		setFreeWidth(vBoxHashSpecs, 0);

		hBoxHashView.setVisible(false);
		AnchorPane.setRightAnchor(hBoxHashView, null);
		// setFreeWidth(hBoxHashView, 300);// maybe unnecessary


		// show the hash things
		vBoxNotHashSpecs.setVisible(true);
		setFreeWidth(vBoxNotHashSpecs, 330);

		searchingChart.setVisible(true);
		setFreeWidth(searchingChart, 0);

		hBoxLegend.setVisible(true);

	}






	/*  HASH TABLE SECTION */


	private void switchToHash()
	{
		// hide the not hash things
		vBoxNotHashSpecs.setVisible(false);
		setFreeWidth(vBoxNotHashSpecs, 0);

		searchingChart.setVisible(false);
		setFreeWidth(searchingChart, 0);

		hBoxLegend.setVisible(false);


		// show the hash things
		vBoxHashSpecs.setVisible(true);
		setFreeWidth(vBoxHashSpecs, 190);
		hBoxHashView.setVisible(true);
		AnchorPane.setRightAnchor(hBoxHashView, 50.0);
		// setFreeWidth(hBoxHashView, 300);// maybe unnecessary
	}


	private void setCenter(Node node)
	{
		double leftRightAnchors = 50;
		AnchorPane.setTopAnchor(node, 170.0);
		AnchorPane.setBottomAnchor(node, 30.0);
		AnchorPane.setLeftAnchor(node, leftRightAnchors);
		AnchorPane.setRightAnchor(node, leftRightAnchors);
		anchorPaneChart.getChildren().add(node);
	}

	public void insertHash()
	{
		hashTable.put(Integer.parseInt(txtInsert.getText()));
		drawHashTable();
	}


	public void deleteHash()
	{
		hashTable.remove(Integer.parseInt(txtDelete.getText()));
		drawHashTable();
	}


	public void searchHash()
	{

		hashTable.setTarget(Integer.parseInt(txtSearch.getText()));
		double timePassed = hashTable.search() / 1000;
		//		setTimePassed(timePassed);

		Queue<Comparison> comparisons = hashTable.getComparisons();

		System.out.println("comparisons size: " + comparisons.size());

		hashAnimation = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(txtInterval.getText())), action -> {
			try
			{
				if (comparisons.size() != 0)
				{
					//todo
					Comparison comparison = comparisons.poll();
					System.out.println("colorizing " + comparison.getBarIndex());

					if (comparison.isSuccessful())
					{
						colorizeItem(comparison.getBucket(), comparison.getBarIndex(), "green");
					} else
					{
						colorizeItem(comparison.getBucket(), comparison.getBarIndex(), "red");
					}
				} else
				{
					hashAnimation.stop();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}));
		hashAnimation.setCycleCount(Animation.INDEFINITE);
		hashAnimation.play();

	}

	private void colorizeItem(int bucketNumber, int itemIndex, String color)
	{
		for (int i = 0; i < hBoxHashView.getChildren().size(); i++)
		{
			Label bucketLabel = null;
			try
			{
				VBox bucket = (VBox) ((VBox) hBoxHashView.getChildren().get(i)).getChildren().get(1);
				bucketLabel = (Label) bucket.getChildren().get(0);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				continue;
			}

			if (bucketLabel.getText().equals(bucketNumber + ""))
			{
				VBox listContainer = (VBox) ((VBox) hBoxHashView.getChildren().get(i)).getChildren().get(0);
				listContainer.getChildren().get(itemIndex).setStyle("-fx-background-color: " + color + ";");
			}
		}
		//		.setStyle("-fx-bar-fill: " + color + ";");
	}


	private void setFreeHeight(Region region, int value)
	{
		region.setPrefHeight(value);
		region.setMaxHeight(Region.USE_PREF_SIZE);
		region.setMinHeight(Region.USE_PREF_SIZE);
	}

	private void setFreeWidth(Region region, int value)
	{
		region.setPrefWidth(value);
		region.setMaxWidth(Region.USE_PREF_SIZE);
		region.setMinWidth(Region.USE_PREF_SIZE);
	}


	public void drawHashTable()
	{
		hBoxHashView.getChildren().clear();
		Iterator it = hashTable.getHashTable().entrySet().iterator();

		while (it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("Pair: " + pair.toString());

			String bucketLabel = pair.getKey() + "";

			int containerWidth = 80;
			int bucketHeight = 50;

			VBox container = new VBox();
			container.setAlignment(Pos.BOTTOM_CENTER);
			setFreeWidth(container, containerWidth);
			hBoxHashView.getChildren().add(container);
			//		container.setStyle("-fx-border-color: 'gray'");

			VBox bucket = new VBox();
			bucket.setAlignment(Pos.CENTER);
			bucket.setStyle("-fx-border-color: 'blue'");
			setFreeWidth(container, containerWidth);
			setFreeHeight(bucket, bucketHeight);
			container.getChildren().add(bucket);
			bucket.getChildren().add(new Label(bucketLabel + ""));

			VBox listContainer = new VBox();
			listContainer.setAlignment(Pos.BOTTOM_CENTER);
			listContainer.setPadding(new Insets(0, 0, 10, 0));
			setFreeHeight(listContainer, 259);
			setFreeWidth(listContainer, 84);
			container.getChildren().add(0, listContainer);

			List<Integer> items = (LinkedList<Integer>) pair.getValue();

			for (int i = 0; i < items.size(); i++)
			{
				VBox listItem = new VBox();
				listItem.setAlignment(Pos.CENTER);
				setFreeHeight(listItem, 39);
				setFreeWidth(listItem, 62);
				listItem.getChildren().add(new Label(items.get(i) + ""));
				listItem.setStyle("-fx-border-color: 'cyan'");
				listContainer.getChildren().add(0, listItem);
			}
		}
	}


	@Override
	void setKeyListener()
	{

	}

	@Override
	void setDefaultCloseOperation()
	{

	}

	@Override
	void runSpecificOperations()
	{

	}
}
