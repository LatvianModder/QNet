package com.latmod.mods.qnet.device;

import com.latmod.mods.qnet.tile.TileControllerBasic;

/**
 * @author LatvianModder
 */
public class QNetInputDevice extends QNetDevice
{
	public QNetInputDevice(TileControllerBasic controller)
	{
		super(controller);
	}

	@Override
	public String getID()
	{
		return "in";
	}
}