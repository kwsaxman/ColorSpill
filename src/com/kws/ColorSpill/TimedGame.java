package com.kws.ColorSpill;

import android.os.CountDownTimer;
import android.util.Log;

public class TimedGame extends Game {

	private long maxTime;
	private long elapsedTime;
	private CountDownTimer timer;

	public TimedGame(int width, int height, long maxTime) {
		super(width, height);
		this.elapsedTime = 0;
		this.maxTime = maxTime;

		this.timer = new GameCountDownTimer(this.maxTime, 1000);

		setChanged();
		notifyObservers();
	}

	public long getElapsedTime() {
		return this.elapsedTime;
	}

	public long getMaxTime() {
		return this.maxTime;
	}

	@Override
	public boolean joinNewCells(int color) {
		boolean successful = super.joinNewCells(color);
		if (getElapsedTime() == (long)0) {
			Log.i("COLORSPILL", "Timer started");
			this.timer.start();
		}
		return successful;
	}

	@Override
	public boolean isGameWon() {
		return (super.getActiveCells().size() >= super.getHeight()
				* super.getWidth())
				&& (getElapsedTime() < getMaxTime());
	}

	@Override
	public boolean isGameLost() {
		return (super.getActiveCells().size() < super.getHeight()
				* super.getWidth())
				&& (getElapsedTime() >= getMaxTime());
	}

	private class GameCountDownTimer extends CountDownTimer {

		private long countDownInterval;

		public GameCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			this.countDownInterval = countDownInterval;
		}

		public void onTick(long millisUntilFinished) {
			elapsedTime += this.countDownInterval;
			setChanged();
			notifyObservers();
		}

		public void onFinish() {
			elapsedTime = getMaxTime();
			if (isGameWon()) {
				// toast?
			} else {
				// toast?
			} 
			Log.i("COLORSPILL", "Timer expired");
			setChanged();
			notifyObservers();
		}
	}

}
