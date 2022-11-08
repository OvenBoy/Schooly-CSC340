package Brogrammers.Schooly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class API {
    public static String getWeather() throws JSONException {
        String url = "https://api.weatherbit.io/v2.0/current?city=Greensboro&country=US&state=North%20Carolina&units=I&key=12b32eca1dd9498dbe364be4818f4a6c";
        RestTemplate restTemplate = new RestTemplate();

        String quote = restTemplate.getForObject(url, String.class);
        JSONObject jo = new JSONObject(quote);

        JSONArray ja = jo.getJSONArray("data");
        JSONObject j01 = ja.getJSONObject(0);
        int temp = j01.getInt("temp");
        String cityName = j01.getString("city_name");
        return "It is " + temp + " degree in " + cityName +".";

    }
}
