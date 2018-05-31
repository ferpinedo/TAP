package org.tapbej.proyectofinal.modelo;

public class Comparison
{
	private boolean successful;
	private int barIndex;

	public Comparison(int barIndex, boolean successful)
	{
		this.successful = successful;
	}

	public boolean isSuccessful()
	{
		return successful;
	}

	public void setSuccessful(boolean successful)
	{
		this.successful = successful;
	}

	public int getBarIndex()
	{
		return barIndex;
	}

	public void setBarIndex(int barIndex)
	{
		this.barIndex = barIndex;
	}
}
