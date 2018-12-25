package com.latmod.mods.qnet.device;

import com.latmod.mods.qnet.QNetTier;
import com.latmod.mods.qnet.tile.TileControllerBasic;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * @author LatvianModder
 */
public final class QNetDeviceEntry
{
	public interface QNetDeviceProvider
	{
		QNetDevice create(TileControllerBasic controller);
	}

	private static final HashMap<String, QNetDeviceEntry> DEVICES = new HashMap<>();

	public static void register(String id, QNetDeviceProvider basic, QNetDeviceProvider advanced, QNetDeviceProvider supreme)
	{
		QNetDeviceEntry entry = new QNetDeviceEntry();
		entry.functions.put(QNetTier.BASIC, basic);
		entry.functions.put(QNetTier.ADVANCED, advanced);
		entry.functions.put(QNetTier.SUPREME, supreme);
		DEVICES.put(id, entry);
	}

	@Nullable
	public static QNetDevice create(String id, TileControllerBasic controller, QNetTier tier)
	{
		QNetDeviceEntry entry = DEVICES.get(id);

		if (entry == null)
		{
			return null;
		}

		QNetDeviceProvider f = entry.functions.get(tier);
		return f == null ? null : f.create(controller);
	}

	private final HashMap<QNetTier, QNetDeviceProvider> functions;

	private QNetDeviceEntry()
	{
		functions = new HashMap<>();
	}
}