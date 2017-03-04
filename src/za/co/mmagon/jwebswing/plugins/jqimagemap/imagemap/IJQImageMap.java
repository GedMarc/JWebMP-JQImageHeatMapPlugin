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

import za.co.mmagon.jwebswing.base.html.Area;
import za.co.mmagon.jwebswing.base.html.Div;
import za.co.mmagon.jwebswing.base.html.Image;
import za.co.mmagon.jwebswing.base.html.Map;
import za.co.mmagon.jwebswing.plugins.jqgradientlinear.JQGradientsLinearFeature;

/**
 *
 * @author GedMarc
 * @since 27 Feb 2017
 *
 */
public interface IJQImageMap
{

    /**
     * Adds an area to the component
     *
     * @param area
     * @return
     */
    Area add(Area area);

    /**
     * Adds a specified area to the image map
     *
     * @param areaName The name of the area
     * @param polygonCoordinates The co-ordinates for the area
     *
     * @return True or false if added public Area addAreaToMap(String areaName, String polygonCoordinates) { Area a = new Area(ImageMapAreaShapes.Poly, polygonCoordinates);
     * a.addAttribute(GlobalAttributes.Name, areaName); this.map.add(a); return a; }
     */
    Area addAreaToMap(String areaName, String polygonCoordinates);

    /**
     * Adds a specified area to the image map
     *
     * @param area The area to add
     *
     * @return Always True
     */
    boolean addAreaToMap(Area area);

    /**
     * Returns the default properties
     *
     * @return
     */
    JQMapInteractiveFeature getDefaultProperties();

    /**
     * getDisplay X Size
     *
     * @return
     */
    int getDisplayXSize();

    /**
     * getDisplay Y size
     *
     * @return
     */
    int getDisplayYSize();

    /**
     * Returns the gradient feature or a new one
     *
     * @return
     */
    JQGradientsLinearFeature getGradientFeature();

    /**
     * Return the heat map options
     *
     * @return
     */
    JQImageHeatMapFeature getHeatMap();

    /**
     * Returns the associated image
     *
     * @return
     */
    Image getImage();

    /**
     * Image x size
     *
     * @return
     */
    int getImageXSize();

    /**
     * Image Y size
     *
     * @return
     */
    int getImageYSize();

    /**
     * Gets the label head div
     *
     * @return
     */
    Div getLabelHeadDiv();

    /**
     * Gets the actual labels
     *
     * @return
     */
    Div getLabelsDiv();

    /**
     * Gets the legend div
     *
     * @return
     */
    Div getLegendDiv();

    /**
     * Gets the legend feature
     *
     * @return
     */
    JQMapLegendFeature getLegendFeature();

    /**
     * Return the current map object
     *
     * @return
     */
    Map getMap();

    /**
     * Returns the Map Image URL
     *
     * @return
     */
    String getMapImageUrl();

    /**
     * If this map is a heat map
     *
     * @return
     */
    boolean isHeatmap();

    /**
     * If this map is interactive
     *
     * @return
     */
    boolean isInteractive();

    /**
     * Set is labeled
     *
     * @return
     */
    boolean isLabeled();

    /**
     * If this map has a legend
     *
     * @return
     */
    boolean isLegend();

    /**
     * If the ratio is currently configured for the screen
     *
     * @return
     */
    boolean isRatioConfigured();

    /**
     * Is value displayed
     *
     * @return
     */
    boolean isValueDisplayed();

    void preConfigure();

    /**
     * Sets the default properties
     *
     * @param defaultProperties
     */
    void setDefaultProperties(JQMapInteractiveFeature defaultProperties);

    /**
     * getDisplay X size
     *
     * @param displayXSize
     */
    void setDisplayXSize(int displayXSize);

    /**
     * getDisplay Y size
     *
     * @param displayYSize
     */
    void setDisplayYSize(int displayYSize);

    /**
     * If this map is a heatmap
     *
     * @param heatmap
     */
    void setHeatmap(boolean heatmap);

    /**
     * Sets the associated image
     *
     * @param image
     */
    void setImage(Image image);

    /**
     * Image x size
     *
     * @param imageXSize
     */
    void setImageXSize(int imageXSize);

    /**
     * Set image y size
     *
     * @param imageYSize
     */
    void setImageYSize(int imageYSize);

    /**
     * If this map is interactive
     *
     * @param interactive
     */
    void setInteractive(boolean interactive);

    /**
     * Sets the label div
     *
     * @param labelHeadDiv
     */
    void setLabelHeadDiv(Div labelHeadDiv);

    /**
     * Set is labeled
     *
     * @param labeled
     */
    void setLabeled(boolean labeled);

    /**
     * Sets the label div
     *
     * @param labelsDiv
     */
    void setLabelsDiv(Div labelsDiv);

    /**
     * If this map has a legend
     *
     * @param legend
     */
    void setLegend(boolean legend);

    /**
     * Sets the legend div
     *
     * @param legendDiv
     */
    void setLegendDiv(Div legendDiv);

    /**
     * Sets the legend div
     *
     * @param legendFeature
     */
    void setLegendFeature(JQMapLegendFeature legendFeature);

    /**
     * Sets the current map object
     *
     * @param map
     */
    void setMap(Map map);

    /**
     * Sets the map image url
     *
     * @param mapImageUrl
     */
    void setMapImageUrl(String mapImageUrl);

    /**
     * Is value displayed
     *
     * @param valueDisplayed
     */
    void setValueDisplayed(boolean valueDisplayed);

}
