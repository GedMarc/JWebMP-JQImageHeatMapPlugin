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
