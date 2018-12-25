package com.latmod.mods.qnet.block;

import com.latmod.mods.qnet.QNetTier;
import com.latmod.mods.qnet.item.ItemDevice;
import com.latmod.mods.qnet.tile.TileControllerAdvanced;
import com.latmod.mods.qnet.tile.TileControllerBasic;
import com.latmod.mods.qnet.tile.TileControllerSupreme;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author LatvianModder
 */
public class BlockController extends BlockWithTier
{
	public BlockController(QNetTier t)
	{
		super(t, Material.IRON, MapColor.BLACK);
		setHardness(1F);
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		switch (tier)
		{
			case ADVANCED:
				return new TileControllerAdvanced();
			case SUPREME:
				return new TileControllerSupreme();
		}

		return new TileControllerBasic();
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tileEntity = world.getTileEntity(pos);

			if (tileEntity instanceof TileControllerBasic)
			{
				ItemStack stack = player.getHeldItem(hand);

				if (stack.getItem() instanceof ItemDevice || stack.getItem() instanceof ItemBlockScreen)
				{
					stack.setTagInfo("controller", new NBTTagLong(((TileControllerBasic) tileEntity).getID()));
					player.sendStatusMessage(new TextComponentTranslation("tile.qnet.basic_controller.linked"), true);
				}
				else
				{
					//TODO: Open GUI
				}
			}
		}

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		TileEntity tileEntity = world.getTileEntity(pos);

		if (tileEntity instanceof TileControllerBasic)
		{
			TileControllerBasic tile = (TileControllerBasic) tileEntity;
			//TODO: Read from stack
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		String maxConnections;
		String connectionRange;

		switch (tier)
		{
			case ADVANCED:
				maxConnections = "64";
				connectionRange = "128 m";
				break;
			case SUPREME:
				maxConnections = I18n.format("qnet.unlimited");
				connectionRange = I18n.format("qnet.unlimited");
				break;
			default:
				maxConnections = "8";
				connectionRange = "16 m";
		}

		tooltip.add(I18n.format("qnet.max_connections", maxConnections));
		tooltip.add(I18n.format("qnet.connection_range", connectionRange));
	}
}