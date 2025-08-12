#loader crafttweaker reloadable

/*
 * 血魔法坠星仪式 (Meteor) ZenScript 适配测试脚本
 * 此脚本演示了如何在模块化机械配方中使用血魔法坠星仪式
 */

import mods.modularmachinery.RecipeBuilder;

// 基础坠星仪式 - 使用铁锭作为催化剂
RecipeBuilder.newBuilder("test_meteor_iron", "meteor_altar", 200)
    .addEnergyPerTickInput(1000)
    .addItemInput(<bloodmagic:slate> * 4)
    .addItemInput(<minecraft:iron_block>)
    .addFluidInput(<liquid:lifeessence> * 2000)
    .addMeteorOutput("minecraft:iron_ingot")  // 新的ZenScript方法
    .build();

// 高级坠星仪式 - 使用钻石作为催化剂
RecipeBuilder.newBuilder("test_meteor_diamond", "meteor_altar", 400)
    .addEnergyPerTickInput(5000)
    .addItemInput(<bloodmagic:slate:2> * 8)
    .addItemInput(<minecraft:diamond_block>)
    .addItemInput(<bloodmagic:demon_crystal> * 2)
    .addFluidInput(<liquid:lifeessence> * 10000)
    .addMeteorOutput("minecraft:diamond")
    .build();

// 黄金坠星仪式
RecipeBuilder.newBuilder("test_meteor_gold", "meteor_altar", 300)
    .addEnergyPerTickInput(3000)
    .addItemInput(<bloodmagic:slate:1> * 6)
    .addItemInput(<minecraft:gold_block>)
    .addFluidInput(<liquid:lifeessence> * 5000)
    .addMeteorOutput("minecraft:gold_ingot")
    .build();

// 特殊坠星仪式 - 使用血魔法特定物品
RecipeBuilder.newBuilder("test_meteor_bloodmagic", "meteor_altar", 600)
    .addEnergyPerTickInput(10000)
    .addItemInput(<bloodmagic:slate:3> * 16)       // 以太石板
    .addItemInput(<bloodmagic:demon_crystal> * 8)   // 恶魔水晶
    .addItemInput(<bloodmagic:blood_shard> * 4)     // 血之碎片
    .addFluidInput(<liquid:lifeessence> * 20000)    // 生命精华
    .addMeteorOutput("bloodmagic:slate:3")          // 使用以太石板作为催化剂
    .build();

print("血魔法坠星仪式ZenScript适配测试脚本已加载！");
print("包含 4 个测试配方：");
print("- test_meteor_iron: 铁锭催化剂坠星仪式");
print("- test_meteor_diamond: 钻石催化剂坠星仪式");  
print("- test_meteor_gold: 黄金催化剂坠星仪式");
print("- test_meteor_bloodmagic: 血魔法特殊催化剂坠星仪式");
