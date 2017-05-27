package com.animania.common.entities.chickens.ai;

import java.util.Random;

import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityAIFindWater extends EntityAIBase
{
    private final EntityCreature temptedEntity;
    private final double         speed;
    private double               targetX;
    private double               targetY;
    private double               targetZ;
    private double               pitch;
    private double               yaw;
    private EntityPlayer         temptingPlayer;
    private int                  delayTemptCounter;
    private boolean              isRunning;

    public EntityAIFindWater(EntityCreature temptedEntityIn, double speedIn) {
        this.temptedEntity = temptedEntityIn;
        this.speed = speedIn;
        this.setMutexBits(3);
        this.delayTemptCounter = 0;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {

    	delayTemptCounter++;
		if (this.delayTemptCounter < 40) {
			return false;
		} else if (delayTemptCounter > 40) {
			if (this.temptedEntity instanceof EntityChickLeghorn) {
				EntityChickLeghorn entity = (EntityChickLeghorn) temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityChickOrpington) {
				EntityChickOrpington entity = (EntityChickOrpington)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityChickPlymouthRock) {
				EntityChickPlymouthRock entity = (EntityChickPlymouthRock)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityChickRhodeIslandRed) {
				EntityChickRhodeIslandRed entity = (EntityChickRhodeIslandRed)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityChickWyandotte) {
				EntityChickWyandotte entity = (EntityChickWyandotte)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityHenLeghorn) {
				EntityHenLeghorn entity = (EntityHenLeghorn)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityHenOrpington) {
				EntityHenOrpington entity = (EntityHenOrpington)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityHenPlymouthRock) {
				EntityHenPlymouthRock entity = (EntityHenPlymouthRock)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityHenRhodeIslandRed) {
				EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityHenWyandotte) {
				EntityHenWyandotte entity = (EntityHenWyandotte)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterLeghorn) {
				EntityRoosterLeghorn entity = (EntityRoosterLeghorn)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterOrpington) {
				EntityRoosterOrpington entity = (EntityRoosterOrpington)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterPlymouthRock) {
				EntityRoosterPlymouthRock entity = (EntityRoosterPlymouthRock)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterRhodeIslandRed) {
				EntityRoosterRhodeIslandRed entity = (EntityRoosterRhodeIslandRed)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterWyandotte) {
				EntityRoosterWyandotte entity = (EntityRoosterWyandotte)temptedEntity;
				if (entity.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			}

            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();

            Biome biomegenbase = this.temptedEntity.world
                    .getBiome(new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ));

            if (poschk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

                if (this.temptedEntity instanceof EntityChickLeghorn) {
                    EntityChickLeghorn entity = (EntityChickLeghorn) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityChickOrpington) {
                    EntityChickOrpington entity = (EntityChickOrpington) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityChickPlymouthRock) {
                    EntityChickPlymouthRock entity = (EntityChickPlymouthRock) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityChickRhodeIslandRed) {
                    EntityChickRhodeIslandRed entity = (EntityChickRhodeIslandRed) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityChickWyandotte) {
                    EntityChickWyandotte entity = (EntityChickWyandotte) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHenLeghorn) {
                    EntityHenLeghorn entity = (EntityHenLeghorn) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHenOrpington) {
                    EntityHenOrpington entity = (EntityHenOrpington) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHenPlymouthRock) {
                    EntityHenPlymouthRock entity = (EntityHenPlymouthRock) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHenRhodeIslandRed) {
                    EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHenWyandotte) {
                    EntityHenWyandotte entity = (EntityHenWyandotte) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityRoosterLeghorn) {
                    EntityRoosterLeghorn entity = (EntityRoosterLeghorn) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityRoosterOrpington) {
                    EntityRoosterOrpington entity = (EntityRoosterOrpington) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityRoosterPlymouthRock) {
                    EntityRoosterPlymouthRock entity = (EntityRoosterPlymouthRock) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityRoosterRhodeIslandRed) {
                    EntityRoosterRhodeIslandRed entity = (EntityRoosterRhodeIslandRed) this.temptedEntity;
                    entity.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityRoosterWyandotte) {
                    EntityRoosterWyandotte entity = (EntityRoosterWyandotte) this.temptedEntity;
                    entity.setWatered(true);
                }

                return false;

            }
            else if (poschk == BlockHandler.blockTrough) {

                TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(currentpos);

                if (te != null && (te.getTroughType() == 3 || te.getTroughType() == 2 || te.getTroughType() == 1)) {
                    if (this.temptedEntity instanceof EntityChickLeghorn) {
                        EntityChickLeghorn entity = (EntityChickLeghorn) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityChickOrpington) {
                        EntityChickOrpington entity = (EntityChickOrpington) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityChickPlymouthRock) {
                        EntityChickPlymouthRock entity = (EntityChickPlymouthRock) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityChickRhodeIslandRed) {
                        EntityChickRhodeIslandRed entity = (EntityChickRhodeIslandRed) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityChickWyandotte) {
                        EntityChickWyandotte entity = (EntityChickWyandotte) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHenLeghorn) {
                        EntityHenLeghorn entity = (EntityHenLeghorn) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHenOrpington) {
                        EntityHenOrpington entity = (EntityHenOrpington) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHenPlymouthRock) {
                        EntityHenPlymouthRock entity = (EntityHenPlymouthRock) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHenRhodeIslandRed) {
                        EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHenWyandotte) {
                        EntityHenWyandotte entity = (EntityHenWyandotte) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityRoosterLeghorn) {
                        EntityRoosterLeghorn entity = (EntityRoosterLeghorn) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityRoosterOrpington) {
                        EntityRoosterOrpington entity = (EntityRoosterOrpington) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityRoosterPlymouthRock) {
                        EntityRoosterPlymouthRock entity = (EntityRoosterPlymouthRock) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityRoosterRhodeIslandRed) {
                        EntityRoosterRhodeIslandRed entity = (EntityRoosterRhodeIslandRed) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityRoosterWyandotte) {
                        EntityRoosterWyandotte entity = (EntityRoosterWyandotte) this.temptedEntity;
                        entity.setWatered(true);
                    }
                    return false;
                }
            }

            double x = this.temptedEntity.posX;
            double y = this.temptedEntity.posY;
            double z = this.temptedEntity.posZ;

            boolean waterFound = false;
            Random rand = new Random();

            BlockPos pos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();
                        if (blockchk == Blocks.WATER) {
                            waterFound = true;

                            if (rand.nextInt(20) == 0) {
                                this.delayTemptCounter = 0;
                                this.resetTask();
                                return false;
                            }
                            else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
                                    && this.temptedEntity.motionZ == 0) {
                                this.delayTemptCounter = 0;
                                this.resetTask();
                                return false;
                            }
                            else
                                return true;

                        }
                        else if (blockchk == BlockHandler.blockTrough) {
                            TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
                            if (te != null && (te.getTroughType() == 1 || te.getTroughType() == 2 || te.getTroughType() == 3)) {
                                waterFound = true;

                                if (rand.nextInt(20) == 0) {
                                    this.delayTemptCounter = 0;
                                    this.resetTask();
                                    return false;
                                }
                                else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
                                        && this.temptedEntity.motionZ == 0) {
                                    this.delayTemptCounter = 0;
                                    this.resetTask();
                                    return false;
                                }
                                else
                                    return true;
                            }
                        }

                    }

            if (!waterFound) {
                this.delayTemptCounter = 0;
                this.resetTask();
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean continueExecuting() {

        return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.isRunning = true;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.isRunning = false;
    }

    @Override
    public void updateTask() {

        double x = this.temptedEntity.posX;
        double y = this.temptedEntity.posY;
        double z = this.temptedEntity.posZ;

        BlockPos currentpos = new BlockPos(x, y, z);
        Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
        if (poschk != Blocks.WATER) {

            boolean waterFound = false;
            int loc = 24;
            int newloc = 24;
            BlockPos pos = new BlockPos(x, y, z);
            BlockPos waterPos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        Biome biomegenbase = this.temptedEntity.world.getBiome(pos);

                        if (blockchk == BlockHandler.blockTrough) {
                            TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
                            if (te != null && (te.getTroughType() == 1 || te.getTroughType() == 2 || te.getTroughType() == 3)) {
                                waterFound = true;
                                newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                                if (newloc < loc) {

                                    loc = newloc;

                                    if (this.temptedEntity.posX < waterPos.getX()) {
                                        BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                        Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                        if (waterBlockchk == BlockHandler.blockTrough)
                                            i = i + 1;
                                    }

                                    if (this.temptedEntity.posZ < waterPos.getZ()) {
                                        BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                        Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                        if (waterBlockchk == BlockHandler.blockTrough)
                                            k = k + 1;
                                    }

                                    waterPos = new BlockPos(x + i, y + j, z + k);

                                }

                            }
                        }

                        if (blockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
                                && !BiomeDictionary.hasType(biomegenbase, Type.BEACH) && !this.temptedEntity.hasPath()) {
                            waterFound = true;
                            newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                            if (newloc < loc) {

                                loc = newloc;

                                if (this.temptedEntity.posX < waterPos.getX()) {
                                    BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                    Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                    if (waterBlockchk == Blocks.WATER)
                                        i = i + 1;
                                }

                                if (this.temptedEntity.posZ < waterPos.getZ()) {
                                    BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                    Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                    if (waterBlockchk == Blocks.WATER)
                                        k = k + 1;
                                }

                                waterPos = new BlockPos(x + i, y + j, z + k);

                            }

                        }

                    }

            if (waterFound) {

                Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPos).getBlock();
                Biome biomegenbase = this.temptedEntity.world.getBiome(waterPos);

                if (waterBlockchk == BlockHandler.blockTrough) {
                    if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
                        this.resetTask();
                    else
                        this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);

                }
                else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
                        && !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
                    if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
                        this.resetTask();
                    else
                        this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
            }
        }

    }

    public boolean isRunning() {
        return this.isRunning;
    }
}