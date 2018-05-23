package org.tapbej.recursividad.controlador;

import javafx.fxml.FXML;
import org.tapbej.recursividad.Main;

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
