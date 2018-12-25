package com.latmod.mods.qnet;

import com.latmod.mods.qnet.device.QNetDeviceEntry;
import com.latmod.mods.qnet.device.QNetInputDevice;
import com.latmod.mods.qnet.device.QNetOutputDevice;
import com.latmod.mods.qnet.device.QNetStorageDevice;
import com.latmod.mods.qnet.item.QNetItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = QNet.MOD_ID,
		name = QNet.MOD_NAME,
		version = QNet.VERSION
)
public class QNet
{
	public static final String MOD_ID = "qnet";
	public static final String MOD_NAME = "QNet";
	public static final String VERSION = "0.0.0.qnet";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static final CreativeTabs TAB = new CreativeTabs(QNet.MOD_ID)
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(QNetItems.ADVANCED_CONTROLLER);
		}
	};

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		QNetDeviceEntry.register("storage", QNetStorageDevice::new, QNetStorageDevice::new, QNetStorageDevice::new);
		QNetDeviceEntry.register("in", QNetInputDevice::new, QNetInputDevice::new, QNetInputDevice::new);
		QNetDeviceEntry.register("out", QNetOutputDevice::new, QNetOutputDevice::new, QNetOutputDevice::new);

		//QNetNetHandler.init();
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, new FTBQuestsGuiHandler());
	}
}