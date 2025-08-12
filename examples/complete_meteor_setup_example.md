# 血魔法坠星仪式机器配置示例

## 机器结构定义 (example_meteor_altar.json)

```json
{
  "registryname": "example_meteor_altar",
  "localizedname": "坠星祭坛",
  "parts": [
    {
      "elements": [
        {
          "x": 0,
          "y": 0, 
          "z": 0,
          "provider": {
            "registryname": "modularmachinery:blockcasing"
          }
        },
        {
          "x": 0,
          "y": 1,
          "z": 0,
          "provider": {
            "registryname": "modularmachinery:blockcontroller"
          }
        },
        {
          "x": 1,
          "y": 0,
          "z": 0,
          "provider": {
            "registryname": "modularmachinery:blockenergyinputhatch"
          }
        },
        {
          "x": -1,
          "y": 0,
          "z": 0,
          "provider": {
            "registryname": "modularmachinery:blockinputbus"
          }
        },
        {
          "x": 0,
          "y": 0,
          "z": 1,
          "provider": {
            "registryname": "modularmachinery:blockfluidinputhatch"
          }
        },
        {
          "x": 0,
          "y": 0,
          "z": -1,
          "provider": {
            "registryname": "modularmachineryaddons:blockmeteorprovideroutput"
          }
        }
      ]
    }
  ]
}
```

## CraftTweaker 脚本示例

```zenscript
#loader crafttweaker reloadable

import mods.modularmachinery.RecipeBuilder;

// 简单的坠星仪式 - 铁锭催化剂
RecipeBuilder.newBuilder("meteor_iron", "example_meteor_altar", 200)
    .addEnergyPerTickInput(1000)
    .addItemInput(<bloodmagic:slate> * 4)
    .addItemInput(<minecraft:iron_block>)
    .addFluidInput(<liquid:lifeessence> * 2000)
    .addMeteorOutput("minecraft:iron_ingot")
    .build();

// 高级坠星仪式 - 钻石催化剂  
RecipeBuilder.newBuilder("meteor_diamond", "example_meteor_altar", 400)
    .addEnergyPerTickInput(5000)
    .addItemInput(<bloodmagic:slate:2> * 8)
    .addItemInput(<minecraft:diamond_block>)
    .addItemInput(<bloodmagic:demon_crystal> * 2)
    .addFluidInput(<liquid:lifeessence> * 10000)
    .addMeteorOutput("minecraft:diamond")
    .build();

// 黄金坠星仪式
RecipeBuilder.newBuilder("meteor_gold", "example_meteor_altar", 300)
    .addEnergyPerTickInput(3000)
    .addItemInput(<bloodmagic:slate:1> * 6)
    .addItemInput(<minecraft:gold_block>)
    .addFluidInput(<liquid:lifeessence> * 5000)
    .addMeteorOutput("minecraft:gold_ingot")
    .build();
```

## 使用说明

1. 将机器定义文件放置在 `config/modularmachinery/machinery/` 文件夹中
2. 将CraftTweaker脚本放置在 `scripts/` 文件夹中
3. 确保安装了血魔法模组和模块化机械插件
4. 重新加载游戏或使用 `/ct reload` 命令

## 注意事项

- 坠星仪式将在指定位置召唤陨石
- 陨石的组成取决于使用的催化剂物品
- 确保有足够的空间进行坠星仪式
- 某些催化剂可能需要特定的血魔法等级或祭坛配置
