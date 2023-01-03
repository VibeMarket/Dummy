package xyz.dashnetwork.status.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StreamUtils {

    public static void writeVarInt(DataOutputStream output, int integer) throws IOException {
        while ((integer & 0xFFFFFF80) != 0) {
            output.writeByte(integer & 127 | 128);
            integer >>>= 7;
        }

        output.writeByte(integer);
    }

    public static int readVarInt(DataInputStream input) throws IOException {
        int i = 0;
        int j = 0;

        while (true) {
            int k = input.readByte();
            i |= (k & 0x7F) << j++ * 7;

            if (j > 5)
                throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128)
                break;
        }

        return i;
    }

    public static String readString(DataInputStream input) throws IOException {
        byte[] bytes = new byte[readVarInt(input)];
        input.read(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

}
