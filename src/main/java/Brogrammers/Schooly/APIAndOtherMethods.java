package Brogrammers.Schooly;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class APIAndOtherMethods {
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
    public static Notification show(String error){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div text = new Div(new Text(error));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();

        notification.setPosition(Notification.Position.MIDDLE);

        return notification;
    }

}
