package com.doej.hyblockutils.nedit.exception;

import java.io.IOException;

/**
 * original by Nullicorn - https://github.com/TheNullicorn/Nedit
 * edited for android and cropped to the needs of this project
 */
public class NBTParseException extends IOException {

    /**
     * Constructs a new exception with the specified detail message.  The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public NBTParseException(String message) {
        super(message);
    }

}
