package dk.itspasser.test;

import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;


import java.util.List;

public class spy extends LabyModAddon {

    private boolean enabled = true;


    @Override
    public void onEnable() {
        this.getApi().registerForgeListener(this);
        System.out.println("Enable test addon!");



        this.getApi().getEventManager().register(new MessageReceiveEvent() {
            public boolean onReceive(final String s, String s1) {
                if (!enabled) {
                    return false;
                }
                if (s1.contains("spy")){
                    System.out.println("spy er i message");
                    LabyMod.getInstance().notifyMessageRaw("cmd SPY", s1);
                    return true;
                }
                if (s1.contains("Spy")){
                    System.out.println("spy er i message");
                    LabyMod.getInstance().notifyMessageRaw("cmd SPY", s1);
                    return true;
                }

                return false;
            }
        });



    }

    @Override
    public void loadConfig() {
        this.enabled = !this.getConfig().has("enabled") || this.getConfig().get("enabled").getAsBoolean();

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        final BooleanElement booleanElement = new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(final Boolean enabled) {
                spy.this.enabled = enabled;
                spy.this.getConfig().addProperty("enabled", enabled);
                spy.this.saveConfig();
            }
        }, this.enabled);

        list.add(booleanElement);
    }

}

















