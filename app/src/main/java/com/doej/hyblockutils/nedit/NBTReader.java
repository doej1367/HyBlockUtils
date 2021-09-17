package com.doej.hyblockutils.nedit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import android.util.Base64;
import com.doej.hyblockutils.nedit.type.NBTCompound;

/**
 * A utility class for reading NBT data from various sources
 *
 * original by Nullicorn - https://github.com/TheNullicorn/Nedit
 * edited for android and cropped to the needs of this project
 */
public final class NBTReader {

    /**
     * Read NBT data from a Base64 string
     *
     * @param base64 Base64-encoded string containing NBT data (may be gzipped)
     * @return The parsed compound
     * @throws IOException If the data could not be read properly
     * @see #read(InputStream)
     */
    public static NBTCompound readBase64(String base64) throws IOException {
        return read(new ByteArrayInputStream(Base64.decode(base64, Base64.DEFAULT)));
    }

    /**
     * Read NBT data from an InputStream
     *
     * @param inputStream An InputStream containing valid NBT data (may be gzipped)
     * @return The parsed compound
     * @throws IOException If the data could not be read properly
     */
    public static NBTCompound read(InputStream inputStream) throws IOException {
        try (InputStream nbtIn = inputStream) {
            return new NBTInputStream(nbtIn).readFully();
        }
    }

    private NBTReader() {
        // Prevent instantiation of this class
    }
}
