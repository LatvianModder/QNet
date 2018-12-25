package com.latmod.mods.qnet.device;

import com.latmod.mods.qnet.tile.TileControllerBasic;

/**
 * @author LatvianModder
 */
public class QNetStorageDevice extends QNetDevice
{
	public QNetStorageDevice(TileControllerBasic controller)
	{
		super(controller);
	}

	@Override
	public String getID()
	{
		return "storage";
	}
}