package com.latmod.mods.qnet.tile;

import com.latmod.mods.qnet.QNetTier;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author LatvianModder
 */
public class TileScreenSupreme extends TileScreenAdvanced
{
	@Override
	public QNetTier getTier()
	{
		return QNetTier.SUPREME;
	}

	@Override
	public void writeData(NBTTagCompound nbt)
	{
		super.writeData(nbt);
	}

	@Override
	public void readData(NBTTagCompound nbt)
	{
		super.readData(nbt);
	}
}