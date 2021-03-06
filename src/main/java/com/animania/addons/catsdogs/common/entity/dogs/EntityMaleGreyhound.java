package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleGreyhound extends EntityMaleDogBase
{

	public EntityMaleGreyhound(World world)
	{
		super(world);
		this.type = DogType.GREYHOUND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -7578572;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -15987708;
	}
}
