package HSAnimal.demo.DTO;

import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public class ChatMessagePromptDTO {
    private List<ChatMessage> chatMessage;

    public List<ChatMessage> getChatMessage(){
        return chatMessage;
    }

    public void setChatMessage(List<ChatMessage> chatMessage){
        this.chatMessage = chatMessage;
    }
}
