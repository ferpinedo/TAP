package org.tapbej.proyectofinal.modelo;

import java.util.Random;

/**
 * Clase con métodos estáticos útil para generar arreglos
 * de enteros con un orden especificado.
 *
 * @author Alejandro Alberto Ramírez Vilchis & Fernando Josue Pinedo Orta
 */
public class GeneradorDatos
{
   private GeneradorDatos(){} // para no permitir la creación de instancias

   /**
    * Genera un arreglo de enteros ordenados
    * de forma ascendente
    *
    * @param n Tamaño del arreglo
    * @return El arreglo de enteros lleno
    */
   public static int[] mejorCaso(int n)
   {
      return llenarArreglo(n);
   }


   /**
    * Genera un arreglo de enteros ordenados
    * de forma descentende
    *
    * @param n Tamaño del arreglo
    * @return El arreglo de enteros lleno
    */
   public static int[] peorCaso(int n)
   {
      int [] datos = new int[n];

      for (int i = n; i > 0; i--)
      {
         datos[n - i] = i;
      }

      return datos;
   }


   /**
    * Genera un arreglo de enteros ordenados
    * de forma pseudorandom. Los elementos pueden repetirse <br/>
    * pero no salirse del límite especificado
    *
    * @param n Tamaño del arreglo
    * @return El arreglo de enteros lleno
    */
   public static int[] casoPromedio(int n)
   {
      int[] datos = llenarArreglo(n);
      randomizarArreglo(datos);
      return datos;
   }


   /**
    * Genera un arreglo de enteros con un porcentaje de ellos ordenado
    * y otro con elementos pseudorandom
    *
    * @param n Tamaño del arreglo
    * @param porcentajeOrdenados El porcentaje de elementos ordenados (valor de 0 a 100)
    * @return El arreglo de enteros lleno
    */
   public static int[] casoMixto(int n, int porcentajeOrdenados)
   {
      int[] datos = new int[n];

      int delimitador = (int)( n * ((double)porcentajeOrdenados / 100.0));
      System.out.println(delimitador);

      int[] parteOrdenada = llenarArreglo(delimitador);
      int[] partePromedio = llenarArreglo(delimitador, n);
      randomizarArreglo(partePromedio);

      for (int i = 0; i < delimitador; i++)
      {
         datos[i] = parteOrdenada[i];
      }
      for (int i = delimitador; i < n; i++)
      {
         datos[i] = partePromedio[i - delimitador];
      }

      return datos;
   }

   /**
    * Método de utilidad para imprimir un arreglo de enteros
    * @param datos El arreglo a imprimir
    */
   public static void imprimirDatos(int [] datos)
   {
      for (int dato : datos)
      {
         System.out.print(dato + "|");
      }
      System.out.println();
   }


   /**
    * Llena un arreglo de enteros de uno al
    * número especificado
    * @param n El tamaño del arreglo deseado
    */
   private static int[] llenarArreglo(int n)
   {
      int [] datos = new int[n];
      for (int i = 0; i < datos.length; i++)
      {
         datos[i] = i+1;
      }
      return datos;
   }


   /**
    * Llena un arreglo de enteros con limites
    * inferior y superior
    * @param inferior El limite inferior del arreglo
    * @param superior El limite superior del arreglo
    */
   private static int[] llenarArreglo(int inferior, int superior)
   {
      int [] datos = new int[superior - inferior];
      for (int i = 0; i < datos.length; i++)
      {
         datos[i] = inferior + (i+1);
      }
      return datos;
   }


   /**
    * Intercambia los valores de dos posiciones en un arreglo
    * @param arrelgo La referencia al arreglo
    * @param indice1 Indice del numero 1
    * @param indice2 Indice del numero 2
    */
   private static void intercambiar(int[] arrelgo, int indice1, int indice2)
   {
      int temporal = arrelgo[indice1];
      arrelgo[indice1] = arrelgo[indice2];
      arrelgo[indice2] = temporal;
   }

   /**
    * "Randomiza" un arreglo de enteros al
    * intercambiar cada una de sus posiciones
    * @param arreglo
    */
   private static void randomizarArreglo(int[] arreglo)
   {
      Random randomizer = new Random();

      for (int i = 0; i < arreglo.length; i++)
      {
         // intercambiar posicion i con una posicion random
         intercambiar(arreglo, i, randomizer.nextInt(arreglo.length));
      }
   }
}
