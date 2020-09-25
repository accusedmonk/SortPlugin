package com.simplyapps.sortplugin;

import com.simplyapps.sortplugin.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SortPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
  }

  @Override
  public void onDisable() {}
}
