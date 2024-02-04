package HSAnimal.demo.controller;

import HSAnimal.demo.domain.User;
import HSAnimal.demo.exception.AccountNotFoundException;
import HSAnimal.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> readAllUser() {
        List<User> userList = userRepository.findAllBy();
        if (userList.isEmpty()){
            throw new AccountNotFoundException("User not found");
        }
        return userList;
    }

    @GetMapping("/{user_id}")
    public User readUser(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new AccountNotFoundException("User not found"));
    }
}
