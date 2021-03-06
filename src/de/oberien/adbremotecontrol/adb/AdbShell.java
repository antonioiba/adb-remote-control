package de.oberien.adbremotecontrol.adb;

import java.io.IOException;

public class AdbShell extends AdbProcess {
    public AdbShell() {
        super("shell");
        try {
            out.write("\n");
            out.flush();
            readUntil((byte) 0x1b, (byte) 0x37, (byte) 0x1b, (byte) 0x5b, (byte) 0x72, (byte) 0x1b, (byte) 0x5b, (byte) 0x39, (byte) 0x39, (byte) 0x39, (byte) 0x3b, (byte) 0x39, (byte) 0x39, (byte) 0x39, (byte) 0x48, (byte) 0x1b, (byte) 0x5b, (byte) 0x36, (byte) 0x6e, (byte) 0x1b, (byte) 0x38);
            readUntil("shell@jfltexx:/ $ ".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeSync(String cmd) throws IOException {
        executeAsync(cmd);
        readUntil("\r\nshell@jfltexx:/ $ ".getBytes());
    }

    public void executeAsync(String cmd) throws IOException {
        System.out.println("Execute Command `" + cmd + "`");
        out.write(cmd);
        out.write("\n");
        out.flush();
    }
}
