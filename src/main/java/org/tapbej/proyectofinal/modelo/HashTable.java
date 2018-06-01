package org.tapbej.proyectofinal.modelo;

import java.util.*;

public class HashTable
{
	private HashMap<Integer, List<Integer>> hashTable;
	private Deque<Comparison> comparisons;
	private int target;

	public HashTable()
	{
		this.hashTable = new HashMap<>();
		this.comparisons = new ArrayDeque<>();
	}


	public void put(int value)
	{
		int bucketAddress = getBucketAddress(value);

		if (!hashTable.containsKey(bucketAddress))
		{
			hashTable.put(bucketAddress, new LinkedList<>());
			hashTable.get(bucketAddress).add(value);
		} else
		{
			hashTable.get(bucketAddress).add(value);
		}
	}

	public void remove(int value)
	{
		int bucketAddress = getBucketAddress(value);

		if (hashTable.containsKey(bucketAddress))
		{
			System.out.println("It contains the key");
			for (int i = 0; i < hashTable.get(bucketAddress).size(); i++)
			{
				if (value == hashTable.get(bucketAddress).get(i))
				{
					System.out.println("Removing item");
					hashTable.get(bucketAddress).remove(i);

					if (hashTable.get(bucketAddress).size() == 0)
					{
						hashTable.keySet().remove(bucketAddress);   // remove bucket too
					}
					break;
				}
			}
		}
	}

	public long search()
	{
		long initialTime = System.nanoTime();

		int bucketAddress = getBucketAddress(target);

		System.out.println("Searching in bucket: " + bucketAddress);

		if (hashTable.containsKey(bucketAddress))
		{
			System.out.println("I have that bucket!");
			for (int i = 0; i < hashTable.get(bucketAddress).size(); i++)
			{
				if (target == hashTable.get(bucketAddress).get(i))
				{
					comparisons.add(new Comparison(bucketAddress, i, true));
					System.out.println("Data found");
					long endTime = System.nanoTime();
					System.out.println("Initial nano secs: " + initialTime + " | End nano secs: " + endTime);
					return endTime - initialTime;
				}
				else
				{
					comparisons.add(new Comparison(bucketAddress, i, false));
				}
			}
		}
		System.out.println("Data not found");

		long endTime = System.nanoTime();
		System.out.println("Initial nano secs: " + initialTime + " | End nano secs: " + endTime);
		return endTime - initialTime;
	}

	private int getBucketAddress(int originalValue)
	{
		return originalValue % 17;
	}


	public Deque<Comparison> getComparisons()
	{
		return comparisons;
	}

	public void setComparisons(Deque<Comparison> comparisons)
	{
		this.comparisons = comparisons;
	}

	public void setTarget(int target)
	{
		this.target = target;
	}

	public int getTarget()
	{
		return target;
	}

	public HashMap<Integer, List<Integer>> getHashTable()
	{
		return hashTable;
	}

	public void setHashTable(HashMap<Integer, List<Integer>> hashTable)
	{
		this.hashTable = hashTable;
	}


}
