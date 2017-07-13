package de.dhbwka.java.exercise.json;

import com.google.gson.Gson;
import de.dhbwka.java.exercise.json.model.citymodel.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

/**
 * Created by floriankling on 13.07.17.
 */
public class CityParser {

    public static void main(String[] args) {
        try (Reader reader = new InputStreamReader(new URL("https://www.iai.kit.edu/~javavorlesung/dhbw/2016-17/cities.json").openStream())) {

            Gson gson = new Gson();
            Response response = gson.fromJson(reader, Response.class);

            Collections.sort(response.getGeonames());
            System.out.println(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
