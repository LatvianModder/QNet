package com.latmod.mods.qnet.tile;

import com.latmod.mods.qnet.QNetTier;
import com.latmod.mods.qnet.device.QNetDevice;
import com.latmod.mods.qnet.device.QNetDeviceEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author LatvianModder
 */
public class TileControllerBasic extends TileQNet implements ITickable, IEnergyStorage
{
	public static final Random RANDOM = new Random();
	public static final ArrayList<TileControllerBasic> CLIENT_CONTROLLERS = new ArrayList<>();
	public static final ArrayList<TileControllerBasic> SERVER_CONTROLLERS = new ArrayList<>();

	@Nullable
	public static TileControllerBasic get(World world, long id)
	{
		for (TileControllerBasic controller : world.isRemote ? CLIENT_CONTROLLERS : SERVER_CONTROLLERS)
		{
			if (controller.uid == id && !controller.isInvalid())
			{
				return controller;
			}
		}

		return null;
	}

	private boolean isDirty = false;
	public int energy = 0;
	private long uid = 0L;
	public final List<QNetDevice> devices = new ArrayList<>();

	public long getID()
	{
		if (uid == 0)
		{
			uid = RANDOM.nextLong();
		}

		return uid;
	}

	@Override
	public void writeData(NBTTagCompound nbt)
	{
		super.writeData(nbt);
		nbt.setLong("uid", getID());
		nbt.setInteger("energy", energy);

		NBTTagList list = new NBTTagList();

		for (QNetDevice device : devices)
		{
			NBTTagCompound nbt1 = new NBTTagCompound();
			device.writeData(nbt1);
			nbt1.setString("id", device.getID());
			nbt1.setIntArray("data", new int[] {device.getTier().ordinal(), device.pos.getX(), device.pos.getY(), device.pos.getZ(), device.rotation.getIndex()});
			list.appendTag(nbt1);
		}

		nbt.setTag("devices", list);
	}

	@Override
	public void readData(NBTTagCompound nbt)
	{
		super.readData(nbt);
		uid = nbt.getLong("uid");
		energy = nbt.getInteger("energy");

		devices.clear();
		NBTTagList list = nbt.getTagList("devices", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt1 = list.getCompoundTagAt(i);
			int[] data = nbt1.getIntArray("data");

			if (data.length >= 5)
			{
				QNetTier tier = QNetTier.VALUES[MathHelper.clamp(data[0], 0, QNetTier.VALUES.length - 1)];
				QNetDevice device = QNetDeviceEntry.create(nbt1.getString("id"), this, tier);

				if (device != null)
				{
					device.pos = new BlockPos(data[1], data[2], data[3]);
					device.rotation = EnumFacing.byIndex(data[4]);
					device.readData(nbt1);
					devices.add(device);
				}
			}
		}
	}

	@Override
	public void onLoad()
	{
		super.onLoad();

		if (world != null)
		{
			if (world.isRemote)
			{
				CLIENT_CONTROLLERS.add(this);
			}
			else
			{
				SERVER_CONTROLLERS.add(this);
			}
		}
	}

	@Override
	public void invalidate()
	{
		if (world != null)
		{
			if (world.isRemote)
			{
				CLIENT_CONTROLLERS.remove(this);
			}
			else
			{
				SERVER_CONTROLLERS.remove(this);
			}
		}

		super.invalidate();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side)
	{
		return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, side);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side)
	{
		return capability == CapabilityEnergy.ENERGY ? (T) this : super.getCapability(capability, side);
	}

	@Override
	public void update()
	{
		if (isDirty)
		{
			super.markDirty();
			isDirty = false;
		}
	}

	@Override
	public void markDirty()
	{
		isDirty = true;
	}

	@Override
	public int receiveEnergy(int amount, boolean simulate)
	{
		int e = Math.min(getMaxEnergyStored() - energy, amount);

		if (!simulate)
		{
			energy += e;
			markDirty();
		}

		return e;
	}

	@Override
	public int extractEnergy(int amount, boolean simulate)
	{
		int e = Math.min(energy, amount);

		if (!simulate)
		{
			energy -= e;
			markDirty();
		}

		return e;
	}

	@Override
	public int getEnergyStored()
	{
		return energy;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return 50000;
	}

	@Override
	public boolean canExtract()
	{
		return true;
	}

	@Override
	public boolean canReceive()
	{
		return true;
	}

	public String getName()
	{
		return String.format("#%016X", getID());
	}
}