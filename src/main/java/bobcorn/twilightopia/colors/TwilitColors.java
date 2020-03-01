package bobcorn.twilightopia.colors;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class TwilitColors {
	public final static int pink1   = 0xFFCCE8;
	public final static int pink2   = 0xFFB2DD;
	public final static int holy1   = 0x20D6FF;
	public final static int holy2   = 0x2098FF;
	public final static int white1  = 0xFFE4F3;
	public final static int white2  = 0xFFFEFF;
	public final static int sweet1  = 0xFF3375;
	public final static int sweet2  = 0x8900BD;
	public final static int ignite1 = 0xFF7600;
	public final static int ignite2 = 0xFF6000;
    public final static int misty1 = 0x20E6FF;
	public final static int misty2 = 0xC6F1C3;

	
	public final static int sky1 = 0x29168C;
	public final static int sky2 = 0x891990;

	public static int getBlockColorAt(int height,int min,int max,int minColor,int maxColor) {
		if (height >= max) return maxColor;
		else if (height <= min) return minColor;
		double frac = ((double)height - (double)min) / ((double)max - (double)min);
		
		return getColorBetween(frac, minColor, maxColor);
	}
	
	public static int getItemColor(ItemStack stack,int lc,int rc) {
		double frac = (double)stack.getCount() / stack.getMaxStackSize();
		return getColorBetween(frac,lc,rc);
	}
	
	public static int getColorBetween(double frac,int lc,int rc) {
		int red1 = (lc & 0xff0000) >> 16;
    	int green1 = (lc & 0xff00) >> 8;
    	int blue1 = lc & 0xff;
    	
    	int red2 = (rc & 0xff0000) >> 16;
    	int green2 = (rc & 0xff00) >> 8;
    	int blue2 = rc & 0xff;
    	
    	int red3 = (int)MathHelper.lerp(frac,red1,red2);
    	int green3 = (int)MathHelper.lerp(frac,green1,green2);
    	int blue3 = (int)MathHelper.lerp(frac,blue1,blue2);
    	
    	return (red3 << 16) + (green3 << 8) + blue3;
	}
	
	public static int getRed(int c) {
		return (c & 0xff0000) >> 16;
	}
	
	public static int getGreen(int c) {
		return (c & 0xff00) >> 8;
	}
	
	public static int getBlue(int c) {
		return (c & 0xff);
	}
	
	public static float getRedf(int c) {
		return (float)((c & 0xff0000) >> 16) / 255.0F;
	}
	
	public static float getGreenf(int c) {
		return (float)((c & 0xff00) >> 8) / 255.0F;
	}
	
	public static float getBluef(int c) {
		return (float)(c & 0xff) / 255.0F;
	}
}
