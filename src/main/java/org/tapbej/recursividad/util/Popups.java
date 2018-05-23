package org.tapbej.recursividad.util;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.tapbej.recursividad.Main;
import org.tapbej.recursividad.controlador.PopupController;
import org.tapbej.recursividad.controlador.PopupWithHeaderController;

import java.io.IOException;

public class Popups
{
   private static void buildAndShowPopup(String content, String paneStyle, Stage owner, long millis)
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("view/Popup.fxml"));
      AnchorPane pane = null;
      try
      {
         pane = loader.load();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      // get controller
      PopupController controller = loader.getController();

      Popup popup = new Popup();

      popup.getContent().addAll(pane);
      popup.centerOnScreen();
      controller.setText(content);
      controller.setPaneStyle(paneStyle);

      popup.show(owner);

      Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
      popup.setX(50/*((primScreenBounds.getWidth() - popup.getWidth()) / 4)*/);
      popup.setY(primScreenBounds.getHeight() - 160);

      PauseTransition pause = new PauseTransition(Duration.millis(millis));
      createAnimation(popup, pause);
      pause.play();
   }

   private static void buildAndShowPopup(String header, String content, String paneStyle, Stage owner, long millis)
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("vista/PopupWithHeaderView.fxml"));
      AnchorPane pane = null;
      try
      {
         pane = loader.load();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      // get controller
      PopupWithHeaderController controller = loader.getController();

      Popup popup = new Popup();

      popup.getContent().addAll(pane);
      popup.centerOnScreen();
      controller.setText(content);
      controller.setPaneStyle(paneStyle);
      controller.setHeader(header);
      popup.show(owner);

      Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
      popup.setX(50/*((primScreenBounds.getWidth() - popup.getWidth()) / 4)*/);
      popup.setY(primScreenBounds.getHeight() - 160);


      PauseTransition pause = new PauseTransition(Duration.millis(millis));
      createAnimation(popup, pause);
      pause.play();
   }

   private static void createAnimation(Popup popup, PauseTransition pause)
   {
      pause.setOnFinished(event ->
              {
                 Timeline timeline = new Timeline();
                 KeyFrame key = new KeyFrame(Duration.millis(1000), new KeyValue(popup.opacityProperty(), 0));
                 timeline.getKeyFrames().add(key);
                 timeline.play();
                 timeline.setOnFinished(action -> popup.hide());
              }
      );
   }

   public static void showSuccess(String content, Stage owner)
   {
      buildAndShowPopup(content, "ready-pane", owner, 3000);
   }

   public static void showFailure(String content, Stage owner)
   {
      buildAndShowPopup(content, "failure-pane", owner, 3000);
   }

   public static void showInfo(String content, Stage owner)
   {
      buildAndShowPopup(content, "info-popup", owner, 7000);
   }

   public static void showInfo(String header, String content, Stage owner)
   {
      buildAndShowPopup(header, content, "info-popup", owner, 5000);
   }

}
