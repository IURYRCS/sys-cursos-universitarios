package org.utils;

import java.nio.file.Paths;

public class PathFXML {
    public static String pathFXML(){
        return Paths.get("src/main/java/org/view").toAbsolutePath().toString();
    }
}

