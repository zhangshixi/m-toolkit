package com.mtoolkit.dbf;

import java.io.IOException;

public class DbfException extends IOException {

    private static final long serialVersionUID = -5577672740131219665L;

    public DbfException() {
    }

    public DbfException(String message) {
        super(message);
    }

    public DbfException(Throwable cause) {
        super(cause);
    }

    public DbfException(String message, Throwable cause) {
        super(message, cause);
    }
}
