package org.tapbej.proyectofinal.util;

import org.tapbej.proyectofinal.modelo.Movement;
import org.tapbej.proyectofinal.modelo.SortMethod;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Encapsula métodos de ordenamiento
 *
 * @author (Pinedo Orta, Fernando Josué ; Ramírez Vilchis, Alejandro Alberto)
 * @version (1.0 - Abril 2018)
 */
public class Sorter
{
	private Deque<Movement> movements;
	private SortMethod method;
	private int[] data;
	private double transcurredMicros;
	private long totalMovements;

	public Sorter(int[] data, SortMethod method)
	{
		this.method = method;
		this.movements = new ArrayDeque<>();
		this.data = new int[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}


	public void sort()
	{
		long initialTime = System.nanoTime();

		switch (method)
		{
			case SELECTION_SORT:
				seleccionDirecta(data);
				break;
			case BUBBLE_SORT:
				intercambioDirecto(data);
				break;
			case INSERTION_SORT:
				insercionDirecta(data);
				break;
			case SHAKER_SORT:
				shakerSort(data);
				break;
			case QUICK_SORT:
				quicksort(data, 0, data.length-1);
		}
		long endTime = System.nanoTime();
//		System.out.println("Initial nano secs: " + initialTime + " | End nano secs: " + endTime);
		transcurredMicros = (endTime - initialTime) / 1000;
		totalMovements = movements.size();
	}


	/**
	 * Intercambia dos valores de un arreglo determinado y agrega la animación o paso a la UI
	 *
	 * @param data arreglo de valores
	 * @param i     posición uno a intercambiar
	 * @param j     posición dos a intercambiar
	 */
	private void intercambiarDatos(int[] data, int i, int j)
	{
		movements.add(new Movement(i, j));
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}


	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Encuentra el valor menor de la parte posiblemente no ordenada y lo intercambia con la primera posición de dicha
	 * parte.
	 * También llamado "Selection search"
	 *
	 * @param data arreglo de enteros a ordenar
	 */
	private void seleccionDirecta(int[] data)
	{
		// Uno por uno se verifican los elementos del arreglo si están ordenada
		for (int i = 0; i < data.length - 1; i++)
		{
			// Se busca el índice del número más pequeño de la parte no ordenada
			int indiceMinimo = i; // Se inicializa el valor mínimo
			for (int j = i + 1; j < data.length; j++)
			{
				if (data[j] < data[indiceMinimo])
					indiceMinimo = j;
			}
			// Si se encuentra un número menor al que se encuentra en la posición actual a verificar,
			// se intercambian los valores
			if (indiceMinimo != i)
			{
				intercambiarDatos(data, indiceMinimo, i);
			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Compara valores adyacentes y los intercambia si no están ordenados correctamente
	 * También llamado "Bubble search"
	 *
	 * @param data arreglo de enteros a ordenar
	 */
	private void intercambioDirecto(int[] data)
	{
		// Ordena de la última posición a la primera
		for(int i=0; i < data.length; i++)
		{
			// Verifica que estén ordenados los valores adyacentes de la parte no ordenada
			for (int j = 1; j < (data.length - i); j++)
			{
				if (data[j - 1] > data[j])
				{
					// Intercambia los valores no ordenados
					intercambiarDatos(data, j, j - 1);
				}

			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Se inserta elemento por elmento en su posición correcta.
	 * También llamado "Insertion search" o "Baraja"
	 *
	 * @param data arreglo de enteros a ordenar
	 */
	private void insercionDirecta(int[] data)
	{
		for (int i = 1; i < data.length; i++)
		{
			// busca e intercambia las posiciones que estén en desorden yendo de uno en uno
			for (int j = i; j > 0 && data[j] < data[j - 1]; j--)
			{
				intercambiarDatos(data, j, j-1);
			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Compara valores adyacentes y los intercambia si no están ordenados correctamente, yendo de un extremo a otro y
	 * viceversa hasta terminar de ordenarlos.
	 * También llamado "Burbuja bidireccional"
	 *
	 * @param data arreglo de enteros a ordenar
	 */
	private void shakerSort(int[] data)
	{
		int limitador = 0;
		do
		{
			for (int i = limitador; i < data.length - (limitador + 1); i++)
			{
				if (data[i] > data[i + 1])
					intercambiarDatos(data, i, i + 1);
			}

			for (int i = data.length - (limitador + 1); i > limitador; i--)
			{
				if (data[i] < data[i - 1])
					intercambiarDatos(data, i, i - 1);
			}
			limitador++;

		} while (limitador != data.length / 2);
	}

	private  void quicksort(int[] data, int first, int last)
	{
		int i = first, j = last;
		int pivote = data[(first + last) / 2];
		int auxiliar;

		do
		{
			while (data[i] < pivote) i++;
			while (data[j] > pivote) j--;

			if (i <= j)
			{
				intercambiarDatos(data, j, i);
				i++;
				j--;
			}
		}
		while (i <= j);

		if (first < j)
		{
			quicksort(data, first, j);
		}

		if (last > i)
		{
			quicksort(data, i, last);
		}
	}


	/**
	 * Es un algoritmo de ordenación recursivo con un número de comparaciones entre elementos del array mínimo.
	 * Su funcionamiento es similar al Quicksort, y está basado en la técnica divide y vencerás.
	 *
	 * @param data   referencia del arreglo a ordenar
	 * @param izquierda límite izquierdo
	 * @param derecha   límite derecho
	 */
	private void mergesort(int data[], int izquierda, int derecha)
	{
		if (izquierda < derecha)
		{
			int m = (izquierda + derecha) / 2;
			mergesort(data, izquierda, m);
			mergesort(data, m + 1, derecha);
			merge(data, izquierda, m, derecha);
		}
	}


	private void merge(int data[], int izquierda, int m, int derecha)
	{
		int i, j, k;
		int[] B = new int[data.length]; //array auxiliar
		for (i = izquierda; i <= derecha; i++) //copia ambas mitades en el array auxiliar
			B[i] = data[i];

		i = izquierda;
		j = m + 1;
		k = izquierda;
		while (i <= m && j <= derecha) //copia el siguiente elemento más grande
			if (B[i] <= B[j])
				data[k++] = B[i++];
			else
				data[k++] = B[j++];
		while (i <= m) //copia los elementos que quedan de la
			data[k++] = B[i++]; //primera mitad (si los hay)
	}

	/**
	 * Este algoritmo consiste en almacenar todos los elementos del vector a ordenar en un montículo (heap)
	 * y luego extraer el nodo que queda como nodo raíz del montículo (cima) en sucesivas iteraciones obteniendo
	 * el conjunto ordenado
	 */
	private void ordenacionMonticulos()
	{
		final int N = data.length;
		for (int nodo = N / 2; nodo >= 0; nodo--) hacerMonticulo(data, nodo, N - 1);
		for (int nodo = N - 1; nodo >= 0; nodo--)
		{
			int tmp = data[0];
			data[0] = data[nodo];
			data[nodo] = tmp;
			hacerMonticulo(data, 0, nodo - 1);
		}
	}

	private void hacerMonticulo(int[] v, int nodo, int fin)
	{
		int izq = 2 * nodo + 1;
		int der = izq + 1;
		int may;
		if (izq > fin) return;
		if (der > fin) may = izq;
		else may = v[izq] > v[der] ? izq : der;
		if (v[nodo] < v[may])
		{
			int tmp = v[nodo];
			v[nodo] = v[may];
			v[may] = tmp;
			hacerMonticulo(v, may, fin);
		}
	}

	/**
	 * Es un algoritmo que recorre el arreglo trasladando cada elemento a una cola determinada por el
	 * residuo, o dígito menos significativo del número. Cuando todos los elementos han sido trasladados
	 * a las colas, se recorren todas las colas en orden trasladando ahora los elementos al vector. El
	 * proceso se repite ahora para los demás dígitos de los elementos del vector.
	 * También conocido como ordenación por residuos.
	 */
	private void radixSort()
	{
		int max = 1;     // cantidad de repeticiones
		int nbytes = 4;     // numero de bytes a desplazar
		int nColas = (int) Math.pow(2, nbytes);
		// Creación e inicialización del arreglo de colas
		Queue<Integer>[] cola = new LinkedList[nColas];
		for (int i = 0; i < nColas; i++) cola[i] = new LinkedList<Integer>();

		int div = 0;        // posición a comparar
		for (int i = 0; i < max; i++)
		{
			// parte 1: recorrer el vector  para guardar cada elemento
			// en la cola correspondiente
			for (int numero : data)
			{
				// buscar el mayor número del vector
				if (i == 0) if (numero > max) max = numero;
				// calcular en qué cola debe ir cada número
				int numCola = (numero >> div) & 0xf;
				cola[numCola].add(numero);
			}
			div = div + nbytes;

			// parte 2: recorrer las colas en orden para poner cada
			// elemento en el vector;
			int j = 0;
			for (Queue<Integer> c : cola)
			{
				while (!c.isEmpty()) data[j++] = c.remove();
			}
			// la primera vez se actualiza el número de veces que se
			// debe ejecutar el proceso
			if (i == 0)
			{
				max = (int) (Math.log(max) / Math.log(nColas)) + 1;
			}
		}
	}


	public Deque<Movement> getMovements()
	{
		return movements;
	}

	public double getTranscurredMicros()
	{
		return transcurredMicros;
	}

	public long getTotalMovements()
	{
		return totalMovements;
	}

}
