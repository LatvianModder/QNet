package com.latmod.mods.qnet.tile;

import com.latmod.mods.qnet.QNetTier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

/**
 * @author LatvianModder
 */
public class TileScreenAdvanced extends TileScreenBasic
{
	public final InventoryCrafting craftingGrid = new InventoryCrafting(new Container()
	{
		@Override
		public boolean canInteractWith(EntityPlayer player)
		{
			return true;
		}

		@Override
		public void onCraftMatrixChanged(IInventory inventory)
		{
			markDirty();
		}
	}, 3, 3);

	public final InventoryCraftResult craftingResult = new InventoryCraftResult();

	@Override
	public QNetTier getTier()
	{
		return QNetTier.ADVANCED;
	}

	@Override
	public void writeData(NBTTagCompound nbt)
	{
		super.writeData(nbt);

		if (!craftingGrid.isEmpty())
		{
			NBTTagList list = new NBTTagList();

			for (int i = 0; i < craftingGrid.getSizeInventory(); i++)
			{
				ItemStack stack = craftingGrid.getStackInSlot(i);
				list.appendTag(stack.isEmpty() ? new NBTTagCompound() : stack.serializeNBT());
			}

			nbt.setTag("crafting_grid", list);
		}
	}

	@Override
	public void readData(NBTTagCompound nbt)
	{
		super.readData(nbt);

		craftingGrid.clear();
		craftingResult.clear();
		NBTTagList list = nbt.getTagList("crafting_grid", Constants.NBT.TAG_COMPOUND);

		if (list.tagCount() == craftingGrid.getSizeInventory())
		{
			for (int i = 0; i < craftingGrid.getSizeInventory(); i++)
			{
				NBTTagCompound nbt1 = list.getCompoundTagAt(i);

				if (!nbt1.isEmpty())
				{
					ItemStack stack = new ItemStack(nbt1);

					if (!stack.isEmpty())
					{
						craftingGrid.setInventorySlotContents(i, stack);
					}
				}
			}
		}
	}

	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
	}
}