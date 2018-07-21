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
package com.jwebmp.plugins.imagemap;

import com.jwebmp.core.Feature;
import com.jwebmp.core.base.html.interfaces.children.ImageMapFeatures;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.core.utilities.ColourUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @since @version @author MMagon
 * 		<p>
 * 		<p>
 */
public class JQImageHeatMapFeature
		extends Feature<JavaScriptPart, JQImageHeatMapFeature>
		implements ImageMapFeatures
{

	private static final long serialVersionUID = 1L;
	/**
	 * The image map
	 */
	private final JQImageMap imageMap;
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
	private List<Double> allValues = new ArrayList<>();

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
		setMinimumValue(minimumValue);
		setMaximumValue(maximumValue);
	}

	/**
	 * Constructs a new image heat map java script portion
	 *
	 * @param imageMap
	 * @param allValues
	 */
	public JQImageHeatMapFeature(JQImageMap imageMap, List<Double> allValues)
	{
		super("JWHeatMapFeature");
		this.imageMap = imageMap;
		setComponent(imageMap);
		this.allValues = allValues;
		minimumValue = getMinNumber(allValues);
		maximumValue = getMaxNumber(allValues);
	}

	/**
	 * Returns the Minimum Number for the Provinces
	 *
	 * @return
	 */
	private double getMinNumber(List<Double> provinceValues)
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
	private double getMaxNumber(List<Double> provinceValues)
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
	 * Sets all the values
	 *
	 * @param allValues
	 */
	public void setValues(List<Double> allValues)
	{
		minimumValue = getMinNumber(allValues);
		maximumValue = getMaxNumber(allValues);
		this.allValues = allValues;
	}

	/**
	 * Gets the colour for a value
	 *
	 * @param value
	 *
	 * @return
	 */
	public String getColourForValue(double value)
	{
		return ColourUtils.getColourBetweenColours(getMinimumValue(), getMaximumValue(), value, getColourMin(), getColourMax());
	}

	/**
	 * Returns the minimum value
	 *
	 * @return
	 */
	public double getMinimumValue()
	{
		minimumValue = getMinNumber(allValues);
		maximumValue = getMaxNumber(allValues);
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
		minimumValue = getMinNumber(allValues);
		maximumValue = getMaxNumber(allValues);
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

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		long temp;
		result = 31 * result + (getImageMap() != null ? getImageMap().hashCode() : 0);
		temp = Double.doubleToLongBits(getMinimumValue());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getMaximumValue());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + getColourMin().hashCode();
		result = 31 * result + getColourMax().hashCode();
		result = 31 * result + getAllValues().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}

		JQImageHeatMapFeature that = (JQImageHeatMapFeature) o;

		if (Double.compare(that.getMinimumValue(), getMinimumValue()) != 0)
		{
			return false;
		}
		if (Double.compare(that.getMaximumValue(), getMaximumValue()) != 0)
		{
			return false;
		}
		if (getImageMap() != null ? !getImageMap().equals(that.getImageMap()) : that.getImageMap() != null)
		{
			return false;
		}
		if (!getColourMin().equals(that.getColourMin()))
		{
			return false;
		}
		return getColourMax().equals(that.getColourMax()) && getAllValues().equals(that.getAllValues());
	}

	@Override
	protected void assignFunctionsToComponent()
	{
		//No queries needed
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
	public List<Double> getAllValues()
	{
		return allValues;
	}
}
