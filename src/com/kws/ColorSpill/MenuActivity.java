package com.kws.ColorSpill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

    }
    
    // ----------------------------------------------------------
    /**
     * Start a new game.
     * 
     * @param theView the view.
     */
    public void onNormalGameClick(View theView)
    {
        Intent gameIntent = new Intent(MenuActivity.this,
				NormalGameActivity.class);
		MenuActivity.this.startActivity(gameIntent);

    }
    
    // ----------------------------------------------------------
    /**
     * Start a new game.
     * 
     * @param theView the view.
     */
    public void onTimedGameClick(View theView)
    {
    	Intent gameIntent = new Intent(MenuActivity.this,
				TimedGameActivity.class);
		MenuActivity.this.startActivity(gameIntent);

    }
    
    // ----------------------------------------------------------
    /**
     * Start a new game.
     * 
     * @param theView the view.
     */
    public void onHighScoresClick(View theView)
    {
    	Intent gameIntent = new Intent(MenuActivity.this,
				NormalGameActivity.class);
		MenuActivity.this.startActivity(gameIntent);

    }
}
