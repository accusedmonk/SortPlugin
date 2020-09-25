package com.simplyapps.sortplugin;

import com.simplyapps.sortplugin.sorter.Sorter;
import org.bukkit.entity.Player;

public class SortManager {
  private static Sorter sorter = new Sorter();

  public SortManager() {}

  public static void sortPlayerInventory(Player player) {
    player
      .getInventory()
      .setContents(
        sorter.sortPlayerInventory(player.getInventory().getContents())
      );
  }

  public static void sortChestInventory(Player player) {
    player
      .getOpenInventory()
      .getTopInventory()
      .setContents(
        sorter.sortChestInventory(
          player.getOpenInventory().getTopInventory().getContents()
        )
      );
  }
}
