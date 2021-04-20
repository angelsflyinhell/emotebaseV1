package utils.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Storage {

    public static void downloadByUrl(String url, String fileName) throws IOException {
        String[] split = url.split("\\.");
        String entityType = split[split.length - 1];

        URL download = new URL(url);
        BufferedImage img = ImageIO.read(download);
        File file = new File("./uploaded/" + fileName + "." + entityType);
        ImageIO.write(img, entityType, file);
    }

}
