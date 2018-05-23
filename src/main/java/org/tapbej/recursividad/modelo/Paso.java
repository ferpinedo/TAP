package org.tapbej.recursividad.modelo;

import javafx.scene.layout.VBox;

public class Paso
{
	private int aro;
	private VBox torre;
	
	
	public Paso(int aro, VBox torre)
	{
		this.aro = aro;
		this.torre = torre;
	}
	
	public VBox getTorre()
	{
		return torre;
	}
	public void setTorre(VBox torre)
	{
		this.torre = torre;
	}
	public int getAro()
	{
		return aro;
	}
	public void setAro(int aro)
	{
		this.aro = aro;
	}

	@Override
	public String toString()
	{
		return "Paso [aro=" + aro + ", torre=" + torre + "]";
	}
	
	
}
