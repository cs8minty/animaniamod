package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyChihuahua extends EntityPuppyBase
{

	public EntityPuppyChihuahua(World world)
	{
		super(world);
		this.type = DogType.CHIHUAHUA;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -593428;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -16382716;
	}
}