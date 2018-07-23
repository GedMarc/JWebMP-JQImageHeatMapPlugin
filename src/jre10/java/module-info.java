import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.imagemap.JQImageMapPageConfigurator;

module com.jwebmp.plugins.imagemap {
	exports com.jwebmp.plugins.imagemap;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;
	requires java.logging;
	requires com.jwebmp.plugins.jqgradientlinear;

	provides IPageConfigurator with JQImageMapPageConfigurator;

}
