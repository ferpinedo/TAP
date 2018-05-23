package org.tapbej.recursividad.controlador;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.shape.Circle;
import org.tapbej.recursividad.Main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.tapbej.recursividad.util.NQueensSolver;
import org.tapbej.recursividad.util.Popups;

import java.util.ArrayList;
import java.util.List;


public class NReinasController
{
   private Main mainApp;
   private GridPane tablero;
   private AnchorPane[][] cuadros;
   private ImageView[][] reinasNegras;
   private ImageView[][] reinasRojas;

   private int[][] tableroPosicional;
   private int nReinas;
   private int reinasColocadas;
//   private ArrayList<int[]> soluciones;

   private  List<PauseTransition> movimientos;
   private SequentialTransition sequentialTransition;
   private SequentialTransition timeLine;

   private long millis = 1;
   private boolean running = false;


   private Thread task;
   private NQueensSolver nQueensSolver;
   private int currentSolution = -1;

   @FXML
   private Button btnNext;

   @FXML
   private BorderPane areaTablero;

   @FXML
   private TextField txtNReinas;

   @FXML
   private TextField txtVelocidad;

   @FXML
   private TextField txtLimitTime;

   @FXML
   private ImageView blackQueen;

   @FXML
   private Label timerLabel;

   @FXML
   private Label lblQueensLeft;

   @FXML
   private AnchorPane queenPane;

   @FXML
   private Circle circle;

   @FXML
   private Label lblStatus;

   @FXML
   public void initialize()
   {
      lblQueensLeft.setVisible(false);
      lblStatus.setVisible(false);
      circle.setVisible(false);
      btnNext.setDisable(true);
   }

   @FXML
   public void handleBtnJugar()
   {
      Popups.showInfo("¡Modo juego activado!", "Coloca las reinas arrastrándolas a tales lugares en los que" +
              " no se puedan amenazar entre ellas mismas. Hazlo antes de que se termine el tiempo", mainApp.getMainWindow());
      generarRecursos();
      queenPane.getStyleClass().add("inner-pane");
      lblQueensLeft.setVisible(true);
      circle.setVisible(true);
      btnNext.setDisable(true);
      lblStatus.setVisible(false);
      lblQueensLeft.setText(txtNReinas.getText());

      int startTime = Integer.parseInt(txtLimitTime.getText());
      timerLabel.setVisible(true);
      timerLabel.setText("Tiempo límite: " + startTime + " seg");

      ArrayList<PauseTransition> transitions = new ArrayList<>();

      for (int i = startTime - 1; i >= 0; i--)
      {
         PauseTransition pt = new PauseTransition(Duration.seconds(1));
         final int tiempo = i;
         pt.setOnFinished(event -> timerLabel.setText("Tiempo límite: " + tiempo + " seg"));
         transitions.add(pt);
      }

      PauseTransition pt = new PauseTransition(Duration.seconds(1));
      pt.setOnFinished(event ->
              {
                 timerLabel.setVisible(false);
                 Popups.showFailure("Has fallado", mainApp.getMainWindow());
                 generarRecursos();
              });
      transitions.add(pt);

      timeLine = new SequentialTransition(transitions.toArray(new PauseTransition[0]));
      timeLine.play();

   }


   @FXML
   public void handleBtnResolver()
   {
      if (!queenPane.getStyleClass().isEmpty())
         queenPane.getStyleClass().clear();

      lblQueensLeft.setVisible(false);
      circle.setVisible(false);
      lblStatus.setVisible(true);
      timerLabel.setVisible(false);

      lblStatus.setText("Estátus: Buscando más soluciones...");
      btnNext.setDisable(false);

      currentSolution = -1;
      movimientos = new ArrayList<>();
      millis = Long.parseLong(txtVelocidad.getText());
      System.out.println("Velocidad: " + millis);

      generarRecursos();
      System.out.println("Empezando algoritmo");

      nQueensSolver = new NQueensSolver(nReinas);

      if (task != null) // stops current thread
      {
         task.interrupt();
         task.stop();
      }


      task = new Thread(() ->{
         nQueensSolver.solve();
         Platform.runLater(() -> lblStatus.setText("Estátus: " + nQueensSolver.getPosibleSolutions() + " Soluciones encontradas"));

      });

      task.setName("NQueens Algorithm Thread");
      task.start();

      if (nReinas > 15)
      {
         while(!nQueensSolver.isFirstTwoSolutionsDone())
         {
            System.out.print("."); //wait
         }
         mostrarTableroUI(nQueensSolver.getSolutions().get(1));

      }
      else
      {
         colocarReinas(0, nReinas);
      }
      btnNext.setVisible(true);
      System.out.println("Listo");

      if (sequentialTransition != null)
      {
         sequentialTransition.stop();
         sequentialTransition.getChildren().clear();
      }

      sequentialTransition = new SequentialTransition(movimientos.toArray(new PauseTransition[0]));
      sequentialTransition.play();

      running = true;

   }

