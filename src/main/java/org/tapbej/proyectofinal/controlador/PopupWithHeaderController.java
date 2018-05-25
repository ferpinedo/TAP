package org.tapbej.proyectofinal.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class PopupWithHeaderController
{
   @FXML
   private Label lblMessage;

   @FXML
   private Pane pane;

   @FXML
   private Label lblHeader;

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

   public void setHeader(String header) { lblHeader.setText(header);}



}
