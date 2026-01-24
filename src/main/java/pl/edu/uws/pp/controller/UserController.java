package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserShortResponse createUser(@RequestBody UserRequest request){
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserShortResponse> usersList(){
        return userService.getUsersList();
    }

    @GetMapping("/{id}")
    public UserProfileResponse userInfo(@PathVariable Long id){
        return userService.getUserProfile(id);
    }

    @PutMapping("/{id}")
    public UserShortResponse editUser(@PathVariable Long id,
                                        @RequestBody UserEditRequest request){
        return userService.editUser(id, request);
    }

    @PostMapping("/{userId}/apartment/{apartmentId}")
    public UserShortResponse AddApartmentForUser(@PathVariable Long userId,
                                                   @PathVariable Long apartmentId){
        return userService.addApartmentForUser(userId, apartmentId);
    }

    @DeleteMapping("/{userId}/apartment/{apartmentId}")
    public UserShortResponse deleteApartmentForUser(@PathVariable Long userId,
                                                      @PathVariable Long apartmentId){
        return userService.deleteApartmentForUser(userId, apartmentId);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
