package com.latmod.mods.qnet.tile;

import com.latmod.mods.qnet.QNetTier;

/**
 * @author LatvianModder
 */
public class TileControllerSupreme extends TileControllerAdvanced
{
	@Override
	public QNetTier getTier()
	{
		return QNetTier.SUPREME;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return 4000000;
	}
}