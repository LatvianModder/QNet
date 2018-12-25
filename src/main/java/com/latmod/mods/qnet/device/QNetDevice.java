package com.latmod.mods.qnet.device;

import com.latmod.mods.qnet.QNetTier;
import com.latmod.mods.qnet.tile.TileControllerBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * @author LatvianModder
 */
public abstract class QNetDevice
{
	public final TileControllerBasic controller;
	public BlockPos pos;
	public EnumFacing rotation;

	public QNetDevice(TileControllerBasic c)
	{
		controller = c;
	}

	public abstract String getID();

	public QNetTier getTier()
	{
		return QNetTier.BASIC;
	}

	public void writeData(NBTTagCompound nbt)
	{
	}

	public void readData(NBTTagCompound nbt)
	{
	}
}