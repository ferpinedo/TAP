package org.tapbej.proyectofinal.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.tapbej.proyectofinal.modelo.*;

public class SearchViewController extends Controller
{

	@FXML
	private ChoiceBox<String> choiceBoxMethod;

	@FXML
	private ChoiceBox<String> choiceBoxDataType;

	@FXML
	private AnchorPane anchorPaneChart;

	@FXML
	private TextField txtDataQuantity;

	@FXML
	private TextField txtTarget;

	@FXML
	private TextField txtPercentage;


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
	private ObservableList<String> searchMethods = FXCollections.observableArrayList("Secuencial", "Búsqueda binaria", "Hash Table");

	private SearchingChart searchingChart;


	@FXML
	public void initialize()
	{
		choiceBoxDataType.setValue(dataTypes.get(0));
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxMethod.setValue(searchMethods.get(0));
		choiceBoxMethod.setItems(searchMethods);

		// todo: type check
		searchingChart = new SearchingChart(SearchMethod.SEQUENTIAL_SEARCH, DataType.ORDERED_LIST, GeneradorDatos.mejorCaso(Integer.parseInt(txtDataQuantity.getText())), 0);
		AnchorPane.setTopAnchor(searchingChart, 170.0);
		AnchorPane.setBottomAnchor(searchingChart, 30.0);
		AnchorPane.setLeftAnchor(searchingChart, 100.0);
		AnchorPane.setRightAnchor(searchingChart, 100.0);
		anchorPaneChart.getChildren().add(searchingChart);

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
			switch (index)
			{
				case SEQUENTIAL_INDEX:
					unlockDataTypes();
					searchingChart.setMethod(SearchMethod.SEQUENTIAL_SEARCH);
					break;
				case BST_INDEX:
					lockDataTypes();
					searchingChart.setMethod(SearchMethod.BINARY_SEARCH);
					break;
				case HASH_TABLE_INDEX:
					unlockDataTypes();
					searchingChart.setMethod(SearchMethod.HASH_TABLE_SEARCH);
					break;
			}
		});
	}

	public void lockDataTypes()
	{
		dataTypes = FXCollections.observableArrayList("Mejor");
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxDataType.getSelectionModel().selectFirst();
	}

	public void unlockDataTypes()
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

		int interval = 20;

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
