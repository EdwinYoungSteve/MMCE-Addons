# 血魔法坠星仪式 (Meteor) ZenScript适配使用说明

## 概述
此适配允许你通过ZenScript在模块化机械配方中使用血魔法的坠星仪式作为输出要求。

## 前置要求
- 血魔法 (Blood Magic) 模组
- 模块化机械 (Modular Machinery)
- CraftTweaker

## 使用方法

### ZenScript语法
```zenscript
mods.modularmachinery.RecipePrimer.addMeteorOutput(RecipePrimer primer, String catalystItem)
```

### 参数说明
- `primer`: 配方原型对象
- `catalystItem`: 催化剂物品的注册名 (例如: "minecraft:iron_ingot")

### 使用示例

#### 基础示例 - 使用铁锭作为催化剂的坠星仪式
```zenscript
import mods.modularmachinery.RecipeBuilder;

// 创建一个坠星仪式配方
RecipeBuilder.newBuilder("meteor_ritual_iron", "my_machine", 200)
    .addEnergyPerTickInput(1000)
    .addItemInput(<minecraft:iron_ingot> * 4)
    .addItemInput(<bloodmagic:slate> * 8)
    .addMeteorOutput("minecraft:iron_ingot")  // 使用铁锭作为催化剂
    .build();
```

#### 高级示例 - 使用血魔法专用催化剂
```zenscript
import mods.modularmachinery.RecipeBuilder;

// 创建一个更高级的坠星仪式配方
RecipeBuilder.newBuilder("meteor_ritual_advanced", "advanced_altar", 400)
    .addEnergyPerTickInput(5000)
    .addItemInput(<bloodmagic:slate:3> * 16)        // 以太石板
    .addItemInput(<bloodmagic:demon_crystal> * 4)   // 恶魔水晶
    .addFluidInput(<liquid:lifeessence> * 10000)    // 生命精华
    .addMeteorOutput("bloodmagic:slate:3")          // 血魔法催化剂
    .build();
```

## 注意事项

1. **催化剂物品必须存在**: 确保指定的催化剂物品在游戏中存在且已注册
2. **血魔法配置**: 确保血魔法模组中有对应催化剂的坠星配置
3. **机器配置**: 你的机器必须有对应的坠星输出端口 (Meteor Provider Output)

## 机器配置要求

你的机器必须包含坠星输出端口才能使用此功能：

```json
{
  "registryname": "my_machine",
  "localizedname": "我的机器",
  "parts": [
    {
      "elements": [
        {
          "provider": {
            "registryname": "modularmachineryaddons:blockmeteorprovideroutput"
          }
        }
      ]
    }
  ]
}
```

## 可用的催化剂示例

常见的血魔法催化剂物品（请根据实际模组版本确认）：
- `minecraft:iron_ingot` - 铁锭
- `minecraft:gold_ingot` - 金锭  
- `minecraft:diamond` - 钻石
- `bloodmagic:slate` - 空白石板
- `bloodmagic:slate:1` - 强化石板
- `bloodmagic:slate:2` - 注魔石板
- `bloodmagic:slate:3` - 以太石板

注意：具体可用的催化剂取决于血魔法模组的配置和版本。
