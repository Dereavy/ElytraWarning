package fr.alexking.elytrawarning.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerMove implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){

            if(event.getPlayer().isGliding()) {
                // Slow and play sound to player
                /*
                event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(0.2f));
                Sound sound = Sound.valueOf("BLOCK_COMPOSTER_FILL");
                float volume = (float) 10;
                float pitch = (float) 0.1;
                Player player = event.getPlayer();
                Location playerLocation = player.getLocation();
                event.getPlayer().playSound(playerLocation, sound, volume, pitch);
                */
            }
    }
}