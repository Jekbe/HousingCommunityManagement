package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PostMapping
    public UserShortResponse createUser(@RequestBody UserRequest request){
        return userService.createUser(request);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @GetMapping
    public List<UserShortResponse> usersList(@AuthenticationPrincipal UserPrincipal user){
        return userService.getUsersList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public UserProfileResponse userInfo(@PathVariable Long id,
                                        @AuthenticationPrincipal UserPrincipal user){
        return userService.getUserProfile(id, user);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'RESIDENT')")
    @PutMapping("/{id}")
    public UserShortResponse editUser(@PathVariable Long id,
                                      @RequestBody UserEditRequest request,
                                      @AuthenticationPrincipal UserPrincipal user){
        return userService.editUser(id, request, user);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PostMapping("/{userId}/apartment/{apartmentId}")
    public UserShortResponse AddApartmentForUser(@PathVariable Long userId,
                                                   @PathVariable Long apartmentId,
                                                 @AuthenticationPrincipal UserPrincipal user){
        return userService.addApartmentForUser(userId, apartmentId, user);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @DeleteMapping("/{userId}/apartment/{apartmentId}")
    public UserShortResponse deleteApartmentForUser(@PathVariable Long userId,
                                                      @PathVariable Long apartmentId,
                                                    @AuthenticationPrincipal UserPrincipal user){
        return userService.deleteApartmentForUser(userId, apartmentId, user);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
