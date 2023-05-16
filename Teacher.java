//View ReadMe file for references. Microsoft CoPilot free trial aided in suggesting the necessary imports. The org.json imports were done by installing the .jar file.


//These prompts are to make calls to the api through the endpoint in order to recieve a response. The api is REST based.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

//These improts are to parse the JSON api response. The api is REST based.
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Teacher {
    private String name;
    private String subject;
    private String apiKey;
    //The teacher has an arraylist of type AvailableBlock to display their avaliability schedule.
    private arrayList<AvailableBlock> availableTimes; 

    
    //Constructor
    public Teacher(String name, String subject, String apiKey) {
        this.name = name;
        this.subject = subject;
        this.apiKey = apiKey;
        this.availableTimes = new ArrayList<>();
    }
    
    //Getter methods
    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }
    
    
    //This works with the api's endpoint to get each teacher's unqiue scheduling link.
    private String getUserSchedulingLink() {
        String apiUrl = "https://api.calendly.com/users/me";
        //Learned about try and catch with some of the videos in the ReadMe file.
        //Calendly API has great documentation to work with the api.
        try {
            //Makes request to the API with parameters Authorization, Bearer (instead of OAUTH), and the user's API key.
            URL url = new URL(apiUrl);
            //Makes the call to the endpoint.
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
                
                //Returns the scheduling_url only from the API response.
                String userSchedulingLink = resourceObject.getString("scheduling_url");
                return userSchedulingLink;
            } else 
            {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

     //Prompts user tobook meeting by retrieving scheduling link.
    public void bookMeeting() {
        System.out.println("Book meeting through this link");
        System.out.println(getUserSchedulingLink());
    }

    //This is an API call identical to get scheduling url, but instead retrieving the user URI. We need the user URI because it is one of the parameters for another api call to get the avaliability schedules.
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
    
    
    //This code parses the JSON response and puts in into each teacher's avalibility schedule array. The teacher's array are of object AvailableBlock. 
    // For this code segment, Microsoft Copilot aided in making the arrays from the JSON responses because I did not now how to parse JSON.
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
    
    
    //This method is the API response for avaliability schedules that gets used in the method above. The value returned from this API call is what is parsed and put into the teacher's arraylist.
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
    
    // This method uses the previous method getUserUri() because it is a necessary parameter to retrieve the tecaher's avaliability schedule.
    //This method prints out the teachers' avaliable times neatly so that students can choose a teacher with a schedule convinient for him/her.
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
