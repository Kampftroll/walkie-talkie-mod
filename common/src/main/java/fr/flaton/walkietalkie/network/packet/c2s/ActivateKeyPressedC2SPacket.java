package fr.flaton.walkietalkie.network.packet.c2s;

import dev.architectury.networking.NetworkManager;
import fr.flaton.walkietalkie.ModSoundEvents;
import fr.flaton.walkietalkie.Util;
import fr.flaton.walkietalkie.item.WalkieTalkieItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;

public class ActivateKeyPressedC2SPacket {
    public static void receive(PacketByteBuf packetByteBuf, NetworkManager.PacketContext packetContext) {
        ServerPlayerEntity player = (ServerPlayerEntity) packetContext.getPlayer();

        ItemStack stack = Util.getOptimalWalkieTalkieRange(player);
        if (stack == null) {
            return;
        }

        boolean activated = stack.getNbt().getBoolean(WalkieTalkieItem.NBT_KEY_ACTIVATE);

        stack.getNbt().putBoolean(WalkieTalkieItem.NBT_KEY_ACTIVATE, !activated);


        player.playSound(
                activated ? ModSoundEvents.OFF_SOUND_EVENT.get() : ModSoundEvents.ON_SOUND_EVENT.get(),
                SoundCategory.PLAYERS,
                0.5f,
                1f
        );
    }
}
