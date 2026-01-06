package net.Fataled.hspemojis;

import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomSounds {
    private static final String modID = "hspemojis";
    private static final Logger LOGGER = LogManager.getLogger();
    private CustomSounds() {}

    // Register a custom Sound
    public static SoundEvent registerSound(String id){
        Identifier identifier = Identifier.of(modID, id);
        SoundEvent event = SoundEvent.of(identifier);
        return Registry.register(Registries.SOUND_EVENT, identifier, event);
    }

    public static void initialize(){
        CustomSounds.LOGGER.info("Registering " + CustomSounds.modID + " Sounds");
    }

}
