/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/generators
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.generators.controllers.exchanger

import net.bdew.generators.Textures
import net.bdew.lib.gui.Texture
import net.bdew.lib.multiblock.data.SlotSet

object OutputSlotsExchanger extends SlotSet("fluidslots_exchanger") {
  val BOTH = Slot("BOTH", "advgenerators.exchanger.output.both", Texture(Textures.sheet, 117, 35, 14, 14))
  val COLD = Slot("COLD", "advgenerators.exchanger.output.cold", Texture(Textures.sheet, 133, 35, 14, 14))
  val HOT = Slot("HOT", "advgenerators.exchanger.output.hot", Texture(Textures.sheet, 101, 35, 14, 14))
  override def default = BOTH
  override val order = Map(BOTH -> COLD, COLD -> HOT, HOT -> BOTH)
}
