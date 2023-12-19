package com.github.angel.raa.modules.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatServiceImpl {

    // 最大重试次数
    private static final int MAX_RETRIES = 3;

    public String Answer(String request) {
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try (CloseableHttpClient httpClient = createHttpClientWithTimeout()) {
                String url = "http://10.58.0.2:6678/v1/chat/completions";
                HttpPost httpPost = new HttpPost(url);

                // 设置请求头
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer your_key");

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
                        "\"content\":" + "\"" + request + "\"" +
                        "}" +
                        "]" +
                        "}";
                httpPost.setEntity(new StringEntity(requestBody));

                // 设置超时时间
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectTimeout(5000) // 连接超时时间
                        .setSocketTimeout(5000)  // 数据传输超时时间
                        .build();
                httpPost.setConfig(requestConfig);

                HttpResponse response = httpClient.execute(httpPost);

                // 处理响应
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response: " + responseBody);

                // 使用 Jackson 解析 JSON
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // 提取生成的消息内容
                String answer = jsonNode.get("choices").get(0).get("message").get("content").asText();
                return answer;
            } catch (IOException e) {
                e.printStackTrace();
                retries++;
                System.out.println("Retry #" + retries);
            }
        }

        // 超过最大重试次数，进行回滚处理
        System.out.println("Max retries exceeded. Performing rollback.");
        return "Error!";
    }

    private CloseableHttpClient createHttpClientWithTimeout() {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000) // 连接超时时间
                        .setSocketTimeout(5000)  // 数据传输超时时间
                        .build())
                .build();
    }
}
