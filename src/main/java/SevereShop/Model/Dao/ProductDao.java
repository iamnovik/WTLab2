package SevereShop.Model.Dao;

import SevereShop.Model.Dao.Sort.SortOrder;
import SevereShop.Model.Dao.Sort.SortType;
import SevereShop.Model.Configs.Product.Product;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);

    List<Product> findProducts(String search, SortType type, SortOrder order);

    void save(Product product);

    void delete(Long id);
}
