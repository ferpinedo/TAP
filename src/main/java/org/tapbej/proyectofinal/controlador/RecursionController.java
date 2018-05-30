package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import org.tapbej.proyectofinal.Main;

public class RecursionController extends Controller
{

	private Main main;


	@FXML
	public void handleHanoi()
	{
		main.showHanoi();
	}

	@FXML
	public void handleNReinas()
	{
		main.mostrarNReinas();
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
