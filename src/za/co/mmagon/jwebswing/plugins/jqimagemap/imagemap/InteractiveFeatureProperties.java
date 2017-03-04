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

public enum InteractiveFeatureProperties
{
    /**
     * Whether to fill the shape
     */
    fill(Boolean.class, Boolean.TRUE),
    /**
     * The color to fill the shape with
     */
    fillColor(String.class, "000000"),
    /**
     * The opacity of the fill (0 = fully transparent, 1 = fully opaque)
     */
    fillOpacity(Double.class, 0.2),
    /**
     * Whether to outline the shape
     */
    stroke(Boolean.class, Boolean.TRUE),
    /**
     * The color of the outline
     */
    strokeColor(String.class, "ff0000"),
    /**
     * The opacity of the outline (0 = fully transparent, 1 = fully opaque)
     */
    strokeOpacity(Double.class, 0.2),
    /**
     * The thickness of the outline
     */
    strokeWidth(Double.class, 0.4),
    /**
     * Whether to fade in the shapes on mouseover
     */
    fade(Boolean.class, Boolean.TRUE),
    /**
     * Whether to always show the hilighted areas
     */
    alwaysOn(Boolean.class, Boolean.FALSE),
    /**
     * Whether to never show the hilighted areas (This only makes sense to use as per-area metadata, of course.)
     */
    neverOn(Boolean.class, Boolean.FALSE),
    /**
     * The name of an attribute to group areas by If this is present then all areas in the map which have the same attribute value as the hovered area will hilight as well
     */
    groupBy(String.class, ""),
    /**
     * If true, applies the class on the img to the wrapper div maphilight created. If a string, that string is used as a class on the wrapper div.
     */
    wrapClass(Boolean.class, Boolean.FALSE),
    /**
     * Whether to apply a shadow to the shape. (Only works with canvas-supporting browsers. So, everything but IE below version 9.)
     */
    shadow(Boolean.class, Boolean.FALSE),
    /**
     * Where to position the shadow. Can be 'outside', 'inside', or 'both'.
     */
    shadowPosition(Double.class, 0.4),
    /**
     * What to use to cast the shadow. Can be 'stroke' or 'fill'. If false, choose a value based on the shadow position. (Generally it looks better if it's being cast from a fill when it's outside the
     * shape, and from a stroke when it's inside the shape.)
     */
    shadowFrom(String.class, "fill"),
    /**
     * Horizontal offset of the shadow from the shape.
     */
    shadowX(Double.class, 0.4),
    /**
     * Vertical offset of the shadow from the shape.
     */
    shadowY(Double.class, 0.4),
    /**
     * Size of the shadow.
     */
    shadowRadius(Double.class, 0.4),
    /**
     * Color of the shadow.
     */
    shadowColor(String.class, "000000"),
    /**
     * Opacity of the shadow.
     */
    shadowOpacity(Double.class, 0.1),
    /**
     * The default colour to overlay with - Heat map uses this
     */
    overlayColorPermanent(String.class, "transparent"),
    /**
     * The opacity for the permanent colour
     */
    overlayColorOpacity(Double.class, 1),
    /**
     * A valid reference ID
     */
    boneId(String.class, "No Bone ID"),
    /**
     * Available attribute association ID
     */
    attName(String.class, "Holder 1"),
    /**
     * Available attribute association ID
     */
    attName2(String.class, "Holder 2");

    private Class classType;
    private Object defaultValue;

    InteractiveFeatureProperties(Class classType, Object defaultValue)
    {
        this.classType = classType;
        this.defaultValue = defaultValue;
    }

    public Class getClassType()
    {
        return classType;
    }

    public void setClassType(Class classType)
    {
        this.classType = classType;
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}
