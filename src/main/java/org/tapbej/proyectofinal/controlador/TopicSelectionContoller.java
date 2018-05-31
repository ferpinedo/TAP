package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TopicSelectionContoller extends Controller
{
	//	private Button btnRecursion;
	//	@FXML
	//
	//	@FXML
	//	private Button btnSorting;
	//
	//	@FXML
	//	private Button btnSearch;

	@FXML
	private Label lblDescription;

	private final String INTRO_TEXT = "Intro";
	private final String RECURSION_TEXT = "Recursion";
	private final String SORTING_TEXT = "Sorting";
	private final String SEARCH_TEXT = "Search";


	@FXML
	public void initialize()
	{
		lblDescription.setText(INTRO_TEXT);
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


	public void handleRecursion()
	{
		mainApp.showRecursionView();
	}

	public void handleSorting()
	{
		mainApp.showSortingView();
	}

	public void handleSearch()
	{
		mainApp.showSearchView();
	}


	public void displayIntro()
	{
		lblDescription.setText(INTRO_TEXT);
	}

	public void displayRecursionDescription()
	{
		lblDescription.setText(RECURSION_TEXT);
	}

	public void displaySortingDescription()
	{
		lblDescription.setText(SORTING_TEXT);
	}

	public void displaySearchDescription()
	{
		lblDescription.setText(SEARCH_TEXT);
	}

}
