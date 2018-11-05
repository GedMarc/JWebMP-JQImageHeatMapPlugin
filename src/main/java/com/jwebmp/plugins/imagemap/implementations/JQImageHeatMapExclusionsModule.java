package com.jwebmp.plugins.imagemap.implementations;

import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class JQImageHeatMapExclusionsModule
		implements IGuiceScanModuleExclusions<JQImageHeatMapExclusionsModule>,
				           IGuiceScanJarExclusions<JQImageHeatMapExclusionsModule>
{

	@Override
	public @NotNull Set<String> excludeJars()
	{
		Set<String> strings = new HashSet<>();
		strings.add("jwebmp-image-map-heatmap-*");
		return strings;
	}

	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.plugins.imagemap");
		return strings;
	}
}
