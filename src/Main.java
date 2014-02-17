import java.io.File;
// based on QuickTip Slick2D tutorials

import org.lwjgl.LWJGLUtil;

public class Main {
	public static void main (String[] args) {
		// to select the correct platform-dependent files (i.e. MACOSX, Windows, Linux)
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());

	}
}
