package xyz.dashnetwork.status.utils;

import java.util.UUID;

public class GameProfile {

    private String name;
    private UUID uuid;

    public GameProfile(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public GameProfile(String name) {
        this.name = ChatColor.translate(name);
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

}
