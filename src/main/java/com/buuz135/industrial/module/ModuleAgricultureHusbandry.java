package com.buuz135.industrial.module;

import com.buuz135.industrial.api.plant.PlantRecollectable;
import com.buuz135.industrial.block.agriculturehusbandry.*;
import com.buuz135.industrial.registry.IFRegistries;
import com.buuz135.industrial.utils.Reference;
import com.buuz135.industrial.utils.apihandlers.plant.*;
import com.hrznstudio.titanium.event.handler.EventManager;
import com.hrznstudio.titanium.module.Feature;
import com.hrznstudio.titanium.tab.AdvancedTitaniumTab;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class ModuleAgricultureHusbandry implements IModule {

    public static AdvancedTitaniumTab TAB_AG_HUS = new AdvancedTitaniumTab(Reference.MOD_ID + "_ag_hus", true);

    public static PlantGathererBlock PLANT_GATHERER = new PlantGathererBlock();
    public static SewerBlock SEWER = new SewerBlock();
    public static SewageComposterBlock SEWAGE_COMPOSTER = new SewageComposterBlock();
    public static PlantFertilizerBlock PLANT_FERTILIZER = new PlantFertilizerBlock();
    public static PlantSowerBlock PLANT_SOWER = new PlantSowerBlock();
    public static SlaughterFactoryBlock SLAUGHTER_FACTORY = new SlaughterFactoryBlock();

    @Override
    public List<Feature.Builder> generateFeatures() {
        List<Feature.Builder> builders = new ArrayList<>();
        builders.add(Feature.builder("crop_farming")
                .content(Block.class, PLANT_GATHERER)
                .event(EventManager.forge(RegistryEvent.NewRegistry.class).process(newRegistry -> IFRegistries.poke()))
                .content(PlantRecollectable.class, new BlockCropPlantRecollectable())
                .content(PlantRecollectable.class, new BlockNetherWartRecollectable())
                .content(PlantRecollectable.class, new DoubleTallPlantRecollectable())
                .content(PlantRecollectable.class, new PumpkinMelonPlantRecollectable())
                .content(PlantRecollectable.class, new TreePlantRecollectable())
                .content(PlantRecollectable.class, new ChorusFruitRecollectable())
                .content(Block.class, PLANT_SOWER)
        );
        builders.add(Feature.builder("sewage").
                content(Block.class, SEWER).
                content(Block.class, SEWAGE_COMPOSTER));
        builders.add(Feature.builder("plant_fertilizer").
                content(Block.class, PLANT_FERTILIZER));
        builders.add(Feature.builder("slaughter_factory").
                content(Block.class, SLAUGHTER_FACTORY));
        TAB_AG_HUS.addIconStack(new ItemStack(PLANT_GATHERER));
        return builders;
    }
}