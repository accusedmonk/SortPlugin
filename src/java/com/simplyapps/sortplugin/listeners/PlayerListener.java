package com.simplyapps.sortplugin.listeners;

import com.simplyapps.sortplugin.SortManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerListener implements Listener {

  // Even though updateInventory() is indicated as deprecated, that is not true according to the Spigot Documentation - 1/26/19
  @EventHandler
  private void onPlayerClick(InventoryClickEvent e) {
    Player player = (Player) e.getWhoClicked();
    ClickType clicktype = e.getClick();

    if (clicktype == ClickType.MIDDLE) {
      if (e.getClickedInventory() == null) return;

      if (e.getClickedInventory().getType() == InventoryType.CHEST) {
        SortManager.sortChestInventory(player);

        player.updateInventory();
      } else if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
        SortManager.sortPlayerInventory(player);

        player.updateInventory();
      }
    }
  }
}
