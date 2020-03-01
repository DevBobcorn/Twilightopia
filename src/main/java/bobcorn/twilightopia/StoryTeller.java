package bobcorn.twilightopia;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.apache.commons.io.IOUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class StoryTeller {
	public static final String GetStory(ItemStack item) {
		return GetStory(getPath(item));
	}
	
	public static final String GetStory(String path) {
		IResource iresource = null;
		String lines = "";
		try {
			System.out.println("Tellin' Story From Path " + path);

			String s = "" + TextFormatting.WHITE + TextFormatting.OBFUSCATED + TextFormatting.GREEN
					+ TextFormatting.AQUA;

			iresource = Minecraft.getInstance().getResourceManager()
					.getResource(new ResourceLocation(TwilightopiaMod.MODID, path));
			System.out.println("Story Found.");
			InputStream inputstream = iresource.getInputStream();
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(inputstream, StandardCharsets.UTF_8));
			Random random = new Random(8124371L);
			String s1;
			while ((s1 = bufferedreader.readLine()) != null) {
				String s2;
				String s3;
				for (s1 = s1.replaceAll("PLAYERNAME", Minecraft.getInstance().getSession().getUsername()); s1
						.contains(s); s1 = s2 + TextFormatting.WHITE + TextFormatting.OBFUSCATED
								+ "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s3) {
					int j = s1.indexOf(s);
					s2 = s1.substring(0, j);
					s3 = s1.substring(j + s.length());
				}
				System.out.println("Line: " + s1);
				lines = lines.concat(s1 + '\n');
			}
			inputstream.close();
		} catch (Exception exception) {
			// System.out.println("No Story / Execution Error!");
		} finally {
			IOUtils.closeQuietly((Closeable) iresource);
		}
		return lines;
	}

	public static final String getPath(String Target) {
		return "stories/" + Minecraft.getInstance().getLanguageManager().getCurrentLanguage().getCode() + '/' + Target + ".txt";
	}
	
	public static final String getPath(ItemStack Target) {
		return "stories/" + Minecraft.getInstance().getLanguageManager().getCurrentLanguage().getCode() + '/' + Target.getItem().getTranslationKey() + ".txt";
	}
}
