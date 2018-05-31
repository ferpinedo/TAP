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

	@FXML
	public void initialize()
	{
		ObservableList<String> dataTypes = FXCollections.observableArrayList("Ordenados", "Desordenados", "Aleatorios", "Mezclados");
		ObservableList<String> searchMethods = FXCollections.observableArrayList("Búsqueda binaria");

		choiceBoxDataType.setValue(dataTypes.get(0));
		choiceBoxDataType.setItems(dataTypes);


		choiceBoxMethod.setValue(searchMethods.get(0));
		choiceBoxMethod.setItems(searchMethods);
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
