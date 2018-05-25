package org.tapbej.proyectofinal.util;

import java.util.ArrayList;

public class NQueensSolver
{

   private int[] x;
   private int posibleSolutions = 0;
   private boolean firstTwoSolutionsDone = false;
   private ArrayList<int[]> solutions;

   public NQueensSolver(int n)
   {
      x = new int[n];
      solutions = new ArrayList<>();
   }

   private boolean canPlaceQueen(int r, int c) {
      /**
       * Returns TRUE if a queen can be placed in row r and column c.
       * Otherwise it returns FALSE. x[] is a global array whose first (r-1)
       * values have been set.
       */
      for (int i = 0; i < r; i++) {
         if (x[i] == c || (i - r) == (x[i] - c) ||(i - r) == (c - x[i]))
         {
            return false;
         }
      }
      return true;
   }

   public void printQueens(int[] queens) {
      int N = queens.length;
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (queens[i] == j) {
               System.out.print("Q ");
            } else {
               System.out.print("* ");
            }
         }
         System.out.println();
      }
      System.out.println();
   }


   private void placeNqueens(int r, int n) {
      /**
       * Using backtracking this method prints all possible placements of n
       * queens on an n x n chessboard so that they are non-attacking.
       */
      for (int c = 0; c < n; c++)
      {
         if (canPlaceQueen(r, c))
         {
            x[r] = c;
            if (r == n - 1)
            {

               if (posibleSolutions == 1)
               {
                  firstTwoSolutionsDone = true;
               }

               addSolution();
               System.out.println("Imprimiedno copia pasada al array");
               printQueens(solutions.get(posibleSolutions));

               posibleSolutions++;
            }
            else
            {
               placeNqueens(r + 1, n);
            }
         }
      }
   }

   public void solve()
   {
      placeNqueens(0, x.length);
   }

   public int[] getX()
   {
      return x;
   }

   public void addSolution()
   {
      int[] newArray = new int[x.length];
      for (int i = 0; i < x.length; i++)
      {
         newArray[i] = x[i];
      }
      solutions.add(newArray);
   }

   public int getPosibleSolutions()
   {
      return posibleSolutions;
   }

   public boolean isFirstTwoSolutionsDone()
   {
      return firstTwoSolutionsDone;
   }

   public ArrayList<int[]> getSolutions()
   {
      return solutions;
   }



}