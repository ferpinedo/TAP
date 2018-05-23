package org.tapbej.recursividad.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class PopupController
{
   @FXML
   private Label lblMessage;

   @FXML
   private Pane pane;

   @FXML
   public void initialize()
   {
   }

   public void setText(String text)
   {
      lblMessage.setText(text);
   }

   public void setPaneStyle(String style)
   {
      pane.getStyleClass().add(style);
   }



}
