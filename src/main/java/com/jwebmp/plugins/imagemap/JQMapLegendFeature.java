/*
 * Copyright (C) 2017 GedMarc
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

import com.jwebmp.core.Component;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.attributes.GlobalAttributes;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.jqgradientlinear.JQGradientsLinearFeature;

/**
 * Adds a legend below a heat map
 *
 * @author MMagon
 * @version 1.0
 * @since 2013/11/27 03:06
 */
public class JQMapLegendFeature
		extends Feature<JQMapLegendFeature, JavaScriptPart, JQMapLegendFeature>
{


	private static final String LEGEND_DEFAULT_STYLE = "width:100%; height: 20px; margin-top: 10px; margin-left: 10px; margin-right:10px";

	private JQGradientsLinearFeature gradientsFeature;
	private Div layoutDiv = new Div();

	private Div firstHalf = new Div();
	private Div secondHalf = new Div();

	public JQMapLegendFeature(JQGradientsLinearFeature gradientFeature)
	{
		super("JWMapLegendFeature");
		setComponent(getLayoutDiv());
		layoutDiv.addAttribute(GlobalAttributes.Style, JQMapLegendFeature.LEGEND_DEFAULT_STYLE);
		firstHalf.addAttribute(GlobalAttributes.Style, JQMapLegendFeature.LEGEND_DEFAULT_STYLE);
		secondHalf.addAttribute(GlobalAttributes.Style, JQMapLegendFeature.LEGEND_DEFAULT_STYLE);
		gradientsFeature = gradientFeature;

		getGradientsFeature().setComponent(getLayoutDiv());
	}

	public Div getLayoutDiv()
	{
		return layoutDiv;
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

	public void setLayoutDiv(Div layoutDiv)
	{
		this.layoutDiv = layoutDiv;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	@Override
	protected void assignFunctionsToComponent()
	{
		//No queries needed
	}
}
