package com.latmod.mods.qnet.block;

import com.latmod.mods.qnet.QNet;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author LatvianModder
 */
@GameRegistry.ObjectHolder(QNet.MOD_ID)
public class QNetBlocks
{
	public static final Block BASIC_CONTROLLER = Blocks.AIR;
	public static final Block BASIC_SCREEN = Blocks.AIR;

	public static final Block ADVANCED_CONTROLLER = Blocks.AIR;
	public static final Block ADVANCED_SCREEN = Blocks.AIR;

	public static final Block SUPREME_CONTROLLER = Blocks.AIR;
	public static final Block SUPREME_SCREEN = Blocks.AIR;
}