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

import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.children.ImageMapFeatures;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.utilities.StaticStrings;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * @since @version @author MMagon
 */
public class JQMapInteractiveFeature extends Feature<JavaScriptPart, JQMapInteractiveFeature> implements ImageMapFeatures
{

	private static final long serialVersionUID = 1L;

	private JQImageMap map;
	private boolean defaultProperties;
	private Map<InteractiveFeatureProperties, Serializable> appliedProperties = new EnumMap<>(InteractiveFeatureProperties.class);

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

	public Map<InteractiveFeatureProperties, Serializable> getAppliedProperties()
	{
		return appliedProperties;
	}

	public void setAppliedProperties(Map<InteractiveFeatureProperties, Serializable> appliedProperties)
	{
		this.appliedProperties = appliedProperties;
	}

	@Override
	public void assignFunctionsToComponent()
	{
		if (this.map != null)
		{
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
	 * Returns all the map highlight properties associated with this interactive feature
	 *
	 * @param inline
	 * 		Set to true for attribute format
	 *
	 * @return The required property line
	 */
	public String getProperties(boolean inline)
	{
		StringBuilder propertySB = new StringBuilder();
		int current = 0;
		for (Map.Entry<InteractiveFeatureProperties, Serializable> entry : appliedProperties.entrySet())
		{
			InteractiveFeatureProperties interactiveFeatureProperties = entry.getKey();
			Object object = entry.getValue();
			current++;

			if (object.toString().equalsIgnoreCase(interactiveFeatureProperties.getDefaultValue().toString()))
			{
				continue;
			}
			propertySB.append((inline ? StaticStrings.STRING_DOUBLE_QUOTES : "") + interactiveFeatureProperties.name() + (inline ? StaticStrings.STRING_DOUBLE_QUOTES_SPACE : "") + ":");
			String bleh = interactiveFeatureProperties.getClassType().getSimpleName();
			if ("Double".equals(bleh) || "Boolean".equals(bleh))
			{
				propertySB.append((inline ? StaticStrings.STRING_DOUBLE_QUOTES : "") + object + (inline ? StaticStrings.STRING_DOUBLE_QUOTES : ""));
			}
			else
			{
				propertySB.append(StaticStrings.STRING_DOUBLE_QUOTES + object + StaticStrings.STRING_DOUBLE_QUOTES);
			}

			if (current != appliedProperties.size())
			{
				propertySB.append(StaticStrings.STRING_COMMNA);
			}
		}
		return propertySB.toString();
	}

	/**
	 * Sets if this is to be rendered as the default image map properties
	 *
	 * @param defaultProperties
	 * 		Whether or not this is the default properties
	 */
	public void setDefaultProperties(boolean defaultProperties)
	{
		this.defaultProperties = defaultProperties;
	}

	public String getProperty(InteractiveFeatureProperties property)
	{
		return appliedProperties.get(property).toString();
	}

	/**
	 * Adds a property to this part of the image map
	 *
	 * @param property
	 * 		The property to add
	 * @param value
	 * 		The value to assign
	 */
	public void addProperty(InteractiveFeatureProperties property, String value)
	{
		appliedProperties.put(property, value);
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

		JQMapInteractiveFeature that = (JQMapInteractiveFeature) o;

		if (isDefaultProperties() != that.isDefaultProperties())
		{
			return false;
		}
		if (!map.equals(that.map))
		{
			return false;
		}
		return getAppliedProperties().equals(that.getAppliedProperties());
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + map.hashCode();
		result = 31 * result + (isDefaultProperties() ? 1 : 0);
		result = 31 * result + getAppliedProperties().hashCode();
		return result;
	}
}
