package com.latmod.mods.qnet;

import com.latmod.mods.qnet.block.BlockController;
import com.latmod.mods.qnet.block.BlockScreen;
import com.latmod.mods.qnet.block.ItemBlockController;
import com.latmod.mods.qnet.block.ItemBlockScreen;
import com.latmod.mods.qnet.block.QNetBlocks;
import com.latmod.mods.qnet.item.ItemWithTier;
import com.latmod.mods.qnet.tile.TileControllerAdvanced;
import com.latmod.mods.qnet.tile.TileControllerBasic;
import com.latmod.mods.qnet.tile.TileControllerSupreme;
import com.latmod.mods.qnet.tile.TileScreenAdvanced;
import com.latmod.mods.qnet.tile.TileScreenBasic;
import com.latmod.mods.qnet.tile.TileScreenSupreme;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = QNet.MOD_ID)
public class QNetEventHandler
{
	private static Block withName(Block block, String name)
	{
		block.setCreativeTab(QNet.TAB);
		block.setRegistryName(name);
		block.setTranslationKey(QNet.MOD_ID + "." + name);
		return block;
	}

	private static Item withName(Item item, String name)
	{
		item.setCreativeTab(QNet.TAB);
		item.setRegistryName(name);
		item.setTranslationKey(QNet.MOD_ID + "." + name);
		return item;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				withName(new BlockController(QNetTier.BASIC), "basic_controller"),
				withName(new BlockScreen(QNetTier.BASIC), "basic_screen"),

				withName(new BlockController(QNetTier.ADVANCED), "advanced_controller"),
				withName(new BlockScreen(QNetTier.ADVANCED), "advanced_screen"),

				withName(new BlockController(QNetTier.SUPREME), "supreme_controller"),
				withName(new BlockScreen(QNetTier.SUPREME), "supreme_screen")
		);

		GameRegistry.registerTileEntity(TileControllerBasic.class, new ResourceLocation(QNet.MOD_ID, "basic_controller"));
		GameRegistry.registerTileEntity(TileScreenBasic.class, new ResourceLocation(QNet.MOD_ID, "basic_screen"));

		GameRegistry.registerTileEntity(TileControllerAdvanced.class, new ResourceLocation(QNet.MOD_ID, "advanced_controller"));
		GameRegistry.registerTileEntity(TileScreenAdvanced.class, new ResourceLocation(QNet.MOD_ID, "advanced_screen"));

		GameRegistry.registerTileEntity(TileControllerSupreme.class, new ResourceLocation(QNet.MOD_ID, "supreme_controller"));
		GameRegistry.registerTileEntity(TileScreenSupreme.class, new ResourceLocation(QNet.MOD_ID, "supreme_screen"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				withName(new ItemWithTier(QNetTier.BASIC), "basic_circuit"),
				new ItemBlockController(QNetBlocks.BASIC_CONTROLLER).setRegistryName("basic_controller"),
				new ItemBlockScreen(QNetBlocks.BASIC_SCREEN).setRegistryName("basic_screen"),

				withName(new ItemWithTier(QNetTier.ADVANCED), "advanced_circuit"),
				new ItemBlockController(QNetBlocks.ADVANCED_CONTROLLER).setRegistryName("advanced_controller"),
				new ItemBlockScreen(QNetBlocks.ADVANCED_SCREEN).setRegistryName("advanced_screen"),

				withName(new ItemWithTier(QNetTier.SUPREME), "supreme_circuit"),
				new ItemBlockController(QNetBlocks.SUPREME_CONTROLLER).setRegistryName("supreme_controller"),
				new ItemBlockScreen(QNetBlocks.SUPREME_SCREEN).setRegistryName("supreme_screen")
		);
	}
}