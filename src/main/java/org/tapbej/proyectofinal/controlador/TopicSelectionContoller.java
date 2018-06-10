package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.tapbej.proyectofinal.Main;

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
	private Label lblTitle;

	@FXML
	private Label lblDescription;

	@FXML
	private ImageView image;

	@FXML
	private ScrollPane scrollPane;

	private final String INTRO_TEXT = "TAP (Tópicos Avanzados de Programación) es un proyecto que consiste en la representación y demostración de algoritmos avanzados de programación." +
			  "\n\nAutores: \n  - Alejandro Alberto Ramírez Vilchis\n  - Fernando Josué Pinedo Orta" +
			  "\n\nMayo, 2018";
	private final String RECURSION_TEXT = "La recursividad se refiere al proceso que se define en términos de uno mismo. Selecciona esta opción si deseas jugar y observar el funcionamiento de la recursividad.";
	private final String SORTING_TEXT = "Ordenamiento consiste en permutar valores de manera que se acomoden de menor valor a mayor (ascendentemente)."; //Elige esta opción para comparar y entender mejor los distintos métodos de ordenamiento.";
	private final String SEARCH_TEXT = "Un algoritmo de búsqueda es aquel que está diseñado para localizar un elemento con ciertas propiedades dentro de una estructura de datos."; // Selecciona esta opción para probar distintos algoritmos de búsqueda. ";


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
		lblTitle.setText("TAP");
		lblDescription.setText(INTRO_TEXT);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		image.setImage(new Image(mainApp.getClass().getResource("iconos/algorithm.png").toExternalForm()));
	}

	public void displayRecursionDescription()
	{
		lblTitle.setText("Recursividad");
		lblDescription.setText(RECURSION_TEXT);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVvalue(0.0);
		image.setImage(new Image(mainApp.getClass().getResourceAsStream("iconos/recursionLogo2.png")));
	}

	public void displaySortingDescription()
	{
		lblTitle.setText("Ordenamiento");
		lblDescription.setText(SORTING_TEXT);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVvalue(0.0);
		image.setImage(new Image(mainApp.getClass().getResource("iconos/sortingLogo2.png").toExternalForm()));
	}

	public void displaySearchDescription()
	{
		lblTitle.setText("Búsqueda");
		lblDescription.setText(SEARCH_TEXT);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVvalue(0.0);
		image.setImage(new Image(mainApp.getClass().getResource("iconos/searchLogo2.png").toExternalForm()));
	}

}
