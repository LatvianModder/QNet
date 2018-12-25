package com.latmod.mods.qnet.item;

import com.latmod.mods.qnet.QNetTier;
import net.minecraft.item.Item;

/**
 * @author LatvianModder
 */
public class ItemWithTier extends Item
{
	public final QNetTier tier;

	public ItemWithTier(QNetTier t)
	{
		tier = t;
	}
}