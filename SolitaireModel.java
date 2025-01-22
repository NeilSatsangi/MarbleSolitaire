package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents an English Solitaire Model.
 */
public abstract class SolitaireModel implements MarbleSolitaireModel {
  protected int score;
  protected int armThickness;
  protected SlotState[][] board;
  protected int rows;
  protected int columns;
  protected int emptyX;
  protected int emptyY;


  /**
   * Empty Constructor.
   */
  public SolitaireModel() {
    this.score = 0;
    this.armThickness = 3;
    this.rows = (this.armThickness * 3) - 2;
    this.columns = this.rows;
    this.emptyX = armThickness;
    this.emptyY = armThickness;
    board = new SlotState[this.rows][this.columns];
    this.initializeBoard();
  }

  /**
   * Constructor that takes in the coordinates of an empty space.
   *
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public SolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    this.rows = (this.armThickness * 3) - 2;
    this.columns = this.rows;
    if (!(validEmptySlot(armThickness, sRow, sCol))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      this.score = 0;
      this.emptyX = sRow;
      this.emptyY = sCol;
      board = new SlotState[this.rows][this.columns];
      this.initializeBoard();
    }
  }

  /**
   * Constructs a solitaire model by taking in an arm thickness and placing the empty slot in the
   * center.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public SolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness <= 2 || armThickness % 2 != 1) {
      throw new IllegalArgumentException("Invalid ArmThickness");
    } else {
      this.score = 0;
      this.armThickness = armThickness;
      this.rows = (this.armThickness * 3) - 2;
      this.columns = this.rows;
      this.emptyX = (((armThickness * 3) - 2) / 2);
      this.emptyY = (((armThickness * 3) - 2) / 2);
      board = new SlotState[this.rows][this.columns];
      this.initializeBoard();
    }

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
  public SolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness <= 2 || armThickness % 2 != 1) {
      throw new IllegalArgumentException("Invalid");
    } else {
      this.armThickness = armThickness;
      this.rows = (this.armThickness * 3) - 2;
      this.columns = this.rows;
    }
    if (!(validEmptySlot(armThickness, sRow, sCol))) {
      throw new IllegalArgumentException("Invalid");
    } else {
      this.score = 0;
      this.emptyX = sRow;
      this.emptyY = sCol;
      board = new SlotState[this.rows][this.columns];
      this.initializeBoard();
    }

  }

  /**
   * Determines if a given x and y is a valid position for an empty slot.
   *
   * @param armThickness the arm thickness of the board
   * @param i            the row of the empty slot
   * @param j            the column of the empty slot
   * @return a boolean determining if the slot is valid or not
   */
  protected boolean validEmptySlot(int armThickness, int i, int j) {
    int lowLimit = armThickness - 1;
    int highLimit = lowLimit * 2;

    if (i < 0 || j < 0 || i >= getBoardSize() || j >= getBoardSize()) {
      return false;
    }
    return ((i >= lowLimit && i <= highLimit) || (j >= lowLimit && j <= highLimit));
  }

  /**
   * Initializes the solitaire board and places marbles accordingly.
   */
  protected void initializeBoard() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (validEmptySlot(this.armThickness, i, j)) {
          board[i][j] = SlotState.Marble;
          this.score += 1;
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
    board[emptyX][emptyY] = SlotState.Empty;
    score -= 1;
  }

  /**
   * Moves a marble from a position to another position if the move is valid.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not considered to be valid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      //right
      if (toCol - fromCol == 2 && fromRow - toRow == 0) {
        if (board[fromRow][fromCol + 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow][fromCol + 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score -= 1;
        }
      }
      //left
      if (fromCol - toCol == 2 && fromRow - toRow == 0) {
        if (board[fromRow][fromCol - 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow][fromCol - 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score -= 1;
        }
      }
      //up
      if (toCol - fromCol == 0 && fromRow - toRow == 2) {
        if (board[fromRow - 1][fromCol] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow - 1][fromCol] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score -= 1;
        }
      }
      //down
      if (toCol - fromCol == 0 && toRow - fromRow == 2) {
        if (board[fromRow + 1][fromCol] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow + 1][fromCol] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score -= 1;
        }
      }
    } else {
      throw new IllegalArgumentException("Move is not valid");
    }
  }

  /**
   * Helper method for move in order to determine if a move is valid or not.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return a boolean determining if the move is valid or not
   */

  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (!(validEmptySlot(this.armThickness, fromRow, fromCol))) {
      return false;
    }
    if (!(validEmptySlot(this.armThickness, toRow, toCol))) {
      return false;
    }
    if (board[fromRow][fromCol] != SlotState.Marble) {
      return false;
    }
    if (board[toRow][toCol] != SlotState.Empty) {
      return false;
    }
    if (board[toRow][toCol] == SlotState.Invalid) {
      return false;
    }
    //checks if the two positions are exactly 2 slots away
    return ((Math.abs(fromRow - toRow) == 2 && (Math.abs(fromCol - toCol)) == 0) ||
            (Math.abs(fromRow - toRow) == 0 && (Math.abs(fromCol - toCol)) == 2));
  }


  /**
   * Determines if the game has ended by determining if there are any playable moves remaining.
   *
   * @return a boolean to see if the game is over.
   */
  @Override
  public boolean isGameOver() {
    int temp = 2;
    boolean proceed = false;
    SlotState e;
    for (int r = 0; r < getBoardSize(); r++) {
      for (int c = 0; c < getBoardSize(); c++) {
        e = board[r][c];
        if (e == SlotState.Marble) {
          if (r >= temp) {
            if (board[r - 1][c] == SlotState.Marble && board[r - temp][c] == SlotState.Empty) {
              proceed |= true;
            } else {
              proceed |= false;
            }
          }
          if (r < getBoardSize() - temp) {
            if (board[1 + r][c] == SlotState.Marble && board[r + temp][c] == SlotState.Empty) {
              proceed |= true;
            } else {
              proceed |= false;
            }
          }
          if (c >= temp) {
            if (board[r][c - 1] == SlotState.Marble && board[r][c - temp] == SlotState.Empty) {
              proceed |= true;
            } else {
              proceed |= false;
            }
          }
          if (c < getBoardSize() - temp) {
            if (board[r][1 + c] == SlotState.Marble && board[r][temp + c] == SlotState.Empty) {
              proceed |= true;
            } else {
              proceed |= false;
            }
          }
        }
      }
    }
    return !proceed;
  }

  /**
   * Gives the size of a board which is the longest dimension.
   *
   * @return an int representing the size of a board.
   */
  @Override
  public int getBoardSize() {
    return this.rows;
  }

  /**
   * Provides the state of a slot at a given position.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return a slotstate of the given position
   * @throws IllegalArgumentException if the given row and col are not valid
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= rows || col >= columns) {
      throw new IllegalArgumentException("Not a valid slot.");
    } else {
      return board[row][col];
    }
  }

  /**
   * Provides the current score of the board.
   *
   * @return an int representing the number of marbles remaining on the board
   */
  @Override
  public int getScore() {
    return this.score;
  }


}