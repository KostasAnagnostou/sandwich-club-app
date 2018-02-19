package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = JsonUtils.class.getSimpleName();

    /**
     * An empty private constructor
     * We don't need something here
     */
    private JsonUtils() {
    }

    /**
     * Return a {@link Sandwich} object that has been built up from
     * parsing the given JSON.
     */
    public static Sandwich parseSandwichJson(String sandwichJson) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(sandwichJson)) {
            return null;
        }

        // Initialize a Sandwich Object
        Sandwich sandwichObject = null;

        // Try to parse the JSON string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON string
            JSONObject baseJsonObject = new JSONObject(sandwichJson);

            // Extract the JSONObject associated with the key called "name"
            JSONObject name = baseJsonObject.getJSONObject("name");

            // Extract the mainName from the key called "mainName"
            String mainName = name.getString("mainName");

            List<String> alsoKnownAsList = new ArrayList<>();
            // Extract the JSONArray associated with the key called "alsoKnownAs"
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            int countAlsoKnownAsArray = alsoKnownAsArray.length();
            // For each name in the JSONArray, add it into the ArrayList
            for (int i = 0; i < countAlsoKnownAsArray; i++) {
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }

            // Extract the placeOfOrigin from the key called "placeOfOrigin"
            String placeOfOrigin = baseJsonObject.getString("placeOfOrigin");

            // Extract the description from the key called "description"
            String description = baseJsonObject.getString("description");

            // Extract the image path from the key called "image"
            String image = baseJsonObject.getString("image");

            List<String> ingredientsList = new ArrayList<>();
            // Extract the JSONArray associated with the key called "ingredients"
            JSONArray ingredientsArray = baseJsonObject.getJSONArray("ingredients");
            int countIngredientsArray = ingredientsArray.length();

            // For each ingredient in the JSONArray, add it into the ArrayList
            for (int j = 0; j < countIngredientsArray; j++) {
                String ingredient = ingredientsArray.getString(j);
                ingredientsList.add(ingredient);
            }

            // Create the Sandwich Object with the data from the Json
            sandwichObject = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JsonUtils", "Problem parsing the Sandwich JSON Object", e);
        }

        // return the Sandwich Object
        return sandwichObject;
    }
}
