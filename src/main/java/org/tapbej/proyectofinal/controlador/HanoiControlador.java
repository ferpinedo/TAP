package org.tapbej.proyectofinal.controlador;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.tapbej.proyectofinal.modelo.Hanoi;


public class HanoiControlador extends Controller
{
	@FXML
	private VBox torre1;

	@FXML
	private VBox torre2;

	@FXML
	private VBox torre3;

	@FXML
	private TextField txtAros;
	
	@FXML
	private TextField txtPause;

	@FXML
	private Label lblMoves;

	@FXML
	private Label lblTime;

	@FXML
	private ToggleButton btnPause;

	private Hanoi hanoi;

	public void initialize()
	{
		hanoi = new Hanoi();
	}

	public void handleResolver()
	{
		btnPause.setSelected(false);

		torre1.getChildren().clear();
		torre2.getChildren().clear();
		torre3.getChildren().clear();
		
		int numeroAros = Integer.parseInt(txtAros.getText());

		ArrayList<Rectangle> aros = new ArrayList<>();
		aros.add(new Rectangle(50, 20)); // para compensar la omision
													// del 0

		hanoi.llenarAros(aros, numeroAros);
		hanoi.setAros(aros);

		System.out.println("Aros:" + numeroAros);

		comenzarResolucion(aros, numeroAros);
		hanoi.trasladarAros(numeroAros, torre1, torre2, torre3);
		hanoi.mostrarPasos(aros, Integer.parseInt(txtPause.getText()));

	}

	public void handlePausa()
	{
		if (hanoi.getTimeline() != null)
		{
			if (hanoi.isOnPause())
			{
				System.out.println("Play");
				hanoi.setOnPause(false);
				hanoi.getTimeline().play();
			}
			else
			{
				System.out.println("Pausado");
				hanoi.setOnPause(true);
				hanoi.getTimeline().pause();
			}
		}
	}


	private void comenzarResolucion(ArrayList<Rectangle> aros, int numeroAros)
	{
		System.out.println("Comenzando; aros apareciendo");

		torre1.getChildren().addAll(aros.subList(1, aros.size()));

		lblMoves.setVisible(true);
		lblTime.setVisible(true);
		lblMoves.setText("Movimientos necesarios: " + (Math.pow(2, numeroAros) - 1));
		lblTime.setText("Tiempo estimado en segundos: " + new DecimalFormat("##.##").format((Math.pow(2, numeroAros) - 1) * (Double.parseDouble(txtPause.getText()) / 1000)));

	}

	@Override
	void setKeyListener()
	{

	}

	@Override
	void setDefaultCloseOperation()
	{
		mainApp.getSecondaryStage().setResizable(true);
	}

	@Override
	void runSpecificOperations()
	{

	}
}