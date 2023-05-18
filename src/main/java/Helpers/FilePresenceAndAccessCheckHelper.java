package Helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilePresenceAndAccessCheckHelper {

    public static boolean isFileExisting(String fileName, String fileExtension){
        Path path = null;
        try {
            path = Files.createTempFile(fileName, fileExtension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Files.exists(path);
    }

    public static boolean isFileReadable(String fileName, String fileExtension){
        Path path = null;
        try {
            path = Files.createTempFile(fileName, fileExtension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Files.isReadable(path);
    }
}
