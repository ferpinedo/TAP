package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import org.tapbej.proyectofinal.Main;

public class MenuControlador
{

	private Main main;
	public void setMain(Main main)
	{
		this.main = main;
	}

	@FXML
	public void handleHanoi()
	{
		main.mostrarHanoi();
	}

	@FXML
	public void handleNReinas()
	{
		main.mostrarNReinas();
	}
}
