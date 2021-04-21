package utils.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Imager {

    private static BufferedImage current;
    private static BufferedImage left;
    private static BufferedImage mid;
    private static BufferedImage right;
    private static String image;

    public static List<byte[]> getCurrent(String type) throws IOException {
        List<byte[]> all = new LinkedList<>();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(left, type, bos);
        all.add(bos.toByteArray());
        bos = new ByteArrayOutputStream();

        ImageIO.write(mid, type, bos);
        all.add(bos.toByteArray());
        bos = new ByteArrayOutputStream();

        ImageIO.write(right, type, bos);
        all.add(bos.toByteArray());
        bos = new ByteArrayOutputStream();
        return all;
    }

    public static String getImage() {
        return image;
    }

    public static void loadImage(String fileName) throws IOException {
        current = ImageIO.read(new FileInputStream("./uploaded/" + fileName));
        image = fileName;
    }

    public static void resize() {
        current = ImageTools.toBufferedImage(current.getScaledInstance(1500, 500, java.awt.Image.SCALE_DEFAULT));
    }

    public static void cutToSquare() throws IOException {
        left = cropImage(current, 0, 0, 500, 500);
        mid = cropImage(current, 0, 500, 500, 500);
        right = cropImage(current, 0, 1000, 500, 500);

        saveNew(left, "1");
        saveNew(mid, "2");
        saveNew(right, "3");
    }

    public static void save() throws IOException {
        String[] split = image.split("\\.");
        String entityType = split[split.length - 1];

        File file = new File("./uploaded/" + split[0] + "." + entityType);
        ImageIO.write(current, entityType, file);
    }

    public static void saveNew(BufferedImage img, String version) throws IOException {
        String[] split = image.split("\\.");
        String entityType = split[split.length - 1];

        File file = new File("./uploaded/" + split[0] + version + "." + entityType);
        ImageIO.write(img, entityType, file);
    }

    public static void close() {
        current.flush();
    }

    private static BufferedImage cropImage(BufferedImage src, int y, int x, int width, int height) {
        return src.getSubimage(x, y, width, height);
    }
}
