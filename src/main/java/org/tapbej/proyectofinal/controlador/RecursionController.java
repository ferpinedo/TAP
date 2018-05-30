package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import org.tapbej.proyectofinal.Main;

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
		mainApp.mostrarNReinas();
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
