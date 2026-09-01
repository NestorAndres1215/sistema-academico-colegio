package com.colegio.backend.shared.utils;

import java.util.UUID;

public final class CodeGenerator {

    private CodeGenerator() {
    }

    public static String generateCode() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }
}