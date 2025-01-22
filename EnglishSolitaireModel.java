package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.SolitaireModel;

/**
 * Represents a traditional English Solitaire Model with pegs on a board.
 */
public class EnglishSolitaireModel extends SolitaireModel {

  /**
   * Empty Constructor.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Constructor that takes in the coordinates of an empty space.
   *
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * Constructs a solitaire model by taking in an arm thickness and placing the empty slot in the
   * center.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);

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
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }


}