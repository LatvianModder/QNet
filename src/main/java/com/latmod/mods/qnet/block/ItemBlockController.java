package com.latmod.mods.qnet.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author LatvianModder
 */
public class ItemBlockController extends ItemBlock
{
	public ItemBlockController(Block block)
	{
		super(block);
		setMaxStackSize(1);
	}
}