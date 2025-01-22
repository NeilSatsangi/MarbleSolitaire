package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a Triangle Solitaire Model with pegs and an empty space that allows the user to play
 * the game.
 */
public class TriangleSolitaireModel extends SolitaireModel {

  /**
   * Default empty constructor that takes in no arguments.
   */
  public TriangleSolitaireModel() {
    this.score = 0;
    this.armThickness = 5;
    this.rows = this.armThickness - 1;
    this.columns = this.rows;
    this.emptyX = 0;
    this.emptyY = 0;
    board = new SlotState[this.armThickness][this.armThickness];
    this.initializeBoard();
  }

  /**
   * Constructs a solitaire model by taking in an arm thickness and placing the empty slot in the
   * center.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public TriangleSolitaireModel(int armThickness) {
    if (armThickness < 2) {
      throw new IllegalArgumentException("Invalid ArmThickness");
    } else {
      this.score = 0;
      this.armThickness = armThickness;
      this.rows = this.armThickness - 1;
      this.columns = this.rows;
      this.emptyX = 0;
      this.emptyY = 0;
      board = new SlotState[this.armThickness][this.armThickness];
      this.initializeBoard();
    }
  }

  /**
   * Constructor that takes in the coordinates of an empty space.
   *
   * @param sRow the row of the empty space
   * @param sCol the column of the empty space
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 5;
    this.rows = this.armThickness - 1;
    this.columns = this.rows;
    if (!(validEmptySlot(armThickness, sRow, sCol))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      this.score = 0;
      this.emptyX = sRow;
      this.emptyY = sCol;
      board = new SlotState[this.armThickness][this.armThickness];
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
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness < 2) {
      throw new IllegalArgumentException("Invalid");
    } else {
      this.armThickness = armThickness;
      this.rows = this.armThickness - 1;
      this.columns = this.rows;
    }
    if (!(validEmptySlot(armThickness, sRow, sCol))) {
      throw new IllegalArgumentException("Invalid");
    } else {
      this.score = 0;
      this.emptyX = sRow;
      this.emptyY = sCol;
      board = new SlotState[this.armThickness][this.armThickness];
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
  public boolean validEmptySlot(int armThickness, int i, int j) throws IllegalArgumentException {
    //not on the board
    if (i < 0 || j < 0 || i > rows || j > columns) {
      return false;
    }
    //condition
    return (i >= j);
  }

  /**
   * Initializes the solitaire board and places marbles accordingly.
   */
  protected void initializeBoard() {
    for (int i = 0; i < armThickness; i++) {
      for (int j = 0; j < armThickness; j++) {
        if (validEmptySlot(this.armThickness, i, j)) {
          board[i][j] = SlotState.Marble;
          this.score++;
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
      //horizontal right
      if (toCol - fromCol == 2 && fromRow - toRow == 0) {
        if (board[fromRow][fromCol + 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow][fromCol + 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //horizontal left
      if (fromCol - toCol == 2 && fromRow - toRow == 0) {
        if (board[fromRow][fromCol - 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow][fromCol - 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //vertical up
      if (toCol - fromCol == 0 && fromRow - toRow == 2) {
        if (board[fromRow - 1][fromCol] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow - 1][fromCol] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //vertical down
      if (toCol - fromCol == 0 && toRow - fromRow == 2) {
        if (board[fromRow + 1][fromCol] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow + 1][fromCol] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //diagonal up left
      if (fromCol - toCol == 2 && fromRow - toRow == 2) {
        if (board[fromRow - 1][fromCol - 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow - 1][fromCol - 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //diagonal up right
      if (fromCol - toCol == -2 && fromRow - toRow == 2) {
        if (board[fromRow - 1][fromCol + 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow - 1][fromCol + 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //diagonal down left
      if (toCol - fromCol == 2 && toRow - fromRow == 2) {
        if (board[fromRow + 1][fromCol + 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow + 1][fromCol + 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
        }
      }
      //diagonal down right
      if (toCol - fromCol == -2 && toRow - fromRow == 2) {
        if (board[fromRow + 1][fromCol - 1] == SlotState.Marble) {
          //initial marble
          board[fromRow][fromCol] = SlotState.Empty;
          //middle marble
          board[fromRow + 1][fromCol - 1] = SlotState.Empty;
          //new slot for marble
          board[toRow][toCol] = SlotState.Marble;
          score--;
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
    //(5,3,3,1)
    if (!(validEmptySlot(this.armThickness, fromRow, fromCol))) {
      return false;
    }
    if (!(validEmptySlot(this.armThickness, toRow, toCol))) {
      return false;
    }
    if (board[fromRow][fromCol] != SlotState.Marble) {
      return false;
    }
    if (board[(toRow + fromRow) / 2][(toCol + fromCol) / 2] != SlotState.Marble) {
      return false;
    }
    if (board[toRow][toCol] != SlotState.Empty) {
      return false;
    }
    if (board[toRow][toCol] == SlotState.Invalid) {
      return false;
    }
    return ((Math.abs(fromRow - toRow) == 2 && (Math.abs(fromCol - toCol)) == 0) ||
            (Math.abs(fromRow - toRow) == 0 && (Math.abs(fromCol - toCol)) == 2) ||
            (Math.abs(fromRow - toRow) == 2 && (Math.abs(fromCol - toCol)) == 2));

  }

  /**
   * Determines if the game has ended by determining if there are any playable moves remaining.
   *
   * @return a boolean to see if the game is over.
   */
  @Override
  public boolean isGameOver() {
    for (int r = 0; r < getBoardSize(); r++) {
      for (int c = 0; c < getBoardSize(); c++) {

        //left
        if (c > 1 && validMove(r, c, r, c - 2)) {
          return false;
        }

        //right
        // c < getBoardSize() - 1 &&
        if (validMove(r, c, r, c + 2)) {
          return false;
        }

        //up
        if (r > 1 && validMove(r, c, r - 2, c)) {
          return false;
        }
        //down
        if (c < getBoardSize() - 1 && validMove(r, c, r + 2, c)) {
          return false;
        }
        //diagonal up left
        if (r > 1 && c > 1 && validMove(r, c, r - 2, c - 2)) {
          return false;
        }
        //diagonal down right
        if (r < getBoardSize() - 1 && c < getBoardSize() - 1 && validMove(r, c, r + 2, c + 2)) {
          return false;
        }
        //diagonal down left
        if (r > 1 && c < getBoardSize() - 1 && validMove(r, c, r + 2, c - 2)) {
          return false;
        }
        //diagonal up right
        if (r < getBoardSize() - 1 && c > 1 && validMove(r, c, r - 2, c + 2)) {
          return false;
        }

      }
    }
    return true;
  }

  /**
   * Gives the size of a board which is the longest dimension.
   *
   * @return an int representing the size of a board.
   */
  @Override
  public int getBoardSize() {
    return super.getBoardSize() + 1;
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
    if (row < 0 || col < 0 || row > rows || col > columns) {
      throw new IllegalArgumentException("Not a valid slot.");
    } else {
      return board[row][col];
    }
  }

}
