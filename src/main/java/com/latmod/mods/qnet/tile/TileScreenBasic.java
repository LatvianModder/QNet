package com.latmod.mods.qnet.tile;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
public class TileScreenBasic extends TileQNet
{
	private long controllerID = 0L;

	@Override
	public void writeData(NBTTagCompound nbt)
	{
		super.writeData(nbt);
		nbt.setLong("controller", controllerID);
	}

	@Override
	public void readData(NBTTagCompound nbt)
	{
		super.readData(nbt);
		controllerID = nbt.getLong("controller");
	}

	public void setController(long id)
	{
		controllerID = id;
	}

	@Nullable
	public TileControllerBasic getController()
	{
		return world == null || controllerID == 0 ? null : TileControllerBasic.get(world, controllerID);
	}
}