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

        MenuCategory mains    = findOrCreateCategory("Mains",    "https://static.vecteezy.com/system/resources/thumbnails/051/028/326/small/large-group-of-barbecue-food-isolated-on-transparent-background-top-view-png.png",    branch);
        MenuCategory desserts = findOrCreateCategory("Desserts", "https://static.vecteezy.com/system/resources/previews/053/823/165/non_2x/a-white-plate-with-a-variety-of-desserts-including-cupcakes-brownies-and-fruit-png.png", branch);
        MenuCategory drinks   = findOrCreateCategory("Drinks",   "https://static.vecteezy.com/system/resources/previews/048/723/424/non_2x/drinks-isolated-on-transparent-background-free-png.png",   branch);
        MenuCategory salads   = findOrCreateCategory("Salads",   "https://static.vecteezy.com/system/resources/thumbnails/049/949/831/small/colorful-salad-with-mixed-greens-cut-out-transparent-png.png",   branch);

        // 4) MENU ITEMS (now with image URLs)
        createIfAbsent("Spaghetti Carbonara",
                "Classic Italian pasta with creamy sauce and bacon",
                8.99,
                "https://static.vecteezy.com/system/resources/previews/053/017/198/non_2x/carbonara-isolated-on-transparent-background-png.png",
                mains);

        createIfAbsent("Lasagna",
                "Layered pasta with cheese, sauce, and ground beef",
                9.99,
                "https://static.vecteezy.com/system/resources/thumbnails/033/544/617/small_2x/lasagna-on-a-plate-isolated-on-transparent-background-ai-generated-png.png",
                mains);

        createIfAbsent("Rib-eye Steak",
                "Juicy grilled rib-eye steak served with mashed potatoes",
                18.99,
                "https://static.vecteezy.com/system/resources/previews/041/326/132/non_2x/ai-generated-grill-ribeye-steak-isolated-on-transparent-background-png.png",
                mains);

        createIfAbsent("Grilled Chicken",
                "Grilled chicken with smoky BBQ sauce",
                12.00,
                "https://static.vecteezy.com/system/resources/previews/043/601/952/non_2x/grilled-chicken-on-isolated-transparent-background-png.png",
                mains);

        createIfAbsent("Classic Burger",
                "Beef patty with lettuce, tomato, and cheese on a bun",
                7.50,
                "https://static.vecteezy.com/system/resources/previews/047/591/502/non_2x/classic-cheeseburger-with-toppings-on-transparent-background-png.png",
                mains);

        createIfAbsent("Chocolate Cake",
                "Rich chocolate layered cake",
                5.00,
                "https://static.vecteezy.com/system/resources/previews/048/563/692/non_2x/chocolate-cake-slice-on-transparent-background-png.png",
                desserts);

        createIfAbsent("Cheesecake",
                "Creamy cheesecake with a graham cracker crust",
                6.50,
                "https://static.vecteezy.com/system/resources/previews/052/243/732/non_2x/cheesecake-isolated-on-transparent-background-png.png",
                desserts);

        createIfAbsent("Vanilla Ice Cream",
                "Homemade vanilla ice cream scoop",
                3.00,
                "https://static.vecteezy.com/system/resources/previews/011/809/006/non_2x/3d-rendering-vanilla-ice-cream-on-transparent-background-png.png",
                desserts);

        createIfAbsent("Strawberry Ice Cream",
                "Creamy strawberry ice cream with real strawberries",
                3.50,
                "https://static.vecteezy.com/system/resources/previews/027/143/707/non_2x/yummy-strawberry-ice-cream-cone-isolated-on-transparent-background-png.png",
                desserts);

        createIfAbsent("Fruit Tart",
                "Tart filled with fresh fruit and custard",
                4.50,
                "https://static.vecteezy.com/system/resources/previews/052/243/981/non_2x/fresh-fruit-tart-isolated-on-transparent-background-png.png",
                desserts);

        createIfAbsent("Green Tea",
                "Hot brewed organic green tea",
                2.00,
                "https://static.vecteezy.com/system/resources/previews/050/609/655/non_2x/green-tea-cup-on-transparent-background-png.png",
                drinks);

        createIfAbsent("Black Coffee",
                "Hot brewed coffee with a rich aroma",
                2.50,
                "https://static.vecteezy.com/system/resources/previews/055/981/052/non_2x/a-cup-of-black-coffee-isolated-on-a-transparent-background-png.png",
                drinks);

        createIfAbsent("Cappuccino",
                "Espresso with steamed milk foam",
                3.00,
                "https://static.vecteezy.com/system/resources/previews/011/771/100/non_2x/cup-of-cappuccino-png.png",
                drinks);

        createIfAbsent("Lemonade",
                "Refreshing homemade lemonade",
                2.20,
                "https://static.vecteezy.com/system/resources/previews/059/633/035/non_2x/refreshing-lemonade-drink-with-slices-and-straw-on-transparent-background-png.png",
                drinks);

        createIfAbsent("Sprite",
                "Chilled can of Sprite",
                1.50,
                "https://static.vecteezy.com/system/resources/previews/054/314/915/non_2x/sprite-can-on-transparent-background-free-png.png",
                drinks);

        createIfAbsent("Caesar Salad",
                "Romaine lettuce, croutons, parmesan, Caesar dressing",
                6.00,
                "https://static.vecteezy.com/system/resources/previews/046/828/252/non_2x/delicious-caesar-salad-in-a-white-bowl-isolated-on-transparent-background-png.png",
                salads);

        createIfAbsent("Greek Salad",
                "Tomatoes, cucumbers, olives, and feta cheese",
                6.50,
                "https://static.vecteezy.com/system/resources/previews/046/828/279/non_2x/delicious-greek-salad-in-a-bowl-isolated-on-transparent-background-png.png",
                salads);

        createIfAbsent("Tropical Fruit Salad",
                "Mango, pineapple, and coconut",
                5.00,
                "https://static.vecteezy.com/system/resources/thumbnails/059/591/226/small/fruit-salad-mix-with-pineapple-berries-and-mint-cut-out-transparent-png.png",
                salads);

        createIfAbsent("Garden Salad",
                "Fresh garden vegetables with light dressing",
                5.50,
                "https://static.vecteezy.com/system/resources/previews/048/557/893/non_2x/a-garden-salad-served-in-a-bowl-isolated-against-a-transparent-background-for-crisp-presentation-free-png.png",
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
