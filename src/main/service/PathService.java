package main.service;

import main.util.PrintingUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathService {

    public static Path getOutputPath(Path path, String suffix) {
        String fullPath = path.toAbsolutePath().toString();
        String source = null;
        if (fullPath.contains(PrintingUtil.ENCRYPTED_FORMAT)) {
            source = fullPath.substring(0, fullPath.lastIndexOf(PrintingUtil.ENCRYPTED_FORMAT + "."));
        } else if (fullPath.contains(PrintingUtil.DECRYPTED_FORMAT)) {
            source = fullPath.substring(0, fullPath.lastIndexOf(PrintingUtil.DECRYPTED_FORMAT + "."));
        } else {
            source = fullPath.substring(0, fullPath.lastIndexOf("."));
        }
        String extension = fullPath.substring(fullPath.lastIndexOf("."));
        return Paths.get(source + suffix + extension);
    }

}
