package com.latmod.mods.qnet.block;

import com.latmod.mods.qnet.QNetTier;
import com.latmod.mods.qnet.tile.TileControllerBasic;
import com.latmod.mods.qnet.tile.TileScreenAdvanced;
import com.latmod.mods.qnet.tile.TileScreenBasic;
import com.latmod.mods.qnet.tile.TileScreenSupreme;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author LatvianModder
 */
public class BlockScreen extends BlockWithTier
{
	private static final AxisAlignedBB[] AABBS = new AxisAlignedBB[] {
			new AxisAlignedBB(0.125D, 0D, 0.125D, 0.875D, 0.125D, 0.875D),
			new AxisAlignedBB(0.125D, 0.875D, 0.125D, 0.875D, 1D, 0.875D),
			new AxisAlignedBB(0.125D, 0.125D, 0D, 0.875D, 0.875D, 0.125D),
			new AxisAlignedBB(0.125D, 0.125D, 0.875D, 0.875D, 0.875D, 1D),
			new AxisAlignedBB(0D, 0.125D, 0.125D, 0.125D, 0.875D, 0.875D),
			new AxisAlignedBB(0.875D, 0.125D, 0.125D, 1D, 0.875D, 0.875D)
	};

	public BlockScreen(QNetTier t)
	{
		super(t, Material.IRON, MapColor.BLACK);
		setDefaultState(blockState.getBaseState().withProperty(BlockDirectional.FACING, EnumFacing.NORTH));
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
				return new TileScreenAdvanced();
			case SUPREME:
				return new TileScreenSupreme();
		}

		return new TileScreenBasic();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BlockDirectional.FACING);
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(BlockDirectional.FACING).getIndex();
	}

	@Override
	@Nullable
	@Deprecated
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return null;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		return true;
	}

	@Override
	@Deprecated
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return getDefaultState().withProperty(BlockDirectional.FACING, facing.getOpposite());
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABBS[state.getValue(BlockDirectional.FACING).getIndex()];
	}

	@Override
	@Deprecated
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(BlockDirectional.FACING, rot.rotate(state.getValue(BlockDirectional.FACING)));
	}

	@Override
	@Deprecated
	public IBlockState withMirror(IBlockState state, Mirror mirror)
	{
		return state.withRotation(mirror.toRotation(state.getValue(BlockDirectional.FACING)));
	}

	@Override
	@Deprecated
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tileEntity = world.getTileEntity(pos);

			if (tileEntity instanceof TileScreenBasic)
			{
				TileControllerBasic controller = ((TileScreenBasic) tileEntity).getController();
				player.sendStatusMessage(new TextComponentString(controller == null ? "-" : controller.getName()), true);

				if (tileEntity instanceof TileScreenAdvanced)
				{
					player.displayGUIChest(((TileScreenAdvanced) tileEntity).craftingGrid);
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

		if (tileEntity instanceof TileScreenBasic)
		{
			TileScreenBasic tile = (TileScreenBasic) tileEntity;

			if (stack.hasTagCompound())
			{
				tile.setController(stack.getTagCompound().getLong("controller"));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);
	}
}