module com.jwebmp.plugins.imagemap {
	exports com.jwebmp.plugins.imagemap;

	requires com.jwebmp.core;
	requires com.guicedee.logmaster;

	requires jakarta.validation;
	requires java.logging;
	requires com.jwebmp.plugins.jqgradientlinear;
	requires com.guicedee.guicedinjection;

	provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.imagemap.JQImageMapPageConfigurator;
	provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.plugins.imagemap.implementations.JQImageHeatMapExclusionsModule;

	opens com.jwebmp.plugins.imagemap to com.fasterxml.jackson.databind, com.jwebmp.core;
}
