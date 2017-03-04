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
 * @since 2013/11/27 03:06
 * @version 1.0
 * @author MMagon
 *
 *
 */
public class JQMapLegendFeature extends Feature<JavaScriptPart, JQMapLegendFeature> implements ImageMapFeatures
{

    private static final long serialVersionUID = 1L;

    private JQGradientsLinearFeature gradientsFeature;// = new JQGradientsLinearFeature();
    private Div layoutDiv = new Div();

    private Div firstHalf = new Div();
    private Div secondHalf = new Div();

    public JQMapLegendFeature(JQImageMap imageMap, JQGradientsLinearFeature gradientFeature)
    {
        super("JWMapLegendFeature");
        this.gradientsFeature = gradientFeature;
        setComponent(getLayoutDiv());
        layoutDiv.addAttribute(GlobalAttributes.Style, "width:100%; height: 20px; margin-top: 10px; margin-left: 10px; margin-right:10px");
        //layoutDiv.add(secondHalf);
        firstHalf.addAttribute(GlobalAttributes.Style, "width:100%; height: 20px; margin-top: 10px; margin-left: 10px; margin-right:10px");
        secondHalf.addAttribute(GlobalAttributes.Style, "width:100%; height: 20px; margin-top: 10px; margin-left: 10px; margin-right:10px");

        getGradientsFeature().setComponent(getLayoutDiv());
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

}
