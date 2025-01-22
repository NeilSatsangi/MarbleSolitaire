package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a type of Marble Solitaire Text View.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  Appendable ap;

  /**
   * Constructor that takes in a MarbleSolitaireModelState.
   *
   * @param model the model state that is displayed
   * @throws IllegalArgumentException if the model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
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
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
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
      for (int j = 0; j < model.getBoardSize() - 1; j++) {
        MarbleSolitaireModelState.SlotState e = model.getSlotAt(i, j);
        switch (e) {
          case Empty:
            if (i >= x && i <= y && j + 1 == model.getBoardSize() - 1) {
              if (model.getSlotAt(i, j + 1)
                      .equals(MarbleSolitaireModelState.SlotState.Empty)) {
                output += "_ _" + "\n";
              } else {
                output += "_ O" + "\n";
              }
              j = model.getBoardSize();
            } else if (i < x || i > y) {
              if (model.getSlotAt(i, j + 1)
                      .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
                output += "_" + "\n";
                j = model.getBoardSize();
              } else {
                output += "_ ";
              }
            } else {
              output += "_ ";
            }
            break;
          case Marble:
            if (i >= x && i <= y && j + 1 == model.getBoardSize() - 1) {
              if (model.getSlotAt(i, j + 1)
                      .equals(MarbleSolitaireModelState.SlotState.Empty)) {
                output += "O _" + "\n";
              } else {
                output += "O O" + "\n";
              }
              j = model.getBoardSize();
            } else if (i < x || i > y) {
              if (model.getSlotAt(i, j + 1)
                      .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
                output += "O" + "\n";
                j = model.getBoardSize();
              } else {
                output += "O ";
              }
            } else {
              output += "O ";
            }
            break;
          case Invalid:
            if (j < x) {
              output += "  ";
            }
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


