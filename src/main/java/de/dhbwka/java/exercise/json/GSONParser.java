package de.dhbwka.java.exercise.json;

import com.google.gson.Gson;
import de.dhbwka.java.exercise.json.model.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by floriankling on 13.07.17.
 */
public class GSONParser {

    public static void main(String[] args) {
        try (Reader reader = new InputStreamReader(new URL("http://maps.googleapis.com/maps/api/geocode/json?address=karlsruhe").openStream())) {
            Gson gson = new Gson();
            Response response = gson.fromJson(reader, Response.class);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
