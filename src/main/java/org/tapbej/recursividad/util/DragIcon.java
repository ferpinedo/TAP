package org.tapbej.recursividad.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DragIcon extends AnchorPane
{

   @FXML
   AnchorPane root_pane;

   public DragIcon() {

      FXMLLoader fxmlLoader = new FXMLLoader(
              getClass().getResource("/DragIcon.fxml")
      );

      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);

      try {
         fxmlLoader.load();

      } catch (IOException exception) {
         throw new RuntimeException(exception);
      }
   }

   @FXML
   private void initialize() {}

   public void relocateToPoint (Point2D p) {

      //relocates the object to a point that has been converted to
      //scene coordinates
      Point2D localCoords = getParent().sceneToLocal(p);
      relocate (
              (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
              (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
      );
   }

}
