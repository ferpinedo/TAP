package org.tapbej.proyectofinal.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SearchViewController extends Controller
{

	@FXML
	private ChoiceBox<String> choiceBoxMethod;

	@FXML
	private ChoiceBox<String> choiceBoxDataType;

	// data types indexes
	private final int ORDERED_INDEX = 0;
	private final int UNORDERED_INDEX = 1;
	private final int RANDOM_INDEX = 2;
	private final int MIXED_INDEX = 3;
	private ObservableList<String> dataTypes = FXCollections.observableArrayList("Ordenados", "Desordenados", "Aleatorios", "Mezclados");

	// methods indexes
	private final int SEQUENTIAL_INDEX = 0;
	private final int BST_INDEX = 1;
	private final int HASH_TABLE_INDEX = 2;
	private ObservableList<String> searchMethods = FXCollections.observableArrayList("Secuencial", "Búsqueda binaria", "Hash Table");


	@FXML
	public void initialize()
	{
		choiceBoxDataType.setValue(dataTypes.get(0));
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxMethod.setValue(searchMethods.get(0));
		choiceBoxMethod.setItems(searchMethods);


		choiceBoxDataType.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
			int index = (Integer) newValue;
			switch (index)
			{
				case ORDERED_INDEX:

					break;
				case UNORDERED_INDEX:

					break;
				case RANDOM_INDEX:

					break;
				case MIXED_INDEX:

					break;
			}
		});

		choiceBoxMethod.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
			int index = (Integer) newValue;
			switch (index)
			{
				case SEQUENTIAL_INDEX:
					unlockDataTypes();
					break;
				case BST_INDEX:
					lockDataTypes();
					break;
				case HASH_TABLE_INDEX:
					unlockDataTypes();
					break;
			}
		});
	}

	public void lockDataTypes()
	{
		dataTypes = FXCollections.observableArrayList("Ordenados");
		choiceBoxDataType.setItems(dataTypes);
		choiceBoxDataType.getSelectionModel().selectFirst();
	}

	public void unlockDataTypes()
	{
		if (choiceBoxDataType.getItems().size() == 1)
		{
			dataTypes = FXCollections.observableArrayList("Ordenados", "Desordenados", "Aleatorios", "Mezclados");
			choiceBoxDataType.setItems(dataTypes);
			choiceBoxDataType.getSelectionModel().selectFirst();
		}
	}


	public void handleSearch()
	{
		System.out.println("Displaying search");
	}

	public void handleBack()
	{
		mainApp.getSecondaryStage().close();
		mainApp.getPrimaryStage().requestFocus();
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