   @FXML
   public void handleNext()
   {
      currentSolution++;

      for (int i = 0; i < nReinas; i++) // limpiar tablero
      {
         for (int j = 0; j < nReinas; j++)
         {
            reinasNegras[i][j].setVisible(false);
         }
      }

      System.out.println("current" + currentSolution);
      if (currentSolution + 1 >= nQueensSolver.getSolutions().size())
      {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("NReinas");
         alert.setHeaderText("No hay más soluciones");
         alert.setContentText("Sí aún se siguen buscando más soluciones, espera un poco más " +
                 "y vuelve a intentarlo despúes. ");
         alert.showAndWait();
      }
      else
      {
         if (currentSolution == 1)
         {
            currentSolution = 2;
         }
         nQueensSolver.printQueens(nQueensSolver.getSolutions().get(currentSolution));
         mostrarTableroUI(nQueensSolver.getSolutions().get(currentSolution));
      }

   }

   /**
    * Crea tablero de ajedrez y reinas
    */
   public void generarRecursos()
   {
      System.out.println("Generando recursos...");
      nReinas = Integer.parseInt(txtNReinas.getText());

      System.out.println("Numero de  cuadros: "+ nReinas * nReinas);

      cuadros = new AnchorPane[nReinas][nReinas];
      reinasNegras = new ImageView[nReinas][nReinas];
      reinasRojas = new ImageView[nReinas ][nReinas];

      tablero = new GridPane();
      tablero.setPrefHeight(700.0);
      tablero.setPrefWidth(700.0);
      tablero.setMaxHeight(700.0);
      tablero.setMaxWidth(700.0);

      String css = mainApp.getClass().getResource("stylesheets/style.css").toExternalForm();
      double ladoCuadro = 700/nReinas - 5;

      for (int i = 0; i < nReinas; i++)
      {
         for (int j = 0; j < nReinas; j++)
         {
            System.out.print(".");
            // añadir cuadro
            cuadros[i][j] = new AnchorPane();
            cuadros[i][j].setPrefSize(500.0, 500.0);
            cuadros[i][j].getStylesheets().add(css);
            setEventHandlers(i, j);

            if ((i+j) % 2 == 1)
               cuadros[i][j].getStyleClass().add("white-pane");
            else
               cuadros[i][j].getStyleClass().add("brown-pane");

            tablero.add(cuadros[i][j], i, j);

            // añadir coronas
            String reinaNegraURL = mainApp.getClass().getResource("iconos/crown.png").toExternalForm();
            reinasNegras[i][j] = new ImageView(new Image(reinaNegraURL, ladoCuadro, ladoCuadro, false, false));
            final int column = i;
            final int row = j;
            reinasNegras[i][j].setOnDragDetected(event ->
            {
               Dragboard db = reinasNegras[column][row].startDragAndDrop(TransferMode.ANY);
               tableroPosicional[row][column] = 0;
               ClipboardContent cb = new ClipboardContent();
               cb.putString("1");
               db.setContent(cb);
               event.consume();
               reinasColocadas--;
               reinasNegras[column][row].setVisible(false);
            });
            tablero.add(reinasNegras[i][j], i, j);
            reinasNegras[i][j].setVisible(false);

            String reinaRojaURL =mainApp.getClass().getResource("iconos/redCrown.png").toExternalForm();
            reinasRojas[i][j] =  new ImageView(new Image(reinaRojaURL, ladoCuadro, ladoCuadro, false, false));
            tablero.add(reinasRojas[i][j], i, j);
            reinasRojas[i][j].setVisible(false);

         }

      }

      // crear cuadros del tablero (posibles posiciones para la reina)
      tableroPosicional = new int[nReinas][nReinas];
      for (int i = 0; i < nReinas; i++)
      {
         for (int j = 0; j < nReinas; j++)
         {
            tableroPosicional[i][j] = 0;
         }
      }

      if (timeLine != null)
      {
         timeLine.stop();
         timeLine.getChildren().clear();
      }

      reinasColocadas = 0;
      areaTablero.setCenter(tablero);

      System.out.println("... \nRecursos generados");

   }

