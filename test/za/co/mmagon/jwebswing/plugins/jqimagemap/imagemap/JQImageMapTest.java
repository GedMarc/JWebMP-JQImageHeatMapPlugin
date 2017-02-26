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

import org.junit.Test;

/**
 *
 * @author GedMarc
 */
public class JQImageMapTest
{

    @Test
    public void testIsRatioConfigured()
    {
        JQImageMap imageMap = new JQImageMap("image");
        imageMap.setInteractive(true);
        JQMapInteractiveFeature in = new JQMapInteractiveFeature(imageMap);
        in.addProperty(InteractiveFeatureProperties.fill, "true");
        in.addProperty(InteractiveFeatureProperties.overlayColorPermanent, "004d36");
        in.addProperty(InteractiveFeatureProperties.overlayColorOpacity, "0.4");
        System.out.println(in.getProperties(true));
        System.out.println(in.getProperties(false));

        System.out.println(imageMap.toString(true));

        System.out.println(imageMap.renderJavascriptAll());

    }

}
