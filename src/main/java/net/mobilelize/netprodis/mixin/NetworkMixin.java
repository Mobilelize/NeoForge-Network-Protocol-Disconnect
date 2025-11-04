package net.mobilelize.netprodis.mixin;

import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.Packet;
import net.mobilelize.netprodis.NetprodisLogger;
import net.mobilelize.netprodis.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonPacketListenerImpl.class)
public class NetworkMixin {
    @Inject(
            method = {"onPacketError"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void onPacketException(Packet npdPacket, Exception npdException, CallbackInfo ci) {
        if (!ConfigManager.configData.modEnabled) return;
        if (ConfigManager.configData.showLogs) {
            NetprodisLogger.LOGGER.warn("Strict error handling was triggered, but disconnection was prevented");
            NetprodisLogger.LOGGER.error("Failed to handle packet {}", npdPacket, npdException);
        }
        ci.cancel();
    }
}