   public void mostrarTableroUI(int[] x)
   {
      int N = x.length;
      for (int i = 0; i < N; i++)
      {
         for (int j = 0; j < N; j++)
         {
            if (x[i] == j)
            {
               showBlackQueenImmediate(j, i);
            }
         }
      }
   }


   /**
    * Intenta colocar las reinas en el tablero, validando que todas las reinas estén a salvo
    * en las casillas colocadas.
    * Utiliza recursividad.
    * @param reina número de reina que será colocada
    * @param n número de reinas en total
    * @return boolean si se pudieron colocar las reinas o no
    */
   private boolean colocarReinas(int reina, int n)
   {
      if (reina == n)
      {
         //si ya no hay más reinas por colocar entonces hemos terminado
         return true;
      }
      for (int fila = 0; fila < n; fila++) //vamos de fila en fila
      {
         // revisar que la reina pueda ser colocada
         if (esSeguro(fila, reina))
         {
            // colocar reina
            tableroPosicional[fila][reina] = 1;

            // encontrar lugar para la siguiente reina
            if (colocarReinas(reina + 1, n))
            {
               return true;
            }
            //"BACKTRACKING"
            // si no encontramos un lugar para la siguiente reina entonces retroceder un paso
            tableroPosicional[fila][reina] = 0;
            hideBlackQueen(reina, fila);
         }
      }
      //si estamos aqui quiere decir que no hay solucion posible
      return false;

   }

   /**
    * Revisa si es seguro colocar la reina en la posicion indicada
    * @param fila
    * @param columna
    * @return
    */
   private boolean esSeguro(int fila, int columna)
   {
      showBlackQueen(columna, fila);
      // como vamos de columna en columna
      // lo primero es revisar que no haya ninguna otra reina en la misma fila
      for (int i = 0; i < columna; i++)
      {

         if (tableroPosicional[fila][i] == 1)
         {
            showAndHideRedQueens(columna, fila, i, fila);
            hideBlackQueen(columna, fila);
            return false;
         }

      }
      // revisar la diagonal superior
      for (int i = fila, j = columna; i >= 0 && j >= 0; i--, j--)
      {
         if (tableroPosicional[i][j] == 1)
         {
            showAndHideRedQueens(columna, fila, j, i);
            hideBlackQueen(columna, fila);
            return false;
         }
      }

      // revisar diagonal inferior
      for (int i = fila, j = columna; i < tableroPosicional.length && j >= 0; i++, j--)
      {
         if (tableroPosicional[i][j] == 1)
         {
            showAndHideRedQueens(columna, fila, j, i);
            hideBlackQueen(columna, fila);
            return false;
         }
      }
      // si hemos llegado hasta aquí quiere decir que es seguro colocar la reina
      return true;
   }

   private ImageView esSeguroColocarReina(int fila, int columna)
   {
      System.out.println("Procesando");
      for (int i = 0; i < nReinas; i++)
      {
         if (i != fila)
         {
            if (tableroPosicional[i][columna] == 1)
            {
               System.out.println("Inseguro! Misma columna");
               return reinasRojas[columna][i];
            }
         }

      }

      for (int i = 0; i < nReinas; i++)
      {
         if (i != columna)
         {
            if (tableroPosicional[fila][i] == 1)
            {
               System.out.println("Inseguro! Misma fila");
               return reinasRojas[i][fila];
            }
         }

      }
      // revisar la diagonal superior
      for (int i = fila + 1, j = columna + 1; i < nReinas && j < nReinas; i++, j++)
      {
         if (tableroPosicional[i][j] == 1)
         {
            imprimirCoordenadasTablero();
            System.out.println("Inseguro! 2");
            return reinasRojas[j][i];
         }
      }

      for (int i = fila - 1, j = columna - 1; i >= 0 && j >= 0; i--, j--)
      {
         if (tableroPosicional[i][j] == 1)
         {
            imprimirCoordenadasTablero();
            System.out.println("Inseguro! 2");
            return reinasRojas[j][i];
         }
      }

      for (int i = fila + 1, j = columna - 1; i < nReinas && j >= 0; i++, j--)
      {
         if (tableroPosicional[i][j] == 1)
         {
            imprimirCoordenadasTablero();
            System.out.println("Inseguro! 2");
            return reinasRojas[j][i];
         }
      }

      for (int i = fila - 1, j = columna + 1; i >= 0 && j < nReinas; i--, j++)
      {
         if (tableroPosicional[i][j] == 1)
         {
            imprimirCoordenadasTablero();
            System.out.println("Inseguro! 2");
            return reinasRojas[i][j];
         }
      }


      // si hemos llegado hasta aquí quiere decir que es seguro colocar la reina
      return null;
   }

