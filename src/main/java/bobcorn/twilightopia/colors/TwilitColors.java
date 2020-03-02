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
	public final static int ignite1 = 0xFFA500;
	public final static int ignite2 = 0xFFCF00;


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
}
