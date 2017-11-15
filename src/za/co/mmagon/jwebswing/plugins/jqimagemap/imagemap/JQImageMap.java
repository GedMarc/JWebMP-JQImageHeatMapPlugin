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
import za.co.mmagon.jwebswing.base.ComponentHierarchyBase;
import za.co.mmagon.jwebswing.base.html.*;
import za.co.mmagon.jwebswing.base.html.attributes.AreaAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.GlobalAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.ImageAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.ImageMapAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.children.BodyChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapFeatures;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;
import za.co.mmagon.jwebswing.generics.Direction;
import za.co.mmagon.jwebswing.htmlbuilder.css.displays.DisplayCSS;
import za.co.mmagon.jwebswing.htmlbuilder.css.displays.Positions;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.ComponentInformation;
import za.co.mmagon.jwebswing.plugins.jqgradientlinear.JQGradientsLinearFeature;
import za.co.mmagon.jwebswing.utilities.StaticStrings;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;

import static za.co.mmagon.jwebswing.utilities.StaticStrings.STRING_COMMNA;

/**
 * The image map component
 *
 * @param <J>
 *
 * @author mmagon
 * @version 0.1
 * @since 2013/01/14
 */
@DisplayCSS(Position = Positions.Relative)
@ComponentInformation(name = "An image map that can be built for a url",
		description = "An image map with selectable colour coded areas that can be determined from a series of values.",
		url = "https://sourceforge.net/p/jwebswing/jquery-image-heatmap/ci/master/tree/")
