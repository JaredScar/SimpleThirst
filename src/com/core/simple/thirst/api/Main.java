package com.core.simple.thirst.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created with IntelliJ IDEA.
 * User: MayoDwarf
 * Date: 6/30/14
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JavaPlugin implements Listener {
    private FileConfiguration settings;
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.settings = this.getConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        int deThirst = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if(Bukkit.getOnlinePlayers().length >= 1) {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(!(p.getGameMode() == GameMode.CREATIVE) && !p.hasPermission("SimpleThirst.bypass")) {
                    if(p.getLevel() >= 1) {
                        p.setLevel(p.getLevel() - 1);
                    } else {
                        p.setLevel(0);
                        PotionEffect pe = new PotionEffect(PotionEffectType.getByName(settings.getString("Potion.PotionEffectTypeByNameAddedOnZeroThirst")), settings.getInt("Potion.Duration")*20, settings.getInt("Potion.Amplifier"));
                        p.addPotionEffect(pe);
                        p.damage(settings.getDouble("DamagePerZeroThirst"));
                    }
                    if(p.getLevel() == 25) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', settings.getString("Warning.TwentyFiveThirstMessage")));
                    }
                    if(p.getLevel() == 5) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', settings.getString("Warnings.FiveThirstLeft")));
                        }
                    if(p.getLevel() == 1) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', settings.getString("Warnings.OneThirstLeft")));
                            }
                        }
                    }
                }
            }
        }, 0, 20L*this.settings.getLong("Time"));
    }
    public void onDisable() {}
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if(e.getItem() !=null) {
            if(e.getItem().getType() == Material.POTION) {
                p.setLevel(this.settings.getInt("Level"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.settings.getString("Warnings.ResetLevel")));
            }
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()) {
            p.setLevel(this.settings.getInt("Level"));
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.setLevel(this.settings.getInt("Level"));
        p.sendMessage(this.settings.getString("Warnings.ResetLevel"));
    }
}
