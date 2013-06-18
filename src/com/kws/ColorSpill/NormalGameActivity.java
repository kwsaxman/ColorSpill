package com.kws.ColorSpill;

import android.content.Intent;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;

// -------------------------------------------------------------------------
/**
 * This is the ColorSpill Activity class. It will control what happens when the
 * user interacts with the GUI.
 * 
 * @author Ryan Harrigan (homerunh)
 * @author Richard Le (rle9)
 * @author Kevin Saxman (kwsaxma)
 * @version Apr 29, 2012
 */
public class NormalGameActivity
    extends Activity
{
    private ColorSpill     	model;
    private GameView 		view;
    private TextView       	status;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        view = (GameView)findViewById(R.id.colorSpillView);
        model = new ColorSpill(10, 25, 0,false);
        view.setModel(model);

        status = (TextView)findViewById(R.id.statusLabel);
        status.setText("Welcome to Color Spill. Click on a color to Start.");

        Button yellow = (Button)findViewById(R.id.Yellow);
        yellow.setBackgroundColor(Color.YELLOW);

        Button red = (Button)findViewById(R.id.Red);
        red.setBackgroundColor(Color.RED);

        Button white = (Button)findViewById(R.id.White);
        white.setBackgroundColor(Color.WHITE);

        Button magenta = (Button)findViewById(R.id.Magenta);
        magenta.setBackgroundColor(Color.MAGENTA);

        Button green = (Button)findViewById(R.id.Green);
        green.setBackgroundColor(Color.GREEN);

        Button blue = (Button)findViewById(R.id.Blue);
        blue.setBackgroundColor(Color.BLUE);

    }


    /**
     * Helper Method for Buttons.
     */
    private void buttonClick()
    {
        status.setText(Integer.toString(model.getMoves()) + "/"
            + model.getMaxMoves() + " Moves");
        if (model.isGameLost())
        {
            status.setText("You Lose!");
        }
        else if (model.isGameWon())
        {
            status.setText("You Win!");
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onYellowClick(View theView)
    {
        if (!model.isGameLost() && !model.isGameWon())
        {
            model.joinNewCells(Color.YELLOW);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onRedClick(View theView)
    {
        if (!model.isGameLost() && !model.isGameWon())
        {
            model.joinNewCells(Color.RED);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onWhiteClick(View theView)
    {
        if (!model.isGameLost() && !model.isGameWon())
        {
            model.joinNewCells(Color.WHITE);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onGreenClick(View theView)
    {
        if (!model.isGameLost() && !model.isGameWon())
        {
            model.joinNewCells(Color.GREEN);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onMagentaClick(View theView)
    {
        if (!model.isGameLost() && !model.isGameWon())
        {
            model.joinNewCells(Color.MAGENTA);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Change color.
     * 
     * @param theView the view.
     */
    public void onBlueClick(View theView)
    {

        if (!model.isGameLost() && !model.isGameWon())
        {
            status.setText("Blue");
            model.joinNewCells(Color.BLUE);
            buttonClick();
        }
    }


    // ----------------------------------------------------------
    /**
     * Start a new game.
     * 
     * @param theView the view.
     */
    public void onNewGameClick(View theView)
    {
        status.setText("New Game");

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
    
 // ----------------------------------------------------------
    /**
     * Start the menu.
     * 
     * @param theView the view.
     */
    public void onMenuClick(View theView)
    {
        Intent menuIntent = new Intent(NormalGameActivity.this,
				MenuActivity.class);
		NormalGameActivity.this.startActivity(menuIntent);
    }


    // ----------------------------------------------------------
    /**
     * Get the model.
     * 
     * @return the model
     */
    public ColorSpill getModel()
    {
        return model;
    }

}
