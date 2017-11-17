package com.neu.jan17.data;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

// Url Handle for Dealer Info Table
public class UrlHandle {
    public void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                System.out.println("Wrong URL");
            }
        }
    }

    public void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println("Wrong URL");
        }
    }
}
