package com.latmod.mods.qnet.client;

import com.latmod.mods.qnet.QNet;
import com.latmod.mods.qnet.item.ItemWithTier;
import com.latmod.mods.qnet.item.QNetItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = QNet.MOD_ID, value = Side.CLIENT)
public class QNetClientEventHandler
{
	private static void addModel(Item item, String variant)
	{
		if (item instanceof ItemWithTier)
		{
			String s = item.getRegistryName().getPath().replace(((ItemWithTier) item).tier.getName() + "_", "");
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().getNamespace() + ":" + s + "/" + ((ItemWithTier) item).tier.getName(), variant));
		}
		else
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		addModel(QNetItems.BASIC_CIRCUIT, "inventory");
		addModel(QNetItems.BASIC_CONTROLLER, "normal");
		addModel(QNetItems.BASIC_SCREEN, "inventory");

		addModel(QNetItems.ADVANCED_CIRCUIT, "inventory");
		addModel(QNetItems.ADVANCED_CONTROLLER, "normal");
		addModel(QNetItems.ADVANCED_SCREEN, "inventory");

		addModel(QNetItems.SUPREME_CIRCUIT, "inventory");
		addModel(QNetItems.SUPREME_CONTROLLER, "normal");
		addModel(QNetItems.SUPREME_SCREEN, "inventory");

		//ClientRegistry.bindTileEntitySpecialRenderer(TileControllerBasic.class, new RenderController());
	}
}