package com.example.MCardSpring.Controller;

import com.example.MCardSpring.MainModel.User;
import com.example.MCardSpring.Repository.UserRepository;
import com.example.MCardSpring.Service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class UserController {
    /**
     * Sınıf içinde kullanılacak servis ve repository inject edilir
     */
    UserService userService;
    UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Eğer rolü admin ise tüm kullanıcıları çeken metot
     * @return: Tüm kullanıcıları çeker
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return ResponseEntity.ok().body(users);
    }

    /**
     * Eğer rol adminse spesifik kullanıcıyı çekme metodu
     * @param id: Çekilmesi istenen kullanıcının id si
     * @return: id ye sahip kullanıcıyı döner
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Yeni kullanıcı ekleme metodu
     * @param user: eklenecek kullanıcı nesnesi
     * @return: Kaydedilen kullanıcıyı döner
     * @throws ParseException
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws ParseException {
        user = userService.createUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/users/" + user.getId().toString());
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * Kullanıcı da düzenleme işleminin yapıldığı metot
     * @param user: Düzenlemenin yapılacağı user nesnesi
     * @param id:  Düzenlemenin yapılacağı user id si
     * @return:  Düzenleme yapılmış user nesnesini döner
     * @throws ParseException
     */
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) throws ParseException {
        userService.updateUser(user, id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * Kullanıcı silme metodu
     * @param id: Silinecek kullanıcının id si
     * @return: Status 204 döner (No Context)
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
