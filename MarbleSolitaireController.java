package cs3500.marblesolitaire.controller;

/**
 * Represents a controller for the Marble Solitaire Models by allowing users to play the game
 * by entering valid moves.
 */
public interface MarbleSolitaireController {

  /**
   * Allows the user to play the game through the command line by entering moves.
   *
   * @throws IllegalArgumentException if an entered argument is invalid.
   */
  void playGame() throws IllegalArgumentException;
}
