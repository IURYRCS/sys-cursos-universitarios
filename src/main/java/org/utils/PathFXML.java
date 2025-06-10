package org.utils;

import java.nio.file.Paths;

public class PathFXML {
    public static String pathFXML(){
        String path = "C:\\Users\\Unifan\\Documents\\AQUIII\\sys-cursos-universitarios\\src\\main\\java\\org\\view";
        return Paths.get(path).toAbsolutePath().toString();
    }
}
