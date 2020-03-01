package bobcorn.twilightopia.colors;

public class RainbowColors {
	public final static int red     = 0xFF0000;
	public final static int yellow  = 0xFFFF00;
	public final static int green   = 0x00FF00;
	public final static int cyan    = 0x00FFFF;
	public final static int blue    = 0x0000FF;
	public final static int magenta = 0xFF00FF;
	
	public static int getRainbowColorAt(double frac) {
		if (frac < 0.0D || frac > 1.0D) return red;
		return getGradientBetween6(frac * 6.0D);
	}
	
	public static int getGradientBetween6(Double frac) {
		int state = frac.intValue();
		double proc = frac - state;
		switch (state) {
			case 0:
				return TwilitColors.getColorBetween(proc, red, yellow);
			case 1:
				return TwilitColors.getColorBetween(proc, yellow, green);
			case 2:
				return TwilitColors.getColorBetween(proc, green, cyan);
			case 3:
				return TwilitColors.getColorBetween(proc, cyan, blue);
			case 4:
				return TwilitColors.getColorBetween(proc, blue, magenta);
			case 5:
				return TwilitColors.getColorBetween(proc, magenta, red);
			default:
				return red;
		}
	}
}
