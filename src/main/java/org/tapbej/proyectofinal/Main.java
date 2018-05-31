package org.tapbej.proyectofinal;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tapbej.proyectofinal.controlador.Controller;


public class Main extends Application
{
	private Stage primaryStage;
	private Stage secondaryStage;

	public static void main(String [] args)
	{
		launch(args);
	}


	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
      this.primaryStage.setTitle("Tópicos Avanzados de Programación");

      secondaryStage = new Stage();
      
      showTopicSelectionView();
	}
	
	
	public void showTopicSelectionView()
	{
		try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("vista/TopicSelectionView.fxml"));
         AnchorPane ventana = loader.load();

         primaryStage.setScene(new Scene(ventana));
         primaryStage.show();
         Controller controller = loader.getController();
         controller.start(this);

      }
		catch (IOException e)
      {
         e.printStackTrace();
      }
	}


	public void showRecursionView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/RecursionView.fxml"));
			AnchorPane ventana = loader.load();

			primaryStage.setScene(new Scene(ventana));
			primaryStage.show();
			Controller controller = loader.getController();
			controller.start(this);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public void showSortingView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/SortingView.fxml"));
			AnchorPane ventana = loader.load();

			secondaryStage.setScene(new Scene(ventana));
			secondaryStage.show();
			Controller controller = loader.getController();
			controller.start(this);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void showSearchView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/SearchView.fxml"));
			AnchorPane ventana = loader.load();

			secondaryStage.setScene(new Scene(ventana));
			secondaryStage.show();
			Controller controller = loader.getController();
			controller.start(this);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



	public void showHanoi()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vista/TorresHanoiView.fxml"));
			AnchorPane ventana = loader.load();
			secondaryStage.setScene(new Scene(ventana));
			secondaryStage.show();
			secondaryStage.setTitle("Torres de Hanói");
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
			AnchorPane ventana = loader.load();

			Controller controlador = loader.getController();
			controlador.start(this);


			secondaryStage.setScene(new Scene(ventana));
			secondaryStage.show();
			secondaryStage.centerOnScreen();
			secondaryStage.setTitle("N-Reinas");

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