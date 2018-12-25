package com.latmod.mods.qnet.item;

import com.latmod.mods.qnet.QNetTier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author LatvianModder
 */
public class ItemDevice extends ItemWithTier
{
	public ItemDevice(QNetTier t)
	{
		super(t);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("controller");
	}
}