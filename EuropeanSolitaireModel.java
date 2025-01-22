package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Represents a European Solitaire Model with pegs on a board.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {

  /**
   * Empty Constructor.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructs a solitaire model by taking in an arm thickness and placing the empty slot in the
   * center.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructor that takes in the coordinates of an empty space.
   *
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * Constructs a model by taking in an arm thickness as well as coordinate for an empty slot.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the slot is not valid or the armthickness
   *                                  is not a positive odd number
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  /**
   * Determines if a given x and y is a valid position for an empty slot.
   *
   * @param armThickness the arm thickness of the board
   * @param i            the row of the empty slot
   * @param j            the column of the empty slot
   * @return a boolean determining if the slot is valid or not
   */
  protected boolean validEmptySlot(int armThickness, int i, int j) throws IllegalArgumentException {
    int lowLimit = armThickness - 1;
    int highLimit = lowLimit + lowLimit;
    //can we get rid of the /2 here?
    int maxLimit = (highLimit + columns - 1) / 2;

    //not on the board
    if (i < 0 || j < 0 || i >= getBoardSize() || j >= getBoardSize()) {
      return false;
    }
    //top left corner
    if (i < lowLimit && j < lowLimit) {
      return (i + j >= lowLimit);
    }
    //top right corner
    if (i < lowLimit && j > highLimit) {
      return ((Math.abs(i - j)) <= highLimit);
    }
    //bottom left corner
    if (i > highLimit && j < lowLimit) {
      return ((Math.abs(i - j)) <= highLimit);
    }
    //bottom right corner
    if (i > highLimit && j > highLimit) {
      //can we get rid of the *2 here?
      return (i + j <= maxLimit * 2);
    }
    return true;
  }


}
