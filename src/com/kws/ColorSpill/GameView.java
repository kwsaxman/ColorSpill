package com.kws.ColorSpill;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import java.util.Observable;
import java.util.Observer;

// -------------------------------------------------------------------------
/**
 * The Color Spill View.
 *
 * @author Ryan Harrigan (homerunh)
 * @author Richard Le (rle9)
 * @author Kevin Saxman (kwsaxma)
 * @version Apr 29, 2012
 */

public class GameView
    extends View
{
    private ColorSpill model;


    // ----------------------------------------------------------
    /**
     * Create a new ColorSpillView object.
     *
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    // ----------------------------------------------------------
    /**
     * Get the model.
     *
     * @return - the model
     */
    public ColorSpill getModel()
    {
        return model;
    }


    // ----------------------------------------------------------
    /**
     * Sets the model that will be rendered by this view. Calling this method
     * also adds the view as an observer for the model.
     *
     * @param theModel
     *            the ColorSpill
     */
    public void setModel(ColorSpill theModel)
    {
        model = theModel;

        model.addObserver(new ColorSpillObserver());
    }


    // ----------------------------------------------------------
    /**
     * Overridden to force the view to be square (have the same width and
     * height).
     *
     * @param widthMeasureSpec
     *            the desired width as determined by the layout
     * @param heightMeasureSpec
     *            the desired height as determined by the layout
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Choose the smallest of the two dimensions to use for both.
        int measureSpec = Math.min(widthMeasureSpec, heightMeasureSpec);

        // Call the superclass implementation but pass it our modified width
        // and height instead of the incoming ones.
        super.onMeasure(measureSpec, measureSpec);
    }


    // ----------------------------------------------------------
    /**
     * Called when the view is asked to redraw its contents.
     *
     * @param canvas
     *            an object that represents the canvas to be drawn on
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        if (model == null)
        {
            return;
        }

        int width = Math.round(getWidth() / model.getSize());
        int height = Math.round(getHeight() / model.getSize());
        Paint paint1 = new Paint();
        paint1.setStyle(Style.FILL);
        for (int x = 0; x < model.getSize(); x++)
        {
            for (int y = 0; y < model.getSize(); y++)
            {
                paint1.setColor(model.getCell(x, y).getColor());

                canvas.drawRect(x * width, y * height, (x + 1) * width, (y + 1)
                    * height, paint1);

            }

        }
    }


    // ----------------------------------------------------------
    /**
     * An observer that listens for changes made to the ColorSpill. This is a
     * nested class inside the view so that it can still access methods that
     * belong to the surrounding view.
     */
    private class ColorSpillObserver
        implements Observer
    {
        // ~ Methods ...........................................................

        // ----------------------------------------------------------
        /**
         * Called when the ColorSpill is changed (for example, when the user
         * drags a finger around the view).
         *
         * @param observable
         *            the Observable object that was changed
         * @param data
         *            extra data about the notification; unused here
         */
        public void update(Observable observable, Object data)
        {
            // The invalidate() method is used to force a view to be repainted
            // at the earliest opportunity (which in most cases is essentially
            // immediately, but may not always be). Note that this is a method
            // on the View class, not the Observer.

            invalidate();
        }
    }
}
