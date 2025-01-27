package com.fiap.techchallenge.persistence.helper;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    private UniqueCodeGenerator(){}

    public static String generateUniqueCode(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTimeString = sdf.format(new Date());
        String seedKey = dateTimeString + name;
        random.setSeed(seedKey.hashCode());

        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}
