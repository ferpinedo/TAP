package org.tapbej.proyectofinal.util;

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
	private Deque<int[]> pasos;
	private SortMethod method;
	private int[] data;

	public Sorter(SortMethod method, int[] data)
	{
		this.method = method;
		this.pasos = new ArrayDeque<>();
		this.data = new int[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}


	public long sort()
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
		}
		long endTime = System.nanoTime();
		System.out.println("Initial nano secs: " + initialTime + " | End nano secs: " + endTime);
		return endTime - initialTime;
	}


	/**
	 * Intercambia dos valores de un arreglo determinado y agrega la animación o paso a la UI
	 *
	 * @param datos arreglo de valores
	 * @param i     posición uno a intercambiar
	 * @param j     posición dos a intercambiar
	 */
	private void intercambiarDatos(int[] datos, int i, int j)
	{
		pasos.add(new int[]{i, j});
		int temp = datos[i];
		datos[i] = datos[j];
		datos[j] = temp;
	}


	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Encuentra el valor menor de la parte posiblemente no ordenada y lo intercambia con la primera posición de dicha
	 * parte.
	 * También llamado "Selection sort"
	 *
	 * @param numeros arreglo de enteros a ordenar
	 */
	public void seleccionDirecta(int[] numeros)
	{
		// Uno por uno se verifican los elementos del arreglo si están ordenada
		for (int i = 0; i < numeros.length - 1; i++)
		{
			// Se busca el índice del número más pequeño de la parte no ordenada
			int indiceMinimo = i; // Se inicializa el valor mínimo
			for (int j = i + 1; j < numeros.length; j++)
			{
				if (numeros[j] < numeros[indiceMinimo])
					indiceMinimo = j;
			}
			// Si se encuentra un número menor al que se encuentra en la posición actual a verificar,
			// se intercambian los valores
			if (indiceMinimo != i)
			{
				intercambiarDatos(numeros, indiceMinimo, i);
			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Compara valores adyaccentes y los intercambia si no están ordenados correctamente
	 * También llamado "Bubble sort"
	 *
	 * @param numeros arreglo de enteros a ordenar
	 */
	public void intercambioDirecto(int[] numeros)
	{
		// Ordena de la última posición a la primera
		for(int i=0; i < numeros.length; i++)
		{
			// Verifica que estén ordenados los valores adyacentes de la parte no ordenada
			for (int j = 1; j < (numeros.length - i); j++)
			{
				if (numeros[j - 1] > numeros[j])
				{
					// Intercambia los valores no ordenados
					intercambiarDatos(numeros, j, j - 1);
				}

			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Se inserta elemento por elmento en su posición correcta.
	 * También llamado "Insertion sort" o "Baraja"
	 *
	 * @param numeros arreglo de enteros a ordenar
	 */
	public void insercionDirecta(int[] numeros)
	{
		for (int i = 1; i < numeros.length; i++)
		{
			// busca e intercambia las posiciones que estén en desorden yendo de uno en uno
			for (int j = i; j > 0 && numeros[j] < numeros[j - 1]; j--)
			{
				int temp = numeros[j];
				numeros[j] = numeros[j - 1];
				numeros[j - 1] = temp;
			}
		}
	}

	/**
	 * Ordena de manera ascendente un arreglo de números enteros.
	 * Compara valores adyaccentes y los intercambia si no están ordenados correctamente, yendo de un extremo a otro y
	 * viceversa hasta terminar de ordenarlos.
	 * También llamado "Burbuja bidireccional"
	 *
	 * @param datos arreglo de enteros a ordenar
	 */
	public void shakerSort(int[] datos)
	{
		int limitador = 0;
		do
		{
			for (int i = limitador; i < datos.length - (limitador + 1); i++)
			{
				if (datos[i] > datos[i + 1])
					intercambiarDatos(datos, i, i + 1);
			}

			for (int i = datos.length - (limitador + 1); i > limitador; i--)
			{
				if (datos[i] < datos[i - 1])
					intercambiarDatos(datos, i, i - 1);
			}
			limitador++;

		} while (limitador != datos.length / 2);
	}

	public  void quicksort(int[] vector, int first, int last)
	{
		int i = first, j = last;
		int pivote = vector[(first + last) / 2];
		int auxiliar;

		do
		{
			while (vector[i] < pivote) i++;
			while (vector[j] > pivote) j--;

			if (i <= j)
			{
				auxiliar = vector[j];
				vector[j] = vector[i];
				vector[i] = auxiliar;
				i++;
				j--;
			}
		}
		while (i <= j);

		if (first < j)
		{
			quicksort(vector, first, j);
		}

		if (last > i)
		{
			quicksort(vector, i, last);
		}
	}


	/**
	 * Es un algoritmo de ordenación recursivo con un número de comparaciones entre elementos del array mínimo.
	 * Su funcionamiento es similar al Quicksort, y está basado en la técnica divide y vencerás.
	 *
	 * @param arreglo   referencia del arreglo a ordenar
	 * @param izquierda límite izquierdo
	 * @param derecha   límite derecho
	 */
	public void mergesort(int arreglo[], int izquierda, int derecha)
	{
		if (izquierda < derecha)
		{
			int m = (izquierda + derecha) / 2;
			mergesort(arreglo, izquierda, m);
			mergesort(arreglo, m + 1, derecha);
			merge(arreglo, izquierda, m, derecha);
		}
	}


	public void merge(int arreglo[], int izquierda, int m, int derecha)
	{
		int i, j, k;
		int[] B = new int[arreglo.length]; //array auxiliar
		for (i = izquierda; i <= derecha; i++) //copia ambas mitades en el array auxiliar
			B[i] = arreglo[i];

		i = izquierda;
		j = m + 1;
		k = izquierda;
		while (i <= m && j <= derecha) //copia el siguiente elemento más grande
			if (B[i] <= B[j])
				arreglo[k++] = B[i++];
			else
				arreglo[k++] = B[j++];
		while (i <= m) //copia los elementos que quedan de la
			arreglo[k++] = B[i++]; //primera mitad (si los hay)
	}

	/**
	 * Este algoritmo consiste en almacenar todos los elementos del vector a ordenar en un montículo (heap)
	 * y luego extraer el nodo que queda como nodo raíz del montículo (cima) en sucesivas iteraciones obteniendo
	 * el conjunto ordenado
	 *
	 * @param arreglo referencia del arreglo que será ordenado
	 */
	public void ordenacionMonticulos(int[] arreglo)
	{
		final int N = arreglo.length;
		for (int nodo = N / 2; nodo >= 0; nodo--) hacerMonticulo(arreglo, nodo, N - 1);
		for (int nodo = N - 1; nodo >= 0; nodo--)
		{
			int tmp = arreglo[0];
			arreglo[0] = arreglo[nodo];
			arreglo[nodo] = tmp;
			hacerMonticulo(arreglo, 0, nodo - 1);
		}
	}

	public void hacerMonticulo(int[] v, int nodo, int fin)
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
	 *
	 * @param arreglo referencia del arreglo que será ordenado
	 */
	public void radixSort(int[] arreglo)
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
			for (int numero : arreglo)
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
				while (!c.isEmpty()) arreglo[j++] = c.remove();
			}
			// la primera vez se actualiza el número de veces que se
			// debe ejecutar el proceso
			if (i == 0)
			{
				max = (int) (Math.log(max) / Math.log(nColas)) + 1;
			}
		}
	}


	public Deque<int[]> getPasos()
	{
		return pasos;
	}

	public void setPasos(Deque<int[]> pasos)
	{
		this.pasos = pasos;
	}
}
