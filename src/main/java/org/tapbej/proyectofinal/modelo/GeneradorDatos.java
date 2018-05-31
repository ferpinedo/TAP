package org.tapbej.proyectofinal.modelo;

import java.util.Random;

/**
 * Clase con métodos estáticos útil para generar arreglos
 * de enteros con un orden especificado.
 *
 * @author Alejandro Alberto Ramírez Vilchis
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
      int [] datos = new int[n];

      for (int i = 0; i < n; i++)
      {
         datos[i] = i + 1;
      }

      return datos;
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
      Random randomizer = new Random();
      int [] datos = new int[n];

      for (int i = 0; i < n; i++)
      {
         datos[i] = randomizer.nextInt(n) + 1;
      }

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
      Random randomizer = new Random();
      int [] datos = new int[n];

      int delimitador = (int)( n * ((double)porcentajeOrdenados / 100.0));

      System.out.println(delimitador);

      for (int i = 0; i < delimitador; i++)
      {
         System.out.println("Metiendo num ordenado en " + i);
         datos[i] = i + 1;
      }

      for (int i = delimitador; i < n; i++)
      {
         datos[i] = (randomizer.nextInt(n - delimitador) + delimitador) + 1;
      }

      return datos;
   }

   /**
    * Método de utilidad para imprimir un arreglo de enteros
    * @param datos El arreglo a imprimir
    */
   public static void imprimirDatos(int [] datos)
   {
      for (int i = 0; i < datos.length; i++)
      {
         System.out.print(datos[i] + "|");
      }
      System.out.println();
   }
}
