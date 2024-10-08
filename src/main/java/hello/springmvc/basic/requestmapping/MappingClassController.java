package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String user() {
        return "user";
    }

    @PostMapping
    public String addUser() {
        return "add user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update user" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete user" + userId;
    }
}
