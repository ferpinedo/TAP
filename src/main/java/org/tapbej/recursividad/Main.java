package org.tapbej.recursividad;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tapbej.recursividad.controlador.MenuControlador;
import org.tapbej.recursividad.controlador.NReinasController;


public class Main extends Application
{
	private Stage primaryStage;

	public static void main(String [] args)
	{
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
      this.primaryStage.setTitle("Recursividad");
      
      mostrarVistaPrincipal();
	}
	
	
	private void mostrarVistaPrincipal()
	{
		try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("vista/MenuView.fxml"));
         AnchorPane ventana = (AnchorPane) loader.load();

         primaryStage.setScene(new Scene(ventana));
         primaryStage.show();
         MenuControlador controlador = loader.getController();
         controlador.setMain(this);

      }
		catch (IOException e)
      {
         e.printStackTrace();
      }
	}


	public void mostrarHanoi()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/TorresHanoiView.fxml"));
			AnchorPane ventana = (AnchorPane) loader.load();
			Stage hanoiStage = new Stage();
			hanoiStage.setScene(new Scene(ventana));
			hanoiStage.show();
			hanoiStage.setTitle("Torres de Hanói");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void mostrarNReinas()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/NReinasView.fxml"));
			AnchorPane ventana = (AnchorPane) loader.load();

			NReinasController controlador = loader.getController();
			controlador.setMainApp(this);

			Stage nReiansStage = new Stage();
			nReiansStage.setScene(new Scene(ventana));
			nReiansStage.show();
			nReiansStage.centerOnScreen();
			nReiansStage.setTitle("N-Reinas");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Stage getMainWindow()
	{
		return primaryStage;
	}
}