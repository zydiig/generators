/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/generators
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.generators.modules.rfOutput

import net.bdew.generators.modules.BaseModule
import net.bdew.lib.multiblock.block.BlockOutput
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.{IBlockAccess, World}

object BlockRfOutput extends BaseModule("RFOutput", "PowerOutput", classOf[TileRfOutput]) with BlockOutput[TileRfOutput] {
  override def canConnectRedstone(world: IBlockAccess, pos: BlockPos, side: EnumFacing) = true

  override def rotateBlock(world: World, pos: BlockPos, axis: EnumFacing): Boolean = {
    val te = getTE(world, pos)
    if (te.getCore.isDefined) {
      te.forcedSides(axis) := !te.forcedSides(axis)
      te.doRescanFaces()
      true
    } else false
  }
}