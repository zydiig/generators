/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/generators
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.generators.compat

import java.util

import cpw.mods.fml.common.versioning.VersionParser
import cpw.mods.fml.common.{Loader, ModAPIManager, ModContainer}
import net.bdew.generators.Generators
import net.bdew.generators.config.Tuning

object PowerProxy {
  final val IC2_MOD_ID = "IC2"
  final val TE_MOD_ID = "CoFHAPI"

  lazy val EUEnabled = Tuning.getSection("Power").getSection("EU").getBoolean("Enabled")
  lazy val RFEnabled = Tuning.getSection("Power").getSection("RF").getBoolean("Enabled")

  lazy val lookup: collection.Map[String, ModContainer] = {
    val mods = new util.ArrayList[ModContainer]
    val nameLookup = new util.HashMap[String, ModContainer]

    nameLookup.putAll(Loader.instance().getIndexedModList)
    ModAPIManager.INSTANCE.injectAPIModContainers(mods, nameLookup)

    import scala.collection.JavaConverters._
    nameLookup.asScala
  }

  lazy val haveIC2 = haveModVersion(IC2_MOD_ID)
  lazy val haveTE = haveModVersion(TE_MOD_ID)
  lazy val haveBCFuel = haveModVersion("BuildCraftAPI|fuels@[2.0,)")
  lazy val haveMekanismGasApi = haveModVersion("MekanismAPI|gas@[8.0.0,)")


  def haveModVersion(modId: String) = {
    val spec = VersionParser.parseVersionReference(modId)
    lookup.contains(spec.getLabel) && spec.containsVersion(lookup(spec.getLabel).getProcessedVersion)
  }

  def getModVersion(modId: String): String = {
    val cont = lookup.getOrElse(modId, return "NOT FOUND")
    cont.getModId + " " + cont.getVersion
  }

  def logModVersions() {
    if (!haveIC2 && !haveTE) {
      Generators.logWarn("No useable energy system detected")
      Generators.logWarn("This mod requires at least one of the following mods to function properly:")
      Generators.logWarn("* CoFHCore (or any mod that includes the API)")
      Generators.logWarn("* IC2 Experimental")
    }
    Generators.logInfo("IC2 Version: %s", getModVersion(IC2_MOD_ID))
    Generators.logInfo("RF Version: %s", getModVersion(TE_MOD_ID))
  }
}
