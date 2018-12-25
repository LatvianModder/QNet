package com.latmod.mods.qnet.tile;

import com.latmod.mods.qnet.QNetTier;

/**
 * @author LatvianModder
 */
public class TileControllerAdvanced extends TileControllerBasic
{
	@Override
	public QNetTier getTier()
	{
		return QNetTier.ADVANCED;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return 300000;
	}
}