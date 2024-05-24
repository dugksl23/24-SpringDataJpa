package study.springdatajpa.entity;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.springdatajpa.repository.ItemRepository;


@SpringBootTest
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    @Commit
    void perishable() {

        Item item = new Item(0l, "item1");
        Item item1 = itemRepository.save(item);

    }

}