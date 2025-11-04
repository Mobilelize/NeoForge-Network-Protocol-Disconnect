package net.mobilelize.netprodis.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigMenu {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Network Protocol Disconnect"))
                .setTransparentBackground(true)
                .setSavingRunnable(ConfigMenu::saveConfig);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory global = builder.getOrCreateCategory(Component.literal("Global"));

        global.addEntry(entryBuilder.startBooleanToggle(Component.literal("Stop Network Protocol Errors"), ConfigManager.configData.modEnabled)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Enables the mod."))
                .setSaveConsumer(newValue -> ConfigManager.configData.modEnabled = newValue)
                .build());

        global.addEntry(entryBuilder.startBooleanToggle(Component.literal("Output Logs"), ConfigManager.configData.showLogs)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Sets if it should log anything."))
                .setSaveConsumer(newValue -> ConfigManager.configData.showLogs = newValue)
                .build());

        return builder.build();
    }

    public static void saveConfig(){
        ConfigManager.saveConfig();
    }
}
