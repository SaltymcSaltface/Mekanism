package mekanism.tools.common.recipe;

import java.util.function.Consumer;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.providers.IItemProvider;
import mekanism.common.recipe.BaseRecipeProvider;
import mekanism.common.recipe.RecipeProviderUtil;
import mekanism.common.recipe.builder.ExtendedShapedRecipeBuilder;
import mekanism.common.recipe.builder.ExtendedSmithingRecipeBuilder;
import mekanism.common.recipe.builder.SpecialRecipeBuilder;
import mekanism.common.recipe.pattern.Pattern;
import mekanism.common.recipe.pattern.RecipePattern;
import mekanism.common.recipe.pattern.RecipePattern.DoubleLine;
import mekanism.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import mekanism.common.util.RegistryUtils;
import mekanism.tools.common.MekanismTools;
import mekanism.tools.common.registries.ToolsItems;
import mekanism.tools.common.registries.ToolsRecipeSerializers;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class ToolsRecipeProvider extends BaseRecipeProvider {

    private static final char AXE_CHAR = 'A';
    private static final char PICKAXE_CHAR = 'P';
    private static final char ROD_CHAR = 'R';
    private static final char SHOVEL_CHAR = 'S';

    //Armor patterns
    private static final RecipePattern HELMET = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT));
    private static final RecipePattern CHESTPLATE = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT));
    private static final RecipePattern LEGGINGS = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT));
    private static final RecipePattern BOOTS = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.EMPTY, Pattern.INGOT));
    private static final RecipePattern SHIELD = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.PREVIOUS, Pattern.INGOT),
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT),
          TripleLine.of(Pattern.EMPTY, Pattern.INGOT, Pattern.EMPTY));
    //Tool Patterns
    private static final RecipePattern AXE = RecipePattern.createPattern(
          DoubleLine.of(Pattern.INGOT, Pattern.INGOT),
          DoubleLine.of(Pattern.INGOT, ROD_CHAR),
          DoubleLine.of(Pattern.EMPTY, ROD_CHAR));
    private static final RecipePattern HOE = RecipePattern.createPattern(
          DoubleLine.of(Pattern.INGOT, Pattern.INGOT),
          DoubleLine.of(Pattern.EMPTY, ROD_CHAR),
          DoubleLine.of(Pattern.EMPTY, ROD_CHAR));
    private static final RecipePattern PICKAXE = RecipePattern.createPattern(
          TripleLine.of(Pattern.INGOT, Pattern.INGOT, Pattern.INGOT),
          TripleLine.of(Pattern.EMPTY, ROD_CHAR, Pattern.EMPTY),
          TripleLine.of(Pattern.EMPTY, ROD_CHAR, Pattern.EMPTY));
    private static final RecipePattern SHOVEL = RecipePattern.createPattern(Pattern.INGOT, ROD_CHAR, ROD_CHAR);
    private static final RecipePattern SWORD = RecipePattern.createPattern(Pattern.INGOT, Pattern.INGOT, ROD_CHAR);
    private static final RecipePattern PAXEL = RecipePattern.createPattern(
          TripleLine.of(AXE_CHAR, PICKAXE_CHAR, SHOVEL_CHAR),
          TripleLine.of(Pattern.EMPTY, ROD_CHAR, Pattern.EMPTY),
          TripleLine.of(Pattern.EMPTY, ROD_CHAR, Pattern.EMPTY));

    public ToolsRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper, MekanismTools.MODID);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        registerRecipeSet(consumer, "bronze", ToolsItems.BRONZE_HELMET, ToolsItems.BRONZE_CHESTPLATE, ToolsItems.BRONZE_LEGGINGS, ToolsItems.BRONZE_BOOTS,
              ToolsItems.BRONZE_SWORD, ToolsItems.BRONZE_PICKAXE, ToolsItems.BRONZE_AXE, ToolsItems.BRONZE_SHOVEL, ToolsItems.BRONZE_HOE, ToolsItems.BRONZE_PAXEL,
              ToolsItems.BRONZE_SHIELD, MekanismTags.Items.INGOTS_BRONZE, Tags.Items.RODS_WOODEN, MekanismItems.BRONZE_NUGGET);
        registerRecipeSet(consumer, "lapis_lazuli", ToolsItems.LAPIS_LAZULI_HELMET, ToolsItems.LAPIS_LAZULI_CHESTPLATE, ToolsItems.LAPIS_LAZULI_LEGGINGS,
              ToolsItems.LAPIS_LAZULI_BOOTS, ToolsItems.LAPIS_LAZULI_SWORD, ToolsItems.LAPIS_LAZULI_PICKAXE, ToolsItems.LAPIS_LAZULI_AXE, ToolsItems.LAPIS_LAZULI_SHOVEL,
              ToolsItems.LAPIS_LAZULI_HOE, ToolsItems.LAPIS_LAZULI_PAXEL, ToolsItems.LAPIS_LAZULI_SHIELD, Tags.Items.GEMS_LAPIS, Tags.Items.RODS_WOODEN, null);
        registerRecipeSet(consumer, "osmium", ToolsItems.OSMIUM_HELMET, ToolsItems.OSMIUM_CHESTPLATE, ToolsItems.OSMIUM_LEGGINGS, ToolsItems.OSMIUM_BOOTS,
              ToolsItems.OSMIUM_SWORD, ToolsItems.OSMIUM_PICKAXE, ToolsItems.OSMIUM_AXE, ToolsItems.OSMIUM_SHOVEL, ToolsItems.OSMIUM_HOE, ToolsItems.OSMIUM_PAXEL,
              ToolsItems.OSMIUM_SHIELD, MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM), Tags.Items.RODS_WOODEN,
              MekanismItems.PROCESSED_RESOURCES.get(ResourceType.NUGGET, PrimaryResource.OSMIUM));
        registerRecipeSet(consumer, "refined_glowstone", ToolsItems.REFINED_GLOWSTONE_HELMET, ToolsItems.REFINED_GLOWSTONE_CHESTPLATE,
              ToolsItems.REFINED_GLOWSTONE_LEGGINGS, ToolsItems.REFINED_GLOWSTONE_BOOTS, ToolsItems.REFINED_GLOWSTONE_SWORD, ToolsItems.REFINED_GLOWSTONE_PICKAXE,
              ToolsItems.REFINED_GLOWSTONE_AXE, ToolsItems.REFINED_GLOWSTONE_SHOVEL, ToolsItems.REFINED_GLOWSTONE_HOE, ToolsItems.REFINED_GLOWSTONE_PAXEL,
              ToolsItems.REFINED_GLOWSTONE_SHIELD, MekanismTags.Items.INGOTS_REFINED_GLOWSTONE, Tags.Items.RODS_WOODEN, MekanismItems.REFINED_GLOWSTONE_NUGGET);
        registerRecipeSet(consumer, "refined_obsidian", ToolsItems.REFINED_OBSIDIAN_HELMET, ToolsItems.REFINED_OBSIDIAN_CHESTPLATE,
              ToolsItems.REFINED_OBSIDIAN_LEGGINGS, ToolsItems.REFINED_OBSIDIAN_BOOTS, ToolsItems.REFINED_OBSIDIAN_SWORD, ToolsItems.REFINED_OBSIDIAN_PICKAXE,
              ToolsItems.REFINED_OBSIDIAN_AXE, ToolsItems.REFINED_OBSIDIAN_SHOVEL, ToolsItems.REFINED_OBSIDIAN_HOE, ToolsItems.REFINED_OBSIDIAN_PAXEL,
              ToolsItems.REFINED_OBSIDIAN_SHIELD, MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, Tags.Items.RODS_WOODEN, MekanismItems.REFINED_OBSIDIAN_NUGGET);
        registerRecipeSet(consumer, "steel", ToolsItems.STEEL_HELMET, ToolsItems.STEEL_CHESTPLATE, ToolsItems.STEEL_LEGGINGS, ToolsItems.STEEL_BOOTS,
              ToolsItems.STEEL_SWORD, ToolsItems.STEEL_PICKAXE, ToolsItems.STEEL_AXE, ToolsItems.STEEL_SHOVEL, ToolsItems.STEEL_HOE, ToolsItems.STEEL_PAXEL,
              ToolsItems.STEEL_SHIELD, MekanismTags.Items.INGOTS_STEEL, Tags.Items.INGOTS_IRON, MekanismItems.STEEL_NUGGET);
        registerVanillaPaxels(consumer);
        SpecialRecipeBuilder.build(consumer, ToolsRecipeSerializers.BANNER_SHIELD);
    }

    private void registerRecipeSet(Consumer<FinishedRecipe> consumer, String name, IItemProvider helmet, IItemProvider chestplate, IItemProvider leggings,
          IItemProvider boots, IItemProvider sword, IItemProvider pickaxe, IItemProvider axe, IItemProvider shovel, IItemProvider hoe, IItemProvider paxel,
          IItemProvider shield, TagKey<Item> ingot, TagKey<Item> rod, @Nullable IItemProvider nugget) {
        String baseArmorPath = name + "/armor/";
        armor(HELMET, helmet, ingot).build(consumer, MekanismTools.rl(baseArmorPath + "helmet"));
        armor(CHESTPLATE, chestplate, ingot).build(consumer, MekanismTools.rl(baseArmorPath + "chestplate"));
        armor(LEGGINGS, leggings, ingot).build(consumer, MekanismTools.rl(baseArmorPath + "leggings"));
        armor(BOOTS, boots, ingot).build(consumer, MekanismTools.rl(baseArmorPath + "boots"));
        ExtendedShapedRecipeBuilder.shapedRecipe(shield)
              .pattern(SHIELD)
              .key(Pattern.PREVIOUS, Items.SHIELD)
              .key(Pattern.INGOT, ingot)
              .category(RecipeCategory.COMBAT)
              .build(consumer, MekanismTools.rl(name + "/shield"));
        String baseToolsPath = name + "/tools/";
        tool(SWORD, sword, ingot, rod).category(RecipeCategory.COMBAT).build(consumer, MekanismTools.rl(baseToolsPath + "sword"));
        tool(PICKAXE, pickaxe, ingot, rod).category(RecipeCategory.TOOLS).build(consumer, MekanismTools.rl(baseToolsPath + "pickaxe"));
        tool(AXE, axe, ingot, rod).category(RecipeCategory.TOOLS).build(consumer, MekanismTools.rl(baseToolsPath + "axe"));
        tool(SHOVEL, shovel, ingot, rod).category(RecipeCategory.TOOLS).build(consumer, MekanismTools.rl(baseToolsPath + "shovel"));
        tool(HOE, hoe, ingot, rod).category(RecipeCategory.TOOLS).build(consumer, MekanismTools.rl(baseToolsPath + "hoe"));
        PaxelShapedRecipeBuilder.shapedRecipe(paxel)
              .pattern(PAXEL)
              .key(AXE_CHAR, ItemTags.create(new ResourceLocation("forge", "tools/axes/" + name)))
              .key(PICKAXE_CHAR, ItemTags.create(new ResourceLocation("forge", "tools/pickaxes/" + name)))
              .key(SHOVEL_CHAR, ItemTags.create(new ResourceLocation("forge", "tools/shovels/" + name)))
              .key(ROD_CHAR, rod)
              .build(consumer, MekanismTools.rl(baseToolsPath + "paxel"));
        //If we have a nugget that means we also want to add recipes for smelting tools/armor into the nugget
        if (nugget != null) {
            String baseNuggetFrom = name + "/nugget_from_";
            RecipeProviderUtil.addSmeltingBlastingRecipes(consumer, Ingredient.of(helmet, chestplate, leggings, boots, sword, pickaxe, axe, shovel, hoe, paxel),
                  nugget, 0.1F, 200, MekanismTools.rl(baseNuggetFrom + "blasting"), MekanismTools.rl(baseNuggetFrom + "smelting"));
        }
    }

    private void registerVanillaPaxels(Consumer<FinishedRecipe> consumer) {
        registerVanillaPaxel(consumer, ToolsItems.WOOD_PAXEL, Items.WOODEN_AXE, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, null);
        registerVanillaPaxel(consumer, ToolsItems.STONE_PAXEL, Items.STONE_AXE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, null);
        registerVanillaPaxel(consumer, ToolsItems.IRON_PAXEL, Items.IRON_AXE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_NUGGET);
        registerVanillaPaxel(consumer, ToolsItems.GOLD_PAXEL, Items.GOLDEN_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLD_NUGGET);
        registerVanillaPaxel(consumer, ToolsItems.DIAMOND_PAXEL, Items.DIAMOND_AXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, null);
        ExtendedSmithingRecipeBuilder.smithing(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ToolsItems.DIAMOND_PAXEL, Items.NETHERITE_INGOT, ToolsItems.NETHERITE_PAXEL).build(consumer);
    }

    private void registerVanillaPaxel(Consumer<FinishedRecipe> consumer, IItemProvider paxel, Item axe, Item pickaxe, Item shovel, @Nullable Item nugget) {
        PaxelShapedRecipeBuilder.shapedRecipe(paxel)
              .pattern(PAXEL)
              .key(AXE_CHAR, axe)
              .key(PICKAXE_CHAR, pickaxe)
              .key(SHOVEL_CHAR, shovel)
              .key(ROD_CHAR, Tags.Items.RODS_WOODEN)
              .build(consumer);
        //If we have a nugget that means we also want to add recipes for smelting tools/armor into the nugget
        if (nugget != null) {
            String baseNuggetFrom = RegistryUtils.getPath(nugget) + "_from_";
            RecipeProviderUtil.addSmeltingBlastingRecipes(consumer, Ingredient.of(paxel), nugget, 0.1F, 200,
                  MekanismTools.rl(baseNuggetFrom + "blasting"), MekanismTools.rl(baseNuggetFrom + "smelting"));
        }
    }

    private ExtendedShapedRecipeBuilder armor(RecipePattern pattern, IItemProvider armor, TagKey<Item> ingot) {
        return ExtendedShapedRecipeBuilder.shapedRecipe(armor)
              .pattern(pattern)
              .key(Pattern.INGOT, ingot)
              .category(RecipeCategory.COMBAT);
    }

    private ExtendedShapedRecipeBuilder tool(RecipePattern pattern, IItemProvider tool, TagKey<Item> ingot, TagKey<Item> rod) {
        return ExtendedShapedRecipeBuilder.shapedRecipe(tool)
              .pattern(pattern)
              .key(Pattern.INGOT, ingot)
              .key(ROD_CHAR, rod);
    }
}