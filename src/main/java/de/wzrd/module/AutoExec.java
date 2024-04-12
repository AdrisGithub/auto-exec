package de.wzrd.module;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.StringListSetting;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

import java.util.List;
import java.util.Objects;

public class AutoExec extends Module {

    private final Setting<Integer> delay = settings.getDefaultGroup().add(new IntSetting.Builder()
        .min(10)
        .max(10000)
        .defaultValue(1000)
        .sliderMax(10000)
        .sliderMin(10)
        .name("Delay")
        .description("Delay between all Commands")
        .build()
    );

    private final Setting<Integer> commandDelay = settings.getDefaultGroup().add(new IntSetting.Builder()
        .min(10)
        .max(10000)
        .defaultValue(1000)
        .sliderMax(10000)
        .sliderMin(10)
        .name("individual Delay")
        .description("Delay between individual Commands")
        .build()
    );

    private Integer counter = 0;

    private final Setting<List<String>> commands = settings.getDefaultGroup().add(new StringListSetting.Builder()
        .name("Commands")
        .description("Commands that can be executed")
        .build()
    );

    public AutoExec() {
        super(Categories.Misc, "auto-exec", "Executes Commands");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (!Objects.equals(delay.get(), counter)) {
            counter++;
            return;
        }
        counter = 0;
        if (mc == null || mc.player == null) {
            return;
        }
        for (final String command : commands.get()) {
            if (command.charAt(0) != '/') {
                sleep();
                mc.player.networkHandler.sendChatCommand(command);
            }
        }

    }

    private void sleep() {
        try {
            Thread.sleep(commandDelay.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
