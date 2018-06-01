package org.tapbej.proyectofinal.modelo;

public class Comparison
{
	private boolean successful;
	private int barIndex;

	public Comparison(int nodeIndex, int bucket, boolean successful)
	{
		this.successful = successful;
		this.barIndex = nodeIndex;
		this.bucket = bucket;
	}

	private int bucket;

	public Comparison(int nodeIndex, boolean successful)
	{
		this.barIndex = nodeIndex;
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

	public int getBucket()
	{
		return bucket;
	}

	public void setBucket(int bucket)
	{
		this.bucket = bucket;
	}
}
