package core.basesyntax.service;

import core.basesyntax.db.StorageDao;
import core.basesyntax.db.StorageDaoImpl;
import core.basesyntax.model.Fruit;
import java.util.Map;

public class StorageServiceImpl implements StorageService {
    private StorageDao storageDao = new StorageDaoImpl();

    @Override
    public void add(Fruit fruit, int amount) {
        Map<Fruit, Integer> fruits = storageDao.getAll();
        if (fruits.keySet().contains(fruit)) {
            fruits.replace(new Fruit(fruit.getName()),
                    fruits.get(fruit), amount + fruits.get(fruit));
        } else {
            storageDao.add(fruit, amount);
        }
    }

    @Override
    public int get(Fruit fruit, int amount) {
        Map<Fruit, Integer> fruits = storageDao.getAll();
        if (!fruits.keySet().contains(fruit)) {
            throw new RuntimeException("There is no such fruit in the store");
        }
        if (fruits.get(fruit) < amount) {
            throw new RuntimeException("There is no such amount in the store. The remnant is "
                    + fruits.get(fruit));
        } else {
            fruits.get(fruit);
            int remnant = storageDao.getAll().get(fruit) - amount;
            return remnant;

        }
    }

    @Override
    public void update(Fruit fruit, int amount) {
        storageDao.add(fruit, amount);
    }
}
