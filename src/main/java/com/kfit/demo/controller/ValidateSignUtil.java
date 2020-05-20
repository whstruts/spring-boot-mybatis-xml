package com.kfit.demo.controller;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.springframework.util.StringUtils.isEmpty;

public class ValidateSignUtil {

    public static byte[] compress(String str) {
        Deflater compresser = new Deflater();

        compresser.setInput(str.getBytes());
        compresser.finish();

        byte[] compressed = new byte[str.length() + 1];
        int compressedDataLength = compresser.deflate(compressed);

        byte[] encodestrig = new byte[compressedDataLength];
        System.arraycopy(compressed, 0, encodestrig, 0, compressedDataLength);
        return encodestrig;
    }

    public static String decompress(byte[] value) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(value.length);
        Inflater decompressor = new Inflater();

        try {
            decompressor.setInput(value);
            final byte[] buf = new byte[1024];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return new String(bos.toByteArray());
    }
    public  boolean areNotEmpty(String... values) {
        boolean result = true;
        if(values != null && values.length != 0) {
            String[] arr$ = values;
            int len$ = values.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String value = arr$[i$];
                result &= !isEmpty(value);
            }
        } else {
            result = false;
        }

        return result;
    }
}
