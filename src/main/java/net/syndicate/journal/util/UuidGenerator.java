package net.syndicate.journal.util;

import java.util.UUID;

public class UuidGenerator {
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
