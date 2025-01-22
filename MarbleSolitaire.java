package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;


/**
 * Represents a type that allows the program to take in command line arguments to specify
 * which type of solitaire model that they would like to construct.
 */
public final class MarbleSolitaire {
  /**
   * Main method that takes in user inputs for the command line.
   * @param args represents user inputs and stores them in an array of Strings.
   */
  public static void main(String[] args) {
    int size = 3;
    int emptyX = 3;
    int emptyY = 3;
    String input = "";
    if (args[0].equals("english")) {
      input = "english";
    }
    if (args[0].equals("triangular")) {
      input = "triangular";
      size = 5;
      emptyX = 0;
      emptyY = 0;
    }
    if (args[0].equals("european")) {
      input = "european";
    }
    if (args.length > 2) {
      if (args[1].equals("-size")) {
        size = Integer.parseInt(args[2]);
      }
      if (args[1].equals("-hole")) {
        emptyX = Integer.parseInt(args[2]);
        emptyY = Integer.parseInt(args[3]);
      }
    }
    if (args.length > 3) {
      if (args[4].equals("-size")) {
        size = Integer.parseInt(args[5]);
      }
      if (args[3].equals("-hole")) {
        emptyX = Integer.parseInt(args[4]);
        emptyY = Integer.parseInt(args[5]);
      }
    }
    switch (input) {
      case "european":
        Readable in = new InputStreamReader(System.in);
        MarbleSolitaireModel model = new EuropeanSolitaireModel(size, emptyX, emptyY);
        MarbleSolitaireView view = new MarbleSolitaireTextView(model, System.out);
        MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
        controller.playGame();
        break;
      case "triangular":
        Readable in2 = new InputStreamReader(System.in);
        MarbleSolitaireModel model2 = new TriangleSolitaireModel(size, emptyX, emptyY);
        MarbleSolitaireView view2 = new TriangleSolitaireTextView(model2, System.out);
        MarbleSolitaireController controller2 =
                new MarbleSolitaireControllerImpl(model2, view2, in2);
        controller2.playGame();
        break;
      case "english":
        Readable in3 = new InputStreamReader(System.in);
        MarbleSolitaireModel model3 = new EnglishSolitaireModel(size, emptyX, emptyY);
        MarbleSolitaireView view3 = new MarbleSolitaireTextView(model3, System.out);
        MarbleSolitaireController controller3 =
                new MarbleSolitaireControllerImpl(model3, view3, in3);
        controller3.playGame();
        break;
      default:
    }

  }
}
