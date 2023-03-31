import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradordeStickers {
    void cria(InputStream inputStream, String nomeArquivo) throws IOException{

        BufferedImage imgOriginal = ImageIO.read(inputStream);
    
        int width = imgOriginal.getWidth();
        int height = imgOriginal.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
    
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imgOriginal, 0, 0, null);

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 96);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);


        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds("TOPZERA", graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (width - larguraTexto) / 2;

        graphics.drawString("TOPZERA", posicaoTextoX, newHeight - 85);

        ImageIO.write(newImage, "png", new File(nomeArquivo));
    }

    // public static void main(String[] args) throws IOException {
    //     var geradora = new GeradordeStickers();
    //     geradora.cria();
    // }
}
