package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.ChatMessagePrompt;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

@RestController
public class GptController {
    private String api_key = "";
    //chat-gpt 와 간단한 채팅 서비스 소스
    @GetMapping("getChat/{prompt}")
    public String getPrompt(@PathVariable String prompt) {
        OpenAiService service = new OpenAiService(api_key);
        CompletionRequest completionRequest = CompletionRequest.builder().prompt(prompt).model("gpt-3.5-turbo-instruct").echo(true).build();
        return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }

    @PostMapping("/chat")
    public String getChatMessages(@RequestBody ChatMessagePrompt prompt) {
        OpenAiService service = new OpenAiService(api_key);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder().messages(prompt.getChatMessage()).model("gpt-3.5-turbo-16k").build();
        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }
}