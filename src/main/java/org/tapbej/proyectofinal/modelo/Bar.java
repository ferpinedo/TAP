package org.tapbej.proyectofinal.modelo;

/**
 * Class model for chart bar
 */
public class Bar
{
	private int value;
	private String color;

	/**
	 *  Constructor of bar
	 * @param value bar height
	 * @param color bar color
	 */
	public Bar(int value, String color)
	{
		this.value = value;
		this.color = color;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	@Override
	public String toString()
	{
		return "Bar{" +
				  "value=" + value +
				  ", color='" + color + '\'' +
				  '}';
	}
}
