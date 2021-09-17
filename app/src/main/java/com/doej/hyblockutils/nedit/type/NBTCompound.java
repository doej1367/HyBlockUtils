package com.doej.hyblockutils.nedit.type;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an NBT compound tag ({@link TagType#COMPOUND})
 *
 * original by Nullicorn - https://github.com/TheNullicorn/Nedit
 * edited for android and cropped to the needs of this project
 */
public class NBTCompound extends HashMap<String, Object> {

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The double at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public double getDouble(String key, double defaultValue) {
        return getNumber(key, defaultValue).doubleValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The floating-point number at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public float getFloat(String key, float defaultValue) {
        return getNumber(key, defaultValue).floatValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The short at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public short getShort(String key, short defaultValue) {
        return getNumber(key, defaultValue).shortValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The long at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public long getLong(String key, long defaultValue) {
        return getNumber(key, defaultValue).longValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The integer at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public int getInt(String key, int defaultValue) {
        return getNumber(key, defaultValue).intValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The byte at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    @SuppressWarnings("unused")
    public byte getByte(String key, byte defaultValue) {
        return getNumber(key, defaultValue).byteValue();
    }

    /**
     * @param key          A dot-separated path to the desired field
     * @param defaultValue The value to return if the field does not exist
     * @return The number at the desired path, or the default value if it does not exist
     * @see #get(String)
     */
    public Number getNumber(String key, @Nullable Number defaultValue) {
        Object result = get(key);
        return result instanceof Number
            ? (Number) result
            : defaultValue;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The string at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public String getString(String key, String defaultValue) {
        Object result = get(key);
        return result != null
            ? result.toString()
            : defaultValue;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The long array at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public long[] getLongArray(String key) {
        Object result = get(key);
        return result instanceof long[]
            ? (long[]) result
            : null;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The integer array at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public int[] getIntArray(String key) {
        Object result = get(key);
        return result instanceof int[]
            ? (int[]) result
            : null;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The byte array at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public byte[] getByteArray(String key) {
        Object result = get(key);
        return result instanceof byte[]
            ? (byte[]) result
            : null;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The list at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public NBTList getList(String key) {
        Object result = get(key);
        return result instanceof NBTList
            ? (NBTList) result
            : null;
    }

    /**
     * @param key A dot-separated path to the desired field
     * @return The compound at the desired path, or null if it does not exist
     * @see #get(String)
     */
    @Nullable
    @SuppressWarnings("unused")
    public NBTCompound getCompound(String key) {
        Object result = get(key);
        return result instanceof NBTCompound
            ? (NBTCompound) result
            : null;
    }

    /**
     * Retrieve a value from within this compound.
     * <p>
     * <p>
     * Consider the following NBT data:
     * <pre>{@code {
     *   user: {
     *     name: "User291",
     *     id: "ae630aa9-cdbb-4aaf-be42-868889585b4d",
     *     tokens: 19531L,
     *     socialMedia: {
     *       discord: "Player291#4444",
     *       twitter: "User291"
     *     }
     *   }
     * }}</pre>
     * In this case, to access the user's <b>Discord username</b>, we'd need the path:
     * <pre>{@code "user.socialMedia.discord"}</pre>
     * To access the <b>user's ID</b>, we could use the path:
     * <pre>{@code "user.id"}</pre>
     * And to access the <b>whole user</b> object, we could just use the path:
     * <pre>{@code "user"}</pre>
     *
     * @param key A dot-separated path to the desired field (see above example)
     * @return The value at the specified path, or null if the field does not exist
     */
    @Nullable
    public Object get(String key) {
        if (key == null || key.isEmpty()) {
            return this;
        }

        String[] path = key.split("\\.");
        Object currentObj = this;
        for (int i = 0; i < path.length; i++) {
            currentObj = ((Map<?, ?>) currentObj).get(path[i]);
            if (i == path.length - 1) {
                // We reached the end of the path, return the final value
                return currentObj;
            }

            if (currentObj == null) {
                // We reached a dead-end before expected, return null
                return null;
            }
        }

        return null;
    }

    /**
     * @return The compound represented by an NBT string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        int i = 0;
        for (Entry<String, Object> tag : entrySet()) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(tag.getKey().isEmpty() ? "\"\"" : tag.getKey());
            sb.append(":");
            sb.append(tagToString(tag.getValue()));
            i++;
        }

        sb.append("}");
        return sb.toString();
    }

    /*
     *
     * ============ toString() Helper Functions ============
     *
     */

    private static String tagToString(Object value) {
        TagType tagType = TagType.fromObject(value);
        switch (tagType) {
            case BYTE:
                return byteToString((Byte) value);
            case SHORT:
                return shortToString((Short) value);
            case INT:
                return intToString((Integer) value);
            case LONG:
                return longToString((Long) value);
            case FLOAT:
                return floatToString((Float) value);
            case DOUBLE:
                return doubleToString((Double) value);
            case BYTE_ARRAY:
                return byteArrayToString((byte[]) value);
            case STRING:
                return stringTagToString(((String) value).replaceAll("\"", "")); // in case the string contains double quotes
            case LIST:
                return listToString((NBTList) value);
            case COMPOUND:
                return value.toString();
            case INT_ARRAY:
                return intArrayToString((int[]) value);
            case LONG_ARRAY:
                return longArrayToString((long[]) value);
            default:
                return "";
        }
    }

    private static String byteToString(byte value) {
        return value + "b";
    }

    private static String shortToString(short value) {
        return value + "s";
    }

    private static String intToString(int value) {
        return Integer.toString(value);
    }

    private static String longToString(long value) {
        return value + "l";
    }

    private static String floatToString(float value) {
        return value + "f";
    }

    private static String doubleToString(double value) {
        return value + "d";
    }

    private static String byteArrayToString(byte[] value) {
        StringBuilder sb = new StringBuilder();
        sb.append("[B;");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i < value.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String stringTagToString(String value) {
        return "\"" + value + "\"";
    }

    private static String listToString(NBTList value) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < value.size(); i++) {
            sb.append(value.get(i) != null ? tagToString(value.get(i)) : 0);
            if (i < value.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String intArrayToString(int[] value) {
        StringBuilder sb = new StringBuilder();
        sb.append("[I;");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i < value.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String longArrayToString(long[] value) {
        StringBuilder sb = new StringBuilder();
        sb.append("[L;");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i < value.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}