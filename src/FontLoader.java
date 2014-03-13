
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.*;


//It does nothing but to load a True Type Font ... we can use it later
//to load unicode fonts as well.
public class FontLoader
{
	 public static TrueTypeFont loadTTFont(String fontLocation, int fontSize)
	            throws IOException, FontFormatException
	    {
	        //Get font from fontLocation
	        BufferedInputStream location = new BufferedInputStream
	                (FontLoader.class.getClassLoader().
	                getResourceAsStream(fontLocation));

	        //Now a font based on the given file is created
	        Font startFont = Font.createFont(Font.TRUETYPE_FONT, location);

	        //As the TrueTypeFont constructor needs already scaled fonts, we need
	        //to do that little WA
	        Font baseFont = startFont.deriveFont(Font.TRUETYPE_FONT, fontSize);

	        return new TrueTypeFont(baseFont, true);
	    }

}