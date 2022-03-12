package xyx.dashnetwork.dummy.status.packets;

import xyx.dashnetwork.dummy.status.ServerInfo;
import xyx.dashnetwork.dummy.status.utils.StreamUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PacketResponse {

    public PacketResponse(DataOutputStream output, ServerInfo info) throws IOException {
        byte[] json = info.buildJson().getBytes(StandardCharsets.UTF_8);
        int length = json.length;

        StreamUtils.writeVarInt(output, 3 + length); // Packet size
        StreamUtils.writeVarInt(output, 0); // Packet ID
        StreamUtils.writeVarInt(output, length); // String length
        output.write(json); // String
    }

}
