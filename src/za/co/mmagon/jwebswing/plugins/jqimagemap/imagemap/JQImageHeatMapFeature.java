/* 
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.co.mmagon.jwebswing.plugins.jqimagemap.imagemap;

import java.util.ArrayList;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapFeatures;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.utilities.ColourUtils;

/**
 * @since @version @author MMagon
 *
 * <p>
 */
public class JQImageHeatMapFeature extends Feature<JavaScriptPart, Feature> implements ImageMapFeatures
{

    private static final long serialVersionUID = 1L;
    /**
     * The image map
     */
    private final JQImageMap imageMap;
    //private JQLayoutFeature imageMapFeature;
    /**
     * The minimum value
     */
    private double minimumValue;
    /**
     * The maximum value
     */
    private double maximumValue;
    /**
     * The colour for the minimum
     */
    private String colourMin = "ffffff";
    /**
     * The colour for the maximum
     */
    private String colourMax = "000000";
    /**
     * Array list of all the values
     */
    private ArrayList<Double> allValues = new ArrayList<>();

    /**
     * Constructs a new image heat map java script portion
     *
     * @param imageMap
     * @param minimumValue
     * @param maximumValue
     */
    public JQImageHeatMapFeature(JQImageMap imageMap, double minimumValue, double maximumValue)
    {
        super("JWHeatMapFeature");
        this.imageMap = imageMap;
        setComponent(imageMap);
    }

    /**
     * Constructs a new image heat map java script portion
     *
     * @param imageMap
     * @param allValues
     */
    public JQImageHeatMapFeature(JQImageMap imageMap, ArrayList<Double> allValues)
    {
        super("JWHeatMapFeature");
        this.imageMap = imageMap;
        setComponent(imageMap);
        this.allValues = allValues;
        this.minimumValue = getMinNumber(allValues);
        this.maximumValue = getMaxNumber(allValues);
    }

    /**
     * Returns the minimum value
     *
     * @return
     */
    public double getMinimumValue()
    {
        this.minimumValue = getMinNumber(allValues);
        this.maximumValue = getMaxNumber(allValues);
        return minimumValue;
    }

    /**
     * Sets the minimum value
     *
     * @param minimumValue
     */
    public void setMinimumValue(double minimumValue)
    {
        this.minimumValue = minimumValue;
    }

    /**
     * Returns the max value
     *
     * @return
     */
    public double getMaximumValue()
    {
        this.minimumValue = getMinNumber(allValues);
        this.maximumValue = getMaxNumber(allValues);
        return maximumValue;
    }

    /**
     * Sets the max value
     *
     * @param maximumValue
     */
    public void setMaximumValue(double maximumValue)
    {
        this.maximumValue = maximumValue;
    }

    /**
     * Sets all the values
     *
     * @param allValues
     */
    public void setValues(ArrayList<Double> allValues)
    {
        this.minimumValue = getMinNumber(allValues);
        this.maximumValue = getMaxNumber(allValues);
        this.allValues = allValues;
    }

    /**
     * Gets the colour for a value
     *
     * @param value
     * @return
     */
    public String getColourForValue(double value)
    {
        return ColourUtils.getColourBetweenColours(getMinimumValue(), getMaximumValue(), value, getColourMin(), getColourMax());
    }

    /**
     * Gets the minimum colour
     *
     * @return
     */
    public String getColourMin()
    {
        return colourMin;
    }

    /**
     * Sets the minimum colour
     *
     * @param colourMin
     */
    public void setColourMin(String colourMin)
    {
        this.colourMin = colourMin;
    }

    /**
     * Returns the maximum colour
     *
     * @return
     */
    public String getColourMax()
    {
        return colourMax;
    }

    /**
     * Sets the maximum colour
     *
     * @param colourMax
     */
    public void setColourMax(String colourMax)
    {
        this.colourMax = colourMax;
    }

    /**
     * Returns the Minimum Number for the Provinces
     *
     * @return
     */
    private double getMinNumber(ArrayList<Double> provinceValues)
    {
        Double min = 999999999.0;

        for (Double type : provinceValues)
        {
            if (type < min)
            {
                min = type;
            }
        }
        return min;
    }

    /**
     * Returns the maximum number for all the provinces
     *
     * @return
     */
    private double getMaxNumber(ArrayList<Double> provinceValues)
    {
        Double max = 0.0;

        for (Double type : provinceValues)
        {
            if (type > max)
            {
                max = type;
            }
        }
        return max;
    }

    /**
     * Returns the middle number of the provinces or the custom middle number value entered 0.0 if the middle number is not a number
     *
     * @return
     */
    public double getMiddleNumber()
    {
        double min = getMinimumValue();
        double max = getMaximumValue();
        return (min + max) / 2;
    }

    /**
     * Returns the image map
     *
     * @return
     */
    public JQImageMap getImageMap()
    {
        return imageMap;
    }

    /**
     * Returns all the currently assigned values
     *
     * @return
     */
    public ArrayList<Double> getAllValues()
    {
        return allValues;
    }

}
