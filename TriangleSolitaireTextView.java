package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a text view that can display the Triangle Solitaire Model in a visual output.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState model;
  private Appendable ap;

  /**
   * Constructor that takes in a MarbleSolitaireModelState.
   *
   * @param model the model state that is displayed
   * @throws IllegalArgumentException if the model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    } else {
      this.model = model;
    }
  }

  /**
   * Constructor that takes in a model state and an appendable object.
   *
   * @param model the model state is displayed
   * @param ap    appendable object
   * @throws IllegalArgumentException if the model or the appendable object is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model is null");
    } else {
      this.model = model;
      this.ap = ap;
    }
  }

  /**
   * toString method that displays the contents of the board as a string.
   *
   * @return a string representing the current board
   */
  @Override
  public String toString() {
    String output = "";
    int temp = (model.getBoardSize() + 2) / 3;
    int x = temp - 1;
    int y = temp * 2 - 2;

    for (int i = 0; i < model.getBoardSize(); i++) {
      if (output.length() > 1) {
        output = output.substring(0, output.length() - 1);
      }
      for (int j = 0; j < model.getBoardSize(); j++) {
        MarbleSolitaireModelState.SlotState e = model.getSlotAt(i, j);
        switch (e) {
          case Empty:
            if (i == model.getBoardSize() && j == model.getBoardSize()) {
              output += "_";
            } else if (i == 0 && j == 0) {
              for (int g = 0; g < model.getBoardSize() - 1; g++) {
                output += " ";
              }
              output += "_" + "\n";
            } else if (i == j) {
              output += "_" + "\n";
            } else {
              output += "_ ";
            }
            break;
          case Marble:
            if (i == model.getBoardSize() && j == model.getBoardSize()) {
              output += "O";
            } else if (i == 0 && j == 0) {
              for (int g = 0; g < model.getBoardSize() - 1; g++) {
                output += " ";
              }
              output += "O" + "\n";
            } else if (i == j) {
              output += "O" + "\n";
            } else {
              output += "O ";
            }
            break;
          case Invalid:
            output += " ";
            break;
          default:
            System.out.print("Error");
        }
      }
    }
    return output.substring(0, output.length() - 1);
  }

  @Override
  public void renderBoard() throws IOException {
    ap.append(toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }
}
