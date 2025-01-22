package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents a Marble Solitaire Controller.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable read;

  /**
   * Constructor for the marble solitaire controllre.
   *
   * @param model the english solitaire model
   * @param view  the english solitaire text view
   * @param read  the readable object
   * @throws IllegalArgumentException if any of the inputs are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable read) throws IllegalArgumentException {
    if (model == null || view == null || read == null) {
      throw new IllegalArgumentException("null");
    } else {
      this.model = model;
      this.view = view;
      this.read = read;
    }
  }

  /**
   * playGame method that allows a user to interact with the game.
   *
   * @throws IllegalArgumentException if inputs are not entered correctly or moves are invalid
   */
  @Override
  public void playGame() throws IllegalArgumentException {
    //outer loop
    while (!model.isGameOver()) {
      try {
        view.renderBoard();
        view.renderMessage("\nScore: " + model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      int numInputs = 0;
      int[] arr = new int[4];
      Scanner scan = new Scanner(this.read);

      //inner loop
      while (numInputs <= 3) {
        String next;
        try {
          next = scan.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException(e);
        }
        if (next.equals("Q") || next.equals("q")) {
          try {
            view.renderMessage("Game quit!\nState of game when quit:\n");
            view.renderBoard();
            view.renderMessage("\nScore: " + model.getScore());
            return;
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        } else {
          try {
            arr[numInputs] = Integer.parseInt(next);
            numInputs = numInputs + 1;
          } catch (NumberFormatException e) {
            try {
              view.renderMessage("Invalid move, please re-enter");
            } catch (IOException ex) {
              throw new IllegalStateException("message transmission failure");
            }
          }
        }
        if (numInputs == 4) {
          try {
            model.move(arr[0] - 1, arr[1] - 1, arr[2] - 1, arr[3] - 1);
            try {
              view.renderBoard();
              view.renderMessage("\nScore: " + model.getScore() + "\n");
            } catch (IOException e) {
              throw new IllegalStateException(e);
            }
            numInputs = 0;
            arr = new int[4];
          } catch (IllegalArgumentException i) {
            try {
              this.view.renderMessage("Invalid Move. Play Again.\n");
              arr = new int[4];
              numInputs = 0;
            } catch (IOException e) {
              throw new IllegalStateException("could not be outputted");
            }
          }
        }
        if (model.isGameOver()) {
          try {
            view.renderMessage("Game over!\n");
            view.renderBoard();
            view.renderMessage("\nScore: " + model.getScore());
            return;
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        //inner loop end
      }
      //outer loop end
    }


  }
}