public class JQImageMap<J extends JQImageMap>
		extends Component<ImageMapChildren, ImageMapAttributes, ImageMapFeatures, GlobalEvents, JQImageMap<J>>
		implements BodyChildren, IJQImageMap
{

	private static final long serialVersionUID = 1L;
	/**
	 * The default interactive feature
	 */
	private final JQImageHeatMapFeature heatMapFeature = new JQImageHeatMapFeature(this, 1, 1000000);
	/**
	 * The labels div
	 */
	private Div labelsDiv;
	/**
	 * The legend div
	 */
	private Div legendDiv;
	/**
	 * The map
	 */
	private Map map;
	/**
	 * The image
	 */
	private Image image;
	/**
	 * The image x size
	 */
	private int imageXSize;
	/**
	 * The image y size
	 */
	private int imageYSize;
	/**
	 * The display x size
	 */
	private int displayXSize;
	/**
	 * The display y size
	 */
	private int displayYSize;
	/**
	 * The label head div
	 */
	private Div labelHeadDiv;
	/**
	 * The default interactive feature
	 */
	private JQMapInteractiveFeature defaultProperties = new JQMapInteractiveFeature(this);
	/**
	 * The gradient feature
	 */
	private JQGradientsLinearFeature gradientFeature;
	/**
	 * The feature that will display the gradient legend
	 */
	private JQMapLegendFeature legendFeature = new JQMapLegendFeature(gradientFeature);

	/**
	 * The map URL
	 */
	private String mapImageUrl;
	/**
	 * Sets whether the image map should apply a heat map This attaches a heat map feature
	 */
	private boolean heatmap;
	/**
	 * Shows whether the map is interactive Allows for highlighting
	 */
	private boolean interactive;
	/**
	 * Shows whether the map is labeled This puts a div at the Center of each polygon
	 */
	private boolean labeled;
	/**
	 * Sets whether to display a legend or not
	 */
	private boolean legend;

	private boolean valueDisplayed;

	private boolean ratioConfigured = false;

	/**
	 * Constructs a new Image Map
	 *
	 * @param mapImageUrl
	 */
	public JQImageMap(String mapImageUrl)
	{
		this(mapImageUrl, false, false, false);
		this.labelHeadDiv = new Div();
	}

	/**
	 * Constructs an ImageMap from the given parameters with the testing sizes
	 *
	 * @param mapImageUrl
	 * @param heatmap
	 * @param interactive
	 * @param labeled
	 */
	public JQImageMap(String mapImageUrl, boolean heatmap, boolean interactive, boolean labeled)
	{
		this(mapImageUrl, heatmap, interactive, labeled, 0, 0, 0, 0);
		this.labelHeadDiv = new Div();
	}

	/**
	 * @param mapImageUrl
	 * @param heatmap
	 * @param interactive
	 * @param labeled
	 * @param imageXSize
	 * 		The original Image size
	 * @param imageYSize
	 * 		The original Image size
	 * @param displayXSize
	 * 		The new image x size
	 * @param displayYSize
	 * 		the new image y size
	 */
	@SuppressWarnings("unchecked")
	public JQImageMap(String mapImageUrl, boolean heatmap, boolean interactive, boolean labeled, int imageXSize, int imageYSize, int displayXSize, int displayYSize)
	{
		super("div", ComponentTypes.Div, false);
		this.labelHeadDiv = new Div();
		this.imageXSize = imageXSize;
		this.imageYSize = imageYSize;
		this.displayYSize = displayYSize;
		this.displayXSize = displayXSize;
		this.mapImageUrl = mapImageUrl;
		this.heatmap = heatmap;
		this.interactive = interactive;
		this.labeled = labeled;

		this.map = new Map();
		this.image = new Image(this.mapImageUrl);

		defaultProperties.setDefaultProperties(true);
		image.addAttribute(ImageAttributes.UseMap, StaticStrings.STRING_HASH + this.map.getID());
		add(image);
		add(map);
	}

	/**
	 * Adds a specified area to the image map
	 *
	 * @param areaName
	 * 		The name of the area
	 * @param polygonCoordinates
	 * 		The co-ordinates for the area
	 *
	 * @return True or false if added public Area addAreaToMap(String areaName, String polygonCoordinates) { Area a = new Area(ImageMapAreaShapes.Poly, polygonCoordinates);
	 * 		a.addAttribute(GlobalAttributes.Name, areaName); this.map.add(a); return a; }
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Area addAreaToMap(String areaName, String polygonCoordinates)
	{
		HighlightedArea a = new HighlightedArea(ImageMapAreaShapes.Poly, polygonCoordinates);
		a.addAttribute(GlobalAttributes.Name, areaName);
		this.map.add(a);
		return a;
	}

	/**
	 * Adds a specified area to the image map
	 *
	 * @param area
	 * 		The area to add
	 *
	 * @return Always True
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean addAreaToMap(Area area)
	{
		this.map.add(area);
		return true;
	}

	/**
	 * Adds an area to the component
	 */
	@Override
	public Area add(Area area)
	{
		addAreaToMap(area);
		return area;
	}

	/**
	 * Resizes accordingly
	 */
	@SuppressWarnings("unchecked")
	public void renderResize()
	{
		if (displayXSize != 0)
		{
			image.addAttribute(ImageAttributes.Width, displayXSize + "px");
			image.addAttribute(ImageAttributes.Height, displayYSize + "px");

			int ratioXDifference = (int) (new Double(displayXSize) / new Double(imageXSize));
			int ratioYDifference = (int) (new Double(displayYSize) / new Double(imageYSize));

			int[] xArray;
			int[] yArray;
			if (!(ratioXDifference == 1 && ratioYDifference == 1) && !ratioConfigured)
			{
				ratioConfigured = true;
				for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext(); )
				{
					HighlightedArea area = (HighlightedArea) it.next();
					int[][] allPoints = area.getCoordinatesArray();
					xArray = new int[allPoints.length];
					yArray = new int[allPoints.length];
					int xTotal = 0;
					int yTotal = 0;
					for (int i = 0; i < allPoints.length; i++)
					{
						int[] is = allPoints[i];
						xArray[i] = (is[0] * ratioXDifference);
						xTotal += xArray[i];
						yArray[i] = (is[1] * ratioYDifference);
						yTotal += yArray[i];
					}

					StringBuilder coords = new StringBuilder("");
					for (int i = 0; i < xArray.length; i++)
					{
						int j = xArray[i];
						int k = yArray[i];
						coords.append(j + STRING_COMMNA + k + STRING_COMMNA);
					}
					coords = coords.deleteCharAt(coords.length() - 1);
					area.setCoordinates(coords.toString());
				}
			}
		}
	}

	/**
	 * Returns the gradient feature or a new one
	 *
	 * @return
	 */
	@Override
	public JQGradientsLinearFeature getGradientFeature()
	{
		if (gradientFeature == null)
		{
			gradientFeature = new JQGradientsLinearFeature(this);
			gradientFeature.getOptions().setDirection(Direction.Vertical);
			gradientFeature.getOptions().setToColour(getHeatMapFeature().getColourMax());
			gradientFeature.getOptions().setFromColour(getHeatMapFeature().getColourMin());
		}
		return gradientFeature;
	}

	@SuppressWarnings("unchecked")
	private void processHeatmap()
	{
		addFeature(defaultProperties);
		addFeature(heatMapFeature);
		if (isLegend())
		{

			getGradientFeature().getOptions().setFromColour(this.heatMapFeature.getColourMin());
			getGradientFeature().getOptions().setToColour(this.heatMapFeature.getColourMax());
			getGradientFeature().getOptions().setDirection(Direction.Vertical);
			addFeature(legendFeature);
			addFeature(gradientFeature);
			if (!getChildren().contains(legendFeature.getLayoutDiv()))
			{
				getChildren().add(legendFeature.getLayoutDiv());
			}
		}

		double totalValue = 0.0;
		ArrayList<Double> values = new ArrayList();
		for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext(); )
		{
			HighlightedArea area = (HighlightedArea) it.next();
			totalValue += area.getValue();
			values.add(area.getValue());
		}
		heatMapFeature.setValues(values);
		for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext(); )
		{
			HighlightedArea area = (HighlightedArea) it.next();
			area.getInteractiveProperties().addProperty(InteractiveFeatureProperties.overlayColorPermanent, heatMapFeature.getColourForValue(area.getValue()));
		}
	}

	@SuppressWarnings("unchecked")
	private void processLabels()
	{
		ArrayList<ComponentHierarchyBase> alreadyAdded = new ArrayList();
		for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext(); )
		{
			HighlightedArea area = (HighlightedArea) it.next();
			if (alreadyAdded.contains(area))
			{
				continue;
			}
			alreadyAdded.add(area);
			Span areaLableDiv = new Span();
			areaLableDiv.setText("");
			areaLableDiv.addAttribute(GlobalAttributes.Style, "color:#000000 !important");
			if (isLabeled())
			{
				areaLableDiv.setText(area.getAttribute(GlobalAttributes.Name));
			}
			if (isValueDisplayed())
			{
				areaLableDiv.setText(areaLableDiv.getText(0) + "<br>" + area.getPrettyValue());
			}


			areaLableDiv.addAttribute(GlobalAttributes.Style, "position:absolute");
			areaLableDiv.addFeature(new Binder(areaLableDiv, area));
			area.add(areaLableDiv);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void preConfigure()
	{
		if (isInteractive())
		{
			addFeature(defaultProperties);
		}

		if (isHeatmap())
		{
			processHeatmap();
		}

		if ((isLabeled() || isValueDisplayed() && (!(getChildren().contains(labelHeadDiv)))))
		{
			processLabels();
		}

		for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext(); )
		{
			HighlightedArea area = (HighlightedArea) it.next();
			area.addAttribute(AreaAttributes.Data_MapHilight, "{" + area.getInteractiveProperties().getProperties(true) + "}");
		}
		super.preConfigure();
	}

	/**
	 * Returns the default properties
	 *
	 * @return
	 */
	@Override
	@Nullable
	public JQMapInteractiveFeature getDefaultProperties()
	{
		return defaultProperties;
	}

	/**
	 * Sets the default properties
	 *
	 * @param defaultProperties
	 */
	@Override
	public void setDefaultProperties(JQMapInteractiveFeature defaultProperties)
	{
		this.defaultProperties = defaultProperties;
	}

	/**
	 * Return the current map object
	 *
	 * @return
	 */
	@Override
	@NotNull
	public Map getMap()
	{
		return map;
	}

	/**
	 * Sets the current map object
	 *
	 * @param map
	 */
	@Override
	public void setMap(Map map)
	{
		this.map = map;
	}

	/**
	 * Returns the associated image
	 *
	 * @return
	 */
	@Override
	public Image getImage()
	{
		return image;
	}

	/**
	 * Sets the associated image
	 *
	 * @param image
	 */
	@Override
	public void setImage(Image image)
	{
		this.image = image;
	}

	/**
	 * Returns the Map Image URL
	 *
	 * @return
	 */
	@Override
	public String getMapImageUrl()
	{
		return mapImageUrl;
	}

	/**
	 * Sets the map image url
	 *
	 * @param mapImageUrl
	 */
	@Override
	public void setMapImageUrl(String mapImageUrl)
	{
		this.mapImageUrl = mapImageUrl;
	}

	/**
	 * If this map is a heat map
	 *
	 * @return
	 */
	@Override
	public boolean isHeatmap()
	{
		return heatmap;
	}

	/**
	 * If this map is a heatmap
	 *
	 * @param heatmap
	 */
	@Override
	public void setHeatmap(boolean heatmap)
	{
		this.heatmap = heatmap;
	}

	/**
	 * If this map is interactive
	 *
	 * @return
	 */
	@Override
	public boolean isInteractive()
	{
		return interactive;
	}

	/**
	 * If this map is interactive
	 *
	 * @param interactive
	 */
	@Override
	public void setInteractive(boolean interactive)
	{
		this.interactive = interactive;
		if (isInteractive())
		{
			addFeature(defaultProperties);
		}
		else
		{
			removeFeature(defaultProperties);
		}
	}

	/**
	 * If this map has a legend
	 *
	 * @return
	 */
	@Override
	public boolean isLegend()
	{
		return legend;
	}

	/**
	 * If this map has a legend
	 *
	 * @param legend
	 */
	@Override
	public void setLegend(boolean legend)
	{
		this.legend = legend;
	}

	/**
	 * Image x size
	 *
	 * @return
	 */
	@Override
	public int getImageXSize()
	{
		return imageXSize;
	}

	/**
	 * Image x size
	 *
	 * @param imageXSize
	 */
	@Override
	public void setImageXSize(int imageXSize)
	{
		this.imageXSize = imageXSize;
	}

	/**
	 * Image Y size
	 *
	 * @return
	 */
	@Override
	public int getImageYSize()
	{
		return imageYSize;
	}

	/**
	 * Set image y size
	 *
	 * @param imageYSize
	 */
	@Override
	public void setImageYSize(int imageYSize)
	{
		this.imageYSize = imageYSize;
	}

	/**
	 * Is value displayed
	 *
	 * @return
	 */
	@Override
	public boolean isValueDisplayed()
	{
		return valueDisplayed;
	}

	/**
	 * Is value displayed
	 *
	 * @param valueDisplayed
	 */
	@Override
	public void setValueDisplayed(boolean valueDisplayed)
	{
		this.valueDisplayed = valueDisplayed;
	}

	/**
	 * getDisplay X Size
	 *
	 * @return
	 */
	@Override
	public int getDisplayXSize()
	{
		return displayXSize;
	}

	/**
	 * getDisplay X size
	 *
	 * @param displayXSize
	 */
	@Override
	public void setDisplayXSize(int displayXSize)
	{
		this.displayXSize = displayXSize;
	}

	/**
	 * getDisplay Y size
	 *
	 * @return
	 */
	@Override
	public int getDisplayYSize()
	{
		return displayYSize;
	}

	/**
	 * getDisplay Y size
	 *
	 * @param displayYSize
	 */
	@Override
	public void setDisplayYSize(int displayYSize)
	{
		this.displayYSize = displayYSize;
	}

	/**
	 * Set is labeled
	 *
	 * @return
	 */
	@Override
	public boolean isLabeled()
	{
		return labeled;
	}

	/**
	 * Set is labeled
	 *
	 * @param labeled
	 */
	@Override
	public void setLabeled(boolean labeled)
	{
		this.labeled = labeled;
	}

	/**
	 * Return the heat map options
	 *
	 * @return
	 */
	@Override
	public JQImageHeatMapFeature getHeatMapFeature()
	{
		return heatMapFeature;
	}

	/**
	 * Gets the actual labels
	 *
	 * @return
	 */
	@Override
	public Div getLabelsDiv()
	{
		return labelsDiv;
	}

	/**
	 * Sets the label div
	 *
	 * @param labelsDiv
	 */
	@Override
	public void setLabelsDiv(Div labelsDiv)
	{
		this.labelsDiv = labelsDiv;
	}

	/**
	 * Gets the legend div
	 *
	 * @return
	 */
	@Override
	public Div getLegendDiv()
	{
		return legendDiv;
	}

	/**
	 * Sets the legend div
	 *
	 * @param legendDiv
	 */
	@Override
	public void setLegendDiv(Div legendDiv)
	{
		this.legendDiv = legendDiv;
	}

	/**
	 * Gets the label head div
	 *
	 * @return
	 */
	@Override
	public Div getLabelHeadDiv()
	{
		return labelHeadDiv;
	}

	/**
	 * Sets the label div
	 *
	 * @param labelHeadDiv
	 */
	@Override
	public void setLabelHeadDiv(Div labelHeadDiv)
	{
		this.labelHeadDiv = labelHeadDiv;
	}

	/**
	 * Gets the legend feature
	 *
	 * @return
	 */
	@Override
	public JQMapLegendFeature getLegendFeature()
	{
		return legendFeature;
	}

	/**
	 * Sets the legend div
	 *
	 * @param legendFeature
	 */
	@Override
	public void setLegendFeature(JQMapLegendFeature legendFeature)
	{
		this.legendFeature = legendFeature;
	}

	/**
	 * If the ratio is currently configured for the screen
	 *
	 * @return
	 */
	@Override
	public boolean isRatioConfigured()
	{
		return ratioConfigured;
	}

	/**
	 * Neater version
	 *
	 * @return
	 */
	public IJQImageMap asMe()
	{
		return this;
	}

	class Binder extends Feature<JavaScriptPart, Binder>
	{

		private static final long serialVersionUID = 1L;

		Span label;
		Area area;

		public Binder(Span label, Area area)
		{
			super("Image Map Label Binder");
			this.label = label;
			this.area = area;
		}

		@Override
		public void assignFunctionsToComponent()
		{

			addQuery("$('#" + label.getID() + "').bind('click', function(event) {$('#" + area.getID() + "').click();});");
			addQuery("$('#" + label.getID() + "').bind('hover', function(event) {$('#" + area.getID() + "').hover();});");
			addQuery("$('#" + label.getID() + "').bind('mouseover', function(event) {$('#" + area.getID() + "').mouseover();});");
			addQuery("$('#" + label.getID() + "').bind('mouseout', function(event) {$('#" + area.getID() + "').mouseout();});");
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

			Binder binder = (Binder) o;

			if (!label.equals(binder.label))
			{
				return false;
			}
			return area.equals(binder.area);
		}

		@Override
		public int hashCode()
		{
			int result = super.hashCode();
			result = 31 * result + label.hashCode();
			result = 31 * result + area.hashCode();
			return result;
		}
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

		JQImageMap<?> that = (JQImageMap<?>) o;

		if (getImageXSize() != that.getImageXSize())
		{
			return false;
		}
		if (getImageYSize() != that.getImageYSize())
		{
			return false;
		}
		if (getDisplayXSize() != that.getDisplayXSize())
		{
			return false;
		}
		if (getDisplayYSize() != that.getDisplayYSize())
		{
			return false;
		}
		if (isHeatmap() != that.isHeatmap())
		{
			return false;
		}
		if (isInteractive() != that.isInteractive())
		{
			return false;
		}
		if (isLabeled() != that.isLabeled())
		{
			return false;
		}
		if (isLegend() != that.isLegend())
		{
			return false;
		}
		if (isValueDisplayed() != that.isValueDisplayed())
		{
			return false;
		}
		if (isRatioConfigured() != that.isRatioConfigured())
		{
			return false;
		}
		if (!getHeatMapFeature().equals(that.getHeatMapFeature()))
		{
			return false;
		}
		if (getLabelsDiv() != null ? !getLabelsDiv().equals(that.getLabelsDiv()) : that.getLabelsDiv() != null)
		{
			return false;
		}
		if (getLegendDiv() != null ? !getLegendDiv().equals(that.getLegendDiv()) : that.getLegendDiv() != null)
		{
			return false;
		}
		if (!getMap().equals(that.getMap()))
		{
			return false;
		}
		if (!getImage().equals(that.getImage()))
		{
			return false;
		}
		if (getLabelHeadDiv() != null ? !getLabelHeadDiv().equals(that.getLabelHeadDiv()) : that.getLabelHeadDiv() != null)
		{
			return false;
		}
		if (getDefaultProperties() != null ? !getDefaultProperties().equals(that.getDefaultProperties()) : that.getDefaultProperties() != null)
		{
			return false;
		}
		if (getGradientFeature() != null ? !getGradientFeature().equals(that.getGradientFeature()) : that.getGradientFeature() != null)
		{
			return false;
		}
		if (getLegendFeature() != null ? !getLegendFeature().equals(that.getLegendFeature()) : that.getLegendFeature() != null)
		{
			return false;
		}
		return getMapImageUrl() != null ? getMapImageUrl().equals(that.getMapImageUrl()) : that.getMapImageUrl() == null;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + getHeatMapFeature().hashCode();
		result = 31 * result + (getLabelsDiv() != null ? getLabelsDiv().hashCode() : 0);
		result = 31 * result + (getLegendDiv() != null ? getLegendDiv().hashCode() : 0);
		result = 31 * result + getMap().hashCode();
		result = 31 * result + getImage().hashCode();
		result = 31 * result + getImageXSize();
		result = 31 * result + getImageYSize();
		result = 31 * result + getDisplayXSize();
		result = 31 * result + getDisplayYSize();
		result = 31 * result + (getLabelHeadDiv() != null ? getLabelHeadDiv().hashCode() : 0);
		result = 31 * result + (getDefaultProperties() != null ? getDefaultProperties().hashCode() : 0);
		result = 31 * result + (getGradientFeature() != null ? getGradientFeature().hashCode() : 0);
		result = 31 * result + (getLegendFeature() != null ? getLegendFeature().hashCode() : 0);
		result = 31 * result + (getMapImageUrl() != null ? getMapImageUrl().hashCode() : 0);
		result = 31 * result + (isHeatmap() ? 1 : 0);
		result = 31 * result + (isInteractive() ? 1 : 0);
		result = 31 * result + (isLabeled() ? 1 : 0);
		result = 31 * result + (isLegend() ? 1 : 0);
		result = 31 * result + (isValueDisplayed() ? 1 : 0);
		result = 31 * result + (isRatioConfigured() ? 1 : 0);
		return result;
	}
}
