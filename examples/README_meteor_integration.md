# 血魔法坠星仪式 (Meteor) ZenScript 适配

## 概述

本次更新为 MMCE-Addons 添加了血魔法坠星仪式的 ZenScript 适配支持，使用户能够通过 CraftTweaker 脚本在模块化机械配方中定义坠星仪式输出。

## 修改的文件

### 核心代码修改

1. **`AddonsPrimer.java`**
   - 添加了 `RequirementMeteor` 的导入
   - 新增了 `addMeteorOutput()` ZenScript 方法

## 新增的功能

### ZenScript 方法

```zenscript
mods.modularmachinery.RecipePrimer.addMeteorOutput(RecipePrimer primer, String catalystItem)
```

- **参数**: 
  - `primer`: 配方原型对象
  - `catalystItem`: 催化剂物品的注册名 (例如: "minecraft:iron_ingot")

### 使用示例

```zenscript
import mods.modularmachinery.RecipeBuilder;

RecipeBuilder.newBuilder("meteor_ritual", "my_machine", 200)
    .addEnergyPerTickInput(1000)
    .addItemInput(<bloodmagic:slate> * 4)
    .addFluidInput(<liquid:lifeessence> * 2000)
    .addMeteorOutput("minecraft:iron_ingot")  // 使用铁锭作为催化剂
    .build();
```

## 前置要求

- 血魔法 (Blood Magic) 模组
- 模块化机械 (Modular Machinery)
- CraftTweaker
- 机器必须包含坠星输出端口 (`modularmachineryaddons:blockmeteorprovideroutput`)

## 技术细节

### 现有基础结构

此适配基于现有的血魔法集成代码：

- `RequirementMeteor` - 坠星仪式要求类
- `ComponentMeteor` - 坠星仪式组件类型
- `TileMeteorProvider` - 坠星仪式提供器 Tile 实体
- `RequirementTypeMeteor` - JSON 配方类型定义

### 实现原理

1. ZenScript 方法调用 `RequirementMeteor.from(catalystItem)`
2. 验证催化剂物品是否存在
3. 从血魔法的 `MeteorRegistry` 获取对应的坠星数据
4. 创建 `RequirementMeteor` 实例作为输出要求

### 错误处理

- 自动验证催化剂物品是否存在
- 检查血魔法注册表中是否有对应的坠星配置
- 提供详细的错误信息用于调试

## 示例文件

在 `examples/` 文件夹中提供了以下示例：

1. **`blood_magic_meteor_zenscript.md`** - 详细的使用说明和文档
2. **`meteor_ritual_recipe_example.json`** - JSON 配方示例
3. **`complete_meteor_setup_example.md`** - 完整的机器和配方设置示例
4. **`test_meteor_zenscript.zs`** - 测试脚本，包含多个示例配方

## 支持的催化剂

常见的血魔法催化剂物品（取决于血魔法配置）：

- `minecraft:iron_ingot` - 铁锭
- `minecraft:gold_ingot` - 金锭
- `minecraft:diamond` - 钻石
- `bloodmagic:slate` - 空白石板
- `bloodmagic:slate:1` - 强化石板
- `bloodmagic:slate:2` - 注魔石板
- `bloodmagic:slate:3` - 以太石板

## 注意事项

1. **仅支持输出**: 坠星仪式从逻辑上只能作为输出（召唤陨石），不支持输入模式
2. **催化剂验证**: 确保指定的催化剂物品在血魔法的坠星注册表中有对应配置
3. **空间要求**: 坠星仪式需要足够的空间进行陨石召唤
4. **机器配置**: 必须在机器结构中包含坠星输出端口

## 兼容性

- 与现有的血魔法集成完全兼容
- 不影响其他模组的功能
- 支持 JSON 和 ZenScript 两种配方定义方式

## 测试建议

1. 使用提供的测试脚本验证功能
2. 确认血魔法模组正确安装并配置
3. 检查机器结构包含必要的组件
4. 验证催化剂物品在游戏中存在
