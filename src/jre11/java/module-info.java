import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;
import com.jwebmp.plugins.imagemap.JQImageMapPageConfigurator;

module com.jwebmp.plugins.imagemap {
	exports com.jwebmp.plugins.imagemap;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;
	requires java.logging;
	requires com.jwebmp.plugins.jqgradientlinear;
	requires com.jwebmp.guicedinjection;

	provides IPageConfigurator with JQImageMapPageConfigurator;

	provides IGuiceScanJarExclusions with com.jwebmp.plugins.imagemap.implementations.JQImageHeatMapExclusionsModule;
	provides IGuiceScanModuleExclusions with com.jwebmp.plugins.imagemap.implementations.JQImageHeatMapExclusionsModule;

	opens com.jwebmp.plugins.imagemap to com.fasterxml.jackson.databind, com.jwebmp.core;
}
