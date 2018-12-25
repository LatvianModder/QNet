package com.latmod.mods.qnet.device;

import com.latmod.mods.qnet.tile.TileControllerBasic;

/**
 * @author LatvianModder
 */
public class QNetOutputDevice extends QNetDevice
{
	public QNetOutputDevice(TileControllerBasic controller)
	{
		super(controller);
	}

	@Override
	public String getID()
	{
		return "out";
	}
}