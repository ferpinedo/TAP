package org.tapbej.proyectofinal.modelo;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Encapsula métodos de ordenamiento
 *
 * @author (Pinedo Orta, Fernando Josué ; Ramírez Vilchis, Alejandro Alberto)
 * @version (1.0 - Abril 2018)
 */
public class Searcher
{
	private Deque<Comparison> comparisons;
	private SearchMethod method;
	private int[] data;
	private int target;

	public Searcher(SearchMethod method, int[] data, int target)
	{
		this.comparisons = new ArrayDeque<>();
		this.method = method;
		this.data = new int[data.length];
		this.target = target;
		System.arraycopy(data, 0, this.data, 0, data.length);
	}


	public long search()
	{
		long initialTime = System.nanoTime();

		switch (method)
		{
			case SEQUENTIAL_SEARCH:
				sequentialSearch();
				break;
			case BINARY_SEARCH:
				binarySearch();
				break;
			case HASH_TABLE_SEARCH:
				break;
		}
		long endTime = System.nanoTime();
		System.out.println("Initial nano secs: " + initialTime + " | End nano secs: " + endTime);
		return endTime - initialTime;
	}


	private void saveFailure(int bar)
	{
		comparisons.add(new Comparison(bar, false));
	}

	private void saveSuccess(int bar)
	{
		comparisons.add(new Comparison(bar, true));
	}


	private void sequentialSearch()
	{
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == target)
			{
				saveSuccess(i);
				return;
			}
			else
			{
				saveFailure(i);
			}
		}
	}


	public void binarySearch()
	{
		int low = 0;
		int high = data.length - 1;

		while (low <= high)
		{
			int middle = (low + high) / 2;
			if (target > data[middle])
			{
				saveFailure(middle);
				low = middle + 1;
			}
			else if (target < data[middle])
			{
				saveFailure(middle);
				high = middle - 1;
			}
			else
			{
				saveSuccess(middle);
				return;
			}
		}
	}


	public Deque<Comparison> getComparisons()
	{
		return comparisons;
	}

	public void setComparisons(Deque<Comparison> comparisons)
	{
		this.comparisons = comparisons;
	}
}
