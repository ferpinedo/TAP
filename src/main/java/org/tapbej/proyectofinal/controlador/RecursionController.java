package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;

public class RecursionController extends Controller
{

	@FXML
	public void handleHanoi()
	{
		mainApp.showHanoi();
	}

	@FXML
	public void handleNReinas()
	{
		mainApp.showNQueens();
	}


	public void handleBack()
	{
		mainApp.showTopicSelectionView();
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
