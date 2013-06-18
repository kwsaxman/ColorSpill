package com.kws.ColorSpill;

import java.util.HashSet;
import java.util.Observable;
import java.util.Random;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;

// -------------------------------------------------------------------------
/**
 * This is the color spill class. It represents the game board and all of its
 * necessary attributes.
 * 
 * @author Ryan Harrigan (homerunh)
 * @author Richard Le (rle9)
 * @author Kevin Saxman (kwsaxma)
 * @version Apr 29, 2012
 */

public class ColorSpill extends Observable {
	private HashSet<Cell> active;
	private int size;
	private int moves;
	private boolean[][] counted;
	private int maxMoves;
	private int[] colors = { Color.RED, Color.BLUE, Color.WHITE, Color.MAGENTA,
			Color.YELLOW, Color.GREEN };
	private Cell[][] board;
	private Random random;
	private boolean timed;
	private CountDownTimer timer;
	private long timeLeft;
	private long maxTime;

	/**
	 * The constructor for the ColorSpill class.
	 * 
	 * @param size
	 *            Used to create the size of the board.
	 * @param maxMoves
	 *            The maximum moves allowed for the game.
	 */
	public ColorSpill(int size, int maxMoves, long maxTime, boolean timed) {
		this.size = size;
		this.maxMoves = maxMoves;
		this.timed = timed;
		this.maxTime = maxTime;
		moves = 0;
		timeLeft = maxTime;
		board = new Cell[size][size];
		active = new HashSet<Cell>();
		random = new Random();
		timer = null;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int randomColor = colors[random.nextInt(colors.length)];
				board[i][j] = new Cell(randomColor, i, j);
			}
		}

		if (this.timed) {
			timer = new CountDownTimer(30000, 1000) {

				public void onTick(long millisUntilFinished) {
					timeLeft -= 1000;
					setChanged();
					notifyObservers();
				}

				public void onFinish() {
					if (isGameWon()) {
						// toast?
					} else if (isGameLost()) {
						// toast?
					} else {
						// Time's up, assume game lost?
					}
					Log.i("COLORSPILL", "Timer expired.");
					setChanged();
					notifyObservers();
				}
			};
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Returns the active cells. All the cells that are currently joined
	 * together.
	 * 
	 * @return The Active cells.
	 */
	public HashSet<Cell> getActiveCells() {
		counted = new boolean[size][size];
		active = new HashSet<Cell>();
		floodcount(0, 0, board[0][0].getColor());
		return active;
	}

	private void floodcount(int x, int y, int color) {
		if (x > board.length - 1 || y > board.length - 1 || x < 0 || y < 0
				|| counted[x][y] || board[x][y].getColor() != color) {
			return;
		}
		active.add(board[x][y]);
		counted[x][y] = true;
		floodcount(x + 1, y, color);
		floodcount(x, y + 1, color);
		floodcount(x - 1, y, color);
		floodcount(x, y - 1, color);
	}

	/**
	 * Joins cells on the specified color.
	 * 
	 * @param color
	 *            The color to join the cells with.
	 */
	public void joinNewCells(int color) {
		Log.i("COLORSPILL", "Join New Cells");
		int before = getActiveCells().size();

		floodfill(0, 0, board[0][0].getColor(), color);
		if (before != getActiveCells().size()) {
			if (moves == 0) {
				Log.i("COLORSPILL", "Timer Start");
				timer.start();
			}
			moves++;
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Fills the board with the color.
	 * 
	 * @param x
	 *            The x index.
	 * @param y
	 *            The y index.
	 * @param start
	 *            The starting color.
	 * @param end
	 *            The ending color.
	 */
	private void floodfill(int x, int y, int start, int end) {
		if (x > board.length - 1 || y > board.length - 1 || x < 0 || y < 0
				|| start == end) {
			return;
		}
		if (board[x][y].getColor() != start) {
			return;
		}

		board[x][y] = new Cell(end, x, y);
		floodfill(x + 1, y, start, end);
		floodfill(x, y + 1, start, end);
		floodfill(x - 1, y, start, end);
		floodfill(x, y - 1, start, end);
	}

	/**
	 * Returns whether the game is won or not. All cells are active.
	 * 
	 * @return Whether the game is won or not.
	 */
	public boolean isGameWon() {
		if (!timed) {
			return getActiveCells().size() == size * size;
		} else {
			return getActiveCells().size() == size * size && isTimeLeft();
		}
	}

	/**
	 * Returns whether the game is lost or not. Max moves reached and board not
	 * controlled.
	 * 
	 * @return Whether the game is lost or not.
	 */
	public boolean isGameLost() {
		if (!timed) {
			return moves >= maxMoves && !isGameWon();
		}
		return !isTimeLeft() && !isGameWon();
	}

	public long getTimeLeft() {
		Log.i("COLORSPILL", "" + timeLeft);
		return timeLeft;
	}

	public boolean isTimeLeft() {
		return (getTimeLeft() != (long) 0);
	}

	// ----------------------------------------------------------
	/**
	 * Get the board size.
	 * 
	 * @return size of the board.
	 */
	public int getSize() {
		return size;
	}

	// ----------------------------------------------------------
	/**
	 * Get the maximum number of moves.
	 * 
	 * @return - the number of max moves.
	 */
	public int getMaxMoves() {
		return maxMoves;
	}

	// ----------------------------------------------------------
	/**
	 * Get the maximum time.
	 * 
	 * @return - the number of max time.
	 */
	public long getMaxTime() {
		return maxTime;
	}

	// ----------------------------------------------------------
	/**
	 * Get Moves.
	 * 
	 * @return moves
	 */
	public int getMoves() {
		return moves;
	}

	// ----------------------------------------------------------
	/**
	 * Get a certain cell on the game board
	 * 
	 * @param x
	 *            - the x coordinate
	 * @param y
	 *            - the y coordinate
	 * @return - the cell at the specified location.
	 */
	public Cell getCell(int x, int y) {
		return board[x][y];
	}
}
