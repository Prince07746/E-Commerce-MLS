package com.ecommerceapp.e_Commerce_App.Controller;

import com.ecommerceapp.e_Commerce_App.Entity.User;
import com.ecommerceapp.e_Commerce_App.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)))
            })
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Add a new user to the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid user data")
            })
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Remove a user from the system",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Modify an existing user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid user data"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public User updateUser(
            @PathVariable String id,
            @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
