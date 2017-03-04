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
import java.util.Iterator;
import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.ComponentHierarchyBase;
import za.co.mmagon.jwebswing.base.html.*;
import za.co.mmagon.jwebswing.base.html.attributes.*;
import za.co.mmagon.jwebswing.base.html.interfaces.children.*;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;
import za.co.mmagon.jwebswing.htmlbuilder.css.displays.DisplayCSS;
import za.co.mmagon.jwebswing.htmlbuilder.css.displays.Positions;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.ComponentInformation;
import za.co.mmagon.jwebswing.plugins.jqgradientlinear.JQGradientsLinearFeature;

/**
 * The image map component
 *
 * @author mmagon
 * @param <J>
 *
 * @since 2013/01/14
 * @version 0.1
 */
@DisplayCSS(Position = Positions.Relative)
@ComponentInformation(name = "An image map that can be built for a url",
        description = "An image map with selectable colour coded areas that can be determined from a series of values.",
        url = "https://sourceforge.net/p/jwebswing/jquery-image-heatmap/ci/master/tree/")
public class JQImageMap<J extends JQImageMap>
        extends Component<ImageMapChildren, ImageMapAttributes, ImageMapFeatures, GlobalEvents, Component>
        implements BodyChildren, IJQImageMap
{

    private static final long serialVersionUID = 1L;

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
     * The default interactive feature
     */
    private final JQImageHeatMapFeature heatMap = new JQImageHeatMapFeature(this, 1, 1000000);

    /**
     * The gradient feature
     */
    private JQGradientsLinearFeature gradientFeature;
    /**
     * The feature that will display the gradient legend
     */
    private JQMapLegendFeature legendFeature = new JQMapLegendFeature(this, gradientFeature);

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
     *
     * @param mapImageUrl
     * @param heatmap
     * @param interactive
     * @param labeled
     * @param imageXSize The original Image size
     * @param imageYSize The original Image size
     * @param displayXSize The new image x size
     * @param displayYSize the new image y size
     */
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
        image.addAttribute(ImageAttributes.UseMap, "#" + this.map.getID());
        add(image);
        add(map);
    }

    /**
     * Adds a specified area to the image map
     *
     * @param areaName The name of the area
     * @param polygonCoordinates The co-ordinates for the area
     *
     * @return True or false if added public Area addAreaToMap(String areaName, String polygonCoordinates) { Area a = new Area(ImageMapAreaShapes.Poly, polygonCoordinates);
     * a.addAttribute(GlobalAttributes.Name, areaName); this.map.add(a); return a; }
     */
    @Override
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
     * @param area The area to add
     *
     * @return Always True
     */
    @Override
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
     * TODO
     */
    private void renderResize()
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
                for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext();)
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
                        xArray[i] = (int) (is[0] * ratioXDifference);
                        xTotal += xArray[i];
                        yArray[i] = (int) (is[1] * ratioYDifference);
                        yTotal += yArray[i];
                    }

                    String coords = "";
                    for (int i = 0; i < xArray.length; i++)
                    {
                        int j = xArray[i];
                        int k = yArray[i];
                        coords += j + "," + k + ",";
                    }
                    coords = coords.substring(0, (coords.length() - 1));
                    area.setCoordinates(coords);
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
            gradientFeature.getOptions().setDirection("vertical");
            gradientFeature.getOptions().setToColour(getHeatMap().getColourMax());
            gradientFeature.getOptions().setFromColour(getHeatMap().getColourMin());
        }
        return gradientFeature;
    }

    @Override
    public void preConfigure()
    {
        if (isInteractive())
        {
            addFeature(defaultProperties);
        }

        if (isHeatmap())
        {
            addFeature(defaultProperties);
            addFeature(heatMap);
            if (isLegend())
            {

                getGradientFeature().getOptions().setFromColour(this.heatMap.getColourMin());
                getGradientFeature().getOptions().setToColour(this.heatMap.getColourMax());
                getGradientFeature().getOptions().setDirection("vertical");
                addFeature(legendFeature);
                addFeature(gradientFeature);
                if (!getChildren().contains(legendFeature.getLayoutDiv()))
                {
                    getChildren().add(getChildren().size(), legendFeature.getLayoutDiv());
                }
            }

            double totalValue = 0.0;
            ArrayList<Double> values = new ArrayList();
            for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext();)
            {
                HighlightedArea area = (HighlightedArea) it.next();
                totalValue += area.getValue();
                values.add(area.getValue());
            }
            heatMap.setValues(values);
            for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext();)
            {
                HighlightedArea area = (HighlightedArea) it.next();
                area.getInteractiveProperties().addProperty(InteractiveFeatureProperties.overlayColorPermanent, heatMap.getColourForValue(area.getValue()));
            }
        }

        if (isLabeled() || isValueDisplayed())
        {
            if (!(getChildren().contains(labelHeadDiv)))
            {
                ArrayList<ComponentHierarchyBase> alreadyAdded = new ArrayList();
                for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext();)
                {
                    HighlightedArea area = (HighlightedArea) it.next();
                    if (alreadyAdded.contains(area))
                    {
                        continue;
                    }
                    alreadyAdded.add(area);
                    Span areaLableDiv = new Span();
                    //areaLableDiv.getCss().getDisplay().setPosition(Positions.Absolute);
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
                    }

                    areaLableDiv.addAttribute(GlobalAttributes.Style, "position:absolute");
                    areaLableDiv.addFeature(new Binder(areaLableDiv, area));
                    //int[] Center = PolygonUtils.getCenterPointOfPolygon(area.getCoordinates());
                    //areaLableDiv.addAttribute(GlobalAttributes.Style, "position:absolute;top:" + Center[0] + ";Left:"+ Center[1]);
                    //areaLableDiv.addFeature(new JQUIPositionFeature(areaLableDiv, new Position(PositionLocationHorizontal.Center, PositionLocationVertical.center, PositionLocationHorizontal.Center, PositionLocationVertical.center, area)));
                    area.add(areaLableDiv);
                    //add(areaLableDiv);
                }
                //add(labelHeadDiv);
            }
        }

        for (Iterator<ComponentHierarchyBase> it = this.map.getChildren().iterator(); it.hasNext();)
        {
            HighlightedArea area = (HighlightedArea) it.next();
            area.addAttribute(AreaAttributes.Data_MapHilight, "{" + area.getInteractiveProperties().getProperties(true) + "}");
        }
        //setPosition(Positions.Absolute);
        super.preConfigure();
    }

    /**
     * Returns the default properties
     *
     * @return
     */
    @Override
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
    public JQImageHeatMapFeature getHeatMap()
    {
        return heatMap;
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

}
