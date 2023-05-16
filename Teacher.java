import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Teacher {
    private String name;
    private String subject;
    private String apiKey;
    private List<AvailableBlock> availableTimes; 

    public Teacher(String name, String subject, String apiKey) {
        this.name = name;
        this.subject = subject;
        this.apiKey = apiKey;
        this.availableTimes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }
    
    private String getUserSchedulingLink() {
        String apiUrl = "https://api.calendly.com/users/me";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                String response = responseBuilder.toString();
                JSONObject responseObject = new JSONObject(response);
                JSONObject resourceObject = responseObject.getJSONObject("resource");

                String userSchedulingLink = resourceObject.getString("scheduling_url");
                return userSchedulingLink;
            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void bookMeeting() {
        System.out.println("Book meeting through this link");
        System.out.println(getUserSchedulingLink());
    }

    private String getUserUri() {
        String apiUrl = "https://api.calendly.com/users/me";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                String response = responseBuilder.toString();
                JSONObject responseObject = new JSONObject(response);
                JSONObject resourceObject = responseObject.getJSONObject("resource");

                String userUri = resourceObject.getString("uri");
                return userUri;
            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void parseAvailabilityResponse(String response) {
        availableTimes.clear();

        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray collection = responseObject.getJSONArray("collection");

            if (collection.length() > 0) {
                JSONObject scheduleObject = collection.getJSONObject(0);
                JSONArray rules = scheduleObject.getJSONArray("rules");

                for (int i = 0; i < rules.length(); i++) {
                    JSONObject rule = rules.getJSONObject(i);
                    String type = rule.optString("type", "");
                    JSONArray intervals = rule.getJSONArray("intervals");

                    if (type.equals("wday") && intervals.length() > 0) {
                        JSONObject interval = intervals.getJSONObject(0);
                        String from = interval.getString("from");
                        String to = interval.getString("to");

                        AvailableBlock block = new AvailableBlock(rule.getString("wday"), from, to);
                        availableTimes.add(block);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getApiResponse(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                return responseBuilder.toString();
            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getAvailability() {
        String userUri = getUserUri();

        if (userUri != null) {
            String apiUrl = "https://api.calendly.com/user_availability_schedules?user=" + userUri;
            String response = getApiResponse(apiUrl);

            if (response != null) {
                parseAvailabilityResponse(response);

                if (!availableTimes.isEmpty()) {
                    System.out.println("Teacher: " + name);
                    System.out.println("Available Times:");
                    for (AvailableBlock block : availableTimes) {
                        System.out.println(block.getDate() + " " + block.getFrom() + " - " + block.getTo());
                    }
                } else {
                    System.out.println("No available times.");
                }
            }
        }
    }
}