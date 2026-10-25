import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;
import java.util.Locale;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(
            Bun bun,
            List<Ingredient> ingredients,
            String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Проверка чека: {0}")
    public static Object[][] testData() {
        return new Object[][]{
                {
                        new Bun("black bun", 100f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200f),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100f),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200f)
                        ),
                        String.format(
                                Locale.getDefault(),
                                "(==== black bun ====)%n" +
                                        "= sauce sour cream =%n" +
                                        "= filling cutlet =%n" +
                                        "= filling dinosaur =%n" +
                                        "(==== black bun ====)%n%n" +
                                        "Price: %f%n",
                                700.0f
                        )
                },
                {
                        new Bun("white bun", 1f),
                        List.of(
                                new Ingredient(IngredientType.FILLING, "cutlet", 0.5f),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 4f),
                                new Ingredient(IngredientType.FILLING, "sausage", 1f)
                        ),
                        String.format(
                                Locale.getDefault(),
                                "(==== white bun ====)%n" +
                                        "= filling cutlet =%n" +
                                        "= sauce chili sauce =%n" +
                                        "= filling sausage =%n" +
                                        "(==== white bun ====)%n%n" +
                                        "Price: %f%n",
                                7.5f
                        )
                },
                {
                        new Bun("red bun", 1f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 5.5f),
                                new Ingredient(IngredientType.FILLING, "sausage", 14.5f),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200f)
                        ),
                        String.format(
                                Locale.getDefault(),
                                "(==== red bun ====)%n" +
                                        "= sauce hot sauce =%n" +
                                        "= filling sausage =%n" +
                                        "= filling dinosaur =%n" +
                                        "(==== red bun ====)%n%n" +
                                        "Price: %f%n",
                                222.0f
                        )
                }
        };
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();

        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}

