package org.tapbej.proyectofinal.ordenamiento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tapbej.proyectofinal.Main;

import java.io.IOException;

public class TestMain extends Application
{
	private Stage primaryStage;

	public void showBarChartsScreen()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("vista/BarChartTest.fxml"));
		AnchorPane ventana = null;
		try
		{
			ventana = (AnchorPane) loader.load();
			primaryStage = new Stage();
			primaryStage.setScene(new Scene(ventana));
			primaryStage.show();
			BarChartController controlador = loader.getController();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
		showBarChartsScreen();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
