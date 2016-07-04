package com.codename1.apps.devicetester;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.l10n.L10NManager;
import com.codename1.messaging.Message;
import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 * Device Tester allows <a href="https://www.codenameone.com/">Codename One</a> developers to inspect
 * various common properties about a device you might have at hand to understand why a specific device behaves
 * in a specific way.
 */
public class DeviceTester {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Basic Details", BoxLayout.y());
        Display d = Display.getInstance();
        String density = "";
        switch(Display.getInstance().getDeviceDensity()) {
            case Display.DENSITY_2HD:
                density = "DENSITY_2HD";
                break;
            case Display.DENSITY_4K:
                density = "DENSITY_4K";
                break;
            case Display.DENSITY_560:
                density = "DENSITY_560";
                break;
            case Display.DENSITY_HD:
                density = "DENSITY_HD";
                break;
            case Display.DENSITY_HIGH:
                density = "DENSITY_HIGH";
                break;
            case Display.DENSITY_LOW:
                density = "DENSITY_LOW";
                break;
            case Display.DENSITY_MEDIUM:
                density = "DENSITY_MEDIUM";
                break;
            case Display.DENSITY_VERY_HIGH:
                density = "DENSITY_VERY_HIGH";
                break;
            case Display.DENSITY_VERY_LOW:
                density = "DENSITY_VERY_LOW";
                break;
        }
        
        double pixelsPerMM = (((double)d.convertToPixels(1000)) / 1000.0);
        L10NManager l10n = L10NManager.getInstance();
        
        hi.add("Density:").
                add(new SpanLabel(density)).
                add(" ").
                add("Platform Name:").
                add(new SpanLabel(d.getPlatformName())).
                add(" ").
                add("User Agent:").
                add(new SpanLabel(d.getProperty("User-Agent", ""))).
                add(" ").
                add("OS:").
                add(new SpanLabel(d.getProperty("OS", ""))).
                add(" ").
                add("OS Version:").
                add(new SpanLabel(d.getProperty("OSVer", ""))).
                add(" ").
                add("UDID:").
                add(new SpanLabel(d.getUdid())).
                add(" ").
                add("MSISDN:").
                add(new SpanLabel(d.getMsisdn())).
                add(" ").
                add("Display Width X Height:").
                add(new SpanLabel(d.getDisplayWidth() + "X" + d.getDisplayHeight())).
                add(" ").
                add("1mm In Pixels:").
                add(new SpanLabel(l10n.format(pixelsPerMM))).
                add(" ").
                add("Language:").
                add(new SpanLabel(l10n.getLanguage())).
                add(" ").
                add("Locale:").
                add(new SpanLabel(l10n.getLocale())).
                add(" ").
                add("Currency Symbol:").
                add(new SpanLabel(l10n.getCurrencySymbol())).
                add(" ").
                add(uneditableCheck("Are Mutable Images Fast", d.areMutableImagesFast())).
                add(uneditableCheck("Can Dial", d.canDial())).
                add(uneditableCheck("Can Force Orientation", d.canForceOrientation())).
                add(uneditableCheck("Has Camera", d.hasCamera())).
                add(uneditableCheck("Badging", d.isBadgingSupported())).
                add(uneditableCheck("Desktop", d.isDesktop())).
                add(uneditableCheck("Tablet", d.isTablet())).
                add(uneditableCheck("Gaussian Blur Support", d.isGaussianBlurSupported())).
                add(uneditableCheck("Get All Contacts Fast", d.isGetAllContactsFast())).
                add(uneditableCheck("Multi Touch", d.isMultiTouch())).
                add(uneditableCheck("PICKER_TYPE_DATE", d.isNativePickerTypeSupported(Display.PICKER_TYPE_DATE))).
                add(uneditableCheck("PICKER_TYPE_DATE_AND_TIME", d.isNativePickerTypeSupported(Display.PICKER_TYPE_DATE_AND_TIME))).
                add(uneditableCheck("PICKER_TYPE_STRINGS", d.isNativePickerTypeSupported(Display.PICKER_TYPE_STRINGS))).
                add(uneditableCheck("PICKER_TYPE_TIME", d.isNativePickerTypeSupported(Display.PICKER_TYPE_TIME))).
                add(uneditableCheck("Native Share", d.isNativeShareSupported())).
                add(uneditableCheck("Native Video Player Controls", d.isNativeVideoPlayerControlsIncluded())).
                add(uneditableCheck("Notification", d.isNotificationSupported())).
                add(uneditableCheck("Open Native Navigation", d.isOpenNativeNavigationAppSupported())).
                add(uneditableCheck("Screen Saver Disable", d.isScreenSaverDisableSupported())).
                add(uneditableCheck("Simulator", d.isSimulator()));
        
