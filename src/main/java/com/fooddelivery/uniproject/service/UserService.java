package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dto.OrderDto;
import com.fooddelivery.uniproject.dto.RegisterAccountDto;
import com.fooddelivery.uniproject.dto.UserDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.audit.Action;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Product;
import com.fooddelivery.uniproject.entity.order.Order;
import com.fooddelivery.uniproject.entity.order.OrderItem;
import com.fooddelivery.uniproject.entity.order.OrderStatus;
import com.fooddelivery.uniproject.exception.*;
import com.fooddelivery.uniproject.repository.*;
import com.fooddelivery.uniproject.utils.Methods;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private CoordinateRepository coordinateRepository;
    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private LocalRepository localRepository;
    private ActionRepository actionRepository;
    private OrderItemsRepository orderItemsRepository;
    private ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, CoordinateRepository coordinateRepository, DriverRepository driverRepository,
                       OrderRepository orderRepository, LocalRepository localRepository,
                       ActionRepository actionRepository, OrderItemsRepository orderItemsRepository,
                       ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.coordinateRepository = coordinateRepository;
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.localRepository = localRepository;
        this.actionRepository = actionRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.productRepository = productRepository;
    }

    public List<UserDto> listAll() {

        actionRepository.save(new Action("List all users"));

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> userDtos.add(
                UserDto.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .coordinate(user.getCoordinate())
                        .build())
        );
        return userDtos;
    }

    @SneakyThrows
    public void registerUser(RegisterAccountDto registerAccountDto) {

        actionRepository.save(new Action("Register users"));

        if (userRepository.findUserByEmailOrUsername(registerAccountDto.getEmail(), registerAccountDto.getUsername()).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }

        User user = User.builder()
                .email(registerAccountDto.getEmail())
                .password(registerAccountDto.getPassword())
                .username(registerAccountDto.getUsername())
                .coordinate(registerAccountDto.getCoordinate()).build();

        userRepository.save(user);
    }

    @SneakyThrows
    public void removeUser(Long userId) {

        actionRepository.save(new Action("Remove user"));

        User user = userRepository.findById(userId).orElseThrow(() -> new AccountNotFoundException("Driver not found"));

        userRepository.delete(user);
    }

    @SneakyThrows
    public void makeOrder(OrderDto orderDto) {

        actionRepository.save(new Action("User made order"));

        Optional<Local> optionalChosenLocal = localRepository.findById(orderDto.getLocalId());
        if (optionalChosenLocal.isEmpty()) {
            throw new NonExistentId();
        }
        Local chosenLocal = optionalChosenLocal.get();
        Map<Optional<Product>,Long> optionalProducts = new HashMap<>();
        orderDto.getOrderItems().forEach(orderItem -> optionalProducts.put(productRepository.getProductByNamePrice(orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice()),orderItem.getQuantity()));

        for (Optional<Product> optionalProduct : optionalProducts.keySet()){
            if (optionalProduct.isEmpty() || (! chosenLocal.getMenu().getProducts().contains(optionalProduct.get()))){
                throw new LocalHasNoSuchProduct();
            }
        }

        Driver driver = closestDriver(chosenLocal);

        List<OrderItem> orderItems = new ArrayList<>();

        for (Optional<Product> optionalProduct : optionalProducts.keySet()){
            orderItems.add(OrderItem.builder()
                    .product(optionalProduct.get())
                    .quantity(optionalProducts.get(optionalProduct))
                    .build());
        }

        Order order = Order.builder()
                .driver(driver)
                .orderItems(orderItems)
                .user(this.get(orderDto.getUserId()))
                .local(chosenLocal)
                .status(OrderStatus.ACTIVE)
                .build();


        orderRepository.save(order);

        driverRepository.setCurrentOrder(order, driver.getId());

    }

    @SneakyThrows
    public void confirmOrder(Long userId) {

        actionRepository.save(new Action("User confirmed action"));

        Optional<Order> order = orderRepository.findActiveOrderByUser(userRepository.getOne(userId));
        if (order.isEmpty()) {
            throw new UserHasNoActiveOrders();
        }

        orderRepository.setOrderInactive(order.get().getId());
        driverRepository.setCurrentOrder(null, order.get().getDriver().getId());

    }

    private Driver closestDriver(Local chosenLocal) throws NoDriverInRangeException {

        List<Driver> drivers = driverRepository.findAll();
        double minimumDistance = Double.POSITIVE_INFINITY;
        int driverIndex = -1;
        Methods methods = new Methods();

        for (Driver driver : drivers) {
            double currentDistance = methods.calculateDistance(driver.getCoordinate(), chosenLocal.getLocation().getCoordinate());
            if (currentDistance < 1000 && currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                driverIndex = drivers.indexOf(driver);
            }
        }
        if (driverIndex == -1) {
            throw new NoDriverInRangeException();
        }
        return drivers.get(driverIndex);
    }

    public User get(Long id) {
        actionRepository.save(new Action("Getting user by id"));
        return userRepository.findById(id).orElseThrow(NonExistentId::new);
    }

    public void renameUser(String oldName, String newName){
        //System.out.println(userRepository.findUserByEmailOrUsername("",oldName).get());
        Optional<User> user = userRepository.findUserByEmailOrUsername("",oldName);
        if (user.isEmpty()) {
            throw new NoUserWithThisUsername();
        }
        if (userRepository.findUserByEmailOrUsername("",newName).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }
        user.get().setUsername(newName);
        userRepository.save(user.get());
        //userRepository.renameUser(oldName,newName);

    }

}
