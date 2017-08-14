package com.apptopus.gettyimageclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ercanozcan on 11/08/17.
 */

public class FixtureUtils {

    public static String readFixture(String path) throws IOException {
        ClassLoader classLoader = FixtureUtils.class.getClassLoader();
        BufferedReader r = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(path)));
        StringBuilder total = new StringBuilder();
        String line = "";
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
