package com.ecommerceapp.e_Commerce_App.Controller;

import com.ecommerceapp.e_Commerce_App.Entity.Order;
import com.ecommerceapp.e_Commerce_App.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class)))
            })
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            })
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new order", description = "Place a new order in the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid order data")
            })
    public Order createOrder(
            @Parameter(description = "Order details", required = true, schema = @Schema(implementation = Order.class))
            @RequestBody Order order) {
        return orderService.createOrder(order);
    }





    @PutMapping("/{id}")
    @Operation(summary = "Update an order", description = "Modify an existing order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid order data"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            })
    public Order updateOrder(@PathVariable String id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }








    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Remove an order from the system",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            })
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }
}
