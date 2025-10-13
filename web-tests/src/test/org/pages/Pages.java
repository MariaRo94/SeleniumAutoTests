package org.pages;

import org.utils.WebDriverFactory;

public class Pages {
    public static TrainingPage trainingPage(WebDriverFactory driverFactory) {
        return new TrainingPage(driverFactory);
    }
}

