package com.example.universityCanteenManagement.component;

import com.example.universityCanteenManagement.domain.Branch;
import com.example.universityCanteenManagement.domain.MenuCategory;
import com.example.universityCanteenManagement.domain.MenuItem;
import com.example.universityCanteenManagement.domain.User;
import com.example.universityCanteenManagement.enums.Role;
import com.example.universityCanteenManagement.repository.BranchRepository;
import com.example.universityCanteenManagement.repository.MenuCategoryRepository;
import com.example.universityCanteenManagement.repository.MenuItemRepository;
import com.example.universityCanteenManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemRepository menuItemRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${spring.sql.init.mode:never}")  // Default to "never" if property missing
//    private String initMode;

    @Override
    public void run(String... args) {
        // --- 1) USERS ---
        if (!userRepository.existsByUsername("Abdurakhmon")) {
            User owner = new User();
            owner.setUsername("Abdurakhmon");
            owner.setPassword(passwordEncoder.encode("123456"));
            owner.setEmail("abdurakhmonkomilov480@gmail.com");
            owner.setRole(Role.OWNER);
            userRepository.save(owner);
        }
        if (!userRepository.existsByUsername("Ravshan")) {
            User admin = new User();
            admin.setUsername("Ravshan");
            admin.setPassword(passwordEncoder.encode("1234567"));
            admin.setEmail("ravshantuymurodov39@gmail.com");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        // --- 2) BRANCH ---
        Branch branch = branchRepository
                .findByName("Main Campus")
                .orElseGet(() -> {
                    Branch b = new Branch();
                    b.setName("Main Campus");
                    b.setDescription("Main campus of the university");
                    b.setLocation("Main Street, City");
                    b.setContactInfo("123-456-7890");
                    return branchRepository.save(b);
                });

        MenuCategory mains    = findOrCreateCategory("Mains",    "https://picsum.photos/seed/mains/200/200",    branch);
        MenuCategory desserts = findOrCreateCategory("Desserts", "https://picsum.photos/seed/desserts/200/200", branch);
        MenuCategory drinks   = findOrCreateCategory("Drinks",   "https://picsum.photos/seed/drinks/200/200",   branch);
        MenuCategory salads   = findOrCreateCategory("Salads",   "https://picsum.photos/seed/salads/200/200",   branch);

        // 4) MENU ITEMS (now with image URLs)
        createIfAbsent("Spaghetti Carbonara",
                "Classic Italian pasta with creamy sauce and bacon",
                8.99,
                "https://picsum.photos/seed/spaghettiCarbonara/200/200",
                mains);

        createIfAbsent("Lasagna",
                "Layered pasta with cheese, sauce, and ground beef",
                9.99,
                "https://picsum.photos/seed/lasagna/200/200",
                mains);

        createIfAbsent("Rib-eye Steak",
                "Juicy grilled rib-eye steak served with mashed potatoes",
                18.99,
                "https://picsum.photos/seed/ribeysteak/200/200",
                mains);

        createIfAbsent("Grilled Chicken",
                "Grilled chicken with smoky BBQ sauce",
                12.00,
                "https://picsum.photos/seed/grilledChicken/200/200",
                mains);

        createIfAbsent("Classic Burger",
                "Beef patty with lettuce, tomato, and cheese on a bun",
                7.50,
                "https://picsum.photos/seed/classicBurger/200/200",
                mains);

        createIfAbsent("Chocolate Cake",
                "Rich chocolate layered cake",
                5.00,
                "https://picsum.photos/seed/chocolateCake/200/200",
                desserts);

        createIfAbsent("Cheesecake",
                "Creamy cheesecake with a graham cracker crust",
                6.50,
                "https://picsum.photos/seed/cheesecake/200/200",
                desserts);

        createIfAbsent("Vanilla Ice Cream",
                "Homemade vanilla ice cream scoop",
                3.00,
                "https://picsum.photos/seed/vanillaIceCream/200/200",
                desserts);

        createIfAbsent("Strawberry Ice Cream",
                "Creamy strawberry ice cream with real strawberries",
                3.50,
                "https://picsum.photos/seed/strawberryIceCream/200/200",
                desserts);

        createIfAbsent("Fruit Tart",
                "Tart filled with fresh fruit and custard",
                4.50,
                "https://picsum.photos/seed/fruitTart/200/200",
                desserts);

        createIfAbsent("Green Tea",
                "Hot brewed organic green tea",
                2.00,
                "https://picsum.photos/seed/greenTea/200/200",
                drinks);

        createIfAbsent("Black Coffee",
                "Hot brewed coffee with a rich aroma",
                2.50,
                "https://picsum.photos/seed/blackCoffee/200/200",
                drinks);

        createIfAbsent("Cappuccino",
                "Espresso with steamed milk foam",
                3.00,
                "https://picsum.photos/seed/cappuccino/200/200",
                drinks);

        createIfAbsent("Lemonade",
                "Refreshing homemade lemonade",
                2.20,
                "https://picsum.photos/seed/lemonade/200/200",
                drinks);

        createIfAbsent("Sprite",
                "Chilled can of Sprite",
                1.50,
                "https://picsum.photos/seed/sprite/200/200",
                drinks);

        createIfAbsent("Caesar Salad",
                "Romaine lettuce, croutons, parmesan, Caesar dressing",
                6.00,
                "https://picsum.photos/seed/caesarSalad/200/200",
                salads);

        createIfAbsent("Greek Salad",
                "Tomatoes, cucumbers, olives, and feta cheese",
                6.50,
                "https://picsum.photos/seed/greekSalad/200/200",
                salads);

        createIfAbsent("Tropical Fruit Salad",
                "Mango, pineapple, and coconut",
                5.00,
                "https://picsum.photos/seed/tropicalFruitSalad/200/200",
                salads);

        createIfAbsent("Garden Salad",
                "Fresh garden vegetables with light dressing",
                5.50,
                "https://picsum.photos/seed/gardenSalad/200/200",
                salads);
    }

    private MenuCategory findOrCreateCategory(String name, String imageUrl, Branch branch) {
        return menuCategoryRepository
                .findByNameAndBranch_Id(name, branch.getId())
                .orElseGet(() -> {
                    MenuCategory c = new MenuCategory();
                    c.setName(name);
                    c.setImage(imageUrl);
                    c.setBranch(branch);
                    return menuCategoryRepository.save(c);
                });
    }

    private void createIfAbsent(String name,
                                String desc,
                                double price,
                                String imageUrl,
                                MenuCategory cat) {
        if (menuItemRepository.findByNameAndMenuCategory_Id(name, cat.getId()).isEmpty()) {
            MenuItem i = new MenuItem();
            i.setName(name);
            i.setDescription(desc);
            i.setPrice(price);
            i.setAvailable(true);
            i.setImage(imageUrl);               // <— set the image URL!
            i.setMenuCategory(cat);
            menuItemRepository.save(i);
        }
    }
}
