package fr.alexking.elytrawarning.events;


import fr.alexking.elytrawarning.ElytraWarning;
import fr.alexking.elytrawarning.models.Title;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.stream.DoubleStream;


public class PlayerItemDamage implements Listener {

    Plugin plugin = ElytraWarning.getPlugin(ElytraWarning.class);
    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event){

        String itemType = event.getItem().getType().toString();

        if(itemType.equalsIgnoreCase("ELYTRA")){
            float configDurability = ((int) plugin.getConfig().get("durability"));

            int itemDurability = ((int) event.getItem().getData().getItemType().getMaxDurability());
            Damageable damageable = (Damageable) event.getItem().getItemMeta();
            int damage = damageable.getDamage();

            Boolean comparable = ( ( itemDurability * configDurability/100) >= (itemDurability - damage) );

            //event.getPlayer().sendMessage(message);


            if(event.getPlayer().isGliding()) {
                if(comparable ) {
                    // Slow and play sound to player
                    Vector playerVector = event.getPlayer().getLocation().getDirection();

                    boolean configAffectSpeed =((boolean) plugin.getConfig().get("affectSpeed"));
                    boolean configWarningGui = ((boolean) plugin.getConfig().get("warningGui"));
                    boolean configWarningSound = ((boolean) plugin.getConfig().get("warningSound"));
                    boolean configWarningTitle = ((boolean) plugin.getConfig().get("warningTitle"));
                    String configTitleColor = (String) plugin.getConfig().get("titleColor");
                    String configSubTitleColor1 = (String) plugin.getConfig().get("subTitleColor1");
                    String configSubTitleColor2 = (String) plugin.getConfig().get("subTitleColor2");
                    String elytraDurability =  ChatColor.valueOf(configSubTitleColor1)+"("+ ChatColor.valueOf(configSubTitleColor2) + (itemDurability-damage)+"/"+itemDurability+ChatColor.valueOf(configSubTitleColor1)+")";

                    //event.getPlayer().setVelocity(playerVector.multiply(0.2f));

                    if (configAffectSpeed) {
                        Location newLocation = event.getPlayer().getLocation();
                        double oldX = event.getPlayer().getLocation().getX();
                        double oldY = event.getPlayer().getLocation().getY();
                        double oldZ = event.getPlayer().getLocation().getZ();
                        Random rand = new Random();
                        float modifier = (float) ((rand.nextFloat() - 0.5) <= 0 ? (-1) : (1));
                        modifier = modifier * 5;
                        double dirX = oldX * modifier;
                        double dirY = oldY;
                        double dirZ = oldZ * modifier;
                        newLocation.add(dirX, dirY, dirZ);
                        Vector newVector = playerVector.add(newLocation.getDirection());
                        event.getPlayer().setVelocity(newVector.multiply(0.1f));

                    }
                    if(configWarningSound){
                        String configSound = (String) plugin.getConfig().get("sound");
                        float configVolume = (int) plugin.getConfig().get("volume");
                        float configPitch = (float)(double) plugin.getConfig().get("pitch");
                        Sound sound = Sound.valueOf(configSound);
                        Player player = event.getPlayer();
                        Location playerLocation = player.getLocation();
                        event.getPlayer().playSound(playerLocation, sound, configVolume, configPitch);
                    }
                    if(configWarningGui){
                        String message = ChatColor.valueOf(configSubTitleColor1)+"Elytra ("+ ChatColor.valueOf(configSubTitleColor2) + (itemDurability-damage)+"/"+itemDurability+ChatColor.valueOf(configSubTitleColor1)+")";
                        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    }

                    if(configWarningTitle){
                        String configWarningText = ((String) plugin.getConfig().get("warningText"));
                        int configFadeInTime = ((int) plugin.getConfig().get("fadeInTime"));
                        int configShowTime = ((int) plugin.getConfig().get("showTime"));
                        int configFadeOutTime = ((int) plugin.getConfig().get("fadeOutTime"));


                        event.getPlayer().sendTitle(ChatColor.getLastColors(configTitleColor)+configWarningText, elytraDurability,configFadeInTime, configShowTime, configFadeOutTime);

                        //event.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(configWarningText));

                    }

                }
            }
        }


    }
}