   public void showBlackQueen(int column, int row)
   {
      PauseTransition pauseTransition = new PauseTransition(Duration.millis(millis));
      pauseTransition.setOnFinished(event ->
              {
                 System.out.println("Mostrando reina en: (" + column + "," + row + ")");
                  reinasNegras[column][row].setVisible(true);
              }
      );
      movimientos.add(pauseTransition);
   }

   private void hideBlackQueen(int column, int row)
   {
      PauseTransition pauseTransition = new PauseTransition(Duration.millis(0));
      pauseTransition.setOnFinished(event ->
              {
                 System.out.println("Mostrando reina en: (" + column + "," + row + ")");
                 reinasNegras[column][row].setVisible(false);
              }
      );
      movimientos.add(pauseTransition);
   }

   public void showAndHideRedQueens(int column, int row, int column2, int row2)
   {

      PauseTransition pauseTransition = new PauseTransition(Duration.millis(millis));
      pauseTransition.setOnFinished(event ->
              {
                 System.out.println("showing red queens");
                 reinasRojas[column][row].setVisible(true);
                 reinasRojas[column2][row2].setVisible(true);

              }
      );
      movimientos.add(pauseTransition);


      PauseTransition secondPauseTransition = new PauseTransition(Duration.millis(millis));
      secondPauseTransition.setOnFinished(event ->
              {
                 System.out.println("Eliminating red queen");
                 reinasRojas[column][row].setVisible(false);
                 reinasRojas[column2][row2].setVisible(false);
              }
      );
      movimientos.add(secondPauseTransition);
   }

   public void showBlackQueenImmediate(int column, int row)
   {
      System.out.println("Mostrando reina en: (" + column + "," + row + ")");
      reinasNegras[column][row].setVisible(true);
   }

   private void showRedQueenImmediate(int column, int row, ImageView guiltyQueen)
   {
      reinasRojas[column][row].setVisible(true);
      guiltyQueen.setVisible(true);
      PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));
      pauseTransition.setOnFinished(event ->
              {
                 System.out.println("Eliminating red queen");
                 reinasRojas[column][row].setVisible(false);
                 reinasNegras[column][row].setVisible(false);
                 guiltyQueen.setVisible(false);
              }
      );
      pauseTransition.play();
   }

   private void showSuccess()
   {
      Popups.showSuccess("¡Felicidades, lo has logrado!", mainApp.getMainWindow());
      timeLine.stop();
      timeLine.getChildren().removeAll();
      timerLabel.setVisible(false);
   }

   public void imprimirCoordenadasTablero()
   {
      int n = tableroPosicional.length;
      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            System.out.print(" " + tableroPosicional[i][j]);
         }
         System.out.println("");
      }
      System.out.println("");
   }

   public void handleDragDetection(MouseEvent event)
   {
      Dragboard db = blackQueen.startDragAndDrop(TransferMode.ANY);
      ClipboardContent cb = new ClipboardContent();
      cb.putString("1");
      db.setContent(cb);
      event.consume();
   }

   public void setEventHandlers(int column, int row)
   {
      cuadros[column][row].setOnDragOver(event -> event.acceptTransferModes(TransferMode.ANY));
      cuadros[column][row].setOnDragDropped(event ->
      {
         showBlackQueenImmediate(column, row);
         tableroPosicional[row][column] = 1;
         ImageView guiltyQueen = esSeguroColocarReina(row, column);
         if (guiltyQueen != null)
         {
            showRedQueenImmediate(column, row, guiltyQueen);
            tableroPosicional[row][column] = 0;
            lblQueensLeft.setText(Integer.parseInt(txtNReinas.getText()) - reinasColocadas + "");
         }
         else
         {
            reinasColocadas++;
            lblQueensLeft.setText(Integer.parseInt(txtNReinas.getText()) - reinasColocadas + "");
         }
         if (nReinas == reinasColocadas)
            showSuccess();
      });

   }

   public void setMainApp(Main mainApp)
   {
      this.mainApp = mainApp;
   }

}
