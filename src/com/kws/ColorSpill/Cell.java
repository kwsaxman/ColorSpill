package com.kws.ColorSpill;

// -------------------------------------------------------------------------
/**
 * Color Spill Cell Hold the color of the cell and its position.
 *
 *
 * @author Ryan Harrigan (homerunh)
 * @author Richard Le (rle9)
 * @author Kevin Saxman (kwsaxma)
 * @version Apr 29, 2012
 */

public class Cell
{
    private int     color;
    private int     xPos;
    private int     yPos;


    // ----------------------------------------------------------
    /**
     * Create a new ColorSpillCell object.
     *
     * @param color
     *            the color of the cell.
     * @param xPos
     *            the x index.
     * @param yPos
     *            the y index.
     */
    public Cell(int color, int xPos, int yPos)
    {
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;

    }


    // ----------------------------------------------------------
    /**
     * Get the color of the cell.
     *
     * @return the color.
     */
    public int getColor()
    {
        return color;
    }


    // ----------------------------------------------------------
    /**
     * Set the color of the cell.
     *
     * @param color
     *            the new color.
     */
    public void setColor(int color)
    {
        this.color = color;
    }


    // ----------------------------------------------------------
    /**
     * Get the x index of the cell.
     *
     * @return the x index.
     */
    public int getXPos()
    {
        return xPos;
    }


    // ----------------------------------------------------------
    /**
     * Get the y index of the cell.
     *
     * @return the y index.
     */
    public int getYPos()
    {
        return yPos;
    }

}
