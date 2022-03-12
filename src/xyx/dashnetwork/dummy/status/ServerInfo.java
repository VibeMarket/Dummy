package xyx.dashnetwork.dummy.status;

import com.google.gson.stream.JsonWriter;
import xyx.dashnetwork.dummy.status.utils.ChatColor;
import xyx.dashnetwork.dummy.status.utils.GameProfile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ServerInfo {

    private BufferedImage favicon;
    private List<GameProfile> players;
    private String description, software;
    private int online, max, protocol;

    public ServerInfo() {
        favicon = null;
        players = new ArrayList<>();
        description = "";
        software = "";
        online = 0;
        max = 0;
        protocol = 0;
    }

    public void setFavicon(BufferedImage favicon) {
        this.favicon = favicon;
    }

    public void addPlayer(GameProfile profile) {
        players.add(profile);
    }

    public void setDescription(String description) {
        this.description = ChatColor.translate(description);
    }

    public void setSoftware(String software) {
        this.software = ChatColor.translate(software);
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String buildJson() {
        try {
            StringWriter writer = new StringWriter();
            JsonWriter json = new JsonWriter(writer);

            json.setLenient(false);

            json.beginObject();

            json.name("version");
            json.beginObject();

            json.name("name").value(software);
            json.name("protocol").value(protocol);

            json.endObject();

            json.name("players");
            json.beginObject();

            json.name("max").value(max);
            json.name("online").value(online);

            if (players.size() > 0) {
                json.name("sample");
                json.beginArray();

                for (GameProfile profile : players) {
                    json.beginObject();
                    json.name("name").value(profile.getName());
                    json.name("id").value(profile.getUuid().toString());
                    json.endObject();
                }

                json.endArray();
            }

            json.endObject();

            json.name("description");
            json.beginObject();

            json.name("text").value(description);

            json.endObject();

            if (favicon != null) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write(favicon, "PNG", output);

                String base64 = Base64.getEncoder().encodeToString(output.toByteArray());

                json.name("favicon").value("data:image/png;base64," + base64);
            }

            json.endObject();

            return writer.toString();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
