package org.tapbej.recursividad.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



public class Hanoi
{
	private ArrayList<Rectangle> aros;

	private Queue<Paso> pasos;

	private Timeline timeline;

	private boolean onPause;


	public void mostrarPasos(ArrayList<Rectangle> aros, int millis)
	{
		timeline = new Timeline(new KeyFrame(Duration.millis(millis), action ->
		{
			if (pasos.size() != 0)
			{

				Paso pasoActual = pasos.poll();
				mover(aros.get(pasoActual.getAro()), pasoActual.getTorre());
				System.out.println(pasoActual.toString());
				onPause = false;
			}
			else
			{
				timeline.stop();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}


	public void llenarAros(ArrayList<Rectangle> aros, int numeroAros)
	{
		pasos = new LinkedList<>();
		Paint[] colores = { Color.RED,
								  Color.BLUE,
								  Color.GREEN,
								  Color.YELLOW,
								  Color.BROWN,
								  Color.VIOLET,
								  Color.PINK,
								  Color.DARKGRAY,
								  Color.WHEAT,
								  Color.AQUA,
								  Color.BURLYWOOD,
								  Color.TEAL,
								  Color.BEIGE,
							     Color.THISTLE,
							     Color.RED,
				 				  Color.BLUE,
								  Color.GREEN,
								  Color.YELLOW,
								  Color.BROWN,
								  Color.VIOLET,
								  Color.PINK,
								  Color.DARKGRAY,
								  Color.SALMON,
								  Color.AQUA,
								  Color.BURLYWOOD,
								  Color.TEAL,
								  Color.BEIGE,
								  Color.THISTLE};

		double grosor = numeroAros > 5? 150 / (double)numeroAros : 20;

		for (int i = 0; i < numeroAros; i++)
		{
			System.out.println("cuenta");
			double radio = (((i + 1) / (double) numeroAros) * 130.0) + 20;
			System.out.println("Porcentaje: " + radio);
			Rectangle rect = new Rectangle(radio, grosor, Color.color(Math.random(), Math.random(), Math.random()/*colores[i]*/));
			rect.setArcHeight(15);
			rect.setArcWidth(15);
			aros.add(rect);
		}
	}

	


	public void trasladarAros(int numAros, VBox torre1, VBox torre2, VBox torre3)
	{
		if (numAros == 1)
		{
			System.out.println("Moviendo aro " + numAros + " de " + torre1.getId() + " a " + torre3.getId());
			guardar(numAros, torre3);

		}
		else
		{
			trasladarAros(numAros - 1, torre1, torre3, torre2);
			System.out.println("Moviendo aro " + numAros + " de " + torre1.getId() + " a " + torre3.getId());
			guardar(numAros, torre3);

			trasladarAros(numAros - 1, torre2, torre1, torre3);
		}
	}


	private void guardar(int aro, VBox torre)
	{
		pasos.add(new Paso(aro, torre));
	}


	private void mover(Rectangle aro, VBox torre)
	{
		torre.getChildren().add(0, aro);
	}


	public ArrayList<Rectangle> getAros()
	{
		return aros;
	}


	public void setAros(ArrayList<Rectangle> aros)
	{
		this.aros = aros;
	}

	public boolean isOnPause()
	{
		return onPause;
	}

	public void setOnPause(boolean onPause)
	{
		this.onPause = onPause;
	}

	public Timeline getTimeline()
	{
		return timeline;
	}

	public void setTimeline(Timeline timeline)
	{
		this.timeline = timeline;
	}

}