        final String densityStr = density;
        hi.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SEND, e -> {
            StringBuilder body = new StringBuilder("Density").
                append(densityStr).
                append("\n").
                append("Platform Name:").
                append(d.getPlatformName()).
                append("\n").
                append("User Agent:").
                append(d.getProperty("User-Agent", "")).
                append("\n").
                append("OS:").
                append(d.getProperty("OS", "")).
                append("\n").
                append("OS Version:").
                append(d.getProperty("OSVer", "")).
                append("\n").
                append("UDID:").
                append(d.getUdid()).
                append("\n").
                append("MSISDN:").
                append(d.getMsisdn()).
                append("\n").
                append("Display Width X Height:").
                append(d.getDisplayWidth()).append("X").append(d.getDisplayHeight()).
                append("\n").
                append("1mm In Pixels:").
                append(l10n.format(pixelsPerMM)).
                append("\n").
                append("Language:").
                append(new SpanLabel(l10n.getLanguage())).
                append("\n").
                append("Locale:").
                append(l10n.getLocale()).
                append("\n").
                append("Currency Symbol:").
                append(l10n.getCurrencySymbol()).
                append("\nAre Mutable Images Fast: ").
                append(d.areMutableImagesFast()).
                append("\nCan Dial: ").
                append(d.canDial()).
                append("\nCan Force Orientation: ").append(d.canForceOrientation()).
                append("\nHas Camera: ").append(d.hasCamera()).
                append("\nBadging: ").append(d.isBadgingSupported()).
                append("\nDesktop: ").append(d.isDesktop()).
                append("\nTablet: ").append(d.isTablet()).
                append("\nGaussian Blur Support: ").append(d.isGaussianBlurSupported()).
                append("\nGet All Contacts Fast: ").append(d.isGetAllContactsFast()).
                append("\nMulti Touch: ").append(d.isMultiTouch()).
                append("\nPICKER_TYPE_DATE: ").append(d.isNativePickerTypeSupported(Display.PICKER_TYPE_DATE)).
                append("\nPICKER_TYPE_DATE_AND_TIME: ").append(d.isNativePickerTypeSupported(Display.PICKER_TYPE_DATE_AND_TIME)).
                append("\nPICKER_TYPE_STRINGS: ").append(d.isNativePickerTypeSupported(Display.PICKER_TYPE_STRINGS)).
                append("\nPICKER_TYPE_TIME: ").append(d.isNativePickerTypeSupported(Display.PICKER_TYPE_TIME)).
                append("\nNative Share: ").append(d.isNativeShareSupported()).
                append("\nNative Video Player Controls: ").append(d.isNativeVideoPlayerControlsIncluded()).
                append("\nNotification: ").append(d.isNotificationSupported()).
                append("\nOpen Native Navigation: ").append(d.isOpenNativeNavigationAppSupported()).
                append("\nScreen Saver Disable: ").append(d.isScreenSaverDisableSupported()).
                append("\nSimulator: ").append(d.isSimulator());
            
            Message msg = new Message(body.toString());
                    
            Display.getInstance().sendMessage(new String[] { Display.getInstance().getProperty("built_by_user", "youremail@somewhere.com") }, 
                    "Device Details", msg);
        });
        
        hi.show();
    }

    private CheckBox uneditableCheck(String t, boolean v) {
        CheckBox c = new CheckBox(t);
        c.setSelected(v);
        c.setEnabled(false);
        return c;
    }
    
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
