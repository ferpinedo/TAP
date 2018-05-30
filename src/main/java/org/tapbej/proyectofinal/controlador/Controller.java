package org.tapbej.proyectofinal.controlador;

import org.tapbej.proyectofinal.Main;


public abstract class Controller
{
	protected Main mainApp;
	
	abstract void setKeyListener();
	abstract void setDefaultCloseOperation();
	abstract void runSpecificOperations();
	
	public void start(Main main)
	{
		this.mainApp = main;
		this.runSpecificOperations();
		this.setDefaultCloseOperation();
	}
}
