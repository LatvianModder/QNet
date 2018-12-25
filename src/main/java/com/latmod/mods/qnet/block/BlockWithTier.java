package com.latmod.mods.qnet.block;

import com.latmod.mods.qnet.QNetTier;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * @author LatvianModder
 */
public class BlockWithTier extends Block
{
	public final QNetTier tier;

	public BlockWithTier(QNetTier t, Material material, MapColor mapColor)
	{
		super(material, mapColor);
		tier = t;
	}
}