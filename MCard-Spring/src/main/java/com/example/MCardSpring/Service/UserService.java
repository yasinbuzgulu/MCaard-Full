package com.example.MCardSpring.Service;

import com.example.MCardSpring.Exception.UserNotFoundException;
import com.example.MCardSpring.MainModel.User;
import com.example.MCardSpring.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    /**
     * Service içinde kullanılacak user repository için instance oluşturmadan constructor ile çağrılır
     */
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Yeni kullanıcı oluşturma metodu
     *
     * @param user: yeni kullanıcı nesnesi
     * @return: depoya kayıt döner
     */
    public User createUser(User user) throws ParseException {
        return userRepository.save(user);

    }

    /**
     * Kullanıcı silme metodu
     *
     * @param id: silinecek kullanıcı id si
     */
    public void deleteUser(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }

    /**
     * Tüm kullanıcıları listeleme metodu
     *
     * @return: tüm kullanıcıları döner
     */
    public List<User> listUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    /**
     * Belirli kullanıcı alma metodu
     *
     * @param id: istenen kullanıcının id si
     * @return: 'id' li kullanıcıyı döner
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Kullanıcı güncelleme metodu
     *
     * @param newUser: güncel kullanıcı verisi
     * @param id:      güncellencek kullanıcının id si
     */
    public void updateUser(User newUser, Long id) throws ParseException {
        User user = userRepository.findById(id).get();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }
}
