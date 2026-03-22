package com.colegio.backend.infrastructure.util;


public final class SequenceGenerator  {

    private static final String DEFAULT_SEQUENCE = "00000000";
    private static final int NUMERIC_LENGTH = 2;

    public static String generateCode(String currentSequence) {

        if (currentSequence == null) {
            currentSequence = DEFAULT_SEQUENCE;
        }

        String prefix = currentSequence.substring(0, currentSequence.length() - NUMERIC_LENGTH);
        String numericPart = currentSequence.substring(currentSequence.length() - NUMERIC_LENGTH);

        int number = Integer.parseInt(numericPart);
        number++;

        if (number > 99) {
            prefix = incrementAlphabetical(prefix);
            number = 0;
        }

        return prefix + String.format("%02d", number);

    }

    private static String incrementAlphabetical(String prefix) {

        char[] letters = prefix.toCharArray();

        for (int i = letters.length - 1; i >= 0; i--) {
            if (letters[i] == 'Z') {
                letters[i] = 'A';
            } else {
                letters[i]++;
                break;
            }
        }

        return new String(letters);
    }
}


