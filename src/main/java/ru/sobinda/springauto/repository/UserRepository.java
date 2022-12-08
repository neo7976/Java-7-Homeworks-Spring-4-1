package ru.sobinda.springauto.repository;

import org.springframework.stereotype.Repository;
import ru.sobinda.springauto.model.Authorities;
import ru.sobinda.springauto.model.User;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {
    Map<User, List<Authorities>> users;
    Map<User, List<Authorities>> map = Stream.of(
                    new AbstractMap.SimpleEntry<>(new User("Ivan", "12345678"), List.of(Authorities.READ, Authorities.WRITE)),
                    new AbstractMap.SimpleEntry<>(new User("Fedua", "12345851448"), List.of(Authorities.DELETE)),
                    new AbstractMap.SimpleEntry<>(new User("Petya", "12345678ghg"), List.of(Authorities.READ, Authorities.WRITE)),
                    new AbstractMap.SimpleEntry<>(new User("Dima", "82651681"), List.of(Authorities.WRITE))
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public UserRepository() {
        this.users = new ConcurrentHashMap<>(map);
    }

    public List<Authorities> getUserAuthorities(User user) {
//        User searchUser = new User(user, password);
        if (map.containsKey(user)) {
            return map.get(user);
        }
        return Collections.emptyList();
    }
}
