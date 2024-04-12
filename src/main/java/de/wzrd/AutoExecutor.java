package de.wzrd;

import de.wzrd.module.AutoExec;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class AutoExecutor extends MeteorAddon {


    @Override
    public void onInitialize() {
        Modules.get().add(new AutoExec());
    }

    @Override
    public String getPackage() {
        return "de.wzrd";
    }
}
