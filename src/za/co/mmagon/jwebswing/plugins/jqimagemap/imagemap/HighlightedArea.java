/*
 * The MIT License
 *
 * Copyright 2017 GedMarc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package za.co.mmagon.jwebswing.plugins.jqimagemap.imagemap;

import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.mmagon.jwebswing.base.html.Area;
import za.co.mmagon.jwebswing.base.html.attributes.AreaAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.GlobalAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapChildren;
import za.co.mmagon.logger.LogFactory;

/**
 *
 * @author GedMarc
 * @since 15 Feb 2017
 *
 */
public class HighlightedArea extends Area implements ImageMapChildren
{

    private static final Logger LOG = LogFactory.getInstance().getLogger("HighlightedArea");
    private static final long serialVersionUID = 1L;
    private JQImageMap.ImageMapAreaShapes areaShape;
    private int[][] coordinatesArray;
    private String coordinates;
    private String prettyValue = "0.0";
    private double value = 0.0;
    private JQMapInteractiveFeature interactiveProperties;
    private JQImageMap drillMap;
    private DecimalFormat decimalFormat;

    /**
     * Construct a new area tag with the given co-ordinates and area shape. A name is required
     *
     * @param areaShape The area shape
     * @param coordinates The co-ordinates of this area
     * @param name The name of this area
     */
    public HighlightedArea(JQImageMap.ImageMapAreaShapes areaShape, String coordinates, String name)
    {
        super();
        this.areaShape = areaShape;
        this.coordinatesArray = getArrayFromStringCoordinates(coordinates);
        this.coordinates = coordinates;
        addAttribute(GlobalAttributes.Name, name);
        addAttribute(AreaAttributes.Coords, this.coordinates);
        addAttribute(AreaAttributes.Shape, areaShape.name().toLowerCase());
        setInlineClosingTag(true);
    }

    /**
     * Construct a new area tag with the given co-ordinates and area shape. The name "MapArea" is assigned as a default for image maps
     *
     * @param areaShape The area shape
     * @param coordinates The co-ordinates of this area
     */
    public HighlightedArea(JQImageMap.ImageMapAreaShapes areaShape, String coordinates)
    {
        this(areaShape, coordinates, "MapArea");
    }

    /**
     * If a drill through map is specified, returns this map
     *
     * @return
     */
    public JQImageMap getDrillMap()
    {
        return drillMap;
    }

    /**
     * Sets the drill map drill through
     *
     * @param drillMap
     */
    public void setDrillMap(JQImageMap drillMap)
    {
        this.drillMap = drillMap;
    }

    /**
     * Pre-configures this component with all the required attributes and features
     */
    @Override
    public void preConfigure()
    {
        if (coordinates != null)
        {
            addAttribute(AreaAttributes.Coords, this.coordinates);
        }
        if (areaShape != null)
        {
            addAttribute(AreaAttributes.Shape, areaShape.name().toLowerCase());
        }
        super.preConfigure();
    }

    /**
     * Returns the interactive properties of this feature
     *
     * @return
     */
    public JQMapInteractiveFeature getInteractiveProperties()
    {
        if (interactiveProperties == null)
        {
            interactiveProperties = new JQMapInteractiveFeature(null);
        }
        return interactiveProperties;
    }

    /**
     * Splits the co-ordinates into an array
     *
     * @param coordinates The Co-ordinate string
     *
     * @return The integer array of all the points
     */
    public static synchronized int[][] getArrayFromStringCoordinates(String coordinates)
    {
        StringTokenizer st = new StringTokenizer(coordinates, ",");
        int coordinateCount = st.countTokens() / 2;
        int[][] coords = new int[coordinateCount][2];
        int currentCoord = 0;
        while (st.hasMoreElements())
        {
            try
            {
                String coord1 = (String) st.nextElement();
                String coord2 = (String) st.nextElement();
                coords[currentCoord][0] = Integer.parseInt(coord1);
                coords[currentCoord][1] = Integer.parseInt(coord2);
                currentCoord++;
            }
            catch (NullPointerException npe)
            {
                LOG.log(Level.WARNING, "Area format incorrect", npe);
            }
        }
        return coords;
    }

    /**
     * Retrieves the Co-Ordinates array
     *
     * @return int[2]
     */
    public int[][] getCoordinatesArray()
    {
        return getArrayFromStringCoordinates(this.coordinates);
    }

    /**
     * Return the string form of the co-ordinates
     *
     * @return
     */
    public String getCoordinates()
    {
        return coordinates;
    }

    /**
     * Set the Co-ordinates
     *
     * @param coordinates
     */
    public void setCoordinates(String coordinates)
    {
        this.coordinates = coordinates;
    }

    /**
     * Return the given area shape
     *
     * @return
     */
    public JQImageMap.ImageMapAreaShapes getAreaShape()
    {
        return areaShape;
    }

    /**
     * sets the given area shape
     *
     * @param areaShape
     */
    public void setAreaShape(JQImageMap.ImageMapAreaShapes areaShape)
    {
        this.areaShape = areaShape;
    }

    /**
     * Returns a value for this value
     *
     * @return
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Sets the value associated with this area
     *
     * @param value
     */
    public void setValue(double value)
    {
        this.value = value;
    }

    /**
     * Sets the value assigned to this area
     *
     * @param value
     */
    public void setValue(String value)
    {
        this.prettyValue = value;
    }

    /**
     * Returns a pretty print version of the given are
     *
     * @return
     */
    public String getPrettyValue()
    {
        return prettyValue;
    }

    /**
     * Set the pretty value of the figure
     *
     * @param prettyValue
     */
    public void setPrettyValue(String prettyValue)
    {
        this.prettyValue = prettyValue;
    }

    /**
     * Sets the Decimal Format of this area for pretty print
     *
     * @return
     */
    public DecimalFormat getDecimalFormat()
    {
        return decimalFormat;
    }

    /**
     * Sets the Decimal Format of this area for pretty print
     *
     * @param decimalFormat
     */
    public void setDecimalFormat(DecimalFormat decimalFormat)
    {
        this.decimalFormat = decimalFormat;
    }
}
