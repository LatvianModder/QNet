package com.latmod.mods.qnet.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author LatvianModder
 */
public class ItemBlockScreen extends ItemBlock
{
	public ItemBlockScreen(Block block)
	{
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("controller");
	}
}