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

import java.util.HashMap;
import java.util.Map;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapFeatures;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;

/**
 * @since @version @author MMagon
 *
 *
 */
public class JQMapInteractiveFeature extends Feature<JavaScriptPart, JQMapInteractiveFeature> implements ImageMapFeatures
{

    private static final long serialVersionUID = 1L;

    private JQImageMap map;
    private boolean defaultProperties;
    private HashMap<InteractiveFeatureProperties, Object> appliedProperties = new HashMap();

    /**
     * Constructs a new Interactive feature for a map, or its area
     *
     * @param map
     */
    public JQMapInteractiveFeature(JQImageMap map)
    {
        super("JWMapInteractiveFeature");
        this.map = map;
        getJavascriptReferences().add(new JQHilightReference());
    }

    public HashMap<InteractiveFeatureProperties, Object> getAppliedProperties()
    {
        return appliedProperties;
    }

    public void setAppliedProperties(HashMap<InteractiveFeatureProperties, Object> appliedProperties)
    {
        this.appliedProperties = appliedProperties;
    }

    @Override
    public void assignFunctionsToComponent()
    {
        if (this.map != null)
        {
            //arr.add("$(function() {$('#" + map.getImage().getID() + "').mapPrep()});;");
            addQuery("$(function() {$('#" + map.getImage().getID() + "').maphilight({" + getProperties(false) + "});"
                    + "});");
        }

    }

    /**
     * Sets whether this is a default properties render inside the constructor
     *
     * @return Whether or not this is the default properties
     */
    public boolean isDefaultProperties()
    {
        return defaultProperties;
    }

    /**
     * Sets if this is to be rendered as the default image map properties
     *
     * @param defaultProperties Whether or not this is the default properties
     */
    public void setDefaultProperties(boolean defaultProperties)
    {
        this.defaultProperties = defaultProperties;
    }

    /**
     * Returns all the map highlight properties associated with this interactive feature
     *
     * @param inline Set to true for attribute format
     * @return The required property line
     */
    public String getProperties(boolean inline)
    {
        String propertySB = "";
        int current = 0;
        for (Map.Entry<InteractiveFeatureProperties, Object> entry : appliedProperties.entrySet())
        {
            InteractiveFeatureProperties interactiveFeatureProperties = entry.getKey();
            Object object = entry.getValue();
            current++;

            if (object.toString().equalsIgnoreCase(interactiveFeatureProperties.getDefaultValue().toString()))
            {
                continue;
            }
            //System.out.println("in [" + interactiveFeatureProperties.getClassType().getSimpleName() + "]");

            propertySB += (inline ? "\"" : "") + interactiveFeatureProperties.name() + (inline ? "\"" : "") + ":";
            String bleh = interactiveFeatureProperties.getClassType().getSimpleName();
            if (bleh.equals("Double") || bleh.equals("Boolean"))
            {
                propertySB += (inline ? "\"" : "") + object + (inline ? "\"" : "");
            }
            else
            {
                propertySB += "\"" + object + "\"";
            }
            /*
             * switch (interactiveFeatureProperties.getClassType().getSimpleName())
             * {
             * case "Double":
             * case "Boolean":
             * {
             * propertySB += (inline ? "\"" : "") + object + (inline ? "\"" : "");
             * break;
             * }
             * case "String":
             * {
             * propertySB += "\""+ object + "\"";
             * break;
             * }
             * default:
             * {
             *
             * }
             * }
             */
            if (current != appliedProperties.size())
            {
                propertySB += ",";
            }
        }
        return propertySB;
    }

    public String getProperty(InteractiveFeatureProperties property)
    {
        return appliedProperties.get(property).toString();
    }

    /**
     * Adds a property to this part of the image map
     *
     * @param property The property to add
     * @param value The value to assign
     */
    public void addProperty(InteractiveFeatureProperties property, String value)
    {
        appliedProperties.put(property, value);
    }

}
