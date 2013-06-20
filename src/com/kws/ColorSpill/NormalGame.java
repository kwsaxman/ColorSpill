package com.kws.ColorSpill;

public class NormalGame extends Game {
	private int moves;
	private int maxMoves;

	public NormalGame(int width, int height, int maxMoves) {
		super(width, height);
		this.moves = 0;
		this.maxMoves = maxMoves;
		
		setChanged();
		notifyObservers();
	}
	
	public int getMoves(){
		return this.moves;
	}
	
	public int getMaxMoves(){
		return this.maxMoves;
	}
	
	@Override
	public boolean joinNewCells(int color){
		boolean successful = super.joinNewCells(color);
		if (successful) {
			setChanged();
			notifyObservers();
			this.moves ++;
		}
		setChanged();
		notifyObservers();
		return successful;
		
	}

	@Override
	public boolean isGameWon() {
		return (this.moves <= this.maxMoves)
				&& (super.getActiveCells().size() == super.getHeight()
						* super.getWidth());
	}

	@Override
	public boolean isGameLost() {
		return (this.moves >= this.maxMoves)
				&& (super.getActiveCells().size() < super.getHeight()
						* super.getWidth());
	}

}
