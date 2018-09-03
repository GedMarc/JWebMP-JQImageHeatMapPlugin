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
package com.jwebmp.plugins.imagemap;

import com.jwebmp.core.Page;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.services.IPageConfigurator;

import javax.validation.constraints.NotNull;

/**
 * @author GedMarc
 * @since 27 Feb 2017
 */
@PluginInformation(pluginName = "JQuery Interactive Image Heat Map",
		pluginUniqueName = "jquery-interactive-image-heat-map",
		pluginDescription = "An image map with selectable colour coded areas that can be determined from a series of values.",
		pluginVersion = "1.0.0",
		pluginDependancyUniqueIDs = "jquery",
		pluginCategories = "canvas, maphilight, jquery, map, area, map, polygons, heatmap, interactive",
		pluginSubtitle = "All-In-One Image Map with interactive features, able to heat mapped",
		pluginGitUrl = "https://github.com/GedMarc/JWebSwing-JQImageHeatMapPlugin",
		pluginSourceUrl = "https://sourceforge.net/p/jwebswing/jquery-image-heatmap/ci/master/tree/",
		pluginWikiUrl = "https://github.com/GedMarc/JWebSwing-JQImageHeatMapPlugin/wiki",
		pluginOriginalHomepage = "http://davidlynch.org/blog/2008/03/maphilight-image-map-mouseover-highlighting/",
		pluginDownloadUrl = "https://sourceforge.net/projects/jwebswing/files/plugins/JQImageHeatMapPlugin.jar/download",
		pluginIconUrl = "bower_components/jquery-image-heatmap/image_map_logo.jpg",
		pluginIconImageUrl = "bower_components/jquery-image-heatmap/image_map_logo.png",
		pluginLastUpdatedDate = "2017/03/04"
)
public class JQImageMapPageConfigurator
		implements IPageConfigurator
{
	/**
	 * If this configurator is enabled
	 */
	private static boolean enabled = true;

	public JQImageMapPageConfigurator()
	{
		//No configuration needed
	}

	/**
	 * Method isEnabled returns the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @return the enabled (type boolean) of this AngularAnimatedChangePageConfigurator object.
	 */
	public static boolean isEnabled()
	{
		return JQImageMapPageConfigurator.enabled;
	}

	/**
	 * Method setEnabled sets the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @param mustEnable
	 * 		the enabled of this AngularAnimatedChangePageConfigurator object.
	 */
	public static void setEnabled(boolean mustEnable)
	{
		JQImageMapPageConfigurator.enabled = mustEnable;
	}

	@NotNull
	@Override
	public Page<?> configure(Page<?> page)
	{
		return page;
	}

	@Override
	public boolean enabled()
	{
		return JQImageMapPageConfigurator.enabled;
	}
}
