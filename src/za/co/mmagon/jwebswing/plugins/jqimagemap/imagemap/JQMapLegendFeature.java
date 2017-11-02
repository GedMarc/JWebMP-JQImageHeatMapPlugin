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

import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.Div;
import za.co.mmagon.jwebswing.base.html.attributes.GlobalAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapFeatures;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.jqgradientlinear.JQGradientsLinearFeature;

/**
 * Adds a legend below a heat map
 *
 * @author MMagon
 * @version 1.0
 * @since 2013/11/27 03:06
 */
public class JQMapLegendFeature extends Feature<JavaScriptPart, JQMapLegendFeature> implements ImageMapFeatures
{

	private static final long serialVersionUID = 1L;
	private static final String LEGEND_DEFAULT_STYLE = "width:100%; height: 20px; margin-top: 10px; margin-left: 10px; margin-right:10px";

	private JQGradientsLinearFeature gradientsFeature;
	private Div layoutDiv = new Div();

	private Div firstHalf = new Div();
	private Div secondHalf = new Div();

	public JQMapLegendFeature(JQGradientsLinearFeature gradientFeature)
	{
		super("JWMapLegendFeature");
		this.gradientsFeature = gradientFeature;
		setComponent(getLayoutDiv());
		layoutDiv.addAttribute(GlobalAttributes.Style, LEGEND_DEFAULT_STYLE);
		firstHalf.addAttribute(GlobalAttributes.Style, LEGEND_DEFAULT_STYLE);
		secondHalf.addAttribute(GlobalAttributes.Style, LEGEND_DEFAULT_STYLE);

		getGradientsFeature().setComponent(getLayoutDiv());
	}
	
	@Override
	protected void assignFunctionsToComponent()
	{
		//No queries needed
	}
	
	public JQGradientsLinearFeature getGradientsFeature()
	{
		if (gradientsFeature == null)
		{
			gradientsFeature = new JQGradientsLinearFeature((Component) getComponent());
		}
		return gradientsFeature;
	}

	public void setGradientsFeature(JQGradientsLinearFeature gradientsFeature)
	{
		this.gradientsFeature = gradientsFeature;
	}

	public Div getLayoutDiv()
	{
		return layoutDiv;
	}

	public void setLayoutDiv(Div layoutDiv)
	{
		this.layoutDiv = layoutDiv;
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

		JQMapLegendFeature that = (JQMapLegendFeature) o;

		if (!getGradientsFeature().equals(that.getGradientsFeature()))
		{
			return false;
		}
		if (!getLayoutDiv().equals(that.getLayoutDiv()))
		{
			return false;
		}
		if (!firstHalf.equals(that.firstHalf))
		{
			return false;
		}
		return secondHalf.equals(that.secondHalf);
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + getGradientsFeature().hashCode();
		result = 31 * result + getLayoutDiv().hashCode();
		result = 31 * result + firstHalf.hashCode();
		result = 31 * result + secondHalf.hashCode();
		return result;
	}
}
