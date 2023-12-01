package com.github.angel.raa.modules.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl {
    public String Answer(String request) throws IOException {
        String url = "http://10.58.0.2:6678/v1/chat/completions";
        String userQue_json = request;
        String userQue = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(userQue_json);
            JsonNode userQuesNode = jsonNode.get("userQues");

            if (userQuesNode.isArray() && userQuesNode.size() > 0) {
                String firstValue = userQuesNode.get(0).asText();
                userQue = firstValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String requestBody = "{\"model\":\"xxx\",\"max_tokens\":2048,\"top_p\":1,\"temperature\":1,\"messages\":[{\"role\":\"system\",\"content\":\"You are a helpful assistant.\"},{\"role\":\"user\",\"content\":\"" + userQue + "\"}]}";

        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_key");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

        } else {
            System.out.println("HTTP POST request failed. Response Code: " + responseCode);
        }

        connection.disconnect();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());

            JsonNode choicesNode = rootNode.get("choices");
            if (choicesNode != null && choicesNode.isArray()) {
                for (JsonNode choiceNode : choicesNode) {
                    JsonNode contentNode = choiceNode.path("message").path("content");
                    if (contentNode.isTextual()) {
                        String content = contentNode.asText();
                        return content;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
