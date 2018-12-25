package com.latmod.mods.qnet;

import net.minecraft.util.IStringSerializable;

/**
 * @author LatvianModder
 */
public enum QNetTier implements IStringSerializable
{
	BASIC("basic"),
	ADVANCED("advanced"),
	SUPREME("supreme");

	public static final QNetTier[] VALUES = values();

	private final String name;

	QNetTier(String s)
	{
		name = s;
	}

	@Override
	public String getName()
	{
		return name;
	}
}