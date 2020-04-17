package com.darkere.experiencemanager;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExperienceElixier {
    public static final ResourceLocation FLUID_STILL = new ResourceLocation("experiencemanager:block/source_elixier");
    public static final ResourceLocation FLUID_FLOWING = new ResourceLocation("experiencemanager:block/flowing_elixier");
    public static final ResourceLocation FLUID_OVERLAY = new ResourceLocation("minecraft:block/water_overlay");

    public static final String resourceName = "experienceelixier";
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExperienceManager.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ExperienceManager.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, ExperienceManager.MODID);

    public static RegistryObject<FlowingFluid> sourceElixier = FLUIDS.register(resourceName ,() ->
        new ForgeFlowingFluid.Source(ExperienceElixier.test_fluid_properties)
    );
    public static RegistryObject<FlowingFluid> flowingElixier = FLUIDS.register("flowing_"+ resourceName, () ->
        new ForgeFlowingFluid.Flowing(ExperienceElixier.test_fluid_properties)
    );

    public static RegistryObject<FlowingFluidBlock> test_fluid_block = BLOCKS.register(resourceName + "_block", () ->
        new FlowingFluidBlock(sourceElixier, Block.Properties.create(net.minecraft.block.material.Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())
    );
    public static RegistryObject<Item> test_fluid_bucket = ITEMS.register(resourceName + "_bucket", () ->
        new BucketItem(sourceElixier, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC))
    );
    public static final ForgeFlowingFluid.Properties test_fluid_properties =
        new ForgeFlowingFluid.Properties(sourceElixier, flowingElixier, FluidAttributes.builder(FLUID_STILL, FLUID_FLOWING).overlay(FLUID_OVERLAY).color(0x0ff54c))
            .bucket(test_fluid_bucket).block(test_fluid_block);
    public ExperienceElixier(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
    }


}
