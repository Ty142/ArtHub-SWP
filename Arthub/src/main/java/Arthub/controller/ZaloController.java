package Arthub.controller;

import Arthub.config.ZaloOaClient;
import Arthub.utils.APIException;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ZaloController {
    static ZaloOaClient client = new ZaloOaClient();
    static String access_token = "3795020683985774210"; // Phải là static để dùng trong main

    public static void main(String[] args) throws APIException {
        ZaloOaClient client = new ZaloOaClient();

        Map<String, String> headers = new HashMap<>();
        headers.put("access_token", access_token);

        JsonObject recipient = new JsonObject();
        recipient.addProperty("user_id", "2468458835296197922");

        JsonObject text = new JsonObject();
        text.addProperty("text", "text_message");

        JsonObject body = new JsonObject();
        body.add("recipient", recipient);
        body.add("message", text);
        System.err.println(body);

        JsonObject excuteRequest = client.excuteRequest("https://openapi.zalo.me/v3.0/oa/message/cs", "POST", null, body, headers, null);
        System.out.println(excuteRequest);
    }
}
