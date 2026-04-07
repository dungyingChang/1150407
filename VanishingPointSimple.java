import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class VanishingPointSimple {

    public static void main(String[] args) {
        try {
            // 👉 只要改這一行就好
            String path = "C:\\image\\ncc.jpg";

            BufferedImage img = ImageIO.read(new File(path));

            if (img == null) {
                System.out.println("讀不到圖片");
                return;
            }

            // 兩條鐵軌上的點（可微調）
            int x1 = 470, y1 = 720;
            int x2 = 620, y2 = 430;

            int x3 = 760, y3 = 720;
            int x4 = 660, y4 = 430;

            double d = (x1 - x2) * (double)(y3 - y4) - (y1 - y2) * (double)(x3 - x4);

            if (d == 0) {
                System.out.println("無交點");
                return;
            }

            double px = ((x1*y2 - y1*x2)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4)) / d;
            double py = ((x1*y2 - y1*x2)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4)) / d;

            int vx = (int)px;
            int vy = (int)py;

            Graphics2D g = img.createGraphics();
            g.setStroke(new BasicStroke(3));

            // 畫線
            g.setColor(Color.RED);
            g.drawLine(x1, y1, x2, y2);

            g.setColor(Color.BLUE);
            g.drawLine(x3, y3, x4, y4);

            // 畫消失點
            g.setColor(Color.YELLOW);
            g.fillOval(vx - 8, vy - 8, 16, 16);

            g.dispose();

            // 👉 直接覆蓋原圖（不用第二個路徑）
            ImageIO.write(img, "jpg", new File(path));

            System.out.println("Vanishing Point: (" + vx + ", " + vy + ")");

        } catch (Exception e) {
            System.out.println("錯誤");
        }
    }
}