package org.tapbej.proyectofinal.modelo;

public class Comparison
{
	private boolean successful;
	private int itemIndex;
	private int bucket;


	public Comparison( int bucket, int nodeIndex, boolean successful)
	{
		this.bucket = bucket;
		this.itemIndex = nodeIndex;
		this.successful = successful;
	}

	public Comparison(int nodeIndex, boolean successful)
	{
		this.itemIndex = nodeIndex;
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

	public int getItemIndex()
	{
		return itemIndex;
	}

	public void setItemIndex(int itemIndex)
	{
		this.itemIndex = itemIndex;
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
