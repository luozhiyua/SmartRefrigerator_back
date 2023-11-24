package com.github.angel.raa.modules.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.angel.raa.modules.dto.ChatDTO;
import com.github.angel.raa.modules.service.interfaces.ChatService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public ChatDTO generateAnswer(ChatDTO chatDTO) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://10.58.0.2:6678/v1/chat/completions";
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer your_key");

            // 获取前端传来的问题字符串
            String question = chatDTO.getQuestion();

            // 设置请求体
            String requestBody = "{" +
                    "\"model\": \"xxx\"," +
                    "\"max_tokens\": 2048," +
                    "\"top_p\": 1," +
                    "\"temperature\": 1," +
                    "\"messages\": [" +
                    "{" +
                    "\"role\": \"system\"," +
                    "\"content\": \"You are a helpful assistant.\"" +
                    "}," +
                    "{" +
                    "\"role\": \"user\"," +
                    "\"content\":" + "\"" + question + "\"" +
                    "}" +
                    "]" +
                    "}";
            httpPost.setEntity(new StringEntity(requestBody));

            HttpResponse response = httpClient.execute(httpPost);

            // 处理响应
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response: " + responseBody);

            // 使用 Jackson 解析 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // 提取生成的消息内容
            String answer = jsonNode.get("choices").get(0).get("message").get("content").asText();
            chatDTO.setAnswer(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chatDTO;
    }
}
